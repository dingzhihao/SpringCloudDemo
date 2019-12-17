package com.hao.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 可以在SpringBoot启动的时候运行一些特定代码
 */
@Component
public class Runner1 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...");
    }

}