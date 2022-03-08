package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SanPhamDao;
import dao.sessionFactory;
import entity.NhanVien;
import entity.SanPham;

public class SanPhamImpl extends UnicastRemoteObject  implements SanPhamDao,sessionFactory{

	public SanPhamImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean ThemSanPham(SanPham sp)  {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(sp); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<SanPham> getSPall()  {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from SanPham",SanPham.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}

	@Override
	public SanPham getSPCuoi()  {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<SanPham> ds = null;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select top 1 * from SanPham where maSP like 'SP%' order by maSP desc",SanPham.class).getResultList();
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
	public boolean SuaSP(SanPham sp)  {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(sp); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<SanPham> getmaSp(String maSP) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<SanPham> ds = new ArrayList<SanPham>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from SanPham where maSP="+"'"+maSP+"'",SanPham.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
}
