package com.xsm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xsm
 * @date 2019/10/14 14:29
 */
@RestController
@RefreshScope // 打开动态刷新功能
public class TestRefreshScopeController {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private Integer age;

    @GetMapping(value = "/test1/{param}")
    public String test(@PathVariable("param") String param){
        return userName + "-" + param + "-" + age;
    }

}
