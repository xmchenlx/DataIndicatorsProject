package com.sjzb.demo.Result;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 9:30:00
 * @Description: 一些其他自定义工具
 */
public class lxTool {

    /**
     * @Author: chenlx
     * @Date: 2021-01-15 9:48:48
     * @Params: jsonString
     * @Return List<String></String>
     * @Description: 针对Neo4j驱动包提供的ListValue类型进行转换成普通的ArrayList
     */
    public static List<String> getListFromJson(String json) {
        if (StringUtils.isNotEmpty(json)) {
            String[] per = json.replace("[", "").replace("]", "").replace("\"", "").split(",");

            String[] permissions = new String[per.length];
            for (int i = 0; i < per.length; i++)
                if (StringUtils.isNotEmpty(per[i]) && per[i].length() > 2) {
//                    permissions[i] = per[i].substring(1, per[i].length() - 1);
                    permissions[i] = per[i];
                }
            return Convert.convert(List.class, permissions);
        }
        return null;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-15 11:29:25
     * @Params: null
     * @Return
     * @Description: 去除伪json的符号，转成普通String格式，虽然没什么必要
     */
    public static String getStringFromStringValue(String json) {
        String per = json.replace("[", "").replace("]", "").replace("\"", "");

        return Convert.convert(String.class, per);
    }

    public String nodeTypeConvert2ZHCN(String name) {
        if (name == "CodeNodeEntity")
            return "代码";
        else if (name == "BasicAndClassWordEntity")
            return "标准别名";
//            return "基本词类词";
        else if (name == "")
            return "标准别名";
        else
            return "未知";
    }


    public Map<String, Object> generateRes() {
        Map<String, Object> res = new HashMap<>();
        return res;
    }


    public String getWebCode(String key) {
        String res = "";
        if (key == "<sec-style>") {
            res = "<style>html{width:100%;}span{display:-moz-inline-box;display:inline-block}.infotitle{text-align:left;width:5rem;max-width:5rem;text-align:right}.infotext{text-align:left;font-weight:bold}pre{font-family:'微软雅黑';max-width:20rem;border:3px solid lightcoral;padding:1rem;margin:0 auto}</style>";
        }
        if (key == "<list-style>") {
            res = "<style>.listText{color:darkred; font-size:16px;margin:0;padding:0;} .listText:hover{color:coral;font-weight:bold;cursor:pointer} .listText:active{color:darksalmon;}</style>";
        }
        if (key == "<newHtml-pre>") {
            res = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no\" /><meta name=\"format-detection\" content=\"telephone=no\"><title>huaci</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./iconfont.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"./huaci.css\" /></head><body><div id=\"doc\"><div id=\"main\">";
        }
        if (key == "<newHtml-after>") {
            res = "</div></div><script src=\"./weblibs.js\"></script><script src=\"./huaci.js\"></script></body></html>";
        }


        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-04 9:37:15
     * @Params: null
     * @Return
     * @Description: 获取项目运行的ip地址
     */
    public String getLocalHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostAdd = address.getHostAddress();
        return hostAdd;
    }


    public void soutLog(String type, String key, String note) {
        Calendar cla = Calendar.getInstance();  // Calendar 函数是获取时间
//        获取当前年月日
        int year = cla.get(Calendar.YEAR);
        int month = cla.get(Calendar.MONTH) + 1; //从0开始 所以+1
        int day = cla.get(Calendar.DATE);
        int hour = cla.get(Calendar.HOUR);
        int min = cla.get(Calendar.MINUTE);
        int sec = cla.get(Calendar.SECOND);
        String _now = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
        String res = "\n\n于" + _now + "收到 " + type + "，关键词：" + key;
        if (note != null) res += "，备注：" + note;
        System.out.println(res);
    }
}
