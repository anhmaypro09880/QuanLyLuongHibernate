package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;
import entity.PhongBan;

public interface NhanVienDao  extends Remote {
	public	boolean ThemNhanVien(NhanVien nv) throws RemoteException;
	public	NhanVien GetNVTheoMa(String ma) throws RemoteException;
	public	List<NhanVien> getNVDangLam() throws RemoteException;
	public	List<NhanVien> getNVall() throws RemoteException;
	public	NhanVien getNVCuoiLD() throws RemoteException;
	public	NhanVien getNVCuoiHC() throws RemoteException;
	public	boolean capNhatTrangThai(String maNV) throws RemoteException;
	public	boolean SuaNV(NhanVien nv) throws RemoteException;
	public	List<NhanVien> getLCN() throws RemoteException;
	public	List<NhanVien> getLNV() throws RemoteException;
	public	List<NhanVien> getTimNVtheoma(String maNV) throws RemoteException;
	public	List<NhanVien> getNVNghiviec() throws RemoteException;
	public	boolean doiMatKhau(NhanVien nv)  throws RemoteException;;
	public	PhongBan getPhongBan(String ma) throws RemoteException;;
	
	

}
