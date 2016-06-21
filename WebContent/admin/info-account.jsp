<%@page import="bean.Quyen"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<%@ include file="/templates/admin/inc/header.jsp"%>
<div>
	<%
		String loi = (String) request.getAttribute("loi");
		User objUser = (User) request.getAttribute("objUser");
		String ngaySinh = objUser.getNgaySinh();
	%>
	<h2>Thông tin cá nhân</h2>
	<div class="loi">
		<span> <%
 	if (loi != null)
 		out.print(loi);
 %>
		</span>
	</div>
	<form action="info-account" method="post"
		class="form-horizontal" onsubmit="return checkAdd()" name="form">
		<div class="form-group">
			<label class="col-sm-2 control-label">Tài khoản *</label>
			<div class="col-sm-4">
				<span><%=objUser.getTaiKhoan() %></span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" value="" class="form-control" name="matKhau"
					placeholder="Nhập mật khẩu mới" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{0,20}" 
						title="Mật khẩu gồm 6 đến 20 ký tự phải có cả chữ cái thường, chữ cái hoa và số"
						onchange="form.nhapLaiMatKhau.pattern = this.value;" >
						
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Nhập lại mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" value="" class="form-control" name="nhapLaiMatKhau"
					placeholder="Nhập mật khẩu mới" title="Nhập lại mật khẩu phải trùng với mật khẩu">
			</div>
		</div>
		<div >
			<div class="form-group">
				<label class="col-sm-2 control-label">Ngày tháng năm sinh *</label>
				<div class="col-sm-6 form-inline">
					<input class="form-control" type="date" value="<%=ngaySinh %>" name="ngaySinh"
					 title="Thời gian phải nằm trong 100 năm trở lại." onchange="checkAdd()"/> 
					 <span id="ngaySinh" class="loi"></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Họ và tên *</label>
			<div class="col-sm-4">
				<input type="text"  value="<%=objUser.getHoVaTen() %>" class="form-control" name="hoVaTen"
					required maxlength="50" placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email *</label>
			<div class="col-sm-4">
				<input type="email" value="<%=objUser.getEmail() %>" class="form-control" 
				name="email" placeholder="" required maxlength="50" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Số điện thoại *</label>
			<div class="col-sm-4">
				<input type="text" value="<%=objUser.getSoDienThoai() %>" class="form-control" name="soDienThoai"
					placeholder="Nhập mật khẩu mới" pattern="[\d]{10,20}" 
						title="Số điện thoại có từ 10 đến 20 ký tự số">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Chứng minh nhân dân *</label>
			<div class="col-sm-4">
				<input type="text" value="<%=objUser.getChungMinhNhanDan() %>" class="form-control" name="chungMinhNhanDan"
					pattern="[\d]{9,20}" title="Chứng minh nhân dân có từ 9 đến 20 ký tự số">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Địa chỉ *</label>
			<div class="col-sm-4">
				<input type="text" value="<%=objUser.getDiaChi() %>" class="form-control" name="diaChi" 
					required maxlength="50">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Quyền *</label>
			<div class="col-sm-4">
				<span><%=objUser.getTenQuyen()%></span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Số bài đăng *</label>
			<div class="col-sm-4">
				<span><%=objUser.getSoBaiDang()%></span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Điểm cống hiến *</label>
			<div class="col-sm-4">
				<span><%=objUser.getDiemCongHien() %></span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<input type="submit" value="Chỉnh sửa" name="edit" class="btn btn-success">
			</div>
			<div class="col-sm-2">
				<button type="reset" class="btn btn-danger" onclick="resetForm();">Nhập
					lại</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	function checkAdd() {
		var msg = "";
		//------------Ngày tháng năm------------------
		var ngay,thang,nam;
		var d = new Date();
		var maxYear = d.getFullYear();
		var minYear = maxYear-100;
		var ngaySinh = form.ngaySinh;
		var namSinh = ngaySinh.value.split("-")[0];
		if(namSinh<minYear || namSinh>maxYear){
			ngaySinh.focus();
			document.getElementById("ngaySinh").innerHTML = "Thời gian phải trong khoảng 100 năm trở lại";
			return false;
		}
	}
</script>