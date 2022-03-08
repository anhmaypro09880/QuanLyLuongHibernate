package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.bytecode.enhance.spi.interceptor.SessionAssociableInterceptor;

import dao.DuLieuNhanVienDao;
import dao.sessionFactory;
import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import util.HibernateUtil;

public class DuLieuNhanVienImpl extends UnicastRemoteObject implements DuLieuNhanVienDao,sessionFactory  {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private EntityManager em;
		public DuLieuNhanVienImpl() throws RemoteException {
			super();
//			em = HibernateUtil.getInstance().getEntityManager();
		}

		@Override
//		public List<ChamCongNVHC> getallNhanVien(){
//			EntityTransaction tr = em.getTransaction();
//			try {
//				tr.begin();
//				Query query = em.createQuery("From ChamCongNVHC  ");
//				List<ChamCongNVHC> dsnv = query.getResultList();
//				tr.commit();
//				return dsnv;
//			} catch (Exception e) {
//				tr.rollback();
//				e.printStackTrace();
//			}
//			return null;
//		}
		public List<ChamCongNVHC> getallNhanVien(){
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
//			EntityTransaction tr = em.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
//				Query query = em.createQuery("From ChamCongNVHC  ");
//				List<ChamCongNVHC> dsnv = query.getResultList();
				ds = session.createNativeQuery("Select * From ChamCongNVHC",ChamCongNVHC.class).getResultList();
				tr.commit();
				return ds;
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
			}
			return null;
		}
		@Override
		public boolean themNhanVien(ChamCongNVHC nv) {
//			EntityTransaction tr = em.getTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			try {
				tr.begin();
				session.save(nv);
				tr.commit();
				return true;
			} catch (Exception e) {
				tr.rollback();
				e.printStackTrace();
			}
			return false;
		}
		@Override
		public List<ChamCongNVHC> getNVCuoi() {

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();

			try {
				tr.begin();
				List<ChamCongNVHC> nv;
				nv = session.createNativeQuery("SELECT *\r\n"
						+ "  FROM ChamCongNVHC as c\r\n"
						+ " WHERE c.maChamCong = (SELECT MAX(maChamCong)\r\n"
						+ "                 FROM ChamCongNVHC)",ChamCongNVHC.class).getResultList();
				tr.commit();
				return nv;
			} catch (Exception e) {
			}
			return null;
		}
		@Override
		public ChamCongNVHC getTNK(String manv,int thang, int nam)
		{

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds;
//			EntityTransaction tr = em.getTransaction();
			try {
				tr.begin();
				
				ds =  session.createNativeQuery("select * from ChamCongNVHC  where maNV = '"+manv+"' and thang = '"+thang+"' and nam = '"+nam+"' ",ChamCongNVHC.class).getResultList();
//				Query query = em.createQuery("from ChamCongNVHC as nv where nv.maNV =:manv and nv.thang =:thang and nv.nam =:nam");
//				query.setParameter("manv", "+manv+");
//				query.setParameter("thang", "+thang+");
//				query.setParameter("nam", "+nam+");
//				List<ChamCongNVHC> ds = query.getResultList();
//				p = con.prepareStatement("select * from ThuNhapKhac where maNV = ? and thang = ? and nam = ?");
				tr.commit();
				return ds.get(0);
			} catch (Exception e2) {
				tr.rollback();
				e2.printStackTrace();
			}
			return null;
		}
		
		@Override
		public boolean upDateNhanVien(ChamCongNVHC nv) {
			SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
//			
			Transaction tr = session.getTransaction();
//			EntityTransaction tr = em.getTransaction();
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
		public List<ChamCongNVHC> getThangNam(int tuthang,int tunam ,int denthang,int dennam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("select * from ChamCongNVHC where (nam> "+tunam+" or (nam = "+tunam+" and thang >="+tuthang+")) and (nam <"+dennam+" or(nam = "+dennam+" and thang <="+denthang+"))",ChamCongNVHC.class).getResultList();
				tr.commit();
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<CongDoan> getCongDoan(String maCD){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From CongDoan where maCongDoan like '"+maCD+"'",CongDoan.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<ChamCongNVHC> getmaBangChamCong(String maNV, int thang, int nam){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From ChamCongNVHC where maNV like '"+maNV+"' and thang like '"+thang+"' and nam like '"+nam+"'",ChamCongNVHC.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<ChamCongNVHC> timKiem(String tk,int fromthang, int fromnam, int tothang, int tonam){
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("select * from ChamCongNVHC where (nam> '"+fromnam+"' or (thang>= '"+fromthang+"' and nam = '"+fromnam+"')) and (nam<'"+tonam+"' or (thang <= '"+tothang+"' and nam = '"+tonam+"')) and maNV like N'%"+tk+"%' ",ChamCongNVHC.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<ChamCongNVHC> getLuongNhanVien(String maNV){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From ChamCongNVHC where maNV like '"+maNV+"' order by thang desc",ChamCongNVHC.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public boolean kiemTraNhanVien(String maNV){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From NhanVien where maNV like '"+maNV+"' ",NhanVien.class).getResultList();
				tr.commit();
				
				if(ds.size()> 0) {
					return true;
				}
				return false;
			} catch (Exception e) {
				tr.rollback();
			}
			return false;
		}
		@Override
		public boolean kiemTraNghiViec(String maNV){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien> ds = null;
			int trangThai = 1;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From NhanVien where maNV like '"+maNV+"' and trangThai like '"+trangThai+"' ",NhanVien.class).getResultList();
				tr.commit();
				if(ds.size()> 0) {
					return true;
				}
				return false;
			} catch (Exception e) {
				tr.rollback();
			}
			return false;
		}
		@Override
		public boolean rangBuocThangNam(String ma,int thang,int nam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From ChamCongNVHC where maNV like '"+ma+"' and thang like '"+thang+"' and nam like '"+nam+"'  ",ChamCongNVHC.class).getResultList();
				tr.commit();
				if(ds.size()> 0) {
					return false;
				}
				return true;
			} catch (Exception e) {
				tr.rollback();
			}
			return false;
		}
		@Override
		public boolean rangBuocThangNamCN(String ma,int thang,int nam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From NhanVien_CongDoan where maNhanVien like '"+ma+"' and thang like '"+thang+"' and nam like '"+nam+"'  ",NhanVien_CongDoan.class).getResultList();
				tr.commit();
				if(ds.size()> 0) {
					return false;
				}
				return true;
			} catch (Exception e) {
				tr.rollback();
			}
			return false;
		}
		
}
