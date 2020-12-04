package com.demo.fileproc.fw.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {

	//private static final SessionFactory sessionFactory = buildSessionFactory();
		private static final SessionFactory sessionFactory = buildSessionFactoryFromFile();
		
		

	private static ServiceRegistry serviceRegistry;

		private static SessionFactory buildSessionFactory() {
			return new Configuration().configure().buildSessionFactory();
		}

		private static SessionFactory buildSessionFactoryFromFile() {
			
			String hibernateConfigFile	=	"hibernate-conf.cfg.xml";
			return new Configuration().configure(
					hibernateConfigFile)
					.buildSessionFactory();
		}
		

		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		 }

		
		
		
		public static void shutdown() {
		// Close caches and connection pools
			getSessionFactory().close();
		}
}
