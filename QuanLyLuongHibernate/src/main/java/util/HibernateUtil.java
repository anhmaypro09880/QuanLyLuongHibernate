package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;

import java.rmi.registry.Registry;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.*;
public class HibernateUtil {
		private static HibernateUtil instance;
		
		private EntityManager entityManager;
		public HibernateUtil() {
			entityManager= Persistence.createEntityManagerFactory("QuanLyLuongHibernate")
					.createEntityManager();
		}
		public synchronized static HibernateUtil getInstance() {
			if(instance == null)
					instance = new HibernateUtil();
			return instance;
		}
		
		public EntityManager getEntityManager() {
			return entityManager;
		}
}
