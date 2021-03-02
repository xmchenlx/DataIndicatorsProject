package com.sjzb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public TomcatServletWebServerFactory servletContainer(){
//        return new TomcatServletWebServerFactory(6868) ;
//    }

}
