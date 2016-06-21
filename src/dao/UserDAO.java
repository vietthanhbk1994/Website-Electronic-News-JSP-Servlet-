package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;
import bo.LibraryBO;

public class UserDAO {
	private PreparedStatement pstm;
	private Statement stm;
	private ResultSet rs;
	LibraryBO db = new LibraryBO();
	public boolean tangSoBaiDang(String idUser, int soBaiDang, int diemCongHien){
		String sql="UPDATE user SET sobaidang=sobaidang+?,diemconghien=diemconghien+? WHERE iduser=?";
		boolean result=false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, soBaiDang);
			pstm.setInt(2, diemCongHien);
			pstm.setString(3, idUser);
			pstm.executeUpdate();
			
			result=true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		//System.out.println(objUser.getHoVaTen()+","+objUser.getTaiKhoan()+","+objUser.getMatKhau()+","+objUser.getsoDienThoai()+","+objUser.getChungMinhNhanDan()+","+objUser.getDiaChi()+","+objUser.getNgaySinh()+","+objUser.getIdUser());
		return result;
	}
	public boolean isUserExist(String taiKhoan){
		String sql = "SELECT * FROM user WHERE taikhoan = ?";
		//System.out.println(sql+taiKhoan);
		boolean result=false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, taiKhoan);
			rs = pstm.executeQuery();
			if(rs.next()){
				result= true;
			}
			db.closeDB(conn, pstm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	public User checkUser(String taiKhoan, String matKhau){
		User objUser = null;
		String sql = "SELECT u.hovaten,u.idquyen,q.tenquyen,u.iduser,q.capdo FROM user AS u, quyen AS q WHERE u.idquyen=q.idquyen AND u.taikhoan = ? AND u.matkhau = ? LIMIT 1";
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, taiKhoan);
			pstm.setString(2, matKhau);
			rs = pstm.executeQuery();
			if(rs.next()){
				objUser = new User();
				String hoVaTen = rs.getString(1);
				String idQuyen = rs.getString(2);
				String tenQuyen = rs.getString(3);
				String idUser = rs.getString(4);
				int capDo = rs.getInt(5);
				objUser.setTaiKhoan(taiKhoan);
				objUser.setMatKhau(matKhau);
				objUser.setHoVaTen(hoVaTen);
				objUser.setIdQuyen(idQuyen);
				objUser.setTenQuyen(tenQuyen);
				objUser.setIdUser(idUser);
				objUser.setCapDo(capDo);
			}
			db.closeDB(conn, pstm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return objUser;
	}
	public int getTotalSearchAdmin(String hoVaTen, String idQuyen, int capDo) {
		int total=0;
		String sq="";
		if(!"".equals(hoVaTen)){
			sq+=" AND u.hovaten LIKE '%"+hoVaTen+"%'";
		}
		if(!"".equals(idQuyen)){
			sq+=" AND u.idquyen='"+idQuyen+"'";
		}
		
		String sql="SELECT COUNT(iduser) AS total FROM user AS u, quyen AS q WHERE q.idquyen=u.idquyen"
				+ " AND q.capdo>"+capDo+sq;
		//System.out.println(sql);
		try {
			Connection conn= db.getConnectMySQL();
			stm=conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()){
				total=rs.getInt("total");
			}
			db.closeDB(conn, stm, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	//loc tin tuc
	public ArrayList<User> searchUserAdmin(String hoVaTen, String idQuyen, int offset,int row_count,int capDo) {
		ArrayList<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM user AS u, quyen AS q WHERE u.idquyen=q.idquyen AND q.capdo>"+capDo;
		String sqlLimit=" ORDER BY u.idquyen LIMIT "+offset+","+row_count;
		String sq="";
		if(!"".equals(hoVaTen)){
			sq+=" AND u.hovaten LIKE '%"+hoVaTen+"%'";
		}
		if(!"".equals(idQuyen)){
			sq+=" AND u.idquyen='"+idQuyen+"'";
		}
		//lay so ket qua tim kiem dc
		sql=sql+sq+sqlLimit;
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				User objUser = new User();
				objUser.setIdUser(rs.getString("iduser"));
				objUser.setTaiKhoan(rs.getString("taikhoan"));
				objUser.setHoVaTen(rs.getString("hovaten"));
				objUser.setIdQuyen(rs.getString("idquyen"));
				objUser.setTenQuyen(rs.getString("tenquyen"));
				objUser.setSoDienThoai(rs.getString("sodienthoai"));
				objUser.setEmail(rs.getString("email"));
				objUser.setDiaChi(rs.getString("diachi"));
				objUser.setSoBaiDang(rs.getInt("sobaidang"));
				objUser.setDiemCongHien(rs.getInt("diemconghien"));
				
				String[] tg = rs.getString("ngaysinh").split("-");
				tg[2]+="-"+tg[1]+"-"+tg[0];
				//chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objUser.setNgaySinh(tg[2]);
				
				listUser.add(objUser);
			}
			db.closeDB(conn, stm, rs);
			pstm.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listUser;
	}
	//xóa san pham
	public boolean deleteUserAdmin(String[] listIdDelete, int capDo){
		String sq="";
		//lay danh sach id can xoa
		if(listIdDelete.length==1){
			sq = listIdDelete[0];
		}else{
			for (int i = 0; i < listIdDelete.length-1; i++) {
				sq+=listIdDelete[i]+"','";
			}
			sq+= listIdDelete[listIdDelete.length-1];
		}
		String sql="DELETE FROM user WHERE EXISTS (SELECT * FROM quyen WHERE quyen.capdo>"+capDo+")"
				+ " AND iduser IN ('"+sq+"')";
		int delete = 0;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			delete = stm.executeUpdate(sql);
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delete!=0;
	}

	//Lấy danh sách san pham show ra danh sach admin
	public ArrayList<User> getListUserAdmin(int offset,int row_count, int capDo) {
		ArrayList<User> listUser = new ArrayList<User>();
		String sql = "SELECT * FROM user AS u, quyen AS q WHERE u.idquyen=q.idquyen AND q.capdo>"+capDo
				+ " ORDER BY u.iduser LIMIT "+offset+","+row_count;
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				User objUser = new User();
				objUser.setIdUser(rs.getString("iduser"));
				objUser.setTaiKhoan(rs.getString("taikhoan"));
				objUser.setHoVaTen(rs.getString("hovaten"));
				objUser.setIdQuyen(rs.getString("idquyen"));
				objUser.setTenQuyen(rs.getString("tenquyen"));
				objUser.setSoDienThoai(rs.getString("sodienthoai"));
				objUser.setEmail(rs.getString("email"));
				objUser.setDiaChi(rs.getString("diachi"));
				objUser.setSoBaiDang(rs.getInt("sobaidang"));
				objUser.setDiemCongHien(rs.getInt("diemconghien"));
				
				String[] tg = rs.getString("ngaysinh").split("-");
				tg[2]+="-"+tg[1]+"-"+tg[0];
				//chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objUser.setNgaySinh(tg[2]);
				
				listUser.add(objUser);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listUser;
	}
	//xem chi tiet 
	public User getDetailUserAdmin(String id) {
		String sql = "SELECT * FROM quyen AS q, user AS u WHERE "
				+ "u.idquyen = q.idquyen AND u.iduser ='"+id+"'";
		User objUser = null;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()){
				String idQuyen = rs.getString("idquyen");
				int capDo = rs.getInt("capdo");
				String tenQuyen = rs.getString("tenquyen");
				String hoVaTen = rs.getString("hovaten");//
				String taiKhoan = rs.getString("taikhoan");//
				String matKhau = rs.getString("matkhau");
				String email = rs.getString("email");//
				String soDienThoai = rs.getString("sodienthoai");//
				String chungMinhNhanDan = rs.getString("chungminhnhandan");//
				String diaChi = rs.getString("diachi");//
				String ngaySinh = rs.getString("ngaysinh");//
				int diemCongHien = rs.getInt("diemconghien");
				int soBaiDang = rs.getInt("sobaidang");
				
				objUser = new User(id, capDo, idQuyen, tenQuyen, hoVaTen, taiKhoan, matKhau, email, soDienThoai, chungMinhNhanDan, diaChi, ngaySinh, diemCongHien, soBaiDang);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return objUser;
	}
	//them
	public boolean addUserAdmin(User objUser){
		//System.out.println("("+objUser.getIdQuyen()+","+objUser.getHoVaTen()+","+objUser.getTaiKhoan()+","+objUser.getMatKhau()+","+objUser.getEmail()+","+objUser.getsoDienThoai()+","+objUser.getChungMinhNhanDan()+","+objUser.getDiaChi()+","+objUser.getNgaySinh()+")");
		LibraryBO boLib = new LibraryBO();
		String idUser = boLib.getIdMax("user", "iduser", "US001");
		
		String sql="INSERT INTO user (iduser,idquyen,hovaten,taikhoan,matkhau,email,sodienthoai,"
				+ "chungminhnhandan,diachi,ngaysinh) VALUES (?,?,?,?,?,?,?,?,?,?)";
		//System.out.println(sql);
		//System.out.println(objUser.getIdQuyen()+","+objUser.getHoVaTen()+","+objUser.getTaiKhoan()+","+objUser.getMatKhau()+","+objUser.getEmail()+","+objUser.getSoDienThoai()+","+objUser.getChungMinhNhanDan()+","+objUser.getDiaChi()+","+objUser.getNgaySinh()+".");
		boolean result=false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idUser);
			pstm.setString(2, objUser.getIdQuyen());
			pstm.setString(3, objUser.getHoVaTen());
			pstm.setString(4, objUser.getTaiKhoan());
			pstm.setString(5, objUser.getMatKhau());
			pstm.setString(6, objUser.getEmail());
			pstm.setString(7, objUser.getSoDienThoai());
			pstm.setString(8, objUser.getChungMinhNhanDan());
			pstm.setString(9, objUser.getDiaChi());
			pstm.setString(10, objUser.getNgaySinh());
			pstm.executeUpdate();
			db.closeDB(conn, pstm);
			result=true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	//edit
	public boolean editUserAdmin(User objUser){
		String sql="UPDATE user SET hovaten=?,matkhau=?,email=?,sodienthoai=?,"
				+ "chungminhnhandan=?,diachi=?,ngaysinh=? WHERE iduser=?";
		boolean result=false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objUser.getHoVaTen());
			pstm.setString(2, objUser.getMatKhau());
			pstm.setString(3, objUser.getEmail());
			pstm.setString(4, objUser.getSoDienThoai());
			pstm.setString(5, objUser.getChungMinhNhanDan());
			pstm.setString(6, objUser.getDiaChi());
			pstm.setString(7, objUser.getNgaySinh());
			pstm.setString(8, objUser.getIdUser());
			pstm.executeUpdate();
			
			result=true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		//System.out.println(objUser.getHoVaTen()+","+objUser.getTaiKhoan()+","+objUser.getMatKhau()+","+objUser.getsoDienThoai()+","+objUser.getChungMinhNhanDan()+","+objUser.getDiaChi()+","+objUser.getNgaySinh()+","+objUser.getIdUser());
		return result;
	}
}