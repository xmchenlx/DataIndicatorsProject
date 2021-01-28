package com.sjzb.demo.controller;

import com.sjzb.demo.BasicClassWordsNodeRepository;
import com.sjzb.demo.CodeNodeRepository;
import com.sjzb.demo.PSRelationRepository;
import com.sjzb.demo.model.BaseNodeEntity;
import com.sjzb.demo.service.BasicClassNodeServiceImpl;
import com.sjzb.demo.service.CodeNodeServiceImpl;
import com.sjzb.demo.service.youdaoTool;
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
    CodeNodeRepository cnRe;

    @Autowired
    BasicClassWordsNodeRepository bcwRe;

    @Autowired
    PSRelationRepository psRe;


    youdaoTool ydtool = new youdaoTool();
    @Autowired
    CodeNodeServiceImpl cnService;
    @Autowired
    BasicClassNodeServiceImpl basicClassService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:07:38
     * @Params: null
     * @Return
     * @Description: 根据搜寻词查找结点的简要信息，返回列表
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

        return res;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-25 16:54:03
     * @Params: null
     * @Return
     * @Description: 往所有实体节点寻找完整的信息
     */
    public Map<String, Object> searchFullDataInVarietyOfClass(String qk) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> tempQueryNodeList;
        //查询代码节点是否存在数据（Code Node）
        tempQueryNodeList = cnService.selectCodeNodeListByNmLike(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put("nodeName", "CodeNode");
            res.put("data", tempQueryNodeList);
            return res;
        }
        //查询【基本词类词】节点是否存在数据（Basic And Class Words Node）
        tempQueryNodeList = basicClassService.selectBCWInfoByNm(qk);
        if (Integer.parseInt(String.valueOf(tempQueryNodeList.get("len"))) != 0) {
            res.put("nodeName", "BasicAndClassNode");
            res.put("data", tempQueryNodeList);
            return res;
        }


        //已知类型节点搜寻完未找到时返回相关信息
        res.put("nodeName", "_NotFound_");
        res.put("data", null);
        return res;
    }

    @GetMapping("/*")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long _begin = System.currentTimeMillis();

        //提取Request信息里的字段
        String queryKey = request.getParameter("q");
        System.out.println("查询字段：" + queryKey);
        String translate = "";
        String dataString = "";
        String a = "" +
                "function research($v){  alert($v);document.getElementsByTagName('input')[0].value = $v;}" +
//                "var p = document.body.getElementsByTagName('*');" +
//                "document.getElementById('tes').innerHTML = p;" +
//                "window.location.reload()" +
//                "$('p').click(function(){alert('测试')})" +

                "function selfHighLightCSS(){alert('asd');document.body.innerHTML+='!2';event.target.style.color='red';}" +
                "function selfRemoveLightCSS(){event.target.style.color='lightcoral'}" +
//                "$('.listText').live(\"click\",function(){selfHighLightCSS(this)});"+

//                "</script>" +
                "<style>.listText{color:darkred; font-size:16px;margin:0;padding:0;} .listText:hover{color:lightcoral;font-weight:bold;cursor:pointer}</style>"
                +"";
//        dataString += a;
        int count = 0;//查询数量

        //过滤不小心触碰的请求
        if (queryKey == "Yodao dict Retest" || queryKey == "null") {
            return;
        }

        Map<Integer, Object> searchBriefRes = searchBriefDataInVarietyOfClass(queryKey);
        if (searchBriefRes.size() == 0) {
            //查询无结果时，返回未知信息
            System.out.println("找不到信息：" + queryKey);
            translate = ydtool.getUnkown(queryKey);
        } else {
            //循环节点实体数据
            for (int m = 0; m < searchBriefRes.size(); m++) {
                Map<String, Object> tempBriefRes = (Map<String, Object>) searchBriefRes.get(m);
                List<?> queryDataList = (List<?>) tempBriefRes.get("node_data");
//                if (m != 0 && m < searchBriefRes.size()) dataString += "<hr/>";
//                String hightlightcss ="on"
                //循环节点内的List数据
                String nodeTag = tempBriefRes.get("node_tag").toString().replace("Optional", "");
                dataString += "<h5 style='color:black;'>" + nodeTag + "（" + queryDataList.size() + "项）</h5>";
                for (int i = 0; i < queryDataList.size(); i++) {
//                Map<String, Object> nodeData = (HashMap) searchBriefRes.get(i);
                    BaseNodeEntity nodeData = (BaseNodeEntity) queryDataList.get(i);
                    String nodeName = nodeData.getNm();
//                String nodeName = nodeData.get("node_Nm").toString();
//                String nodeTag = nodeData.get("node_tag").toString().replace("Optional","");
                    count++;
                    dataString += "<p class='listText' onmousemove='selfHighLightCSS(this)' onclick='research(\""+nodeName+"\")'>" + nodeName + "</p>";
 //                    dataString += "<p style='text-align:right;font-size:13px'>来源：";
//
//                    dataString += nodeTag + "</p>";
//                    if (i < queryDataList.size() - 1)
//                        dataString += "<hr/>";
                }
                dataString += "<br/>";
            }

//            translationList
            translate = ydtool.translationList(dataString, count,a);

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
        System.out.println(translate);
        response.getWriter().print(translate);

        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//
    }
}
