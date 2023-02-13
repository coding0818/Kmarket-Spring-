package kr.co.kmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // --------------------- 외부 경로 파일 참조 ---------------------
    // ResourceHandler를 통해 기능 구현
    // 1) WebMvcConfigurer 구현
    // 2) addResourceHandler 구현

    // 만약 spring-security 사용 시, 추가 설정 필요

    //클라이언트로부터 http://localhost:8080/kmarket/admin/product/list ...
    // 와 같은 요청이 들어왔을 때,
    //resourcePath로 연결된다.

    private String connectPath = "/admin/product/list/**";
    private String resourcePath = "file:////Users/iilhwan/Desktop/Workspace/Kmarket-Spring-/SpringBoot/file/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
    registry.addResourceHandler(connectPath)            // 클라이언트가 파일에 접근하기 위해 요청하는 url
            .addResourceLocations(resourcePath);    // 실제 리소스가 존재하는 외부 경로
    }

}
