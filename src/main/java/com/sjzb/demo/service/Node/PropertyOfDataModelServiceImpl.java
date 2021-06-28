package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.PropertyOfDataModelRepository;
import com.sjzb.demo.model.PropertyOfDataModel;
import com.sjzb.demo.util.lxTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-08 10:08:08
 * @Description:
 */
@Service
public class PropertyOfDataModelServiceImpl {

    @Autowired
    private PropertyOfDataModelRepository pdmRe;

    private lxTool lxtool = new lxTool();


    /**
     *
     * @Author: chenlx
     * @Date: 2021-03-04 14:46:01
     * @Params: null
     * @Return
     * @Description: 精确查询
     */
//    public Map<String, Object> selectBCWInfoByNm(String querykey, String bnm) {
//        Map<String, Object> res = new HashMap<>();
////        List<BasicAndClassWordEntity> t;
////        t = bcwRe.findBasicAndClassWordEntitiesByNm(querykey);
//        res.put("len", 0);
////        String selectedNm = t.iterator().next().getNm();
////        Object tempTags = bcwRe.findTagByNm(t.get(0).getNm());
////        List<String> nodeTagList = getListFromJson(tempTags.toString());
////        List<Object> relationList =  new ArrayList<>(),tempList = new ArrayList<>();
//
////        tempList.add(reRe.findAbbrRelationByNm(selectedNm));
////        for(int i=0;i<tempList.size();i++){
////            List<Object> temp = (List<Object>)tempList.get(i);
////            for(int j=0;j<temp.size();j++){
////                relationList.add(temp.get(j));
////            }
////        }
//        List<PropertyOfDataModel> relist = mdReRe.findByNm(querykey);
//        res.put("node_relation",relist);
////        res.put("node_data", null);
////        res.put("node_Nm", selectedNm);
////        res.put("node_tag", nodeTagList);
//        res.put("node_type", "PropertyOfDataModel");
////        res.put("len", 0);
//        return res;
//
//    }


    public Integer getPDMCount(){
        return pdmRe.getAllItemCount();
    }
    public List<PropertyOfDataModel> getPDMList(Integer pageNum,Integer pageSize){
        return pdmRe.findAll(pageNum,pageSize);
    }
}
