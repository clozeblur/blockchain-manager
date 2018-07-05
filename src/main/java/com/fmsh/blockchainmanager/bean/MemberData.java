package com.fmsh.blockchainmanager.bean;

import com.fmsh.blockchainmanager.model.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:25
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MemberData extends BaseData {

    private List<Member> members;
}
