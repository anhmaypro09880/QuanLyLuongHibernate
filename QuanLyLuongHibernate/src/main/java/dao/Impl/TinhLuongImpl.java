package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhanVienDao;
import dao.TinhLuongDao;
import dao.sessionFactory;
import entity.ChamCongNVHC;
import entity.NhanVien_CongDoan;

public class TinhLuongImpl extends UnicastRemoteObject implements TinhLuongDao,sessionFactory {
	
	public TinhLuongImpl() throws RemoteException {
//		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<NhanVien_CongDoan> getNhanVien_CongDoan(int tuthang,int tunam ,int denthang,int dennam) throws RemoteException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<NhanVien_CongDoan> ds = null;
		try {
			tr.begin();
			ds =  session.createNativeQuery("select * from NhanVien_CongDoan where (nam> "+tunam+" or (nam = "+tunam+" and thang >="+tuthang+")) and(nam <"+dennam+" or(nam = "+dennam+" and thang <="+denthang+"))",NhanVien_CongDoan.class).getResultList();
			
			tr.commit();
		
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return ds;
	}

	@Override
	public List<NhanVien_CongDoan> TimKiem(int fromthang,int fromnam,int tothang,int tonam ,String tk) throws RemoteException {
		// TODO Auto-generated method stub
//		where (nam> '"+fromnam+"' or (thang>= '"+fromthang+"' and nam = '"+fromnam+"')) and (nam<'"+tonam+"' or (thang <= '"+tothang+"' and nam = '"+tonam+"')) and maNhanVien like N'%"+tk+"%' "
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<NhanVien_CongDoan> ds = null;
		try {
			tr.begin();
			ds =  session.createNativeQuery("select * from NhanVien_CongDoan where (nam> '"+fromnam+"' or (thang>= '"+fromthang+"' and nam = '"+fromnam+"')) and (nam<'"+tonam+"' or (thang <= '"+tothang+"' and nam = '"+tonam+"')) and maNhanVien like N'%"+tk+"%' ",NhanVien_CongDoan.class).getResultList();
			
			tr.commit();
//			NhanVienDao nv_dao = new NhanVienImpl();
//			List<String> dsnv = nv_dao.GetMaTheoTen(tk);
//			for(int i = 0 ;i < dsnv.size() ; i++)
//			{
//				try {
//					tr.begin();
//					List<NhanVien_CongDoan> ds1 =  session.createNativeQuery("select * from NhanVien_CongDoan where maNhanVien = '"+dsnv.get(i)+"'",NhanVien_CongDoan.class).getResultList();
//					ds.add(ds1.get(0));
//					tr.commit();
//				} catch (Exception e) {
//					// TODO: handle exception
//					tr.rollback();
//				}
//			}
			
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return ds;
	}

	@Override
	public boolean TinhLuong(String ma , int thang , int nam) throws RemoteException {
	
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<NhanVien_CongDoan> ds = null;
		
		try {
			tr.begin();
			String sql = "UPDATE NhanVien_CongDoan set tinhTrang = true where maNhanVien = '"+ma+"' and thang = "+thang+" and nam = "+nam+"" ;
			Query query = session.createQuery(sql);
			int result = query.executeUpdate();
			tr.commit();
			return true;
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return false;
	}

	@Override
	public List<ChamCongNVHC> getChamCongNVHC(int tuthang, int tunam, int denthang, int dennam) throws RemoteException {
	
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<ChamCongNVHC> ds = null;
		try {
			tr.begin();
			ds =  session.createNativeQuery("select * from ChamCongNVHC where (nam> "+tunam+" or (nam = "+tunam+" and thang >="+tuthang+")) and(nam <"+dennam+" or(nam = "+dennam+" and thang <="+denthang+"))",ChamCongNVHC.class).getResultList();
			
			tr.commit();
		
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return ds;
	}

	@Override
	public List<ChamCongNVHC> TimKiemNV(int fromthang,int fromnam,int tothang,int tonam ,String tk) throws RemoteException {
	
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<ChamCongNVHC> ds = null;
		try {
			tr.begin();
			ds =  session.createNativeQuery("select * from ChamCongNVHC where (nam> '"+fromnam+"' or (thang>= '"+fromthang+"' and nam = '"+fromnam+"')) and (nam<'"+tonam+"' or (thang <= '"+tothang+"' and nam = '"+tonam+"')) and maNV like N'%"+tk+"%' ",ChamCongNVHC.class).getResultList();
			
			tr.commit();
			
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return ds;
	}

	@Override
	public boolean TinhLuongNV(String ma, int thang, int nam) throws RemoteException {
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction tr = session.getTransaction();
		List<NhanVien_CongDoan> ds = null;
		
		try {
			tr.begin();
			String sql = "UPDATE ChamCongNVHC set tinhTrang = true where maNV = '"+ma+"' and thang = "+thang+" and nam = "+nam+"" ;
			Query query = session.createQuery(sql);
			int result = query.executeUpdate();
			tr.commit();
			return true;
			
		} catch (Exception e) {
			tr.rollback();
		}
	
		return false;
	}

}
