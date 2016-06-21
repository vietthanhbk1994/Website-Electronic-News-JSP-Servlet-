package bean;

public class User{
	private String idUser;
	private int capDo;
	private String idQuyen;
	private String tenQuyen;
	private String hoVaTen;
	private String taiKhoan;
	private String matKhau;
	private String email;
	private String soDienThoai;
	private String chungMinhNhanDan;
	private String diaChi;
	private String ngaySinh;
	private int diemCongHien;
	private int soBaiDang;
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public int getCapDo() {
		return capDo;
	}
	public void setCapDo(int capDo) {
		this.capDo = capDo;
	}
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
	public String getHoVaTen() {
		return hoVaTen;
	}
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getChungMinhNhanDan() {
		return chungMinhNhanDan;
	}
	public void setChungMinhNhanDan(String chungMinhNhanDan) {
		this.chungMinhNhanDan = chungMinhNhanDan;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public int getDiemCongHien() {
		return diemCongHien;
	}
	public void setDiemCongHien(int diemCongHien) {
		this.diemCongHien = diemCongHien;
	}
	public int getSoBaiDang() {
		return soBaiDang;
	}
	public void setSoBaiDang(int soBaiDang) {
		this.soBaiDang = soBaiDang;
	}
	public User() {
		super();
	}
	public User(String idUser, int capDo, String idQuyen, String tenQuyen, String hoVaTen, String taiKhoan,
			String matKhau, String email, String soDienThoai, String chungMinhNhanDan, String diaChi, String ngaySinh,
			int diemCongHien, int soBaiDang) {
		super();
		this.idUser = idUser;
		this.capDo = capDo;
		this.idQuyen = idQuyen;
		this.tenQuyen = tenQuyen;
		this.hoVaTen = hoVaTen;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.chungMinhNhanDan = chungMinhNhanDan;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.diemCongHien = diemCongHien;
		this.soBaiDang = soBaiDang;
	}
}
