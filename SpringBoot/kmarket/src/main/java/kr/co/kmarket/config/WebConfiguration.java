package kr.co.kmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private String connectPath = "/image/**";
    private String resourePath = "file:///http://43.200.2.115/home/Kmarket/thumb";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourePath);
    }
}
