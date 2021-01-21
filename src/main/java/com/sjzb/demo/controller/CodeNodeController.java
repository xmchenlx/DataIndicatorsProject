package com.sjzb.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.sjzb.demo.CodeNodeRepository;
import com.sjzb.demo.PSRelationRepository;
import com.sjzb.demo.model.BaseNodeEntity;
import com.sjzb.demo.service.youdaoTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.sjzb.demo.Result.lxTool.getListFromJson;

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

    youdaoTool ydtool = new youdaoTool();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @GetMapping("/*")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long _begin = System.currentTimeMillis();

        String queryKey = request.getParameter("q");
        System.out.println("查询字段：" + queryKey);
//        String translate = translation(queryKey);
        Object tempNodeList = cnRe.findCodeNodeEntityByNmLike(queryKey);
        List<BaseNodeEntity> t = Convert.convert(new TypeReference<List<BaseNodeEntity>>() {
        }, tempNodeList);
        String translate;
        if (t.size() == 0) {
            System.out.println("找不到信息：" + queryKey);
            translate = ydtool.getUnkown(queryKey);
        } else {
            //获取节点的标签
            String tNm = t.iterator().next().getNm();
            String tempCneTag = cnRe.findTagByNm(tNm).toString();
            List<String> nodeTagList = getListFromJson(tempCneTag);
            translate = ydtool.translation(queryKey, t,nodeTagList);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(translate);

        long _end = System.currentTimeMillis();
        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
//
    }


//    /**
//     * @Author: chenlx
//     * @Date: 2021-01-15 10:34:16
//     * @Params: null
//     * @Return
//     * @Description: 通过节点的名称进行模糊查询，返回所有可能匹配的结果
//     */
//
//    public String selectCodeNodeByNmLike(String queryKey) {
////        boolean isfind = true;
////        Object res = cnRe.findCodeNodeEntityByNmLike(queryKey);
////        if (res.equals(null)) isfind = false;
//
//        return translation(queryKey);
//
////        return getUnkown(queryKey);
//    }
//
//
//    /**
//     * 通过查询key翻译字段
//     *
//     * @param queryKey 查询key
//     * @return
//     */
//    public String translation(String queryKey) {
//        if (queryKey == null || "".equals(queryKey.trim())) {
//            System.out.println("查询字段为空");
//            return getUnkown(queryKey);
//        }
//
////        //1.查询所有配置
////        List<CodeNodeEntity> list = cnRe.findCodeNodeEntityByNmLike(queryKey);
////        if (list == null) {
////            System.out.println("参数表未配置");
////            return getUnkown(queryKey);
////        }
//
//        //2.根据配置查表
//        StringBuffer customTranslationSb = new StringBuffer("<custom-translation>");
////        list.forEach(tbSysConfig -> {
//        String res = "";
//        List<CodeNodeEntity> cneList = cnRe.findCodeNodeEntityByNmLike(queryKey);
//
//
//        if (cneList.size() == 0) {
//            System.out.println("查询不到数据");
//            return getUnkown(queryKey);
//        }
//
//        for (int i = 0; i < cneList.size(); i++) {
//            //LIKE模糊查询的结点个数遍历
//            CodeNodeEntity tempb = cneList.iterator().next();
//            String tempCneTag = cnRe.findTagByNm(tempb.getNm()).toString();
////            遍历节点的标签
//            List<String> cneTag = getListFromJson(tempCneTag);
//            res = "来源：";
//            for (int x = 0; x < cneTag.size(); x++) {
//                if (x != 0) res += "、";
//                res += "《" + cneTag.get(x).replace("Optional", "") + "》";
//            }
//            res += "<br/>";
//            res += "类别：" + cneList.get(0).getSrc() + "<br/>版本：" + cneList.get(0).getVer();
//            res += "<br/><p style='color:gray;text-align:center'>代码如下</p><hr/>";
//
//            int jmax = tempb.getCd().size();
//            res += "<pre>";
//            for (int j = 0; j < jmax; j++) {
//                //当前结点的属性遍历
//                res += "<strong>" + tempb.getCd().get(j) + "</strong>\t" + tempb.getCmnt().get(j);
//                if (j != jmax) res += "<br />";
//
//            }
//            res += "</pre>";
//        }
//        customTranslationSb.append(getTranslation("", res));
//        customTranslationSb.append("</custom-translation>");
//
//        //3.组装返回xml
//        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
//        youdaodictSb.append("<return-phrase><![CDATA[").append(cneList.get(0).getNm()).append("]]></return-phrase>")
//                .append(customTranslationSb).append("</yodaodict>");
//        return youdaodictSb.toString();
//
//    }
//
//    private String getUnkown(String queryKey) {
//        String res = "<p>查询不到有关<strong style='color:red'>" + queryKey + "</strong>的信息，调整一下划词范围试试吧！</p>";
//        StringBuffer youdaodictSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"GB2312\"?><yodaodict>");
//        youdaodictSb.append("<return-phrase><![CDATA[")
//                .append(queryKey)
//                .append("]]></return-phrase>")
//                .append("<custom-translation>")
//                .append("<translation><content><![CDATA[")
//                .append(res)
//                .append("]]></content></translation>")
//                .append("</custom-translation>")
//                .append("</yodaodict>");
//        return youdaodictSb.toString();
//    }
//
//
//    /**
//     * 组装翻译
//     *
//     * @param tbNameCn
//     * @param content
//     * @return
//     */
//    private String getTranslation(String tbNameCn, String content) {
//        //拼接翻译内容content
//        StringBuffer translation = new StringBuffer();
//        translation.append("<translation><content><![CDATA[").append(tbNameCn).append(content).append("]]></content></translation>");
//        return translation.toString();
//    }
//
////    /**
////     * @Author: chenlx
////     * @Date: 2021-01-15 15:57:34
////     * @Params: null
////     * @Return
////     * @Description: 添加一个结点
////     */
////    @PostMapping("addOneCodeNode")
////    public Result createOneCodeNode(@RequestBody CodeNodeEntity cne) {
////        long _begin = System.currentTimeMillis();
////        cnRe.save(cne);
////
////        long _end = System.currentTimeMillis();
////        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
////
////        return Result.create(true);
////    }
////
////    /**
////     * @Author: chenlx
////     * @Date: 2021-01-15 10:34:53
////     * @Params: null
////     * @Return
////     * @Description: 通过具体的代码节点名称与代码cd，查询该代码的详细信息
////     */
////    @GetMapping("getOnePropertyOfCodeNodeBynm_cd")
////    public Result getOneColumnPropertyOfCodeNodeByCd(String nm, String cd) {
////        long _begin = System.currentTimeMillis();
////        boolean isFind = false;
////        int index = -1;
////        Collection<CodeNodeEntity> ccn = cnRe.findCodeNodeEntityByNm(nm);
////        if (ccn.equals(null)) {
////            isFind = false;
////            if (!isFind) {
////                long _end = System.currentTimeMillis();
////
////                System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
////
////                return Result.create(true, "NO_NODE_OR_PROPERTY", null);
////            }
////        }
////
////        CodeNodeEntity b = ccn.iterator().next();
////
////        for (Object c : b.getCd()) {
////            index++;
////            if (cd.equals(c)) {
////                isFind = true;
////                break;
////            }
////        }
////
////        if (!isFind) {
////            long _end = System.currentTimeMillis();
////            System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
////            return Result.create(true, "NO_DATA", null);
////        }
////
////        Map<String, Object> res = new HashMap<>();
////        res.put("_ColumnIndex", index);
////        res.put("Cd", b.getCd().get(index));
////        res.put("Nm", b.getNm());
////        res.put("Cmnt", b.getCmnt().get(index));
////        if ("".equals(getStringFromStringValue(b.getDef().toString()))) {
////            res.put("Def", "/");
////        } else {
////            List<String> tempDef = getListFromJson(b.getDef().toString());
////            res.put("Def", tempDef.get(index));
////        }
////        res.put("Src", b.getSrc());
////        res.put("Ver", b.getVer());
////        res.put("Orig", b.getOrig() == null ? null : b.getOrig().get(index));
////        res.put("Lvl", b.getLvl() == null ? b.getLvl() : b.getLvl().get(index));
////        res.put("Cty_En_Nm", b.getCty_en_nm() == null ? null : b.getCty_en_nm().get(index));
////        res.put("Ltr_Cd", b.getLtr_cd() == null ? null : b.getLtr_cd().get(index));
////        res.put("En_2Snm", b.getEn_2snm() == null ? null : b.getEn_2snm().get(index));
////        res.put("Digt_Cd", b.getDigt_cd() == null ? null : b.getDigt_cd().get(index));
////        res.put("Cty_Nm", b.getCty_nm() == null ? null : b.getCty_nm().get(index));
////        long _end = System.currentTimeMillis();
////        System.out.println("当前程序：" + Thread.currentThread().getStackTrace()[1].getMethodName() + " 耗时" + (((float) (_end - _begin) / 1000)) + "秒.");
////        return Result.create(isFind, res);
////    }
////
////
////
////    @GetMapping("re/getBloodRelation")
////    public Result getBloodRelationOfCodeNode(String Nm){
////        return Result.create(true,psRe.findAllByStartNode(Nm));
////    }
////


}
