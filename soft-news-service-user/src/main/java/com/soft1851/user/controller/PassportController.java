package com.soft1851.user.controller;

import com.soft1851.api.user.PassportControllerApi;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.SmsUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PassportController implements PassportControllerApi {
    @Resource
    private SmsUtil smsUtil;
    @Override
    public GraceResult getCode(String mobile, HttpServletRequest request) {
        //生成随机验证码且发送短信
        String random = (int)((Math.random() *9 +1)*10000) +"";
        System.out.println(random);
        smsUtil.sendSms(mobile,random);
        return GraceResult.ok();
    }
}
