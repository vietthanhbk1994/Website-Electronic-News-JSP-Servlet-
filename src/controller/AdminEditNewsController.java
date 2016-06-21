package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import bean.DanhMuc;
import bean.TinTuc;
import bean.User;
import bo.DanhMucBO;
import bo.TinTucBO;
import bo.UserBO;

/**
 * Servlet implementation class AdminEditNewsController
 */
//@WebServlet("/AdminEditNewsController")
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditNewsController() {
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
		UserBO boUser = new UserBO();
		if(boUser.isLogin(request, response)){//neu ton tai session
			TinTucBO boTinTuc = new TinTucBO();
			String idTinTuc = request.getParameter("id");
			TinTuc objTinTucOld = boTinTuc.getDetailTinTucAdmin(idTinTuc);
			if(request.getParameter("edit")!=null){
				String tieuDe = "",nguon = "", hinhAnh = "",moTa = "",noiDung = "",danhMuc="";
				int  doUuTien = 0;
				
				HttpSession session = request.getSession();
				User objUser = (User) session.getAttribute("userObj");
				String idUser = objUser.getIdUser();
				
				if (ServletFileUpload.isMultipartContent(request)) {
					DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
					ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
					try {
						List<FileItem> fileItems = fileUpload.parseRequest(request);
						for (FileItem fileItem : fileItems) {
							// neu la file thong thuong
							if (fileItem.isFormField()) {
								String fieldName = fileItem.getFieldName();
								String fieldValue = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
								switch (fieldName) {
								case "tieuDe":
									tieuDe = fieldValue;
									break;
								case "nguon":
									nguon = fieldValue;
									break;
								case "danhMuc":
									danhMuc = fieldValue;
									break;
								case "doUuTien":
									doUuTien = Integer.parseInt(fieldValue);
									break;
								case "moTa":
									moTa = fieldValue;
									break;
								case "noiDung":
									noiDung = fieldValue;
									break;
								/*case "duyet":
									duyet = Integer.parseInt(fieldValue);
									break;*/
								default:
									break;
								}
							} else {// neu la file
								hinhAnh = fileItem.getName();
								if (!"".equals(hinhAnh)) {
									Path path = Paths.get(request.getServletContext().getRealPath("") + File.separator + "files"
											+ File.separator + objTinTucOld.getHinhAnh());
									Files.delete(path);
									//FilenameUtils.getBaseName(hinhAnh)
									hinhAnh = "file-" + System.nanoTime() + "."
											+ FilenameUtils.getExtension(hinhAnh);
									String pathName = request.getServletContext().getRealPath("") + File.separator
											+ "files" + File.separator + hinhAnh;
									File file = new File(pathName);
									fileItem.write(file);
								} else {
									hinhAnh = objTinTucOld.getHinhAnh();
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					TinTuc objTinTuc = new TinTuc();
					objTinTuc.setIdTinTuc(idTinTuc);
					objTinTuc.setIdUser(idUser);
					objTinTuc.setIdDanhMuc(danhMuc);
					objTinTuc.setTieuDe(tieuDe);
					objTinTuc.setMoTa(moTa);
					objTinTuc.setNoiDung(noiDung);
					objTinTuc.setDuyet(objTinTucOld.getDuyet());
					objTinTuc.setHinhAnh(hinhAnh);
					//dung ham curdate() cua mysql de lay ngay thang hien tai la thoigiandang
					objTinTuc.setDoUuTien(doUuTien);
					//objTinTuc.setLuotXem(0);
					objTinTuc.setNguon(nguon);
					// -----------Them tin tuc vao db--------
					if(boTinTuc.editTinTucAdmin(objTinTuc)){
						System.out.println("vao 2");
						response.sendRedirect("index-news");
					}else{
						request.setAttribute("loi", "Thêm dữ liệu vào database thất bại");
						DanhMucBO boDanhMuc = new DanhMucBO();
						ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc();
						request.setAttribute("listDanhMuc", listDanhMuc);
						if(objTinTucOld!=null){
							request.setAttribute("objTinTuc", objTinTucOld);
							request.getRequestDispatcher("edit-news.jsp").forward(request, response);
						}/*else{
							response.sendRedirect("index-news");
						}*/
					}
				}
			}
			// hien thi form edit
			if(request.getParameter("showedit")!=null){
				DanhMucBO boDanhMuc = new DanhMucBO();
				ArrayList<DanhMuc> listDanhMuc = boDanhMuc.getListDanhMuc();
				request.setAttribute("listDanhMuc", listDanhMuc);
				if(objTinTucOld!=null){
					request.setAttribute("objTinTuc", objTinTucOld);
					request.getRequestDispatcher("edit-news.jsp").forward(request, response);
				}else{
					response.sendRedirect("index-news");
				}
			}
		}else{//neu ko co session
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
		}
	}

}
