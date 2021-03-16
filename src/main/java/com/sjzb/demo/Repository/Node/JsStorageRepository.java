package com.sjzb.demo.Repository.Node;

import com.sjzb.demo.model.JsStorageEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:33:57
 * @Description:
 */
public interface JsStorageRepository extends Neo4jRepository<JsStorageEntity, Long> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);


    @Query("MATCH (a:`JS库包`) WHERE a.Nm = $Nm return a limit 1")
    JsStorageEntity findByNm(@Param("Nm") String nm);


}
