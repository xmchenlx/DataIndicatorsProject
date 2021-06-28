package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.CModelRepository;
import com.sjzb.demo.model.CModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Author: chenlx
 * @Date: 2021-03-31 9:56:39
 * @Params: null
 * @Return
 * @Description: C模型服务层
 */
@Service
public class CModelServiceImpl {

    @Autowired
    private CModelRepository cmRe;

    public List<CModelEntity> getCModelList(Integer pageNum,Integer pageSize){
        return cmRe.findAll(pageNum,pageSize);
    }


//    /**
//     * @Author: chenlx
//     * @Date: 2021-01-26 15:25:32
//     * @Params: null
//     * @Return Map
//     * @Description: 模糊查询代码节点的信息，返回Map数据（Json）
//     */
//    public Map<String, Object> selectCodeNodeListByNmLike(String querykey) {
//        Map<String, Object> res = new HashMap<>();
//        List<CodeNodeEntity> tempNodeList = cnRe.findCodeNodeEntityByNmLike(querykey);
//        res.put("len", tempNodeList.size());
//        if (tempNodeList.size() == 0) {
//            return res;
//        }
////        Object tempTags = cnRe.findTagByNm(tempNodeList.get(0).getNm());
////        List<String> nodeTagList = getListFromJson(tempTags.toString());
//        List<String> nodeTagList = new ArrayList<>();
//        nodeTagList.add(TypeEnum.Code.getName());
//        res.put("node_data", tempNodeList);
//        res.put("node_Nm", tempNodeList.iterator().next().getNm());
//        res.put("node_tag", nodeTagList);
//        res.put("node_type", "CodeNodeEntity");
//        return res;
//
//    }
//
//    public Map<String, Object> selectCodeNodeListByNm(String querykey) {
//
//        Map<String, Object> res = new HashMap<>();
//        List<CodeNodeEntity> tempNodeList = cnRe.findCodeNodeEntityByNm(querykey);
//        res.put("len", tempNodeList.size());
//        if (tempNodeList.size() == 0) {
//            return res;
//        }
////        Object tempTags = cnRe.findTagByNm(tempNodeList.get(0).getNm());
//        List<String> nodeTagList = new ArrayList<>();
//        nodeTagList.add(TypeEnum.Code.getName());
////        BaseNodeEntity a = tempNodeList.get(0);
//        res.put("node_data", tempNodeList);
//        res.put("node_Nm", tempNodeList.iterator().next().getNm());
//        res.put("node_tag", nodeTagList);
//        res.put("node_type", "CodeNodeEntity");
//        return res;
//
//    }

}
