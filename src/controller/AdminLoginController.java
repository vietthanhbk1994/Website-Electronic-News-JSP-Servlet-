package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bo.LibraryBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminLoginController
 */
//@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginController() {
        super();
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
		
		String submit = request.getParameter("login");
		//Kiem tra mo trang dang nhap hay click vao dang nhap
		if(submit!=null){//neu nham vao submit thi kiem tra tai khoan mat khau
			LibraryBO boLib = new LibraryBO();
			String taiKhoan = request.getParameter("taiKhoan");
			String matKhau = boLib.md5(request.getParameter("matKhau"));
			UserBO boUser = new UserBO();
			// goi ham kiem tra tai khoan, mat khau trong Model
			User userObj = boUser.checkUser(taiKhoan, matKhau);
			if(userObj!=null){//dung tai khoan mat khau
				HttpSession session = request.getSession();
				session.setAttribute("userObj", userObj);
				response.sendRedirect("index");
			}else{//sai tai khoan mat khau
				request.setAttribute("loi", "Tên đăng nhập hoặc mật khẩu bị sai. Vui lòng nhập lại");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		}else{//neu mo trang dang nhap
			response.sendRedirect("login.jsp");
		}
	}
}
