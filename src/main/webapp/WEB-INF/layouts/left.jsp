<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="span2">
	<h1>用户管理</h1>
	<div class="submenu">
		<a id="user-tab" href="${centerurl}/demo/list.htm">假用户列表</a>
		<a id="user-tab1" href="${centerurl}/demo/ruserlist.htm?limit=50">真实用户列表</a>
	</div>
</div>