package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import app.App;
import dao.BangLuongDao;

//import dao.ThongKe_DAO;
import entity.BangLuong;
import entity.NhanVien;
import entity.PhongBan;
import entity.ThuNhapKhac;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;


public class ThongKe_GUI extends JPanel implements ActionListener  {

	private static final long serialVersionUID = 1L;
	private JButton btnXuatFile;
	private JButton btnLamMoi;			
	private JComboBox<String> cboTKThangQuy;	
	private JPanel pnlBieuDo;	
	private ButtonGroup group;
	
	private JComboBox<String> cboNam;
	private JRadioButton rdbTatCa;
	private JRadioButton rdbQuy;
	private JRadioButton rdbThang;
	private JLabel lblTongSo;
	private JComboBox<String> cboTKTheo;
	private JLabel lblTongLuong;
	private JButton btnTongLuong;

	private BangLuongDao bl_dao = App.blDao;
	
	private DefaultTableModel modelDS;
	private JTable tbDanhSach;
	
//	private ThongKe_DAO thongKeDao = new ThongKe_DAO();
	private ArrayList<BangLuong> listBL ;
	
	private JPanel panelMenu;
	private JButton btnCN;
	private JButton btnNV;
	private JLabel lblTong;
	private int kt =0;
	private int btn=2;

	public ThongKe_GUI() throws RemoteException {	
		setBackground(Color.WHITE);
		listBL = new ArrayList<BangLuong>();
		
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		
		pnlBieuDo = new JPanel();
		pnlBieuDo.setBackground(Color.WHITE);
		pnlBieuDo.setBounds(540, 70, 1180, 555);
		add(pnlBieuDo);
		pnlBieuDo.setLayout(null);
		pnlBieuDo.setBorder(BorderFactory.createTitledBorder(""));
		
		JLabel lblTitle = new JLabel("Danh sách thống kê lương");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitle.setBounds(760, 25, 400, 40);
		add(lblTitle);

		
		
		
		modelDS = new DefaultTableModel(new String[] { "Mã nhân viên", "Tên nhân viên",
				"Tháng", "Năm",  "Lương (VND)"}, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(3, 25, 980, 530);
		pnlBieuDo.add(scrollPane1);
		pnlBieuDo.setBorder(BorderFactory.createTitledBorder("Table"));
		tbDanhSach = new JTable(modelDS);
		scrollPane1.setViewportView(tbDanhSach);
		
		tbDanhSach.getTableHeader().setBackground(new Color(146, 200, 240));
		tbDanhSach.getTableHeader().setForeground(new Color(255, 255, 255));
		tbDanhSach.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		
		
		tbDanhSach.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbDanhSach.getColumnModel().getColumn(1).setPreferredWidth(250);
		tbDanhSach.getColumnModel().getColumn(2).setPreferredWidth(20);
		
//		tbDanhSach.getColumnModel().getColumn(5).setPreferredWidth(80);
//		tbDanhSach.getColumnModel().getColumn(6).setPreferredWidth(80);
		tbDanhSach.setRowHeight(tbDanhSach.getRowHeight()+15);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tbDanhSach.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tbDanhSach.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tbDanhSach.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(39, 93, 1249, 8);
		pnlBieuDo.add(separator1);

		
		lblTongSo = new JLabel("Tổng số:");
		lblTongSo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSo.setBounds(20, 575, 88, 23);
		pnlBieuDo.add(lblTongSo);
		
		lblTongSo = new JLabel("0");
		lblTongSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSo.setBounds(103, 472, 57, 20);
		pnlBieuDo.add(lblTongSo);
		
		lblTongSo.setText(listBL.size()+"");
		
		//-----------------
		panelMenu = new JPanel();
		panelMenu.setBackground(Color.RED);
		panelMenu.setBounds(0,0, 190, 630);
		add(panelMenu);
		//------------------
		JPanel pnlCN = new JPanel();
		pnlCN.setBackground(Color.WHITE);
		pnlCN.setBounds(200, 70, 330, 555);
		add(pnlCN);
		pnlCN.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pnlCN.setLayout(null);
		
		
		JLabel lblTKTheo = new JLabel("Thống kê theo:");
		lblTKTheo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTKTheo.setBounds(20, 80, 138, 44);
//		pnlCN.add(lblTKTheo);
		
		cboTKTheo = new JComboBox<String>();
		cboTKTheo.setBounds(20, 125, 138, 30);
//		pnlCN.add(cboTKTheo);
		cboTKTheo.addItem("Tất cả");
		cboTKTheo.addItem("Công nhân");
		cboTKTheo.addItem("Nhân viên");
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBounds(20, 200, 138, 44);
		btnLamMoi.setFocusPainted(false);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLamMoi.setBackground(new Color(228, 237, 225));
		btnLamMoi.setIcon(new ImageIcon(ThongKe_GUI.class.getResource("/images/lam_moi.png")));
		pnlCN.add(btnLamMoi);
//		tbDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lương công nhân"));
		//-------------------
		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNam.setBounds(20, 30, 50, 24);
		pnlCN.add(lblNam);
		
		cboNam = new JComboBox<String>();
		cboNam.setBounds(70, 30, 200, 24);
		pnlCN.add(cboNam);
		
		for(int i = LocalDateTime.now().getYear(); i> LocalDateTime.now().getYear() - 10; i--) {
			cboNam.addItem("" +i);
		}
		
		
		rdbTatCa = new JRadioButton("");
		rdbTatCa.setBackground(Color.WHITE);
		rdbTatCa.setSelected(true);
		rdbTatCa.setBounds(60, 72, 20, 20);
		pnlCN.add(rdbTatCa);
		
		
		JLabel lblTatCa = new JLabel("Tất cả");
		lblTatCa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTatCa.setBounds(20, 68, 50, 24);
		pnlCN.add(lblTatCa);
		
		rdbQuy = new JRadioButton("");
		rdbQuy.setBackground(Color.WHITE);
		rdbQuy.setBounds(180, 72,  20, 20);
		pnlCN.add(rdbQuy);
		
		JLabel lblThang = new JLabel("Tháng");
		lblThang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThang.setBounds(85, 68, 50, 24);
		pnlCN.add(lblThang);

		rdbThang = new JRadioButton("");
		rdbThang.setBackground(Color.WHITE);
		rdbThang.setBounds(125, 72, 20, 20);
		pnlCN.add(rdbThang);
		
		group = new ButtonGroup();
		group.add(rdbQuy);
		group.add(rdbThang);
		group.add(rdbTatCa);
		
		JLabel lblQuy= new JLabel("Quý");
		lblQuy.setBounds(150, 68, 50, 24);
		pnlCN.add(lblQuy);
		lblQuy.setFont(new Font("Tahoma", Font.BOLD, 12));

		cboTKThangQuy = new JComboBox<String>();
		cboTKThangQuy.setBounds(220, 68, 50, 24);
		pnlCN.add(cboTKThangQuy);
		
		//--------------------
		btnXuatFile = new JButton("Xuất File");
		btnXuatFile.setBounds(170, 200, 138, 44);
		btnXuatFile.setFocusPainted(false);
		btnXuatFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXuatFile.setBackground(new Color(228, 237, 225));
		btnXuatFile.setIcon(new ImageIcon(ThongKe_GUI.class.getResource("/images/thong_ke.png")));
		pnlCN.add(btnXuatFile);
		
		btnTongLuong = new JButton("Tổng lương");
		btnTongLuong.setBounds(20, 380, 138, 44);
		btnTongLuong.setFocusPainted(false);
		btnTongLuong.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnTongLuong.setBackground(new Color(228, 237, 225));
		btnTongLuong.setIcon(new ImageIcon(ThongKe_GUI.class.getResource("/images/dollarTongLuong.png")));
//		pnlCN.add(btnTongLuong);
		
		lblTongLuong = new JLabel("0.0");
		lblTongLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongLuong.setBounds(150, 105, 237, 44);
		lblTong = new JLabel("Tổng lương : ");
		lblTong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTong.setBounds(20, 105, 237, 44);
		pnlCN.add(lblTong);
		
		pnlCN.add(lblTongLuong);
		
		

		rdbThang.addActionListener(this);
		rdbQuy.addActionListener(this);
		rdbTatCa.addActionListener(this);
		
		cboNam.addActionListener(this);
		cboTKTheo.addActionListener(this);
		cboTKThangQuy.addActionListener(this);
		
		btnLamMoi.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnTongLuong.addActionListener(this);
		panelMenu.setLayout(null);
		btnCN = new JButton("Công Nhân");
		btnCN.setBounds(30, 90, 159, 60);
		btnCN.setBackground(new Color(51,153,255));
		btnCN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelMenu.add(btnCN);
		btnCN.setForeground(new java.awt.Color(255, 255, 255));
		btnNV = new JButton("Nhân Viên");
		btnNV.setBackground(new java.awt.Color(40,57,88));
		btnNV.setBounds(30, 160, 159, 60);
		btnNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNV.setForeground(new java.awt.Color(255, 255, 255));
		panelMenu.add(btnNV);
		panelMenu.setBackground(new java.awt.Color(23,35,51));
		btnCN.setSelected(true);
		
//		tbDanhSach.sett
	
		btnNV.addActionListener(this);
		btnCN.addActionListener(this);
		cboNam.setSelectedIndex(0);
		this.btn =2;
		kt=2;
		rdbThang.setSelected(true);
		cboTKThangQuy.removeAllItems();
		for(int i = 1; i < 13; i++)
			cboTKThangQuy.addItem(""+ i);
		cboTKThangQuy.setSelectedIndex(LocalDateTime.now().getMonthValue()-1);
	
		List<BangLuong> bl = null;
		int chon =0;
		try {
			 chon = Integer.parseInt(cboTKThangQuy.getSelectedItem().toString());
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		try {
			bl =  bl_dao.GetBLCNTheoThang(Integer.parseInt(cboNam.getSelectedItem().toString()),chon);
		} catch (NumberFormatException | RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		xoaAllModel();
		DocDuLieuVaoModelDS(bl);
		}
		

	

//	private void xoaAllModel() {
//		modelDS.getDataVector().removeAllElements();
//		tbDanhSach.setModel(modelDS);
//		
//
//	}
		
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
	
		if(o.equals(rdbQuy)) {
		
//			cboTKThangQuy.removeAllItems();
//			for (int i =1; i<=4; i++) 
//				cboTKThangQuy.addItem("" +i);
//			if (cboNam.getSelectedIndex() == 0)
//				listBL = new ArrayList<BangLuong>();
//			else {
//				int check;
//				if(rdbTatCa.isSelected())
//					check = 0;
//				else if (rdbQuy.isSelected())
//					check = 1;
//				else
//					check = 2;
//				
////				listBL = thongKeDao.getAllBangLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
////						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
////								cboTKTheo.getSelectedIndex());
//				
//			}
//			xoaAllModel();
//			modelDS.getDataVector().removeAllElements();
//			DocDuLieuVaoModelDS();
//			modelDS.fireTableDataChanged();
//			kt = tru;
			kt =1;
//			JOptionPane.showMessageDialog(null,btn);
			cboTKThangQuy.removeAllItems();
			for (int i =1; i<=4; i++) 
				cboTKThangQuy.addItem("" +i);
//			if(btn==2)
//			{
////				JOptionPane.showMessageDialog(null,"ok");
//				
//				BangLuongDao bl_dao = new BangLuongImpl();
//				List<BangLuong> bl = null;
//				try {
//					bl =  bl_dao.GetQuy1(Integer.parseInt(cboNam.getSelectedItem().toString()));
//				} catch (NumberFormatException | RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				System.out.println(bl);
//			}
		} 
		else if(o.equals(btnNV))
		{
			btnNV.setBackground(new Color(51,153,255));
//			tbDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lương nhân viên"));
			btnCN.setBackground(new java.awt.Color(40,57,88));
			this.btn =1;
			kt=2;
			rdbThang.setSelected(true);
			cboTKThangQuy.removeAllItems();
			for(int i = 1; i < 13; i++)
				cboTKThangQuy.addItem(""+ i);
			cboTKThangQuy.setSelectedIndex(LocalDateTime.now().getMonthValue()-1);
		}
		else if(o.equals(btnCN))
		{
			btnCN.setBackground(new Color(51,153,255));
//			tbDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lương công nhân"));
			btnNV.setBackground(new java.awt.Color(40,57,88));
			this.btn =2;
			kt=2;
			rdbThang.setSelected(true);
			cboTKThangQuy.removeAllItems();
			for(int i = 1; i < 13; i++)
				cboTKThangQuy.addItem(""+ i);
			cboTKThangQuy.setSelectedIndex(LocalDateTime.now().getMonthValue()-1);
		
			List<BangLuong> bl = null;
			int chon =0;
			try {
				 chon = Integer.parseInt(cboTKThangQuy.getSelectedItem().toString());
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			try {
				bl =  bl_dao.GetBLCNTheoThang(Integer.parseInt(cboNam.getSelectedItem().toString()),chon);
			} catch (NumberFormatException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			xoaAllModel();
			DocDuLieuVaoModelDS(bl);
		}
		else if(o.equals(rdbThang)) {
			kt=2;
			cboTKThangQuy.removeAllItems();
			for(int i = 1; i < 13; i++)
				cboTKThangQuy.addItem(""+ i);
			cboTKThangQuy.setSelectedIndex(LocalDateTime.now().getMonthValue()-1);
//			if(cboNam.getSelectedIndex() == 0)
//				listBL = new ArrayList<BangLuong>();
//			else {
//				int check;
//				if(rdbTatCa.isSelected())
//					check = 0;
//				else if(rdbQuy.isSelected())
//					check = 1;
//				else
//					check = 2;
				
//				listBL = thongKeDao.getAllBangLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
//						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
//								cboTKTheo.getSelectedIndex());
				
//			}
			
//			xoaAllModel();
////			DocDuLieuVaoModelDS();
//			modelDS.fireTableDataChanged();
		} else if(o.equals(rdbTatCa)) {
		
			cboTKThangQuy.removeAllItems();
//			int check;
//			if(rdbTatCa.isSelected())
//				check = 0;
//			else if(rdbQuy.isSelected())
//				check = 1;
//			else 
//				check = 2;
//			if(cboNam.getSelectedIndex() == 0)
//				listBL = new ArrayList<BangLuong>();
//			else
////				listBL = thongKeDao.getAllBangLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
////						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
////								cboTKTheo.getSelectedIndex());
//			xoaAllModel();
////			DocDuLieuVaoModelDS();
//			modelDS.fireTableDataChanged();
//			lblTongSo.setText("" +listBL.size());
			if(btn==2)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetBLall(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
//				modelDS.getDataVector().removeAllElements();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==1)
			{
			
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetBLNVall(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
//				modelDS.getDataVector().removeAllElements();
				DocDuLieuVaoModelDS(bl);
			}
			
			
		} else if(o.equals(cboTKThangQuy)) {
//			if(kt) {
//				if(cboNam.getSelectedIndex() == 0) {
//					listBL = new ArrayList<BangLuong>();
//				}else {
//					int check;
//					if(rdbTatCa.isSelected())
//						check = 0;
//					else if(rdbQuy.isSelected())
//						check = 1;
//					else
//						check = 2;
//					
////					listBL = thongKeDao.getAllBangLuong(Integer.parseInt(cboNam.getSelectedItem().toString()),
////						rdbTatCa.isSelected() ? 1 : Integer.parseInt(cboTKThangQuy.getSelectedItem().toString()), check,
////								cboTKTheo.getSelectedIndex());
//				}
//				
//				setDataTable(listBL);
//				xoaAllModel();
//				DocDuLieuVaoModelDS();
//				modelDS.fireTableDataChanged();
//				lblTongSo.setText("" + listBL.size());
//				
//
//			}
			if(btn==2&&kt==1 &&cboTKThangQuy.getSelectedIndex()==0)
			{
			
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy1(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
//				modelDS.getDataVector().removeAllElements();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==2&&kt==1 &&cboTKThangQuy.getSelectedIndex()==1)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy2(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==2&&kt==1 &&cboTKThangQuy.getSelectedIndex()==2)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy3(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==2&&kt==1 &&cboTKThangQuy.getSelectedIndex()==3)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy4(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			
			if(btn==2&&kt==2)
			{
				
				List<BangLuong> bl = null;
				int chon =0;
				try {
					 chon = Integer.parseInt(cboTKThangQuy.getSelectedItem().toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				try {
					bl =  bl_dao.GetBLCNTheoThang(Integer.parseInt(cboNam.getSelectedItem().toString()),chon);
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
				
			}
			if(btn==1&&kt==2)
			{
				
				List<BangLuong> bl = null;
				int chon =0;
				try {
					 chon = Integer.parseInt(cboTKThangQuy.getSelectedItem().toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				try {
					bl =  bl_dao.GetBLNVTheoThang(Integer.parseInt(cboNam.getSelectedItem().toString()),chon);
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			
			
			if(btn==1&&kt==1 &&cboTKThangQuy.getSelectedIndex()==0)
			{
			
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy1NV(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
//				modelDS.getDataVector().removeAllElements();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==1&&kt==1 &&cboTKThangQuy.getSelectedIndex()==1)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy2NV(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==1&&kt==1 &&cboTKThangQuy.getSelectedIndex()==2)
			{
				
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy3NV(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			if(btn==1&&kt==1 &&cboTKThangQuy.getSelectedIndex()==3)
			{
		
				List<BangLuong> bl = null;
				try {
					bl =  bl_dao.GetQuy4NV(Integer.parseInt(cboNam.getSelectedItem().toString()));
				} catch (NumberFormatException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				xoaAllModel();
				DocDuLieuVaoModelDS(bl);
			}
			
//			if(kt==1)
//			{	
//				int row = Integer.parseInt(cboTKThangQuy.getSelectedItem().toString());
//				if(row ==1)
//				{
//					BangLuongDao bl_dao = new BangLuongImpl();
//					List<BangLuong> bl = null;
//					try {
//						bl =  bl_dao.GetQuy1(Integer.parseInt(cboNam.getSelectedItem().toString()));
//					} catch (NumberFormatException | RemoteException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
////					xoaAllModel();
////					DocDuLieuVaoModelDS(bl);
//				}
//				if(row ==2)
//				{
//					BangLuongDao bl_dao = new BangLuongImpl();
//					List<BangLuong> bl = null;
//					try {
//						bl =  bl_dao.GetQuy2(Integer.parseInt(cboNam.getSelectedItem().toString()));
//					} catch (NumberFormatException | RemoteException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
////					xoaAllModel();
////					DocDuLieuVaoModelDS(bl);
//				}
//				if(row ==3)
//				{
//					BangLuongDao bl_dao = new BangLuongImpl();
//					List<BangLuong> bl = null;
//					try {
//						bl =  bl_dao.GetQuy3(Integer.parseInt(cboNam.getSelectedItem().toString()));
//					} catch (NumberFormatException | RemoteException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
////					xoaAllModel();
////					DocDuLieuVaoModelDS(bl);
//				}
//				if(row ==4)
//				{
//					BangLuongDao bl_dao = new BangLuongImpl();
//					List<BangLuong> bl = null;
//					try {
//						bl =  bl_dao.GetQuy4(Integer.parseInt(cboNam.getSelectedItem().toString()));
//					} catch (NumberFormatException | RemoteException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
////					xoaAllModel();
////					DocDuLieuVaoModelDS(bl);
//				}
//			}
			
		} else if(o.equals(btnXuatFile)) {
			
			if(tbDanhSach.getRowCount() == 0) {
				JOptionPane.showConfirmDialog(null, "Chưa có nội dung để xuất file");
				return;
			}
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.addChoosableFileFilter(new FileFilter() {
				
				@Override
				public String getDescription() {
					return "Excel file (*.xls, *xlsx)";
				}
				
				@Override
				public boolean accept(File f) {
					if(f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".xls")
								|| f.getName().toLowerCase().endsWith(".xlsx");
					}
					
				}
			});
			
			int i = fileChooser.showSaveDialog(this);
			if(i == 0) {
				String path = fileChooser.getSelectedFile().getAbsolutePath();
				
				if(!path.matches("(.)+(\\.xls|\\.xlsx)$")) {
					path += ".xlsx";
				}
				int xacNhanLai = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất file Excel", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if(xacNhanLai == JOptionPane.YES_NO_OPTION) {
					boolean t = ghiRaFileExcel(path);
					if(t) {
						JOptionPane.showConfirmDialog(null, "Xuất file thành công");
						xacNhanLai = JOptionPane.showConfirmDialog(null, "Bạn có muốn xem file", "Thông báo", JOptionPane.YES_NO_OPTION);
						if(xacNhanLai == JOptionPane.YES_OPTION) 
							try {
								Desktop.getDesktop().open(new File(fileChooser.getSelectedFile().getParent()));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					} else
						JOptionPane.showMessageDialog(null, "Không xuất được file");
				}
				
			}
		} else if(o.equals(btnLamMoi)) {
			lamMoi();
			
		} else if(o.equals(btnTongLuong)) {
			int numrow = tbDanhSach.getRowCount();
			double total = 0;
			
			
			DecimalFormat formatt = new DecimalFormat("###,###,###.0");
			
			for(int i=0; i < numrow; i++) {
				double val = Double.valueOf(tbDanhSach.getValueAt(i, 5).toString());
				total += val;
				
			}
			
			
			Locale localeVN = new Locale("vi", "VN");
			
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
			String str = currencyVN.format(total);
			
			lblTongLuong.setText(str);
			
		}
		
	}

	private void lamMoi() {
		cboNam.setSelectedIndex(0);
		tbDanhSach.clearSelection();
		lblTongSo.setText("0");
		lblTongLuong.setText("0.0");
		cboTKTheo.setSelectedIndex(0);;
	}

	private boolean ghiRaFileExcel(String path) {
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sh = workbook.createSheet("Sheet1");
		String header[] = {"Mã nhân viên", "Tên nhân viên", "Tháng",
				"Năm", "Lương"};
		
		Row rowHeader = sh.createRow(0);
		for(int i = 0; i< header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		int numberRow = 1;
		for (int i = 0 ;i<tbDanhSach.getRowCount();i++) {
			Row row = sh.createRow(numberRow++);
			row.createCell(0).setCellValue(XoaKhoangTrang(tbDanhSach.getValueAt(i, 0).toString()) );
			row.createCell(1).setCellValue(XoaKhoangTrang(tbDanhSach.getValueAt(i, 1).toString()) );
			row.createCell(2).setCellValue(XoaKhoangTrang(tbDanhSach.getValueAt(i, 2).toString()) );
			row.createCell(3).setCellValue(XoaKhoangTrang(tbDanhSach.getValueAt(i, 3).toString()) );
			row.createCell(4).setCellValue(XoaKhoangTrang(tbDanhSach.getValueAt(i, 4).toString()) );
		}
		
		for(int i = 0; i < header.length; i++)
			sh.autoSizeColumn(i);
		
		try {
			FileOutputStream f = new FileOutputStream(path);
			workbook.write(f);
			f.close();
			workbook.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public String XoaKhoangTrang(String text)
    {	
    	String chuoi = "";
    	
    	for (int i = 0; i< text.length();i++)
    	{	char ca = text.charAt(i);
    		if(ca != '.' )
    		{
    			chuoi+=String.valueOf(text.charAt(i));
    		}
    	}
    	return chuoi;
    }
	public void setDataTable(List<BangLuong>list) {
		List<BangLuong>salarys= new ArrayList<>();
		salarys=list;
		DefaultTableModel tableModel;
		tbDanhSach.getModel();
		tableModel=(DefaultTableModel)tbDanhSach.getModel();
		tableModel.setRowCount(0);
//		salarys.forEach(salary->{
//			System.out.println(salary.getMaBL());
//			System.out.println(salary.getNhanVien().getMaNV());
//			System.out.println(salary.getThang());
//			System.out.println(salary.getNam());
//			System.out.println(salary.getLuong());
//		});
		salarys.forEach(info->{
			String code=info.getNhanVien().getMaNV();
			String name=info.getNhanVien().getTenNV();
		
			tableModel.addRow(new Object[] {
					info.getMaBL(),code,name,info.getThang(),info.getNam(),info.getLuong()
			});
		});
		
	}
	public void DocDuLieuVaoModelDS(List<BangLuong> ds) {
		modelDS.getDataVector().removeAllElements();
		double tong=0;
		if(ds.size()==0)
		{
			modelDS.addRow(new Object[] {});
		}
		else
		{
			for(BangLuong bl : ds) {
				modelDS.addRow(new Object[] { 
						bl.getNhanVien().getMaNV(), bl.getNhanVien().getTenNV(),
						
						 bl.getThang(), bl.getNam(),tach(bl.getLuong()) 
						 });
				tong= tong+bl.getLuong();
			}
			
		}
		lblTongLuong.setText(tach(tong));
//		BangLuongDao bl_dao = new Ba

	}
	 public String tach(double luong)
	    {
	    	int chucnghin,tramnghin,trieu,nghin,tram,chuc,dvi, ty;
	    	
	        trieu=(int)  (luong/1000000);
	        tramnghin =  (int)((luong-(trieu*1000000))/100000);
	        chucnghin= (int) ((luong-(trieu*1000000)-(tramnghin*100000))/10000);
	        nghin = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000))/1000);
	        tram =(int) ((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000))/100);
	        chuc = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000) - (tram*100))/10);
	        dvi = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000) - (tram*100)-(chuc *10)));
	        
	        if(trieu>0&&trieu<1000)
	        {
	        return (""+trieu+"."+tramnghin+""+chucnghin+""+nghin+"."+tram+""+chuc+""+dvi);
	        }
	        else if(trieu>=1000)
	        {
	        	ty = trieu/1000;
	        	trieu = trieu-( ty*1000);
	        	return (ty+"."+trieu+"."+tramnghin+""+chucnghin+""+nghin+"."+tram+""+chuc+""+dvi);
	        }
	        else if(trieu==0 && tramnghin>0)
	        {
	           return(""+tramnghin+chucnghin+nghin+"."+tram+chuc+dvi); 
	        }
	        else if(trieu==0 && tramnghin == 0 && chucnghin >0)
	        {
	        	return (""+chucnghin+nghin+"."+tram+chuc+dvi);
	        }
	        else if(trieu==0 && tramnghin == 0 && chucnghin == 0 && nghin >0)
	        {
	        	return(""+nghin+"."+tram+chuc+dvi);
	        }
	        else if(trieu==0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 &&  tram>0)
	        {
	        	return(""+tram+chuc+dvi);
	        }
	        else if(trieu==0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 && tram == 0 &&  chuc > 0)
	        {
	        	return(""+chuc+dvi);
	        }
	        else
	        {
	        	return(""+dvi);
	        }
	    }
}


