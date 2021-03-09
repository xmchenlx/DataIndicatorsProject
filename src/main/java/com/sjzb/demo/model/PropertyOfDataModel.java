package com.sjzb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-08 9:40:41
 * @Description: 数据模型-属性
 */
@Node(value = "数据模型-属性")
@Data
@ConstructorBinding
@AllArgsConstructor
public class PropertyOfDataModel extends BaseNodeEntity {
    @Property(name = "Nm")
    private String nm;

    @Property(name = "EN_Nm")
    private String en_nm;

    @Property(name = "Len")
    private Integer len;

    @Property(name="Tp")
    private String tp;
}
