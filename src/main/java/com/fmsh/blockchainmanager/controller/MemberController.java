package com.fmsh.blockchainmanager.controller;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.fmsh.blockchainmanager.bean.MemberData;
import com.fmsh.blockchainmanager.common.LeaderPersist;
import com.fmsh.blockchainmanager.manager.MemberManager;
import com.fmsh.blockchainmanager.model.Leader;
import com.fmsh.blockchainmanager.model.Member;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:35
 * @Description:
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberManager memberManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    ///**
    // * 校验某服务是否合法，结果将决定对方能不能启动
    // * @param name 公司名
    // * @param appId 节点id
    // * @param ip 节点ip
    // * @return 合法与否的标志
    // */
    //@GetMapping
    //public Integer checkId(String name, String appId, String ip) {
    //    return memberManager.checkIdAndIp(name, appId, ip);
    //}

    /**
     * 获取所有的节点信息
     * @param name name
     * @param appId appId
     * @param ip  ip
     * @return ip
     */
    @GetMapping("/memberData")
    public MemberData member(String name, String appId, String ip) {
        return memberManager.memberData(name, appId, ip);
    }

    @GetMapping("/getRandom")
    @ResponseBody
    public MemberData getRandom() {
        return memberManager.getRandom();
    }

    @GetMapping("/getLeader")
    @ResponseBody
    public Leader getLeader() {
        return LeaderPersist.getLeader();
    }

    @GetMapping("/testRedisSet")
    @ResponseBody
    public String testSet() {
        Leader leader = new Leader();
        leader.setMember(getRandom().getMembers().get(0));
        leader.setTimestamp(System.currentTimeMillis());
        String out = new String(new FastJsonRedisSerializer<>(Leader.class).serialize(leader));
        System.out.println(out);
        stringRedisTemplate.opsForValue().set("test-key", "test-value", 5, TimeUnit.MINUTES);
        stringRedisTemplate.opsForValue().set("test-leader", new String(new FastJsonRedisSerializer<>(Leader.class).serialize(leader)), 5, TimeUnit.MINUTES);
        return "ok";
    }

    @GetMapping("/testRedisGet")
    @ResponseBody
    public String testGet() {
        return stringRedisTemplate.opsForValue().get("test-key") + " \n" + (new FastJsonRedisSerializer<>(Leader.class).deserialize(stringRedisTemplate.opsForValue().get("test-leader").getBytes()));
    }
}
