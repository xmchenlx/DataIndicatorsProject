package com.sjzb.demo.Repository.Node;

import com.sjzb.demo.model.PropertyOfDataModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:33:57
 * @Description:
 */
public interface PeopertyOfDataModelRepository extends Neo4jRepository<PropertyOfDataModel, Long> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);

    @Query("MATCH (n:`数据模型-属性`)  return n")
    List<PropertyOfDataModel> findAllPropertyOfDataModel();

}
