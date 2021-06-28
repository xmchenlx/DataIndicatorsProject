package com.sjzb.demo.Repository.Node;

import com.sjzb.demo.model.CModelEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:33:57
 * @Description:
 */
public interface CModelRepository extends Neo4jRepository<CModelEntity, Long> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);


    @Query("MATCH (n:`C模型`) RETURN n skip $startIndex limit $Num ")
    List<CModelEntity> findAll(@Param("startIndex")Integer startIndex,@Param("Num")Integer num);



}
