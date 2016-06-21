package bean;

public class Quyen {
	private String idQuyen;
	private String tenQuyen;
	private int capDo;
	public String getIdQuyen() {
		return idQuyen;
	}
	public void setIdQuyen(String idQuyen) {
		this.idQuyen = idQuyen;
	}
	public String getTenQuyen() {
		return tenQuyen;
	}
	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}
	public int getCapDo() {
		return capDo;
	}
	public void setCapDo(int capDo) {
		this.capDo = capDo;
	}
	public Quyen(String idQuyen, String tenQuyen, int capDo) {
		super();
		this.idQuyen = idQuyen;
		this.tenQuyen = tenQuyen;
		this.capDo = capDo;
	}
	public Quyen() {
		super();
	}
}
