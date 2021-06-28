package com.sjzb.demo.Result;

import lombok.Data;

/**
 * @ProgramName: houserent
 * @Auther: xmche
 * @Date: 2019-10-20 15:01:45
 * @Description: 用于创造多条数据需要分页时返回的类
 */
@Data
public class ResultPage {
    private Boolean success;

    private Object data;

    private Long total;

    private Long pageNum;

    private Long pageSize;

    public static ResultPage create(Object data,Long pageNum,Long pageSize,Long total){
        return create(true,data,pageNum,pageSize,total);
    }


    public static ResultPage create(Boolean success,Object data,Long pageNum,Long pageSize,Long total){
        ResultPage result = new ResultPage();
        result.setData(data);
        result.setSuccess(success);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotal(total);
        return result;
    }
}
