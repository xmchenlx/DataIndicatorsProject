package com.sjzb.demo.Result;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.types.Path;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

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
        else if (name == "ReportEntity")
            return "报表/报文";
        else
            return "未知";
    }


    public String getScriptCdnUrl(String key) {
        if (key.equals("jquery")) {
            return "https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js";
        } else if (key.equals("handlebars")) {
            return "https://cdn.bootcdn.net/ajax/libs/handlebars.js/4.7.6/handlebars.min.js";
        } else if (key.equals("script-echart")) {
            return "<script src=\"https://cdn.bootcdn.net/ajax/libs/echarts/5.0.1/echarts.min.js\"></script>";
        } else {
            return "";
        }
    }

    public String getWebCode(String key) {
        String res = "";
        if (key == "<sec-style>") {
            res = "<style>#chartDiv{display:none;height: 50vh;border: 3px solid lightcoral;width: 50rem;padding: 1rem;margin: 0 auto;}#title{text-align:center;background-color:lightcoral;margin:-1.2rem -1.1rem 0 -1.1rem;white-space:normal;color:white;padding:0.5rem 0;word-break:break-all;font-size:1.2rem;}span{display:-moz-inline-box;display:inline-block;vertical-align:middle;white-space:normal;word-break:break-all;font-weight:bold}.infotitle{vertical-align:top;text-align:left;width:5rem;max-width:5rem;text-align:right}.infotext{font-weight:normal;word-wrap:break-word;max-width:80%;text-align:left;}pre{font-family:'微软雅黑';min-width: 20rem;max-width: 50rem;border:3px solid lightcoral;padding:1rem;margin:0 auto}</style>";
        }
        if (key == "<list-style>") {
            res = "<style>.listText{color:darkred; font-size:16px;margin:0;padding:0;} span{display:inline-block;vertical-align:middle;white-space:normal;word-break:break-all;} .listText:hover{color:coral;font-weight:bold;cursor:pointer} .listText:active{color:darksalmon;}</style>";
        }
        if (key == "<newHtml-pre>") {
            res = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no\" /><meta name=\"format-detection\" content=\"telephone=no\"><title>${replaceTitle}</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./iconfont.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"./huaci.css\" /></head><body><div id=\"doc\"><div id=\"main\">";
        }
        if (key == "<newHtml-after>") {
            res = "</div></div><script src=\"./weblibs.js\"></script><script src=\"./huaci.js\"></script></body></html>";
        }
        if (key.equals("<html-handlebars>")) {
            res = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>${title}</title>     ${style}</head><body><div id=\"container\"></div> ${loadScript}<script type=\"text/x-handlebars-template\" id=\"template-user\"><pre><h1 id=\"title\">{{res.nm}}</h1>{{{showBaseProperty res datasource}}} <hr>            {{{mixCdCmnt res.cd res.cmnt}}}</pre></script> ${chartModule} <script>        Handlebars.registerHelper(\"mixCdCmnt\", function ($cd, $cmnt) {            let $res = '';            for (let $i = 0; $i< $cd.length; $i++) {                $res += '<br><strong>' + $cd[$i] + '</strong>\\t' + $cmnt[$i]            }            return $res;        });Handlebars.registerHelper(\"showBaseProperty\",function($res,$datasource){            let $r= '';            for(let obj in $res){  if(obj=='cd') continue;            $r+= '<span class=\"infotitle\">'+$datasource[obj] +'：</span><span class=\"infotext\">'+$res[obj]+'</span><br>';            }            return $r;        });        var jsoninfo = ${jsoninfo}   ;           var $container = $('#container');        var source = $('#template-user').html();        var template = Handlebars.compile(source);       var html2 = template(jsoninfo);       $container.html(html2);</script></body></html>";
        }
//        if(key.equals("<html-handlebars-after>"));


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
        String res = "\n\n于" + _now + " 收到【" + type + "】请求，关键词：" + key;
        if (note != null) res += "，备注：" + note;
        System.out.println(res);
    }

    public String writeTempNote(String note) {
        String detailNote = "";
        if (note == "数据元缺失") {
            detailNote = "该节点隶属的数据元缺少部分或全部的定义，程序已自动写回原英文属性名称。";
        } else if (note == "通用展示")
            detailNote = "该节点还没设置属性展示，已使用通用字段展示";
        return "<p class='listText' style='color:white;white-space:normal;word-break:break-all;background-color:darkred;font-weight:bold;font-size:1rem'>#临时注释#" + detailNote + "</p>";
    }

    public String generateRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String convertMapToJsonString(Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        String s = JSONObject.toJSONString(map);
        return s;
    }

    public String convertListToJsonString(List<Object> map) {
        String json = JSONUtil.toJsonStr(map);
        return json;
    }

    public Map<Integer, Object> ConvertPathValueToRelationshipMap(List<Object> objs, String queryNm) {
        Map<Integer, Object> res = new HashMap<>();
        for (int i = 0; i < objs.size(); i++) {

            PathValue a = (PathValue) objs.get(i);
            Path path = a.asPath();
            String startNodeName = path.start().get("Nm").toString().replace("\"", "");
            String endNodeName = path.end().get("Nm").toString().replace("\"", "");

            Map<String, Object> t = new HashMap<>();
            Long myselfId;
//            if (i == 0) {
                if (startNodeName.equals(queryNm)) {
                    myselfId = path.start().id();
                } else {
                    myselfId = path.end().id();
                }
                t.put("myselfId", myselfId);

//            }
            t.put("startnode", startNodeName);
            t.put("endnode", endNodeName);  //getName + , + getId
            t.put("startnodeId", path.start().id());
            t.put("endnodeId", path.end().id());
            t.put("typename", path.relationships().iterator().next().type());
            res.put(i, t);
        }

        return res;
    }

    public Map<Integer, Object> tempConvertPathValueToRelationshipMap(List<Object> objs) {
        Map<Integer, Object> res = new HashMap<>();
        for (int i = 0; i < objs.size(); i++) {
            Object aa = objs.get(i);

            PathValue a = (PathValue) objs.get(i);
            Path path = a.asPath();
            String json = JSONObject.toJSONString(path);

            Map<String, Object> t = new HashMap<>();
            t.put("startnode", path.start().get("Nm").toString().replace("\"", ""));
            t.put("endnode", path.end().get("Nm").toString().replace("\"", ""));  //getName + , + getId
            t.put("typename", path.relationships().iterator().next().type());
            res.put(i, t);
        }

        return res;
    }
}
