package bo;

import java.util.ArrayList;

import bean.Quyen;
import dao.QuyenDAO;

public class QuyenBO {
	QuyenDAO dao  = new QuyenDAO();
	public ArrayList<Quyen> getListQuyen(int capDo){
		return dao.getListQuyen(capDo);
	}
}
