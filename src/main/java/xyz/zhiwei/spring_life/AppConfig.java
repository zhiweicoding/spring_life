package xyz.zhiwei.spring_life;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        System.out.println("创建 MyService Bean");
        return new MyService();
    }
}
