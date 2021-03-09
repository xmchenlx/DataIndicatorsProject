package com.sjzb.demo.model.Relationship;

import com.sjzb.demo.model.BaseRelationShipEntity;
import org.neo4j.ogm.annotation.RelationshipEntity;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:27:03
 * @Description: to express the relationship that connect two node
 */
//@RelationshipProperties
@RelationshipEntity(type = "属性")
public class RelationShipEntityDataModel extends BaseRelationShipEntity {
    public RelationShipEntityDataModel(List<String> relation, String sourceId) {
        super(relation, sourceId);
    }

}
