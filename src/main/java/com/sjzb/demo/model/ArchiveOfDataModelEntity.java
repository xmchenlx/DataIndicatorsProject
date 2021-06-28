package com.sjzb.demo.model;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.neo4j.core.schema.Node;

/**
 *
 * @Author: chenlx
 * @Date: 2021-03-30 16:46:57
 * @Params: null
 * @Return
 * @Description: 数据模型-归档
 */
@Node(value = "数据模型-归档")
@ConstructorBinding
public class ArchiveOfDataModelEntity extends PropertyOfDataModel{

    public ArchiveOfDataModelEntity(String nm, String en_nm, Integer len, String tp) {
        super(nm, en_nm, len, tp);
    }
}
