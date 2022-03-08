/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import app.App;
import dao.DuLieuCongNhanDao;
import dao.DuLieuNhanVienDao;

import entity.ChamCongNVHC;
import entity.CongDoan;

import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.SanPham;
import entity.ThuNhapKhac;
/**
 *
 * @author PC PHAN TRONG
 */
public class DuLieuTinhLuong_GUI extends javax.swing.JPanel {
		DefaultTableModel dataNhanVien = new DefaultTableModel(
				new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
						"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
				0);
	
		DefaultTableModel dataCongNhan = new DefaultTableModel(new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn",
				"Sản phẩm", "Sản phẩm thực tế", "Tháng", "Năm", "Thu nhập khác" }, 0);
	//	private static final Set<String> generatedNumbers = new HashSet<String>();
	
	//	private NhanVien_DAO nvDao = new NhanVien_DAO();
	//	private ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();
		
	//	private Connection con = new Connect().getConnect();
		private Calendar instance = Calendar.getInstance();
		private static final Set<String> generatedNumbers = new HashSet<String>();
    public DuLieuTinhLuong_GUI() throws RemoteException {
        initComponents();
        jpnCongNhan.setVisible(true);
        jpnNhanVien.setVisible(false);
        cboCongDoan.removeAllItems();
		cboTenSP.removeAllItems();
	
		List<NhanVien_CongDoan> dss = App.cnDao.getallCongNhan();

//		for(NhanVien_CongDoan nv: dss) {
//			System.out.println(nv.getMaNhanVien().getTenNV());
//		}
		for (NhanVien_CongDoan nv: dss) {
			List<CongDoan> cd = App.dlieu.getCongDoan(nv.getMaCongDoan().getMaCongDoan());
			String maSP = cd.get(0).getMaSanPham().getMaSP();
			List<SanPham> sp = App.cnDao.getTenSanPham(maSP);
			String tenSP = sp.get(0).getTenSP();
//			String maSP = ds.get(0).getMaSanPham().getMaSP();
//			
//			List<SanPham> dss = cnDao.getTenSanPham(maSP);
			double luongLamThem = nv.getTnk().getLuongLamThem();
			double phuCap = nv.getTnk().getPhuCap();
			double thuong = nv.getTnk().getThuong();
			double thuNhapKhac = luongLamThem + phuCap +thuong;
			dataCongNhan.addRow(new Object[] { nv.getMaNhanVien().getMaNV(), nv.getMaNhanVien().getTenNV(),
					nv.getMaCongDoan().getTenCongDoan(),
					tenSP, nv.getSoluong(), nv.getThang(), nv.getNam(),
					tach(thuNhapKhac) });
		}
		jtableCongNhan.setModel(dataCongNhan);
		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
		jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
        
		jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		jtableCongNhan.setFont(new Font("Arial", Font.PLAIN, 13));
		jtableCongNhan.setRowHeight(jtableCongNhan.getRowHeight()+20);
		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
		jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
		jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
		jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
		jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
		DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
		rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
		centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
		jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
		jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
		jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
		
//		List<SanPham> tenSp = cnDao.getallTenSanPham();
//		String tensp = tenSp.get(0).getTenSP();
//		cboTenSP.addItem(tensp);
		
		cboCongDoan.removeAllItems();
		cboTenSP.removeAllItems();
		List<SanPham> dsss = App.cnDao.getallTenSanPham();
//		System.out.println(dsss.get(0).getTenSP());
		for(int i=0; i<dsss.size();i++) {
			String tenSp = dsss.get(i).getTenSP();
			cboTenSP.addItem(tenSp);
		}
		
		
		
		
//		try {
//			String sql = "Select tenSP From SanPham";
//			PreparedStatement p = con.prepareStatement(sql);
//			ResultSet rs = p.executeQuery();
//			cboTenSP.removeAllItems();
//			while (rs.next()) {
//				cboTenSP.addItem(rs.getString("tenSP"));
//			}
//			rs.close();
//			p.close();
//			con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// NHÂN VIÊN
		
		List<ChamCongNVHC> ds = App.dlieu.getallNhanVien();
		for (int i = 0; i < ds.size(); i++) {
			int soCongThuong = ds.get(i).getSoCongNgayThuong();
			int soCongLe = ds.get(i).getSoCongNgayLe();
			int nghiKhongPhep = ds.get(i).getNghiKhongPhep();
			int soCongChuan = soCongThuong + soCongLe +nghiKhongPhep;
			
			double luongLamThem = ds.get(i).getTnk().getLuongLamThem();
			double phuCap = ds.get(i).getTnk().getPhuCap();
			double thuong = ds.get(i).getTnk().getThuong();
			double thuNhapKhac = luongLamThem + phuCap +thuong;
			
			dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV().getMaNV(), ds.get(i).getMaNV().getTenNV(),
					tach(ds.get(i).getHeSoLuong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
					ds.get(i).getSoCongNgayThuong(), soCongChuan, ds.get(i).getThang(),
					ds.get(i).getNam(), tach(thuNhapKhac) });
		}
		jtableNhanVien.setModel(dataNhanVien);
		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		jtableNhanVien.setFont(new Font("Arial", Font.PLAIN, 13));
		jtableNhanVien.setRowHeight(jtableNhanVien.getRowHeight()+20);
		JTableHeader h = jtableNhanVien.getTableHeader();
		h.setFont(new Font("Arial", Font.BOLD, 13));
		h.setForeground(new Color(255, 255, 255));
		h.setBackground(new Color(146, 200, 240));
		jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
		jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
		jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
		jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
		jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
		jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
		jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
		jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
		jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
		jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
		jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);

		cboTenSP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					cboTenSPActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTimNV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnTimNVActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTimKiemCN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnTimKiemCNActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jMonthTimKiemNV2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				try {
					monthTuNhanVienPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jMonthTimKiemNV1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				try {
					monthDenNhanVienPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jMonthTimKiemCN2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				try {
					monthTuCongNhanPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jMonthTimKiemCN1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				try {
					monthDenCongNhanPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jPanel16.setBackground(new java.awt.Color(255,255,255));
		
		JTableHeader h1 = jtableCongNhan.getTableHeader();
		h1.setFont(new Font("Arial", Font.BOLD, 13));
		h1.setForeground(new Color(255, 255, 255));
		h1.setBackground(new Color(146, 200, 240));
		btnCongNhan.setBackground(new Color(51,153,255));
        btnNhanVien.setBackground(new java.awt.Color(40,57,88));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnCongNhan = new java.awt.Button();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnNhanVien = new java.awt.Button();
        jpn = new javax.swing.JPanel();
        jpnCongNhan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        panelttNV3 = new javax.swing.JPanel();
        lblmaNV4 = new javax.swing.JLabel();
        txtMaCongNhan = new javax.swing.JTextField();
        lblmaNV5 = new javax.swing.JLabel();
        lblmaNV9 = new javax.swing.JLabel();
        lblmaNV10 = new javax.swing.JLabel();
        txtSanPhamThucTe = new javax.swing.JTextField();
        cboTenSP = new javax.swing.JComboBox<>();
        lblmaNV15 = new javax.swing.JLabel();
        jMonthCongNhan = new com.toedter.calendar.JMonthChooser();
        jYearCongNhan = new com.toedter.calendar.JYearChooser();
        cboCongDoan = new javax.swing.JComboBox<>();
        panelttNV4 = new javax.swing.JPanel();
        lblmaNV11 = new javax.swing.JLabel();
        txtLuongLamThemCongNhan = new javax.swing.JTextField();
        lblmaNV12 = new javax.swing.JLabel();
        txtPhuCapCongNhan = new javax.swing.JTextField();
        lblmaNV13 = new javax.swing.JLabel();
        txtThuongCongNhan = new javax.swing.JTextField();
        panelttNV5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableCongNhan = new javax.swing.JTable();
        panel6 = new java.awt.Panel();
        jMonthTimKiemCN1 = new com.toedter.calendar.JMonthChooser();
        jLabel14 = new javax.swing.JLabel();
        jYearTimKiemCN2 = new com.toedter.calendar.JYearChooser();
        jLabel15 = new javax.swing.JLabel();
        jMonthTimKiemCN2 = new com.toedter.calendar.JMonthChooser();
        jYearCN2 = new com.toedter.calendar.JYearChooser();
        textField5 = new java.awt.TextField();
        btnTimKiemCN = new javax.swing.JButton();
        txtTimKiemCN = new javax.swing.JTextField();
        btnLamMoiCongNhan = new javax.swing.JButton();
        btnLamXoaRongCN = new javax.swing.JButton();
        btnThemThemCongNhan = new javax.swing.JButton();
        btnCapNhapCongNhan = new javax.swing.JButton();
        jpnNhanVien = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        panelttNV = new javax.swing.JPanel();
        lblmaNV = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        lblmaNV1 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        lblmaNV2 = new javax.swing.JLabel();
        lblmaNV3 = new javax.swing.JLabel();
        txtCongNgayLe = new javax.swing.JTextField();
        txtNghiKhongPhep = new javax.swing.JTextField();
        txtCongNgayThuong = new javax.swing.JTextField();
        lblmaNV14 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jMonthNhanVien = new com.toedter.calendar.JMonthChooser();
        jYearNhanVien = new com.toedter.calendar.JYearChooser();
        panelttNV1 = new javax.swing.JPanel();
        lblmaNV6 = new javax.swing.JLabel();
        txtLuongLamThemNV = new javax.swing.JTextField();
        lblmaNV7 = new javax.swing.JLabel();
        txtPhuCapNV = new javax.swing.JTextField();
        lblmaNV8 = new javax.swing.JLabel();
        txtThuongNV = new javax.swing.JTextField();
        panelttNV2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableNhanVien = new javax.swing.JTable();
        panel2 = new java.awt.Panel();
        jMonthTimKiemNV1 = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();
        jYearTimKiemNV1 = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jMonthTimKiemNV2 = new com.toedter.calendar.JMonthChooser();
        jYearTimKiemNV2 = new com.toedter.calendar.JYearChooser();
        textField1 = new java.awt.TextField();
        btnTimNV = new javax.swing.JButton();
        txtTimNhanVIen = new javax.swing.JTextField();
        btnXoaRongNhanVien = new javax.swing.JButton();
        btnLamMoiNhanVien = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        btnCapNhapNhanVien = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(23, 35, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(41, 57, 80));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        btnCongNhan.setBackground(new java.awt.Color(40, 57, 88));
        btnCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCongNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnCongNhan.setLabel("Công Nhân");
        btnCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCongNhanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addComponent(btnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 182, -1));

        jPanel9.setBackground(new java.awt.Color(41, 57, 80));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnNhanVien.setActionCommand("Nhân Viên");
        btnNhanVien.setBackground(new java.awt.Color(40, 57, 88));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setLabel("Nhân Viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 157, -1, -1));

        jpnCongNhan.setPreferredSize(new java.awt.Dimension(1351, 629));
        jpnCongNhan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(1341, 6));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Công Nhân");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(120, 162, 252));

        panelttNV3.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        lblmaNV4.setText("Mã nhân viên");

        lblmaNV5.setText("Tên sản phẩm");

        lblmaNV9.setText("Công đoạn");

        lblmaNV10.setText("Sản phẩm thực tế");

        cboTenSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblmaNV15.setText("Thời Gian");

        cboCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelttNV3Layout = new javax.swing.GroupLayout(panelttNV3);
        panelttNV3.setLayout(panelttNV3Layout);
        panelttNV3Layout.setHorizontalGroup(
            panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV3Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(lblmaNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNV3Layout.createSequentialGroup()
                            .addComponent(lblmaNV9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cboCongDoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelttNV3Layout.createSequentialGroup()
                            .addComponent(lblmaNV5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSanPhamThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMonthCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNV3Layout.setVerticalGroup(
            panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCongDoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSanPhamThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMonthCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelttNV3Layout.createSequentialGroup()
                        .addComponent(lblmaNV15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );

        panelttNV4.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thu nhập khác"));
        panelttNV4.setPreferredSize(new java.awt.Dimension(345, 191));

        lblmaNV11.setText("Lương làm thêm");

        lblmaNV12.setText("Phụ cấp");

        lblmaNV13.setText("Thưởng");

        javax.swing.GroupLayout panelttNV4Layout = new javax.swing.GroupLayout(panelttNV4);
        panelttNV4.setLayout(panelttNV4Layout);
        panelttNV4Layout.setHorizontalGroup(
            panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongLamThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhuCapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV4Layout.createSequentialGroup()
                        .addComponent(lblmaNV13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtThuongCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNV4Layout.setVerticalGroup(
            panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongLamThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhuCapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelttNV4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThuongCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelttNV5.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Thu nhập khác"
            }
        ));
        jtableCongNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					jtableCongNhanMouseClicked(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jScrollPane3.setViewportView(jtableCongNhan);

        panel6.setPreferredSize(new java.awt.Dimension(174, 58));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Đến");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Từ");

        textField5.setText("textField1");

        btnTimKiemCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Search.png"))); // NOI18N
        btnTimKiemCN.setText("Tìm ");
        btnTimKiemCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnTimKiemCNActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthTimKiemCN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthTimKiemCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearCN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(txtTimKiemCN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiemCN)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemCN)
                    .addComponent(btnTimKiemCN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jYearCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearTimKiemCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemCN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemCN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout panelttNV5Layout = new javax.swing.GroupLayout(panelttNV5);
        panelttNV5.setLayout(panelttNV5Layout);
        panelttNV5Layout.setHorizontalGroup(
            panelttNV5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelttNV5Layout.setVerticalGroup(
            panelttNV5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV5Layout.createSequentialGroup()
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3))
        );

        btnLamMoiCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnLamMoiCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Refresh.png"))); // NOI18N
        btnLamMoiCongNhan.setText("Làm mới");
        btnLamMoiCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnLamMoiCongNhanActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnLamXoaRongCN.setBackground(new java.awt.Color(228, 237, 225));
        btnLamXoaRongCN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamXoaRongCN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Black pin.png"))); // NOI18N
        btnLamXoaRongCN.setText("Xóa rỗng");
        btnLamXoaRongCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamXoaRongCNActionPerformed(evt);
            }
        });

        btnThemThemCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnThemThemCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemThemCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Create.png"))); // NOI18N
        btnThemThemCongNhan.setText("Thêm");
        btnThemThemCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnThemThemCongNhanActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnCapNhapCongNhan.setBackground(new java.awt.Color(228, 237, 225));
        btnCapNhapCongNhan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhapCongNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Update.png"))); // NOI18N
        btnCapNhapCongNhan.setText("Cập nhập");
        btnCapNhapCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnCapNhapCongNhanActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelttNV4, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(panelttNV3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamXoaRongCN, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemThemCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoiCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhapCongNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addComponent(panelttNV5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(panelttNV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelttNV4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLamXoaRongCN, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemThemCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnCapNhapCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(panelttNV5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnCongNhan.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 630));

        jpnNhanVien.setPreferredSize(new java.awt.Dimension(1250, 629));
        jpnNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setMinimumSize(new java.awt.Dimension(100, 70));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(846, 60));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân Viên");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 1340, -1));

        jPanel4.setBackground(new java.awt.Color(120, 162, 252));

        panelttNV.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));
        panelttNV.setPreferredSize(new java.awt.Dimension(345, 268));

        lblmaNV.setText("Mã nhân viên");

        lblmaNV1.setText("Hệ số lương");

        lblmaNV2.setText("Nghĩ không phép");

        lblmaNV3.setText("Công ngày lễ");

        lblmaNV14.setText("Công ngày thường");

        lblThoiGian.setText("Thời gian");

        javax.swing.GroupLayout panelttNVLayout = new javax.swing.GroupLayout(panelttNV);
        panelttNV.setLayout(panelttNVLayout);
        panelttNVLayout.setHorizontalGroup(
            panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelttNVLayout.createSequentialGroup()
                            .addComponent(lblmaNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNghiKhongPhep, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblmaNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCongNgayLe, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblmaNV14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCongNgayThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNVLayout.createSequentialGroup()
                        .addComponent(lblThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jMonthNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelttNVLayout.setVerticalGroup(
            panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNVLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNghiKhongPhep, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCongNgayLe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCongNgayThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMonthNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jYearNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        panelttNV1.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thu nhập khác"));

        lblmaNV6.setText("Lương làm thêm");

        lblmaNV7.setText("Phụ cấp");

        lblmaNV8.setText("Thưởng");

        javax.swing.GroupLayout panelttNV1Layout = new javax.swing.GroupLayout(panelttNV1);
        panelttNV1.setLayout(panelttNV1Layout);
        panelttNV1Layout.setHorizontalGroup(
            panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLuongLamThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhuCapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelttNV1Layout.createSequentialGroup()
                        .addComponent(lblmaNV8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtThuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelttNV1Layout.setVerticalGroup(
            panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelttNV1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongLamThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhuCapNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelttNV1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmaNV8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panelttNV2.setBackground(new java.awt.Color(255, 255, 255));
        panelttNV2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin "));

        jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ", "Công ngày thường", "Tháng", "Năm", "Số công chuẩn", "Thu nhập khác"
            }
        ));
        jtableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					jtableNhanVienMouseClicked(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jScrollPane2.setViewportView(jtableNhanVien);

        panel2.setPreferredSize(new java.awt.Dimension(174, 58));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Đến");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Từ");

        textField1.setText("textField1");

        btnTimNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Search.png"))); // NOI18N
        btnTimNV.setText("Tìm ");
        btnTimNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnTimNVActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMonthTimKiemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMonthTimKiemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearTimKiemNV2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimNhanVIen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimNV)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimNhanVIen)
                    .addComponent(btnTimNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jYearTimKiemNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearTimKiemNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthTimKiemNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout panelttNV2Layout = new javax.swing.GroupLayout(panelttNV2);
        panelttNV2.setLayout(panelttNV2Layout);
        panelttNV2Layout.setHorizontalGroup(
            panelttNV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelttNV2Layout.setVerticalGroup(
            panelttNV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelttNV2Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
        );

        btnXoaRongNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnXoaRongNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaRongNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Black pin.png"))); // NOI18N
        btnXoaRongNhanVien.setText("Xóa rỗng");
        btnXoaRongNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaRongNhanVienActionPerformed(evt);
            }
        });

        btnLamMoiNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnLamMoiNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoiNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Refresh.png"))); // NOI18N
        btnLamMoiNhanVien.setText("Làm mới");
        btnLamMoiNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnLamMoiNhanVienActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnThemNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnThemNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Create.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnThemNhanVienActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        btnCapNhapNhanVien.setBackground(new java.awt.Color(228, 237, 225));
        btnCapNhapNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhapNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Update.png"))); // NOI18N
        btnCapNhapNhanVien.setText("Cập nhập");
        btnCapNhapNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnCapNhapNhanVienActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelttNV1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelttNV, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaRongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhapNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addComponent(panelttNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(panelttNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelttNV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaRongNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnLamMoiNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(btnCapNhapNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(panelttNV2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel8.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1340, 560));

        jpnNhanVien.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        javax.swing.GroupLayout jpnLayout = new javax.swing.GroupLayout(jpn);
        jpn.setLayout(jpnLayout);
        jpnLayout.setHorizontalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
            .addGroup(jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnLayout.createSequentialGroup()
                    .addComponent(jpnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpnLayout.setVerticalGroup(
            jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnCongNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCongNhanActionPerformed
                 jpnNhanVien.setVisible(false);
        jpnCongNhan.setVisible(true);
        btnCongNhan.setBackground(new Color(51,153,255));
        btnNhanVien.setBackground(new java.awt.Color(40,57,88));
    }//GEN-LAST:event_btnCongNhanActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        jpnNhanVien.setVisible(false);
    	jpnCongNhan.setVisible(true);
         jpnNhanVien.setVisible(true);
         jpnCongNhan.setVisible(false);
         btnNhanVien.setBackground(new Color(51,153,255));
         btnCongNhan.setBackground(new java.awt.Color(40,57,88));
    }//GEN-LAST:event_btnNhanVienActionPerformed
    public static String getRandomId(final int maxLength, final int maxTry) {
	    final Random random = new Random(System.nanoTime());
	    final int max = (int) Math.pow(10, maxLength);
	    final int maxMin = (int) Math.pow(10, maxLength-1);
	    int i = 0;
	    boolean unique = false;
	    int randomId = -1;
	    while (i < maxTry) {
	        randomId = random.nextInt(max - maxMin - 1) + maxMin;
	 
	        synchronized (generatedNumbers) {
	            if (generatedNumbers.contains(randomId) == false) {
	                unique = true;
	                break;
	            }
	        }
	        i++;
	    }
	    if (unique == false) {
	        throw new RuntimeException("Cannot generate unique id!");
	    }
	 
	    synchronized (generatedNumbers) {
	        generatedNumbers.add(String.valueOf(randomId));
	    }
	    String a = String.valueOf(randomId);
	    String code = "CC";
	    return code +a;
	}
// MOUSE CLICK NHÂN VIÊN
    private void jtableNhanVienMouseClicked(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_jtableNhanVienMouseClicked
    	List<ChamCongNVHC> list = new ArrayList<ChamCongNVHC>();
		int row = jtableNhanVien.getSelectedRow();
		txtMaNV.setText(jtableNhanVien.getValueAt(row, 0).toString());
//		double heSoluong = (double) (jtableNhanVien.getValueAt(row,2));
//		double chuyenHSL = (int)Double.parseDouble(heSoluong);
//		txtHeSoLuong.setText(String.valueOf(heSoluong));
		String hesoLuong = jtableNhanVien.getValueAt(row, 2).toString();
//		int hesoLuong2 = (int) Integer.parseInt(hesoLuong);
//		JOptionPane.showMessageDialog(null, hesoLuong2);
//		String chuyenhs = String.valueOf((hesoLuong2));
//		txtHeSoLuong.setText(chuyenhs);
		txtHeSoLuong.setText(jtableNhanVien.getValueAt(row, 2).toString());
		
		txtNghiKhongPhep.setText(jtableNhanVien.getValueAt(row, 3).toString());
		txtCongNgayLe.setText(jtableNhanVien.getValueAt(row, 4).toString());
		txtCongNgayThuong.setText(jtableNhanVien.getValueAt(row, 5).toString());
		int x = Integer.parseInt(jtableNhanVien.getValueAt(row, 7).toString());
		jMonthNhanVien.setMonth(x - 1);
		int y = Integer.parseInt(jtableNhanVien.getValueAt(row, 8).toString());
		jYearNhanVien.setYear(y);
//		ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();

		String maNV = jtableNhanVien.getValueAt(row, 0).toString();
		ChamCongNVHC tnk = App.dlieu.getTNK(maNV, x, y);
		
		int luongLamThem = (int)tnk.getTnk().getLuongLamThem();
		String chuyenLLT = String.valueOf((luongLamThem));
		txtLuongLamThemNV.setText(chuyenLLT);
//		
				
		int phuCap = (int)tnk.getTnk().getPhuCap();
		String chuyenPC = String.valueOf((phuCap));
		txtPhuCapNV.setText(chuyenPC);
//
		int Thuong = (int)tnk.getTnk().getThuong();
		String chuyenThuong = String.valueOf((Thuong));
		txtThuongNV.setText((chuyenThuong));
//		ThuNhapKhac tnk = tnkDao.getluongLamThem(maNV,x,y);
		txtMaNV.setEditable(false);
		jYearNhanVien.setEnabled(false);
		jMonthNhanVien.setEnabled(false);
    }//GEN-LAST:event_jtableNhanVienMouseClicked
// MOUSE CLICK CÔNG NHÂN
    private void jtableCongNhanMouseClicked(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_jtableCongNhanMouseClicked
    	int row = jtableCongNhan.getSelectedRow();
		txtMaCongNhan.setText(jtableCongNhan.getValueAt(row, 0).toString());
		cboTenSP.setSelectedItem(jtableCongNhan.getValueAt(row, 3).toString());
		cboCongDoan.setSelectedItem(jtableCongNhan.getValueAt(row, 2).toString());
		txtSanPhamThucTe.setText(jtableCongNhan.getValueAt(row, 4).toString());

		int x = Integer.parseInt(jtableCongNhan.getValueAt(row, 5).toString());
		jMonthCongNhan.setMonth(x - 1);
		int y = Integer.parseInt(jtableCongNhan.getValueAt(row, 6).toString());
		jYearCongNhan.setYear(y);

//		ThuNhapKhac_DAO tnkDao = new ThuNhapKhac_DAO();

		String maCN = jtableCongNhan.getValueAt(row, 0).toString();

		NhanVien_CongDoan tnk = App.cnDao.getTNK(maCN, x, y);
		int luongLamThem =(int) tnk.getTnk().getLuongLamThem();
		String chuyenLLT = String.valueOf((luongLamThem));
		txtLuongLamThemCongNhan.setText(chuyenLLT);
//
		int phuCap = (int)tnk.getTnk().getPhuCap();
		String chuyenPC = String.valueOf((phuCap));
		txtPhuCapCongNhan.setText(chuyenPC);
//
		int Thuong = (int)tnk.getTnk().getThuong();
		String chuyenThuong = String.valueOf((Thuong));
		txtThuongCongNhan.setText(chuyenThuong);

		txtMaCongNhan.setEditable(false);
		txtMaCongNhan.requestFocus();
		jYearCongNhan.setEnabled(false);
		jMonthCongNhan.setEnabled(false);
    }//GEN-LAST:event_jtableCongNhanMouseClicked
// BUTTON TÌM NHÂN VIÊN
    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnTimNVActionPerformed
    	Object o = evt.getSource();
		if (o.equals(btnTimNV)) {
			DefaultTableModel newmodel = new DefaultTableModel(
					new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
							"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
					0);
			List<ChamCongNVHC> ds = App.dlieu.timKiem(txtTimNhanVIen.getText(), jMonthTimKiemNV1.getMonth() + 1,
					jYearTimKiemNV1.getYear(), jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());
//    	jtableNhanVien.setModel(newmodel);
			if (ds.size() > 0) {
				jtableNhanVien.setModel(newmodel);
				jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
				for (int i = 0; i < ds.size(); i++) {
					int soCongThuong = ds.get(i).getSoCongNgayThuong();
					int soCongLe = ds.get(i).getSoCongNgayLe();
					int nghiKhongPhep = ds.get(i).getNghiKhongPhep();
					int soCongChuan = soCongThuong + soCongLe +nghiKhongPhep;
					
					double luongLamThem = ds.get(i).getTnk().getLuongLamThem();
					double phuCap = ds.get(i).getTnk().getPhuCap();
					double thuong = ds.get(i).getTnk().getThuong();
					double thuNhapKhac = luongLamThem + phuCap +thuong;
					newmodel.addRow(new Object[] { ds.get(i).getMaNV().getMaNV(), ds.get(i).getMaNV().getTenNV(),
							tach(ds.get(i).getHeSoLuong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
							ds.get(i).getSoCongNgayThuong(), soCongChuan, ds.get(i).getThang(),
							ds.get(i).getNam(), tach(thuNhapKhac) });
				}
			} else {
				jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null } },
						new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép",
								"Công ngày lễ", "Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm",
								"Thu Nhập Khác" }));
				jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
				jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
				jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
				jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
				jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
			}
		}
    }//GEN-LAST:event_btnTimNVActionPerformed
 // BUTTON TÌM CÔNG NHÂN
    private void btnTimKiemCNActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnTimKiemCNActionPerformed
    	Object o = evt.getSource();
		if (o.equals(btnTimKiemCN)) {
	   	 DefaultTableModel datanew = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
					"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
			dataCongNhan.getDataVector().removeAllElements();
			List<NhanVien_CongDoan> dss = App.cnDao.timKiem(txtTimKiemCN.getText(), jMonthTimKiemCN1.getMonth() + 1,
					jYearTimKiemCN2.getYear(), jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
		

//	   	 JOptionPane.showMessageDialog(null, dss);
			if (dss.size() > 0) {
				jtableCongNhan.setModel(dataCongNhan);
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
				for (NhanVien_CongDoan nv: dss) {
					List<CongDoan> cd = App.dlieu.getCongDoan(nv.getMaCongDoan().getMaCongDoan());
					String maSP = cd.get(0).getMaSanPham().getMaSP();
					List<SanPham> sp = App.cnDao.getTenSanPham(maSP);
					String tenSP = sp.get(0).getTenSP();
//					String maSP = ds.get(0).getMaSanPham().getMaSP();
//					
//					List<SanPham> dss = cnDao.getTenSanPham(maSP);
					double luongLamThem = nv.getTnk().getLuongLamThem();
					double phuCap = nv.getTnk().getPhuCap();
					double thuong = nv.getTnk().getThuong();
					double thuNhapKhac = luongLamThem + phuCap +thuong;
					dataCongNhan.addRow(new Object[] { nv.getMaNhanVien().getMaNV(), nv.getMaNhanVien().getTenNV(),
							nv.getMaCongDoan().getTenCongDoan(),
							tenSP, nv.getSoluong(), nv.getThang(), nv.getNam(),
							tach(thuNhapKhac) });
				}
			} else {
				jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

				}, new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Tháng",
						"Năm", "Thu nhập khác" }));
				jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
				jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
				jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
				jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
				jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
				jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
				DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
				rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
				centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
				jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
				JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");

			}
		}
    }//GEN-LAST:event_btnTimKiemCNActionPerformed
//  XÓA RỖNG CÔNG NHÂN
    private void btnLamXoaRongCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamXoaRongCNActionPerformed
    	jtableCongNhan.clearSelection();
		txtMaCongNhan.setText("");
//		cboCongDoan.removeAllItems();
//		cboTenSP.removeAllItems();
//		cboTenSP.setSelectedItem(null);
		cboCongDoan.setSelectedItem(null);
		txtSanPhamThucTe.setText("");
		int month = instance.get(Calendar.MONTH);
		jMonthCongNhan.setMonth(month);
		int year = instance.get(Calendar.YEAR);
		jYearCongNhan.setYear(year);
		txtLuongLamThemCongNhan.setText("");
		txtThuongCongNhan.setText("");
		txtPhuCapCongNhan.setText("");
		txtThuongNV.setText("");
		txtPhuCapNV.setText("");
		txtMaCongNhan.setEditable(true);
		txtMaCongNhan.requestFocus();
		jMonthCongNhan.setEnabled(true);
		jYearCongNhan.setEnabled(true);
    }//GEN-LAST:event_btnLamXoaRongCNActionPerformed
// THÊM CÔNG NHÂN
    private void btnThemThemCongNhanActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnThemThemCongNhanActionPerformed
    	Object o = evt.getSource();
		
		if (o.equals(btnThemThemCongNhan)) {
			if (jtableCongNhan.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng làm mới trước khi thêm");
				return;
			}
			if(App.dlieu.kiemTraNhanVien(txtMaCongNhan.getText())) {
				if(App.dlieu.kiemTraNghiViec(txtMaCongNhan.getText())) {
					if(App.dlieu.rangBuocThangNamCN(txtMaCongNhan.getText(), jMonthCongNhan.getMonth()+1, jYearCongNhan.getYear())) {
						if (validataCongNhan() && thuNhapKhac(txtLuongLamThemCongNhan) && thuNhapKhac(txtPhuCapCongNhan) && thuNhapKhac(txtThuongCongNhan)  ) {
							String maNv = txtMaCongNhan.getText();
							NhanVien nv = App.nvDao.GetNVTheoMa(maNv);
							
//							JOptionPane.showMessageDialog(null, nv);
							String tensp = cboTenSP.getSelectedItem().toString();
							String tencd = cboCongDoan.getSelectedItem().toString();
							List<SanPham> sp = App.cnDao.getMaSpTheoTenSP(tensp);
							String maSP = sp.get(0).getMaSP();
//							JOptionPane.showMessageDialog(null, maSP);
//							List<CongDoan> cd = cnDao.getTenCongDoan(tenSP);
							CongDoan cdoan = App.cnDao.getCongDoan(tencd,maSP);
//							JOptionPane.showMessageDialog(null, cdoan);
							
							int soLuong = Integer.parseInt(txtSanPhamThucTe.getText().toString());
							int thang = jMonthCongNhan.getMonth() + 1;
							int nam = jYearCongNhan.getYear();
							double luongLamThem = Double.parseDouble(txtLuongLamThemCongNhan.getText());
							double phuCap = Double.parseDouble(txtPhuCapCongNhan.getText());
							double thuong = Double.parseDouble(txtThuongCongNhan.getText());
							double thuNhapKhac = (luongLamThem + phuCap + thuong);
							NhanVien_CongDoan nvcd = new NhanVien_CongDoan(nv,cdoan,soLuong,thang,nam,false);
							ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem,phuCap,thuong);
							nvcd.setTnk(tnk);
//							JOptionPane.showMessageDialog(null, nvcd);
							if(App.cnDao.themCongNhan(nvcd)) {
								dataCongNhan.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(),
										tencd,tensp, soLuong, thang, nam,
										tach(thuNhapKhac) });
								JOptionPane.showMessageDialog(null, "Thành công");
							}
							else {
								JOptionPane.showMessageDialog(null, "Fail");
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nhân viên đã có dữ liệu ở tháng này");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Nhân viên đã nghĩ việc!!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên này!!!");
			}
		}
    }//GEN-LAST:event_btnThemThemCongNhanActionPerformed
// LÀM MỚI CÔNG NHÂN
    private void btnLamMoiCongNhanActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnLamMoiCongNhanActionPerformed
    	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
 				"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
    	Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
 		dataCongNhan.getDataVector().removeAllElements();
 		List<NhanVien_CongDoan> dss = App.cnDao.getThangNam(month + 1, year,
 				month + 1, year);
 		if (dss.size() > 0) {
 			jtableCongNhan.setModel(dataCongNhan);
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
 			for (NhanVien_CongDoan nv: dss) {
 				List<CongDoan> cd = App.dlieu.getCongDoan(nv.getMaCongDoan().getMaCongDoan());
 				String maSP = cd.get(0).getMaSanPham().getMaSP();
 				List<SanPham> sp = App.cnDao.getTenSanPham(maSP);
 				String tenSP = sp.get(0).getTenSP();
// 				String maSP = ds.get(0).getMaSanPham().getMaSP();
// 				
// 				List<SanPham> dss = cnDao.getTenSanPham(maSP);
 				double luongLamThem = nv.getTnk().getLuongLamThem();
 				double phuCap = nv.getTnk().getPhuCap();
 				double thuong = nv.getTnk().getThuong();
 				double thuNhapKhac = luongLamThem + phuCap +thuong;
 				dataCongNhan.addRow(new Object[] { nv.getMaNhanVien().getMaNV(), nv.getMaNhanVien().getTenNV(),
 						nv.getMaCongDoan().getTenCongDoan(),
 						tenSP, nv.getSoluong(), nv.getThang(), nv.getNam(),
 						tach(thuNhapKhac) });
 			}
 		}
 		else {
 			jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

 			}, new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Tháng",
 					"Năm", "Thu nhập khác" }));
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
//  	   		 JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");
 		}
    }
// CẬP NHẬP CÔNG NHÂN
    private void btnCapNhapCongNhanActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnCapNhapCongNhanActionPerformed
    	Object o = evt.getSource();
		if (o.equals(btnCapNhapCongNhan)) {
			int row = jtableCongNhan.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				return;
			} else {
				if(App.cnDao.kiemTraThanhToanCN(txtMaCongNhan.getText(), jMonthCongNhan.getMonth()+1, jYearCongNhan.getYear())) {
					if (validataCongNhan() && thuNhapKhac(txtLuongLamThemCongNhan) && thuNhapKhac(txtPhuCapCongNhan) && thuNhapKhac(txtThuongCongNhan)  ) {
						String maNv = txtMaCongNhan.getText();
						NhanVien nv = App.nvDao.GetNVTheoMa(maNv);	
//						JOptionPane.showMessageDialog(null, nv);
						String tensp = cboTenSP.getSelectedItem().toString();
						String tencd = cboCongDoan.getSelectedItem().toString();
						List<SanPham> sp = App.cnDao.getMaSpTheoTenSP(tensp);
						String maSP = sp.get(0).getMaSP();
//						JOptionPane.showMessageDialog(null, maSP);
//						List<CongDoan> cd = cnDao.getTenCongDoan(tenSP);
						CongDoan cdoan = App.cnDao.getCongDoan(tencd,maSP);
//						JOptionPane.showMessageDialog(null, cdoan);
						
						int soLuong = Integer.parseInt(txtSanPhamThucTe.getText().toString());
						int thang = jMonthCongNhan.getMonth() + 1;
						int nam = jYearCongNhan.getYear();
						double luongLamThem = Double.parseDouble(txtLuongLamThemCongNhan.getText());
						double phuCap = Double.parseDouble(txtPhuCapCongNhan.getText());
						double thuong = Double.parseDouble(txtThuongCongNhan.getText());
						double thuNhapKhac = (luongLamThem + phuCap + thuong);
						NhanVien_CongDoan nvcd = new NhanVien_CongDoan(nv,cdoan,soLuong,thang,nam,false);
						ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem,phuCap,thuong);
						nvcd.setTnk(tnk);
						if(App.cnDao.upDateCongNhan(nvcd)) {
							jtableCongNhan.setValueAt(maNv, row, 0);
							jtableCongNhan.setValueAt(nv.getTenNV(), row, 1);
							jtableCongNhan.setValueAt(tencd, row, 2);
							jtableCongNhan.setValueAt(tensp, row, 3);
							jtableCongNhan.setValueAt(soLuong, row, 4);
							jtableCongNhan.setValueAt(thang, row, 5);
							jtableCongNhan.setValueAt(nam, row, 6);
							jtableCongNhan.setValueAt(tach(thuNhapKhac), row, 7);
							JOptionPane.showMessageDialog(null, "Thành công");
						}
						else {
							JOptionPane.showMessageDialog(null, "Fail");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Đã thanh toán không thể cập nhập!!!");
				}
			}
		}
    }
//  XÓA RỖNG NHÂN VIÊN
    private void btnXoaRongNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
//    	JOptionPane.showMessageDialog(null, "ALO");
    	Object o = evt.getSource();
		if (o.equals(btnXoaRongNhanVien)) {
			jtableNhanVien.clearSelection();
			xoaRongNhanVien();
		}
		jYearNhanVien.setEnabled(true);
		jMonthNhanVien.setEnabled(true);
    }
// LÀM MỚI NHÂN VIÊN
    private void btnLamMoiNhanVienActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {
    	new ImportData_GUI().setVisible(true);
    	
//    	dataNhanVien.getDataVector().removeAllElements();
//    	Calendar instance = Calendar.getInstance();
//        int year = instance.get(Calendar.YEAR);
//        int month = instance.get(Calendar.MONTH);
////        JOptionPane.showMessageDialog(null, year);
////        JOptionPane.showMessageDialog(null, month+1);
// 		List<ChamCongNVHC> ds = App.dlieu.getThangNam(month + 1, year,
// 				month + 1, year);
// 		if (ds.size() > 0) {
// 			jtableNhanVien.setModel(dataNhanVien);
// 			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
// 			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
// 			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
// 			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
// 			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
// 			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
// 			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
// 			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
// 			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
// 			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
// 			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
// 			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
// 			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
// 			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
// 			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
// 			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
// 			for (int i = 0; i < ds.size(); i++) {
// 				int soCongThuong = ds.get(i).getSoCongNgayThuong();
// 				int soCongLe = ds.get(i).getSoCongNgayLe();
// 				int nghiKhongPhep = ds.get(i).getNghiKhongPhep();
// 				int soCongChuan = soCongThuong + soCongLe +nghiKhongPhep;
// 				
// 				double luongLamThem = ds.get(i).getTnk().getLuongLamThem();
// 				double phuCap = ds.get(i).getTnk().getPhuCap();
// 				double thuong = ds.get(i).getTnk().getThuong();
// 				double thuNhapKhac = luongLamThem + phuCap +thuong;
// 				dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV().getMaNV(), ds.get(i).getMaNV().getTenNV(),
// 						tach(ds.get(i).getHeSoLuong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
// 						ds.get(i).getSoCongNgayThuong(), soCongChuan, ds.get(i).getThang(),
// 						ds.get(i).getNam(), tach(thuNhapKhac) });
// 			}
// 		}
// 		else {
//     			
//     	        dataNhanVien.getDataVector().removeAllElements();
//     	        
//     			jtableNhanVien.setModel(dataNhanVien);
//     			jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
//     			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
//     			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//     			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
//     			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
//     			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
//     			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
//     			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
//     			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
//     			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
//     			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
//     			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
//     			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
//     			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
//     			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
//     			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//     			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//     			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
//     			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
// 		}
    }
// THÊM NHÂN VIÊN
    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnThemNhanVienActionPerformed
    	Object o = evt.getSource();
		if (o.equals(btnThemNhanVien)) {
			if (jtableNhanVien.getSelectedRow() > -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng làm mới trước khi thêm");
				return;
			}
			if(App.dlieu.kiemTraNhanVien(txtMaNV.getText())) {
				if(App.dlieu.kiemTraNghiViec(txtMaNV.getText())) {
					if(App.dlieu.rangBuocThangNam(txtMaNV.getText(),jMonthNhanVien.getMonth() +1, jYearNhanVien.getYear())) {
						if (validataNhanVien() && thuNhapKhac(txtLuongLamThemNV) && thuNhapKhac(txtPhuCapNV) && thuNhapKhac(txtThuongNV) ) {
							String ma = taoMaNhanVienHanhChinh();
							String maNv = txtMaNV.getText();
						
							NhanVien nv = App.nvDao.GetNVTheoMa(maNv);
//							JOptionPane.showMessageDialog(null, nv);
							
							int thang = jMonthNhanVien.getMonth();
							int nam = jYearNhanVien.getYear();
							double heSoLuong = Double.parseDouble(txtHeSoLuong.getText());
							int congNgayLe = Integer.parseInt(txtCongNgayLe.getText());
							int congNgayThuong = Integer.parseInt(txtCongNgayThuong.getText());
							int nghiKhongPhep = Integer.parseInt(txtNghiKhongPhep.getText());
							int soCongChuan = 2 * congNgayLe + congNgayThuong - nghiKhongPhep;
							
							double luongLamThem = Double.parseDouble(txtLuongLamThemNV.getText());
							double phuCap = Double.parseDouble(txtPhuCapNV.getText());
							double thuong = Double.parseDouble(txtThuongNV.getText());
							ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem, phuCap, thuong);
							double thuNhapKhac = luongLamThem + phuCap +thuong;
							ChamCongNVHC nvhc = new ChamCongNVHC(ma, nv, heSoLuong, congNgayLe, congNgayThuong, nghiKhongPhep, thang+1, nam);
							nvhc.setTnk(tnk);
//							JOptionPane.showMessageDialog(null, nv);
							if(App.dlieu.themNhanVien(nvhc)) {
								dataNhanVien.addRow(new Object[] { maNv, nv.getTenNV(), tach(heSoLuong), nghiKhongPhep,
										congNgayLe, congNgayThuong, soCongChuan, thang + 1, nam, tach(thuNhapKhac) });
								JOptionPane.showMessageDialog(null, "Thành công");
								jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
				     			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
				     			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				     			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
				     			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
				     			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
				     			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
				     			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
				     			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
				     			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
				     			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
				     			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
				     			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
				     			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				     			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				     			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				     			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				     			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
				     			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Nhân viên đã được chấm công");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Nhân viên đã nghĩ việc");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên");
			}
		}
    }//GEN-LAST:event_btnThemNhanVienActionPerformed
// CẬP NHẬP NHÂN VIÊN
    private void btnCapNhapNhanVienActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_btnCapNhapNhanVienActionPerformed
    	Object o = evt.getSource();
		if (o.equals(btnCapNhapNhanVien)) {
			int row = jtableNhanVien.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				return;
			} else {
				if(App.dlieu.kiemTraNghiViec(txtMaNV.getText())) {
					if(App.cnDao.kiemTraThanhToanNV(txtMaNV.getText(), jMonthNhanVien.getMonth() +1, jYearNhanVien.getYear())) {
						if (validataNhanVien() && thuNhapKhac(txtLuongLamThemNV) && thuNhapKhac(txtPhuCapNV) && thuNhapKhac(txtThuongNV) ) {
							String maNv = txtMaNV.getText();
//							JOptionPane.showMessageDialog(null, maNv);
							NhanVien nv = App.nvDao.GetNVTheoMa(maNv);
//							JOptionPane.showMessageDialog(null, nv);
							int thang = jMonthNhanVien.getMonth();
							int nam = jYearNhanVien.getYear();
							List<ChamCongNVHC> ccnv = App.dlieu.getmaBangChamCong(maNv, thang+1, nam);
//							JOptionPane.showMessageDialog(null, ccnv);
							String maChamCong = ccnv.get(0).getMaChamCong();
							
//							String maNV = txtMaNV.getText();
							String tenNV = nv.getTenNV();
							
							double heSoLuong = Double.parseDouble(txtHeSoLuong.getText());
							int congNgayLe = Integer.parseInt(txtCongNgayLe.getText());
							int congNgayThuong = Integer.parseInt(txtCongNgayThuong.getText());
							int nghiKhongPhep = Integer.parseInt(txtNghiKhongPhep.getText());
							int soCongChuan = 2 * congNgayLe + congNgayThuong - nghiKhongPhep;
							
							double luongLamThem = Double.parseDouble(txtLuongLamThemNV.getText());
							double phuCap = Double.parseDouble(txtPhuCapNV.getText());
							double thuong = Double.parseDouble(txtThuongNV.getText());
							ThuNhapKhac tnk = new ThuNhapKhac(luongLamThem, phuCap, thuong);
							double thuNhapKhac = luongLamThem + phuCap +thuong;
//							ChamCongNVHC nvhc = new ChamCongNVHC(nv, heSoLuong, congNgayLe, congNgayThuong, nghiKhongPhep, thang+1, nam);
							ChamCongNVHC nvhc = new ChamCongNVHC( maChamCong,nv, heSoLuong, congNgayLe, congNgayThuong, nghiKhongPhep, thang+1, nam);
//							JOptionPane.showMessageDialog(null, nvhc);
							nvhc.setTnk(tnk);
							if(App.dlieu.upDateNhanVien(nvhc)) {
								
								jtableNhanVien.setValueAt(maNv, row, 0);
								jtableNhanVien.setValueAt(tenNV, row, 1);
								jtableNhanVien.setValueAt(tach(heSoLuong), row, 2);
								jtableNhanVien.setValueAt(nghiKhongPhep, row, 3);
								jtableNhanVien.setValueAt(congNgayLe, row, 4);
								jtableNhanVien.setValueAt(congNgayThuong, row, 5);
								jtableNhanVien.setValueAt(soCongChuan, row, 6);
								jtableNhanVien.setValueAt(thang, row, 7);
								jtableNhanVien.setValueAt(nam, row, 8);
								jtableNhanVien.setValueAt(tach(thuNhapKhac), row, 9);
								JOptionPane.showMessageDialog(null, "Cập nhập thành công");
							}
							else {
								JOptionPane.showMessageDialog(null, "Fail");
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Đã thanh toán không thể cập nhập!!!");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Không thể vì nhân viên đã nghỉ việc!!!");
				}
		}
	}
    }
    
 // TÌM KIẾM NHÂN VIÊN
 	// TỪ NHÂN VIÊN
 	private void monthTuNhanVienPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {
 		
 		dataNhanVien.getDataVector().removeAllElements();
 		List<ChamCongNVHC> ds = App.dlieu.getThangNam(jMonthTimKiemNV1.getMonth() + 1, jYearTimKiemNV1.getYear(),
 				jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());
 		if (ds.size() > 0) {
 			jtableNhanVien.setModel(dataNhanVien);
 			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
 			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
 			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
 			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
 			for (int i = 0; i < ds.size(); i++) {
 				int soCongThuong = ds.get(i).getSoCongNgayThuong();
 				int soCongLe = ds.get(i).getSoCongNgayLe();
 				int nghiKhongPhep = ds.get(i).getNghiKhongPhep();
 				int soCongChuan = soCongThuong + soCongLe +nghiKhongPhep;
 				
 				double luongLamThem = ds.get(i).getTnk().getLuongLamThem();
 				double phuCap = ds.get(i).getTnk().getPhuCap();
 				double thuong = ds.get(i).getTnk().getThuong();
 				double thuNhapKhac = luongLamThem + phuCap +thuong;
 				dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV().getMaNV(), ds.get(i).getMaNV().getTenNV(),
 						tach(ds.get(i).getHeSoLuong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
 						ds.get(i).getSoCongNgayThuong(), soCongChuan, ds.get(i).getThang(),
 						ds.get(i).getNam(), tach(thuNhapKhac) });
 			}
 		}
 		else {
     			
     	        dataNhanVien.getDataVector().removeAllElements();
     	        
     			jtableNhanVien.setModel(dataNhanVien);
     			jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
     			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
     			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
     			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
     			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
     			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
     			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
     			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
     			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
     			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
     			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
     			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
     			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
     			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
     			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
     			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
     			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
     			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
     			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
 		}
 	}

 	// ĐẾN NHÂN VIÊN
 	private void monthDenNhanVienPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {
 		DefaultTableModel newmodel2 = new DefaultTableModel(
 				new String[] { "Mã nhân viên", "Tên nhân viên", "Hệ số lương", "Nghĩ không phép", "Công ngày lễ",
 						"Công ngày thường", "Số Công Chuẩn", "Tháng", "Năm", "Thu Nhập Khác" },
 				0);
 		dataNhanVien.getDataVector().removeAllElements();
 		List<ChamCongNVHC> ds = App.dlieu.getThangNam(jMonthTimKiemNV1.getMonth() + 1, jYearTimKiemNV1.getYear(),
 				jMonthTimKiemNV2.getMonth() + 1, jYearTimKiemNV2.getYear());

 		if (ds.size() > 0) {
 			jtableNhanVien.setModel(dataNhanVien);
 			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
 			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
 			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
 			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
 			for (int i = 0; i < ds.size(); i++) {
 				int soCongThuong = ds.get(i).getSoCongNgayThuong();
 				int soCongLe = ds.get(i).getSoCongNgayLe();
 				int nghiKhongPhep = ds.get(i).getNghiKhongPhep();
 				int soCongChuan = soCongThuong + soCongLe +nghiKhongPhep;
 				
 				double luongLamThem = ds.get(i).getTnk().getLuongLamThem();
 				double phuCap = ds.get(i).getTnk().getPhuCap();
 				double thuong = ds.get(i).getTnk().getThuong();
 				double thuNhapKhac = luongLamThem + phuCap +thuong;
 				dataNhanVien.addRow(new Object[] { ds.get(i).getMaNV().getMaNV(), ds.get(i).getMaNV().getTenNV(),
 						tach(ds.get(i).getHeSoLuong()), ds.get(i).getNghiKhongPhep(), ds.get(i).getSoCongNgayLe(),
 						ds.get(i).getSoCongNgayThuong(), soCongChuan, ds.get(i).getThang(),
 						ds.get(i).getNam(), tach(thuNhapKhac) });
 			}
 		} else {

//          	JOptionPane.showMessageDialog(null, "Error");
//      		jtableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
//     	            new Object [][] {
//     	                {null, null, null, null},
//     	                {null, null, null, null},
//     	                {null, null, null, null},
//     	                {null, null, null, null}
//     	            },
//     	            new String [] {"Mã nhân viên","Tên nhân viên","Hệ số lương",
//     	     				"Nghĩ không phép","Công ngày lễ","Công ngày thường","Số Công Chuẩn","Tháng","Năm","Thu Nhập Khác"}
//     	        ));
 			dataNhanVien.getDataVector().removeAllElements();
 			jtableNhanVien.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(3).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(4).setPreferredWidth(100);
 			jtableNhanVien.getColumnModel().getColumn(5).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(6).setPreferredWidth(110);
 			jtableNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(8).setPreferredWidth(50);
 			jtableNhanVien.getColumnModel().getColumn(9).setPreferredWidth(122);
 			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
 			rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 			centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableNhanVien.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
 			jtableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
 			jtableNhanVien.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
 		}
 	}
 	
 // TỪ CÔNG NHÂN
 	private void monthTuCongNhanPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {
     	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
 				"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
 		dataCongNhan.getDataVector().removeAllElements();
 		List<NhanVien_CongDoan> dss = App.cnDao.getThangNam(jMonthTimKiemCN1.getMonth() + 1, jYearTimKiemCN2.getYear(),
 				jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
 		if (dss.size() > 0) {
 			jtableCongNhan.setModel(dataCongNhan);
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
 			for (NhanVien_CongDoan nv: dss) {
 				List<CongDoan> cd = App.dlieu.getCongDoan(nv.getMaCongDoan().getMaCongDoan());
 				String maSP = cd.get(0).getMaSanPham().getMaSP();
 				List<SanPham> sp = App.cnDao.getTenSanPham(maSP);
 				String tenSP = sp.get(0).getTenSP();
// 				String maSP = ds.get(0).getMaSanPham().getMaSP();
// 				
// 				List<SanPham> dss = cnDao.getTenSanPham(maSP);
 				double luongLamThem = nv.getTnk().getLuongLamThem();
 				double phuCap = nv.getTnk().getPhuCap();
 				double thuong = nv.getTnk().getThuong();
 				double thuNhapKhac = luongLamThem + phuCap +thuong;
 				dataCongNhan.addRow(new Object[] { nv.getMaNhanVien().getMaNV(), nv.getMaNhanVien().getTenNV(),
 						nv.getMaCongDoan().getTenCongDoan(),
 						tenSP, nv.getSoluong(), nv.getThang(), nv.getNam(),
 						tach(thuNhapKhac) });
 			}
 		}
 		else {
 			jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

 			}, new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế", "Tháng",
 					"Năm", "Thu nhập khác" }));
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
//  	   		 JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");
 		}
 	}

 	// ĐẾN CÔNG NHÂN
 	private void monthDenCongNhanPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {
     	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Công đoạn",
 				"Sản phẩm","Sản phẩm thực tế","Tháng","Năm","Thu nhập khác"},0);
 		dataCongNhan.getDataVector().removeAllElements();
 	
 		List<NhanVien_CongDoan> dss = App.cnDao.getThangNam(jMonthTimKiemCN1.getMonth() + 1, jYearTimKiemCN2.getYear(),
 				jMonthTimKiemCN2.getMonth() + 1, jYearCN2.getYear());
 		if (dss.size() > 0) {
 			jtableCongNhan.setModel(dataCongNhan);
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
 			for (NhanVien_CongDoan nv: dss) {
 				List<CongDoan> cd = App.dlieu.getCongDoan(nv.getMaCongDoan().getMaCongDoan());
 				String maSP = cd.get(0).getMaSanPham().getMaSP();
 				List<SanPham> sp = App.cnDao.getTenSanPham(maSP);
 				String tenSP = sp.get(0).getTenSP();
// 				String maSP = ds.get(0).getMaSanPham().getMaSP();
// 				
// 				List<SanPham> dss = cnDao.getTenSanPham(maSP);
 				double luongLamThem = nv.getTnk().getLuongLamThem();
 				double phuCap = nv.getTnk().getPhuCap();
 				double thuong = nv.getTnk().getThuong();
 				double thuNhapKhac = luongLamThem + phuCap +thuong;
 				dataCongNhan.addRow(new Object[] { nv.getMaNhanVien().getMaNV(), nv.getMaNhanVien().getTenNV(),
 						nv.getMaCongDoan().getTenCongDoan(),
 						tenSP, nv.getSoluong(), nv.getThang(), nv.getNam(),
 						tach(thuNhapKhac) });
 			}
 		} else {
 			jtableCongNhan.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
 					new String[] { "Mã nhân viên", "Tên nhân viên", "Công đoạn", "Sản phẩm", "Sản phẩm thực tế",
 							"Tháng", "Năm", "Thu nhập khác" }));
 			jtableCongNhan.getTableHeader().setBackground(new Color(146, 200, 240));
 			jtableCongNhan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 			jtableCongNhan.getColumnModel().getColumn(0).setPreferredWidth(100);
 			jtableCongNhan.getColumnModel().getColumn(1).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(2).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(3).setPreferredWidth(150);
 			jtableCongNhan.getColumnModel().getColumn(4).setPreferredWidth(120);
 			jtableCongNhan.getColumnModel().getColumn(5).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(6).setPreferredWidth(87);
 			jtableCongNhan.getColumnModel().getColumn(7).setPreferredWidth(150);
 			DefaultTableCellRenderer rightRendererCN = new DefaultTableCellRenderer();
 			rightRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
 			DefaultTableCellRenderer centerRendererCN = new DefaultTableCellRenderer();
 			centerRendererCN.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
 			jtableCongNhan.getColumnModel().getColumn(4).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(5).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(6).setCellRenderer(centerRendererCN);
 			jtableCongNhan.getColumnModel().getColumn(7).setCellRenderer(rightRendererCN);
//  	   		 JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên ");
 		}
 	}
 	
 // CBO TÊN SẢN PHẨM
 	private void cboTenSPActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {
 		String tenSP = (String) cboTenSP.getSelectedItem();
 		loadTenCongDoan(tenSP);
 	}

 	// CBO TÊN CÔNG ĐOẠN
 	public void loadTenCongDoan(String tensp) throws RemoteException {
// 	String tenSP = cboTenSP.getSelectedItem().toString();
 		cboCongDoan.removeAllItems();
 		List<SanPham> sp = App.cnDao.getMaSpTheoTenSP(tensp);
 		String maSP = sp.get(0).getMaSP();
 		
 		List<CongDoan> cd = App.cnDao.getTenCongDoan(maSP);
 		for(int i=0;i<cd.size();i++) {
 			String tenCD = cd.get(i).getTenCongDoan();
 			cboCongDoan.addItem(tenCD);
 		}
 	}

 	public String tach(double luong) {
 		int chucnghin, tramnghin, trieu, nghin, tram, chuc, dvi;
 		trieu = (int) (luong / 1000000);
 		tramnghin = (int) ((luong - (trieu * 1000000)) / 100000);
 		chucnghin = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000)) / 10000);
 		nghin = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000)) / 1000);
 		tram = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)) / 100);
 		chuc = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)
 				- (tram * 100)) / 10);
 		dvi = (int) ((luong - (trieu * 1000000) - (tramnghin * 100000) - (chucnghin * 10000) - (nghin * 1000)
 				- (tram * 100) - (chuc * 10)));

 		if (trieu > 0) {
 			return ("" + trieu + "." + tramnghin + "" + chucnghin + "" + nghin + "." + tram + "" + chuc + "" + dvi);
 		} else if (trieu == 0 && tramnghin > 0) {
 			return ("" + tramnghin + chucnghin + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin > 0) {
 			return ("" + chucnghin + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin > 0) {
 			return ("" + nghin + "." + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 && tram > 0) {
 			return ("" + tram + chuc + dvi);
 		} else if (trieu == 0 && tramnghin == 0 && chucnghin == 0 && nghin == 0 && tram == 0 && chuc > 0) {
 			return ("" + chuc + dvi);
 		} else {
 			return ("" + dvi);
 		}
 	}

 	// TẠO MÃ THU NHẬP KHÁC
 	public String taoMaThuNhapKhac() {

// 		String maCuoi = dlieu.getTHCuoi().getMaThuNhapKhac();
// 		long so = Long.parseLong(maCuoi.substring(2)) + 100000 + 1;
// 		String soMoi = ("" + so).substring(1);
// 		return "TN" + soMoi;
 		return null;
 	}

 //TẠO MÃ NHÂN VIÊN
 	public String taoMaNhanVienHanhChinh() throws RemoteException {
 		
 		List<ChamCongNVHC> ds = App.dlieu.getNVCuoi();
 		if(ds.size()==0)
 		{
 			return "CC00001";
 		}
 		String maCuoi = ds.get(0).getMaChamCong();
 		long so = Long.parseLong(maCuoi.substring(2)) + 100000 + 1;
 		String soMoi = ("" + so).substring(1);
 		return "CC" + soMoi;
 	}

 // XÓA RỖNG NHÂN VIÊN
 	public void xoaRongNhanVien() {
 		txtMaNV.setText("");
 		txtMaNV.setEditable(true);
 		txtHeSoLuong.setText("");
 		txtNghiKhongPhep.setText("");
 		txtCongNgayLe.setText("");
 		txtCongNgayThuong.setText("");
 		txtNghiKhongPhep.setText("");
 		txtLuongLamThemNV.setText("");
 		txtPhuCapNV.setText("");
 		txtThuongNV.setText("");
 		txtMaNV.requestFocus();
 	}

 // Validata NHÂN VIÊN
 	private boolean validataNhanVien() {
 		if (txtMaNV.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Mã nhân viên không được rỗng");
 			txtMaNV.requestFocus();
 			return false;
 		}

 		if (!(txtMaNV.getText().matches("[H][C][0-9]{1,6}"))) {
 			JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ");
 			txtMaNV.requestFocus();
 			txtMaNV.selectAll();
 			return false;
 		}
 		if (txtHeSoLuong.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Hệ số lương không được rỗng");
 			txtHeSoLuong.requestFocus();
 			return false;
 		}
 		if (!(txtHeSoLuong.getText().matches("[0-9]{2,12}"))) {
 			JOptionPane.showMessageDialog(null, "Hệ số lương không hợp lệ");
 			txtHeSoLuong.requestFocus();
 			txtHeSoLuong.selectAll();
 			return false;
 		}
 		if (txtNghiKhongPhep.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Nghỉ không phép không được rỗng");
 			txtNghiKhongPhep.requestFocus();
 			return false;
 		}
 		if (!(txtNghiKhongPhep.getText().matches("[0-9]{1}"))) {
 			JOptionPane.showMessageDialog(null, "Nghĩ không phép không hợp lệ");
 			txtNghiKhongPhep.requestFocus();
 			txtNghiKhongPhep.selectAll();
 			return false;
 		}
 		if (txtCongNgayLe.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Công ngày lễ không được rỗng");
 			txtCongNgayLe.requestFocus();
 			return false;
 		}
 		if (!(txtCongNgayLe.getText().matches("[0-9]{1,5}"))) {
 			JOptionPane.showMessageDialog(null, "Công ngày lễ không hợp lệ");
 			txtCongNgayLe.requestFocus();
 			txtCongNgayLe.selectAll();
 			return false;
 		}
 		if (txtCongNgayThuong.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Công ngày thường không được rỗng");
 			txtCongNgayThuong.requestFocus();
 			return false;
 		}
 		if (!(txtCongNgayThuong.getText().matches("[0-9]{1,3}"))) {
 			JOptionPane.showMessageDialog(null, "Công ngày thường không hợp lệ");
 			txtCongNgayThuong.requestFocus();
 			txtCongNgayThuong.selectAll();
 			return false;
 		}
 		if (!(txtLuongLamThemNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Lương làm thêm không hợp lệ");
 			txtLuongLamThemNV.requestFocus();
 			txtLuongLamThemNV.selectAll();
 			return false;
 		}
 		if (!(txtPhuCapNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Phụ cấp không hợp lệ");
 			txtPhuCapNV.requestFocus();
 			txtPhuCapNV.selectAll();
 			return false;
 		}
 		if (!(txtThuongNV.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Thưởng không hợp lệ");
 			txtThuongNV.requestFocus();
 			txtThuongNV.selectAll();
 			return false;
 		}
//     	if(txtLuongLamThemNV.getText().trim().equals("")) {
//     		int a =0;
//     		String b = String.valueOf(a);
//     		txtLuongLamThemNV.setText(b);
//     		
//     		if(txtPhuCapNV.getText().trim().equals("")) {
//         		int c =0;
//         		String d = String.valueOf(c);
//         		txtPhuCapNV.setText(b);
//         	}
//     		if(txtThuongNV.getText().trim().equals("")) {
// 				int e =0;
//         		String f = String.valueOf(e);
//         		txtThuongNV.setText(b);
// 			}
//     	}
 		return true;
 	}
 	public boolean thuNhapKhac(javax.swing.JTextField txt) {
 		if(txt.getText().trim().equals("")) {
     		int a =0;
     		String b = String.valueOf(a);
     		txt.setText(b);
//     		JOptionPane.showMessageDialog(null, txt.getText());
     		return true;
 		}
 		
 		return true;
 	}
 // VALIDATA CÔNG NHÂN
 	private boolean validataCongNhan() {
 		if (txtMaCongNhan.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Mã công nhân không được rỗng");
 			txtMaCongNhan.requestFocus();
 			return false;
 		}

 		if (!(txtMaCongNhan.getText().matches("[L][D][0-9]{1,6}"))) {
 			JOptionPane.showMessageDialog(null, "Mã công nhân không hợp lệ");
 			txtMaCongNhan.requestFocus();
 			txtMaCongNhan.selectAll();
 			return false;
 		}
 		if (cboTenSP.getSelectedItem() == null) {
 			JOptionPane.showMessageDialog(null, "Vui lòng chọn tên sản phẩm");
 			cboTenSP.requestFocus();
 			return false;
 		}
 		if (cboCongDoan.getSelectedItem() == null) {
 			JOptionPane.showMessageDialog(null, "Vui lòng chọn tên công đoạn");
 			return false;
 		}
 		if (txtSanPhamThucTe.getText().trim().equals("")) {
 			JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không được rỗng");
 			txtSanPhamThucTe.requestFocus();
 			return false;
 		}
 		if (!(txtSanPhamThucTe.getText().matches("[0-9]{0,4}"))) {
 			JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ");
 			txtSanPhamThucTe.requestFocus();
 			txtSanPhamThucTe.selectAll();
 			return false;
 		}
 		if (!(txtLuongLamThemCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Lương làm thêm không hợp lệ");
 			txtLuongLamThemCongNhan.requestFocus();
 			txtLuongLamThemCongNhan.selectAll();
 			return false;
 		}

 		if (!(txtPhuCapCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Phụ cấp không hợp lệ");
 			txtPhuCapCongNhan.requestFocus();
 			txtPhuCapCongNhan.selectAll();
 			return false;
 		}
 		if (!(txtThuongCongNhan.getText().matches("[0-9]{0,12}"))) {
 			JOptionPane.showMessageDialog(null, "Thưởng không hợp lệ");
 			txtThuongCongNhan.requestFocus();
 			txtThuongCongNhan.selectAll();
 			return false;
 		}
 		if (txtLuongLamThemCongNhan.getText().trim().equals("")) {
 			int a = 0;
 			String b = String.valueOf(a);
 			txtLuongLamThemCongNhan.setText(b);

 			if (txtPhuCapCongNhan.getText().trim().equals("")) {
 				int c = 0;
 				String d = String.valueOf(c);
 				txtPhuCapCongNhan.setText(b);
 			}
 			if (txtThuongCongNhan.getText().trim().equals("")) {
 				int e = 0;
 				String f = String.valueOf(e);
 				txtThuongCongNhan.setText(b);
 			}
 		}
 		return true;
 	}
//  XÓA KHOẢNG TRẮNG
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhapCongNhan;
    private javax.swing.JButton btnCapNhapNhanVien;
    private java.awt.Button btnCongNhan;
    private javax.swing.JButton btnLamMoiCongNhan;
    private javax.swing.JButton btnLamMoiNhanVien;
    private javax.swing.JButton btnLamXoaRongCN;
    private java.awt.Button btnNhanVien;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemThemCongNhan;
    private javax.swing.JButton btnTimKiemCN;
    private javax.swing.JButton btnTimNV;
    private javax.swing.JButton btnXoaRongNhanVien;
    private javax.swing.JComboBox<String> cboCongDoan;
    private javax.swing.JComboBox<String> cboTenSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JMonthChooser jMonthCongNhan;
    private com.toedter.calendar.JMonthChooser jMonthNhanVien;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemCN1;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemCN2;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemNV1;
    private com.toedter.calendar.JMonthChooser jMonthTimKiemNV2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JYearChooser jYearCN2;
    private com.toedter.calendar.JYearChooser jYearCongNhan;
    private com.toedter.calendar.JYearChooser jYearNhanVien;
    private com.toedter.calendar.JYearChooser jYearTimKiemCN2;
    private com.toedter.calendar.JYearChooser jYearTimKiemNV1;
    private com.toedter.calendar.JYearChooser jYearTimKiemNV2;
    private javax.swing.JPanel jpn;
    private javax.swing.JPanel jpnCongNhan;
    private javax.swing.JPanel jpnNhanVien;
    private javax.swing.JTable jtableCongNhan;
    private javax.swing.JTable jtableNhanVien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblmaNV;
    private javax.swing.JLabel lblmaNV1;
    private javax.swing.JLabel lblmaNV10;
    private javax.swing.JLabel lblmaNV11;
    private javax.swing.JLabel lblmaNV12;
    private javax.swing.JLabel lblmaNV13;
    private javax.swing.JLabel lblmaNV14;
    private javax.swing.JLabel lblmaNV15;
    private javax.swing.JLabel lblmaNV2;
    private javax.swing.JLabel lblmaNV3;
    private javax.swing.JLabel lblmaNV4;
    private javax.swing.JLabel lblmaNV5;
    private javax.swing.JLabel lblmaNV6;
    private javax.swing.JLabel lblmaNV7;
    private javax.swing.JLabel lblmaNV8;
    private javax.swing.JLabel lblmaNV9;
    private java.awt.Panel panel2;
    private java.awt.Panel panel6;
    private javax.swing.JPanel panelttNV;
    private javax.swing.JPanel panelttNV1;
    private javax.swing.JPanel panelttNV2;
    private javax.swing.JPanel panelttNV3;
    private javax.swing.JPanel panelttNV4;
    private javax.swing.JPanel panelttNV5;
    private java.awt.TextField textField1;
    private java.awt.TextField textField5;
    private javax.swing.JTextField txtCongNgayLe;
    private javax.swing.JTextField txtCongNgayThuong;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtLuongLamThemCongNhan;
    private javax.swing.JTextField txtLuongLamThemNV;
    private javax.swing.JTextField txtMaCongNhan;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNghiKhongPhep;
    private javax.swing.JTextField txtPhuCapCongNhan;
    private javax.swing.JTextField txtPhuCapNV;
    private javax.swing.JTextField txtSanPhamThucTe;
    private javax.swing.JTextField txtThuongCongNhan;
    private javax.swing.JTextField txtThuongNV;
    private javax.swing.JTextField txtTimKiemCN;
    private javax.swing.JTextField txtTimNhanVIen;
    // End of variables declaration//GEN-END:variables
}
