package bo;

import java.util.ArrayList;

import bean.DanhMuc;
import dao.DanhMucDAO;

public class DanhMucBO {
	DanhMucDAO dao = new DanhMucDAO();
	public ArrayList<DanhMuc> getListDanhMuc(){
		return dao.getListDanhMuc();
	}
	public int getTotalSearchAdmin(String tenDanhMuc) {
		return dao.getTotalSearchAdmin(tenDanhMuc);
	}
	public ArrayList<DanhMuc> searchDanhMucAdmin(String tenDanhMuc, int offset, int row_count) {
		return dao.searchDanhMucAdmin(tenDanhMuc, offset, row_count);
	}
	public DanhMuc getDetailDanhMucAdmin(String idDanhMuc) {
		return dao.getDetailDanhMucAdmin(idDanhMuc);
	}
	public boolean deleteCatAdmin(String[] listIdDelete) {
		return dao.deleteCatAdmin(listIdDelete);
	}
	public boolean addDanhMucAdmin(DanhMuc objDanhMuc) {
		return dao.addDanhMucAdmin(objDanhMuc);
	}
	public boolean editDanhMucAdmin(DanhMuc objDanhMuc) {
		return dao.editDanhMucAdmin(objDanhMuc);
	}
	public boolean isDanhMucExist(String tenDanhMuc) {
		return dao.isDanhMucExist(tenDanhMuc);
	}
	public boolean tangLuotXemDanhMuc(String idDanhMuc) {
		return dao.tangLuotXemDanhMuc(idDanhMuc);
	}
}