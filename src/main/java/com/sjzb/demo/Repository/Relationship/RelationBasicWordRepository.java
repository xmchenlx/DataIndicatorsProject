package com.sjzb.demo.Repository.Relationship;

import com.sjzb.demo.model.BasicAndClassWordEntity;
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
public interface RelationBasicWordRepository extends Neo4jRepository<BasicAndClassWordEntity,Long> {
//“与CREATE中的属性不同，MATCH要求映射必须是文字。这是因为在编译查询时必须预先知道属性名称，以便有效地计划其执行。”
    //http://www.codesd.com/item/request-cypher-match-when-parameters-are-not-known-in-advance.html
//    @Override
//Iterable<RelationShipBasicWordEntity> findAll();

//    @Query("match (a)<-->(b) where a.Nm='$Nm' return a,b")
//    Object findByNm(@Param("Nm")String Nm);

    @Query("MATCH p=(a)-[:`abbr`]->(b)  return p")
    List<Object> findAllNode();

    @Query("MATCH p=(a)-[:`abbr`]->(b) where a.Nm=$Nm return p")
    List<Object> findAbbrRelationByNm(@Param("Nm") String nm);

}
