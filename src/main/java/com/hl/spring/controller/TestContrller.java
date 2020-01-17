package com.hl.spring.controller;

import com.hl.spring.mvcframework.annotation.Controller;
import com.hl.spring.mvcframework.annotation.HLAutoWired;
import com.hl.spring.mvcframework.annotation.RequestMapping;
import com.hl.spring.service.impl.TestService;

@Controller
@RequestMapping("/test")
public class TestContrller {

    @HLAutoWired
    private TestService testService;

    @RequestMapping("/test")
    public void test(){
        System.out.println(123);
    }

}
