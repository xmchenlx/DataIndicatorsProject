package com.sjzb.demo;

import com.sjzb.demo.config.SystemSetting;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-02-23 9:38:26
 * @Description: 伴随服务启动时一同启动的函数
 */
@Component
@Order(value = 1)
public class StartComponent implements ApplicationRunner {
    private static SystemSetting sysTool = new SystemSetting();

    @Override
    public void run(ApplicationArguments args)
            throws Exception {
        System.out.println("【配置词典】即将打开有道词典代理设置外部程序来链接数据指标词典数据库。");
//        sysTool.runYodaoDictProxySetting();

    }
}
