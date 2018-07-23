package com.fmsh.blockchainmanager.common;

import com.fmsh.blockchainmanager.model.Leader;
import com.fmsh.blockchainmanager.model.Member;
import com.fmsh.blockchainmanager.repository.MemberRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/23 9:12
 * @Description:
 */
@Component
public class LeaderConfig {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        selectLeader();
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void task() {
        selectLeader();
    }

    private void selectLeader() {
        List<Member> members = memberRepository.findAll();
        if (CollectionUtils.isEmpty(members)) return;

        int i = new Random().nextInt(members.size());
        Member leaderMember = members.get(i);

        long timestamp = System.currentTimeMillis();
        Leader leader = new Leader();
        leader.setMember(leaderMember);
        leader.setTimestamp(timestamp);

        LeaderPersist.setLeader(leader);
        stringRedisTemplate.convertAndSend("manager", leader);
    }
}
