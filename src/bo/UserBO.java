package bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class UserBO {
	UserDAO dao = new UserDAO();
	public boolean tangSoBaiDang(String idUser, int soBaiDang, int diemCongHien){
		return dao.tangSoBaiDang(idUser, soBaiDang, diemCongHien);
	}
	public boolean isUserExist(String taiKhoan){
		return dao.isUserExist(taiKhoan);
	}
	public User checkUser(String taiKhoan, String matKhau){
		return dao.checkUser(taiKhoan, matKhau);
	}
	public int getTotalSearchAdmin(String hoVaTen, String idQuyen, int capDo) {
		return dao.getTotalSearchAdmin(hoVaTen, idQuyen, capDo);
	}
	//loc tin tuc
	public ArrayList<User> searchUserAdmin(String hoVaTen, String idQuyen, int offset,int row_count,int capDo) {
		return dao.searchUserAdmin(hoVaTen, idQuyen, offset, row_count, capDo);
	}
	//xóa san pham
	public boolean deleteUserAdmin(String[] listIdDelete, int capDo){
		return dao.deleteUserAdmin(listIdDelete, capDo);
	}
	//Lấy danh sách san pham show ra danh sach admin
	public ArrayList<User> getListUserAdmin(int offset,int row_count, int capDo) {
		return dao.getListUserAdmin(offset, row_count, capDo);
	}
	//xem chi tiet 
	public User getDetailUserAdmin(String id) {
		return dao.getDetailUserAdmin(id);
	}
	//them
	public boolean addUserAdmin(User objUser){
		return dao.addUserAdmin(objUser);
	}
	//edit
	public boolean editUserAdmin(User objUser){
		return dao.editUserAdmin(objUser);
	}
	//kiem tra dang nhap
	public boolean isLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("userObj");
		if(userObj==null){
			return false;
		}
		return true;
	}
}