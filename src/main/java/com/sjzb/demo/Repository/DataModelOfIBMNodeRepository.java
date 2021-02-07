package com.sjzb.demo.Repository;

import com.sjzb.demo.model.DataModelOfIBMNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:33:57
 * @Description:IBM数据模型数据库操作层
 */
public interface DataModelOfIBMNodeRepository extends Neo4jRepository<DataModelOfIBMNodeEntity, String> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);

    List<DataModelOfIBMNodeEntity> findDataModelOfIBMNodeEntityByNm(@Param("Nm") String nm);

    @Query("MATCH (n:`IBM数据模型分类`) where n.Nm =~('.*'+$Nm+'.*') RETURN n")
    List<DataModelOfIBMNodeEntity> findDataModelOfIBMNodeEntityByNmLike(@Param("Nm") String nm);

}
