package com.sjzb.demo.Repository;

import com.sjzb.demo.model.DataSourceEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: chenlx
 * @Date: 2021-02-02 10:33:29
 * @Params: null
 * @Return
 * @Description: 数据源操作仓库层
 */
public interface dataSourceRepository extends Neo4jRepository<DataSourceEntity, String> {

    @Query("MATCH (n:`数据元`) where n.Nm = $Nm return n limit 1")
    List<DataSourceEntity> findDataSourceEntityByNm(@Param("Nm") String nm);


}
