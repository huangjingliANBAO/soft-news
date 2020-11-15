package com.soft1851.utils;

import ch.qos.logback.core.net.server.Client;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.soft1851.utils.extend.AliyunResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SmsUtil {
    @Resource
    public AliyunResource aliyunResource;
    public void sendSms(String mobile,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing",
                aliyunResource.getAccessKeyId(),
                aliyunResource.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId","cn-beijing");
        request.putQueryParameter("PhoneNumbers",mobile);
        request.putQueryParameter("SignName","后台管理系统");
        request.putQueryParameter("TemplateCode","SMS_183765274");
        request.putQueryParameter("TemplateParam","{\"code\":\"" + code + "\"}");
        try{
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch (ClientException e){
            System.err.println(e.getMessage());
        }
    }
}
