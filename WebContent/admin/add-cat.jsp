<%@page import="bean.Quyen"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<div>
	<%
		String loi = (String) request.getAttribute("loi");
	%>
	<h2>Thêm danh mục</h2>
	<div class="loi">
		<span> <% if (loi != null) out.print(loi); %>
		</span>
	</div>
	<form action="add-cat" method="post" class="form-horizontal" onsubmit="return checkAdd()">
		<div class="form-group">
			<label class="col-sm-2 control-label">Tên danh mục *</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="tenDanhMuc" required maxlength="50">
			</div>
		</div>
		<div class="form-group">
		    <label class="col-sm-2 control-label">Mô tả *</label>
		    <div class="col-sm-10">
		      	<textarea rows="4" cols="50" name="moTa" required="required" maxlength="100"></textarea>
		    </div>
		 </div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-2">
				<button type="submit" class="btn btn-success" name="add">Thêm</button>
			</div>
			<div class="col-sm-2">
				<button type="reset" class="btn btn-danger" onclick="resetForm();">Nhập lại</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
	function resetForm(){
		for ( instance in CKEDITOR.instances ){
	        CKEDITOR.instances[instance].setData('');
	    }
	}
</script>