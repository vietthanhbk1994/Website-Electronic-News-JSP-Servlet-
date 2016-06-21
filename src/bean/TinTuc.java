package bean;

public class TinTuc {
	private String idTinTuc;
	private String nguoiDang;
	private String danhMuc;
	private String tieuDe;
	private String moTa;
	private String noiDung;
	private String hinhAnh;
	private String thoiGianDang;
	private int luotXem;
	private int doUuTien;
	private String nguon;
	private String idUser;
	private String idDanhMuc;
	private int duyet;
	public String getIdTinTuc() {
		return idTinTuc;
	}
	public void setIdTinTuc(String idTinTuc) {
		this.idTinTuc = idTinTuc;
	}
	public String getNguoiDang() {
		return nguoiDang;
	}
	public void setNguoiDang(String nguoiDang) {
		this.nguoiDang = nguoiDang;
	}
	public String getDanhMuc() {
		return danhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		this.danhMuc = danhMuc;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getThoiGianDang() {
		return thoiGianDang;
	}
	public void setThoiGianDang(String thoiGianDang) {
		this.thoiGianDang = thoiGianDang;
	}
	public int getLuotXem() {
		return luotXem;
	}
	public void setLuotXem(int luotXem) {
		this.luotXem = luotXem;
	}
	public int getDoUuTien() {
		return doUuTien;
	}
	public void setDoUuTien(int doUuTien) {
		this.doUuTien = doUuTien;
	}
	public String getNguon() {
		return nguon;
	}
	public void setNguon(String nguon) {
		this.nguon = nguon;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdDanhMuc() {
		return idDanhMuc;
	}
	public void setIdDanhMuc(String idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}
	public int getDuyet() {
		return duyet;
	}
	public void setDuyet(int duyet) {
		this.duyet = duyet;
	}
	public TinTuc(String idTinTuc, String nguoiDang, String danhMuc, String tieuDe, String moTa, String noiDung,
			String hinhAnh, String thoiGianDang, int luotXem, int doUuTien, String nguon, String idUser,
			String idDanhMuc, int duyet) {
		super();
		this.idTinTuc = idTinTuc;
		this.nguoiDang = nguoiDang;
		this.danhMuc = danhMuc;
		this.tieuDe = tieuDe;
		this.moTa = moTa;
		this.noiDung = noiDung;
		this.hinhAnh = hinhAnh;
		this.thoiGianDang = thoiGianDang;
		this.luotXem = luotXem;
		this.doUuTien = doUuTien;
		this.nguon = nguon;
		this.idUser = idUser;
		this.idDanhMuc = idDanhMuc;
		this.duyet = duyet;
	}
	public TinTuc() {
		super();
	}
	public String toString(){
		return idTinTuc+","+tieuDe+","+moTa.substring(0,5)+","+noiDung.substring(0,5)+","+hinhAnh+","+thoiGianDang+","+luotXem+","+doUuTien+","+nguon+","+idUser+","+idDanhMuc;
	}
	
}
