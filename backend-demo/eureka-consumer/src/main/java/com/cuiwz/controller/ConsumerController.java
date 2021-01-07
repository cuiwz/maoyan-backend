package com.cuiwz.controller;

import com.cuiwz.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cuiwz
 * @Date 2020/5/15 16:11
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/consumer/hello")
    public String sayHello(String message) {
        return consumerService.sayHello(message);
    }

}
