package com.sjzb.demo;

import com.sjzb.demo.model.LikeRelation;
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
public interface PSRelationRepository extends Neo4jRepository<LikeRelation,Long> {

    @Override
    List<LikeRelation> findAll();

    @Query("match (a)<-->(b) where a.Nm='$Nm' return a,b")
    List<LikeRelation> findAllByStartNode(@Param("Nm")String Nm);
}
