package com.sjzb.demo.controller;

import com.sjzb.demo.model.BaseNodeEntity;
import com.sjzb.demo.service.*;
import com.sjzb.demo.tool.SystemSetting;
import com.sjzb.demo.tool.lxTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:35:09
 * @Description:
 */
@RestController
public class CodeNodeController {
    @Autowired
    youdaoTool ydtool;

    @Autowired
    CodeNodeServiceImpl cnService;
    @Autowired
    BasicClassNodeServiceImpl basicClassService;
    @Autowired
    StandardDataNodeServiceImpl standardDataService;

    @Autowired
    ReportNodeServiceImpl reportService;
    @Autowired
    DataModelOfIBMNodeServiceImpl ibmModelService;
    @Autowired
    IndicatorsNodeServiceImpl indicatorService;


    lxTool lxtool = new lxTool();
    SystemSetting sysTool = new SystemSetting();
//    @Autowired
//    dataSourceServiceImpl dsService;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @GetMapping("/*")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long _begin = System.currentTimeMillis();


        String queryKey = request.getParameter("q");            //提取Request信息里的查询字段
        String isNewPage = request.getParameter("ist");         //提取是否为新开标签布尔
        String secQueryKey = request.getParameter("sqk");       //获取第二查询关键词
        if (queryKey == null) {
            lxtool.soutLog("查询", queryKey, "查询请求的字段为空，终止查询。");
            return;
        }
        if (queryKey.length() == 0) return;
        lxtool.soutLog("查询", queryKey, (secQueryKey == null ? null : "该请求为二级查询（有道结果单击查询）"));
        String translate = "";
        String dataString = "<div style='width:100%;'>";
        String findGuide = "<h5>查询向导</h5><p style='font-size:10px;'>*单击标题快速跳转到该类查询结果。</p>";//查询向导
        String a = lxtool.getWebCode("<list-style>");
        int count = 0;//查询数量

        //过滤不小心触碰的请求
        if (queryKey.equals("Yodao dict Retest") || queryKey.length() == 0 || queryKey == "Yodao dict Retest" || queryKey == "" || queryKey == null) {
            lxtool.soutLog("查询", queryKey, "查询请求的字段为无效关键字（非划词），终止查询。");
            return;
        }

        //请求是否为新开页面（a标签点开的请求）。为是-精确查找；为否-模糊匹配
        Map<Integer, Object> searchBriefRes;
        if ("true".equals(isNewPage))
            searchBriefRes = searchBriefDataInVarietyOfClassInAcc(queryKey, secQueryKey);
        else
            searchBriefRes = searchBriefDataInVarietyOfClass(queryKey);

        if (searchBriefRes.size() == 0) {
            //查询无结果时，返回未知信息
            System.out.println("数据库没有找到划词的节点或关系，请求的关键词为：" + queryKey);
            translate = ydtool.getUnkown(queryKey);
        } else {
            //查询结果只有1条时，直接呈现详细结果
            Map<String, Object> tempMap = new HashMap<>();
            tempMap = (Map<String, Object>) searchBriefRes.get(0);
            if (searchBriefRes.size() == 1 && (int) tempMap.get("len") < 2) {
                count++;


                translate = ydtool.translation(tempMap.get("node_Nm").toString(), tempMap.get("node_data"), tempMap.get("node_type").toString(), (List<String>) tempMap.get("node_tag"), isNewPage,(Map<Integer,Object>)tempMap.get("node_relation"));
                translate = translate;

            } else {
//                查询结果多条时，呈现结果列表
                //循环节点实体数据
                for (int m = 0; m < searchBriefRes.size(); m++) {
                    Map<String, Object> tempBriefRes = (Map<String, Object>) searchBriefRes.get(m);
                    List<?> queryDataList = (List<?>) tempBriefRes.get("node_data");
                    //循环节点内的List数据
                    String nodeTag = tempBriefRes.get("node_tag").toString().replace("Optional", "");
                    int maxNum = queryDataList.size() > 50 ? 50 : queryDataList.size();
                    String ifMaxOver50Str = (maxNum == 50 ? "的前50项" : "");
                    dataString += "<h5 style='color:black;'>在 " + nodeTag + " 找到" + queryDataList.size() + "项" + ifMaxOver50Str + "</h5>";
                    findGuide += "<a class='listText' href='#" + nodeTag + "'>跳转到：" + nodeTag + "</a><br>";
                    for (int i = 0; i < maxNum; i++) {
                        BaseNodeEntity nodeData = (BaseNodeEntity) queryDataList.get(i);
                        String nodeName = nodeData.getNm();
                        count++;
                        //target='_blank'
                        dataString += "<p  class='listText'><a id='" + nodeTag + "' alt='点击将会跳转到浏览器展示详细信息' target='_blank' href='http://" + sysTool.getLocalHost() + ":6868?q=" + nodeName + "&sqk=" + queryKey + "&ist=true' >" + nodeName + "</a></p>";
                    }
                    dataString += "<br/>";

                }
                if (count > 50)
                    dataString = findGuide + "<hr><br>" + dataString;
                translate = ydtool.translationList(dataString, count, a);

            }
        }


//        //查询字段存在的结点实体（在逻辑上表现为：查找查询信息分别来自于哪些分类）
//        Map<String, Object> searchRes = searchDataInVarietyOfClass(queryKey);
//        if (searchRes.get("nodeName") == "_NotFound_") {
//            //查询无结果时，返回未知信息
//            System.out.println("找不到信息：" + queryKey);
//            translate = ydtool.getUnkown(queryKey);
//        } else {
//            //获取节点的标签
//            Map<String, Object> nodeData = (HashMap) searchRes.get("data"); //获取结点的数据（多个结点信息用List<实体>表示）
//            String tNm = nodeData.get("node_Nm").toString(); //获取结点的Nm名称
//            List<String> nodeTagList = (List<String>) nodeData.get("node_tag"); //获取结点的标签名（多个标签用List<String>表示）
//            String nodeType = nodeData.get("node_type").toString();
//            //获取结点的java实体类名称（在传递给有道翻译函数时，根据此名称进行不同属性的读取，以解决各个结点实体属性不一致的问题）
//
//            translate = ydtool.translation(queryKey, nodeData.get("node_data"), nodeType, nodeTagList);
//        }
        response.setContentType("text/html;charset=UTF-8");
//        System.out.println(translate);
        response.getWriter().print(translate);

        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//
    }


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:07:38
     * @Params: null
     * @Return
     * @Description: 根据搜寻词模糊查找结点的简要信息，返回列表
     */
    public Map<Integer, Object> searchBriefDataInVarietyOfClass(String qk) {
        Map<Integer, Object> res = new HashMap<>();
        int index = 0;
        Map<String, Object> tempQueryNodeList;

        //查找【代码节点】（基础数据标准）
        tempQueryNodeList = cnService.selectCodeNodeListByNmLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【基本词类词节点】
        tempQueryNodeList = basicClassService.selectBCWInfoByNmLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【指标节点】
        tempQueryNodeList = indicatorService.selectIndicatorsLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

//        //查找【基础数据标准节点】
//        tempQueryNodeList = standardDataService.selectDataStandardNodeInfoByNmLike(qk);
//        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
//            res.put(index++, tempQueryNodeList);
//        }

        //查找【报表节点】
        tempQueryNodeList = reportService.selectReportNodeInfoByNmLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【IBM模型节点】
        tempQueryNodeList = ibmModelService.selectIBMModelByNmLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-02-03 11:10:11
     * @Params: null
     * @Return
     * @Description: 精确查询（主要体现在a标签跳转出的新页面时不需要再进行模糊查询的情况下）
     */
    public Map<Integer, Object> searchBriefDataInVarietyOfClassInAcc(String qk, String secQueryKey) {
        Map<Integer, Object> res = new HashMap<>();
        int index = 0;
        Map<String, Object> tempQueryNodeList;

        //查找【代码节点】（基础数据标准）
        tempQueryNodeList = cnService.selectCodeNodeListByNm(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【基本词类词节点】
        tempQueryNodeList = basicClassService.selectBCWInfoByNm(qk, secQueryKey);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【基础数据标准节点】
//        tempQueryNodeList = standardDataService.selectDataStandardNodeInfoByNm(qk,secQueryKey);
//        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
//            res.put(index++, tempQueryNodeList);
//        }
        //查找【指标节点】
        tempQueryNodeList = indicatorService.selectIndicators(qk, null);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }
        //查找【报表节点】
        tempQueryNodeList = reportService.selectReportNodeInfoByNm(qk, null);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        //查找【IBM模型节点】
        tempQueryNodeList = ibmModelService.selectIBMModelByNm(qk, null);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put(index++, tempQueryNodeList);
        }

        return res;
    }

}
