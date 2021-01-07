package com.cuiwz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author cuiwz
 * @Date 2020/5/15 16:02
 */
@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/hello")
    public String providerSayHello(String message) {
        log.error("Provider say hello port:{}, message:{}", port, message);

        return "Provider say hell port:" + port + ", message:" + message;
    }

}
