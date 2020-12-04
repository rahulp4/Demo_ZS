package com.demo.apps.service;

import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class AppContext {
private static AppContext appContext	=	null;
	
	private static ClassPathXmlApplicationContext springContext = null;
	private static ApplicationContext ctx ;
	
	private HashMap<String, Object> appData	=	null;
	
	private AppContext(){
		appData	=	new HashMap<String, Object>();
	}
	
	
	public static ClassPathXmlApplicationContext getSpringContext(){
		
		if(springContext==null){
			synchronized (AppContext.class) {
				if(springContext==null){
					springContext = new ClassPathXmlApplicationContext(new ClassPathResource("spring-config.xml").getPath());
					//ctx 	= new GenericXmlApplicationContext("classpath*:spring-config.xml");
				}
			}
		}
		
		return springContext;
		
	}
	
	
	public static ApplicationContext getContext(){
		
		if(springContext==null){
			synchronized (AppContext.class) {
				if(springContext==null){
					//springContext = new ClassPathXmlApplicationContext(new ClassPathResource("spring-config.xml").getPath());
					ctx 	= new GenericXmlApplicationContext("classpath*:spring-config.xml");
				}
			}
		}
		
		return ctx;
		
	}

	
	public static void closeSpringContext(){
		if(springContext!=null){
			springContext.close();
		}
	}
	
	
	private static AppContext getAppContext(){
		if(appContext==null){
			synchronized (AppContext.class) {
				if(appContext==null){
					appContext	=	new AppContext();
				}
			}
		}
		
		return appContext;
	}
}
