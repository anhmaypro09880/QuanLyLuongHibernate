package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CongDoan;
import entity.NhanVien_CongDoan;
import entity.SanPham;

public interface DuLieuCongNhanDao  extends Remote {

	public List<NhanVien_CongDoan> getallCongNhan() throws RemoteException;

	public	NhanVien_CongDoan getTNK(String manv, int thang, int nam) throws RemoteException;

	public	List<SanPham> getTenSanPham(String maSP)throws RemoteException;

	public	List<SanPham> getallTenSanPham()throws RemoteException;

	public	List<CongDoan> getTenCongDoan(String maSP)throws RemoteException;

	public	List<SanPham> getMaSpTheoTenSP(String tenSP)throws RemoteException;

	public	List<NhanVien_CongDoan> timKiem(String tk, int fromthang, int fromnam, int tothang, int tonam) throws RemoteException;

	public	boolean themCongNhan(NhanVien_CongDoan nv) throws RemoteException;

	public	CongDoan getCongDoan(String maSP, String tenCD) throws RemoteException;

	public List<NhanVien_CongDoan> getThangNam(int tuthang, int tunam, int denthang, int dennam) throws RemoteException;

	public	List<NhanVien_CongDoan> getLuongCongNhan(String maNV) throws RemoteException;

	public	boolean upDateCongNhan(NhanVien_CongDoan nv) throws RemoteException;

	public	boolean kiemTraThanhToanCN(String maNV,int thang, int nam) throws RemoteException;

	public	boolean kiemTraThanhToanNV(String maNV, int thang, int nam) throws RemoteException;

}