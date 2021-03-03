package com.sjzb.demo.model;


/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-03 15:16:09
 * @Description:
 */
public enum TypeEnum {
    BaseNode("基础节点", 999),
    IndicatorsNode("指标", 0),

    BasicAndClassWord("基本词类词", 1),


    Code("代码、基础数据标准", 2),

    Report("报表/报文", 3),

    DataModelOfIBMNode("IBM数据模型分类", 4),

    DataSource("数据元", 998);


    private String name;
    private Integer index;

    TypeEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // 覆盖方法
    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

    public int getIndexByChName(Integer index) {
        Integer res = -1;
        int i=0;
        for (TypeEnum c : TypeEnum.values()) {
            Integer a = c.index;
            if (c.index.equals(index)) {
                res = c.index;
                break;
            }
            i++;
        }
        return res;
    }
}