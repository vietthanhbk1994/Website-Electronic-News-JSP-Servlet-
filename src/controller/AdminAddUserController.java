package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Quyen;
import bean.User;
import bo.QuyenBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminEditInfoAccountController
 */
public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddUserController() {
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
			HttpSession session = request.getSession();
			User objUserSession = (User) session.getAttribute("userObj");
			int capDo = objUserSession.getCapDo();
			QuyenBO boQuyen = new QuyenBO();
			ArrayList<Quyen> listQuyen = boQuyen.getListQuyen(capDo);
			request.setAttribute("listQuyen", listQuyen);
			if(request.getParameter("add")!=null){
				String idQuyen,taiKhoan,matKhau,hoVaTen, soDienThoai,email,diaChi,chungMinhNhanDan,ngaySinh;
				taiKhoan = request.getParameter("taiKhoan");
				//System.out.println(taiKhoan);
				//Kiem tra trung
				if(boUser.isUserExist(taiKhoan)){
					request.setAttribute("loi", "Tài khoản đã bị trùng");
					RequestDispatcher rd = request.getRequestDispatcher("add-user.jsp");
					rd.forward(request, response);
					return;
				}
				matKhau = request.getParameter("matKhau");
				ngaySinh = request.getParameter("ngaySinh");
				hoVaTen = request.getParameter("hoVaTen");
				soDienThoai = request.getParameter("soDienThoai");
				chungMinhNhanDan = request.getParameter("chungMinhNhanDan");
				email = request.getParameter("email");
				diaChi = request.getParameter("diaChi");
				idQuyen = request.getParameter("idQuyen");
				
				User objUser = new User();
				objUser.setTaiKhoan(taiKhoan);
				objUser.setMatKhau(matKhau);
				objUser.setNgaySinh(ngaySinh);
				objUser.setHoVaTen(hoVaTen);
				objUser.setSoDienThoai(soDienThoai);
				objUser.setChungMinhNhanDan(chungMinhNhanDan);
				objUser.setEmail(email);
				objUser.setDiaChi(diaChi);
				objUser.setIdQuyen(idQuyen);
				if(boUser.addUserAdmin(objUser)){
					response.sendRedirect("index-user");
				}else{
					request.setAttribute("loi", "Thêm dữ liệu vào database thất bại");
					request.getRequestDispatcher("add-user.jsp").forward(request, response);
				}
			}
			// hien thi form add
			if(request.getParameter("showadd")!=null){
				request.getRequestDispatcher("add-user.jsp").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
