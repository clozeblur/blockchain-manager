package com.fmsh.blockchainmanager.repository;

import com.fmsh.blockchainmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/6 16:02
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
