package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.NhanVien;

public interface DangNhapDao  extends Remote {
	public	boolean KtraQuanly( String username) throws RemoteException;
	public	boolean KtraDangNhap (String username, String password) throws RemoteException;
}
