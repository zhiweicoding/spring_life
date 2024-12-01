package xyz.zhiwei.spring_life;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class MyLifecycle implements SmartLifecycle {
    private boolean isRunning = false;

    @Override
    public void start() {
        System.out.println("SmartLifecycle 项目启动后执行的逻辑...");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("SmartLifecycle 项目停止时执行的逻辑...");
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}

