package com.fmsh.blockchainmanager.manager;

import com.fmsh.blockchainmanager.bean.BaseData;
import com.fmsh.blockchainmanager.bean.UserData;
import com.fmsh.blockchainmanager.common.Constants;
import com.fmsh.blockchainmanager.model.User;
import com.fmsh.blockchainmanager.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:33
 * @Description: user管理类
 */
@Component
public class UserManager {

    @Resource
    private UserRepository userRepository;

    public UserData userData(String username) {
        UserData userData = new UserData();
        if (StringUtils.isEmpty(username)) {
            userData.setCode(-1);
            userData.setMessage("用户名为空");
        }
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            userData.setCode(-1);
            userData.setMessage("用户不存在");
        } else if (StringUtils.isEmpty(user.getAddress())) {
            userData.setCode(-1);
            userData.setMessage("地址hash异常");
        } else {
            userData.setCode(0);
            userData.setUser(user);
        }
        return userData;
    }

    public BaseData register(String username, String address) {
        BaseData base = new BaseData();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(address)) {
            base.setCode(Constants.FAIL);
            base.setMessage("用户名或地址hash异常");
            return base;
        }
        if (userRepository.findFirstByUsername(username) != null) {
            base.setCode(Constants.FAIL);
            base.setMessage("用户名已存在");
            return base;
        } else if (userRepository.findFirstByAddress(address) != null) {
            base.setCode(Constants.FAIL);
            base.setMessage("地址hash已存在");
            return base;
        }
        User user = new User();
        user.setUsername(username);
        user.setAddress(address);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userRepository.save(user);
        base.setCode(Constants.SUCCESS);
        return base;
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
