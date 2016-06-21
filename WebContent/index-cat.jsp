<%@page import="bean.TinTuc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/templates/public/inc/header.jsp"%>
	<%
		ArrayList<TinTuc> listTinTuc = (ArrayList<TinTuc>) request.getAttribute("listTinTuc");
		String loi = (String) request.getAttribute("loi");
		String idMuc = (String) request.getAttribute("idDanhMuc");
	%>
	<div id="body">
		<div class="content">
			<div class="loi">
				<span><%if(loi!=null) out.print(loi); %></span>
			</div>
			<div id="blog">
				<h2>
					<% 
						for(DanhMuc objDanhMuc : listDanhMuc){
							if(objDanhMuc.getIdDanhMuc().equals(idMuc)){
								out.print(objDanhMuc.getTenDanhMuc());
								break;
							}
						}
					%>
				</h2>
				<ul>
					<%
						String urlTin = request.getContextPath()+"/chi-tiet/";
						for(TinTuc objTinTuc: listTinTuc){
					%>
					<li>
						<div class="article">
							<h3><a href="<%=urlTin+boLib.createSlug(objTinTuc.getTieuDe())+"_"+objTinTuc.getIdTinTuc()+".html"%>" ><%=objTinTuc.getTieuDe() %></a></h3>
							<small>Ngày đăng: <%=objTinTuc.getThoiGianDang() %></small>
							<p>
								<%=objTinTuc.getMoTa() %>								
							</p>
						</div>
						<div class="stats">
							<a href="<%=urlTin+boLib.createSlug(objTinTuc.getTieuDe())+"_"+objTinTuc.getIdTinTuc()+".html"%>" class="more" target="_blank"><img src="<%=request.getContextPath() %>/files/<%=objTinTuc.getHinhAnh() %>" alt="" /></a>
						</div>
					</li>
					<%} %>
				</ul>
				<!-- Phân trang. -->
				<div class="right ">
					<nav>
					<ul class="pagination">
					<%	
						String active="";
						int sotrang=(Integer)(request.getAttribute("sotrang"));
						int current_page=(Integer)(request.getAttribute("current_page"));
						String search="";
						String urlPage = request.getContextPath()+"/index-cat?page=";
						if(idMuc!=null){
							search="&idCat="+idMuc;
						}
						for(int i=1;i<=sotrang;i++){
							if(current_page==i){
								active=" class= 'btn disabled'";
							}else{
								active="";
							}
							if(i==1){
					%>
							<li>
								<a href="<%=urlPage+i+search %>" <%=active %>>Đầu</a>
							</li>
							<li>
								<a href="<%=urlPage+(current_page-1)+search %>" <%=active %>>Trước</a>
							</li>
							<%} %>
							<li>		
								<%
									if(i==current_page){
								%>
										<a href="<%=urlPage+i+search %>" <%=active %>><%=current_page %></a>
								<%
									}
								%>	
							</li>
							<%if(i==sotrang) {%>
							<li>
								<a href="<%=urlPage+(current_page+1)+search %>" <%=active %>>Sau</a>
							</li>
							<li>
								<a href="<%=urlPage+i+search %>" <%=active %>>Cuối</a>
							</li>
					<%	
							}
						} 
					%>
					</ul>
					</nav>
				</div>
			</div>
			<%@ include file="/templates/public/inc/sidebar.jsp"%>
			<div id="goTop">
				<img src="<%=request.getContextPath() %>/templates/public/images/back.png" alt="Back to top" width="45px" height="45x"/>
			</div>
		</div>
	</div>
	<%@ include file="/templates/public/inc/footer.jsp"%>