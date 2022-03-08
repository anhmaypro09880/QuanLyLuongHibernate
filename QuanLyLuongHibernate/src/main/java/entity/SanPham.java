package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SanPham implements Serializable {
	@Id
	private String maSP;
	@Column(columnDefinition = "Nvarchar(255)")
	private String tenSP;
	@Column(columnDefinition = "Nvarchar(255)")
	private String moTa;
	@Column(columnDefinition = "Nvarchar(255)")
	private String mau;
	
	@OneToMany(mappedBy = "maSanPham")
	private Set<CongDoan> dscd;
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP, String tenSP, String moTa, String mau) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.mau = mau;
	}
	
	public SanPham(String maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}
	
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	public SanPham(String tenSP, String moTa, String mau) {
		super();
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.mau = mau;
	}
	
	
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getMau() {
		return mau;
	}
	public void setMau(String mau) {
		this.mau = mau;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", moTa=" + moTa + ", mau=" + mau + "]";
	}
	
}
