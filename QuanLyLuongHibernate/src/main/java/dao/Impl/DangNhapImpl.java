package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.DangNhapDao;
import dao.sessionFactory;
import entity.NhanVien;

public class DangNhapImpl extends UnicastRemoteObject implements DangNhapDao,sessionFactory{
	
	
	
	public DangNhapImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public boolean KtraQuanly(String username) throws RemoteException {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> nv =null;
		try {
			tr.begin();
			nv =  session.createNativeQuery("select * from NhanVien where maNV ="+"'"+username+"'",NhanVien.class).getResultList();
			tr.commit();
			if(nv.get(0).isQuanLy())
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
//			tr.rollback();
			return false;
		}
		return false;
	}
	@Override
	public boolean KtraDangNhap(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> nv =null;
		try {
			tr.begin();
			nv =  session.createNativeQuery("select * from NhanVien where maNV ="+"'"+username+"'",NhanVien.class).getResultList();
			tr.commit();
			if(nv.get(0).isTrangThai()&&nv.get(0).getPassword().equals(password))
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
//			tr.rollback();
			return false;
		}
		return false;
	}
	
}
