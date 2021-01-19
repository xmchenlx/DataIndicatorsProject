package com.sjzb.demo.controller;

import com.sjzb.demo.CodeNodeRepository;
import com.sjzb.demo.PSRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProgramName: demo
 * @Author: xmche
 * @Date: 2021-01-08 9:35:09
 * @Description:
 */
@RestController
public class CodeNodeController {

    @Autowired
    CodeNodeRepository cnRe;

    @Autowired
    PSRelationRepository psRe;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @GetMapping("/*")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryKey = request.getParameter("q");
        System.out.println("查询字段："+queryKey);
        String translate = translation(queryKey);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(translate);
    }

    /**
     * @Author: chenlx
     * @Date: 2021-01-15 10:34:16
     * @Params: null
     * @Return
     * @Description: 通过节点的名称进行模糊查询，返回所有可能匹配的结果
     */

    public String selectCodeNodeByNmLike(String queryKey) {
//        boolean isfind = true;
//        Object res = cnRe.findCodeNodeEntityByNmLike(queryKey);
//        if (res.equals(null)) isfind = false;

        return translation(queryKey);

//        return getUnkown(queryKey);
    }

    /**
     * 通过查询key翻译字段
     *
     * @param queryKey 查询key
     * @return
     */
    public String translation(String queryKey) {
        if (queryKey == null || "".equals(queryKey.trim())) {
            System.out.println("查询字段为空");
            return getUnkown(queryKey);
        }

//        //1.查询所有配置
//        List<CodeNodeEntity> list = cnRe.findCodeNodeEntityByNmLike(queryKey);
//        if (list == null) {
//            System.out.println("参数表未配置");
//            return getUnkown(queryKey);
//        }

        //2.根据配置查表
        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
//        list.forEach(tbSysConfig -> {
            String tbNameCn = "代码表";
            Object content = cnRe.findCodeNodeEntityByNmLike(queryKey);
            System.out.println(content);
            if (!content.equals("")) {
                customTranslationSb.append(getTranslation(tbNameCn, content.toString()));
            }
//        });
        customTranslationSb.append("</custom-translation>");

        //3.组装返回xml
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[").append(queryKey).append("]]></return-phrase>")
                .append(customTranslationSb).append("</yodaodict>");
        return youdaodictSb.toString();

    }

    private String getUnkown(String queryKey) {
        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
        youdaodictSb.append("<return-phrase><![CDATA[")
                .append(queryKey)
                .append("]]></return-phrase>")
                .append("<custom-translation>")
                .append("<translation><content><![CDATA[")
                .append(queryKey)
                .append("]]></content></translation>")
                .append("</custom-translation>")
                .append("</yodaodict>");
        return youdaodictSb.toString();
    }


    /**
     * 组装翻译
     *
     * @param tbNameCn
     * @param content
     * @return
     */
    private String getTranslation(String tbNameCn, String content) {
        //拼接翻译内容content
        StringBuffer translation = new StringBuffer();
        translation.append("<translation><content><![CDATA[《").append(tbNameCn).append("》").append(content).append("]]></content></translation>");
        return translation.toString();
    }

//    /**
//     * @Author: chenlx
//     * @Date: 2021-01-15 15:57:34
//     * @Params: null
//     * @Return
//     * @Description: 添加一个结点
//     */
//    @PostMapping("addOneCodeNode")
//    public Result createOneCodeNode(@RequestBody CodeNodeEntity cne) {
//        long _begin = System.currentTimeMillis();
//        cnRe.save(cne);
//
//        long _end = System.currentTimeMillis();
//        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//
//        return Result.create(true);
//    }
//
//    /**
//     * @Author: chenlx
//     * @Date: 2021-01-15 10:34:53
//     * @Params: null
//     * @Return
//     * @Description: 通过具体的代码节点名称与代码cd，查询该代码的详细信息
//     */
//    @GetMapping("getOnePropertyOfCodeNodeBynm_cd")
//    public Result getOneColumnPropertyOfCodeNodeByCd(String nm, String cd) {
//        long _begin = System.currentTimeMillis();
//        boolean isFind = false;
//        int index = -1;
//        Collection<CodeNodeEntity> ccn = cnRe.findCodeNodeEntityByNm(nm);
//        if (ccn.equals(null)) {
//            isFind = false;
//            if (!isFind) {
//                long _end = System.currentTimeMillis();
//
//                System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//
//                return Result.create(true, "NO_NODE_OR_PROPERTY", null);
//            }
//        }
//
//        CodeNodeEntity b = ccn.iterator().next();
//
//        for (Object c : b.getCd()) {
//            index++;
//            if (cd.equals(c)) {
//                isFind = true;
//                break;
//            }
//        }
//
//        if (!isFind) {
//            long _end = System.currentTimeMillis();
//            System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//            return Result.create(true, "NO_DATA", null);
//        }
//
//        Map<String, Object> res = new HashMap<>();
//        res.put("_ColumnIndex", index);
//        res.put("Cd", b.getCd().get(index));
//        res.put("Nm", b.getNm());
//        res.put("Cmnt", b.getCmnt().get(index));
//        if ("".equals(getStringFromStringValue(b.getDef().toString()))) {
//            res.put("Def", "/");
//        } else {
//            List<String> tempDef = getListFromJson(b.getDef().toString());
//            res.put("Def", tempDef.get(index));
//        }
//        res.put("Src", b.getSrc());
//        res.put("Ver", b.getVer());
//        res.put("Orig", b.getOrig() == null ? null : b.getOrig().get(index));
//        res.put("Lvl", b.getLvl() == null ? b.getLvl() : b.getLvl().get(index));
//        res.put("Cty_En_Nm", b.getCty_en_nm() == null ? null : b.getCty_en_nm().get(index));
//        res.put("Ltr_Cd", b.getLtr_cd() == null ? null : b.getLtr_cd().get(index));
//        res.put("En_2Snm", b.getEn_2snm() == null ? null : b.getEn_2snm().get(index));
//        res.put("Digt_Cd", b.getDigt_cd() == null ? null : b.getDigt_cd().get(index));
//        res.put("Cty_Nm", b.getCty_nm() == null ? null : b.getCty_nm().get(index));
//        long _end = System.currentTimeMillis();
//        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//        return Result.create(isFind, res);
//    }
//
//
//
//    @GetMapping("re/getBloodRelation")
//    public Result getBloodRelationOfCodeNode(String Nm){
//        return Result.create(true,psRe.findAllByStartNode(Nm));
//    }
//



}
