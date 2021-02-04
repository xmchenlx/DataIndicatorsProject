package com.sjzb.demo.service;

import com.sjzb.demo.Repository.CodeNodeRepository;
import com.sjzb.demo.model.CodeNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sjzb.demo.Result.lxTool.getListFromJson;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-20 15:02:45
 * @Description:
 */
@Service
public class CodeNodeServiceImpl {

    @Autowired
    private CodeNodeRepository cnRe;

    /**
     * @Author: chenlx
     * @Date: 2021-01-26 15:25:32
     * @Params: null
     * @Return Map
     * @Description: 模糊查询代码节点的信息，返回Map数据（Json）
     */
    public Map<String, Object> selectCodeNodeListByNmLike(String querykey) {
        Map<String, Object> res = new HashMap<>();
        List<CodeNodeEntity> tempNodeList = cnRe.findCodeNodeEntityByNmLike(querykey);
        res.put("len", tempNodeList.size());
        if (tempNodeList.size() == 0) {
            return res;
        }
        Object tempTags = cnRe.findTagByNm(tempNodeList.get(0).getNm());
        List<String> nodeTagList = getListFromJson(tempTags.toString());

        res.put("node_data", tempNodeList);
        res.put("node_Nm", tempNodeList.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "CodeNodeEntity");
        return res;

    }

    public Map<String, Object> selectCodeNodeListByNm(String querykey) {
        Map<String, Object> res = new HashMap<>();
        List<CodeNodeEntity> tempNodeList = cnRe.findCodeNodeEntityByNm(querykey);
        res.put("len", tempNodeList.size());
        if (tempNodeList.size() == 0) {
            return res;
        }
        Object tempTags = cnRe.findTagByNm(tempNodeList.get(0).getNm());
//        List<String> nodeTagList = getListFromJson(tempTags.toString());
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("代码");

        res.put("node_data", tempNodeList);
        res.put("node_Nm", tempNodeList.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "CodeNodeEntity");
        return res;

    }

}
