package com.sjzb.demo.service;

import com.sjzb.demo.Repository.Node.DataStandardRepository;
import com.sjzb.demo.model.StandardOfDataEntity;
import com.sjzb.demo.model.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author: chenlx
 * @Date: 2021-02-04 15:13:22
 * @Params: null
 * @Return
 * @Description: 基础数据标准服务层
 */
@Service
public class StandardDataNodeServiceImpl {
    @Autowired
    private DataStandardRepository dsRe;


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:24:16
     * @Params: null
     * @Return
     * @Description: 模糊查询
     */
    public Map<String, Object> selectDataStandardNodeInfoByNmLike(String querykey) {
        Map<String, Object> res = new HashMap<>();
        List<StandardOfDataEntity> t = dsRe.findStandardOfDataEntityByNmLike(querykey);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add(TypeEnum.StandardOfData.getName()); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "StandardOfDataEntity");
        res.put("len", t.size());
        return res;

    }


    public Map<String, Object> selectDataStandardNodeInfoByNm(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<StandardOfDataEntity> t;
        t = dsRe.findStandardOfDataEntityByNm(querykey);
        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList =  new ArrayList<>();
        nodeTagList.add(TypeEnum.StandardOfData.getName()); //updated in 2021-02-04 15:19
        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "StandardOfDataEntity");
        res.put("len", t.size());
        return res;

    }
}
