package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;
import entity.SanPham;
public interface SanPhamDao  extends Remote {
	public		boolean ThemSanPham(SanPham sp) throws RemoteException;
	public	List<SanPham> getSPall() throws RemoteException;
	public	SanPham getSPCuoi() throws RemoteException;
	public	boolean SuaSP(SanPham sp) throws RemoteException;
	public	List<SanPham> getmaSp(String maSP) throws RemoteException;

}
