package com.demo.fileproc.fw;

import javax.activation.FileDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.demo.apps.service.AppContext;
import com.demo.fileproc.common.AppProperty;
import com.demo.fileproc.fw.hibernate.HibernateInitializer;
import com.demo.fileproc.service.cache.CacheServiceFactory;
import com.demo.fileproc.service.dto.FileChunkDataDTO;

public class ApplicationInitilizer {

	
	public  static void initApp() {
		
		ApplicationContext context	=	AppContext.getSpringContext();
		CacheServiceFactory.getCache("filechunkcoherenace");
		CacheServiceFactory.getCache("entitycoherenace");
		AppProperty.getInstance();
		new HibernateInitializer().startComponent(context);
	}
}
