package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
//import org.springframework.data.neo4j.core.schema.Property;
import java.util.List;

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


    @Property(name = "Cd")
    protected List<String> cd;

    @Property(name = "Cmnt")
    protected List<String> cmnt;

    @Property(name = "Ver")
    protected String ver;

    @Property(name = "Src")
    protected String src;


    public BaseNodeEntity(){}



    public BaseNodeEntity(String nm, List<String> cd, List<String> cmnt, String ver, String src) {
        this.nm = nm;
        this.cd = cd;
        this.src=src;
        this.cmnt = cmnt;
        this.ver = ver;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public List<String> getCd() {
        return cd;
    }

    public void setCd(List<String> cd) {
        this.cd = cd;
    }

    public List<String> getCmnt() {
        return cmnt;
    }

    public void setCmnt(List<String> cmnt) {
        this.cmnt = cmnt;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
