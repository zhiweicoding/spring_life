package xyz.zhiwei.spring_life;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 所有属性设置完毕后执行...");
    }
}
