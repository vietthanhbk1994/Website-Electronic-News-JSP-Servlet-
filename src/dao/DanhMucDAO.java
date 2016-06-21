package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.DanhMuc;
import bo.LibraryBO;

public class DanhMucDAO {
	private PreparedStatement pstm;
	private Statement stm;
	private ResultSet rs;
	LibraryBO db = new LibraryBO();

	// tang luot xem
	public boolean tangLuotXemDanhMuc(String idDanhMuc) {
		String sql = "UPDATE danhmuc SET luotxem=luotxem+1 WHERE iddanhmuc=? LIMIT 1";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idDanhMuc);
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}

	public ArrayList<DanhMuc> getListDanhMuc() {
		ArrayList<DanhMuc> listDanhMuc = new ArrayList<DanhMuc>();
		String sql = "SELECT * FROM danhmuc";
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				DanhMuc objDanhMuc = new DanhMuc();
				objDanhMuc.setIdDanhMuc(rs.getString(1));
				objDanhMuc.setTenDanhMuc(rs.getString(2));
				objDanhMuc.setLuotXem(rs.getInt(3));
				objDanhMuc.setMoTa(rs.getString(4));
				listDanhMuc.add(objDanhMuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listDanhMuc;
	}

	public int getTotalSearchAdmin(String tenDanhMuc) {
		int total = 0;
		String sq = "";
		if (!"".equals(tenDanhMuc)) {
			sq += " AND tendanhmuc LIKE '%" + tenDanhMuc + "%'";
		}
		String sql = "SELECT COUNT(iddanhmuc) AS total FROM danhmuc WHERE 1" + sq;
		// System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
			db.closeDB(conn, stm, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(total);
		return total;
	}

	public ArrayList<DanhMuc> searchDanhMucAdmin(String tenDanhMuc, int offset, int row_count) {
		ArrayList<DanhMuc> listDanhMuc = new ArrayList<DanhMuc>();
		String sqlLimit = " ORDER BY iddanhmuc LIMIT " + offset + "," + row_count;
		String sq = "";
		if (!"".equals(tenDanhMuc)) {
			sq += " AND tendanhmuc LIKE '%" + tenDanhMuc + "%'";
		}
		String sql = "SELECT * FROM danhmuc WHERE 1" + sq + sqlLimit;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				DanhMuc objDanhMuc = new DanhMuc();
				objDanhMuc = new DanhMuc();
				objDanhMuc.setIdDanhMuc(rs.getString(1));
				objDanhMuc.setTenDanhMuc(rs.getString(2));
				objDanhMuc.setLuotXem(rs.getInt(3));
				objDanhMuc.setMoTa(rs.getString(4));
				listDanhMuc.add(objDanhMuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDanhMuc;
	}

	public DanhMuc getDetailDanhMucAdmin(String idDanhMuc) {
		DanhMuc objDanhMuc = null;
		String sql = "SELECT * FROM danhmuc WHERE iddanhmuc='" + idDanhMuc + "'";
		System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				objDanhMuc = new DanhMuc();
				objDanhMuc.setIdDanhMuc(rs.getString(1));
				objDanhMuc.setTenDanhMuc(rs.getString(2));
				System.out.println(objDanhMuc.getTenDanhMuc());
				objDanhMuc.setLuotXem(rs.getInt(3));
				objDanhMuc.setMoTa(rs.getString(4));
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return objDanhMuc;
	}

	public boolean deleteCatAdmin(String[] listIdDelete) {
		String sq = "";
		// lay danh sach id can xoa
		if (listIdDelete.length == 1) {
			sq = listIdDelete[0];
		} else {
			for (int i = 0; i < listIdDelete.length - 1; i++) {
				sq += listIdDelete[i] + "','";
			}
			sq += listIdDelete[listIdDelete.length - 1];
		}
		String sql = "DELETE FROM danhmuc WHERE iddanhmuc IN ('" + sq + "')";
		int delete = 0;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			delete = stm.executeUpdate(sql);
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delete != 0;
	}

	public boolean addDanhMucAdmin(DanhMuc objDanhMuc) {
		LibraryBO boLib = new LibraryBO();
		String idDanhMuc = boLib.getIdMax("danhmuc", "iddanhmuc", "DM001");

		String sql = "INSERT INTO danhmuc (iddanhmuc,tendanhmuc,mota) VALUES (?,?,?)";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idDanhMuc);
			pstm.setString(2, objDanhMuc.getTenDanhMuc());
			pstm.setString(3, objDanhMuc.getMoTa());
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean editDanhMucAdmin(DanhMuc objDanhMuc) {
		String sql = "UPDATE danhmuc SET tendanhmuc=?,mota=? WHERE iddanhmuc=?";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objDanhMuc.getTenDanhMuc());
			pstm.setString(2, objDanhMuc.getMoTa());
			pstm.setString(3, objDanhMuc.getIdDanhMuc());
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean isDanhMucExist(String tenDanhMuc) {
		String sql = "SELECT * FROM danhmuc WHERE tendanhmuc = ?";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, tenDanhMuc);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = true;
			}
			db.closeDB(conn, pstm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
}