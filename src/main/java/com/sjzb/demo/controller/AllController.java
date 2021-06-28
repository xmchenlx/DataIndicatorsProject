package com.sjzb.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.sjzb.demo.MySessionContext;
import com.sjzb.demo.config.SystemSetting;
import com.sjzb.demo.model.BaseNodeEntity;
import com.sjzb.demo.model.TypeEnum;
import com.sjzb.demo.model.UserOrderEntity;
import com.sjzb.demo.service.Node.*;
import com.sjzb.demo.service.youdaoTool;
import com.sjzb.demo.util.lxTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:35:09
 * @Description:
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AllController {
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
    @Autowired
    JsStorageServiceImpl jsService;
    lxTool lxtool = new lxTool();
    SystemSetting sysTool = new SystemSetting();
    proController proCon = new proController();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @GetMapping("/fsearch")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySessionContext myc = MySessionContext.getInstance();
        if (session.isNew()) {
            myc.addSession(session);
        }
        Object a = session.getAttribute("sjzb_order");
        UserOrderEntity uoe = (UserOrderEntity) session.getAttribute("sjzb_order");
        if (uoe == null) {
            uoe = generateDefaultSessionInfo(session);
            session.setAttribute("sjzb_order", uoe);
            myc.addSession(session);

        }
        String uri = request.getRequestURI();
        if (uri.equals("/fsearch")) {
            getWord(request, response);
        }
//        } else if (uri.equals("/fsetting")) {
//            getWordSetting(request, response);
//        } else if (uri.equals("/getjs")) {
//            getJsByName(request, response);
//        }else if(uri.substring(0,4).equals("/pro")){
//            proCon.doGet(request,response);
//        }


    }

    @GetMapping("/getjs")
    protected void getJsByName(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String jsPackageName = request.getParameter("jsn");            //提取Request信息里的查询字段
        StringBuilder res = new StringBuilder();
//        if(jsPackageName.equals("handlebars")){
        res = jsService.getJsPackageByName(jsPackageName);
//            res =  jsService.selectDataByNm("handlebars").getCd() ;
//        }

        response.setContentType("text/html;charset=UTF-8");
//        System.out.println(translate);
        response.getWriter().print(res);
    }


    protected UserOrderEntity generateDefaultSessionInfo(HttpSession session) {
        UserOrderEntity uoe = (UserOrderEntity) session.getAttribute("sjzb_order");
        if (uoe == null) {
            uoe = new UserOrderEntity();
        }
        if (uoe.getData_ch() == null) {
//            置入模板数据
            Map<Integer, String> dataCh = new HashMap<>();
            dataCh.put(TypeEnum.IndicatorsNode.getIndex(), TypeEnum.IndicatorsNode.getName());
            dataCh.put(TypeEnum.BasicAndClassWord.getIndex(), TypeEnum.BasicAndClassWord.getName());
            dataCh.put(TypeEnum.Code.getIndex(), TypeEnum.Code.getName());//"代码、基础数据标准"
            dataCh.put(TypeEnum.Report.getIndex(), TypeEnum.Report.getName());// "报表/报文"
            dataCh.put(TypeEnum.DataModelOfIBMNode.getIndex(), TypeEnum.DataModelOfIBMNode.getName());//"IBM数据模型分类"
            dataCh.put(TypeEnum.StandardOfData.getIndex(), TypeEnum.StandardOfData.getName()); //基数标准
//            dataCh.put(TypeEnum.EntityOfDataModel.getIndex(), TypeEnum.EntityOfDataModel.getName()); //基数标准
//            dataCh.put(TypeEnum.PropertyOfDataModel.getIndex(), TypeEnum.PropertyOfDataModel.getName()); //基数标准

            uoe.setData_ch(dataCh);
        }

        if (uoe.getOrder() == null) {
//            目前没有发现这有什么实际的用途，，，先留着
            List<Integer> dataOrder = new ArrayList<>();
            for (int i = 0; i < uoe.getData_ch().size(); i++) {
                dataOrder.add(i);
            }
            uoe.setOrder(dataOrder);
        }
        if (uoe.getSelect() == null) {
            List<Integer> dataSel = new ArrayList<>();
//            根据需求，默认值只查询基本词类词
            dataSel.add(TypeEnum.IndicatorsNode.getIndex());
            dataSel.add(TypeEnum.Code.getIndex());
            dataSel.add(TypeEnum.BasicAndClassWord.getIndex());
            uoe.setSelect(dataSel);
        }
        return uoe;
    }

    @GetMapping("/fsetting")
    protected void getWordSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String webRes = "";
        String sessionId = request.getParameter("sid");
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        UserOrderEntity uoe = (UserOrderEntity) session.getAttribute("sjzb_order");
        if (uoe == null)
            uoe = generateDefaultSessionInfo(session);
        webRes = lxtool.getWebCode("<settingPage>");
        webRes = webRes.replace("${javaDataReplace}", uoe.toString());
        webRes = webRes.replace("${JSESSIONID}", sessionId);
        response.setContentType("text/html;charset=UTF-8");
//        System.out.println(translate);
        response.getWriter().print(webRes);
    }

    @PostMapping("/fsettingUpload")
    protected void updateWordSetting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserOrderEntity queryKey = JSONObject.parseObject(request.getParameter("body"), UserOrderEntity.class);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(queryKey.getJsessionid());

        session.setAttribute("sjzb_order", queryKey);
        session.setMaxInactiveInterval(60 * 60);//以秒为单位
        response.getWriter().print("FINISHED");
    }

    protected void getWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long _begin = System.currentTimeMillis();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session = request.getSession();
        String queryKey = request.getParameter("q");            //提取Request信息里的查询字段
        String isNewPage = request.getParameter("ist");         //提取是否为新开标签布尔
        String secQueryKey = request.getParameter("sqk");       //获取第二查询关键词
        if (queryKey == null) {
            return;
        }
        if (queryKey.length() == 0) return;
        //过滤不小心触碰的请求
        if (queryKey.equals("Yodao dict Retest") || queryKey.length() == 0 || queryKey == "Yodao dict Retest" || queryKey == "" || queryKey == null) {
            return;
        }
        UserOrderEntity userOrder = (UserOrderEntity) session.getAttribute("sjzb_order");
        lxtool.soutLog("查询", queryKey, (secQueryKey == null ? null : "该请求为二级查询（有道结果单击查询）"));
        String translate = "";
        String dataString = "<div style='width:100%;'>";
        String userOrderLabel = "点我设置自定义查询";
        if (userOrder.getSelect().size() < 2 && userOrder.getSelect().get(0).equals(TypeEnum.BasicAndClassWord.getIndex())) {
            //当用户选择自定义实体存储的值只有1个且第一个值为基本词类词，即代表用户自定义选择为系统设置的默认的值
            userOrderLabel = "当前仅查询 [基本词类词] 信息，" + userOrderLabel;
        }
        dataString += "<a target='_blank' href='http://" + sysTool.getLocalHost() + ":6868/fsetting?sid=" + sessionId + "' >※" + userOrderLabel + "</a><hr>";
        String findGuide = "<h5>查询向导</h5><p style='font-size:10px;'>*单击标题快速跳转到该类查询结果。</p>";//查询向导
        String a = lxtool.getWebCode("<list-style>");
        int count = 0;//查询数量


        //请求是否为新开页面（a标签点开的请求）。为是-精确查找；为否-模糊匹配
        Map<Integer, Object> searchBriefRes;
        if ("true".equals(isNewPage)) {
            searchBriefRes = searchBriefDataInVarietyOfClassInAcc(queryKey, secQueryKey);
        } else {
            searchBriefRes = searchBriefDataInVarietyOfClass(queryKey, userOrder);
        }
        if (searchBriefRes.size() == 0) {
            //查询无结果时，返回未知信息
            System.out.println("数据库没有找到划词的节点或关系，请求的关键词为：" + queryKey);
            translate = ydtool.getUnkown(queryKey, sessionId);
        } else {
            Map<String, Object> tempMap = new HashMap<>();
            Iterator<Integer> keys = searchBriefRes.keySet().iterator();
            Integer searchCurrentKey = keys.next();
            tempMap = (Map<String, Object>) searchBriefRes.get(searchCurrentKey);
            if (searchBriefRes.size() == 1 && (int) tempMap.get("len") < 2) {
                //查询结果只有1条时，直接呈现详细结果
                count++;
                translate = ydtool.translation(tempMap.get("node_Nm").toString(), tempMap.get("node_data"), tempMap.get("node_type").toString(), tempMap.get("node_tag").toString(), (List<String>) tempMap.get("node_tag"), isNewPage, (Map<Integer, Object>) tempMap.get("node_relation"), sessionId);
            } else {
//                查询结果多条时，呈现结果列表
                //循环节点实体数据
//                每类结果分别存在了不同的map子项，查询到多项结果时应该变量提取结果
                do {

                    Map<String, Object> tempBriefRes = (Map<String, Object>) searchBriefRes.get(searchCurrentKey);
                    if (tempBriefRes == null) continue;
                    List<?> queryDataList = (List<?>) tempBriefRes.get("node_data");
                    //循环节点内的List数据
                    List<String> nodeTags = (List<String>) tempBriefRes.get("node_tag");

                    String nodeTag = nodeTags.toString().replace("Optional", "").replace("[", "").replace("]", "");
                    int maxNum = queryDataList.size() > 50 ? 50 : queryDataList.size();
                    String ifMaxOver50Str = (maxNum == 50 ? "的前50项" : "");
                    dataString += "<h5 style='color:black;'>在 " + nodeTag + " 找到" + queryDataList.size() + "项" + ifMaxOver50Str + "</h5>";
                    findGuide += "<a class='listText' href='#" + nodeTag + "'>跳转到：" + nodeTag + "</a><br>";
                    for (int i = 0; i < maxNum; i++) {
                        BaseNodeEntity nodeData = (BaseNodeEntity) queryDataList.get(i);
                        String nodeName = nodeData.getNm();
                        count++;
                        dataString += "<p  class='listText'><a id='" + nodeTag + "' alt='点击将会跳转到浏览器展示详细信息' target='_blank' href='http://" + sysTool.getLocalHost() + ":6868/fsearch?q=" + nodeName + "&sqk=" + nodeTags.get(0).replace("Optional", "") + "&ist=true' >" + nodeName + "</a><span id=\"tinytext\">[" + nodeTag + "]</span></p>";
                    }
                    dataString += "<br/>";
                    if (keys.hasNext())
                        searchCurrentKey = keys.next();
                } while (keys.hasNext());

                if (count > 50)
                    dataString = findGuide + "<hr><br>" + dataString;
                translate = ydtool.translationList(dataString, count, a);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(translate);
        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");

    }


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:07:38
     * @Params: null
     * @Return
     * @Description: 根据搜寻词模糊查找结点的简要信息，返回列表
     */
    public Map<Integer, Object> searchBriefDataInVarietyOfClass(String qk, UserOrderEntity userOrder) {
        Map<Integer, Object> res = new HashMap<>();
        int index = 0;
        Map<String, Object> tempQueryNodeList;
//        根据用户自定义查询的节点与节点顺序进行排序
        List<Integer> selectList = userOrder.getSelect();
        for (Integer i = 0; i < selectList.size(); i++) {
            Integer currentSelectIndex = selectList.get(i);
            if (currentSelectIndex.equals(TypeEnum.IndicatorsNode.getIndex())) {
                //查找【指标节点】
                tempQueryNodeList = indicatorService.selectIndicatorsLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            } else if (currentSelectIndex.equals(TypeEnum.BasicAndClassWord.getIndex())) {
                //查找【基本词类词节点】
                tempQueryNodeList = basicClassService.selectBCWInfoByNmLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            } else if (currentSelectIndex.equals(TypeEnum.Code.getIndex())) {
                //查找【代码节点】（基础数据标准）
                tempQueryNodeList = cnService.selectCodeNodeListByNmLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            } else if (currentSelectIndex.equals(TypeEnum.Report.getIndex())) {
                //查找【报表节点】
                tempQueryNodeList = reportService.selectReportNodeInfoByNmLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            } else if (currentSelectIndex.equals(TypeEnum.DataModelOfIBMNode.getIndex())) {
                //查找【IBM模型节点】
                tempQueryNodeList = ibmModelService.selectIBMModelByNmLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            } else if (currentSelectIndex.equals(TypeEnum.StandardOfData.getIndex())) {
                //        查找【基础数据标准节点】
                tempQueryNodeList = standardDataService.selectDataStandardNodeInfoByNmLike(qk);
                if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                    res.put(index, tempQueryNodeList);
                }
            }
            index++;
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
        if (secQueryKey.equals(TypeEnum.Code.getName())) {
            tempQueryNodeList = cnService.selectCodeNodeListByNm(qk);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }

        //查找【基本词类词节点】
        if (secQueryKey.equals(TypeEnum.BasicAndClassWord.getName())) {
            tempQueryNodeList = basicClassService.selectBCWInfoByNm(qk, secQueryKey);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }

//        查找【基础数据标准节点】
        if (secQueryKey.equals(TypeEnum.StandardOfData.getName())) {
            tempQueryNodeList = standardDataService.selectDataStandardNodeInfoByNm(qk, secQueryKey);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }
        //查找【指标节点】
        if (secQueryKey.equals(TypeEnum.IndicatorsNode.getName())) {
            tempQueryNodeList = indicatorService.selectIndicators(qk, null);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }
        //查找【报表节点】
        if (secQueryKey.equals(TypeEnum.Report.getName())) {
            tempQueryNodeList = reportService.selectReportNodeInfoByNm(qk, null);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }


        //查找【IBM模型节点】
        if (secQueryKey.equals(TypeEnum.DataModelOfIBMNode.getName())) {
            tempQueryNodeList = ibmModelService.selectIBMModelByNm(qk, null);
            if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
                res.put(index++, tempQueryNodeList);
            }
            return res;
        }
        return res;
    }

}
