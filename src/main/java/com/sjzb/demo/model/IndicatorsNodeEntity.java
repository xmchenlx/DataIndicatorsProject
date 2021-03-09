package com.sjzb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author: chenlx
 * @Date: 2021-02-07 14:31:38
 * @Params: null
 * @Return
 * @Description: 指标节点实体
 */
@Node(value = "指标")
@NodeEntity(label = "指标")
@Data
@ConstructorBinding
@AllArgsConstructor
public class IndicatorsNodeEntity extends BaseNodeEntity {

    @Property(name = "Nm")
    private String nm;

    @Property(name = "Unt")
    private String unt;
    @Property(name = "Src")
    private String src;
    @Property(name ="Attr")
    private String attr;
    @Property(name ="Clbr")
    private Object clbr;
    @Property(name ="Cyc")
    private List<String> cyc;
    @Property(name ="Def")
    private String def;
    @Property(name ="Fmt")
    private String fmt;
    @Property(name ="No")
    private String no;
    @Property(name ="Us")
    private List<String> us;

    @Property(name = "Snstv")
    private String snstv;

    @Property(name = "Idx")
    private Object idx;

    @Property(name = "Meta")
    private String meta;




    public IndicatorsNodeEntity() {
    }




    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }



    @Override
    public String getNm() {
        return nm;
    }

    @Override
    public void setNm(String nm) {
        this.nm = nm;
    }


    @Relationship(type = "派生")
    private List<IndicatorsNodeEntity> rel = new ArrayList<>();
}
