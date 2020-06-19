简单实现SpringMvc原理
===
基于HttpServlet实现简单的SpringMVC（实现Spring注解、IOC、DI、请求映射相关原理）。

## 实现的思路与步骤：

##### 1.读取配置
     * a.通过web.xml加载我们自己写的MyDispatcherServlet（代替org.springframework.web.servlet.DispatcherServlet）和读取配文件application.properties(代替原spring-mvc.xml，定义springIoc注解的包扫描路径)。
	 
##### 2.初始化
     重写init()方法,实现原九大组件中的基本的功能：
	 * a.加载配置文件application.properties（获得注解的扫描包路径）
	 * b.扫描配置包下的类
	 * c.通过反射机制实例化被自定义注解的类，并放到ioc容器中（Map容器，键值对beanName-bean）,beanName默认首字母小写
	 * d.初始化HandlerMapping
