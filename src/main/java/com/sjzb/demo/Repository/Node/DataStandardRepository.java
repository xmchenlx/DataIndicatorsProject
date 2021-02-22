package com.sjzb.demo.Repository.Node;

import com.sjzb.demo.model.StandardOfDataEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: chenlx
 * @Date: 2021-02-02 10:33:29
 * @Params: null
 * @Return
 * @Description: 基础数据标准操作仓库层
 */
public interface DataStandardRepository extends Neo4jRepository<StandardOfDataEntity, Long> {

//    @Query("MATCH (n:`数据元`) where n.Nm = $Nm return n limit 1")
//    List<DataSourceEntity> findDataSourceEntityByNm(@Param("Nm") String nm);


    @Query("MATCH (n:`基础数据标准`) where n.Nm =~('.*'+$Nm+'.*') RETURN n")
    List<StandardOfDataEntity> findStandardOfDataEntityByNmLike(@Param("Nm") String nm);


    List<StandardOfDataEntity> findStandardOfDataEntityByNm(@Param("Nm") String nm);

}
