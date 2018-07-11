package com.fmsh.blockchainmanager.bean;

import com.fmsh.blockchainmanager.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:25
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserData extends BaseData {

    private User user;
}
