package com.sjzb.demo.service;

import com.sjzb.demo.Repository.DataModelOfIBMNodeRepository;
import com.sjzb.demo.model.DataModelOfIBMNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenlx
 * @Date: 2021-02-04 15:13:22
 * @Params: null
 * @Return
 * @Description: 基础数据标准服务层
 */
@Service
public class DataModelOfIBMNodeServiceImpl {

    @Autowired
    private DataModelOfIBMNodeRepository ibmDMRe;


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:24:16
     * @Params: null
     * @Return
     * @Description: 模糊查询
     */
    public Map<String, Object> selectIBMModelByNmLike(String querykey) {
        Map<String, Object> res = new HashMap<>();
//        List<Object> a = ibmDMRe.findDataModelOfIBMNodeEntityByNmLike(querykey);
        List<DataModelOfIBMNodeEntity> t = ibmDMRe.findDataModelOfIBMNodeEntityByNmLike(querykey);
        System.out.println(t);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
//        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());   --搜索应当按照当前实体来显示标签而非直接读取neo4j数据库里的结点标签遍历。下同。 20210204 15:16
//        List<String> nodeTagList = getListFromJson(tempTags.toString());
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("IBM数据模型分类"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
//        res.put("node_tag", nodeTagList);
        res.put("node_tag", nodeTagList);
        res.put("node_type", "DataModelOfIBMNodeEntity");
        res.put("len", t.size());
        return res;

    }


    public Map<String, Object> selectIBMModelByNm(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<DataModelOfIBMNodeEntity> t;
        t = ibmDMRe.findDataModelOfIBMNodeEntityByNm(querykey);
        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("IBM数据模型分类"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "DataModelOfIBMNodeEntity");
        res.put("len", t.size());
        return res;

    }
}
