package com.cse.haste;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cse.haste.repository")
public class HasteApplication {

    public static void main(String[] args) {
        SpringApplication.run(HasteApplication.class, args);
    }

}
