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
        sysTool.runYodaoDictProxySetting();

        System.out.println("启动工作即将完成。服务器的IP地址为："+sysTool.getLocalHost()+":6868 。");

    }
}
