package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class PhongBan  implements Serializable{
	@Id

	private String maPB;
	@Column(columnDefinition = "Nvarchar(255)")
	private String tenPB;
	
	@OneToMany(mappedBy ="phongban")
	private Set<NhanVien> dsnv;
	
	public PhongBan() {
		// TODO Auto-generated constructor stub
	}
	public String getMaPB() {
		return maPB;
	}
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}
	public String getTenPB() {
		return tenPB;
	}
	public void setTenPB(String tenPB) {
		this.tenPB = tenPB;
	}
	public PhongBan(String maPB, String tenPB) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
	}
	
	public PhongBan(String maPB) {
		super();
		this.maPB = maPB;
	}
	@Override
	public String toString() {
		return "PhongBan [maPB=" + maPB + ", tenPB=" + tenPB + "]";
	}
	
}
