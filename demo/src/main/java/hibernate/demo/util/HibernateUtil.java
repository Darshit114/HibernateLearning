package hibernate.demo.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import hibernate.demo.model.Employee1;

public class HibernateUtil {

	//XML based configuration
	private static SessionFactory sessionFactory;
	
	//Annotation based configuration 
	private static SessionFactory sessionAnnotationFactory;
	
	//Property based cofiguration
	private static SessionFactory sessionJavaConfigFactory;
	
	private static SessionFactory buildSessionFactory() {
		
		try {
			
			//Create the session factory from the hibernate.cfg.xml
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			System.out.println("Hibernate configuration loaded.");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
													.applySettings(cfg.getProperties()).build();
			System.out.println("Hibernate service registry created");
			
			SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			
			return sessionFactory;
			
		} catch (Throwable ex) {
			
			System.out.println("Initial SessionFactory creation failed: " + ex);
			
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	private static SessionFactory buildSessionAnnotationFactory() {
		
		try {
			
			Configuration cfg = new Configuration();
			
			cfg.configure("hibernate-annotation.cfg.xml");
			System.out.println("Hibernate Annotation Configuration loaded");
						
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
													.applySettings(cfg.getProperties()).build();
			
			System.out.println("Hibernate Annotation serviceRegistry created");

			SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			
			return sessionFactory;
			
		}catch (Throwable ex) {
			
			System.err.println("Initial SessionFactory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	private static SessionFactory buildSessionJavaConfigFactoy() {
		
		try {
			
			Configuration cfg = new Configuration();
			
			Properties props = new Properties();
			
			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernatedemo");
			props.put("hibernate.connection.username", "root");
			props.put("hibernate.connection.password", "root");
			props.put("hibernate.current_session_context_class", "thread");
			
			cfg.setProperties(props);
			
			cfg.addAnnotatedClass(Employee1.class);
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
												.applySettings(cfg.getProperties()).build();
			System.out.println("Hibernate Java Config ServiceRegistry created");
			
			SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			
			return sessionFactory;
			
		} catch (Throwable ex) {
	
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
	}
	
	
	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static SessionFactory getSessionAnnotationFactory() {
			
			if(sessionAnnotationFactory == null) {
				sessionAnnotationFactory = buildSessionAnnotationFactory();
			}
			
			return sessionAnnotationFactory;
	}
	
	public static SessionFactory getSessionJavaConfigFactory() {
		
		if(sessionJavaConfigFactory == null) {
			sessionJavaConfigFactory = buildSessionJavaConfigFactoy();
		}
		
		return sessionJavaConfigFactory;
	}
}
