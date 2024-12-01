package xyz.zhiwei.spring_life;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class ReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("ApplicationReadyEvent 应用程序启动完毕，准备处理请求...");
    }
}

