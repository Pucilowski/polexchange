package com.pucilowski.exchange.main.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by martin on 13/05/14.
 */

@Configuration
//@EnableAutoConfiguration
@EnableWebMvc
//@ComponentScan(basePackages = {        "com.pucilowski.exchange.web.api.controller",        "com.pucilowski.exchange.web.controller"})
@ComponentScan(basePackages = {"com.pucilowski.exchange.main.web"})
//@ComponentScan(basePackages = {"com.pucilowski.exchange.web", "com.pucilowski.exchange.web.api.controller", "com.pucilowski.exchange.web.controller"})
//@ComponentScan(basePackages = {        "com.pucilowski.exchange.web.api.controller",        "com.pucilowski.exchange.web.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}