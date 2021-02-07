package com.sjzb.demo.Repository;

import com.sjzb.demo.model.IndicatorsNodeEntity;
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
public interface IndicatorsNodeRepository extends Neo4jRepository<IndicatorsNodeEntity, String> {

    @Query("MATCH (n:`指标`) where n.Nm =~('.*'+$Nm+'.*') RETURN n")
    List<IndicatorsNodeEntity> findIndicatorsNodeEntityByNmLike(@Param("Nm") String nm);

    List<IndicatorsNodeEntity> findIndicatorsNodeEntityByNm(String Nm);



}
