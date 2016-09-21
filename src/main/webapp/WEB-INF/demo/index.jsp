<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理首页</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.0.min.js"></script>
</head>
<body>
<h1>管理首页</h1>

<table id="contentTable"
	class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<td><b>功能</b></td>
			<td><b>链接</b></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>假用户列表</td>
			<td><a target="_blank" href="${centerurl}/demo/list.htm">${centerurl}/demo/list.htm</a></td>
		</tr>
	</tbody>
</table>
</body>
</html>