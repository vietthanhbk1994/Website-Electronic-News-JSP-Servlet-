<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Đấu giá</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/admin/css/styles.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/templates/admin/js/check.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.js"></script>
</head>
<%
	User userObj = (User) session.getAttribute("userObj");
	if (userObj == null) {
		//chua dang nhap thi chuyen ve trang login
		response.sendRedirect("login.jsp");
		return;
	} else
%>

<body class="container-fluid">
	<div style="background-color: #f1f1f1;">
		<!-- Header -->
		<div id="header">
			<!-- TOP -->
			<div class="row">
				<div class="col-md-6">
					<h3>
						<a href="#">Quản trị hệ thống Website báo điện tử</a>
					</h3>
				</div>
				<div class="col-md-3 col-md-offset-2" style="padding-top: 15px;">
					Chào <span class="text text-lg text-danger"><%=userObj.getTenQuyen()%>
						<b><%=userObj.getHoVaTen()%></b></span>
				</div>
				<div style="padding-top: 10px;">
					<a class="btn btn-danger" href="logout"><img
						src="<%=request.getContextPath()%>/templates/admin/images/Logout-icon.png"
						alt="" width="20" height="20">Thoát</a>
				</div>
			</div>
			<!-- /TOP -->
			<!-- BOTTOM -->
			<div class="row" style="padding-top: 30px;">
				<!-- /LEFT -->
				<div class="col-md-2">
					<!-- Groups -->
					<div class="panel-group" id="menu-quantri">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<img
										src="<%=request.getContextPath()%>/templates/admin/images/home.png"
										alt="" width="20" height="20"> <a href="index"
										data-parent="#menu-quantri">Trang chủ</a>
								</h4>
							</div>
						</div>
						<%
							if (userObj.getCapDo() == 0 || userObj.getCapDo() == 1) {
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<img
										src="<%=request.getContextPath()%>/templates/admin/images/location-news-icon.png"
										alt="" width="20" height="20"> <a data-toggle="collapse"
										data-parent="#menu-quantri" href="#tintuc">Quản trị tin
										tức</a>
								</h4>
							</div>
							<div id="tintuc" class="panel-collapse collapse">
								<ul class="list-group">
									<li class="list-group-item"><a href="index-news">Danh
											sách</a></li>
									<li class="list-group-item"><a href="index-news">Tìm
											kiếm</a></li>
									<li class="list-group-item"><a href="add-news?showadd=1">Thêm</a></li>
									<li class="list-group-item"><a href="index-news">Sửa</a></li>
									<li class="list-group-item"><a href="index-news">Xóa</a></li>
								</ul>
							</div>
						</div>
						<%
							}
							if (userObj.getCapDo() == 0 || userObj.getCapDo() == 3) {
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<img
										src="<%=request.getContextPath()%>/templates/admin/images/icon-head-t.png"
										alt="" width="20" height="20"> <a data-toggle="collapse"
										data-parent="#menu-quantri" href="#danhmuc">Quản trị danh
										mục</a>
								</h4>
							</div>
							<div id="danhmuc" class="panel-collapse collapse">
								<ul class="list-group">
									<li class="list-group-item"><a href="index-cat">Danh
											sách</a></li>
									<li class="list-group-item"><a href="index-cat">Tìm
											kiếm</a></li>
									<li class="list-group-item"><a href="add-cat?showadd=1">Thêm</a></li>
									<li class="list-group-item"><a href="index-cat">Sửa</a></li>
									<li class="list-group-item"><a href="index-cat">Xóa</a></li>
								</ul>
							</div>
						</div>
						<%
							}
							if (userObj.getCapDo() == 0 || userObj.getCapDo() == 2) {
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<img
										src="<%=request.getContextPath()%>/templates/admin/images/user.png"
										alt="" width="20" height="20"> <a data-toggle="collapse"
										data-parent="#menu-quantri" href="#thanhvien">Quản trị
										thành viên</a>
								</h4>
							</div>
							<div id="thanhvien" class="panel-collapse collapse">
								<ul class="list-group">
									<li class="list-group-item"><a href="index-user">Danh
											sách</a></li>
									<li class="list-group-item"><a href="index-user">Tìm
											kiếm</a></li>
									<li class="list-group-item"><a href="add-user?showadd=1">Thêm</a></li>
									<li class="list-group-item"><a href="index-user">Xóa</a></li>
								</ul>
							</div>
						</div>
						<%
							}
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<img
										src="<%=request.getContextPath()%>/templates/admin/images/profile-icon-63199.png"
										alt="" width="20" height="20"> <a href="info-account"
										data-parent="#menu-quantri">Thông tin tài khoản</a>
								</h4>
							</div>
						</div>
					</div>
				</div>
				<!-- /LEFT -->
				<!-- RIGHT -->
				<div class="col-md-10">