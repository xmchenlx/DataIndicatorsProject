package com.sjzb.demo.service;

import com.sjzb.demo.BasicClassWordsNodeRepository;
import com.sjzb.demo.model.BasicAndClassWordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sjzb.demo.Result.lxTool.getListFromJson;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-25 14:19:32
 * @Description:
 */
@Service
public class BasicClassNodeServiceImpl  {

    @Autowired
    private BasicClassWordsNodeRepository bcwRe;

//    public List<BasicAndClassWordEntity> selectBCWInfoByNm(String querykey){
//        List<BasicAndClassWordEntity> bacwList = bcwRe.findByNm(querykey);
////        if(bacwList.size() !=0){
////            bacwList
////        }
//        return bacwList;
//    }
    /**
     *
     * @Author: chenlx
     * @Date: 2021-01-27 15:24:16
     * @Params: null
     * @Return
     * @Description: 模糊查询
     */
    public Map<String,Object> selectBCWInfoByNmLike(String querykey){
        Map<String, Object> res = new HashMap<>();
        List<BasicAndClassWordEntity> t = bcwRe.findBasicAndClassWordEntitiesByNmLike(querykey);
        System.out.println(t);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());
        List<String> nodeTagList = getListFromJson(tempTags.toString());

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "BasicAndClassWordEntity");
        res.put("len", t.size());
        return res;

    }

    public Map<String, Object> selectBCWInfoByNm(String querykey) {
        Map<String, Object> res = new HashMap<>();
        List<BasicAndClassWordEntity> t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);
        System.out.println(t);
//        List<BasicAndClassWordEntity> t = Convert.convert(new TypeReference<List<BasicAndClassWordEntity>>() {
//        }, ttempNodeList);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());
        List<String> nodeTagList = getListFromJson(tempTags.toString());

        res.put("node_data", t);
        res.put("node_Nm", t.iterator().next().getNm());
        res.put("node_tag", nodeTagList);
        res.put("node_type", "BasicAndClassWordEntity");
        res.put("len", t.size());
        return res;

    }
}
