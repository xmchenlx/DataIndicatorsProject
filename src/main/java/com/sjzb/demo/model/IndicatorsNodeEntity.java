package com.sjzb.demo.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

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
public class IndicatorsNodeEntity extends BaseNodeEntity {
//    @Id
//    @GeneratedValue
//    private String tid;

    @Property(name = "Nm")
    private String nm;

    @Property(name = "Unt")
    private String unt;
    @Property(name = "Src")
    private String src;
    @Property(name ="Attr")
    private String attr;
    @Property(name ="Clbr")
    private String clbr;
    @Property(name ="Cyc")
    private String cyc;
    @Property(name ="Def")
    private String def;
    @Property(name ="Fmt")
    private String fmt;
    @Property(name ="No")
    private String no;
    @Property(name ="Us")
    private List<String> us;


    public IndicatorsNodeEntity() {
    }

    public IndicatorsNodeEntity(String nm, String ver, String src, List<String> us, String src1, String attr, String clbr, String cyc, String def, String fmt, String no, String unt) {
//        super(nm);
        this.nm = nm;
        this.us = us;
        this.src = src1;
        this.attr = attr;
        this.clbr = clbr;
        this.cyc = cyc;
        this.def = def;
        this.fmt = fmt;
        this.no = no;
        this.unt = unt;
    }





    public List<String> getUs() {
        return us;
    }

    public void setUs(List<String> us) {
        this.us = us;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getClbr() {
        return clbr;
    }

    public void setClbr(String clbr) {
        this.clbr = clbr;
    }

    public String getCyc() {
        return cyc;
    }

    public void setCyc(String cyc) {
        this.cyc = cyc;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUnt() {
        return unt;
    }

    @Override
    public String getNm() {
        return nm;
    }

    @Override
    public void setNm(String nm) {
        this.nm = nm;
    }

    public void setUnt(String unt) {
        this.unt = unt;
    }
}
