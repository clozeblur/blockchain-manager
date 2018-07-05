package com.fmsh.blockchainmanager.repository;

import com.fmsh.blockchainmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:30
 * @Description:
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 查询同一个联盟内，除自己外其他的所有节点
     *
     * @param groupId
     *         groupId
     * @param id
     *         自己节点id
     * @return 成员集合
     */
    List<Member> findByGroupIdAndAppIdNot(String groupId, String id);

    /**
     * 查询某个节点
     *
     * @param appId
     *         appId
     * @return Member
     */
    Member findFirstByAppId(String appId);

    /**
     * 查询一个联盟内所有节点
     *
     * @param groupId
     *         groupId
     * @return 成员集合
     */
    List<Member> findByGroupId(String groupId);

    /**
     * 根据机构名查询
     *
     * @param name
     *         name
     * @return Member
     */
    Member findFirstByName(String name);
}
