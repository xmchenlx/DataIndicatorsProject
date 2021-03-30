package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.JsStorageRepository;
import com.sjzb.demo.model.JsStorageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 *
 * @Author: chenlx
 * @Date: 2021-03-09 16:17:36
 * @Params: null
 * @Return
 * @Description: JS库包服务层
 */
@Service
public class JsStorageServiceImpl {
    @Autowired
    private JsStorageRepository jsRe;

    public String selectCdByNm(String querykey){
        String res = "";

        Object t = jsRe.findByNm(querykey);
        JsStorageEntity a = (JsStorageEntity)t;
        res = a.getCd();
        return res;
    }

    /**
     * 
     * @Author: chenlx
     * @Date: 2021-03-30 14:37:23
     * @Params: null
     * @Return 
     * @Description: 读取JS插件（图数据库读取的JS有问题，先不使用）
     */
    public JsStorageEntity selectDataByNm(String querykey) {
        JsStorageEntity t = jsRe.findByNm(querykey);
        JsStorageEntity a = t;

        return t;
    }

    /**
     *
     * @Author: chenlx
     * @Date: 2021-03-30 14:37:53
     * @Params: null
     * @Return
     * @Description: 读取JS插件包文本（图数据库读取的JS有问题，暂时先从Tomcat这里获取）
     */
    public StringBuilder getJsPackageByName(String name) throws IOException {
        if(name.equals("handlebars")){
            return readFile("handlebars.js");
        }
        if(name.equals("jquery")){
            return readFile("jquery.js");
        }
        if(name.equals("echarts")){
            return readFile("echarts.js");
        }
        return new StringBuilder();
    }

    /**
     *
     * @Author: chenlx
     * @Date: 2021-03-30 14:38:34
     * @Params: null
     * @Return
     * @Description: 从Resources/JsPackage这里读取文件
     */
    public StringBuilder readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        ClassPathResource classPathResource = new ClassPathResource("JsPackage/"+filename);
        System.out.println("读取文件："+classPathResource.getPath());
        BufferedReader reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));
        content.append(reader.lines().collect(Collectors.joining("\n")));
        reader.close();
        return content;
    }

}
