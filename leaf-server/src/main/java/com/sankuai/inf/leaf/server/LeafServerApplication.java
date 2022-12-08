package com.sankuai.inf.leaf.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackageClasses = {gaozhi.online.base.ScanClass.class, LeafServerApplication.class})
public class LeafServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeafServerApplication.class, args);
    }
}
