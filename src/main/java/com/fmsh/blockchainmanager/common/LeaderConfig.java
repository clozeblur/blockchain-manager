package com.fmsh.blockchainmanager.common;

import com.alibaba.fastjson.JSONObject;
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

    @Scheduled(cron = "0/10 * * * * ?")
    public void task() {
        selectLeader();
    }

    private void selectLeader() {
        List<Member> members = memberRepository.findAll();
        if (CollectionUtils.isEmpty(members)) return;
//        Random r = new Random();
//        r.setSeed(System.currentTimeMillis());
//        int i = r.nextInt(members.size());
//        Member leaderMember = members.get(i);
//
        long timestamp = System.currentTimeMillis();
        Member leaderMember = new Member();
        leaderMember.setAppId("node1");
        leaderMember.setGroupId("1");
        leaderMember.setName("node1");
        leaderMember.setIp("192.168.95.133");
        leaderMember.setPort(14000);

        Leader leader = new Leader();
        leader.setMember(leaderMember);
        leader.setTimestamp(timestamp);

        LeaderPersist.setLeader(leader);
        stringRedisTemplate.convertAndSend("manager", JSONObject.toJSONString(leader));
    }
}
