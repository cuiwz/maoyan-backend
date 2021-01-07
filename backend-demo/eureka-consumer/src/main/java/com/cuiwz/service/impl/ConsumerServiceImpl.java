package com.cuiwz.service.impl;

import com.cuiwz.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author cuiwz
 * @Date 2020/5/15 16:10
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private LoadBalancerClient eurekaClient;

    @Override
    public String sayHello(String message) {
        // 服务提供者信息
//        String hostname = "localhost";
//        int port = 7101;
//        String uri = "/provider/hello?message=" + message;

        // get register
//        ServiceInstance service = eurekaClient.choose("hello-service-provider");
//        String hostname = service.getHost();
//        int port = service.getPort();
        String uri = "/provider/hello?message=" + message;

        String url = "http://hello-service-provider" + uri;
        // invoker provider test
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }

}
