package com.fmsh.blockchainmanager.controller;

import com.fmsh.blockchainmanager.bean.BaseData;
import com.fmsh.blockchainmanager.bean.UserData;
import com.fmsh.blockchainmanager.manager.UserManager;
import com.fmsh.blockchainmanager.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserManager userManager;

    /**
     * 获取用户地址hash信息
     *
     * @param username username
     * @return userData
     */
    @GetMapping("/getUser")
    @ResponseBody
    public UserData member(@RequestParam("username") String username) {
        return userManager.userData(username);
    }

    @PostMapping("/register")
    @ResponseBody
    public BaseData register(String username, String address) {
        return userManager.register(username, address);
    }

    @GetMapping("/listUsers")
    @ResponseBody
    public List<User> listUsers() {
        return userManager.list();
    }
}
