package com.sjzb.demo;

import com.sjzb.demo.model.BasicAndClassWordEntity;
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
public interface BasicClassWordsNodeRepository extends Neo4jRepository<BasicAndClassWordEntity, String> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);


    @Query("MATCH (a:`基本词类词`)-[:abbr]->(b:`基本词类词`) WHERE a.Nm = $Nm return b")
    List<BasicAndClassWordEntity> findBasicAndClassWordEntitiesByNm(@Param("Nm") String nm);

        @Query("MATCH (a:`基本词类词`)-[:abbr]->(b:`基本词类词`) WHERE a.Nm=~('.*'+$Nm+'.*') return b")
    List<BasicAndClassWordEntity> findBasicAndClassWordEntitiesByNmLike(@Param("Nm") String nm);


    @Query("match (n:`基本词类词`)  where n.Nm = $Nm return labels(n)")
    Object findTagByNm(@Param("Nm")String Nm);

}
