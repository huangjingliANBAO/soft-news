package com.soft1851.api;

import com.soft1851.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    //这里要声明为public，要不子类不能使用
    @Autowired
    public RedisOperator redis;
    public static final String MOBILE_SMSCODE = "mobile:smscode";
}
