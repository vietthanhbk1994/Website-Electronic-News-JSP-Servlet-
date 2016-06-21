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

/**
 * Servlet implementation class PublicIndexController
 */
//@WebServlet("/PublicIndexController")
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
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
		
		DanhMucBO boDanhMuc = new DanhMucBO();
		TinTucBO boTinTuc = new TinTucBO();
		ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc(); 
		request.setAttribute("listDanhMuc", listDanhMuc);
		PhanTrangBO boPhanTrang = new PhanTrangBO();
		int row_count=boPhanTrang.getRowCountPublic();
		//System.out.println(row_count);
		//lay dang sach tin tuc. trang chu
		ArrayList<ArrayList<TinTuc>> listTinTuc = new ArrayList<ArrayList<TinTuc>>();
		for (DanhMuc objDanhMuc : listDanhMuc) {
			//System.out.println("1");
			ArrayList<TinTuc> list= boTinTuc.getListTinTucUser(0, row_count,objDanhMuc.getIdDanhMuc());
			listTinTuc.add(list);
		}
		request.setAttribute("listTinTuc", listTinTuc);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

}
