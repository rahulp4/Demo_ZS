package com.demo.fileproc.fw.hibernate;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.demo.fileproc.fw.IComponentInitilizer;

public class HibernateInitializer implements IComponentInitilizer {
	Logger logger 	=	LoggerFactory.getLogger(HibernateInitializer.class);
	@Override
	public boolean startComponent(ApplicationContext context) {
		// TODO Auto-generated method stub
		boolean returnFlag	=	false;
		
		try{
			SessionFactory sessionFactory	=	HibernateSessionFactory.getSessionFactory();
			
			returnFlag	=	true;
			logger.info("Done with DB initilization");
		} catch (Exception e){
			logger.error("Error in initilizing Hibernate");
			throw e;
		}
		return returnFlag;
	}

	@Override
	public boolean stopComponent(ApplicationContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
