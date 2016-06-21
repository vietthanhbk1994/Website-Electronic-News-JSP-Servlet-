package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DanhMuc;
import bean.TinTuc;
import bo.DanhMucBO;
import bo.PhanTrangBO;
import bo.TinTucBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminSearchNewsController
 */
//@WebServlet("/AdminSearchNewsController")
public class AdminSearchNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchNewsController() {
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
			if(request.getParameter("search")!=null){
				String tieuDe = new String(request.getParameter("tieuDe").getBytes("ISO-8859-1"),"UTF-8");
				String idDanhMuc = request.getParameter("danhMuc");
				String thoiGianDang = request.getParameter("thoiGianDang");
					DanhMucBO boDanhMuc = new DanhMucBO();
					ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc();
					request.setAttribute("listDanhMuc", listDanhMuc);
					TinTucBO boTinTuc = new TinTucBO();
					PhanTrangBO boPhanTrang = new PhanTrangBO();
					//---------load danh sach, tim kiem, xoa tin tuc
					int current_page = 1;
					if (request.getParameter("page") != null) {
						current_page = Integer.parseInt(request.getParameter("page"));
					}
					int total = boTinTuc.getTotal(tieuDe,idDanhMuc,thoiGianDang);
					int row_count = boPhanTrang.getRowCountAdmin();
					request.setAttribute("sotrang", boPhanTrang.getSoTrang(total,row_count));
					request.setAttribute("current_page", current_page);
					ArrayList<TinTuc> listTinTuc = boTinTuc.getListTinTucAdmin(tieuDe,idDanhMuc,thoiGianDang,boPhanTrang.getOffset(current_page,row_count), row_count);
					// truong hop bang ko co du lieu + xoa dl da bi xoa truoc do
					if (listTinTuc.size() == 0) {
						request.setAttribute("msg", "Không tồn tại dữ liệu!");
					} else {
						request.setAttribute("listTinTuc", listTinTuc);
					}
					RequestDispatcher rd = request.getRequestDispatcher("index-news.jsp");
					rd.forward(request, response);
				}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
