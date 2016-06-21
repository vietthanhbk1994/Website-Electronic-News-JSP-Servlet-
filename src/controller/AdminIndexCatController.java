package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DanhMuc;
import bo.DanhMucBO;
import bo.PhanTrangBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminIndexNewsController
 */
// @WebServlet("/AdminIndexNewsController")
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminIndexCatController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		UserBO boUser = new UserBO();
		if(boUser.isLogin(request, response)){//neu ton tai session
			String msg = "";
			if(request.getAttribute("msg")!=null){
				msg = (String)request.getAttribute("msg");
			}
			DanhMucBO boDanhMuc = new DanhMucBO();
			
			PhanTrangBO boPhanTrang = new PhanTrangBO();
			//---------load danh sach, tim kiem, xoa
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			int total = boDanhMuc.getTotalSearchAdmin("");
			int row_count = boPhanTrang.getRowCountAdmin();
			request.setAttribute("sotrang", boPhanTrang.getSoTrang(total,row_count));
			request.setAttribute("current_page", current_page);
				// neu load trang
			ArrayList<DanhMuc> listDanhMuc = boDanhMuc.searchDanhMucAdmin("", boPhanTrang.getOffset(current_page, row_count), row_count);
			request.setAttribute("listDanhMuc", listDanhMuc);
			// truong hop bang ko co du lieu + xoa dl da bi xoa truoc do
			if (listDanhMuc.size() == 0) {
				msg+="\nKhông tồn tại dữ liệu!";
			} else {
				request.setAttribute("listDanhMuc", listDanhMuc);
			}
			request.setAttribute("loi", msg);
			request.getRequestDispatcher("index-cat.jsp").forward(request, response);
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}
}
