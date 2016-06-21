<%@page import="bean.Quyen"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
	<%
		ArrayList<Quyen> listQuyen = (ArrayList<Quyen>) request.getAttribute("listQuyen");
		ArrayList<User> listUser = (ArrayList<User>) request.getAttribute("listUser");
		int stt=1;
		String loi = (String)request.getAttribute("loi");
		String hoVaTenSearch="";
		String idQuyenSearch="";
		if(request.getParameter("search")!=null){
			hoVaTenSearch = new String(request.getParameter("hoVaTen").getBytes("ISO-8859-1"),"UTF-8");
			idQuyenSearch = request.getParameter("idQuyen");
		}
	%>
	<div>
		<form action="search-user" method="get" name="" class="form-inline" onsubmit="return checkSearch()" accept-charset="UTF-8" >
		 	 <div class="form-group">
		   		<label class="control-label">Họ và tên:</label>
		   		<input type="text" class="form-control" name="hoVaTen" maxlength="50"
		   		value="<% if(!"".equals(hoVaTenSearch)) out.print(hoVaTenSearch);%>"/>
		 	 </div>
		 	 <div class="form-group">
		   		<label class="control-label">Quyền:</label>
		   		<select class="form-control" name="idQuyen">
		   			<option value="">Tất cả</option>
		   			<%
		   				for(Quyen objQuyen: listQuyen){
		   			%>
					<option value="<%=objQuyen.getIdQuyen()%>" <%if(objQuyen.getIdQuyen().equals(idQuyenSearch)) out.print("selected"); %>><%=objQuyen.getTenQuyen()%></option>
					<%} %>
				</select>
		 	 </div>
		 	 <div class="form-group">
		   		<input type="submit" class="form-control btn btn-success" id="" value="Tìm kiếm" name="search" style="width: 80px" />
		 	 </div>
		</form>
		<br />
		<div class="loi" id="msg">
			<span><%if(loi!=null) out.print(loi); %></span>
		</div>
	</div>
	<div>
	<form action="add-user" method="post" class="form-inline">
		<div class="form-group left" style="padding-right: 10px">
			<button type="submit" class="btn btn-info" name="showadd" >
				<img src="<%=request.getContextPath() %>/templates/admin/images/add.png" alt="" width="20" height="20"> Thêm
			</button>
		</div>
	</form>
		<form action="index-user" method="post" class="form-inline" onsubmit="return checkDelete()">
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
					<th>Tài khoản</th>
					<th>Họ và tên</th>
					<th>Ngày sinh</th>
					<th>Quyền</th>
					<th>SĐT</th>
					<th>Email</th>
					<th>Địa chỉ</th>
					<th>Số bài đăng</th>
					<th>Điểm cống hiến</th>
				</tr>
				<%
					if(listUser!=null){
						for(User objUser:listUser){
				%>
				<tr>
				<td><%=stt++ %></td>
				<td>
					<input type="checkbox" name="check" value="<%=objUser.getIdUser() %>">
				</td>
				<td><%=objUser.getIdUser() %></td>
				<td><%=objUser.getTaiKhoan() %></td>
				<td><%=objUser.getHoVaTen() %></td>
				<td><%=objUser.getNgaySinh() %></td>
				<td><%=objUser.getTenQuyen() %></td>
				<td><%=objUser.getSoDienThoai() %></td>
				<td><%=objUser.getEmail() %></td>
				<td><%=objUser.getDiaChi() %></td>
				<td><%=objUser.getSoBaiDang() %></td>
				<td><%=objUser.getDiemCongHien() %></td>
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
						String urlPage=request.getContextPath()+"/admin/index-user?page=";
						if(request.getParameter("search")!=null){
							urlPage=request.getContextPath()+"/admin/search-user?hoVaTen="+hoVaTenSearch+"&idQuyen="+idQuyenSearch+"&search=1&page=";
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
	function checkSearch() {
	}
	</script>
	<!-- Kết thúc phần gì gì đó -->
<%@ include file="/templates/admin/inc/footer.jsp"%>
