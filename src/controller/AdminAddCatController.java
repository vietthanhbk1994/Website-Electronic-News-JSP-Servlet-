package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DanhMuc;
import bo.DanhMucBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminAddCatController
 */
//@WebServlet("/AdminAddCatController")
public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCatController() {
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
			DanhMucBO boDanhMuc = new DanhMucBO();
			String loi="";
			if(request.getParameter("add")!=null){
				String tenDanhMuc, moTa;
				tenDanhMuc = request.getParameter("tenDanhMuc");
				//Kiem tra trung
				if(boDanhMuc.isDanhMucExist(tenDanhMuc)){
					loi+= "Tên danh mục đã bị trùng";
				}else{
					moTa = request.getParameter("moTa");
					DanhMuc objDanhMuc = new DanhMuc();
					objDanhMuc.setTenDanhMuc(tenDanhMuc);
					objDanhMuc.setMoTa(moTa);
					
					if(boDanhMuc.addDanhMucAdmin(objDanhMuc)){
						loi += "Thêm danh mục thành công";
						request.setAttribute("msg", loi);
						request.getRequestDispatcher("index-cat").forward(request, response);
						return;
					}else{
						loi+="Thêm dữ liệu vào database thất bại";
					}
				}
				request.setAttribute("loi", loi);
				request.getRequestDispatcher("add-cat.jsp").forward(request, response);
			}
			// hien thi form add
			if(request.getParameter("showadd")!=null){
				request.getRequestDispatcher("add-cat.jsp").forward(request, response);
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}
}
