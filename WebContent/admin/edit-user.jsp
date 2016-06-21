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
	<form action="index-user?add=1&load=1" method="post"
		class="form-horizontal" onsubmit="return checkAdd()">
		<div class="form-group">
			<label class="col-sm-2 control-label">Tài khoản *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="taiKhoan">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="matKhau"
					placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Nhập lại mật khẩu *</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="nhapLaiMatKhau"
					placeholder="">
			</div>
		</div>
		<div >
			<div class="form-group">
				<label class="col-sm-2 control-label">Ngày tháng năm sinh *</label>
				<div class="col-sm-6 form-inline">
					<input class="form-control" type="text" name="ngay" placeholder="Ngày" style="width: 12%;"/>
					<input class="form-control" type="text" name="thang" placeholder="Tháng" style="width: 12%;"/>
					<input class="form-control" type="text" name="nam" placeholder="Năm" style="width: 12%;"/>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Họ và tên *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="hoVaTen"
					placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Email *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="email" placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Số điện thoại *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="soDienThoai"
					placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Chứng minh nhân dân *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="chungMinhNhanDan"
					placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">Địa chỉ *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="diaChi" placeholder="">
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
				<button type="submit" class="btn btn-success">Thêm</button>
			</div>
			<div class="col-sm-2">
				<button type="reset" class="btn btn-danger" onclick="">Nhập
					lại</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	//reset form
	/* function resetForm() {
		var resetInput = document.getElementsByTagName("input");
		for (var i = 0; i < resetInput.length; i++) {
			resetInput[i].value = "";
		}
		var resetSelect = document.getElementsByTagName("select");
		for (var i = 0; i < resetSelect.length; i++) {
			resetSelect[i].selectedIndex = "0";
		}
	} */
	function checkAdd() {
		var taiKhoan, matKhau, nhapLaiMatKhau, hoVaTen, email, soDienThoai, chungMinhNhanDan, diaChi, idQuyen;
		taiKhoan = document.getElementsByName("taiKhoan")[0].value;
		matKhau = document.getElementsByName("matKhau")[0].value;
		nhapLaiMatKhau = document.getElementsByName("nhapLaiMatKhau")[0].value;
		hoVaTen = document.getElementsByName("hoVaTen")[0].value;
		email = document.getElementsByName("email")[0].value;
		soDienThoai = document.getElementsByName("soDienThoai")[0].value;
		chungMinhNhanDan = document.getElementsByName("chungMinhNhanDan")[0].value;
		diaChi = document.getElementsByName("diaChi")[0].value;
		idQuyen = document.getElementsByName("idQuyen")[0].value;
		//kiem tra taiKhoan
		var msg = "";
		while (1) {
			if (taiKhoan.length < 1) {
				msg = "Tài khoản không được để trống";
				break;
			}
			if (taiKhoan.length < 4) {
				msg = "Tài khoản nhập vào ít nhất 4 ký tự";
				break;
			}
			if (taiKhoan.length > 20) {
				msg = "Tài khoản nhập vào nhiều nhất 20 ký tự";
				break;
			}
			if (/(\W+)/.test(taiKhoan)) {
				msg = "Tài khoản chỉ gồm chữ số và chữ cái";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("taiKhoan")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Mat khau------------------
		while (1) {
			if (matKhau.length < 1) {
				msg = "Mật khẩu không được để trống";
				break;
			}
			if (matKhau.length < 6) {
				msg = "Mật khẩu nhập vào ít nhất 6 ký tự";
				break;
			}
			if (matKhau.length > 20) {
				msg = "Mật khẩu nhập vào nhiều nhất 20 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("matKhau")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Nhap lai Mat khau------------------
		if (nhapLaiMatKhau != matKhau) {
			alert("Nhập lại mật khẩu phải trùng với Mật khẩu");
			document.getElementsByName("nhapLaiMatKhau")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Ngày tháng năm------------------
		var ngay,thang,nam;
		var d = new Date();
		var maxYear = d.getFullYear();
		var minYear = maxYear-100;
		ngay = document.getElementsByName("ngay")[0];
		thang = document.getElementsByName("thang")[0];
		nam = document.getElementsByName("nam")[0];
		if(ngay.value==""){
			msg="Ngày không được để trống";
			ngay.focus();
			alert(msg);
			return false;
		}
		if(thang.value==""){
			msg="Tháng không được để trống";
			thang.focus();
			alert(msg);
			return false;
		}
		if(nam.value==""){
			msg="Năm không được để trống";
			nam.focus();
			alert(msg);
			return false;
		}
		if(thang.value>0&&thang.value<13){
			if(nam.value>minYear&&nam.value<maxYear){
				switch(thang.value){
					case "1":
					case "3":
					case "5":
					case "7":
					case "8":
					case "10":
					case "12":
						if(ngay.value<0||ngay.value>31){
							msg="Ngày không hợp lệ";
						}
						break;
					case "2":
					case "4":
					case "6":
					case "9":
					case "11":
						if(ngay.value<0||ngay.value>30){
							msg="Ngày không hợp lệ";
						}
						break;
					case "2":
						if((nam.value%4==0&nam.value%100!=0)||nam.value%400==0){
							if(ngay.value<0||ngay.value>29){
								msg="Ngày không hợp lệ";
							}
						}else{
							if(ngay.value<0||ngay.value>28){
								msg="Ngày không hợp lệ";
							}
						}
				}
				if(msg!=""){
					alert(msg);
					thang.focus();
					return false;
				}
					
			}else{
				alert("Năm không hợp lệ");
				nam.focus();
				return false;		
			}
		}else{
			alert("Tháng không hợp lệ");
			thang.focus();
			return false;
		}
		//--------------------------------------------
		//------------Họ và tên------------------
		while (1) {
			if (hoVaTen.length < 1) {
				msg = "Họ và tên không được để trống";
				break;
			}
			if (hoVaTen.length > 50) {
				msg = "Họ và tên nhập vào nhiều nhất 50 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("hoVaTen")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Email------------------
		while (1) {
			if (email.length < 1) {
				msg = "Email không được để trống";
				break;
			}
			if (!(/\S+@\S+\.\S+/.test(email))) {
				msg = "Không đúng định dạng email. Email khải có dạng abc@xyz.abc";
				break;
			}
			if (email.length > 50) {
				msg = "Họ và tên nhập vào nhiều nhất 50 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("email")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Số điện thoại------------------
		while (1) {
			if (soDienThoai.length < 1) {
				msg = "Số điện thoại không được để trống";
				break;
			}
			if ((/\D+/.test(soDienThoai))) {
				msg = "Số điện thoại phải là 1 chuỗi số nguyên không âm";
				break;
			}
			if (soDienThoai.length > 20) {
				msg = "Số điện thoại nhập vào nhiều nhất 20 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("soDienThoai")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Chứng minh nhân dân------------------
		while (1) {
			if (chungMinhNhanDan.length < 1) {
				msg = "Chứng minh nhân dân không được để trống";
				break;
			}
			if ((/\D+/.test(chungMinhNhanDan))) {
				msg = "Chứng minh nhân dân là 1 chuỗi số nguyên không âm";
				break;
			}
			if (chungMinhNhanDan.length > 20) {
				msg = "Chứng minh nhân dân nhập vào nhiều nhất 20 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("chungMinhNhanDan")[0].focus();
			return false;
		}
		//--------------------------------
		//------------Địa chỉ------------------
		while (1) {
			if (diaChi.length < 1) {
				msg = "Địa chỉ không được để trống";
				break;
			}
			if (diaChi.length > 50) {
				msg = "Địa chỉ nhập vào nhiều nhất 50 ký tự";
				break;
			}
			break;
		}
		if (msg != "") {
			alert(msg);
			document.getElementsByName("diaChi")[0].focus();
			return false;
		}
		//--------------------------------

	}
</script>