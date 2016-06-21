package bean;

public class DanhMuc {
	private String idDanhMuc;
	private String tenDanhMuc;
	private int luotXem;
	private String moTa;
	public String getIdDanhMuc() {
		return idDanhMuc;
	}
	public void setIdDanhMuc(String idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}
	public String getTenDanhMuc() {
		return tenDanhMuc;
	}
	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
	public int getLuotXem() {
		return luotXem;
	}
	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public DanhMuc(String idDanhMuc, String tenDanhMuc, int luotXem, String moTa) {
		super();
		this.idDanhMuc = idDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.luotXem = luotXem;
		this.moTa = moTa;
	}
	public DanhMuc() {
		super();
	}
}
