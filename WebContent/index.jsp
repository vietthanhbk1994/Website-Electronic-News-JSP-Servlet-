<%@page import="bean.TinTuc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/templates/public/inc/header.jsp"%>
	<%
		ArrayList<ArrayList<TinTuc>> listTinTuc = (ArrayList<ArrayList<TinTuc>>) request.getAttribute("listTinTuc"); 
	%>
	<div id="body">
		<div class="section">
			<h2>Báo điện tử</h2>
			<p class="ptop">
				Cập nhật các tin tức mới nhất, nổi bật nhất.
			</p>
			<%
				String urlTin = request.getContextPath()+"/chi-tiet/";
				for(int i=0; i<listTinTuc.size(); i++){
			%>
			<!-- begin block -->
			<div class="project-wrap">
				<h3 class="title">
				<a href="<%= urlCat + listDanhMuc.get(i).getIdDanhMuc()%>"><%= listDanhMuc.get(i).getTenDanhMuc()%></a>
				</h3>
				<div class="loi">
				<%
					if(listTinTuc.get(i).size()==0){
						out.print("<span>Không tồn tại dữ liệu</span>");
					}else{
				%>
				</div>
				<div class="project-top">
					<a href="<%=urlTin + listTinTuc.get(i).get(0).getIdTinTuc()%>"><img src="<%=request.getContextPath()%>/files/<%=listTinTuc.get(i).get(0).getHinhAnh() %>" alt=""></a>
					<div>
						<b><a href="<%=urlTin+boLib.createSlug(listTinTuc.get(i).get(0).getTieuDe())+"_" + listTinTuc.get(i).get(0).getIdTinTuc()+".html"%>"><%=listTinTuc.get(i).get(0).getTieuDe() %></a></b> 
						<small>Ngày đăng: <%=listTinTuc.get(i).get(0).getThoiGianDang() %></small>
						<p class="preview_text">
							<%=listTinTuc.get(i).get(0).getMoTa() %>						
						</p>
					</div>
				</div>
				<ul class="article">
					<%
						for(int j=1; j<listTinTuc.get(i).size(); j++){
					%>
					<li>
						<a href="<%=urlTin+boLib.createSlug(listTinTuc.get(i).get(j).getTieuDe())+"_" + listTinTuc.get(i).get(0).getIdTinTuc()+".html"%>"><img src="<%=request.getContextPath()%>/files/<%=listTinTuc.get(i).get(j).getHinhAnh() %>" alt=""></a>
						<b><a href="<%=urlTin+boLib.createSlug(listTinTuc.get(i).get(j).getTieuDe())+"_" + listTinTuc.get(i).get(0).getIdTinTuc()+".html"%>"><%=listTinTuc.get(i).get(j).getTieuDe() %></a></b> 
						<small>Ngày đăng: <%=listTinTuc.get(i).get(j).getThoiGianDang() %></small>
						<p>
							<%=listTinTuc.get(i).get(j).getMoTa() %>	
						</p>
						
					</li>
					<%} %>
				</ul>
				<div class="clr"></div>
				<%} %>
			</div> <!-- end block -->
			<%} %>
			<div id="goTop">
				<img src="<%=request.getContextPath() %>/templates/public/images/back.png" alt="Back to top" width="45px" height="45x"/>
			</div>
		</div>
	</div>
	<%@ include file="/templates/public/inc/footer.jsp"%>