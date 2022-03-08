package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class CongDoan implements Serializable {
	
	@Id
	private String maCongDoan;
	@Column(columnDefinition = "Nvarchar(255)")
	private String tenCongDoan;
	
	private double donGia;	
	
	@OneToMany(mappedBy = "maCongDoan")
	private Set<NhanVien_CongDoan> dsnvcd;
	
	
	@ManyToOne 
	@JoinColumn(name = "maSanPham")
	private SanPham maSanPham;

	public CongDoan() {
		// TODO Auto-generated constructor stub
	}
	public String getMaCongDoan() {
		return maCongDoan;
	}


	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
	}


	public String getTenCongDoan() {
		return tenCongDoan;
	}


	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}


	public double getDonGia() {
		return donGia;
	}


	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}


	public SanPham getMaSanPham() {
		return maSanPham;
	}


	public void setMaSanPham(SanPham maSanPham) {
		this.maSanPham = maSanPham;
	}


	public CongDoan(String maCongDoan, String tenCongDoan, double donGia, Set<NhanVien_CongDoan> dsnvcd,
			SanPham maSanPham) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.dsnvcd = dsnvcd;
		this.maSanPham = maSanPham;
	}
	
	public CongDoan(String maCongDoan, String tenCongDoan, double donGia, SanPham maSanPham) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.maSanPham = maSanPham;
	}
	public CongDoan(String maCongDoan, String tenCongDoan, Float donGia) {
		super();
		this.maCongDoan = maCongDoan;
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
	}
	@Override
	public String toString() {
		return "CongDoan [maCongDoan=" + maCongDoan + ", tenCongDoan=" + tenCongDoan + ", donGia=" + donGia
				+ ", maSanPham=" + maSanPham + "]";
	}


	
	
	
	
}
