package com.sjzb.demo.service;

import com.sjzb.demo.Repository.Node.BasicClassWordsNodeRepository;
import com.sjzb.demo.Repository.Relationship.RelationBasicWordRepository;
import com.sjzb.demo.tool.lxTool;
import com.sjzb.demo.model.BasicAndClassWordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sjzb.demo.tool.lxTool.getListFromJson;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-25 14:19:32
 * @Description:
 */
@Service
public class BasicClassNodeServiceImpl {

    @Autowired
    private BasicClassWordsNodeRepository bcwRe;

    @Autowired
    private RelationBasicWordRepository reRe;

    private lxTool lxtool = new lxTool();

//    public List<BasicAndClassWordEntity> selectBCWInfoByNm(String querykey){
//        List<BasicAndClassWordEntity> bacwList = bcwRe.findByNm(querykey);
////        if(bacwList.size() !=0){
////            bacwList
////        }
//        return bacwList;
//    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-27 15:24:16
     * @Params: null
     * @Return
     * @Description: 模糊查询
     */
    public Map<String, Object> selectBCWInfoByNmLike(String querykey) {
        Map<String, Object> res = new HashMap<>();
        List<BasicAndClassWordEntity> t = bcwRe.findBasicAndClassWordEntitiesByNmLike(querykey);
//        System.out.println(t);

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


    public Map<String, Object> selectBCWInfoByNm(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<BasicAndClassWordEntity> t;
        //版本迭代， 暂时只需要先找出自己本身的信息，目前先注释处理 2021-2-4 14:23
//        if (!bnm.equals(null)) {
//            t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey, bnm);
//        } else {
//            t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);
//
//        }
        t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);

//        List<BasicAndClassWordEntity> t = Convert.convert(new TypeReference<List<BasicAndClassWordEntity>>() {
//        }, ttempNodeList);

        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        String selectedNm = t.iterator().next().getNm();
        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());
        List<String> nodeTagList = getListFromJson(tempTags.toString());
//        List<Object> relationList =  reRe.findAbbrRelationByNm(selectedNm);
        List<Object> relationList =  new ArrayList<>(),tempList = new ArrayList<>();

        tempList.add(reRe.findAbbrRelationByNm(selectedNm));
        for(int i=0;i<tempList.size();i++){
            List<Object> temp = (List<Object>)tempList.get(i);
            for(int j=0;j<temp.size();j++){
                relationList.add(temp.get(j));
            }
        }

        res.put("node_relation",lxtool.ConvertPathValueToRelationshipMap(relationList,selectedNm));

//        res.put("node_relation",lxtool.tempConvertPathValueToRelationshipMap(relationList));
        res.put("node_data", t);
        res.put("node_Nm", selectedNm);
        res.put("node_tag", nodeTagList);
        res.put("node_type", "BasicAndClassWordEntity");
        res.put("len", t.size());
        return res;

    }


}
