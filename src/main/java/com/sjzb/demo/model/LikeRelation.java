package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:27:03
 * @Description: to express the relationship that connect two node
 */

@RelationshipEntity(type = "派生")
public class LikeRelation {

    @GraphId
    private Long id;

    /**
     * 定义关系的起始节点 == StartNode
     */

    @StartNode
    private BaseNodeEntity startNode;

    /**
     * 定义关系的终止节点 == EndNode
     */

    @EndNode
    private BaseNodeEntity endNode;


    /**
     * 定义关系的属性
     */

    @Property(name = "reason")
    private String  reason;
    @Property(name = "since")
    private Integer since;
    @Property(name = "relationID")
    private Integer relationID;

    public LikeRelation() {
    }

    public LikeRelation(BaseNodeEntity startNode, BaseNodeEntity endNode, String reason, Integer since, Integer relationID) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.reason = reason;
        this.since = since;
        this.relationID = relationID;
    }

    public BaseNodeEntity getStartNode() {
        return startNode;
    }

    public void setStartNode(BaseNodeEntity startNode) {
        this.startNode = startNode;
    }

    public BaseNodeEntity getEndNode() {
        return endNode;
    }

    public void setEndNode(BaseNodeEntity endNode) {
        this.endNode = endNode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    public Integer getRelationID() {
        return relationID;
    }

    public void setRelationID(Integer relationID) {
        this.relationID = relationID;
    }
}
