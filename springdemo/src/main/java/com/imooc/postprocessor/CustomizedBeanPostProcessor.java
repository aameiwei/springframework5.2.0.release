package com.imooc.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
//bean的后置处理器
@Configuration
public class CustomizedBeanPostProcessor implements BeanPostProcessor {
	//bean初始化之前调用的，注意不是BeanDefinition
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
		System.out.println(beanName + "调用了 postProcessBeforeInitialization() ");
		return bean;
	}
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
		System.out.println(beanName + "调用了 postProcessAfterInitialization() ");
		return bean;
	}
}
