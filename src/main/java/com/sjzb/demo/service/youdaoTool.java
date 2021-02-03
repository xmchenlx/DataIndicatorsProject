package com.sjzb.demo.service;

import com.sjzb.demo.Result.lxTool;
import com.sjzb.demo.model.BaseNodeEntity;
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
public class  youdaoTool {

    //    @Autowired
//    private CodeNodeRepository cnRe;
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
        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-20 15:38:01
     * @Params: null
     * @Return
     * @Description: 将查找的信息组成有道划词的显示格式
     */
    public String translation(String queryKey, Object oriNodeList, String nodeType, List<String> nodeTagList) {
        if (queryKey == null || "".equals(queryKey.trim()) || queryKey == "Yodao dict Test" || queryKey == "Yodao dict Retest") {
            System.out.println("查询字段为空");
            return getUnkown(queryKey);
        }
        //根据传递的类名nodeType 实例化List<XXX>
        List<?> nodeList = (List<?>) oriNodeList;
        //获取节点类型英文名称，转换为中文名称-Nm
        String typeCnName = lxtool.nodeTypeConvert2ZHCN(nodeType);
        //根据中文名称查询 数据元节点的属性信息
        List<DataSourceEntity> dataSourceList = dsService.getDataSource(typeCnName);

        if (nodeList.size() == 0) {
            System.out.println("查询不到数据");
            return getUnkown(queryKey);
        }

        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
        String res = "";
        res += "<style>span{display:-moz-inline-box;display:inline-block;}.infotitle{text-align:left;width:70px;max-width:70px;text-align:right;}.infotext{text-align:left;font-weight:bold;}</style>";

//        当为代码节点时
        if (nodeType == "CodeNodeEntity") {
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                res += "";
                BaseNodeEntity tempb = (BaseNodeEntity) nodeList.iterator().next();
                res += "<a href='javascript:history.back(-1);' style='font-size:15px;'>返回</a> <br/> ";
                res += "<pre>";
                //遍历节点的标签
                res += "<span class='infotitle'>来源：</span><span class='infotext'>";
                for (int x = 0; x < nodeTagList.size(); x++) {
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
                res += "</span><br/>";

                res +="<span class='infotitle'>"+ getCnByNm(dataSourceList.get(0),"Src")+"：</span><span class='infotext'>" + tempb.getSrc() + " </span><br/><span class='infotitle'>"+getCnByNm(dataSourceList.get(0),"Ver")+"：</span><span class='infotext'>" + tempb.getVer()+"</span>";
                res += "" +
//                        "<p style='color:gray;text-align:center'>代码</p>" +
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
            res += "<a href='javascript:history.back(-1);' style='text-decoration:underline;font-size:15px;'>关闭详细</a><br/>  ";
            for (int i = 0; i < nodeList.size(); i++) {
                //LIKE模糊查询的结点个数遍历
                BaseNodeEntity tempb = null;
                if (nodeList.iterator().hasNext()) tempb = (BaseNodeEntity) nodeList.get(i);
                //遍历节点的标签
                res += "<span class='infotitle'>来源：</span><span  class='infotext'>";
//                遍历标签名称
                for (int x = 0; x < nodeTagList.size(); x++) {
                    if (x != 0) res += "、";
                    res += "《" + nodeTagList.get(x).replace("Optional", "").trim() + "》";
                }
//                获取Nm属性的中文名称与Nm值
                res += "</span><br/><span class='infotitle'>";
                res += getCnByNm(dataSourceList.get(0),"Nm")+"：</span><span class='infotext'>" + tempb.getNm() + "</span><br/>";

            }
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
