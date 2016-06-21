<%@page import="bean.DanhMuc"%>
<%@page import="bean.TinTuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
	<%
		ArrayList<DanhMuc> listDanhMuc = (ArrayList<DanhMuc>) request.getAttribute("listDanhMuc");
		int stt=1;
		String loi = (String)request.getAttribute("loi");
		String tenDanhMucSearch="";
		if(request.getParameter("search")!=null){
			tenDanhMucSearch = new String(request.getParameter("tenDanhMuc").getBytes("ISO-8859-1"),"UTF-8");
		}
		
	%>
	<div>
		<form action="search-cat" method="get" name="" class="form-inline" onsubmit="return checkSearch()" accept-charset="UTF-8" >
		 	 <div class="form-group">
		   		<label class="control-label">Tên danh mục:</label>
		   		<input type="text" class="form-control" name="tenDanhMuc" value="<%=tenDanhMucSearch %>"
		   		 maxlength="50" title="Tên danh mục là chữ cái nhỏ hơn 50 ký tự" />
		 	 </div>
		 	 <div class="form-group">
		   		<input type="submit" class="form-control btn btn-success" id="" value="Tìm kiếm" name="search" style="width: 80px" />
		 	 </div>
		</form>
		<br />
		<div class="loi" >
			<span id="msg"><%if(loi!=null) out.print(loi); %></span>
		</div>
	</div>
	<div>
	<form action="add-cat" method="post" class="form-inline">
		<div class="form-group left" style="padding-right: 10px">
			<button type="submit" class="btn btn-info" name="showadd" >
				<img src="<%=request.getContextPath() %>/templates/admin/images/add.png" alt="" width="20" height="20"> Thêm
			</button>
		</div>
	</form>
		<form action="delete-cat" method="post" onsubmit="return checkDelete()" class="form-inline">
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
					<th>ID</th>
					<th>Tên danh mục</th>
					<th>Lượt xem</th>
					<th>Mô tả</th>
					<th>Sửa</th>
				</tr>
				<%
					if(listDanhMuc!=null){
						for(DanhMuc objDanhMuc:listDanhMuc){
				%>
				<tr>
				<td><%=stt++ %></td>
				<td>
					<input type="checkbox" name="check" value="<%=objDanhMuc.getIdDanhMuc() %>">
				</td>
				<td><%=objDanhMuc.getIdDanhMuc() %></td>
				<td><%=objDanhMuc.getTenDanhMuc() %></td>
				<td><%=objDanhMuc.getLuotXem() %></td>
				<td><%=objDanhMuc.getMoTa() %></td>
				<td>
					<a href="edit-cat?showedit=1&id=<%=objDanhMuc.getIdDanhMuc()%>">Sửa</a>
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
						String urlPage=request.getContextPath()+"/admin/index-cat?page=";
						if(request.getParameter("search")!=null){
							urlPage=request.getContextPath()+"/admin/search-cat?tenDanhMuc="+tenDanhMucSearch+"&search=1&page=";
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
	<!-- Kiem tra du lieu tim kiem -->
	<script type="text/javascript">
	//kiem tra xoa
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
	<!-- Kết thúc phần gì gì đó -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
