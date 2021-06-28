package com.sjzb.demo.model;

import com.sjzb.demo.model.Relationship.RelationShipBasicWordEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-13 14:12:57
 * @Description: C模型
 */
@Node(value = "C模型")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CModelEntity extends BaseNodeEntity {
//    @Id
//    @GeneratedValue
    @Property(name = "Nm")
    private String nm;

    @Property(name = "Def")
    private String def;


    /**
     * Neo4j 并没有真正的双向关系，我们只有在查询的时候忽略关系的方向
     * 可以参考下面这个链接对neo4j的关系作出正确的理解：
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "0:n", direction = Relationship.OUTGOING)
    private List<RelationShipBasicWordEntity> rela1;
    @Relationship(type = "1:n", direction = Relationship.OUTGOING)
    private List<RelationShipBasicWordEntity> rela2;

}
