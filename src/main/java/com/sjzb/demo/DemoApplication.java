package com.sjzb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import java.io.IOException;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.sjzb.demo.SessionListener")
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) throws IOException {

//        redisService.goRun(5);
        SpringApplication.run(DemoApplication.class, args);

    }


}
