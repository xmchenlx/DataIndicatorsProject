package com.sjzb.demo.service;

import com.sjzb.demo.Result.lxTool;
import com.sjzb.demo.model.BaseNodeEntity;
import com.sjzb.demo.model.BasicAndClassWordEntity;
import com.sjzb.demo.model.DataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-20 15:03:45
 * @Description:
 */
@Component
public class youdaoTool {

    private lxTool lxtool = new lxTool();

    @Autowired
    dataSourceServiceImpl dsService;


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
        for (int i = 0; i < dse.getAttr().size(); i++) {
            if (dse.getAttr().get(i).equals(nm)) {
                res = dse.getCn().get(i);
                break;
            }
        }
        if(res.equals(""))res = "['数据元'无该解释]";
        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-20 15:38:01
     * @Params: null
     * @Return
     * @Description: 将查找的信息组成有道划词的显示格式
     */
    public String translation(String queryKey, Object oriNodeList, String nodeType, List<String> nodeTagList, String isNewPage) {
        if (queryKey == null || "".equals(queryKey.trim()) || queryKey == "Yodao dict Test" || queryKey == "Yodao dict Retest") {
//            System.out.println("查询字段为空");
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
//            System.out.println("查询不到数据");
            lxtool.soutLog("结果", queryKey, "数据库没有找到划词的节点或关系");
            return getUnkown(queryKey);
        }

        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
        String res = "";
//        res += "<style>span{display:-moz-inline-box;display:inline-block;}.infotitle{text-align:left;width:70px;max-width:70px;text-align:right;}.infotext{text-align:left;font-weight:bold;}</style>";
        res += lxtool.getWebCode("<sec-style>");
//        当为代码节点时
        if (nodeType == "CodeNodeEntity") {
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                res += "";
                BaseNodeEntity tempb = (BaseNodeEntity) nodeList.iterator().next();
                res += "<pre>";
                //遍历节点的标签
                res += "<span class='infotitle'>来源：</span><span class='infotext'>";
                for (int x = 0; x < nodeTagList.size(); x++) {
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
                res += "</span><br/>";

                res += "<span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Src") + "：</span><span class='infotext'>" + tempb.getSrc() + " </span><br/><span class='infotitle'>" + getCnByNm(dataSourceList.get(0), "Ver") + "：</span><span class='infotext'>" + tempb.getVer() + "</span>";
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
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
//                获取Nm属性的中文名称与Nm值
                res += "</span><br/><span class='infotitle'>";
                res += getCnByNm(dataSourceList.get(0), "Nm") + "：</span><span class='infotext'>" + tempb.getNm() + "</span><br/><span class='infotitle'>" +
                        getCnByNm(dataSourceList.get(0),"Cl") + "：</span><span class='infotext'>" + tempb.getCl() + "</span><br/>";

            }
            res += "</pre>";
        }
        //如果是二级页面，需要返回html格式，而非xml格式。
        if ("true".equals(isNewPage)) {
//            String prefix = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no\" /><meta name=\"format-detection\" content=\"telephone=no\"><title>huaci</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./iconfont.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"./huaci.css\" /><style>html{font-family:tahoma,Arial,\"Microsoft YaHei\" !important}</style></head><body><div id=\"doc\"><div id=\"main\">";
//            String after = "</div>\n" +
//                    "    </div>\n" +
//                    "    <script src=\"./weblibs.js\">\n" +
//                    "    </script> \n" +
//                    "    <script src=\"./huaci.js\"></script>\n" +
//                    "</body>\n" +
//                    "\n" +
//                    "</html>";
            String prefix = lxtool.getWebCode("<newHtml-pre>");
            String after = lxtool.getWebCode("<newHtml-after>");
            return prefix + res + after;
        }

        customTranslationSb.append(getTranslation("", res));
        customTranslationSb.append("</custom-translation>");

        //3.组装返回xml
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[").append(((BaseNodeEntity) nodeList.iterator().next()).getNm()).append("]]></return-phrase>")
                .append(customTranslationSb).append("</yodaodict>");
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
