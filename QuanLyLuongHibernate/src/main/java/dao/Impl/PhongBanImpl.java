package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.PhongBanDao;
import dao.sessionFactory;
import entity.PhongBan;

public class PhongBanImpl extends UnicastRemoteObject implements PhongBanDao,sessionFactory{
	
	public PhongBanImpl() throws RemoteException {
//		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PhongBan getPBten(String tenPB) throws RemoteException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		List<PhongBan> pb = null;
		try {
			tr.begin();
			pb=  session.createNativeQuery("select * from PhongBan where tenPB =N'"+tenPB+"'",PhongBan.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tr.rollback();
		}
		return pb.get(0);
	}
	
}	
