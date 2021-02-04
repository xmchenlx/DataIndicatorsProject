package com.sjzb.demo.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

/**
 * @Author: chenlx
 * @Date: 2021-02-04 15:11:12
 * @Params: null
 * @Return
 * @Description: 基础数据标准节点实体
 */
@Node(value = "基础数据标准")
public class StandardOfDataEntity extends BaseNodeEntity {
    //    @Id
//    @GeneratedValue
//    private String id;
    @Id
    @GeneratedValue
    private String tid;


    @Property(name = "No")
    private String no;
    @Property(name = "EN_Nm")
    private String en_nm;
    @Property(name = "EN_SNm")
    private String en_snm;
    @Property(name = "Def")
    private Object def;
    @Property(name = "Rul")
    private String rul;
    @Property(name = "Acg")
    private String acg;
    @Property(name = "Cgy")
    private String cgy;
    @Property(name = "Fmt")
    private String fmt;
    @Property(name = "Unt")
    private String unt;
    @Property(name = "Def_Dep")
    private String def_dep;
    @Property(name = "Mng_Dep")
    private String mng_dep;
    @Property(name = "Pd_Dep")
    private String pd_dep;
    @Property(name = "Eff_Dt")
    private String eff_dt;
    @Property(name = "Exp_Dt")
    private String exp_dt;
    @Property(name = "RChk_Dt")
    private String rchk_dt;
    @Property(name = "Mnt_Tm")
    private String mnt_tm;
    @Property(name = "Mnr")
    private String mnr;



    //-------------------------

    @Property(name = "Nm")
    private String nm;


    @Property(name = "Cd")
    private List<String> cd;

    @Property(name = "Cmnt")
    private List<String> cmnt;

    @Property(name = "Ver")
    private String ver;

    @Property(name = "Src")
    private String src;

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

    public StandardOfDataEntity(String no, String en_nm, String en_snm, Object def, String rul, String acg, String cgy, String fmt, String unt, String def_dep, String mng_dep, String pd_dep, String eff_dt, String exp_dt, String rchk_dt, String mnt_tm, String mnr, String nm, List<String> cd, List<String> cmnt, String ver, String src) {
        this.no = no;
        this.en_nm = en_nm;
        this.en_snm = en_snm;
        this.def = def;
        this.rul = rul;
        this.acg = acg;
        this.cgy = cgy;
        this.fmt = fmt;
        this.unt = unt;
        this.def_dep = def_dep;
        this.mng_dep = mng_dep;
        this.pd_dep = pd_dep;
        this.eff_dt = eff_dt;
        this.exp_dt = exp_dt;
        this.rchk_dt = rchk_dt;
        this.mnt_tm = mnt_tm;
        this.mnr = mnr;
        this.nm = nm;
        this.cd = cd;
        this.cmnt = cmnt;
        this.ver = ver;
        this.src = src;
    }


    //    --------------------------


    public StandardOfDataEntity() {
    }

    public StandardOfDataEntity( String no, String en_nm, String en_snm, Object def, String rul, String acg, String cgy, String fmt, String unt, String def_dep, String mng_dep, String pd_dep, String eff_dt, String exp_dt, String rchk_dt, String mnt_tm, String mnr) {
        this.no = no;
        this.en_nm = en_nm;
        this.en_snm = en_snm;
        this.def = def;
        this.rul = rul;
        this.acg = acg;
        this.cgy = cgy;
        this.fmt = fmt;
        this.unt = unt;
        this.def_dep = def_dep;
        this.mng_dep = mng_dep;
        this.pd_dep = pd_dep;
        this.eff_dt = eff_dt;
        this.exp_dt = exp_dt;
        this.rchk_dt = rchk_dt;
        this.mnt_tm = mnt_tm;
        this.mnr = mnr;
    }

//    public StandardOfDataEntity(String nm, List<String> cd, List<String> cmnt, String ver, String src, String no, String en_nm, String en_snm, Object def, String rul, String acg, String cgy, String fmt, String unt, String def_dep, String mng_dep, String pd_dep, String eff_dt, String exp_dt, String rchk_dt, String mnt_tm, String mnr) {
//        super(nm, cd, cmnt, ver, src);
//        this.no = no;
//        this.en_nm = en_nm;
//        this.en_snm = en_snm;
//        this.def = def;
//        this.rul = rul;
//        this.acg = acg;
//        this.cgy = cgy;
//        this.fmt = fmt;
//        this.unt = unt;
//        this.def_dep = def_dep;
//        this.mng_dep = mng_dep;
//        this.pd_dep = pd_dep;
//        this.eff_dt = eff_dt;
//        this.exp_dt = exp_dt;
//        this.rchk_dt = rchk_dt;
//        this.mnt_tm = mnt_tm;
//        this.mnr = mnr;
//    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEn_Nm() {
        return en_nm;
    }

    public void setEn_Nm(String en_nm) {
        this.en_nm = en_nm;
    }

    public String getEn_SNm() {
        return en_snm;
    }

    public void setEn_SNm(String en_snm) {
        this.en_snm = en_snm;
    }

    public Object getDef() {
        return def;
    }

    public void setDef(Object def) {
        this.def = def;
    }

    public String getRul() {
        return rul;
    }

    public void setRul(String rul) {
        this.rul = rul;
    }

    public String getAcg() {
        return acg;
    }

    public void setAcg(String acg) {
        this.acg = acg;
    }

    public String getCgy() {
        return cgy;
    }

    public void setCgy(String cgy) {
        this.cgy = cgy;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getUnt() {
        return unt;
    }

    public void setUnt(String unt) {
        this.unt = unt;
    }

    public String getDef_Dep() {
        return def_dep;
    }

    public void setDef_Dep(String def_dep) {
        this.def_dep = def_dep;
    }

    public String getMng_Dep() {
        return mng_dep;
    }

    public void setMng_Dep(String mng_dep) {
        this.mng_dep = mng_dep;
    }

    public String getPd_Dep() {
        return pd_dep;
    }

    public void setPd_Dep(String pd_dep) {
        this.pd_dep = pd_dep;
    }

    public String getEff_Dt() {
        return eff_dt;
    }

    public void setEff_Dt(String eff_dt) {
        this.eff_dt = eff_dt;
    }

    public String getExp_Dt() {
        return exp_dt;
    }

    public void setExp_Dt(String exp_dt) {
        this.exp_dt = exp_dt;
    }

    public String getRchk_Dt() {
        return rchk_dt;
    }

    public void setRchk_Dt(String rchk_dt) {
        this.rchk_dt = rchk_dt;
    }

    public String getMnt_Tm() {
        return mnt_tm;
    }

    public void setMnt_Tm(String mnt_tm) {
        this.mnt_tm = mnt_tm;
    }

    public String getMnr() {
        return mnr;
    }

    public void setMnr(String mnr) {
        this.mnr = mnr;
    }

    @Override
    public String toString() {
        return "StandardOfDataEntity{" +
                ", no='" + no + '\'' +
                ", en_nm='" + en_nm + '\'' +
                ", en_snm='" + en_snm + '\'' +
                ", def='" + def + '\'' +
                ", rul='" + rul + '\'' +
                ", acg='" + acg + '\'' +
                ", cgy='" + cgy + '\'' +
                ", fmt='" + fmt + '\'' +
                ", unt='" + unt + '\'' +
                ", def_dep='" + def_dep + '\'' +
                ", mng_dep='" + mng_dep + '\'' +
                ", pd_dep='" + pd_dep + '\'' +
                ", eff_dt='" + eff_dt + '\'' +
                ", exp_dt='" + exp_dt + '\'' +
                ", rchk_dt='" + rchk_dt + '\'' +
                ", mnt_tm='" + mnt_tm + '\'' +
                ", mnr='" + mnr + '\'' +
                '}';
    }
}
