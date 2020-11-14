package com.soft1851.user.controller;

import com.soft1851.result.GraceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import user.HelloControllerApi;

@RestController
@Slf4j
public class HelloController implements HelloControllerApi {
    @Override
    public Object hello(){
        log.info("a");
        log.debug("b");
        return GraceResult.ok("hello");
    }
}
