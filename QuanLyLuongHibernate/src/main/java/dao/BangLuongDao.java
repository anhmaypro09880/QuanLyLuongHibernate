package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.BangLuong;

public interface BangLuongDao extends Remote  {
	String GetMaCuoiBL() throws RemoteException;
	public	boolean ThemBangLuong(BangLuong bl) throws RemoteException;
	public	List<BangLuong> GetBLall(int nam) throws RemoteException;
	public	List<BangLuong> GetBLNVall(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy1(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy2(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy3(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy4(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy1NV(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy2NV(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy3NV(int nam) throws RemoteException;
	public	List<BangLuong> GetQuy4NV(int nam) throws RemoteException;
	public	List<BangLuong> GetBLCNTheoThang(int nam,int thang) throws RemoteException;
	public	List<BangLuong> GetBLNVTheoThang(int nam,int thang) throws RemoteException;
	

	
}
