<%@page import="bean.DanhMuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<div>
	<%
		ArrayList<DanhMuc> listDanhMuc = (ArrayList<DanhMuc>) request.getAttribute("listDanhMuc");
		String loi = (String)request.getAttribute("loi");
	%>
	<h2>Thêm tin tức</h2>
	<div class="loi">
		<span><%if(loi!=null) out.print(loi); %></span>
	</div>
	<form action="add-news?add=1" method="post" enctype="multipart/form-data" class="form-horizontal" onsubmit="return checkAdd()">
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Tiêu đề *</label>
		    <div class="col-sm-8">
		      	<input type="text" class="form-control" name="tieuDe"  required maxlength="100">
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Nguồn *</label>
		    <div class="col-sm-4">
		      	<input type="text" class="form-control" name="nguon" placeholder="Tác giả" required maxlength="50">
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Danh mục *</label>
		    <div class="col-sm-4">
		      	<select class="form-control" name="danhMuc">
		   			<%
		   				for(DanhMuc objDanhMuc: listDanhMuc){
		   			%>
					<option value="<%=objDanhMuc.getIdDanhMuc()%>"><%=objDanhMuc.getTenDanhMuc()%></option>
					<%} %>
				</select>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Đô ưu tiên: *</label>
		    <div class="col-sm-4">
		      	<select class="form-control" name="doUuTien">
		   			<%
		   				
		   				for(int i = 1; i<=10; i++){
		   					String uuTien = "";
		   					if(i==1) uuTien=" (Thấp nhất)";
		   					if(i==10) uuTien=" (Cao nhất)";
		   			%>
					<option value="<%=i%>"><%=i+uuTien%></option>
					<%} %>
				</select>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Hình ảnh *</label>
		    <div class="col-sm-10">
		      	<input type="file" class="" name="hinhAnh" onchange="checkHinhAnh(this);" required accept="image/*">
		      	<span class="loi" id="loi"></span>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">
		    	
		    </label>
		    <div class="col-sm-3">
		      	<img id="img" src="<%=request.getContextPath()%>/templates/admin/images/noImage.jpg" alt="img" height="108" width="180" />
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Mô tả *</label>
		    <div class="col-sm-10">
		      	<textarea rows="3" cols="100" class="" name="moTa" required maxlength="97"></textarea>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-2 control-label">Nội dung *</label>
		    <div class="col-sm-10">
		      	<textarea rows="" cols="" class="ckeditor" name="noiDung" required></textarea>
		    </div>
		 </div>
		 <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-2">
		 		<button type="submit" class="btn btn-success" name="">Thêm</button>
		    </div>
		    <div class="col-sm-2">
		    	<button type="reset" class="btn btn-danger" onclick="resetForm();">Nhập lại</button>
		    </div>
		 </div>
	</form>
</div>
<script type="text/javascript">
	//reset form
	function resetForm(){
		for ( instance in CKEDITOR.instances ){
	        //CKEDITOR.instances[instance].updateElement();
	        CKEDITOR.instances[instance].setData('');
	    }
	}
	//kiem tra loc giao dich
	function checkHinhAnh(input){
		var reader = new FileReader();
		reader.onload = function(e){
			var img = document.getElementById("img");
			img.src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	}
	function checkAdd() {
	    var hinhAnh,msg="";
	    //check hinh anh
	    hinhAnh = document.getElementsByName("hinhAnh")[0];
		var txt;
		if('files' in hinhAnh){
			if(hinhAnh.files.length == 1){
				var file = hinhAnh.files[0];
				if('size' in file){
					var sizeFile = file.size;
					if(sizeFile>(1024*1024)){//>2MB
						msg="Kích thước ảnh phải <= 2MB";
					}
				}
				if('name' in file){
					var nameFile = file.name;
					if(nameFile.length<1|| nameFile.length>50){
						msg="Tên ảnh phải <=50 ký tự";
					}
					var typeFile = nameFile.split('.')[nameFile.split('.').length - 1].toLowerCase();
					if(!(typeFile=="jpg"||typeFile=="jpeg"||typeFile=="gif"||typeFile=="png")){
						msg="Phải chọn file hình ảnh .jpg, .jpeg, .gif, .png";
					}
				}
				if(msg!=""){
					document.getElementById("loi").innerHTML=msg;
					document.getElementsByName("hinhAnh")[0].focus();
					return false;
				}
			}
		}
	}
</script>