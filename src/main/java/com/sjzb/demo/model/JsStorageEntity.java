package com.sjzb.demo.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

//import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:37:05
 * @Description: basicNode
 */
@Node(value = "JS库包")
@NodeEntity(label = "JS库包")
@Data
public class JsStorageEntity {
    @Id
    @GeneratedValue
    @Property(name = "id")
    private Long id;

    @Property(name = "Nm")
    private String nm;

    @Property(name = "Ver")
    private String ver;

    @Property(name = "Cd")
    private String cd;

    public JsStorageEntity(String nm, String ver, String cd) {
        this.nm = nm;
        this.ver = ver;
        this.cd = cd;
    }

    public JsStorageEntity() {
    }
}
