package bo;

import java.util.ArrayList;

import bean.TinTuc;
import dao.TinTucDAO;

public class TinTucBO {
	TinTucDAO dao = new TinTucDAO();
	public void setActive(String idtinTuc, int duyet){
		dao.setActive(idtinTuc, duyet);
	}
	//lay so luong tin tuc de phan trang
	public int getTotal(String tieuDe,String idDanhMuc,String thoiGianDang) {
		return dao.getTotal(tieuDe, idDanhMuc,thoiGianDang);
	}
	//xóa san pham
	public boolean deleteTinTucAdmin(String[] listIdDelete){
		return dao.deleteTinTucAdmin(listIdDelete);
	}
	//Lấy danh sách san pham show ra danh sach admin
	public ArrayList<TinTuc> getListTinTucAdmin(String tieuDe, String idDanhMuc, String thoiGianDang, int offset,int row_count) {
		return dao.getListTinTucAdmin(tieuDe, idDanhMuc, thoiGianDang, offset, row_count);
	}
	//xem chi tiet tin tuc
	public TinTuc getDetailTinTucAdmin(String id) {
		return dao.getDetailTinTucAdmin(id);
	}
	//them
	public boolean addTinTucAdmin(TinTuc objTinTuc){
		return dao.addTinTucAdmin(objTinTuc);
	}
	//edit
	public boolean editTinTucAdmin(TinTuc objTinTuc){
		return dao.editTinTucAdmin(objTinTuc);
	}
	public ArrayList<TinTuc> getListTinTucUser(int offset,int row_count, String idDanhMuc) {
		return dao.getListTinTucUser(offset, row_count, idDanhMuc);
	}
	public ArrayList<TinTuc> getListTinTucUserLienQuan(int offset,int row_count, String idTinTuc,String idDanhMuc) {
		return dao.getListTinTucUserLienQuan(offset, row_count, idTinTuc, idDanhMuc);
	}
	
	public ArrayList<TinTuc> getListTinTucGoiY(int offset,int row_count, String dieuKien) {
		return dao.getListTinTucGoiY(offset, row_count, dieuKien);
	}
	public ArrayList<TinTuc> getListTinTucSearchUser(int offset,int row_count, String tieuDe) {
		return dao.getListTinTucSearchUser(offset, row_count, tieuDe);
	}
	//
	public boolean tangLuotXemTinTuc(String idTinTuc) {
		return dao.tangLuotXemTinTuc(idTinTuc);
	}
	//
	public int getTotalPublic(String tieuDe, String idDanhMuc) {
		return dao.getTotalPublic(tieuDe, idDanhMuc);
	}
}
