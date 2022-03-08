package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.DuLieuCongNhanDao;
import dao.sessionFactory;

import javax.persistence.*;

import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.SanPham;
import util.HibernateUtil;

public class DuLieuCongNhanImpl extends UnicastRemoteObject  implements DuLieuCongNhanDao,sessionFactory {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private EntityManager em;
		public DuLieuCongNhanImpl() throws RemoteException {
//			super();
			em = HibernateUtil.getInstance().getEntityManager();
		}
	

		@Override
		public List<NhanVien_CongDoan> getallCongNhan(){
			
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds;
	
			try {
				tr.begin();
//				Query query = em.createQuery(" From NhanVien_CongDoan");
//				List<NhanVien_CongDoan> dsnv = query.getResultList();
				ds = session.createNativeQuery("Select * From NhanVien_CongDoan", NhanVien_CongDoan.class).getResultList();
				tr.commit();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
				tr.rollback();
			}
			return null;
		}
		
		@Override
		public NhanVien_CongDoan getTNK(String manv,int thang, int nam)
		{
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds;
//			
			try {
				tr.begin();
				
				ds =  session.createNativeQuery("select * from NhanVien_CongDoan  where maNhanVien = '"+manv+"' and thang = '"+thang+"' and nam = '"+nam+"' ",NhanVien_CongDoan.class).getResultList();
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
		public List<SanPham> getTenSanPham(String maSP){
	
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<SanPham> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From SanPham where maSP like '"+maSP+"'",SanPham.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<SanPham> getallTenSanPham(){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<SanPham> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From SanPham",SanPham.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<CongDoan> getTenCongDoan(String maSP){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From CongDoan where maSanPham like '"+maSP+"'",CongDoan.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public CongDoan getCongDoan(String tenCD, String maSP){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From CongDoan where tenCongDoan like N'"+tenCD+"' and maSanPham like '"+maSP+"'",CongDoan.class).getResultList();
				tr.commit();
				
				return ds.get(0);
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<SanPham> getMaSpTheoTenSP(String tenSP){
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<SanPham> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From SanPham where tenSP like N'"+tenSP+"'",SanPham.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<NhanVien_CongDoan> timKiem(String tk,int fromthang, int fromnam, int tothang, int tonam){
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("select * from NhanVien_CongDoan where (nam> '"+fromnam+"' or (thang>= '"+fromthang+"' and nam = '"+fromnam+"')) and (nam<'"+tonam+"' or (thang <= '"+tothang+"' and nam = '"+tonam+"')) and maNhanVien like N'%"+tk+"%' ",NhanVien_CongDoan.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public List<NhanVien_CongDoan> getThangNam(int tuthang,int tunam ,int denthang,int dennam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("select * from NhanVien_CongDoan where (nam> "+tunam+" or (nam = "+tunam+" and thang >="+tuthang+")) and (nam <"+dennam+" or(nam = "+dennam+" and thang <="+denthang+"))",NhanVien_CongDoan.class).getResultList();
				tr.commit();
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public boolean themCongNhan(NhanVien_CongDoan nv) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
//			
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
		public List<NhanVien_CongDoan> getLuongCongNhan(String maNV){

			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds = null;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From NhanVien_CongDoan where maNhanVien like '"+maNV+"' order by thang desc ",NhanVien_CongDoan.class).getResultList();
				tr.commit();
				
				return ds;
			} catch (Exception e) {
				tr.rollback();
			}
			return null;
		}
		@Override
		public boolean upDateCongNhan(NhanVien_CongDoan nv) {
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
		public boolean kiemTraThanhToanCN(String maNV,int thang, int nam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<NhanVien_CongDoan> ds = null;
			int trangThai = 1;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From NhanVien_CongDoan where tinhTrang like '"+trangThai+"' and maNhanVien like '"+maNV+"' and thang like '"+thang+"' and nam like '"+nam+"' ",NhanVien_CongDoan.class).getResultList();
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
		public boolean kiemTraThanhToanNV(String maNV,int thang, int nam) {
			Session session = sessionFactory.getCurrentSession();
			
			Transaction tr = session.getTransaction();
			List<ChamCongNVHC> ds = null;
			int trangThai = 1;
			try {
				tr.begin();
				ds =  session.createNativeQuery("Select * From ChamCongNVHC where tinhTrang like '"+trangThai+"' and maNV like '"+maNV+"' and thang like '"+thang+"' and nam like '"+nam+"' ",ChamCongNVHC.class).getResultList();
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
