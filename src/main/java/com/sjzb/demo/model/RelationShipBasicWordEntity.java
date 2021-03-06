package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:27:03
 * @Description: to express the relationship that connect two node
 */
//@RelationshipProperties
@RelationshipEntity(type = "attr")
public class RelationShipBasicWordEntity extends BaseRelationShipEntity{
    public RelationShipBasicWordEntity(List<String> relation, BasicAndClassWordEntity sn, String sourceId, String type, BasicAndClassWordEntity en) {
        super(relation, sn, sourceId, type, en);
    }
}
