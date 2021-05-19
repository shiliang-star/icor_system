package com.shiliang.icor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName ApplicationContextConfig.java
 * @Description TODO
 * @createTime 2021年04月17日 22:39:00
 */
@Configuration
public class ApplicationContextConfig {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
