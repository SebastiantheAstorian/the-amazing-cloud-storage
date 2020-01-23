package com.cc.cloudstoragemanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class ClientViewerConfiguration implements WebMvcConfigurer {

    private static final String ASSETS_SUB_FOLDER = "assets";

    private final String prefix;

    public ClientViewerConfiguration(@Value("${spring.thymeleaf.prefix}") String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.setOrder(1);
        registry.addResourceHandler("/*.js", "/*.js.map").addResourceLocations(prefix);
        registry.addResourceHandler("/*.css", "/*.css.map").addResourceLocations(prefix);
        registry.addResourceHandler("/*.eot", "/*.svg", "/*.woff2", "/*.woff", "/*.ttf", "/*.ico")
                .addResourceLocations(prefix);
        registry.addResourceHandler("/" + ASSETS_SUB_FOLDER + "/**")
                .addResourceLocations(prefix + ASSETS_SUB_FOLDER + "/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(2);
        // To avoid conflicts with default boot resource handler, static resources were moved to extra URL (spring.mvc.static-path-pattern)
        registry.addViewController("/**").setViewName("index");
    }

}