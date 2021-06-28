package com.sjzb.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sjzb.demo.Result.ResultPage;
import com.sjzb.demo.config.SystemSetting;
import com.sjzb.demo.service.Node.*;
import com.sjzb.demo.service.youdaoTool;
import com.sjzb.demo.util.lxTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pro")
public class proController {
    @Autowired
    youdaoTool ydtool;

    @Autowired
    CodeNodeServiceImpl cnService;
    @Autowired
    BasicClassNodeServiceImpl basicClassService;
    @Autowired
    StandardDataNodeServiceImpl standardDataService;

    @Autowired
    ReportNodeServiceImpl reportService;
    @Autowired
    DataModelOfIBMNodeServiceImpl ibmModelService;
    @Autowired
    IndicatorsNodeServiceImpl indicatorService;
    @Autowired
    PropertyOfDataModelServiceImpl pdmService;
    lxTool lxtool = new lxTool();
    SystemSetting sysTool = new SystemSetting();

    @GetMapping("/pro")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        MySessionContext myc = MySessionContext.getInstance();
//        if (session.isNew()) {
//            myc.addSession(session);
//        }
//        Object a = session.getAttribute("sjzb_order");
//        UserOrderEntity uoe = (UserOrderEntity) session.getAttribute("sjzb_order");
//        if (uoe == null) {
//            uoe = generateDefaultSessionInfo(session);
//            session.setAttribute("sjzb_order", uoe);
//            myc.addSession(session);
//
//        }
        String uri = request.getRequestURI().replace("/pro","");
        if (uri.equals("/PDM")) {
            Integer pageNum = Integer.parseInt(request.getParameter("p"));
            response.getWriter().print(getCmodelList( pageNum));
        }


    }


    @GetMapping("/PDM/getList")
    public ResultPage getCmodelList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Integer pageSize = 5;
        Page p = PageHelper.startPage(pageNum, pageSize);
//        List<CModelEntity> cModelList = cmodelService.getCModelList();

        return ResultPage.create(true,
                pdmService.getPDMList(pageNum*pageSize,pageSize),
                pageNum.longValue(),
                pageSize.longValue(),
                pdmService.getPDMCount().longValue());
    }


}
