//package xyz.zhiwei.spring_life.config;
//
//import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
//import jakarta.servlet.*;
//
//import java.io.IOException;
//
///**
// * @author zhiweicoding.xyz
// * @date 25/12/2024
// * @email diaozhiwei2k@gmail.com
// */
//public class JakartaSentinelFilterAdapter implements Filter {
//
//    private final CommonFilter sentinelFilter = new CommonFilter();
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        // 将 jakarta.servlet 的请求与响应对象转换为 javax.servlet 的实现
//        try {
//            sentinelFilter.doFilter(
//                    (javax.servlet.ServletRequest) request,
//                    (javax.servlet.ServletResponse) response,
//                    (javax.servlet.FilterChain) chain
//            );
//        } catch (javax.servlet.ServletException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
