<%@page import="bean.DanhMuc"%>
<%@page import="bean.TinTuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
	<%
		ArrayList<TinTuc> listTinTuc = (ArrayList<TinTuc>) request.getAttribute("listTinTuc");
		ArrayList<DanhMuc> listDanhMuc = (ArrayList<DanhMuc>) request.getAttribute("listDanhMuc");
		int stt=1;
		String msg = (String)request.getAttribute("msg");
		String tieuDeSearch="";
		String thoiGianDangSearch=null;
		String danhMucSearch="";
		if(request.getParameter("search")!=null){
			tieuDeSearch = new String(request.getParameter("tieuDe").getBytes("ISO-8859-1"),"UTF-8");
			danhMucSearch = request.getParameter("danhMuc");
			thoiGianDangSearch = request.getParameter("thoiGianDang");
		}
	%>
	<div>
		<form action="search-news" method="get" name="" class="form-inline" onsubmit="return checkSearch()" accept-charset="UTF-8" >
		 	 <div class="form-group">
		   		<label class="control-label">Tiêu đề:</label>
		   		<input type="text" class="form-control" name="tieuDe" value="<%if(tieuDeSearch!=null) out.print(tieuDeSearch); %>"
		   		maxlength="100" title="Tiêu đề phải nhỏ hơn hoặc bằng 100 kí tự" />
		 	 </div>
		 	 <div class="form-group">
		   		<label class="control-label">Danh mục:</label>
		   		<select class="form-control" name="danhMuc">
		   			<option value="">Tất cả</option>
		   			<%
		   				for(DanhMuc objDanhMuc: listDanhMuc){
		   			%>
					<option value="<%=objDanhMuc.getIdDanhMuc()%>" <% if(objDanhMuc.getIdDanhMuc().equals(danhMucSearch)) out.print("selected"); %>><%=objDanhMuc.getTenDanhMuc()%></option>
					<%} %>
				</select>
		 	 </div>
		 	 <div class="form-group">
		   		<label class="control-label">Thời gian đăng: </label>
		   		<input type="date" class="form-control" name="thoiGianDang" value="<% if(thoiGianDangSearch!=null) out.print(thoiGianDangSearch);%>"/>
		 	 </div>
		 	 <div class="form-group">
		   		<input type="submit" class="form-control btn btn-success" id="" value="Tìm kiếm" name="search" style="width: 80px" />
		 	 </div>
		</form>
		<br />
		<div class="loi" id="msg">
			<span><%if(msg!=null) out.print(msg); %></span>
		</div>
	</div>
	<div>
	<form action="add-news" method="post" class="form-inline">
		<div class="form-group left" style="padding-right: 10px">
			<button type="submit" class="btn btn-info" name="showadd" >
				<img src="<%=request.getContextPath() %>/templates/admin/images/add.png" alt="" width="20" height="20"> Thêm
			</button>
		</div>
	</form>
		<form action="delete-news" method="post" onsubmit="return checkDelete()" class="form-inline">
			<div>
				<div class="form-group left">
					<button type="submit" class="btn btn-danger" name="delete" >
						<img src="<%=request.getContextPath() %>/templates/admin/images/del-icon.png" alt="" width="20" height="20"> Xóa
					</button>
				</div>
				<div class="clear"></div>
			</div>
			<table class="table table-bordered table-hover">
				<tr>
					<th>STT</th>
					<th>Chọn</th>
					<th>Hiển thị</th>
					<th>ID</th>
					<th>Tiêu đề</th>
					<th>Người đăng</th>
					<th>Danh mục</th>
					<th>Thời gian đăng</th>
					<th>Luợt xem</th>
					<th>Hình ảnh</th>
					<th>Sửa</th>
				</tr>
				<%
					if(listTinTuc!=null){
						for(TinTuc objTinTuc:listTinTuc){
				%>
				<tr>
				<td><%=stt++ %></td>
				<td>
					<input type="checkbox" name="check" value="<%=objTinTuc.getIdTinTuc() %>">
				</td>
				<td align="center" id="setactive-<%=objTinTuc.getIdTinTuc()%>">
					<a href="javascript:void(0)" onclick="return setActive('<%=objTinTuc.getIdTinTuc()%>')" title="">
						<%if (objTinTuc.getDuyet() == 1){%>
						<img alt="" src="<%=request.getContextPath()%>/templates/admin/images/tick-circle.gif">
						<%} else {%>
						<img alt="" src="<%=request.getContextPath()%>/templates/admin/images/minus-circle.gif">
						<%}%>
					</a>
				</td>
				<td><%=objTinTuc.getIdTinTuc() %></td>
				<td><%=objTinTuc.getTieuDe() %></td>
				<td><%=objTinTuc.getNguoiDang() %></td>
				<td><%=objTinTuc.getDanhMuc() %></td>
				<td><%=objTinTuc.getThoiGianDang() %></td>
				<td><%=objTinTuc.getLuotXem() %></td>
				<td><img src="<%=request.getContextPath() %>/files/<%=objTinTuc.getHinhAnh() %> " width="150px" height="120px"></td>
				<td>
					<a href="edit-news?showedit=1&id=<%=objTinTuc.getIdTinTuc()%>">Sửa</a>
				</td>
				</tr>
				<%	
						}
					}
				%>
			</table>
				<!-- Phân trang. -->
				<div class="right ">
					<nav>
					<ul class="pagination">
					<%	
						String urlPage=request.getContextPath()+"/admin/index-news?page=";
						if(request.getParameter("search")!=null){
							urlPage=request.getContextPath()+"/admin/search-news?tieuDe="+tieuDeSearch+"&danhMuc="+danhMucSearch+"&thoiGianDang="+thoiGianDangSearch+"&search=1&page=";
						}
						String active="";
						int sotrang=(Integer)(request.getAttribute("sotrang"));
						int current_page=(Integer)(request.getAttribute("current_page"));
						
						for(int i=1;i<=sotrang;i++){
							if(current_page==i){
								active=" class= 'btn disabled'";
							}else{
								active="";
							}
							if(i==1){
					%>
							<li>
								<a href="<%=urlPage+i %>" <%=active %>>Đầu</a>
							</li>
							<li>
								<a href="<%=urlPage+(current_page-1)%>" <%=active %>>Trước</a>
							</li>
							<%} %>
							<li>		
								<%
									if(i==current_page){
								%>
										<a href="<%=urlPage+i%>" <%=active %>><%=i %></a>
								<%
									}
								%>	
							</li>
							<%if(i==sotrang) {%>
							<li>
								<a href="<%=urlPage+(current_page+1)%>" <%=active %>>Sau</a>
							</li>
							<li>
								<a href="<%=urlPage+i%>" <%=active %>>Cuối</a>
							</li>
					<%	
							}
						} 
					%>
					</ul>
					</nav>
				</div>
		</form>
	</div>
	<script type="text/javascript">
	//kiem tra xoa giao dich
	function setActive(id){
		//alert(0);
		$.ajax({
			url : '<%=request.getContextPath()%>/admin/active-news',
			type : 'POST',
			cache : false,
			data : {
				idTinTuc : id,
			},
			success : function(data) {
				$('#setactive-' + id).html(data);
			},
			error : function() {
				alert('Có lỗi xảy ra');
			}
		});
		return false;
	}
	function checkDelete() {
		var check = document.getElementsByName("check");
		for (var i = 0; i < check.length; i++) {
			if(check[i].checked){
				return true;
			}
		}
		document.getElementById("msg").innerHTML="Chưa chọn mục để xóa";
		return false;
	}
	</script>
<%@ include file="/templates/admin/inc/footer.jsp"%>