package com.sjzb.demo.Result;

import cn.hutool.core.convert.Convert;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-15 9:30:00
 * @Description: 一些其他自定义工具
 */
public class lxTool {
    /**
     *
     * @Author: chenlx
     * @Date: 2021-01-15 9:48:48
     * @Params: jsonString
     * @Return List<String></String>
     * @Description: 针对Neo4j驱动包提供的ListValue类型进行转换成普通的ArrayList
     */
    public static List<String> getListFromJson(String json){
        if(StringUtils.isNotEmpty(json)){
            String[] per = json.replace("[","").replace("]","").replace("\"","").split(",");

            String[] permissions = new String[per.length];
            for (int i =0; i< per.length; i++)
                if (StringUtils.isNotEmpty(per[i]) && per[i].length() > 2) {
//                    permissions[i] = per[i].substring(1, per[i].length() - 1);
                    permissions[i] = per[i];
                }
            return Convert.convert(List.class, permissions);
        }
        return null;
    }

    /**
     *
     * @Author: chenlx
     * @Date: 2021-01-15 11:29:25
     * @Params: null
     * @Return
     * @Description: 去除伪json的符号，转成普通String格式，虽然没什么必要
     */
    public static String getStringFromStringValue(String json){
            String per = json.replace("[","").replace("]","").replace("\"","");

            return Convert.convert(String.class, per);
    }
}
