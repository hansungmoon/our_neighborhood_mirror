package ywphsm.ourneighbor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ywphsm.ourneighbor.interceptor.LogInterceptor;
import ywphsm.ourneighbor.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/sign_in", "/sign_up", "/logout",
                        "/css/**", "/*ico", "/error", "/sign_up/certified",
                        "/sign_up/certified_phone"
                );
    }


}
