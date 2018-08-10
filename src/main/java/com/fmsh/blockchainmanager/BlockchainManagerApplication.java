package com.fmsh.blockchainmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: yuanjiaxin
 * @Date: 2018/7/5 16:21
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class BlockchainManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainManagerApplication.class, args);
    }
}
