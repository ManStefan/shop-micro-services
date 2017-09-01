package com.man.shop.config;

import com.man.shop.utils.PlatformConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by smanolache on 6/5/2017.
 */
@Configuration
public class AdminAppConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = PlatformConstants.Environments.Constants.DEV_VALUE, matchIfMissing = false)
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("http://localhost:8080")
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS");
//                registry.addMapping("/**").allowedOrigins("http://localhost:8080")
//                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS");
//                registry.addMapping("/*").allowedOrigins("http://localhost:8080")
//                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS");
//                registry.addMapping("/").allowedOrigins("http://localhost:8080")
//                        .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS");
            }
        };
    }

    @Bean
    public RestTemplateConfig getRestTemplateConfig(){
        return new RestTemplateConfig();
    }
}
