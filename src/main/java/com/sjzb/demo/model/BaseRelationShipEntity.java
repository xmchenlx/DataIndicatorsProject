package com.sjzb.demo.model;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:27:03
 * @Description: to express the relationship that connect two node
 */
@Data
//@RelationshipProperties
    @RelationshipEntity(type = "")
public class BaseRelationShipEntity {
    /**
     * Neo4j会分配的ID（节点唯一标识 当前类中有效）
     */
    @Id
    @GeneratedValue
    private Long id;
//    @Property(name = "Nm")
//    private  String name;

    private final List<String> relation;

    @StartNode
//    @JsonIgnore
    @Property(name = "start")
    private  BasicAndClassWordEntity sn;

    @Property(name = "id")
    private String sourceId;

    @Property(name = "type")
    private String type;


//    @JsonIgnore
    @EndNode
    @Property(name = "end")
    private BasicAndClassWordEntity en;

    public BaseRelationShipEntity(List<String> relation, BasicAndClassWordEntity sn, String sourceId, String type, BasicAndClassWordEntity en) {
        this.relation = relation;
        this.sn = sn;
        this.sourceId = sourceId;
        this.type = type;
        this.en = en;
    }
}
