	<div class="index-admin" style="text-align: center; font-size: 350%">
		<%
			if(userObj.getCapDo()==0|| userObj.getCapDo()==1){
		%>
		<div class="left col-md-3">
			<a href="add-news?showadd=1">
			<img src="<%=request.getContextPath()%>/templates/admin/images/location-news-icon.png"
				alt="" width="60%" height="60%">
			Thêm tin tức</a>
		</div>
		<%
			}
			if(userObj.getCapDo()==0|| userObj.getCapDo()==2){
		%>
		<div class="left col-md-3">
			<a href="add-user?showadd=1" >
			<img src="<%=request.getContextPath()%>/templates/admin/images/user.png"
				alt="" width="60%" height="60%">
			Thêm thành viên</a>
		</div>
		<%
			}
			if(userObj.getCapDo()==0|| userObj.getCapDo()==3){
		%>
		<div class="left col-md-3">
			<a href="add-cat?showadd=1" >
			<img src="<%=request.getContextPath()%>/templates/admin/images/icon-head-t.png"
				alt="" width="60%" height="60%">
			Thêm danh mục</a>
		</div>
		<%
			}
		%>
		<div class="left col-md-3">
			<a href="info-account" >
			<img src="<%=request.getContextPath()%>/templates/admin/images/profile-icon-63199.png" alt="" width="60%" height="60%">
			Thông tin tài khoản</a>
		</div>
	</div>
<%@ include file="/templates/admin/inc/footer.jsp"%>