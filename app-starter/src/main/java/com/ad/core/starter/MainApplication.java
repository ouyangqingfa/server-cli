package com.ad.core.starter;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author CoderYoung
 */
@EnableOpenApi
@EnableKnife4j
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.ad.core", "com.ad.modules"})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
