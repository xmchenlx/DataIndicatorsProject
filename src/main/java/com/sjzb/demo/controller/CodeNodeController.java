package com.sjzb.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.json.JSONUtil;
import com.sjzb.demo.CodeNodeRepository;
import com.sjzb.demo.Result.Result;
import com.sjzb.demo.model.CodeNodeEntity;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.internal.value.ListValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.sjzb.demo.Result.lxTool.getListFromJson;
import static com.sjzb.demo.Result.lxTool.getStringFromStringValue;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:35:09
 * @Description:
 */
@RestController
@RequestMapping("/getCodeNode/")
public class CodeNodeController {

    @Autowired
    CodeNodeRepository cnRe;


    /**
     * @Author: chenlx
     * @Date: 2021-01-15 10:34:16
     * @Params: null
     * @Return
     * @Description: 通过节点的名称进行模糊查询，返回所有可能匹配的结果
     */
    @GetMapping("getByNmLike")
    public Result selectCodeNodeByNmLike(String nm) {
        boolean isfind = true;
        Object res = cnRe.findCodeNodeEntityByNmLike(nm);
        if (res.equals(null)) isfind = false;
        return Result.create(isfind, res);
    }

    public Result selectBloodRelationShipByNmOfCodeNode(String nm){

        return Result.create(false);
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-15 15:57:34
     * @Params: null
     * @Return
     * @Description: 添加一个结点
     */
    @PostMapping("addOneCodeNode")
    public Result createOneCodeNode(@RequestBody CodeNodeEntity cne) {
        long _begin = System.currentTimeMillis();
        cnRe.save(cne);

        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");

        return Result.create(true);
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-15 10:34:53
     * @Params: null
     * @Return
     * @Description: 通过具体的代码节点名称与代码cd，查询该代码的详细信息
     */
    @GetMapping("getOnePropertyOfCodeNodeBynm_cd")
    public Result getOneColumnPropertyOfCodeNodeByCd(String nm, String cd) {
        long _begin = System.currentTimeMillis();
        boolean isFind = false;
        int index = -1;
        Collection<CodeNodeEntity> ccn = cnRe.findCodeNodeEntityByNm(nm);
        if (ccn.equals(null)) {
            isFind = false;
            if (!isFind) {
                long _end = System.currentTimeMillis();

                System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");

                return Result.create(true, "NO_NODE_OR_PROPERTY", null);
            }
        }

        CodeNodeEntity b = ccn.iterator().next();

        for (Object c : b.getCd()) {
            index++;
            if (cd.equals(c)) {
                isFind = true;
                break;
            }
        }

        if (!isFind) {
            long _end = System.currentTimeMillis();
            System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
            return Result.create(true, "NO_DATA", null);
        }

        Map<String, Object> res = new HashMap<>();
        res.put("_ColumnIndex", index);
        res.put("Cd", b.getCd().get(index));
        res.put("Nm", b.getNm());
        res.put("Cmnt", b.getCmnt().get(index));
        if ("".equals(getStringFromStringValue(b.getDef().toString()))) {
            res.put("Def", "/");
        } else {
            List<String> tempDef = getListFromJson(b.getDef().toString());
            res.put("Def", tempDef.get(index));
        }
        res.put("Src", b.getSrc());
        res.put("Ver", b.getVer());
        res.put("Orig", b.getOrig() == null ? null : b.getOrig().get(index));
        res.put("Lvl", b.getLvl() == null ? b.getLvl() : b.getLvl().get(index));
        res.put("Cty_En_Nm", b.getCty_en_nm() == null ? null : b.getCty_en_nm().get(index));
        res.put("Ltr_Cd", b.getLtr_cd() == null ? null : b.getLtr_cd().get(index));
        res.put("En_2Snm", b.getEn_2snm() == null ? null : b.getEn_2snm().get(index));
        res.put("Digt_Cd", b.getDigt_cd() == null ? null : b.getDigt_cd().get(index));
        res.put("Cty_Nm", b.getCty_nm() == null ? null : b.getCty_nm().get(index));
        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
        return Result.create(isFind, res);
    }


}
