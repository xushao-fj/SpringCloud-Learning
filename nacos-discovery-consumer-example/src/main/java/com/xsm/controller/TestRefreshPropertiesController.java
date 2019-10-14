package com.xsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xsm
 * @date 2019/10/14 15:15
 */
@RestController
public class TestRefreshPropertiesController {

    @Autowired
    private TestProperties testProperties;

    @GetMapping(value = "/test2/{param}")
    public String test(@PathVariable("param") String param){
        return testProperties.getName() + "-" + param + "-" + testProperties.getAge();
    }

}
