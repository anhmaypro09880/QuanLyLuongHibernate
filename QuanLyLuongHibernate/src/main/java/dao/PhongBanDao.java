package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.PhongBan;

public interface PhongBanDao  extends Remote {
	public	PhongBan getPBten(String tenPB) throws RemoteException;
}
