package dao.Impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


import entity.BangLuong;
import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.PhongBan;
import entity.SanPham;
import entity.ThuNhapKhac;

public class MySessionFactory {
	private SessionFactory sessionFactory;
	
	public MySessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.configure() //hibernate.cfg.xml
				.build();	
		
		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(BangLuong.class)
				.addAnnotatedClass(ChamCongNVHC.class)
				.addAnnotatedClass(CongDoan.class)
				.addAnnotatedClass(NhanVien_CongDoan.class)
				.addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(PhongBan.class)
				.addAnnotatedClass(SanPham.class)
				.addAnnotatedClass(ThuNhapKhac.class)
//				.addAnnotatedClass(Store.class)
				.getMetadataBuilder()
				.build();
		
		sessionFactory = metadata
				.getSessionFactoryBuilder()
				.build();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void close() {
		sessionFactory.close();
	}
}
