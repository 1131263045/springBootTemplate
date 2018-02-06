package com.linkinstars.springBootTemplate.controller;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.linkinstars.springBootTemplate.bean.UserEntity;
import com.linkinstars.springBootTemplate.service.IUserService;
import com.linkinstars.springBootTemplate.validator.NotNullStringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 控制层
 * @author LinkinStar
 */
@Controller
public class UserController {

    /** 用户服务 **/
    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    public String test(HttpServletRequest request, @RequestParam(required = false) Integer pageNum){
        //测试校验字符串参数
        String checkedString = "";
        Result validatorResult = FluentValidator.checkAll()
                .on(checkedString, new NotNullStringValidator("测试姓名"))
                .doValidate()
                .result(ResultCollectors.toSimple());
        if (!validatorResult.isSuccess()) {
            System.out.println(validatorResult.getErrors());
        }

        //分页参数校验
        int pageSize = 3;
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }

        //进行分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userList = userService.listUser();
        PageInfo pageInfo = new PageInfo(userList);

        //设置返回参数
        request.setAttribute("userList", userList);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pageSum", pageInfo.getPages());

        //测试打印日志
        /*LogUtil.printLog("测试打印info日志信息");
        Exception exception = new Exception("测试打印error日志");
        try {
            throw exception;
        } catch (Exception e) {
            LogUtil.printLog(e, this.getClass());
        }*/

        //测试session存放redis测试
        HttpSession session = request.getSession();
        System.out.println("session数据：" + session.getAttribute("xxx"));
        session.setAttribute("xxx","123");
        System.out.println("session数据：" + session.getAttribute("xxx"));
        return "index";
    }
}
