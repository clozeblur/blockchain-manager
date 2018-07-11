package com.fmsh.blockchainmanager.model;

import com.fmsh.blockchainmanager.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:28
 * @Description: 联盟链，多个节点组成一个group，一个group为一个联盟链
 */
@Entity
@Table(name = "member_group")
public class MemberGroup extends BaseEntity {

    private String name;
    /**
     * 设置一个业务id
     */
    private String groupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
