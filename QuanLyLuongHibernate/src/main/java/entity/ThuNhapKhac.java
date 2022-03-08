package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Id;

@Embeddable

public class ThuNhapKhac implements Serializable {
		private double luongLamThem;
		private double phuCap;
		private double thuong;
//		@ManyToOne
//		@JoinColumn(name = "maNV")
		public ThuNhapKhac() {
			// TODO Auto-generated constructor stub
		}
		public double getLuongLamThem() {
			return luongLamThem;
		}
		public void setLuongLamThem(double luongLamThem) {
			this.luongLamThem = luongLamThem;
		}
		public double getPhuCap() {
			return phuCap;
		}
		public void setPhuCap(double phuCap) {
			this.phuCap = phuCap;
		}
		public double getThuong() {
			return thuong;
		}
		public void setThuong(double thuong) {
			this.thuong = thuong;
		}
		public ThuNhapKhac(double luongLamThem, double phuCap, double thuong) {
			super();
			this.luongLamThem = luongLamThem;
			this.phuCap = phuCap;
			this.thuong = thuong;
		}
		@Override
		public String toString() {
			return "ThuNhapKhac [luongLamThem=" + luongLamThem + ", phuCap=" + phuCap + ", thuong=" + thuong + "]";
		}
		
		
		
		


		
		
}
