package com.sjzb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-08 9:39:57
 * @Description: 数据模型-实体
 */
@Node(value = "数据模型-实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConstructorBinding
public class EntityOfDataModelEntity extends BaseNodeEntity{
    @Property(name = "Nm")
    private String nm;



}
