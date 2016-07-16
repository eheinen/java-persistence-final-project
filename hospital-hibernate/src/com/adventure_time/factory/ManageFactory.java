package com.adventure_time.factory;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageFactory {
	
	private Class<?> classEntity;
	
	public ManageFactory(Class<?> classEntity){
		this.classEntity = classEntity;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T insert(T obj) {
		Session session = Factory.getInstance().openSession();
		Transaction tx = null;
		T getObject = null;
		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(obj);
			getObject = (T) session.get(classEntity, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return getObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list() {
		Session session = Factory.getInstance().openSession();
		Transaction tx = null;
		List<T> objs = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			query = session.createQuery("FROM " + classEntity.getSimpleName()).setCacheable(true);
			objs = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return objs;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void delete(Integer id) {
		Session session = Factory.getInstance().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			T getObject = (T) session.get(classEntity, id);
			session.delete(getObject);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
