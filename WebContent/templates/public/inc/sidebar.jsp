 <%@page import="bo.LibraryBO"%>
<%@page import="bean.TinTuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		ArrayList<TinTuc> listTinTucNoiBat = (ArrayList<TinTuc>) request.getAttribute("listTinTucNoiBat");
		ArrayList<TinTuc> listTinTucXemNhieu = (ArrayList<TinTuc>) request.getAttribute("listTinTucXemNhieu");
	%>
<div id="sidebar">
	<div class="testimonials awards">
		<h3>Tin tức xem nhiều nhất</h3>
		<ul>
			<%
				LibraryBO boL2 = new LibraryBO();
				String urlDetail2 = request.getContextPath()+"/chi-tiet/";
				for(TinTuc objTinTucNoiBat : listTinTucNoiBat){
			%>
			<li>
				<a href="<%=urlDetail2+boL2.createSlug(objTinTucNoiBat.getTieuDe())+"_"+objTinTucNoiBat.getIdTinTuc()+".html"%>">
					<img src="<%=request.getContextPath()%>/files/<%=objTinTucNoiBat.getHinhAnh() %>" alt="">
				</a>
				<b><a href="<%=urlDetail2+boL2.createSlug(objTinTucNoiBat.getTieuDe())+"_"+objTinTucNoiBat.getIdTinTuc()+".html"%>"><%=objTinTucNoiBat.getMoTa() %></a></b>
				<small>Ngày đăng: <%=objTinTucNoiBat.getThoiGianDang()%></small>
				<small>Lượt xem: <%=objTinTucNoiBat.getLuotXem()%></small>
			</li>
			<%} %>
		</ul>
	</div>
	<div class="testimonials awards">
		<h3>Tin tức nổi bật</h3>
		<ul>
			<%
				for(TinTuc objTinTucXemNhieu : listTinTucXemNhieu){
			%>
			<li>
				<a href="<%=urlDetail2+boL2.createSlug(objTinTucXemNhieu.getTieuDe())+"_"+objTinTucXemNhieu.getIdTinTuc()+".html"%>">
					<img src="<%=request.getContextPath()%>/files/<%=objTinTucXemNhieu.getHinhAnh() %>" alt="">
				</a>
				<b><a href="<%=urlDetail2+boL2.createSlug(objTinTucXemNhieu.getTieuDe())+"_"+objTinTucXemNhieu.getIdTinTuc()+".html"%>"><%=objTinTucXemNhieu.getMoTa() %></a></b>
				<small>Ngày đăng: <%=objTinTucXemNhieu.getThoiGianDang()%></small>
				<small>Lượt xem: <%=objTinTucXemNhieu.getLuotXem()%></small>
			</li>
			<%} %>
		</ul>
	</div>
</div>