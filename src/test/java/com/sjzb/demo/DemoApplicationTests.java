package com.sjzb.demo;

import com.sjzb.demo.model.CodeNodeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    CodeNodeRepository cnRe;
    @Test
    void contextLoads() {
    }
    @Test
    public  void  testFind(){
        Collection<CodeNodeEntity> cn = cnRe.findCodeNodeEntityByNmLike("代码");
//        Collection<CodeNodeEntity> cn = cnRe.findCodeNodeEntityByNmLike("信用证");
        System.out.println(cn.iterator().next());
//        System.out.println(cn.toString());
    }

    @Test
    public void testSave(){
    }

}
