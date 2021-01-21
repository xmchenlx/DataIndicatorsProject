package com.sjzb.demo.service;

import com.sjzb.demo.CodeNodeRepository;
import com.sjzb.demo.model.BaseNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-20 15:03:45
 * @Description:
 */
public class youdaoTool {

    @Autowired
    CodeNodeRepository cnRe;

    /**
     * @Author: chenlx
     * @Date: 2021-01-20 15:38:01
     * @Params: null
     * @Return
     * @Description: 将查找的信息组成有道划词的显示格式
     */
    public String translation(String queryKey, List<BaseNodeEntity> nodeList,List<String> nodeTagList) {
        if (queryKey == null || "".equals(queryKey.trim()) || queryKey=="Yodao dict Test"|| queryKey == "Yodao dict Retest") {
            System.out.println("查询字段为空");
            return getUnkown(queryKey);
        }

//        //1.查询所有配置
//        List<CodeNodeEntity> list = cnRe.findCodeNodeEntityByNmLike(queryKey);
//        if (list == null) {
//            System.out.println("参数表未配置");
//            return getUnkown(queryKey);
//        }

//        String tNm = nodeList.iterator().next().getNm();
//        String tempCneTag = cnRe.findTagByNm(tNm).toString();
//        List<String> nodeTagList = getListFromJson(tempCneTag);


        //2.根据配置查表
        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
//        list.forEach(tbSysConfig -> {
        String res = "";
//        List<CodeNodeEntity> nodeList = cnRe.findCodeNodeEntityByNmLike(queryKey);
//        List<queryEntity> nodeList = cnRe.findCodeNodeEntityByNmLike(queryKey);


        if (nodeList.size() == 0) {
            System.out.println("查询不到数据");
            return getUnkown(queryKey);
        }

        for (int i = 0; i < nodeList.size(); i++) {
            //LIKE模糊查询的结点个数遍历
            BaseNodeEntity tempb = nodeList.iterator().next();
//            遍历节点的标签
            res = "来源：";
            for (int x = 0; x < nodeTagList.size(); x++) {
                if (x != 0) res += "、";
                res += "《" + nodeTagList.get(x).replace("Optional", "") + "》";
            }
            res += "<br/>";
            res += "类别：" + nodeList.get(0).getSrc() + "<br/>版本：" + nodeList.get(0).getVer();
            res += "<br/><p style='color:gray;text-align:center'>代码如下</p><hr/>";
//            遍历代码（Cd、Cmnt）
            int jmax = tempb.getCd().size();

            res += "<pre>";
            for (int j = 0; j < jmax; j++) {
                //当前结点的属性遍历
                res += "<strong>" + tempb.getCd().get(j) + "</strong>\t" + tempb.getCmnt().get(j);
                if (j != jmax) res += "<br />";

            }
            res += "</pre>";
        }
        customTranslationSb.append(getTranslation("", res));
        customTranslationSb.append("</custom-translation>");

        //3.组装返回xml
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[").append(nodeList.get(0).getNm()).append("]]></return-phrase>")
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
        String res = "<p>查询不到有关<strong style='color:red'>" + queryKey + "</strong>的信息，调整一下划词范围试试吧！</p>";
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
