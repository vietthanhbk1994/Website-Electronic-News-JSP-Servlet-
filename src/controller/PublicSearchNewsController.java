package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DanhMuc;
import bean.TinTuc;
import bo.DanhMucBO;
import bo.PhanTrangBO;
import bo.TinTucBO;

/**
 * Servlet implementation class PublicSearchNewsController
 */
//@WebServlet("/PublicSearchNewsController")
public class PublicSearchNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicSearchNewsController() {
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

		String tieuDe = request.getParameter("tieuDe");
		if (tieuDe != null) {// tim kiem
			DanhMucBO boDanhMuc = new DanhMucBO();
			TinTucBO boTinTuc = new TinTucBO();
			ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc(); 
			request.setAttribute("listDanhMuc", listDanhMuc);
			PhanTrangBO boPhanTrang = new PhanTrangBO();
			int row_count=boPhanTrang.getRowCountPublic();
			ArrayList<TinTuc> listTinTucNoiBat = boTinTuc.getListTinTucGoiY(0, row_count,"douutien");
			ArrayList<TinTuc> listTinTucXemNhieu = boTinTuc.getListTinTucGoiY(0, row_count,"luotxem");
			request.setAttribute("listTinTucNoiBat", listTinTucNoiBat);
			request.setAttribute("listTinTucXemNhieu", listTinTucXemNhieu);
			//Phan trang----------------------------------
			int current_page = 1;
			String page=request.getParameter("page");
			if(page!=null){
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			
			int total = boTinTuc.getTotalPublic(tieuDe,"");
			request.setAttribute("sotrang", boPhanTrang.getSoTrang(total,row_count));
			request.setAttribute("current_page", current_page);
			int offset = boPhanTrang.getOffset(current_page, row_count);
			request.setAttribute("tieuDe", tieuDe);
			
			ArrayList<TinTuc> listTinTuc = boTinTuc.getListTinTucSearchUser(offset, row_count, tieuDe);
			request.setAttribute("listTinTuc", listTinTuc);
			if (listTinTuc.size() == 0) {// tra ve danh sach tim dc
				request.setAttribute("loi", "Không tồn tại dữ liệu!");
			}
			request.getRequestDispatcher("search-news.jsp").forward(request, response);
		}
	}

}
