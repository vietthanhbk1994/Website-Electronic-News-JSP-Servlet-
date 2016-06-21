package bo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Normalizer;
import java.util.regex.Pattern;

import dao.LibraryDAO;

public class LibraryBO {
	LibraryDAO dao = new LibraryDAO();

	public Connection getConnectMySQL() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/baodientuphp?useUnicode=true&characterEncoding=UTF-8", "root", "");
		} catch (Exception e) {
			e.getStackTrace();
		}
		return conn;
	}

	public void closeDB(Connection conn, PreparedStatement pstm) {
		try {
			pstm.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	public void closeDB(Connection conn, Statement stm, ResultSet rs) {
		try {
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	public void closeDB(Connection conn, PreparedStatement pstm, ResultSet rs) {
		try {
			rs.close();
			pstm.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	public String getIdMax(String table, String id, String idMacDinh) {
		String idMax = dao.getIdMax(table, id, idMacDinh);
		// System.out.println(idMax);
		if (null==idMax) {
			return idMacDinh;
		}
		int lengDau = 0;
		for (int i = 0; i < idMax.length(); i++) {
			if (isNumber(idMax.charAt(i))) {
				lengDau = i;
				break;
			}
		}
		String dau = idMax.substring(0, lengDau);
		String duoi = idMax.substring(lengDau);
		String duoiMax = Integer.valueOf(duoi) + 1 + "";
		String idMoi = "";
		for (int i = duoiMax.length(); i < idMax.length() - dau.length(); i++) {
			idMoi = "0" + idMoi;
		}
		idMoi = dau + idMoi + duoiMax;
		return idMoi;
	}

	public boolean isNumber(char i) {
		try {
			Integer.parseInt(String.valueOf(i));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public String md5(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(str.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}

	public static String createSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Thay đ thành d
		slug = slug.replaceAll("đ", "d");
		// Xóa các ký tự đặt biệt
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Thay space thành dấu gạch ngang
		slug = slug.replaceAll("[\\s]", "-");
		// Đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
		slug = slug.replaceAll("(-+)", "-");
		// Xóa các ký tự gạch ngang ở đầu và cuối
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}
}
