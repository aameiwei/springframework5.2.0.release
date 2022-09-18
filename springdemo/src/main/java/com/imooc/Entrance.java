package com.imooc;

//import com.imooc.aspect.OutSide;
//import com.imooc.controller.HelloController;
//import com.imooc.controller.HiController;
import com.imooc.controller.HelloController;
import com.imooc.controller.HiController;
import com.imooc.controller.WelcomeController;
//import com.imooc.dao.impl.Company;
//import com.imooc.entity.User;
//import com.imooc.entity.factory.UserFactoryBean;
import com.imooc.introduction.LittleUniverse;
//import com.imooc.service.HelloService;
//import com.imooc.service.HiService;
import com.imooc.dao.impl.BoyFriend;
import com.imooc.dao.impl.Company;
import com.imooc.entity.User;
import com.imooc.entity.factory.UserFactoryBean;
import com.imooc.service.WelcomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@Configuration
@EnableAspectJAutoProxy
//@Import(OutSide.class)
@ComponentScan("com.imooc")
public class Entrance {

	public static void main1(String[] args) {
		String xmlPath = "D:\\ideaworkspace\\springframework5.2.0.release\\springdemo\\src\\main\\resources\\spring\\spring-config.xml";
		FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(xmlPath);
		WelcomeService welcomeService = (WelcomeService)fileSystemXmlApplicationContext.getBean("welcomeService");
		welcomeService.sayHello("gaoding");
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
//		HiService hiService = (HiService)applicationContext.getBean("hiServiceImpl");
//		hiService.sayHi();
//		System.out.println("---------------------------分割线以下执行HelloService-------------------------------");
//		HelloService helloService = (HelloService)applicationContext.getBean("helloServiceImpl");
//		helloService.sayHello();
	}

	public static void main2(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		WelcomeService welcomeService = (WelcomeService)applicationContext.getBean("welcomeServiceImpl");
		welcomeService.sayHello("welcomeService");
		WelcomeController welcomeController = (WelcomeController)applicationContext.getBean("welcomeController");
		welcomeController.handleRequest();
	}

	public static void main3(String[] args) {
		String xmlPath = "D:\\ideaworkspace\\springframework5.2.0.release\\springdemo\\src\\main\\resources\\spring\\spring-config.xml";
		FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(xmlPath);
		//用类的无参构造函数创建
		User user1a = (User)fileSystemXmlApplicationContext.getBean("user1");
		User user1b = (User)fileSystemXmlApplicationContext.getBean("user1");
		System.out.println("用类的无参构造函数创建user1a:"+user1a);
		System.out.println("用类的无参构造函数创建user1b:"+user1b);
       //静态工厂创建的对象
		User user2a = (User)fileSystemXmlApplicationContext.getBean("user2");
		User user2b = (User)fileSystemXmlApplicationContext.getBean("user2");
		System.out.println("静态工厂创建的对象user2a:"+user2a);
		System.out.println("静态工厂创建的对象user2b:"+user2b);

		//实例工厂创建的对象
		User user3a = (User)fileSystemXmlApplicationContext.getBean("user3");
		User user3b = (User)fileSystemXmlApplicationContext.getBean("user3");
		System.out.println("实例工厂创建的对象user3a:"+user3a);
		System.out.println("实例工厂创建的对象user3b:"+user3b);
        //得到BeanFactory创建的对象
		User user4a = (User)fileSystemXmlApplicationContext.getBean("userFactoryBean");
		User user4b = (User)fileSystemXmlApplicationContext.getBean("userFactoryBean");
		UserFactoryBean user4c = (UserFactoryBean)fileSystemXmlApplicationContext.getBean("&userFactoryBean");
		System.out.println("得到BeanFactory创建的对象user4a:"+user4a);
		System.out.println("得到BeanFactory创建的对象user4b:"+user4b);
		System.out.println("得到BeanFactory创建的自己本身user4c:"+user4c);

	}

	public static void main4(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
		/**
		 * 在类AbstractApplicationContext的refresh（）--> finishBeanFactoryInitialization()实例化所有的类-->beanFactory.preInstantiateSingletons()实例化所有剩余的(non-lazy-init非延时加载的)单例
		 * 其实现在DefaultListableBeanFactory类中，方法中preInstantiateSingletons（）方法中有实例化的判断	if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit())
		 * 说明容器在刷新完成的是时候只是加载 “非延迟加载的单例”，所以对于@Scope(value = "prototype")的情况来说需要显式调用getBean进行首次加载
		 * 这里会报循环依赖的异常，
		 * 说明spring默认不支持prototype的构造器循环依赖,也不支持prototype的setter注入的循环依赖
		 *
		 *
		 * 将Company的@Scope属性去掉，即变为singleton的方式
		 * 依旧报错
		 * 说明spring也不支持singleton的方式的构造器循环依赖
		 * 说明spring仅支持singleton的方式的setter注入的循环依赖
		 * 因为在AbstractAutowireCapableBeanFactory类的doCreateBean方法的中的createBeanInstance（）方法中创建bean的时候并没有使用三级缓存
		 * 查看其中的 autowireConstructor（）方法断点调试
		 */
		//Company company = (Company)applicationContext.getBean("company");
		//BoyFriend boyFriend = (BoyFriend)applicationContext.getBean("boyFriend");
	}
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Entrance.class);
		System.out.println("**********AOP相关*************");
		HelloController helloController = (HelloController)applicationContext.getBean("helloController");
		//helloController.handleRequest();
//		HiController hiController = (HiController)applicationContext.getBean("hiController");
//		hiController.handleRequest();
		((LittleUniverse)helloController).burningup();

	}
}
