package com.sjzb.demo.service;

import com.sjzb.demo.Repository.IndicatorsNodeRepository;
import com.sjzb.demo.model.IndicatorsNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenlx
 * @Date: 2021-02-07 15:23:45
 * @Params: null
 * @Return
 * @Description: 指标节点服务层
 */
@Service
public class IndicatorsNodeServiceImpl {

    @Autowired
    private IndicatorsNodeRepository indicateRe;


    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:24:16
     * @Params: null
     * @Return
     * @Description: 模糊查询
     */
    public Map<String, Object> selectIndicatorsLike(String querykey) {
        Map<String, Object> res = new HashMap<>();
//        List<Object> a = ibmDMRe.findDataModelOfIBMNodeEntityByNmLike(querykey);
        List<IndicatorsNodeEntity> t = indicateRe.findIndicatorsNodeEntityByNmLike(querykey);
        System.out.println(t);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("指标"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
//        res.put("node_tag", nodeTagList);
        res.put("node_tag", nodeTagList);
        res.put("node_type", "IndicatorsNodeEntity");
        res.put("len", t.size());
        return res;

    }


    public Map<String, Object> selectIndicators(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<IndicatorsNodeEntity> t;
        t = indicateRe.findIndicatorsNodeEntityByNm(querykey);
        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("指标"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "IndicatorsNodeEntity");
        res.put("len", t.size());
        return res;

    }
}
