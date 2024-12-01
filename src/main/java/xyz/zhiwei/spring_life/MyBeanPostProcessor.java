package xyz.zhiwei.spring_life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zhiweicoding.xyz
 * @date 30/10/2024
 * @email diaozhiwei2k@gmail.com
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanLifeComponent")) {
            System.out.println("BeanPostProcessor 执行初始化前置方法");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanLifeComponent")) {
            System.out.println("BeanPostProcessor 执行初始化后置方法");
        }
        return bean;
    }
}