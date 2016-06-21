package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Quyen;
import bean.User;
import bo.PhanTrangBO;
import bo.QuyenBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminSearchUserController
 */
//@WebServlet("/AdminSearchUserController")
public class AdminSearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		UserBO boUser = new UserBO();
		if(boUser.isLogin(request, response)){//neu ton tai session
			String hoVaTen = new String(request.getParameter("hoVaTen").getBytes("ISO-8859-1"),"UTF-8");
			String idQuyen=request.getParameter("idQuyen");
			if (hoVaTen != null&&idQuyen!=null) {// tim kiem
				HttpSession session = request.getSession();
				User objUser = (User) session.getAttribute("userObj");
				int capDo = objUser.getCapDo();
				QuyenBO boQuyen = new QuyenBO();
				ArrayList<Quyen> listQuyen = boQuyen.getListQuyen(capDo);
				request.setAttribute("listQuyen", listQuyen);
				PhanTrangBO boPhanTrang = new PhanTrangBO();
				//---------load danh sach, tim kiem, xoa tin tuc
				int current_page = 1;
				if (request.getParameter("page") != null) {
					current_page = Integer.parseInt(request.getParameter("page"));
				}
				int total = boUser.getTotalSearchAdmin(hoVaTen, idQuyen, capDo);
				int row_count = boPhanTrang.getRowCountAdmin();
				request.setAttribute("sotrang", boPhanTrang.getSoTrang(total,row_count));
				request.setAttribute("current_page", current_page);
					// neu load trang
				ArrayList<User> listUser = boUser.searchUserAdmin(hoVaTen, idQuyen, boPhanTrang.getOffset(current_page, row_count), row_count,capDo);
				// truong hop bang ko co du lieu + xoa dl da bi xoa truoc do
				if (listUser.size() == 0) {
					request.setAttribute("loi", "Không tồn tại dữ liệu!");
				} else {
					request.setAttribute("listUser", listUser);
				}
				request.getRequestDispatcher("index-user.jsp").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}
}
