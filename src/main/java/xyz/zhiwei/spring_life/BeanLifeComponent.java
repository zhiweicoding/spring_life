package xyz.zhiwei.spring_life;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class BeanLifeComponent implements BeanNameAware {
    public void setBeanName(String s) {
        System.out.println("BeanNameAware 执行 BeanName 的通知方法");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("BeanNameAware 执行初始化方法");
    }

    public void use() {
        System.out.println("BeanNameAware 使用 Bean");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("BeanNameAware 执行销毁方法");
    }
}