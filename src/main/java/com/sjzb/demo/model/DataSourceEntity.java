package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:37:05
 * @Description: basicNode
 */
@Node(value = "数据元")
public class DataSourceEntity {

    @Id
    @GeneratedValue
    private String id;

    @Property(name = "Attr")
    protected List<String> attr;

    @Property(name = "Cn")
    protected List<String> cn;

    @Property(name = "Nm")
    protected String nm;

    @Property(name = "Tp")
    protected List<String> tp;

    public DataSourceEntity(List<String> attr, List<String> cn, String nm, List<String> tp) {
        this.attr = attr;
        this.cn = cn;
        this.nm = nm;
        this.tp = tp;
    }

    public DataSourceEntity(){}

    public List<String> getAttr() {
        return attr;
    }

    public void setAttr(List<String> attr) {
        this.attr = attr;
    }

    public List<String> getCn() {
        return cn;
    }

    public void setCn(List<String> cn) {
        this.cn = cn;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public List<String> getTp() {
        return tp;
    }

    public void setTp(List<String> tp) {
        this.tp = tp;
    }
}
