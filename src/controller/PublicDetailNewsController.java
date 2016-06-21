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
 * Servlet implementation class PublicDetailNewsController
 */
// @WebServlet("/PublicDetailNewsController")
public class PublicDetailNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicDetailNewsController() {
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
		String idTinTuc = request.getParameter("idNews");
		if (idTinTuc != null) {
			DanhMucBO boDanhMuc = new DanhMucBO();
			TinTucBO boTinTuc = new TinTucBO();
			ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc();
			request.setAttribute("listDanhMuc", listDanhMuc);

			TinTuc objTinTuc = boTinTuc.getDetailTinTucAdmin(idTinTuc);
			//tang luot xem
			boTinTuc.tangLuotXemTinTuc(idTinTuc);
			boDanhMuc.tangLuotXemDanhMuc(objTinTuc.getIdDanhMuc());
			
			PhanTrangBO boPhanTrang = new PhanTrangBO();
			int row_count = boPhanTrang.getRowCountPublic();
			ArrayList<TinTuc> listTinTuc = boTinTuc.getListTinTucUserLienQuan(0, row_count, idTinTuc,
					objTinTuc.getIdDanhMuc());

			ArrayList<TinTuc> listTinTucNoiBat = boTinTuc.getListTinTucGoiY(0, row_count, "douutien");
			ArrayList<TinTuc> listTinTucXemNhieu = boTinTuc.getListTinTucGoiY(0, row_count, "luotxem");
			request.setAttribute("listTinTucNoiBat", listTinTucNoiBat);
			request.setAttribute("listTinTucXemNhieu", listTinTucXemNhieu);

			request.setAttribute("listTinTuc", listTinTuc);
			request.setAttribute("objTinTuc", objTinTuc);
			request.getRequestDispatcher("detail-news.jsp").forward(request, response);
		}
	}

}
