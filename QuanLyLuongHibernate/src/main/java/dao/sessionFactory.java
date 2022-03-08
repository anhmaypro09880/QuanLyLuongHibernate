package dao;

import org.hibernate.SessionFactory;

import dao.Impl.MySessionFactory;

public interface sessionFactory {
	public static SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
}
