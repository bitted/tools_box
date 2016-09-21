<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>假用户列表</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.0.min.js"></script>
<script type="text/javascript">
	var centerurl = '${centerurl}';

	$(window).load(
			function() {
				$('.ltbutton').live(
						'click',
						function() {
							var myuid = $(this).attr("uid");
							var tguid = $("#input_" + myuid + "").val();

							//alert(myuid);
							//alert(tguid);

							if (tguid == '') {
								alert("请输入对方uid");
								return;
							}

							if (myuid.length != tguid.length) {
								alert("输入的uid不合法");
								return;
							}

							location.href = centerurl
									+ "/demo/chathislist.htm?uid=" + myuid
									+ "&formUid=" + tguid;

						});
			});
</script>
</head>
<h2>假用户管理</h2>

<body>
<span>按最后聊天时间降序</span>
<table id="contentTable2"
	class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<td><b>ID</b></td>
			<td><b>名称</b></td>
			<td><b>性别</b></td>
			<td><b>头像</b></td>
			<td><b>收条数</b></td>
			<td><b>发条数</b></td>
			<td><b>是否在线</b></td>
			<td><b>最后聊天时间</b></td>
			<td><b>有无未读消息</b></td>
			<td><b>操作</b></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="item">
			<tr>
				<td>${item.uid}</td>
				<td>${item.name}</td>
				<td> <c:if test="${item.gender==1}">男</c:if> <c:if
					test="${item.gender==0}">女</c:if> </td>
				<td><img width="60" src="${imgcdn}/${item.leave}" /></td>
				<td>${item.demo_resTime}</td>
				<td>${item.demo_sendTime}</td>
				<td> <c:if test="${item.isconn==1}">
					<font color="green">在线</font>
				</c:if> <c:if test="${item.isconn==0}">
					<font color="red">离线</font>
				</c:if> </td>
				<td>${item.demo_chatDateStr}</td>
				<td> <c:if test="${item.demo_restype==0}">有</c:if>
				<c:if test="${item.demo_restype==1}">无</c:if> </td>
				<td ><c:if test="${item.isconn==1}">
					<a style="color: red"
						href="${centerurl}/demo/modifyconn.htm?uid=${item.uid}&isconn=0"
						target="_blank">我要离线</a>
				</c:if> <c:if test="${item.isconn==0}">
					<a href="${centerurl}/demo/modifyconn.htm?uid=${item.uid}&isconn=1"
						target="_blank">我要上线</a>
				</c:if> &nbsp; <a href="${centerurl}/demo/chathislist.htm?uid=${item.uid}"
					target="_blank">看聊天历史</a> &nbsp; 和<input type="text"
					id="input_${item.uid}" />聊天输入uid，<input class="ltbutton"
					uid="${item.uid}" type="button" value="开聊" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>