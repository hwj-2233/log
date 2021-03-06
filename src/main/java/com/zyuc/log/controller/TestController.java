package com.zyuc.log.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.servlet.ServletUtil;

import cn.hutool.http.HttpStatus;
import com.zyuc.log.annotation.MyLog;
import com.zyuc.log.entity.DepartmentDto;
import com.zyuc.log.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;


/**
 * @author hongwj
 * @date 2020/09/08
 **/
@RestController
@Api("controller")
@SuppressWarnings("all")
public class TestController {

    @Value("${server.port}")
    private String port;

    @Value("${com.waner.Directory}")
    private String Directory;

    @Value("${com.waner.captcha}")
    private String captcha;

    @Autowired
    private MessageService messageService;


    @ApiOperation(value = "下载application", notes = "application下载")
    @GetMapping("/download/{fileName}")
    @MyLog(value = "下载")
    public String test(@PathVariable("fileName") String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {
        ServletUtil.write(response, FileUtil.file(Directory + fileName));
        return fileName + "下载完成" + DateUtil.now();
    }

    @ApiOperation(value = "获取手机验证码", notes = "获取手机验证码")
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        com.wf.captcha.utils.CaptchaUtil.out(request, response);
    }


    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping("/doLogin")
    @MyLog(value = "用户登录")
    public String doLogin(String captchaForm) throws Exception {
        if (captcha.equals(captchaForm)) {
            return "success";
        } else {
            return "false";
        }
    }

    @PostMapping("add_department")
    @ApiOperation(value = "添加部门")
    public String addDepartment(@Valid DepartmentDto departmentDto) {
        try {
        }
        catch (Exception e){
            return "失败";
        }
        return "成功";
    }

}
