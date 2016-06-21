package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import bo.LibraryBO;

public class LibraryDAO {
	private Statement stm;
	private ResultSet rs;
	public String getIdMax(String table, String id, String idMacDinh){
		String idMax="";
		String sql = "SELECT MAX("+id+") AS id FROM "+table;
		System.out.println(sql);
		LibraryBO db = new LibraryBO();
		try {
			Connection conn = db.getConnectMySQL();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()){
				idMax = rs.getString("id");
			}
			db.closeDB(conn, stm, rs);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return idMax;
	}
}
