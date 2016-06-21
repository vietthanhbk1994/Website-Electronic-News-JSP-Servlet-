<%@page import="bo.LibraryBO"%>
<%@page import="bean.DanhMuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Đấu giá</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/css/styles.css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/libraries/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/libraries/js/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/libraries/js/ckeditor/ckeditor.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/templates/public/js/check.js"></script>
	</head>
	<%
		ArrayList<DanhMuc> listDanhMuc = (ArrayList<DanhMuc>) request.getAttribute("listDanhMuc");
		String tieuDe = request.getParameter("tieuDe");
	%>
	<body class="container">
		<!-- Header -->
        <div id="header">
         	<!-- Header. Status part -->
         	<div class="header-top">
	            <div class="left header-logo">
	            	<a href="<%=request.getContextPath() %>/trang-chu.html">
	            		<img src="<%=request.getContextPath()%>/templates/public/images/logotc.png" width="200px" height="50px"/>
	            	</a>
	            </div> 
	            <!-- End #header-status -->
	            <!-- Header. Main part -->
	            <div class="header-search">
	            	<form action="<%=request.getContextPath() %>/search-news" method="get" class="form-inline">
						<input type="text" value="<%if(tieuDe!=null) out.print(tieuDe); %>" class="form-control col-sm-4" name="tieuDe" placeholder="Nhập tiêu đề tin cần tìm kiếm" style="width: 45%;">
						<input type="submit" class="btn btn-success" name="search" value="Tìm kiếm" style="margin-left: 1%;">
	            	</form>
	            </div> 
            <!-- End #header-main -->
            </div>
            <div class="header-menu">
            	<div>
            		<ul class="nav nav-pills">
            			<li role="presentation" class="active">
            				<a href="<%=request.getContextPath() %>/trang-chu.html">
            					<img alt="" src="<%=request.getContextPath() %>/templates/public/images/home.png" width="20px" height="20px"/>
            				</a>
            			</li>
            			<%
            				LibraryBO boLib = new LibraryBO();
            				String urlCat = request.getContextPath()+"/index-cat?idCat=";
            				for(DanhMuc objDanhMuc : listDanhMuc){
            			%>
            			<li role="presentation" >
            				<a href=<%=urlCat + objDanhMuc.getIdDanhMuc()%>>
            					<%=objDanhMuc.getTenDanhMuc() %>
            				</a>
            			</li>
            			<%
            				}
            			%>
            		</ul>
            	</div>
            </div>
        </div> 
         <!-- End #header -->
         