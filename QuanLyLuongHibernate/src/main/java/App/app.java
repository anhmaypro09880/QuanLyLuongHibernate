package App;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dao.BangLuongDao;
import dao.CongDoanDao;
import dao.DangNhapDao;
import dao.DuLieuCongNhanDao;
import dao.DuLieuNhanVienDao;
import dao.NhanVienDao;
import dao.PhongBanDao;
import dao.SanPhamDao;
import dao.TinhLuongDao;
import dao.Impl.BangLuongImpl;
import dao.Impl.CongDoanImpl;
import dao.Impl.DangNhapImpl;
import dao.Impl.DuLieuCongNhanImpl;
import dao.Impl.DuLieuNhanVienImpl;
import dao.Impl.MySessionFactory;
import dao.Impl.NhanVienImpl;
import dao.Impl.PhongBanImpl;
import dao.Impl.SanPhamImpl;
import dao.Impl.TinhLuongImpl;
import entity.CongDoan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.PhongBan;


import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import util.HibernateUtil;

public class app {
	public static void main(String[] args) {
		EntityManager em = HibernateUtil.getInstance().getEntityManager();
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy","policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try {
			LocateRegistry.createRegistry(1124);
			DuLieuCongNhanDao cnDao = new DuLieuCongNhanImpl();
			DangNhapDao dnDao =new  DangNhapImpl();
			DuLieuNhanVienDao nvDao =  new DuLieuNhanVienImpl();
			NhanVienDao nvienDao = new NhanVienImpl();
			TinhLuongDao tlDao = new TinhLuongImpl();
			CongDoanDao cdDao = new CongDoanImpl();
			SanPhamDao spDao = new SanPhamImpl();
			BangLuongDao blDao = new BangLuongImpl();
			PhongBanDao pbDao = new PhongBanImpl();
			
			String ip = "localhost:1124";
			Naming.bind("rmi://" + ip + "/cnDao", cnDao);
			Naming.bind("rmi://" + ip + "/dnDao", dnDao);
			Naming.bind("rmi://" + ip + "/nvienDao", nvienDao);
			Naming.bind("rmi://" + ip + "/pbDao", pbDao);
			Naming.bind("rmi://" + ip + "/tlDao", tlDao);
			Naming.bind("rmi://" + ip + "/blDao", blDao);
			Naming.bind("rmi://" + ip + "/nvDao", nvDao);
			Naming.bind("rmi://" + ip + "/spDao", spDao);
			Naming.bind("rmi://" + ip + "/cdDao", cdDao);
			
			System.out.println("Thanh Cong");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
