package com.soft1851.user.controller;

import com.soft1851.api.BaseController;
import com.soft1851.api.user.PassportControllerApi;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.SmsUtil;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PassportController extends BaseController implements PassportControllerApi {
    @Resource
    private SmsUtil smsUtil;
    @Override
    public GraceResult getCode(String mobile, HttpServletRequest request) {
        //获取用户id
        String userIp = IpUtil.getRequestIp(request);
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp,userIp);
        //生成随机验证码且发送短信
        String random = (int)((Math.random() *9 +1)*10000) +"";
        System.out.println(random);
        smsUtil.sendSms(mobile,random);
        redis.set(MOBILE_SMSCODE + ":" + mobile,random,30*60);
        return GraceResult.ok();
    }
}
