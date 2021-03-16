package com.sjzb.demo.service;

import cn.hutool.core.date.DateTime;

import java.util.Date;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-12 14:09:33
 * @Description: 定时任务类
 */
public class TaskTimer {
    public static void main(String[] args) {
        //获取当前日期
        Date date = new Date();
        DateTime today = new DateTime(date);
        String todayString =today.toString("yyyy-MM-dd");
        System.out.println(todayString);


    }
}
