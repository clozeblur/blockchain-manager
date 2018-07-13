package com.fmsh.blockchainmanager.manager;

import com.fmsh.blockchainmanager.bean.MemberData;
import com.fmsh.blockchainmanager.model.Member;
import com.fmsh.blockchainmanager.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:33
 * @Description:
 */
@Component
public class MemberManager {

    @Resource
    private MemberRepository memberRepository;

    /**
     * 查询某成员的groupId
     * @param memberName
     * 成员名
     * @return
     * 分组id
     */
    public String findGroupId(String memberName) {
        Member member = memberRepository.findFirstByName(memberName);
        if (member != null) {
            return member.getGroupId();
        }
        return null;
    }

    public MemberData memberData(String name, String appId, String ip) {
        MemberData memberData = new MemberData();
        Member member = memberRepository.findFirstByAppId(appId);
        //客户不存在
        if (member == null) {
            memberData.setCode(-1);
            memberData.setMessage("客户不存在");
        } else if (!member.getName().equals(name)) {
            //name错误
            memberData.setCode(-2);
            memberData.setMessage("name错误");
        } else if (!member.getIp().equals(ip)) {
            //ip错误
            memberData.setCode(-3);
            memberData.setMessage("ip错误");
        } else {
            memberData.setCode(0);
            String groupId = findGroupId(name);
            //如果该member是合法的，则返回给他联盟内所有的成员列表
            List<Member> members = memberRepository.findByGroupId(groupId);
            memberData.setMembers(members);
        }
        return memberData;
    }

    public MemberData getRandom() {
        MemberData memberData = new MemberData();
        List<Member> members = memberRepository.findAll();
        if (!CollectionUtils.isEmpty(members)) {
            int i = new Random().nextInt(members.size());
            memberData.setCode(0);
            memberData.setMembers(Collections.singletonList(members.get(i)));
        } else {
            memberData.setCode(-1);
            memberData.setMessage("没有合适的投票节点");
        }
        return memberData;
    }
}
