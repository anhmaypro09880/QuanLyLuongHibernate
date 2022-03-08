package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.NhanVien_CongDoan;

public interface DuLieuNhanVienDao  extends Remote {

	public	List<ChamCongNVHC> getallNhanVien() throws RemoteException;

	public	boolean themNhanVien(ChamCongNVHC nv)throws RemoteException;

	public	List<ChamCongNVHC> getNVCuoi()throws RemoteException;

	public	ChamCongNVHC getTNK(String manv, int thang, int nam)throws RemoteException;

	public	boolean upDateNhanVien(ChamCongNVHC nv)throws RemoteException;
	
	public	 List<ChamCongNVHC> getThangNam(int tuthang,int tunam ,int denthang,int dennam)throws RemoteException;

	public	List<CongDoan> getCongDoan(String maCD)throws RemoteException;

	public	List<ChamCongNVHC> getmaBangChamCong(String maNV, int thang, int nam)throws RemoteException;

	public	List<ChamCongNVHC> timKiem(String tk, int fromthang, int fromnam, int tothang, int tonam)throws RemoteException;

	public	List<ChamCongNVHC> getLuongNhanVien(String maNV)throws RemoteException;

	public	boolean kiemTraNhanVien(String maNV)throws RemoteException;


	public	boolean kiemTraNghiViec(String maNV)throws RemoteException;

	public	boolean rangBuocThangNam(String ma, int thang, int nam)throws RemoteException;

	public	boolean rangBuocThangNamCN(String ma, int thang, int nam)throws RemoteException;

}