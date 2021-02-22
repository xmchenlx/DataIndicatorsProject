package com.sjzb.demo.Repository.Relationship;

import com.sjzb.demo.model.ReportEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: chenlx
 * @Date: 2021-02-02 10:33:29
 * @Params: null
 * @Return
 * @Description: 报表操作仓库层
 */
public interface ReportRepository extends Neo4jRepository<ReportEntity, Long> {



    @Query("MATCH (n:`报表/报文`) where n.Nm =~('.*'+$Nm+'.*') RETURN n")
    List<ReportEntity> findReportEntityByNmLike(@Param("Nm") String nm);


    List<ReportEntity> findReportEntityByNm(@Param("Nm") String nm);

}
