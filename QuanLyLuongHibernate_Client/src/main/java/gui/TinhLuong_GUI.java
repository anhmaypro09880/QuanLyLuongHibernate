/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import app.App;
import dao.BangLuongDao;
import dao.NhanVienDao;
import dao.TinhLuongDao;

import entity.BangLuong;
import entity.ChamCongNVHC;
import entity.CongDoan;
import entity.NhanVien;
import entity.NhanVien_CongDoan;
import entity.SanPham;

/**
 *
 * @author tcao2
 */

public class TinhLuong_GUI extends javax.swing.JPanel implements ActionListener {
	
    private DefaultTableModel modelCN;
	private JPanel jPanel15;
	private JLabel jLabel15;
	private JLabel jLabel16;
	
	private JLabel jLabel17;
	
	
	private JButton btnTim3;
	private JMonthChooser monthTuNV3;
	private JYearChooser yearTuNV3;
	private JMonthChooser monthDenNV3;
	private JPanel TKCN;
	private JYearChooser yearDenNV3;
	
	
	private JMonthChooser monthTuCN;
	private JYearChooser yearTuCN;
	private JYearChooser yearDenCN;
	private JMonthChooser monthDenCN;
	private JPanel TKNV;
	private JTextField txtTKNV;
	private JTextField txtTKCN;
	private DefaultTableModel modelNV;
	private JButton btnXuatFileNV;
	private JButton btnTinhLuongNV;
	private JButton btnXuatFileCN;
	private JButton btnTinhluongCN;
	
	private TinhLuongDao tl_dao = App.tlDao;
	private	BangLuongDao bl_dao = App.blDao;
	private NhanVienDao nv_dao = App.nvDao;
	
	/**
     * Creates new form TinhLuong_GUI
     */
    public TinhLuong_GUI() throws RemoteException {
        initComponents();
        btnCN.setFocusPainted(false);
        btnNV.setFocusPainted(false);
        btnCN.setBackground(new Color(51,153,255));
        TKCN.setVisible(true);
        TKNV.setVisible(false);
        PanelCN.setVisible(true);
        PanelNV.setVisible(false);
        modelCN = new DefaultTableModel(new String[] {"Mã nhân viên", "Tên nhân viên","Sản phẩm",
				"Công đoạn", "Số lượng", "Thu nhập khác", "Tháng","Năm","Tổng lương","Tình trạng"}, 0);
        
       
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
       
        DecimalFormat format = new DecimalFormat("0.#");
        
       
        
        jTable1.setModel(modelCN);
        jTable1.getTableHeader().setBackground(new Color(146, 200, 240));
        
		jTable1.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		jTable1.setFont(new Font("Arial", Font.PLAIN, 13));
		

	
		JTableHeader h = jTable1.getTableHeader();
		h.setFont(new Font("Arial", Font.BOLD, 14));
		h.setForeground(new Color(255, 255, 255));
		h.setBackground(new Color(146, 200, 240));
		jTable1.setRowHeight(jTable1.getRowHeight()+20);
//		jTable1.
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(112);
		jTable1.getColumnModel().getColumn(3).setPreferredWidth(113);
		jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
		jTable1.getColumnModel().getColumn(5).setPreferredWidth(107);
		jTable1.getColumnModel().getColumn(6).setPreferredWidth(68);
		jTable1.getColumnModel().getColumn(7).setPreferredWidth(70);
		jTable1.getColumnModel().getColumn(8).setPreferredWidth(110);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setVerticalAlignment(DefaultTableCellRenderer.CENTER);
		
		jTable1.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		jTable1.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jTable1.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		jTable1.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		monthDenCN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
					monthDenCNPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		monthTuCN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
					monthTuCNPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		monthDenNV3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
					monthDenNVPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		monthTuNV3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                try {
					monthTuNVPropertyChange(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		btnTim3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnTimNVActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnTimActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jTable2.getTableHeader().setBackground(new Color(146, 200, 240));
        
		jTable2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));
		jTable2.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JTableHeader h1 = jTable2.getTableHeader();
		h1.setFont(new Font("Arial", Font.BOLD, 14));
		h1.setForeground(new Color(255, 255, 255));
		h1.setBackground(new Color(146, 200, 240));
		
		jTable2.setRowHeight(jTable2.getRowHeight()+20);
		modelNV = new DefaultTableModel(new String[] {"Mã nhân viên", "Tên nhân viên","Hệ số lương", "Số công chuẩn","Thu nhập khác","Tháng","Năm","Tổng lương","Tình trạng"}, 0);
		
		
		jTable2.setModel(modelNV);
		jTable2.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTable2.getColumnModel().getColumn(3).setPreferredWidth(90);
		jTable2.getColumnModel().getColumn(4).setPreferredWidth(90);
//		
//		jTable2.getColumnModel().getColumn(6).setPreferredWidth(100);
//		jTable2.getColumnModel().getColumn(7).setPreferredWidth(100);
//		jTable2.getColumnModel().getColumn(8).setPreferredWidth(50);
//		jTable2.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		btnTim.setBackground(new java.awt.Color(153, 255, 255));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Find.png")));
        btnTim.setText("");
        btnTim3.setBackground(new java.awt.Color(153, 255, 255));
        btnTim3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photo for Design Form/Find.png")));
        btnTim3.setText("");
        btnTinhluongCN.setBackground(new java.awt.Color(153, 255, 255));
        btnTinhLuongNV.setBackground(new java.awt.Color(153, 255, 255));
        btnXuatFileCN.setBackground(new java.awt.Color(153, 255, 255));
        btnXuatFileNV.setBackground(new java.awt.Color(153, 255, 255));
     
		btnTinhluongCN.addActionListener(this);
		btnTinhLuongNV.addActionListener(this);
		btnXuatFileCN.addActionListener(this);
		btnXuatFileNV.addActionListener(this);
		
		btnTinhluongCN.setBackground(new java.awt.Color(228, 237, 225));
		btnTinhLuongNV.setBackground(new java.awt.Color(228, 237, 225));
		btnXuatFileCN.setBackground(new java.awt.Color(228, 237, 225));
		btnXuatFileNV.setBackground(new java.awt.Color(228, 237, 225));
		btnTim.setBackground(new java.awt.Color(228, 237, 225));
		btnTim3.setBackground(new java.awt.Color(228, 237, 225));
		btnTinhLuongNV.setText("Tính Lương");
		
		jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		jTable2.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		jTable2.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		jTable2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		jTable2.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnNV = new javax.swing.JButton();
        btnCN = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jpNV = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpCN = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        TKNV = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTKNV = new javax.swing.JTextField();
        monthTuNV3 = new com.toedter.calendar.JMonthChooser();
        yearTuNV3 = new com.toedter.calendar.JYearChooser();
        monthDenNV3 = new com.toedter.calendar.JMonthChooser();
        yearDenNV3 = new com.toedter.calendar.JYearChooser();
        btnTim3 = new javax.swing.JButton();
        btnXuatFileNV = new javax.swing.JButton();
        btnTinhLuongNV = new javax.swing.JButton();
        TKCN = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTKCN = new javax.swing.JTextField();
        yearTuCN = new com.toedter.calendar.JYearChooser();
        monthDenCN = new com.toedter.calendar.JMonthChooser();
        yearDenCN = new com.toedter.calendar.JYearChooser();
        monthTuCN = new com.toedter.calendar.JMonthChooser();
        btnTim = new javax.swing.JButton();
        btnTinhluongCN = new javax.swing.JButton();
        btnXuatFileCN = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        PanelNV = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        PanelCN = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(23,35,51));

        btnNV.setBackground(new java.awt.Color(40,57,88));
        btnNV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNV.setForeground(new java.awt.Color(255, 255, 255));
        btnNV.setText("Nhân viên");
        btnNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNVActionPerformed(evt);
            }
        });

        btnCN.setBackground(new java.awt.Color(51,153,255));
        btnCN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCN.setForeground(new java.awt.Color(255, 255, 255));
        btnCN.setText("Công nhân");
        btnCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCN, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCN, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNV, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jpNV.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lương nhân viên hành chính");

        javax.swing.GroupLayout jpNVLayout = new javax.swing.GroupLayout(jpNV);
        jpNV.setLayout(jpNVLayout);
        jpNVLayout.setHorizontalGroup(
            jpNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNVLayout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(432, Short.MAX_VALUE))
        );
        jpNVLayout.setVerticalGroup(
            jpNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNVLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpCN.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Lương công nhân");

        javax.swing.GroupLayout jpCNLayout = new javax.swing.GroupLayout(jpCN);
        jpCN.setLayout(jpCNLayout);
        jpCNLayout.setHorizontalGroup(
            jpCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCNLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(469, Short.MAX_VALUE))
        );
        jpCNLayout.setVerticalGroup(
            jpCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCNLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 5));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        TKNV.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jLabel15.setText("Từ");

        jLabel16.setText("Đến");

        jLabel17.setText("Tìm kiếm");

        btnTim3.setText("jButton1");

        btnXuatFileNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXuatFileNV.setText("Xuất Fille");

        btnTinhLuongNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnTinhLuongNV.setText("Tính lương");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(monthDenNV3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(monthTuNV3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yearTuNV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yearDenNV3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTim3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnTinhLuongNV, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatFileNV, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthTuNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearTuNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthDenNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearDenNV3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTKNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXuatFileNV, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnTinhLuongNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TKNVLayout = new javax.swing.GroupLayout(TKNV);
        TKNV.setLayout(TKNVLayout);
        TKNVLayout.setHorizontalGroup(
            TKNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TKNVLayout.setVerticalGroup(
            TKNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TKNVLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 137, Short.MAX_VALUE))
        );

        TKCN.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jLabel2.setText("Từ");

        jLabel3.setText("Đến");

        jLabel4.setText("Tìm kiếm");

        btnTim.setText("jButton1");

        btnTinhluongCN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnTinhluongCN.setText("Tính Lương");

        btnXuatFileCN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXuatFileCN.setText("Xuất File");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(monthDenCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(yearDenCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(monthTuCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(yearTuCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtTKCN, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(35, 35, 35))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTinhluongCN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatFileCN, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthTuCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearTuCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthDenCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(yearDenCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTKCN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTinhluongCN, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(btnXuatFileCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TKCNLayout = new javax.swing.GroupLayout(TKCN);
        TKCN.setLayout(TKCNLayout);
        TKCNLayout.setHorizontalGroup(
            TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TKCNLayout.setVerticalGroup(
            TKCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TKCNLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TKCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TKNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TKCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(TKNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelNV.setBackground(new java.awt.Color(255, 255, 255));
        PanelNV.setBorder(javax.swing.BorderFactory.createTitledBorder("Lương nhân viên"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout PanelNVLayout = new javax.swing.GroupLayout(PanelNV);
        PanelNV.setLayout(PanelNVLayout);
        PanelNVLayout.setHorizontalGroup(
            PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        PanelNVLayout.setVerticalGroup(
            PanelNVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        PanelCN.setBackground(new java.awt.Color(255, 255, 255));
        PanelCN.setBorder(javax.swing.BorderFactory.createTitledBorder("Lương công nhân"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout PanelCNLayout = new javax.swing.GroupLayout(PanelCN);
        PanelCN.setLayout(PanelCNLayout);
        PanelCNLayout.setHorizontalGroup(
            PanelCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        PanelCNLayout.setVerticalGroup(
            PanelCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }/// </editor-fold>//GEN-END:initComponents

    private void btnCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNActionPerformed
            // TODO add your handling code here:
            jpCN.setVisible(true);
            jpNV.setVisible(false);
            PanelCN.setVisible(true);
            PanelNV.setVisible(false);
            TKCN.setVisible(true);
            TKNV.setVisible(false);
            btnCN.setBackground(new Color(51,153,255));
            btnNV.setBackground(new java.awt.Color(40,57,88));
    }//GEN-LAST:event_btnCNActionPerformed

    private void btnNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNVActionPerformed
        // TODO add your handling code here:
            jpCN.setVisible(false);
            jpNV.setVisible(true);
            PanelCN.setVisible(false);
            PanelNV.setVisible(true);
            TKCN.setVisible(false);
            TKNV.setVisible(true);
            btnNV.setBackground(new Color(51,153,255));
            btnCN.setBackground(new java.awt.Color(40,57,88));
    }//GEN-LAST:event_btnNVActionPerformed
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException
    {
//    	DefaultTableModel newmodel = new DefaultTableModel(new String[] {"Mã nhân viên", "Tên nhân viên","Sản phẩm",
//				"Công đoạn", "Số lượng", "Thu nhập khác", "Tháng","Năm","Tổng lương","Tình trạng"}, 0);
//    	modelCN.getDataVector().removeAllElements();
    	
    	List<NhanVien_CongDoan> ds = tl_dao.TimKiem(monthTuCN.getMonth()+1,yearTuCN.getYear(),monthDenCN.getMonth()+1,yearDenCN.getYear(), txtTKCN.getText());
    	if(ds.size()>0)
    	{
    		modelCN.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			SanPham sp = ds.get(i).getMaCongDoan().getMaSanPham();
    			modelCN.addRow(new Object[] {ds.get(i).getMaNhanVien().getMaNV(),ds.get(i).getMaNhanVien().getTenNV(),ds.get(i).getMaCongDoan().getMaSanPham().getTenSP(),ds.get(i).getMaCongDoan().getTenCongDoan(),ds.get(i).getSoluong(),tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())),ds.get(i).getThang(),ds.get(i).getNam(),
        				tach(((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())+(ds.get(i).getSoluong()*ds.get(i).getMaCongDoan().getDonGia()))),tinhTrang	
        			});
    		}
    	}
    	else
    	{
    		modelCN.getDataVector().removeAllElements();
    		modelCN.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
    	}
    	
    	
    	
    }
    private void btnTimNVActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException
    {	
    	
//    	List<ChamCongNVHC> ds = tl_dao.getChamCongNVHC(monthTuNV3.getMonth()+1, yearTuNV3.getYear(), monthDenNV3.getMonth()+1, yearDenNV3.getYear());
    	List<ChamCongNVHC> ds = tl_dao.TimKiemNV(monthTuNV3.getMonth()+1,yearTuNV3.getYear(),monthDenNV3.getMonth()+1,yearDenNV3.getYear(),txtTKNV.getText());
    	if(ds.size()>0)
    	{
    		modelNV.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			modelNV.addRow(new Object[] {ds.get(i).getMaNV().getMaNV(),ds.get(i).getMaNV().getTenNV(),tach(ds.get(i).getHeSoLuong()),
    					(ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep()),
    					tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),
    					ds.get(i).getThang(),ds.get(i).getNam(),tach(((ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep())*ds.get(i).getHeSoLuong())+(ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),tinhTrang	
    			});
    		}
    	}
    	else
    	{
    		modelNV.getDataVector().removeAllElements();
    		modelNV.addRow(new Object[] {null,null,null,null,null,null,null,null});
    	}
    	
    }
    
    private void monthDenCNPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {                                           
    	
    	List<NhanVien_CongDoan> ds = tl_dao.getNhanVien_CongDoan(monthTuCN.getMonth()+1, yearTuCN.getYear(), monthDenCN.getMonth()+1, yearDenCN.getYear());
    	if(ds.size()>0)
    	{
    		modelCN.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			modelCN.addRow(new Object[] {ds.get(i).getMaNhanVien().getMaNV(),ds.get(i).getMaNhanVien().getTenNV(),ds.get(i).getMaCongDoan().getMaSanPham().getTenSP(),ds.get(i).getMaCongDoan().getTenCongDoan(),ds.get(i).getSoluong(),tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())),ds.get(i).getThang(),ds.get(i).getNam(),
        				tach(((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())+(ds.get(i).getSoluong()*ds.get(i).getMaCongDoan().getDonGia()))),tinhTrang	
        			});
    		}
    	}
    	else
    	{
    		modelCN.getDataVector().removeAllElements();
    		modelCN.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
    	}
    	
    }
    
    private void monthTuCNPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException {                                           
   
    	List<NhanVien_CongDoan> ds = tl_dao.getNhanVien_CongDoan(monthTuCN.getMonth()+1, yearTuCN.getYear(), monthDenCN.getMonth()+1, yearDenCN.getYear());
    	if(ds.size()>0)
    	{
    		modelCN.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			modelCN.addRow(new Object[] {ds.get(i).getMaNhanVien().getMaNV(),ds.get(i).getMaNhanVien().getTenNV(),ds.get(i).getMaCongDoan().getMaSanPham().getTenSP(),ds.get(i).getMaCongDoan().getTenCongDoan(),ds.get(i).getSoluong(),tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())),ds.get(i).getThang(),ds.get(i).getNam(),
    				tach(((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getLuongLamThem()+ds.get(i).getTnk().getThuong())+(ds.get(i).getSoluong()*ds.get(i).getMaCongDoan().getDonGia()))),tinhTrang	
    			});
    		}
    	}
    	else
    	{
    		modelCN.getDataVector().removeAllElements();
    		modelCN.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
    	}
    		
    }
    
    
    
    
    public String tach(double luong)
    {
    	int chucnghin,tramnghin,trieu,nghin,tram,chuc,dvi;
        trieu=(int)  (luong/1000000);
        tramnghin =  (int)((luong-(trieu*1000000))/100000);
        chucnghin= (int) ((luong-(trieu*1000000)-(tramnghin*100000))/10000);
        nghin = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000))/1000);
        tram =(int) ((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000))/100);
        chuc = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000) - (tram*100))/10);
        dvi = (int)((luong - (trieu*1000000)-(tramnghin*100000) - (chucnghin*10000) - (nghin*1000) - (tram*100)-(chuc *10)));
        
        if(trieu>0)
        {
        return (""+trieu+"."+tramnghin+""+chucnghin+""+nghin+"."+tram+""+chuc+""+dvi);
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

    
    private void monthDenNVPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException
    {
    	
    	List<ChamCongNVHC> ds = tl_dao.getChamCongNVHC(monthTuNV3.getMonth()+1, yearTuNV3.getYear(), monthDenNV3.getMonth()+1, yearDenNV3.getYear());
    	if(ds.size()>0)
    	{
    		modelNV.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			modelNV.addRow(new Object[] {ds.get(i).getMaNV().getMaNV(),ds.get(i).getMaNV().getTenNV(),tach(ds.get(i).getHeSoLuong()),
    					(ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep()),
    					tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),
    					ds.get(i).getThang(),ds.get(i).getNam(),tach(((ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep())*ds.get(i).getHeSoLuong())+(ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),tinhTrang	
    			});
    		}
    	}
    	else
    	{
    		modelNV.getDataVector().removeAllElements();
    		modelNV.addRow(new Object[] {null,null,null,null,null,null,null,null});
    	}
    
    }
    
    private void monthTuNVPropertyChange(java.beans.PropertyChangeEvent evt) throws RemoteException
    {	
    	
    	List<ChamCongNVHC> ds = tl_dao.getChamCongNVHC(monthTuNV3.getMonth()+1, yearTuNV3.getYear(), monthDenNV3.getMonth()+1, yearDenNV3.getYear());
    	if(ds.size()>0)
    	{
    		modelNV.getDataVector().removeAllElements();
    		for(int i = 0; i<ds.size();i++)
    		{
    			String tinhTrang = null; 
    			if(ds.get(i).isTinhTrang())
    			{
    				tinhTrang = "Đã tính";
    			}
    			else
    			{
    				tinhTrang = "Chưa tính";
    			}
    			modelNV.addRow(new Object[] {ds.get(i).getMaNV().getMaNV(),ds.get(i).getMaNV().getTenNV(),tach(ds.get(i).getHeSoLuong()),
    					(ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep()),
    					tach((ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),
    					ds.get(i).getThang(),ds.get(i).getNam(),tach(((ds.get(i).getSoCongNgayLe()*2+ds.get(i).getSoCongNgayThuong()-ds.get(i).getNghiKhongPhep())*ds.get(i).getHeSoLuong())+(ds.get(i).getTnk().getPhuCap()+ds.get(i).getTnk().getThuong()+ds.get(i).getTnk().getLuongLamThem())),tinhTrang	
    			});
    		}
    	}
    	else
    	{
    		modelNV.getDataVector().removeAllElements();
    		modelNV.addRow(new Object[] {null,null,null,null,null,null,null,null});
    	}
    	
    }
    
    
    private javax.swing.JPanel PanelCN;
    private javax.swing.JPanel PanelNV;
    private javax.swing.JButton btnCN;
    private javax.swing.JButton btnNV;
    private JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
 
    
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private javax.swing.JPanel jpCN;
    private javax.swing.JPanel jpNV;
    // End of variables declaration//GEN-END:variables
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnTinhluongCN))
		{	int row = jTable1.getSelectedRow();
			
//			
			
			if(row<0)
			{
				JOptionPane.showMessageDialog(null, "Phải chọn nhân viên muốn tính lương");
				
			}
			else
			{
				
			
			if(jTable1.getValueAt(row, 9).toString().equals("Chưa tính"))
			{
				
				
//				NhanVien_CongDoan nvcd = new NhanVien_CongDoan(new NhanVien(jTable1.getValueAt(row, 0).toString(),null),
//						Integer.parseInt(jTable1.getValueAt(row, 6).toString()) ,Integer.parseInt(jTable1.getValueAt(row, 7).toString()));
				NhanVien_CongDoan nvcd = new NhanVien_CongDoan();
			
//				nvcd.setMaNhanVien(new NhanVien(jTable1.getValueAt(row, 0).toString()));
//				nvcd.setThang(Integer.parseInt(jTable1.getValueAt(row, 6).toString()));
//				nvcd.setNam(Integer.parseInt(jTable1.getValueAt(row, 7).toString()));
//				nvcd.setMaCongDoan(new CongDoan("CD000001"));
//				nvcd.setTinhTrang(true);
				
			
				try {
					tl_dao.TinhLuong(jTable1.getValueAt(row, 0).toString(),Integer.parseInt(jTable1.getValueAt(row, 6).toString()),Integer.parseInt(jTable1.getValueAt(row, 7).toString()));
					JOptionPane.showMessageDialog(null, "Tính lương thành công");
					modelCN.setValueAt("Đã tính", row, 9);
				} catch (Exception e2) {
					// TODO: handle exception
				}
		
				String ma = null;
				try {
					ma = taoMaBangLuong();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				BangLuong bl = new BangLuong();
				bl.setMaBL(ma);
				bl.setLuong(Double.parseDouble(XoaKhoangTrang(jTable1.getValueAt(row, 8).toString())) );
				bl.setThang(Integer.parseInt(jTable1.getValueAt(row, 6).toString()));
				bl.setNam(Integer.parseInt(jTable1.getValueAt(row, 7).toString()));
				
				
				NhanVien nv = null;
				try {
					 nv = nv_dao.GetNVTheoMa(jTable1.getValueAt(row, 0).toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				bl.setNhanVien(nv);
//				JOptionPane.showMessageDialog(null, bl);
				try {
					bl_dao.ThemBangLuong(bl);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				JOptionPane.showMessageDialog(null, ma);
//				try {
//					JOptionPane.showMessageDialog(null, bl_dao.GetMaCuoiBL());
//				} catch (HeadlessException | RemoteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
//				String ma = taoMaBangLuong();
//				BangLuong bl = new BangLuong(ma,Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 6).toString()) ) ,Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 8).toString())) , Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 7).toString()) ));
//				bl.setNhanVien(new NhanVien(jTable1.getValueAt(row, 0).toString(),null));
//			
//				bl.setMaBL(ma);
//				bl.setLuong(Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 8).toString()) ));
//				bl.setThang(Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 6).toString()) ));
//				bl.setNam(Integer.parseInt(XoaKhoangTrang(jTable1.getValueAt(row, 7).toString()) ));
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nhân viên đã được tính lương");
			}
			}
			
			
			
		}
		if(obj.equals(btnTinhLuongNV))
		{
			int row = jTable2.getSelectedRow();
			if(row<0)
			{
				JOptionPane.showMessageDialog(null, "Phải chọn nhân viên muốn tính lương");
			}
			else
			{
				
			
			
			if(jTable2.getValueAt(row, 8).toString().equals("Chưa tính"))
			{
				
//				ChamCongNVHC cc = new ChamCongNVHC();
//				cc.setMaNV(new NhanVien(jTable2.getValueAt(row, 0).toString(),null));
//				cc.setThang(Integer.parseInt(jTable2.getValueAt(row, 5).toString()));
//				cc.setNam(Integer.parseInt(jTable2.getValueAt(row, 6).toString()));
				
				try {
					tl_dao.TinhLuongNV(jTable2.getValueAt(row, 0).toString(),Integer.parseInt(jTable2.getValueAt(row, 5).toString()),Integer.parseInt(jTable2.getValueAt(row, 6).toString()) );
					JOptionPane.showMessageDialog(null, "Tính lương thành công");
					modelNV.setValueAt("Đã tính", row, 8);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				BangLuong bl = new BangLuong();
				String ma = null;
				try {
					ma = taoMaBangLuong();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				bl.setMaBL(ma);
				
				NhanVien nv = null ;
				try {
					 nv  =nv_dao.GetNVTheoMa(jTable2.getValueAt(row, 0).toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bl.setNhanVien(nv);
//				bl.setNhanVien(new NhanVien(jTable2.getValueAt(row, 0).toString(),null));
				bl.setLuong(Integer.parseInt(XoaKhoangTrang(jTable2.getValueAt(row, 7).toString()) ));
				bl.setThang(Integer.parseInt(XoaKhoangTrang(jTable2.getValueAt(row, 5).toString()) ));
				bl.setNam(Integer.parseInt(XoaKhoangTrang(jTable2.getValueAt(row, 6).toString()) ));
				
				try {
					bl_dao.ThemBangLuong(bl);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Nhân viên đã được tính lương");
			}
		}
			
		}
		
		if(obj.equals(btnXuatFileCN))
		{
			if(jTable1.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Chưa có nội dung để xuất file");
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
		}
		if(obj.equals(btnXuatFileNV))
		{
			if(jTable2.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Chưa có nội dung để xuất file");
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
					boolean t = ghiRaFileExcelNV(path);
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
		}
		
		
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
	private boolean ghiRaFileExcel(String path) {
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sh = workbook.createSheet("Sheet1");
		String header[] = {"Mã nhân viên", "Tên nhân viên","Sản phẩm",
				"Công đoạn", "Số lượng", "Thu nhập khác", "Tháng","Năm","Tổng lương","Tình trạng"};
		
		Row rowHeader = sh.createRow(0);
		for(int i = 0; i< header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		int numberRow = 1;
		String ma,ten,sp,congdoan,soluong,tnk,thang,nam,tongluong,tinhtrang;
		
		
		
		int dem = jTable1.getRowCount();
		for (int i = 0 ; i<dem;i++) {
			ma = jTable1.getValueAt(i, 0).toString();
			ten = jTable1.getValueAt(i, 1).toString();
			sp = jTable1.getValueAt(i, 2).toString();
			congdoan = jTable1.getValueAt(i, 3).toString();
			soluong = jTable1.getValueAt(i, 4).toString();
			tnk = jTable1.getValueAt(i, 5).toString();
			thang = jTable1.getValueAt(i, 6).toString();
			nam = jTable1.getValueAt(i, 7).toString();
			tongluong = jTable1.getValueAt(i, 8).toString();
			tinhtrang = jTable1.getValueAt(i, 9).toString();
			Row row = sh.createRow(numberRow++);
			row.createCell(0).setCellValue(ma);
			row.createCell(1).setCellValue(ten);
			row.createCell(2).setCellValue(sp);
			row.createCell(3).setCellValue(congdoan);
			row.createCell(4).setCellValue(soluong);
			row.createCell(5).setCellValue(tnk);
			row.createCell(6).setCellValue(thang);
			row.createCell(7).setCellValue(nam);
			row.createCell(8).setCellValue(tongluong);
			row.createCell(9).setCellValue(tinhtrang);
			
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
	
	private boolean ghiRaFileExcelNV(String path) {
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sh = workbook.createSheet("Sheet1");
		String header[] = {"Mã nhân viên", "Tên nhân viên","Hệ số lương",
				"Số công chuẩn","Thu nhập khác", "Tháng","Năm","Tổng lương","Tình trạng"};
		
		Row rowHeader = sh.createRow(0);
		for(int i = 0; i< header.length; i++) {
			Cell cell = rowHeader.createCell(i);
			cell.setCellValue(header[i]);
		}
		
		int numberRow = 1;
		String ma,ten,hsl,scc,tnk,thang,nam,tongluong,tinhtrang;
		
		
		
		int dem = jTable2.getRowCount();
		for (int i = 0 ; i<dem;i++) {
			ma = jTable2.getValueAt(i, 0).toString();
			ten = jTable2.getValueAt(i, 1).toString();
			hsl = jTable2.getValueAt(i, 2).toString();
			scc = jTable2.getValueAt(i, 3).toString();
			
			tnk = jTable2.getValueAt(i, 4).toString();
			thang = jTable2.getValueAt(i, 5).toString();
			nam = jTable2.getValueAt(i, 6).toString();
			tongluong = jTable2.getValueAt(i, 7).toString();
			tinhtrang = jTable2.getValueAt(i, 8).toString();
			Row row = sh.createRow(numberRow++);
			row.createCell(0).setCellValue(ma);
			row.createCell(1).setCellValue(ten);
			row.createCell(2).setCellValue(hsl);
			row.createCell(3).setCellValue(scc);
			
			row.createCell(4).setCellValue(tnk);
			row.createCell(5).setCellValue(thang);
			row.createCell(6).setCellValue(nam);
			row.createCell(7).setCellValue(tongluong);
			row.createCell(8).setCellValue(tinhtrang);
			
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
	
	static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		WordWrapCellRenderer() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(value.toString());
	        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
	        setFont(table.getFont());
	        if (table.getRowHeight(row) != getPreferredSize().height) {
	            table.setRowHeight(row, getPreferredSize().height);
	        }
	        return this;
	    }
	}
	public String taoMaBangLuong() throws RemoteException {
		
		String maCuoi = bl_dao.GetMaCuoiBL();
		if(!maCuoi.equals(""))
		{
			long so = Long.parseLong(maCuoi.substring(2)) + 1000000 + 1;
			String soMoi = ("" + so).substring(1);
			return "BL" + soMoi;
		}
		else
			return "BL000001";
		
	}
	
}
