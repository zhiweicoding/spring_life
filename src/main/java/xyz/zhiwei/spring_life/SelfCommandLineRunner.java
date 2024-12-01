package xyz.zhiwei.spring_life;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class SelfCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner 执行");
    }
}
