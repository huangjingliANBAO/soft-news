package com.soft1851.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author crq
 */
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.admin.mapper")
@ComponentScan("com.soft1851")
@ComponentScan("org.n3r.idworker")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}