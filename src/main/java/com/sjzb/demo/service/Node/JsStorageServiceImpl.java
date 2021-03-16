package com.sjzb.demo.service.Node;

import com.sjzb.demo.Repository.Node.JsStorageRepository;
import com.sjzb.demo.model.JsStorageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public JsStorageEntity selectDataByNm(String querykey) {
        JsStorageEntity t = jsRe.findByNm(querykey);
        JsStorageEntity a = t;

        return t;
    }
}
