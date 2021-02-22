package com.sjzb.demo.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-25 11:09:27
 * @Description: 基本词类词模型
 */

@Node("基本词类词")
@NodeEntity(label = "基本词类词")
@Data
public class BasicAndClassWordEntity extends BaseNodeEntity {

//    @Id
//    @GeneratedValue
    @Property(name = "Nm")
    private String nm;

    @Property(name = "Cl")
    private String cl;


    public BasicAndClassWordEntity() {
    }


    public BasicAndClassWordEntity(String nm, String cl) {
        this.nm = nm;
        this.cl = cl;
    }

    public String getCl() {
        return cl;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }


    public void setCl(String cl) {
        this.cl = cl;
    }


    @Relationship(type = "abbr")
    private List<RelationShipBasicWordEntity> rel = new ArrayList<>();


}
