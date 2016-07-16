package com.adventure_time.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

	private static SessionFactory instance = new Configuration().configure().buildSessionFactory();
	
	private Factory(){}
	
	public static SessionFactory getInstance(){
		return instance;
	}
	
}
