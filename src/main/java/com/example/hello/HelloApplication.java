package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
 * @SpringBootApplication = 스프링부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정.
 * 이 어노테이션이 붙은 위치부터 설정을 읽어가므로 이 클래스는 항상 프로젝트의 최상단에 위치해야함
 * */
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
