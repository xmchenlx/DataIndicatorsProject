package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.BasicClassWordsNodeRepository;
import com.sjzb.demo.Repository.Relationship.RelationBasicWordRepository;
import com.sjzb.demo.util.lxTool;
import com.sjzb.demo.model.BasicAndClassWordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sjzb.demo.util.lxTool.getListFromJson;

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
        if(t.size() == 1){
            addingRequestCount(res.get("node_Nm").toString(),t.get(0).getCnt());
        }
        return res;

    }

    public void addingRequestCount(String selectedNm,Integer nodeCnt){
        Integer nowClickCount = 1;
        if (nodeCnt != null && nodeCnt > 0) {
            nowClickCount += nodeCnt;
        }
        Object a = bcwRe.setNewCount(selectedNm, nowClickCount);
    }


    /**
     *
     * @Author: chenlx
     * @Date: 2021-03-04 14:46:01
     * @Params: null
     * @Return
     * @Description: 精确查询
     */
    public Map<String, Object> selectBCWInfoByNm(String querykey, String bnm) {
        Map<String, Object> res = new HashMap<>();
        List<BasicAndClassWordEntity> t;
        t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);
        res.put("len", t.size());
        if (t.size() == 0) {
            return res;
        }
        String selectedNm = t.iterator().next().getNm();
        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());
        List<String> nodeTagList = getListFromJson(tempTags.toString());
        List<Object> relationList =  new ArrayList<>(),tempList = new ArrayList<>();

        tempList.add(reRe.findAbbrRelationByNm(selectedNm));
        for(int i=0;i<tempList.size();i++){
            List<Object> temp = (List<Object>)tempList.get(i);
            for(int j=0;j<temp.size();j++){
                relationList.add(temp.get(j));
            }
        }

        res.put("node_relation",lxtool.ConvertPathValueToRelationshipMap(relationList,selectedNm));
        res.put("node_data", t);
        res.put("node_Nm", selectedNm);
        res.put("node_tag", nodeTagList);
        res.put("node_type", "BasicAndClassWordEntity");
        res.put("len", t.size());
        addingRequestCount(selectedNm,t.get(0).getCnt());
        return res;

    }


}
