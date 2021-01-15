package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:37:05
 * @Description: basicNode
 */
public class BaseNodeEntity {

    @GraphId
    private Long id;

    @Property(name = "Nm")
    protected String nm;

    public BaseNodeEntity(){}

    public BaseNodeEntity(String nm) {
        this.nm = nm;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }
}
