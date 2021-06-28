package com.sjzb.demo.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.sjzb.demo.config.SystemSetting;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.internal.value.ListValue;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.internal.value.StringValue;
import org.neo4j.driver.types.Path;

import java.util.*;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 9:30:00
 * @Description: 一些其他自定义工具
 */
public class lxTool {
    private SystemSetting sysTool = new SystemSetting();

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

    public String getStringFromListValueOrStringValue(Object olist) {
        String res = "";

        if (olist.getClass().getName().equals("org.neo4j.driver.internal.value.StringValue")) {
            StringValue a = (StringValue) olist;

            return a.asString();
        }
        ListValue lvlist = (ListValue) olist;
        for (int i = 0; i < lvlist.size(); i++) {
            if (i != 0) res += "、";
            res += lvlist.get(i);
        }
        res = res.replace("\"", "");
        return res;
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
        if (name == "BasicAndClassWordEntity")
            return "基本词类词";
//            return "基本词类词";
        if(name == "IndicatorsNodeEntity")
            return "指标";
        if (name == "")
            return "标准别名";
        if (name == "ReportEntity")
            return "报表/报文";
        return "未知";
    }


    public String getScriptCdnUrl(String key) {
        if (key.equals("jquery")) {
            return "https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js";
        } else if (key.equals("handlebars")) {
            return "https://cdn.bootcdn.net/ajax/libs/handlebars.js/4.7.6/handlebars.min.js";
        } else if (key.equals("handlebars_local")) {
            return "";
        } else if (key.equals("script-echart")) {
            return "<script src=\"https://cdn.bootcdn.net/ajax/libs/echarts/5.0.1/echarts.min.js\"></script>";
        } else {
            return "";
        }
    }

    public String getWebCode(String key) {
        String res = "";
        if (key == "<sec-style>") {
            return "<style>#chartDiv{display:none;height: 50vh;border: 3px solid lightcoral;width: 50rem;padding: 1rem;margin: 0 auto;}#title{text-align:center;background-color:lightcoral;margin:-1.2rem -1.1rem 0 -1.1rem;white-space:normal;color:white;padding:0.5rem 0;word-break:break-all;font-size:1.2rem;}span{display:-moz-inline-box;display:inline-block;vertical-align:middle;white-space:normal;word-break:break-all;font-weight:bold}.infotitle{vertical-align:top;text-align:left;width:8rem;max-width:8rem;text-align:right}.infotext{font-weight:normal;word-wrap:break-word;max-width:80%;text-align:left;}pre{font-family:'微软雅黑';min-width: 20rem;max-width: 50rem;border:3px solid lightcoral;padding:1rem;margin:0 auto}</style>";

        }
        if (key == "<list-style>") {
            return "<style>#tinytext{font-size:10px}.listText{color:darkred; font-size:16px;margin:0;padding:0;} span{display:inline-block;vertical-align:middle;white-space:normal;word-break:break-all;} .listText:hover{color:coral;font-weight:bold;cursor:pointer} .listText:active{color:darksalmon;}</style>";
        }
        if (key == "<newHtml-pre>") {
            return "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no\" /><meta name=\"format-detection\" content=\"telephone=no\"><title>${replaceTitle}</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./iconfont.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"./huaci.css\" /></head><body><div id=\"doc\"><div id=\"main\">";
        }
        if (key == "<newHtml-after>") {
            return "</div></div><script src=\"./weblibs.js\"></script><script src=\"./huaci.js\"></script></body></html>";
        }
        if (key.equals("<html-handlebars>")) {
            return "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>${title}</title>     ${style}</head><body><div id=\"container\"></div> ${loadScript}<script type=\"text/x-handlebars-template\" id=\"template-user\"><pre><h1 id=\"title\">{{res.nm}}</h1>{{{showBaseProperty res datasource}}} <hr>            {{{mixCdCmnt res.cd res.cmnt}}}</pre></script> ${chartModule} <script>        Handlebars.registerHelper(\"mixCdCmnt\", function ($cd, $cmnt) {            let $res = '';            for (let $i = 0; $i< $cd.length; $i++) {                $res += '<br><strong>' + $cd[$i] + '</strong>\\t' + $cmnt[$i]            }            return $res;        });Handlebars.registerHelper(\"showBaseProperty\",function($res,$datasource){            let $r= '';            for(let obj in $res){  if(obj=='cd') continue; if(obj=='cmnt') continue;            $r+= '<span class=\"infotitle\">'+$datasource[obj] +'：</span><span class=\"infotext\">'+$res[obj]+'</span><br>';            }            return $r;        });        var jsoninfo = ${jsoninfo}   ;           var $container = $('#container');        var source = $('#template-user').html();        var template = Handlebars.compile(source);       var html2 = template(jsoninfo);       $container.html(html2);</script></body></html>";
        }
        if (key.equals("<settingPage>")) {
            return "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0\"><title>类别顺序自定义</title><style>#main{margin:0 auto;width:80%}#itembox div{border:1px solid gray;list-style:none;padding:5px;margin:5px;max-width:50vw}</style></head><body><div id=\"main\"><p>勾选以下选项代表是否查询、拖拽选项进行顺序排序。</p><div id=\"itembox\"></div><input type=\"button\"value=\"提交设置\"onclick=\"savingSetting()\"style=\"margin: 0 auto;\"></input></div><script>var dataClassInfo=${javaDataReplace};function loadDataClass(){let res='';let items=dataClassInfo.order;let index=0;items.forEach(obj=>{res+='<div draggable=\"true\"><input  type=\"checkbox\" id=\"di'+obj+'\">'+(++index)+'、'+dataClassInfo.data_ch[obj]+'</input></div>'});document.getElementById('itembox').innerHTML=res;dataClassInfo.select.forEach(se=>{document.getElementById('di'+se).checked=true})};function dragg(){var box=document.querySelector('#itembox').getElementsByTagName('div');var content=null;for(let i=0;i<box.length;i++){box[i].ondragstart=function(){content=this};box[i].ondragover=function(){event.preventDefault()};box[i].ondrop=function(){if(content!=null&&content!=this){var temp=document.createElement(\"div\");document.querySelector(\"#itembox\").replaceChild(temp,this);document.querySelector(\"#itembox\").replaceChild(this,content);document.querySelector(\"#itembox\").replaceChild(content,temp)}}}};function savingSetting(){let newBox=document.querySelector('#itembox').getElementsByTagName('div');let newOrder=[],newSelect=[];for(let i=0;i<newBox.length;i++){let no=newBox[i].getElementsByTagName('input')[0];let sid=no.id.replace('di','');let scheck=no.checked;newOrder.push(sid);if(scheck==true)newSelect.push(sid)}saveToLocalstorage(newOrder,newSelect)}function saveToLocalstorage(newOrder,newSelect){dataClassInfo.select=newSelect;dataClassInfo.order=newOrder;dataClassInfo.jsessionid = '${JSESSIONID}';var httpRequest=new XMLHttpRequest();let url='http://" + sysTool.getLocalHost() + ":6868/fsettingUpload';httpRequest.open('POST',url,true);httpRequest.setRequestHeader(\"Content-type\",\"application/x-www-form-urlencoded\");httpRequest.send('body='+JSON.stringify(dataClassInfo));httpRequest.onreadystatechange=function(){if(httpRequest.readyState==4&&httpRequest.status==200){var json=httpRequest.responseText;if(json=='FINISHED')alert('设置已保存。')}}}loadDataClass();dragg();</script></body></html>";
        }
//        if(key.equals("<html-handlebars-after>"));


        return res;
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
        String res = "\n\n于" + _now + " 进行【" + type + "】，关键词：" + key;
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
