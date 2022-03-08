package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.BangLuongDao;
import dao.sessionFactory;
import entity.BangLuong;

public class BangLuongImpl extends UnicastRemoteObject implements BangLuongDao,sessionFactory {
	
	public BangLuongImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String GetMaCuoiBL() throws RemoteException {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select top 1 * from BangLuong order by maBL DESC",BangLuong.class).getResultList();
			tr.commit();
			if(ds.size()==0)
			{
				return "BL000000";
			}
			return ds.get(0).getMaBL();
			
		} catch (Exception e) {
			tr.rollback();
			return "BL000000";
		}
	}

	@Override
	public boolean ThemBangLuong(BangLuong bl) throws RemoteException {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		
		try {
			tr.begin();
			session.save(bl);
			tr.commit();
			return true;
			
		} catch (Exception e) {
			tr.rollback();
			return false;
		}
	}

	@Override
	public List<BangLuong> GetBLall(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where maNV like 'LD%' and nam = "+nam,BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy1(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >0 and thang <4 and maNV like 'LD%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy2(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >3 and thang <7 and maNV like 'LD%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy3(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >6 and thang <10 and maNV like 'LD%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy4(int nam) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >9 and thang <13 and maNV like 'LD%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy1NV(int nam) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >0 and thang <4 and maNV like 'HC%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy2NV(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >3 and thang <7 and maNV like 'HC%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy3NV(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >6 and thang <10 and maNV like 'HC%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetQuy4NV(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang >9 and thang <13 and maNV like 'HC%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetBLCNTheoThang(int nam, int thang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang ="+thang+" and maNV like 'LD%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetBLNVTheoThang(int nam, int thang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where nam = "+nam+"and thang ="+thang+" and maNV like 'HC%'",BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

	@Override
	public List<BangLuong> GetBLNVall(int nam) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<BangLuong> ds = null;
		try {
			tr.begin();
			ds = session.createNativeQuery("select * from BangLuong where maNV like 'HC%' and nam = "+nam,BangLuong.class).getResultList();
			tr.commit();
			return ds;
			
		} catch (Exception e) {
			tr.rollback();
			return null;
		}
	}

}
