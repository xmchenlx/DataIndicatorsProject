package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.DataModelOfIBMNodeRepository;
import com.sjzb.demo.model.DataModelOfIBMNodeEntity;
import com.sjzb.demo.model.TypeEnum;
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
        List<DataModelOfIBMNodeEntity> t = ibmDMRe.findDataModelOfIBMNodeEntityByNmLike(querykey);
        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add(TypeEnum.DataModelOfIBMNode.getName());
        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
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
        nodeTagList.add(TypeEnum.DataModelOfIBMNode.getName());
        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "DataModelOfIBMNodeEntity");
        res.put("len", t.size());
        return res;
    }
}
