package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhanVienDao;
import dao.sessionFactory;
import entity.NhanVien;
import entity.PhongBan;
import util.HibernateUtil;

public class NhanVienImpl extends UnicastRemoteObject implements NhanVienDao,sessionFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	public NhanVienImpl() throws RemoteException {
//		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}
	@Override
	public boolean ThemNhanVien(NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(nv); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}
	@Override
	public NhanVien GetNVTheoMa(String ma) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where maNV ="+"'"+ma+"'",NhanVien.class).getResultList();
			tr.commit();
			return ds.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return null;
	}
	public List<NhanVien> getNVDangLam() throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where trangThai = N'1' and maNV not like 'QL%'",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public List<NhanVien> getNVall() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where maNV not like 'QL%'",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public NhanVien getNVCuoiLD() throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = null;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select top 1 * from NhanVien where maNV like 'LD%' order by maNV desc",NhanVien.class).getResultList();
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
	public NhanVien getNVCuoiHC() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = null;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select top 1 * from NhanVien where maNV like 'HC%' order by maNV desc",NhanVien.class).getResultList();
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
	public boolean capNhatTrangThai(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.createQuery("update NhanVien set trangThai = 0 where maNV ="+"'"+maNV+"'").executeUpdate();
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}
	@Override
	public boolean SuaNV(NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(nv); 
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}
	@Override
	public List<NhanVien> getLCN() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where maPB = N'LD' and trangThai = N'1' ",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public List<NhanVien> getLNV() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where maPB = N'HC' and trangThai = N'1' ",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public List<NhanVien> getTimNVtheoma(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where (maNV ="+"'"+maNV+"' or maNV like N'%"+maNV+"%'or tenNV like N'%"+maNV+"%' or CMND like '%"+maNV+"%') and trangThai = N'1'",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public List<NhanVien> getNVNghiviec() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from NhanVien where trangThai = N'0'",NhanVien.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return ds;
	}
	@Override
	public boolean doiMatKhau(NhanVien nv) {
//		EntityTransaction tr = em.getTransaction();
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public PhongBan getPhongBan(String ma){
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<PhongBan> ds;
		try {
			tr.begin();
			ds=  session.createNativeQuery("select * from PhongBan where maPB ="+"'"+ma+"'",PhongBan.class).getResultList();
			tr.commit();
			return ds.get(0);
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}
	
	

}
