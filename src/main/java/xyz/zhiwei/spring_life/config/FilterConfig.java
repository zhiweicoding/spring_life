//package xyz.zhiwei.spring_life.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author zhiweicoding.xyz
// * @date 25/12/2024
// * @email diaozhiwei2k@gmail.com
// */
//@Configuration
//public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean<JakartaSentinelFilterAdapter> sentinelFilterRegistration() {
//        FilterRegistrationBean<JakartaSentinelFilterAdapter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new JakartaSentinelFilterAdapter());
//        registration.addUrlPatterns("/*");
//        registration.setName("jakartaSentinelFilter");
//        registration.setOrder(1);
//
//        return registration;
//    }
//}
