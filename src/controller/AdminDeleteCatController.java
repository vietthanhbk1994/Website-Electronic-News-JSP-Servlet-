package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bo.DanhMucBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminDeleteCatController
 */
//@WebServlet("/AdminDeleteCatController")
public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteCatController() {
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
				DanhMucBO boDanhMuc = new DanhMucBO();
				if (boDanhMuc.deleteCatAdmin(listIdDelete)) {
					// thi chay xuong load lai trang
					msg = "Xóa thành công\n";
				} else {// neu xoa that bai, thong bao ko ton tai dl,
					msg = "Dữ liệu không tồn tại để xóa!\n";
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("index-cat").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
