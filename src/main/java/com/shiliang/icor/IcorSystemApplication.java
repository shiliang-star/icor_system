package com.shiliang.icor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author sl
 * @Date 2021/2/23 17:38
 * @Description TODO
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.shiliang"})
public class IcorSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(IcorSystemApplication.class, args);
    }
}
