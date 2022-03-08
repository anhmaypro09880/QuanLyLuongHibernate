package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CongDoan;
import entity.SanPham;

public interface CongDoanDao extends Remote {
	public	boolean ThemCongDoan(CongDoan cd) throws RemoteException;
	public	CongDoan getCDCuoi() throws RemoteException;
	public	List<CongDoan> getCongDoanTheoMaSp(String maSP) throws RemoteException;;
	public	boolean SuaCD(CongDoan cd) throws RemoteException;
}
