package com.sjzb.demo.service;

import com.sjzb.demo.Repository.Node.DataStandardRepository;
import com.sjzb.demo.model.StandardOfDataEntity;
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
//        Object a = dsRe.findStandardOfDataEntityByNmLike(querykey);
        List<StandardOfDataEntity> t = dsRe.findStandardOfDataEntityByNmLike(querykey);
        System.out.println(t);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
//        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());   --搜索应当按照当前实体来显示标签而非直接读取neo4j数据库里的结点标签遍历。下同。 20210204 15:16
//        List<String> nodeTagList = getListFromJson(tempTags.toString());
        List<String> nodeTagList = new ArrayList<>();
        nodeTagList.add("基础数据标准"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
//        res.put("node_tag", nodeTagList);
        res.put("node_tag", nodeTagList);
        res.put("node_type", "StandardOfDataEntity");
        res.put("len", t.size());
        return res;

    }


    public Map<String, Object> selectDataStandardNodeInfoByNm(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<StandardOfDataEntity> t;
        //版本迭代， 暂时只需要先找出自己本身的信息，目前先注释处理 2021-2-4 14:23
//        if (!bnm.equals(null)) {
//            t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey, bnm);
//        } else {
//            t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);
//
//        }
        t = dsRe.findStandardOfDataEntityByNm(querykey);

//        List<BasicAndClassWordEntity> t = Convert.convert(new TypeReference<List<BasicAndClassWordEntity>>() {
//        }, ttempNodeList);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
//        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());   --搜索应当按照当前实体来显示标签而非直接读取neo4j数据库里的结点标签遍历。下同。 20210204 15:16
//        List<String> nodeTagList = getListFromJson(tempTags.toString());
        List<String> nodeTagList =  new ArrayList<>();
        nodeTagList.add("基础数据标准"); //updated in 2021-02-04 15:19

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "StandardOfDataEntity");
        res.put("len", t.size());
        return res;

    }
}
