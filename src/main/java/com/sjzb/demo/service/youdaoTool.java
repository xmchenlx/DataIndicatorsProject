package com.sjzb.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.sjzb.demo.tool.SystemSetting;
import com.sjzb.demo.tool.lxTool;
import com.sjzb.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-20 15:03:45
 * @Description:
 */
@Component
public class youdaoTool {

    private lxTool lxtool = new lxTool();
    private List<Long> validNodeIdList;

    @Autowired
    dataSourceServiceImpl dsService;

    private SystemSetting sysTool = new SystemSetting();


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 14:41:50
     * @Params: null
     * @Return
     * @Description: 首次查询时，不查询所有的信息，而是取出摘要形成信息列表并且返回
     */
    public String translationList(String dataString, int count, String a) {
        String res = "找到" + count + "项结果";
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[")
                .append(res)
                .append("]]></return-phrase>")
                .append("<custom-translation>")
                .append("<translation><content>")
                .append("<![CDATA[")
                .append(a)
                .append(dataString)
                .append("]]></content></translation>")
                .append("</custom-translation>")
                .append("</yodaodict>");
        return youdaodictSb.toString();
    }

    public String getCnByNm(DataSourceEntity dse, String nm) {
        String res = "";
        if (dse.getNm() == null) return nm;
        for (int i = 0; i < dse.getAttr().size(); i++) {
            if (dse.getAttr().get(i).equals(nm)) {
                res = dse.getCn().get(i);
                break;
            }
        }
        if (res.equals("")) res = nm;
        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-08 15:35:20
     * @Params: null
     * @Return
     * @Description: 查询结果在新页面呈现，使用handlebars进行数据展示。
     */
    public String translationForHTML(String queryKey, List<?> nodeList, String nodeType, List<String> nodeTagList, List<DataSourceEntity> dataSourceList, Map<Integer, Object> nodeRelation) {
        String html = "";
        JSONObject jsonResult = new JSONObject();
        JSONObject jsonRes = new JSONObject();
        JSONObject jsonDataSource = new JSONObject();
//        JSONObject jsonGraph = new JSONObject();
        List<Object> jsonGraph = new ArrayList<>();
        if (nodeType == "CodeNodeEntity") {
//            for (int i = 0; i < nodeList.size(); i++) {
            //LIKE模糊查询的结点个数遍历
            BaseNodeEntity tempb = (BaseNodeEntity) nodeList.iterator().next();
            //遍历节点的标签
            jsonRes.put("nm", tempb.getNm());
            jsonDataSource.put("nm", getCnByNm(dataSourceList.get(0), "Nm"));
            String tempSrc = "";
            for (int x = 0; x < nodeTagList.size(); x++) {
                tempSrc += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
            }
            jsonRes.put("src", tempb.getSrc());
            jsonDataSource.put("src", getCnByNm(dataSourceList.get(0), "Src"));
            jsonRes.put("label", tempSrc);
            jsonDataSource.put("label", getCnByNm(dataSourceList.get(0), "节点标签"));
            jsonRes.put("ver", tempb.getVer());
            jsonDataSource.put("ver", getCnByNm(dataSourceList.get(0), "Ver"));
            jsonRes.put("cd", tempb.getCd());
            jsonDataSource.put("cd", getCnByNm(dataSourceList.get(0), "Cd"));
            jsonRes.put("cmnt", tempb.getCmnt());
            jsonDataSource.put("cmnt", getCnByNm(dataSourceList.get(0), "Cmnt"));
            jsonResult.put("res", jsonRes);
            jsonResult.put("datasource", jsonDataSource);
//            }
//            处理基本词类词结点
        } else if (nodeType == "BasicAndClassWordEntity") {
            BasicAndClassWordEntity tempb = (BasicAndClassWordEntity) nodeList.iterator().next();
            //遍历节点的标签
            jsonRes.put("nm", tempb.getNm());
            jsonDataSource.put("nm", getCnByNm(dataSourceList.get(0), "Nm"));
            String tempSrc = "";
            for (int x = 0; x < nodeTagList.size(); x++) {
                tempSrc += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
            }
            jsonRes.put("src", tempb.getSrc());
            jsonDataSource.put("src", getCnByNm(dataSourceList.get(0), "Src"));
            jsonRes.put("label", tempSrc);
            jsonDataSource.put("label", getCnByNm(dataSourceList.get(0), "节点标签"));
            jsonRes.put("cl", tempb.getCl());
            jsonDataSource.put("cl", getCnByNm(dataSourceList.get(0), "Cl"));

            List<String> tempCd = new ArrayList<>();
            jsonRes.put("cd", tempCd);
            jsonDataSource.put("cd", getCnByNm(dataSourceList.get(0), "Cd"));
            jsonRes.put("cmnt", tempb.getCmnt());
            jsonDataSource.put("cmnt", getCnByNm(dataSourceList.get(0), "Cmnt"));

            jsonResult.put("res", jsonRes);
            jsonResult.put("datasource", jsonDataSource);

        } else if (nodeType == "ReportEntity") {
            ReportEntity tempb = (ReportEntity) nodeList.iterator().next();
            //遍历节点的标签
            jsonRes.put("nm", tempb.getNm());
            jsonDataSource.put("nm", getCnByNm(dataSourceList.get(0), "Nm"));
            String tempSrc = "";
            for (int x = 0; x < nodeTagList.size(); x++) {
                tempSrc += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
            }
            jsonRes.put("src", tempb.getSrc());
            jsonDataSource.put("src", getCnByNm(dataSourceList.get(0), "Src"));
            jsonRes.put("label", tempSrc);
            jsonDataSource.put("label", getCnByNm(dataSourceList.get(0), "节点标签"));
            jsonRes.put("cl", tempb.getCl());
            jsonDataSource.put("cl", getCnByNm(dataSourceList.get(0), "Cl"));

            List<String> tempCd = new ArrayList<>();
            jsonRes.put("cd", tempCd);
            jsonDataSource.put("cd", getCnByNm(dataSourceList.get(0), "Cd"));
            jsonRes.put("cmnt", tempb.getCmnt());
            jsonDataSource.put("cmnt", getCnByNm(dataSourceList.get(0), "Cmnt"));

            jsonResult.put("res", jsonRes);
            jsonResult.put("datasource", jsonDataSource);
        } else if (nodeType == "DataModelOfIBMNodeEntity") {
            DataModelOfIBMNodeEntity tempb = (DataModelOfIBMNodeEntity) nodeList.iterator().next();
            //遍历节点的标签
            jsonRes.put("nm", tempb.getNm());
            jsonDataSource.put("nm", getCnByNm(dataSourceList.get(0), "Nm"));
            String tempSrc = "";
            for (int x = 0; x < nodeTagList.size(); x++) {
                tempSrc += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
            }
            jsonRes.put("src", tempb.getSrc());
            jsonDataSource.put("src", getCnByNm(dataSourceList.get(0), "Src"));
            jsonRes.put("label", tempSrc);
            jsonDataSource.put("label", getCnByNm(dataSourceList.get(0), "节点标签"));

            List<String> tempCd = new ArrayList<>();
            jsonRes.put("cd", tempCd);
            jsonDataSource.put("cd", getCnByNm(dataSourceList.get(0), "Cd"));
            jsonRes.put("cmnt", tempb.getCmnt());
            jsonDataSource.put("cmnt", getCnByNm(dataSourceList.get(0), "Cmnt"));

            jsonResult.put("res", jsonRes);
            jsonResult.put("datasource", jsonDataSource);
        } else if (nodeType == "IndicatorsNodeEntity") {
            IndicatorsNodeEntity tempb = (IndicatorsNodeEntity) nodeList.iterator().next();
            //遍历节点的标签
            jsonRes.put("nm", tempb.getNm());
            jsonDataSource.put("nm", getCnByNm(dataSourceList.get(0), "Nm"));
            String tempSrc = "";
            for (int x = 0; x < nodeTagList.size(); x++) {
                tempSrc += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
            }
            jsonRes.put("src", validIsNull(tempb.getSrc()));
            jsonDataSource.put("src", getCnByNm(dataSourceList.get(0), "Src"));
            jsonRes.put("label", tempSrc);
            jsonDataSource.put("label", getCnByNm(dataSourceList.get(0), "节点标签"));

            jsonRes.put("unt", validIsNull(tempb.getUnt()));
            jsonDataSource.put("unt", getCnByNm(dataSourceList.get(0), "Unt"));

            jsonRes.put("no", validIsNull(tempb.getNo()));
            jsonDataSource.put("no", getCnByNm(dataSourceList.get(0), "No"));

            jsonRes.put("fmt", validIsNull(tempb.getFmt()));
            jsonDataSource.put("fmt", getCnByNm(dataSourceList.get(0), "Fmt"));

            jsonRes.put("def", validIsNull(tempb.getDef()));
            jsonDataSource.put("def", getCnByNm(dataSourceList.get(0), "Def"));

            jsonRes.put("cyc", validIsNull(tempb.getCyc()));
            jsonDataSource.put("cyc", getCnByNm(dataSourceList.get(0), "Cyc"));

            jsonRes.put("attr", validIsNull(tempb.getAttr()));
            jsonDataSource.put("attr", getCnByNm(dataSourceList.get(0), "Attr"));

            jsonRes.put("clbr", validIsNull(tempb.getClbr()));
            jsonDataSource.put("clbr", getCnByNm(dataSourceList.get(0), "Clbr"));

            List<String> tempCd = new ArrayList<>();
            jsonRes.put("cd", tempCd);
            jsonDataSource.put("cd", getCnByNm(dataSourceList.get(0), "Cd"));
            jsonRes.put("cmnt", tempb.getCmnt());
            jsonDataSource.put("cmnt", getCnByNm(dataSourceList.get(0), "Cmnt"));

            jsonResult.put("res", jsonRes);
            jsonResult.put("datasource", jsonDataSource);
        }


        html = lxtool.getWebCode("<html-handlebars>");
        html = html.replace("${title}", queryKey + " -查询结果 - 数据指标项目");
        String loadscript = "<script src=\"" + lxtool.getScriptCdnUrl("handlebars") + "\"></script>"
                + "<script src=\"" + lxtool.getScriptCdnUrl("jquery") + "\"></script>"
                + lxtool.getScriptCdnUrl("script-echart")
                + "";


        html = html.replace("${loadScript}", loadscript);
        String style = lxtool.getWebCode("<sec-style>");
        style += lxtool.getWebCode("<list-style>");
        html = html.replace("${style}", style);
        html = html.replace("${jsoninfo}", jsonResult.toJSONString());
        String chartModule = "";
        if (nodeRelation != null) {
            chartModule = processEChartsModuleInfo(nodeRelation);
        }

        html = html.replace("${chartModule}", chartModule);
        return html;
    }

    public String processEChartsModuleInfo(Map<Integer, Object> nodeRelationMap) {
        String res = "<div id=\"chartDiv\">未连接网络或网络质量不佳导致加载图形失败，请尝试刷新页面</div>";
        res += "<script type=\"text/javascript\">        var chartDom = document.getElementById('chartDiv');        var myChart = echarts.init(chartDom);        var option;        var Jsongraph = {            \"nodes\": ${Jsongraph.nodeList},            \"links\": ${Jsongraph.linksList},            \"categories\": []        };       function processChart(graph) {   if (graph.nodes.length > 0) { $('#chartDiv').css('display', 'block') };         option = {                title: {                    text: '${Jsongraph.title}',                },                tooltip: {},                legend: [{                     selectedMode: 'single',                    data: graph.categories.map(function (a) {                        return a.Nm;                    })                }],                animationDuration: 1500,                animationEasingUpdate: 'quinticInOut',                series: [                    {                                             type: 'graph',                        layout: 'force',                        data: graph.nodes,                        links: graph.links,                        categories: graph.categories,                        roam: true,                         draggable:true,                        itemStyle: {                                                        color: {                                type: 'linear',                                x: 0,                                y: 0,                                x2: 0,                                y2: 1,                                colorStops: [{                                    offset: 0, color: 'lightcoral'                                 }, {                                    offset: 1, color: 'coral'                                 }],                            },                        },                        zoom: 10,                        label: {                            position: 'bottom',                                                     color:'white', backgroundColor:'coral', fontSize:12, padding:2,                           shadowColor:'gray',                            shadowBlur:5,                            shadowOffsetX:2,                            shadowOffsetY:2,                            show:true                        },                        edgeLabel:{                            show:true,                            formatter: '{c}',                        },      edgeSymbol: ['none', 'arrow'],                  lineStyle: {                            color: 'source',                   borderRadius: 2,         curveness: 0.2,                            width:5                        },                        emphasis: {                            focus: 'adjacency',                            lineStyle: {                                width: 15                            }                        }                    }                ]            };            myChart.setOption(option);            option && myChart.setOption(option);        };        processChart(Jsongraph)</script>";

        List<Object> nodeJson = new ArrayList<>(), linksJson = new ArrayList<>();
        validNodeIdList = new ArrayList<>();
        for (int i = 0; i < nodeRelationMap.size(); i++) {
            Map<String, Object> nodeRelation = (Map<String, Object>) nodeRelationMap.get(i);


            Map<String, Object> innerJson = new HashMap<>();
            Long suuid = (Long) nodeRelation.get("startnodeId");
            Long euuid = (Long) nodeRelation.get("endnodeId");
            Long myselfId = (Long) nodeRelation.get("myselfId");
            if (!validIsIdRepeat(suuid) || i < 1) {

                innerJson.put("id", suuid.toString());
                innerJson.put("name", nodeRelation.get("startnode").toString());
                nodeJson.add(innerJson);
                innerJson = new HashMap<>();
            }

            if (!validIsIdRepeat(euuid) || i < 1) {

                innerJson.put("id", euuid.toString());
                innerJson.put("name", nodeRelation.get("endnode").toString());
                nodeJson.add(innerJson);
                innerJson = new HashMap<>();

            }
            innerJson.put("source", suuid.toString());
            innerJson.put("target", euuid.toString());
            innerJson.put("value", nodeRelation.get("typename").toString());
            linksJson.add(innerJson);
        }

        //待替换的内容为结点信息： ${Jsongraph.nodeList}  关系信息：${Jsongraph.linksList}
        res = res.replace("${Jsongraph.nodeList}", lxtool.convertListToJsonString(nodeJson));
        res = res.replace("${Jsongraph.linksList}", lxtool.convertListToJsonString(linksJson));
        res = res.replace("${Jsongraph.title}", "结点关系");
        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-22 14:03:23
     * @Params: null
     * @Return
     * @Description: 判断结点的ID是否已经记录。false-未记录，true-已记录
     */
    public boolean validIsIdRepeat(Long id) {
        boolean status = false;
        for (Long forid : validNodeIdList) {
            if (forid.equals(id)) {
                status = true;
                break;
            }
        }

        //对临时存的数组进行foreach判断是否重复，无重复 新纪录然后返回真， 有重复不存返回假
        if (status == false)
            validNodeIdList.add(id);

        return status;

    }

    public String validIsNull(String s) {
        if (s == null || "".equals(s))
            return "/";
        return s;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-20 15:38:01
     * @Params: null
     * @Return
     * @Description: 将查找的信息组成有道划词的显示格式
     */
    public String translation(String queryKey, Object oriNodeList, String nodeType, List<String> nodeTagList, String isNewPage, Map<Integer, Object> nodeRelation) {
        if (queryKey == null || "".equals(queryKey.trim()) || queryKey == "Yodao dict Test" || queryKey == "Yodao dict Retest") {
            lxtool.soutLog("查询", queryKey, "查询字段与有道的无效关键字相同（非划词查询），查询终止");
            return getUnkown(queryKey);
        }
        //根据传递的类名nodeType 实例化List<XXX>
        List<?> nodeList = (List<?>) oriNodeList;
        //获取节点类型英文名称，转换为中文名称-Nm
        String typeCnName = lxtool.nodeTypeConvert2ZHCN(nodeType);
        //根据中文名称查询 数据元节点的属性信息
        List<DataSourceEntity> dataSourceList = dsService.getDataSource(typeCnName);

        if (nodeList.size() == 0) {
            lxtool.soutLog("结果", queryKey, "数据库没有找到划词的节点或关系");
            return getUnkown(queryKey);
        }

        if ("true".equals(isNewPage)) {
            //如若为新开页面，则转发至专门处理html页面的函数进行页面处理。
            String html = translationForHTML(queryKey, nodeList, nodeType, nodeTagList, dataSourceList, nodeRelation);
            return html;
        }

        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
        String res = "";
        res += "<p  class='listText'><a target='_blank' href='http://" + sysTool.getLocalHost() + ":6868/fsearch?q=" + queryKey + "&ist=true' >" + "[点我打开新网页查看更多]" + "</a></p>";

//        当为代码节点时
        if (nodeType == "CodeNodeEntity") {
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                res += "";
                BaseNodeEntity tempb = (BaseNodeEntity) nodeList.iterator().next();
                res += "<pre>";
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span class='infotext'>";
                for (int x = 0; x < nodeTagList.size(); x++) {
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
                res += "</span><br/>";

                res +=
                        "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Src") + "：</span><span class='infotext'>" + tempb.getSrc() + "</span><br/>" +
                                "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Ver") + "：</span>" + "<span class='infotext'>" + tempb.getVer() + "</span>";
                res += "" +
                        "<hr/>";
                //遍历代码（Cd、Cmnt）
                int jmax = tempb.getCd().size();

                for (int j = 0; j < jmax; j++) {
                    //当前结点的属性遍历
                    res += "<strong>" + tempb.getCd().get(j) + "</strong>\t" + tempb.getCmnt().get(j);
                    if (j != jmax) res += "<br />";

                }
                res += "</pre>";
            }
//            处理基本词类词结点
        } else if (nodeType == "BasicAndClassWordEntity") {
            res += "<pre>";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                BasicAndClassWordEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (BasicAndClassWordEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
//                获取Nm属性的中文名称与Nm值
                res += "</span><br/>";
                res += "<span class='infotitle'>" +
                        getCnByNm(dataSourceList.get(0), "Cl") + "：</span><span class='infotext'>" + tempb.getCl() + "</span><br/>";

            }
            res += lxtool.writeTempNote("数据元缺失");

            res += "</pre>";

        } else if (nodeType == "ReportEntity") {
            res += "<pre>";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                ReportEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (ReportEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }

//                获取Nm属性的中文名称与Nm值
                res += "</span><br/><span class='infotitle'>";
                res +=
                        getCnByNm(dataSourceList.get(0), "Cl") + "：</span><span class='infotext'>" + (tempb.getCl() == null ? "/" : tempb.getCl()) + "</span><br/>";

            }
            res += lxtool.writeTempNote("数据元缺失");
            res += "</pre>";
        } else if (nodeType == "DataModelOfIBMNodeEntity") {
            res += "<pre>";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                DataModelOfIBMNodeEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (DataModelOfIBMNodeEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }

//                获取Nm属性的中文名称与Nm值
                res += "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Cd") + "：</span><span class='infotext'>" + (tempb.getMycd() == null ? "/" : tempb.getCd()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Cmnt") + "：</span><span class='infotext'>" + (tempb.getMycmnt() == null ? "/" : tempb.getCmnt()) + "</span><br/>";

            }
        } else if (nodeType == "IndicatorsNodeEntity") {
            res += "<pre>";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                IndicatorsNodeEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (IndicatorsNodeEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }

//                获取Nm属性的中文名称与Nm值
                res += "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Clbr") + "：</span><span class='infotext'>" + (tempb.getClbr() == null ? "/" : tempb.getClbr()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Attr") + "：</span><span class='infotext'>" + (tempb.getAttr() == null ? "/" : tempb.getAttr()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Cyc") + "：</span><span class='infotext'>" + (tempb.getCyc() == null ? "/" : tempb.getCyc()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Def") + "：</span><span class='infotext'>" + (tempb.getDef() == null ? "/" : tempb.getDef()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Fmt") + "：</span><span class='infotext'>" + (tempb.getFmt() == null ? "/" : tempb.getFmt()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "No") + "：</span><span class='infotext'>" + ("".equals(tempb.getNo()) ? "/" : tempb.getNo()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Unt") + "：</span><span class='infotext'>" + (tempb.getUnt() == null ? "/" : tempb.getUnt()) + "</span><br/>";

            }
        } else {
            res += "<pre>";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                BaseNodeEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (BaseNodeEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br>";
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
//                获取Nm属性的中文名称与Nm值
                res += "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Cd") + "：</span><span class='infotext'>" + (tempb.getCd() == null ? "/" : tempb.getCd()) + "</span><br/>";
                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Cmnt") + "：</span><span class='infotext'>" + (tempb.getCmnt() == null ? "/" : tempb.getCmnt()) + "</span><br/>";

            }
            res += lxtool.writeTempNote("通用展示");
            res += "</pre>";
        }

        //如果是二级页面，需要返回html格式，而非xml格式。
//        if ("true".
//
//                equals(isNewPage)) {
//            String prefix = lxtool.getWebCode("<newHtml-pre>").replace("${replaceTitle}", queryKey + " -查询结果 - 数据指标项目");
//            String after = lxtool.getWebCode("<newHtml-after>");
//            return prefix + res + after;
//        }

        customTranslationSb.append(

                getTranslation("", res));
        customTranslationSb.append("</custom-translation>");

        //3.组装返回xml
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[").

                append(((BaseNodeEntity) nodeList.

                        iterator().

                        next()).

                        getNm()).

                append("]]></return-phrase>")
                .

                        append(customTranslationSb).

                append("</yodaodict>");
        return youdaodictSb.toString();

    }


    /**
     * @Author: chenlx
     * @Date: 2021-01-20 15:05:50
     * @Params: null
     * @Return
     * @Description: 未查询到结果时返回的xml信息
     */
    public String getUnkown(String queryKey) {
        String res = "<p>查询不到有关<span style='color:red'>" + queryKey + "</span>的信息，请尝试调整一下划词范围吧！</p>";
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[")
                .append(queryKey)
                .append("]]></return-phrase>")
                .append("<custom-translation>")
                .append("<translation><content><![CDATA[")
                .append(res)
                .append("]]></content></translation>")
                .append("</custom-translation>")
                .append("</yodaodict>");
        return youdaodictSb.toString();
    }

    /**
     * 组装翻译
     *
     * @param tbNameCn
     * @param content
     * @return
     */
    private String getTranslation(String tbNameCn, String content) {
        //拼接翻译内容content
        StringBuffer translation = new StringBuffer();
        translation.append("<translation><content><![CDATA[").append(tbNameCn).append(content).append("]]></content></translation>");
        return translation.toString();
    }

}
