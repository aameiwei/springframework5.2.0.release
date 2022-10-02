package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * DispatcherServlet中上下文相关的Bean
 *
 * @return
 */
@Configuration
@ComponentScan("com.imooc.controller")
@EnableWebMvc
public class MVCConfig {
	@Bean
	public InternalResourceViewResolver viewResolver(){
		//InternalResourceViewResolver为spring 视图解析，是URLBasedViewResolver的子类，
		//InternalResourceViewResolver会把返回的视图名称都解析为InternalResourceView对象，
		//InternalResourceView会把Controller处理器方法返回的模型属性都存放到对应的request属性中，然后通过RequestDispatcher在服务器端把请求forword重定向到目标URL。
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
}
