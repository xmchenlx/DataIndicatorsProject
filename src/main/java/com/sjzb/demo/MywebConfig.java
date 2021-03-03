package com.sjzb.demo;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-03 9:57:35
 * @Description:
 */
public class MywebConfig implements WebMvcConfigurer {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new SessionListener());
        System.out.println("listener");
        return srb;
    }
}
