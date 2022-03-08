package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChamCongNVHC;
import entity.NhanVien_CongDoan;

public interface TinhLuongDao extends Remote {
	public	List<NhanVien_CongDoan> getNhanVien_CongDoan(int tuthang,int tunam ,int denthang,int dennam) throws RemoteException;
	public	List<NhanVien_CongDoan> TimKiem(int fromthang,int fromnam,int tothang,int tonam ,String tk) throws RemoteException;
	public	boolean TinhLuong(String ma , int thang, int nam) throws RemoteException;
	public	List<ChamCongNVHC> getChamCongNVHC(int tuthang,int tunam ,int denthang,int dennam) throws RemoteException;
	public	List<ChamCongNVHC> TimKiemNV(int fromthang,int fromnam,int tothang,int tonam ,String tk) throws RemoteException;
	public	boolean TinhLuongNV(String ma , int thang , int nam) throws RemoteException;
}
