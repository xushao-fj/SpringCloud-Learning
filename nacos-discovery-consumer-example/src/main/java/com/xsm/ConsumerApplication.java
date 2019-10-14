package com.xsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xsm
 * @Date 2019/10/13 11:30
 */
@EnableFeignClients(basePackages = {"com.xsm.feign"})
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {


    @LoadBalanced // 负载均衡调用服务
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }



}
