package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import bo.UserBO;

/**
 * Servlet implementation class AdminDeleteUserController
 */
//@WebServlet("/AdminDeleteUserController")
public class AdminDeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteUserController() {
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
			if(request.getParameter("delete")!=null){
				String[] listIdDelete = request.getParameterValues("check");
				String msg;
				HttpSession session = request.getSession();
				User objUser = (User) session.getAttribute("userObj");
				int capDo = objUser.getCapDo();
				if (boUser.deleteUserAdmin(listIdDelete, capDo)) {
					// thi chay xuong load lai trang
					msg = "Xóa thành công\n";
				} else {// neu xoa that bai, thong bao ko ton tai dl,
					msg = "Dữ liệu không tồn tại để xóa!\n";
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("index-user").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
