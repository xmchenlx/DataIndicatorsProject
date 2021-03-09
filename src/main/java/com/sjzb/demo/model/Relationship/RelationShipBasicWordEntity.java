package com.sjzb.demo.model.Relationship;

import com.sjzb.demo.model.BaseRelationShipEntity;
import com.sjzb.demo.model.BasicAndClassWordEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:27:03
 * @Description: to express the relationship that connect two node
 */
//@RelationshipProperties
@RelationshipEntity(type = "attr")
public class RelationShipBasicWordEntity extends BaseRelationShipEntity {

    @Property(name = "type")
    private String type;

    @EndNode
    @Property(name = "end")
    private BasicAndClassWordEntity en;

    @StartNode
//    @JsonIgnore
    @Property(name = "start")
    private BasicAndClassWordEntity sn;

    public RelationShipBasicWordEntity(List<String> relation, String sourceId, String type, BasicAndClassWordEntity en, BasicAndClassWordEntity sn) {
        super(relation, sourceId);
        this.type = type;
        this.en = en;
        this.sn = sn;
    }
}
