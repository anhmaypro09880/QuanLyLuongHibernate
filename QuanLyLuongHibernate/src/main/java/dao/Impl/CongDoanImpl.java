package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.CongDoanDao;
import dao.sessionFactory;
import entity.CongDoan;
import entity.SanPham;

public class CongDoanImpl extends UnicastRemoteObject implements CongDoanDao,sessionFactory{

	public CongDoanImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean ThemCongDoan(CongDoan cd) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(cd); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public CongDoan getCDCuoi() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<CongDoan> ds = null;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select top 1 * from CongDoan where maCongDoan like 'CD%' order by maCongDoan desc",CongDoan.class).getResultList();
			tr.commit();
			if(ds.size()>0) {
				return ds.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return null;
	}

	@Override
	public List<CongDoan> getCongDoanTheoMaSp(String maSP) throws RemoteException{
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<CongDoan> ds = new ArrayList<CongDoan>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from CongDoan where maSanPham ="+"'"+maSP+"'",CongDoan.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}

	@Override
	public boolean SuaCD(CongDoan cd) throws RemoteException{
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(cd); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

}
