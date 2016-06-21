<%@page import="bean.TinTuc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="/templates/public/inc/header.jsp"%>
	<%
		TinTuc objTinTuc = (TinTuc) request.getAttribute("objTinTuc");
		ArrayList<TinTuc> listTinTuc = (ArrayList<TinTuc>) request.getAttribute("listTinTuc");
	%>
<style>
	.news_detail img{
		width:100%;
	}
</style>
	<div id="body">
	<div id="fb-root"></div>
	
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.6";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	
		<div class="content">
			<div id="blog">
				<div class="news_detail">
					<h1><%=objTinTuc.getTieuDe() %></h1>
					<p class="date">Ngày đăng: <%=objTinTuc.getThoiGianDang() %> - Lượt xem: <%=objTinTuc.getLuotXem() %></p>
					<div class="news_content">
						<%=objTinTuc.getNoiDung() %>
					</div>
					<p class="author">Nguồn: <%=objTinTuc.getNguon() %></p>
				</div>
				
				<div class="fb-comments" data-href="localhost/baodientu" data-numposts="5"></div>
				
				<h2>Tin tức liên quan</h2>
				<ul>
					<%
						LibraryBO boL = new LibraryBO();
						String urlDetail = request.getContextPath()+"/chi-tiet/";
						for(TinTuc obj: listTinTuc){
					%>
					<li>
						<div class="article">
							<h3>
							<a href="<%=urlDetail+boL.createSlug(obj.getTieuDe())+"_"+obj.getIdTinTuc()+".html"%>">
							<%=obj.getTieuDe() %></a></h3>
							<small>Ngày đăng: <%=obj.getThoiGianDang() %></small>
							<small>Lượt xem: <%=obj.getLuotXem()%></small>
							<p>
								<%=obj.getMoTa() %>								
							</p>
						</div>
						<div class="stats">
							<a href="<%=urlDetail+boL.createSlug(obj.getTieuDe())+"_"+obj.getIdTinTuc()+".html"%>"><img src="<%=request.getContextPath() %>/files/<%=obj.getHinhAnh() %>" alt="" /></a>
						</div>
					</li>
					<%} %>
				</ul>
			</div>
			<%@ include file="/templates/public/inc/sidebar.jsp"%>
			<div id="goTop">
				<img src="<%=request.getContextPath() %>/templates/public/images/back.png" alt="Back to top" width="45px" height="45x"/>
			</div>
		</div>
	</div>
	<%@ include file="/templates/public/inc/footer.jsp"%>