package com.sjzb.demo.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @Author: chenlx
 * @Date: 2021-02-04 15:11:12
 * @Params: null
 * @Return
 * @Description: 报表报文实体
 */
@Node(value = "报表/报文")
public class ReportEntity extends  BaseNodeEntity{
//    @Id
//    @GeneratedValue
//    private String tid;


    @Property(name = "Nm")
    private String nm;

    @Property(name = "Cl")
    private String cl;

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public ReportEntity(String nm, String cl) {
        this.nm = nm;
        this.cl = cl;
    }

    public ReportEntity() {
    }
}
