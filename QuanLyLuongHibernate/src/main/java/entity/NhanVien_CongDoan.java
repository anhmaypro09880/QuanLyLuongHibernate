package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.*;

@Entity
@IdClass(NhanVienCongDoanPK.class)
public class NhanVien_CongDoan implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVien maNhanVien;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "maCongDoan")
	private CongDoan maCongDoan;
	private int soluong;
	@Id
	private int thang;
	@Id
	private int nam;
	private boolean tinhTrang;
	@Embedded
	private ThuNhapKhac tnk;
	public NhanVien_CongDoan() {
		// TODO Auto-generated constructor stub
	}

	public NhanVien_CongDoan(NhanVien maNhanVien, CongDoan maCongDoan, int soluong, int thang, int nam) {
		super();
		this.maNhanVien = maNhanVien;
		this.maCongDoan = maCongDoan;
		this.soluong = soluong;
		this.thang = thang;
		this.nam = nam;
	}
	
	
	public NhanVien_CongDoan(NhanVien maNhanVien, CongDoan maCongDoan, int soluong, int thang, int nam,
			boolean tinhTrang) {
		super();
		this.maNhanVien = maNhanVien;
		this.maCongDoan = maCongDoan;
		this.soluong = soluong;
		this.thang = thang;
		this.nam = nam;
		this.tinhTrang = tinhTrang;
	}

	public NhanVien_CongDoan(NhanVien maNhanVien, int thang, int nam) {
		super();
		this.maNhanVien = maNhanVien;
		this.thang = thang;
		this.nam = nam;
	}

	public NhanVien_CongDoan(CongDoan maCongDoan, int soluong, int thang, int nam) {
		super();
		this.maCongDoan = maCongDoan;
		this.soluong = soluong;
		this.thang = thang;
		this.nam = nam;
	}

	public NhanVien getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(NhanVien maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public CongDoan getMaCongDoan() {
		return maCongDoan;
	}
	
	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public void setMaCongDoan(CongDoan maCongDoan) {
		this.maCongDoan = maCongDoan;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	public int getNam() {
		return nam;
	}

	public void setNam(int nam) {
		this.nam = nam;
	}
	
	
	public ThuNhapKhac getTnk() {
		return tnk;
	}

	public void setTnk(ThuNhapKhac tnk) {
		this.tnk = tnk;
	}

	@Override
	public String toString() {
		return "NhanVien_CongDoan [maNhanVien=" + maNhanVien + ", maCongDoan=" + maCongDoan + ", soluong=" + soluong
				+ ", thang=" + thang + ", nam=" + nam + ", tinhTrang=" + tinhTrang + ", tnk=" + tnk + "]";
	}

	
	
	
	
	



	
	
	
	
	
	
	
}
