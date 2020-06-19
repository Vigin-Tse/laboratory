package com.vg.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vg.mvc.annotation.*;


/**
 * 实现前端控制功能
 * */
public class MyDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String CONTEXT_CONFIG_NAME = "contextConfig";
	
	private static final String SCAN_PACKAGE_CONFIG = "ScanPackage";
	
	//读取的配置（application.properties）
	private Properties properties = new Properties();

	// 类的全路径名集合
    private List<String> classNames = new ArrayList<String>();
    
    //IOC容器
    private Map<String, Object> ioc = new HashMap<String, Object>();

    //handlerMapping保存uri和method的关系
	private Map<String, Method> handlerMapping = new HashMap<String, Method>();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		this.doDispatcher(req, resp);
	}

	private void doDispatcher(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		if (this.handlerMapping.isEmpty()){
			resp.getWriter().write("404 NOT FOUND!");
			return;
		}

		String url = req.getRequestURI();
		String contexgtPath = req.getContextPath();

		url = url.replace(contexgtPath, "").replaceAll("/+","/");

		if (!handlerMapping.containsKey(url)){
			resp.getWriter().write("404 NOT FOUND!");
			return;
		}

		//取回调用方法
		Method method = this.handlerMapping.get(url);

		//被调用方法的参数类型
		Class<?>[] methodParmType = method.getParameterTypes();

		//方法的参数
		Parameter[] p = method.getParameters();

		//取回请求参数
		Map<String, String[]> reqParmMap = req.getParameterMap();

        // 保存参数值
		Object[] parmValues = new Object[methodParmType.length];

		// 方法的参数列表
		for(int i = 0; i < methodParmType.length; i++){

			String parmName = methodParmType[i].getSimpleName();
			Parameter mPara = p[i];

			if(parmName.equals("HttpServletRequest")){
				parmValues[i] = req;
				continue;
			}

			if(parmName.equals("HttpServletResponse")){
				parmValues[i] = resp;
				continue;
			}

			if (parmName.equals("String")){

				String[] values = null;

				if (mPara.isAnnotationPresent(RequestParamter.class)
						&& mPara.getAnnotation(RequestParamter.class).value() != null){

                    values = reqParmMap.get(mPara.getAnnotation(RequestParamter.class).value());
				}else{
					values = reqParmMap.get(mPara.getName());
				}

				if (values != null){
					parmValues[i] = Arrays.toString(values)
							.replaceAll("\\[","")
							.replaceAll("\\]","")
							.replaceAll(",\\s", "");
				}
			}
		}

		//利用反射机制调用
		try {
			method.invoke(this.ioc.get(url), parmValues);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 重写初始化方法
	 * */
	@Override
	public void init(ServletConfig config){
		
		//1.加载配置文件
		this.doLoadConfig(config.getInitParameter(CONTEXT_CONFIG_NAME));
		
		//2.初始化所有的类，扫配配置文件（application.properties）中定义包下面所有的类
		this.doscanner(properties.getProperty(SCAN_PACKAGE_CONFIG));
		
		//3.拿到扫描的类，通过反射机制，实例化，放到IOC容器中（K-V，beanName-object）beanName默认首字母小写
		this.doInstance();
		
		//4.初始化handlerMapping，将url和method对应上
		this.initHandlerMapping();

		//5.实现（DI）注入
		this.doDi();
	}
	
	/**
	 * 1.加载配置文件
	 * */
	private void doLoadConfig(String configsLocation){
		
		// 把web.xml中的contextConfigLocation对应value值的文件加载到留里面
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(configsLocation);
		
		try {
			// 用Properties文件加载文件里的内容
			if (in != null) {
				this.properties.load(in);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			//关流
			if (in != null) {
				
				try {
					
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 2.初始化所有的类，扫配配置文件（application.properties）中定义包下面所有的类
	 * */
	private void doscanner(String packageName){
		
		if (packageName == null || "".equals(packageName)) {
			return;
		}
		
		//把所有.替换成/
		String path = "/" + packageName.replace(".", "/");
		URL url = this.getClass().getClassLoader().getResource(path);
		
		if (url == null) {
			return;
		}
		
		try {
			//URLDecoder.decode 对路径的空格、特殊字符进行处理
			File dir = new File(URLDecoder.decode(url.getFile(), "UTF-8"));
			
			if (dir != null) {
				
				for(File file : dir.listFiles()){
					
					if (file.isDirectory()) {
						
						//如果是文件夹，递归
						this.doscanner(packageName + "." + file.getName());
					}else{
						
						String className = packageName + "." + file.getName().replace(".class", "");
						this.classNames.add(className);
						System.out.println("扫描：" + className);
					}
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 3.拿到扫描的类，通过反射机制，实例化被@Controller、@Service标注的类，
	 * 并放到IOC容器中（K-V，beanName-object）beanName默认首字母小写
	 * */
	private void doInstance(){
		
		if (this.classNames.isEmpty()) {
			return;
		}
		
		for(String className : this.classNames){
			
			try {
				
				//拿到类对象
				Class<?> clazz = Class.forName(className);
				
				//判断该类对象是否用@Controller注解
				if (clazz.isAnnotationPresent(Controller.class)) {
					
					//实例化被注解的类
					Object obj = clazz.newInstance();
					
					//获悉该类的注解对象信息
					Controller conInfo = clazz.getAnnotation(Controller.class);
					
					if (conInfo.value() != null && !"".equals(conInfo.value()) ) {//如果该类的自定义信息不为空
						
						ioc.put(conInfo.value(), obj);
						System.out.println(className + "实例化，并装进IOC容器，beanName=" + conInfo.value());
					}else{
						
						ioc.put(this.toLowerCaseFirstLetter(clazz.getSimpleName()), obj);
						System.out.println(className + "实例化，并装进IOC容器，beanName=" + this.toLowerCaseFirstLetter(clazz.getSimpleName()));
					}
					
				}
				
				//判断该类对象是否用@Service注解
				if (clazz.isAnnotationPresent(Service.class)) {

					
					//实例化被注解的类
					Object obj = clazz.newInstance();
					
					//获悉该类的注解对象信息
					Service conInfo = clazz.getAnnotation(Service.class);
					
					if (conInfo.value() != null && !"".equals(conInfo.value()) ) {//如果该类的自定义信息不为空
						
						ioc.put(conInfo.value(), obj);
						System.out.println(className + "实例化，并装进IOC容器，beanName=" + conInfo.value());
					}else{
						
						ioc.put(this.toLowerCaseFirstLetter(clazz.getSimpleName()), obj);
						System.out.println(className + "实例化，并装进IOC容器，beanName=" + this.toLowerCaseFirstLetter(clazz.getSimpleName()));
					}
					
				
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 4.初始化handlerMapping，将url和method对应上
	 * */
	private void initHandlerMapping(){
		if (this.ioc.isEmpty()){
			return;
		}

		//临时Map,保存url和controler实力
		Map<String, Object> ins = new HashMap<String, Object>();

		//遍历IOC容器中的controller实例，建立uri和method的关系
		for (Map.Entry<String, Object> entry : this.ioc.entrySet()){

			Class<? extends Object> clazz = entry.getValue().getClass();

			//只处理controller
			if (!clazz.isAnnotationPresent(Controller.class)){
				continue;
			}

			String cUrl = "";

			if (clazz.isAnnotationPresent(RequestMapping.class)){
				RequestMapping req = clazz.getAnnotation(RequestMapping.class);
				cUrl = req.value();
			}

			Method[] method = clazz.getDeclaredMethods();

			for (Method m : method){

				//只处理有requestMapping注释的方法
				if (!m.isAnnotationPresent(RequestMapping.class)){
					continue;
				}

				RequestMapping mReq = m.getAnnotation(RequestMapping.class);
				String mUrl = (cUrl + "/" +mReq.value()).replaceAll("/+", "/");
				handlerMapping.put(mUrl, m);
				ins.put(mUrl, entry.getValue());
				System.out.println("rest:" + mUrl + "," + entry.getValue().getClass().getSimpleName() + "." + m.getName());
			}
		}

		this.ioc.putAll(ins);
	}

	/**
	 * 5.实现（DI）注入
	 * */
	private void doDi(){
		if (this.ioc.isEmpty()){
			return;
		}

		for(Map.Entry<String, Object> entry : this.ioc.entrySet()){

			//getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
			//getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
			Field[] fields = entry.getValue().getClass().getDeclaredFields();

			for(Field f : fields){

				f.setAccessible(true);// 可以访问私有属性

				if (f.isAnnotationPresent(Qualifier.class)){
					Qualifier qualifier = f.getAnnotation(Qualifier.class);

					String value = qualifier.value();
					String key;

					// 注解有参数就使用参数作为引用，没参数使用字段名字作为引用	
					if (value != null && !"".equals(value)){//注解有参数的
						key = value;
					}else{
						key = f.getName();
					}

					try {
						if (this.ioc.get(key) != null) {
							f.set(entry.getValue(), this.ioc.get(key));
							System.out.println("依赖注入：" + key);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private String toLowerCaseFirstLetter(String name){
		if (name == null) {
			return "";
		}
		
		char[] namesChat = name.toCharArray();
		namesChat[0] += 32;
		
		return String.valueOf(namesChat);
	}
	
	
	public static void main(String[] args){
		MyDispatcherServlet servlet = new MyDispatcherServlet();
		System.out.println(servlet.getClass().getClassLoader());
		System.out.println(servlet.getClass().getClassLoader().getResource("com/vg/demo"));
		System.out.println(servlet.getClass().getClassLoader().getResource("/com/vg/demo"));
		
//		String c = "com.vg.mvc";
//		
//		System.out.println(c.replace(".", "/"));
//		System.out.println(c.replaceAll("\\.", "/"));
	}
}
