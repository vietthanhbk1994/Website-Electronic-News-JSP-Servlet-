package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.TinTuc;
import bo.TinTucBO;

/**
 * Servlet implementation class AdminAjaxActiveController
 */
//@WebServlet("/AdminAjaxActiveController")
public class AdminAjaxActiveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAjaxActiveController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//HttpSession session = request.getSession();
		//User objSUser = (User) session.getAttribute("objSUser");
		String id = request.getParameter("idTinTuc");
		TinTucBO boTinTuc = new TinTucBO();
		TinTuc objTinTuc = boTinTuc.getDetailTinTucAdmin(id);
		//if (objSUser.getCapDo() ==2 || objSUser.getCapDo()==0|| objSUser.getIdUser()==objTinTuc.getIdUser()) {
			PrintWriter out = response.getWriter();
			String contextPath = request.getContextPath();
			if (objTinTuc.getDuyet()==1) {
				objTinTuc.setDuyet(0);
				out.print("<a href=\"javascript:void(0)\" onclick=\"return setActive('" + objTinTuc.getIdTinTuc()
						+ "')\" title=\"Kích hoạt\">");
				out.print("<img src='" + contextPath + "/templates/admin/images/minus-circle.gif'" + "/>");
				out.print("</a>");
			} else {
				objTinTuc.setDuyet(1);
				out.print("<a href=\"javascript:void(0)\" onclick=\"return setActive('" + objTinTuc.getIdTinTuc()
						+ "')\" title=\"Kích hoạt\">");
				out.println("<img src='" + contextPath + "/templates/admin/images/tick-circle.gif'" + "/>");
				out.print("</a>");
			}
			boTinTuc.editTinTucAdmin(objTinTuc);
			System.out.println(objTinTuc.toString());
		//}
	}

}
