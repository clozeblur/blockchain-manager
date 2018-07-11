package com.fmsh.blockchainmanager.repository;

import com.fmsh.blockchainmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/6 16:02
 * @Description: 用户repository类
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找某个地址hash
     *
     * @param username 用户名
     * @return user
     */
    User findFirstByUsername(String username);

    /**
     * 根据地址hash查找用户
     *
     * @param address 地址hash
     * @return user
     */
    User findFirstByAddress(String address);
}
