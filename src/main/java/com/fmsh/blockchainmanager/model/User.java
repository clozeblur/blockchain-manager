package com.fmsh.blockchainmanager.model;

import com.fmsh.blockchainmanager.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/6 15:43
 * @Description: 成员用户
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private String username;

    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
