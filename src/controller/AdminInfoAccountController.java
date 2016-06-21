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
 * Servlet implementation class AdminInfoAccountController
 */
//@WebServlet("/AdminInfoAccountController")
public class AdminInfoAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminInfoAccountController() {
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
			String loi = "";
			String edit = request.getParameter("edit");
			HttpSession session = request.getSession();
			User objUserSession =(User) session.getAttribute("userObj");
			User objUser = new User();
			objUser = boUser.getDetailUserAdmin(objUserSession.getIdUser());
			request.setAttribute("objUser", objUser);
			if(edit!=null){//edit info account
				String matKhau,hoVaTen, soDienThoai,email,diaChi,chungMinhNhanDan,ngaySinh;
				matKhau = request.getParameter("matKhau");
				hoVaTen = request.getParameter("hoVaTen");
				soDienThoai = request.getParameter("soDienThoai");
				chungMinhNhanDan = request.getParameter("chungMinhNhanDan");
				ngaySinh = request.getParameter("ngaySinh");
				email = request.getParameter("email");
				diaChi = request.getParameter("diaChi");
				
				if("".equals(matKhau)){
					objUser.setMatKhau(objUserSession.getMatKhau());
				}else{
					objUser.setMatKhau(matKhau);
				}
				objUser.setIdUser(objUserSession.getIdUser());
				objUser.setHoVaTen(hoVaTen);
				objUser.setSoDienThoai(soDienThoai);
				objUser.setChungMinhNhanDan(chungMinhNhanDan);
				objUser.setNgaySinh(ngaySinh);
				objUser.setEmail(email);
				objUser.setDiaChi(diaChi);
				if(!boUser.editUserAdmin(objUser)){
					loi ="Cập nhật dữ liệu vào database thất bại";
					request.setAttribute("loi", loi);
				}else{
					session.setAttribute("userObj", objUser);
				}
				request.getRequestDispatcher("info-account.jsp").forward(request, response);
			}else{//load trang info-account
				request.getRequestDispatcher("info-account.jsp").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
