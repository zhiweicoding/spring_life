package xyz.zhiwei.spring_life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringLifeApplication {

    public static void main(String[] args) {
        System.out.println("开始执行spring boot");
        ConfigurableApplicationContext run = SpringApplication.run(SpringLifeApplication.class, args);
        BeanLifeComponent bean = run.getBean(BeanLifeComponent.class);
        bean.use();
//        System.out.println("开始关闭spring boot");
//        run.close();
//        System.out.println("关闭了spring boot");

    }

}
