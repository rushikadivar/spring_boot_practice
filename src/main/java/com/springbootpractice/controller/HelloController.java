package com.springbootpractice.controller;


import org.hibernate.engine.internal.Cascade;
import org.hibernate.engine.spi.CascadeStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${welcome.message}")
    String welcomeMessage;

    @Value("${name}")
    String commandName;

    @GetMapping(value = "/hello")
    public String hello(){
        return welcomeMessage;
    }

    @GetMapping("/")
    public String commandName(){
        return commandName;
    }
}
