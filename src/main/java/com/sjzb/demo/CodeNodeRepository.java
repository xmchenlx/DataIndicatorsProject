package com.sjzb.demo;

import com.sjzb.demo.model.CodeNodeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:33:57
 * @Description:
 */
public interface CodeNodeRepository extends Neo4jRepository<CodeNodeEntity,String> {

//    CodeNodeEntity findCodeNodeEntityByNmLike(String Nm);

//    @Query("MATCH (n:`测试标签`) RETURN n LIMIT 1")
    Collection<CodeNodeEntity> findCodeNodeEntityByNmLike(String Nm);

    Collection<CodeNodeEntity> findCodeNodeEntityByNm(String Nm);

    @Query("match (n) where n.Nm = $Nm and n.Cd = $Cd return n")
    Collection<CodeNodeEntity> findCodeNodeEntityByNmAndCd(@Param("Nm")String nm, @Param("Cd")String cd);
    //cd存储的是数组，该查询写法无效。


}
