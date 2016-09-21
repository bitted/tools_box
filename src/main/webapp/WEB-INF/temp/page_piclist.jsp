<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未审核图片列表</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.0.min.js"></script>
</head>
<body>
<table id="contentTable4"
	class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<td><b>缩略图</b></td>
			<td><b>上传时间</b></td>
			<td><b>审核通过</b></td>
			<td><b>审核不通过</b></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pics}" var="item">
			<tr>

				<td><img width="280" src="${imgcdn}/${item.leave}" /></td>
				<td>${item.createDateFormat}</td>
				<td><a
					href="${centerurl}/temp/picmg/setok.htm?pid=${item.pid}&cu=${item.uid}&limit=${limit}">审核通过</a></td>
				<td><a
					href="${centerurl}/temp/picmg/seterr.htm?pid=${item.pid}&cu=${item.uid}&limit=${limit}">审核不通过</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>