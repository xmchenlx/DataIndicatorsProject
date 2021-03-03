package com.sjzb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-02-24 15:37:35
 * @Description: 词典查询排序自定义的存储数据的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderEntity {
    private List<Integer> order;
    private List<Integer> select;
    private Map<Integer, String> data_ch;
    private String jsessionid;

    @Override
    public String toString() {
        String datach = "";
        for (int i = 0; i < data_ch.size(); i++) {
            datach += "\""+ i + "\":\"" + data_ch.get(i) + "\"";
            if (i != data_ch.size()-1) datach += ",";
        }
        return "{" +
                "\"order\":" + order +
                ", \"select\":" + select +
                ",\"data_ch\":{" + datach + "}" +
                '}';
    }
}
