package com.fmsh.blockchainmanager.manager;

import com.fmsh.blockchainmanager.repository.MemberGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:34
 * @Description:
 */
@Component
public class MemberGroupManager {

    @Resource
    private MemberGroupRepository memberGroupRepository;
}
