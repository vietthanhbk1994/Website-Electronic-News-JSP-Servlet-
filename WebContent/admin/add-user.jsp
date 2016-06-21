<%@page import="bean.Quyen"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<div>
	<%
		ArrayList<Quyen> listQuyen = (ArrayList<Quyen>) request.getAttribute("listQuyen");
		String loi = (String) request.getAttribute("loi");
	%>
	<h2>Thêm thành viên</h2>
	<div class="loi">
		<span> <%
 	if (loi != null)
 		out.print(loi);
 %>
		</span>
	</div>
	<form action="add-user" method="post"
		class="form-horizontal" onsubmit="return checkAdd()">
		<div class="form-group">
			<label class="col-sm-2 control-label">Tài khoản *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="taiKhoan" required maxlength="20" minlength="4">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" value="" class="form-control" name="matKhau"
					placeholder="Có ít nhất 6 ký tự chữ Hoa, chữ thường,số" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}" 
						title="Mật khẩu gồm 6 đến 20 ký tự phải có cả chữ cái thường, chữ cái hoa và số"
						onchange="form.nhapLaiMatKhau.pattern = this.value;" >
						
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Nhập lại mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" value="" class="form-control" name="nhapLaiMatKhau"
					placeholder="Nhập lại mật khẩu" title="Nhập lại mật khẩu phải trùng với mật khẩu">
			</div>
		</div>
		<div >
			<div class="form-group">
				<label class="col-sm-2 control-label">Ngày tháng năm sinh *</label>
				<div class="col-sm-6 form-inline">
					<input class="form-control" type="date" value="" name="ngaySinh"
					 title="Thời gian phải nằm trong 100 năm trở lại." onchange="checkAdd()" required /> 
					 <span id="ngaySinh" class="loi"></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Họ và tên *</label>
			<div class="col-sm-4">
				<input type="text"  value="" class="form-control" name="hoVaTen"
					placeholder=""  required maxlength="50">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email *</label>
			<div class="col-sm-4">
				<input type="email" value="" class="form-control" 
				name="email" placeholder="" required maxlength="50" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Số điện thoại *</label>
			<div class="col-sm-4">
				<input type="text" value="" class="form-control" name="soDienThoai"
					placeholder="" pattern="[\d]{10,20}" title="Số điện thoại có từ 10 đến 20 ký tự số">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Chứng minh nhân dân*</label>
			<div class="col-sm-4">
				<input type="text" value="" class="form-control" name="chungMinhNhanDan"
					pattern="[\d]{9,20}" title="Chứng minh nhân dân có từ 9 đến 20 ký tự số">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Địa chỉ *</label>
			<div class="col-sm-4">
				<input type="text" value="" class="form-control" name="diaChi" 
					required maxlength="50">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Quyền *</label>
			<div class="col-sm-4">
				<select class="form-control" name="idQuyen">
					<%
						for (Quyen objQuyen : listQuyen) {
					%>
					<option value="<%=objQuyen.getIdQuyen()%>"><%=objQuyen.getTenQuyen()%></option>
					<%
						}
					%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn btn-success" name="add">Thêm</button>
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