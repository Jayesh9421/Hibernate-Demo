package com.jrk.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.jrk.util.entities.Employee;

public class HibernateUtil {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	static {
		try {
			if (sessionFactory == null) {
				// Create standardServiceRegistry
				StandardServiceRegistryBuilder	standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
				standardServiceRegistryBuilder.loadProperties("hibernate.properties");
				standardServiceRegistry = standardServiceRegistryBuilder.build();
				
				// Create MetadataSources
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				metadataSources.addAnnotatedClass(Employee.class);
				// Create MetaData
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	public static SessionFactory getSessionFactory() {
		
		
		return sessionFactory;
	}
}
