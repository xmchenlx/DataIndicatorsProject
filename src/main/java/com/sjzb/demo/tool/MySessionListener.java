package com.sjzb.demo.tool;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-02-26 16:48:37
 * @Description: Session监听
 */
//@WebListener
public class MySessionListener implements  HttpSessionListener {
    public static Map userMap = new HashMap();
    private MySessionContext myc=MySessionContext.getInstance();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        myc.AddSession(httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.DelSession(session);
    }
}
