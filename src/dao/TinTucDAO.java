package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.TinTuc;
import bo.LibraryBO;

public class TinTucDAO {
	private PreparedStatement pstm;
	private Statement stm;
	private ResultSet rs;
	LibraryBO db = new LibraryBO();
	public void setActive(String idtinTuc, int duyet){
		String sql = "UPDATE tintuc SET duyet = ? WHERE idtintuc = ?";
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, duyet);
			pstm.setString(2, idtinTuc);
			pstm.executeUpdate();
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// lay so luong tin tuc de phan trang
	public int getTotal(String tieuDe, String idDanhMuc, String thoiGianDang) {
		int total = 0;
		String sql = "SELECT COUNT(idtintuc) AS total FROM tintuc AS t WHERE 1";
		//, user AS u WHERE t.iduser=u.iduser
		if (!"".equals(tieuDe)) {
			sql += " AND t.tieude LIKE '%" + tieuDe + "%'";
		}
		if (!"".equals(idDanhMuc)) {
			sql += " AND t.iddanhmuc='" + idDanhMuc + "'";
		}
		if (!"".equals(thoiGianDang)) {
			sql += " AND t.thoigiandang ='" + thoiGianDang + "'";
		}
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	// lay so luong tin tuc de phan trang
		public int getTotalPublic(String tieuDe, String idDanhMuc) {
			int total = 0;
			String sql = "SELECT COUNT(idtintuc) AS total FROM tintuc AS t WHERE duyet=1";
			//, user AS u WHERE t.iduser=u.iduser
			if (!"".equals(tieuDe)) {
				sql += " AND t.tieude LIKE '%" + tieuDe + "%'";
			}
			if (!"".equals(idDanhMuc)) {
				sql += " AND t.iddanhmuc='" + idDanhMuc + "'";
			}
			//System.out.println(sql);
			try {
				Connection conn = db.getConnectMySQL();
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				if (rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return total;
		}

	// loc tin tuc
	public ArrayList<TinTuc> getListTinTucAdmin(String tieuDe, String idDanhMuc, String thoiGianDang, int offset,
			int row_count) {
		ArrayList<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		String sql = "SELECT t.idtintuc, t.tieude, u.hovaten, d.tendanhmuc, t.thoigiandang,"
				+ " t.luotxem, t.hinhanh, u.iduser,t.duyet FROM tintuc AS t, user AS u, danhmuc AS d"
				+ " WHERE t.iduser = u.iduser AND t.iddanhmuc = d.iddanhmuc";
		if (!"".equals(tieuDe)) {
			sql += " AND t.tieude LIKE '%" + tieuDe + "%'";
		}
		if (!"".equals(idDanhMuc)) {
			sql += " AND t.iddanhmuc='" + idDanhMuc + "'";
		}
		if (!"".equals(thoiGianDang)) {
			sql += " AND t.thoigiandang ='" + thoiGianDang + "'";
		}
		// lay so ket qua tim kiem dc
		sql += " ORDER BY t.thoigiandang DESC LIMIT " + offset + "," + row_count;
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TinTuc objTinTuc = new TinTuc();
				objTinTuc.setIdTinTuc(rs.getString(1));
				objTinTuc.setTieuDe(rs.getString(2));
				objTinTuc.setNguoiDang(rs.getString(3));
				objTinTuc.setDanhMuc(rs.getString(4));

				String[] tg = rs.getString(5).split("-");
				tg[2] += "/" + tg[1] + "/" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);

				objTinTuc.setLuotXem(rs.getInt(6));
				objTinTuc.setHinhAnh(rs.getString(7));
				objTinTuc.setIdUser(rs.getString(8));
				objTinTuc.setDuyet(rs.getInt(9));
				listTinTuc.add(objTinTuc);
			}
			db.closeDB(conn, stm, rs);
			pstm.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listTinTuc;
	}

	// xóa san pham
	public boolean deleteTinTucAdmin(String[] listIdDelete) {
		String sq = "";
		// lay danh sach id can xoa
		if (listIdDelete.length == 1) {
			sq = "'" + listIdDelete[0] + "'";
		} else {
			for (int i = 0; i < listIdDelete.length - 1; i++) {
				sq += "'" + listIdDelete[i] + "',";
			}
			sq += "'" + listIdDelete[listIdDelete.length - 1] + "'";
		}
		String sql = "DELETE FROM tintuc WHERE idtintuc IN (" + sq + ")";
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

	// xem chi tiet tin tuc
	public TinTuc getDetailTinTucAdmin(String id) {
		String sql = "SELECT t.*,u.hovaten,d.tendanhmuc FROM tintuc AS t, user AS u, danhmuc AS d"
				+ " WHERE t.iduser = u.iduser AND d.iddanhmuc = t.iddanhmuc AND t.idtintuc ='" + id + "'";
		//System.out.println(sql);
		TinTuc objTinTuc = null;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if (rs.next()) {
				objTinTuc = new TinTuc();
				objTinTuc.setIdUser(rs.getString("iduser"));
				objTinTuc.setDanhMuc(rs.getString("tendanhmuc"));
				objTinTuc.setIdTinTuc(rs.getString("idtintuc"));
				objTinTuc.setIdDanhMuc(rs.getString("iddanhmuc"));
				objTinTuc.setTieuDe(rs.getString("tieude"));
				objTinTuc.setMoTa(rs.getString("mota"));
				objTinTuc.setNoiDung(rs.getString("noidung"));
				objTinTuc.setHinhAnh(rs.getString("hinhanh"));

				String[] tg = rs.getString("thoigiandang").split("-");
				tg[2] += "-" + tg[1] + "-" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);

				objTinTuc.setLuotXem(rs.getInt("luotxem"));
				objTinTuc.setDoUuTien(rs.getInt("douutien"));
				objTinTuc.setNguon(rs.getString("nguon"));
				// objTinTuc.setIdUser(rs.getString("iduser"));
				objTinTuc.setDuyet(rs.getInt("duyet"));
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		//System.out.println(objTinTuc.toString());
		return objTinTuc;
	}

	// them
	public boolean addTinTucAdmin(TinTuc objTinTuc) {
		LibraryBO boLib = new LibraryBO();
		String idTinTuc = boLib.getIdMax("tintuc", "idtintuc", "TT00000001");
		System.out.println(idTinTuc);
		String sql = "INSERT INTO tintuc (idtintuc,iduser,iddanhmuc,tieude,mota,noidung,hinhanh,"
				+ "thoigiandang,douutien,luotxem,nguon) VALUES (?,?,?,?,?,?,?,CURDATE(),?,?,?)";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idTinTuc);
			pstm.setString(2, objTinTuc.getIdUser());
			pstm.setString(3, objTinTuc.getIdDanhMuc());
			pstm.setString(4, objTinTuc.getTieuDe());
			pstm.setString(5, objTinTuc.getMoTa());
			pstm.setString(6, objTinTuc.getNoiDung());
			pstm.setString(7, objTinTuc.getHinhAnh());
			pstm.setInt(8, objTinTuc.getDoUuTien());
			pstm.setInt(9, objTinTuc.getLuotXem());
			pstm.setString(10, objTinTuc.getNguon());
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println(objTinTuc.toString());
		return result;
	}
	// edit
	public boolean editTinTucAdmin(TinTuc objTinTuc) {
		String sql = "UPDATE tintuc SET iddanhmuc=?,tieude=?,mota=?,noidung=?,hinhanh=?,douutien=?,nguon=?,duyet=? WHERE idtintuc=?";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objTinTuc.getIdDanhMuc());
			pstm.setString(2, objTinTuc.getTieuDe());
			pstm.setString(3, objTinTuc.getMoTa());
			pstm.setString(4, objTinTuc.getNoiDung());
			pstm.setString(5, objTinTuc.getHinhAnh());
			pstm.setInt(6, objTinTuc.getDoUuTien());
			pstm.setString(7, objTinTuc.getNguon());
			pstm.setInt(8, objTinTuc.getDuyet());
			pstm.setString(9, objTinTuc.getIdTinTuc());
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		System.out.println("UPDATE tintuc SET iddanhmuc='"+objTinTuc.getIdDanhMuc()+"',tieude="+objTinTuc.getTieuDe()+",mota='"+objTinTuc.getMoTa()+"',noidung='"+objTinTuc.getHinhAnh()+"'"+ ",hinhanh='"+objTinTuc.getHinhAnh()+"',douutien='"+objTinTuc.getDoUuTien()+"',nguon='"+objTinTuc.getNguon()+"',duyet='"+objTinTuc.getDuyet()+"' WHERE idtintuc='"+objTinTuc.getIdTinTuc()+"'");
		System.out.println(objTinTuc.toString()+"\n"+result);
		return result;
	}

	public ArrayList<TinTuc> getListTinTucUser(int offset, int row_count, String idDanhMuc) {
		ArrayList<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		String sql = "SELECT t.idtintuc,t.iddanhmuc, t.tieude, t.mota, t.thoigiandang, t.hinhanh,t.luotxem FROM tintuc AS t"
				+ " WHERE t.iddanhmuc='" + idDanhMuc + "' AND t.duyet=1 ORDER BY t.thoigiandang DESC,t.douutien DESC LIMIT " + offset
				+ "," + row_count;
		//System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TinTuc objTinTuc = new TinTuc();
				objTinTuc.setIdTinTuc(rs.getString(1));
				objTinTuc.setIdDanhMuc(rs.getString(2));
				objTinTuc.setTieuDe(rs.getString(3));
				objTinTuc.setMoTa(rs.getString(4));
				String[] tg = rs.getString(5).split("-");
				tg[2] += "-" + tg[1] + "-" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);
				objTinTuc.setHinhAnh(rs.getString(6));
				objTinTuc.setLuotXem(rs.getInt(7));
				listTinTuc.add(objTinTuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listTinTuc;
	}

	public ArrayList<TinTuc> getListTinTucUserLienQuan(int offset, int row_count, String idTinTuc, String idDanhMuc) {
		ArrayList<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		String sql = "SELECT t.idtintuc,t.iddanhmuc, t.tieude, t.mota, t.thoigiandang, t.hinhanh,t.luotxem FROM tintuc AS t"
				+ " WHERE t.iddanhmuc='" + idDanhMuc + "' AND t.idtintuc!='" + idTinTuc
				+ "'  AND t.duyet=1 ORDER BY t.thoigiandang DESC LIMIT " + offset + "," + row_count;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TinTuc objTinTuc = new TinTuc();
				objTinTuc.setIdTinTuc(rs.getString(1));
				objTinTuc.setIdDanhMuc(rs.getString(2));
				objTinTuc.setTieuDe(rs.getString(3));
				objTinTuc.setMoTa(rs.getString(4));
				String[] tg = rs.getString(5).split("-");
				tg[2] += "-" + tg[1] + "-" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);
				objTinTuc.setHinhAnh(rs.getString(6));
				objTinTuc.setLuotXem(rs.getInt(7));
				listTinTuc.add(objTinTuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listTinTuc;
	}

	public ArrayList<TinTuc> getListTinTucGoiY(int offset, int row_count, String dieuKien) {
		String sapXep = "t.douutien DESC,t.luotxem DESC";
		if ("luotxem".equals(dieuKien)) {
			sapXep = "t.luotxem DESC,t.douutien DESC";
		}
		ArrayList<TinTuc> listTinTuc = new ArrayList<TinTuc>();
		String sql = "SELECT t.idtintuc,t.iddanhmuc, t.tieude, t.mota, t.thoigiandang,"
				+ " t.hinhanh,t.luotxem FROM tintuc AS t WHERE t.duyet=1 ORDER BY " + sapXep + " LIMIT " + offset + "," + row_count;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TinTuc objTinTuc = new TinTuc();
				objTinTuc.setIdTinTuc(rs.getString(1));
				objTinTuc.setIdDanhMuc(rs.getString(2));
				objTinTuc.setTieuDe(rs.getString(3));
				objTinTuc.setMoTa(rs.getString(4));
				String[] tg = rs.getString(5).split("-");
				tg[2] += "-" + tg[1] + "-" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);
				objTinTuc.setHinhAnh(rs.getString(6));
				objTinTuc.setLuotXem(rs.getInt(7));
				listTinTuc.add(objTinTuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listTinTuc;
	}

	public ArrayList<TinTuc> getListTinTucSearchUser(int offset, int row_count, String tieuDe) {
		ArrayList<TinTuc> listTinTuc = new ArrayList<TinTuc>();

		String sql = "SELECT t.idtintuc,t.iddanhmuc, t.tieude, t.mota, t.thoigiandang,"
				+ " t.hinhanh,t.luotxem FROM tintuc AS t WHERE t.tieuDe LIKE " + "'%" + tieuDe
				+ "%'  AND t.duyet=1 ORDER BY t.thoigiandang DESC LIMIT " + offset + "," + row_count;
		// System.out.println(sql);
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				TinTuc objTinTuc = new TinTuc();
				objTinTuc.setIdTinTuc(rs.getString(1));
				objTinTuc.setIdDanhMuc(rs.getString(2));
				objTinTuc.setTieuDe(rs.getString(3));
				objTinTuc.setMoTa(rs.getString(4));
				String[] tg = rs.getString(5).split("-");
				tg[2] += "-" + tg[1] + "-" + tg[0];
				// chuyen chuoi tu yyyy-mm-dd sang dd-mm-yyyy
				objTinTuc.setThoiGianDang(tg[2]);
				objTinTuc.setHinhAnh(rs.getString(6));
				objTinTuc.setLuotXem(rs.getInt(7));

				listTinTuc.add(objTinTuc);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listTinTuc;
	}

	// tang luot xem
	public boolean tangLuotXemTinTuc(String idTinTuc) {
		String sql = "UPDATE tintuc SET luotxem=luotxem+1 WHERE idtintuc=? LIMIT 1";
		//String sqlDM = "UPDATE danhmuc SET luotxem=luotxem+1 WHERE iddanhmuc=? LIMIT 1";
		boolean result = false;
		try {
			Connection conn = db.getConnectMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idTinTuc);
			pstm.executeUpdate();
			result = true;
			db.closeDB(conn, pstm);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
}