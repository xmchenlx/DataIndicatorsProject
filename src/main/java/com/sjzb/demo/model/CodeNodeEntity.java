package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-13 14:12:57
 * @Description: 代码节点
 */
@Node(value = "代码")
public class CodeNodeEntity extends BaseNodeEntity {
    @Id
    @GeneratedValue
    @Property(name = "Nm")
    private String nm;

    @Property(name = "Cd")
    private List<String> cd;

    @Property(name = "Cmnt")
    private List<String> cmnt;

    @Property(name = "Def")
    private Object def;

    @Property(name = "Src")
    private String src;

    @Property(name = "Ver")
    private String ver;

    @Property(name = "Orig")
    private List<String> orig;

    @Property(name = "Lvl")
    private List<String> lvl;

    @Property(name = "Cty_En_Nm")
    private List<String> cty_en_nm;
    @Property(name = "Ltr_Cd")
    private List<String> ltr_cd;
    @Property(name = "En_2SNm")
    private List<String> en_2snm;




    @Property(name = "Digt_Cd")
    private List<String> digt_cd;
    @Property(name = "Cty_Nm")
    private List<String> cty_nm;
//    @Property(name = "_Relation_")
//    private List<String> _relation_;

    public CodeNodeEntity() {
    }

    public CodeNodeEntity(String nm, List<String> cd, List<String> cmnt, Object def, String src, String ver, List<String> orig, List<String> lvl, List<String> cty_en_nm, List<String> ltr_cd, List<String> en_2snm, List<String> digt_cd, List<String> cty_nm) {
        this.nm = nm;
        this.cd = cd;
        this.cmnt = cmnt;
        this.def = def;
        this.src = src;
        this.ver = ver;
        this.orig = orig;
        this.lvl = lvl;
        this.cty_en_nm = cty_en_nm;
        this.ltr_cd = ltr_cd;
        this.en_2snm = en_2snm;
        this.digt_cd = digt_cd;
        this.cty_nm = cty_nm;
    }

    public CodeNodeEntity(String nm, List<String> cd, List<String> cmnt, Object def, String src, String ver) {
        this.nm = nm;
        this.cd = cd;
        this.cmnt = cmnt;
        this.def = def;
        this.src = src;
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

    public Object getDef() {
        return def;
    }

    public void setDef(Object def) {
        this.def = def;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<String> getOrig() {
        return orig;
    }

    public void setOrig(List<String> orig) {
        this.orig = orig;
    }

    public List<String> getLvl() {
        return lvl;
    }

    public void setLvl(List<String> lvl) {
        this.lvl = lvl;
    }

    public List<String> getCty_en_nm() {
        return cty_en_nm;
    }

    public void setCty_en_nm(List<String> cty_en_nm) {
        this.cty_en_nm = cty_en_nm;
    }

    public List<String> getLtr_cd() {
        return ltr_cd;
    }

    public void setLtr_cd(List<String> ltr_cd) {
        this.ltr_cd = ltr_cd;
    }

    public List<String> getEn_2snm() {
        return en_2snm;
    }

    public void setEn_2snm(List<String> en_2snm) {
        this.en_2snm = en_2snm;
    }

    public List<String> getDigt_cd() {
        return digt_cd;
    }

    public void setDigt_cd(List<String> digt_cd) {
        this.digt_cd = digt_cd;
    }

    public List<String> getCty_nm() {
        return cty_nm;
    }

    public void setCty_nm(List<String> cty_nm) {
        this.cty_nm = cty_nm;
    }

    @Override
    public String toString() {
        return "CodeNodeEntity{" +
                "nm='" + nm + '\'' +
                ", cd=" + cd +
                ", cmnt=" + cmnt +
                ", def=" + def +
                ", src='" + src + '\'' +
                ", ver='" + ver + '\'' +
                ", orig=" + orig +
                ", lvl=" + lvl +
                ", cty_en_nm=" + cty_en_nm +
                ", ltr_cd=" + ltr_cd +
                ", en_2snm=" + en_2snm +
                ", digt_cd=" + digt_cd +
                ", cty_nm=" + cty_nm +
                '}';
    }
}
