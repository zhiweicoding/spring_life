package xyz.zhiwei.spring_life.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhiweicoding.xyz
 * @date 28/12/2024
 * @email diaozhiwei2k@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/s")
public class ShortController {

    @GetMapping("/{shortKey}")
    public void redirectUrl(@PathVariable("shortKey") String shortKey,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        log.info("shortKey: {}", shortKey);
        // 1. 根据短链 key 查询长链

        //假设去获取来的
        String longUrl = "https://zhiwei.plus/archives/1733061010367";
        if (longUrl == null) {
            // 可返回一个 404 页面
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "短链不存在");
            return;
        }

        // 2. 统计监控数据 (访问者ip, 浏览器信息, referrer 等)
//        urlMonitorService.collect(shortKey, request);

        // 3. 做 302 临时重定向
        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.setHeader("Location", longUrl);
    }
}
