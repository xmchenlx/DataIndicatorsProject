package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

//import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 16:37:05
 * @Description: basicNode
 */
@Node
public class BaseNodeEntity {

//    @GraphId
    @Id
    @GeneratedValue
    @Property(name = "id")
    private Long id;

    @Property(name = "Nm")
    private String nm;


    @Property(name = "Cd")
    private List<String> basecd;

    @Property(name = "Cmnt")
    private List<String> basecmnt;

    @Property(name = "Ver")
    private String basever;

    @Property(name = "Src")
    private String basesrc;


    public BaseNodeEntity() {
    }


    public BaseNodeEntity(String nm) {
        this.nm = nm;
    }

    public BaseNodeEntity(String nm, List<String> cd, List<String> cmnt, String ver, String src) {
        this.nm = nm;
        this.basecd = cd;
        this.basesrc = src;
        this.basecmnt = cmnt;
        this.basever = ver;
    }

    public BaseNodeEntity(String nm, String ver, String src) {
        this.nm = nm;
        this.basever = ver;
        this.basesrc = src;
    }

    public BaseNodeEntity(String nm, String basesrc) {
        this.nm = nm;
        this.basesrc = basesrc;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public List<String> getCd() {
        return basecd;
    }

    public void setCd(List<String> cd) {
        this.basecd = cd;
    }

    public List<String> getCmnt() {
        return basecmnt;
    }

    public void setCmnt(List<String> cmnt) {
        this.basecmnt = cmnt;
    }

    public String getVer() {
        return basever;
    }

    public void setVer(String ver) {
        this.basever = ver;
    }


    public String getSrc() {
        return basesrc;
    }

    public void setSrc(String src) {
        this.basesrc = src;
    }

    public BaseNodeEntity(Long id, String nm, List<String> basecd, List<String> basecmnt, String basever, String basesrc) {
        this.id = id;
        this.nm = nm;
        this.basecd = basecd;
        this.basecmnt = basecmnt;
        this.basever = basever;
        this.basesrc = basesrc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseNodeEntity(Long id) {
        this.id = id;
    }
}
