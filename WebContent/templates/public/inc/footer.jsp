<%@page import="java.util.ArrayList"%>
<%@page import="bean.DanhMuc"%>
<%@page import="bo.LibraryBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Footer -->
<%
	ArrayList<DanhMuc> listDanhMuc2 = (ArrayList<DanhMuc>) request.getAttribute("listDanhMuc");
%>
<div class="footer" id="header">
	<div class="header-menu">
		<div>
			<ul class="nav nav-pills">
				<li role="presentation" class="active"><a
					href="<%=request.getContextPath()%>/trang-chu.html"> <img
						alt=""
						src="<%=request.getContextPath()%>/templates/public/images/home.png"
						width="20px" height="20px" />
				</a></li>
				<%
					String urlCat2 = request.getContextPath() + "/index-cat?idCat=";
					for (DanhMuc objDanhMuc : listDanhMuc2) {
				%>
				<li role="presentation"><a
					href=<%=urlCat2 + objDanhMuc.getIdDanhMuc()%>> <%=objDanhMuc.getTenDanhMuc()%>
				</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<div id="header">
		<!-- Header. Status part -->
		<div class="header-top">
			<div class="left header-logo">
				<a href="<%=request.getContextPath()%>/trang-chu.html"> <img
					src="<%=request.getContextPath()%>/templates/public/images/logotc.png"
					width="200px" height="50px" />
				</a>
				<span>Viết bởi Ngô Viết Thành, lớp 12T4, khoa CNTT, trường Đại học Bách Khoa- Đại học Đà Nẵng</span>
			</div>
		</div>
	</div>

</div>
<!-- End #footer -->
</body>
</html>