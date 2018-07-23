package com.fmsh.blockchainmanager.common;

import com.fmsh.blockchainmanager.model.Leader;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/23 11:18
 * @Description:
 */
public class LeaderPersist {

    private LeaderPersist() {}

    private static Leader leader;

    public static Leader getLeader() {
        return leader;
    }

    public static void setLeader(Leader leader) {
        LeaderPersist.leader = leader;
    }
}
