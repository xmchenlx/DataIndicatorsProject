package com.sjzb.demo.Result;

import lombok.Data;

@Data
public class Result {

    private Boolean success;

    private Object data;

    private String status_text;

    public static Result create(){
        return create(null);
    }

    public static Result create(Object data){
        return create(true,data);
    }



    public static Result create(Boolean success, Object data){
        Result result = new Result();
        result.setData(data);
        result.setSuccess(success);
        result.setStatus_text("OK");
        return result;
    }

    public static Result create(Boolean success, Object data,String status){
        Result result = new Result();
        result.setData(data);
        result.setStatus_text(status==null?"OK":status);
        result.setSuccess(success);
        return result;
    }


}
