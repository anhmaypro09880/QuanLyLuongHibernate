package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class NhanVienCongDoanPK implements Serializable {
	private String maNhanVien;
	private String maCongDoan;
	private int thang;
	private int nam;
	
	public NhanVienCongDoanPK() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCongDoan, maNhanVien, nam, thang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVienCongDoanPK other = (NhanVienCongDoanPK) obj;
		return Objects.equals(maCongDoan, other.maCongDoan) && Objects.equals(maNhanVien, other.maNhanVien)
				&& nam == other.nam && thang == other.thang;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getMaCongDoan() {
		return maCongDoan;
	}

	public void setMaCongDoan(String maCongDoan) {
		this.maCongDoan = maCongDoan;
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

	@Override
	public String toString() {
		return "NhanVienCongDoanPK [maNhanVien=" + maNhanVien + ", maCongDoan=" + maCongDoan + ", thang=" + thang
				+ ", nam=" + nam + "]";
	}
	
}
