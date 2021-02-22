package com.sjzb.demo.Repository.Relationship;

import com.sjzb.demo.model.IndicatorsNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:51:28
 * @Description:
 */
public interface RelationIndicatorNodeRepository extends Neo4jRepository<IndicatorsNodeEntity,Long> {
    //重复写SQL的原因：“与CREATE中的属性不同，MATCH要求映射必须是文字。这是因为在编译查询时必须预先知道属性名称，以便有效地计划其执行。”
    //http://www.codesd.com/item/request-cypher-match-when-parameters-are-not-known-in-advance.html



    @Query("MATCH p=(a)-[:`派生`]->(b) where b.Nm=$Nm return p")
    List<Object> findPSRelationByNmOut(@Param("Nm") String nm);

    @Query("MATCH p=(a)-[:`衍生`]->(b) where b.Nm=$Nm  return p")
    List<Object> findYSRelationByNmOut(@Param("Nm") String nm);

    @Query("MATCH p=(b)-[:`派生`]->(a) where b.Nm=$Nm return p")
    List<Object> findPSRelationByNmIn(@Param("Nm") String nm);

    @Query("MATCH p=(b)-[:`衍生`]->(a) where b.Nm=$Nm  return p")
    List<Object> findYSRelationByNmIn(@Param("Nm") String nm);


}
