package com.sjzb.demo.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

//import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:37:05
 * @Description: IBM数据模型节点
 */
@Node(value = "IBM数据模型分类")
public class DataModelOfIBMNodeEntity extends BaseNodeEntity{
    @Property(name = "Cd")
    private String mycd;

    @Property(name = "Cmnt")
    private String mycmnt;
    @Property(name = "Nm")
    private String nm;

    @Property(name = "Ver")
    private String ver;
    @Property(name = "Src")
    private String src;

    public DataModelOfIBMNodeEntity(String mycd, String mycmnt, String nm, String ver, String src) {
        this.mycd = mycd;
        this.mycmnt = mycmnt;
        this.nm = nm;
        this.ver = ver;
        this.src = src;
    }

    public String getMycd() {
        return mycd;
    }

    public void setMycd(String mycd) {
        this.mycd = mycd;
    }

    public String getMycmnt() {
        return mycmnt;
    }

    public void setMycmnt(String mycmnt) {
        this.mycmnt = mycmnt;
    }

    @Override
    public String getNm() {
        return nm;
    }

    @Override
    public void setNm(String nm) {
        this.nm = nm;
    }

    @Override
    public String getVer() {
        return ver;
    }

    @Override
    public void setVer(String ver) {
        this.ver = ver;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }
}
