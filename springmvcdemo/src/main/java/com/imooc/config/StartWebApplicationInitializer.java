package com.imooc.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 实现无xml化的全注解springmvc配置
 */
public class StartWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/**
	 * SpringContext中相关的bean
	 *
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{SpringRootConfig.class};
	}
	/**
	 * DispatcherServlet中上下文相关的Bean
	 *
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{MVCConfig.class};
	}
	/**
	 * Servlet请求映射路径
	 *
	 * @return
	 */
	@Override
	protected String[] getServletMappings(){
		//这里表明所有的请求都会交由这个servlet来处理
		return new String[]{"/"};
	}
	/**
	 * 拦截并处理请求的编码
	 *
	 * @return
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[]{encodingFilter};
	}
}

