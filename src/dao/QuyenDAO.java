package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Quyen;
import bo.LibraryBO;

public class QuyenDAO {
	private Statement stm;
	private ResultSet rs;
	LibraryBO db = new LibraryBO();
	public ArrayList<Quyen> getListQuyen(int capDo){
		ArrayList<Quyen> listQuyen = new ArrayList<Quyen>();
		
		String sql = "SELECT * FROM quyen WHERE capdo >"+capDo;
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				Quyen objQuyen = new Quyen();
				objQuyen.setIdQuyen(rs.getString(1));
				objQuyen.setTenQuyen(rs.getString(2));
				objQuyen.setCapDo(rs.getInt(3));
				listQuyen.add(objQuyen);
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return listQuyen;
	}
}
