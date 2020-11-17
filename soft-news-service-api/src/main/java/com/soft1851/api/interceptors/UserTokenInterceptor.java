package com.soft1851.api.interceptors;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTokenInterceptor extends BaseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response,
                             @NotNull Object handler)throws Exception{
     String userId = request.getHeader("headerUserId");
     String userToken = request.getHeader("headerUserToken");
     return verifyUserIdToken(userId,userToken,REDIS_USER_TOKEN);
    }
}
