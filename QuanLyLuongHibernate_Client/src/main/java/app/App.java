package app;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dao.BangLuongDao;
import dao.CongDoanDao;
import dao.DangNhapDao;
import dao.DuLieuCongNhanDao;
import dao.DuLieuNhanVienDao;
import dao.NhanVienDao;
import dao.PhongBanDao;
import dao.SanPhamDao;
import dao.TinhLuongDao;

import entity.NhanVien_CongDoan;
import gui.Login_GUI;

public class App {
	public static DuLieuCongNhanDao cnDao;
	public static DangNhapDao dnDao;
	public static NhanVienDao nvDao;
	public static DuLieuNhanVienDao dlieu ;
	public static TinhLuongDao tlDao ;
	public static CongDoanDao cdDao ;
	public static SanPhamDao spDao  ;
	public static BangLuongDao blDao ;
	public static PhongBanDao pbDao ;

	public static void main(String[] args) {
		
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy","policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String ip = "rmi://localhost:1124";
			cnDao = (DuLieuCongNhanDao) Naming.lookup("rmi://localhost:1124/cnDao");
			dnDao = (DangNhapDao) Naming.lookup("rmi://localhost:1124/dnDao");
			nvDao = (NhanVienDao) Naming.lookup("rmi://localhost:1124/nvienDao");
			dlieu = (DuLieuNhanVienDao) Naming.lookup("rmi://localhost:1124/nvDao");
			tlDao = (TinhLuongDao) Naming.lookup("rmi://localhost:1124/tlDao");
			cdDao = (CongDoanDao) Naming.lookup("rmi://localhost:1124/cdDao");
			spDao = (SanPhamDao) Naming.lookup("rmi://localhost:1124/spDao");
			blDao = (BangLuongDao) Naming.lookup("rmi://localhost:1124/blDao");
			pbDao = (PhongBanDao) Naming.lookup("rmi://localhost:1124/pbDao");
			
			System.out.println("connet");
//			List<NhanVien_CongDoan> cd = cnDao.getallCongNhan();
//			cd.forEach(p->System.out.println(p));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		new Login_GUI().setVisible(true);
	}
}
