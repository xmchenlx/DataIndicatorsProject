package com.sjzb.demo.service;

import com.sjzb.demo.Repository.Node.dataSourceRepository;
import com.sjzb.demo.model.DataSourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-01-20 15:02:45
 * @Description:
 */
@Service
public class dataSourceServiceImpl {

    @Autowired
    private dataSourceRepository dataSourceRe;

    /**
     * @Author: chenlx
     * @Date: 2021-02-02 10:42:14
     * @Params: nm
     * @Return List<DataSourceEntity>
     * @Description: 根据数据元的名称返回数据元的格式
     */
    public List<DataSourceEntity> getDataSource(String nm) {
        List<DataSourceEntity> ls = dataSourceRe.findDataSourceEntityByNm(nm);
        if(ls.size() == 0){
            ls.add(new DataSourceEntity());
        }
        return ls;
    }

}
