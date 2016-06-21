<!-- Đăng nhập admin. Ngô Viết Thành -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Đấu giá</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/templates/admin/css/styles.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/libraries/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/libraries/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/libraries/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/libraries/bootstrap/bootstrap.js"></script>

</head>
<body class="container">
	<!-- Code ở giữa này-->
	<!-- Bắt form dang nhap -->
	<div>
		<div style="text-align:center;">
			<img src="../templates/admin/images/logotc.png" width="600px"/>
		</div>
		<div style="font-size: 400%; text-align: center;">
			<label>Trang quản trị website báo điện tử</label>
		</div>
		<br />
		<div class="loi">
			<span>
				<%
					String loi = (String) request.getAttribute("loi");
					if (loi != null)
						out.print(loi);
				%>
			</span>
		</div>
		<br />
		<form action="login" method="post" class="form-horizontal"
			onsubmit="return checkLogin()">
			<div class="form-group">
				<label class="col-sm-5 control-label">Tài khoản:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="taiKhoan" placeholder="Nhập tài khoản"
					 required pattern="^[A-Za-z0-9]{4,20}$" title="Tài khoản gồm 4 đến 20 ký tự chữ cái hoặc số"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-5 control-label">Mật khẩu:</label>
				<div class="col-sm-2">
					<input type="password" class="form-control" name="matKhau"
						placeholder="Nhập mật khẩu"  required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" 
						title="Mật khẩu gồm 6 đến 20 ký tự phải có cả chữ cái thường, chữ cái hoa và số"
						/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-2">
					<input type="submit" name="login" value="Submit"
						class="btn btn-success" /> <input type="reset" name="reset"
						value="Reset" class="btn btn-success" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>