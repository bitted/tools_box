<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>真实用户列表</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.0.min.js"></script>
</head>
<body>
<!--	<a target="_blank" href="${centerurl}/demo/index.htm"><<<返回首页</a><br/>-->
<!--	<br/>-->
<h1>在线用户数：<b>[${conncount}]</b> 按最后登陆时间降序。</h1>

<table id="contentTable2"
	class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<td><b>ID</b></td>
			<td><b>名称</b></td>
			<td><b>邮箱</b></td>
			<td><b>性别</b></td>
			<td><b>头像</b></td>
	<!--		<td><b>连线数</b></td>
			<td><b>掉线数</b></td>
			<td><b>登录数</b></td>
			<td><b>被看数</b></td>
			<td><b>被赞数</b></td>-->
			<td><b>离线总数</b></td>
			<td><b>是否在线</b></td>
			<td><b>图片管理</b></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.uid}</td>
				<td>${item.name}</td>
				<td>${item.email}</td>
				<td><c:if test="${item.gender==1}">男</c:if> <c:if
					test="${item.gender==0}">女</c:if></td>
				<td><c:if test="${not empty item.leave}">
					<img width="80" src="${imgcdn}/${item.leave}" />
				</c:if> <c:if test="${empty item.leave}">
						无
					</c:if></td>
<!--				<td>${item.connTime}</td>
				<td>${item.unconnTime}</td>
			<td>${item.loginTime}</td>
				<td>${item.newQueryTime}</td>
				<td>${item.likeTime}</td>-->
				<td>${item.offlinemsgcount}</td>

				<td><c:if test="${item.isconn==1}">
					<font color="green">在线</font>
				</c:if> <c:if test="${item.isconn==0}">
					<font color="red">离线</font>
				</c:if></td>
				<td><c:if test="${not empty item.leave}">
					<a target="_blank"
						href="${centerurl}/temp/picmg/listuserpic.htm?cu=${item.uid}">图片管理</a>
				</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>