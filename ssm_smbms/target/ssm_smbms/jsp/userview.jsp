<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
	<div class="main">
	<input type="hidden" id="path" value="${pageContext.request.contextPath}">
		<div class="optitle clearfix">
			<div class="title">用户管理&gt;&gt;</div>

		</div>
			<div class="content">
				<table class="box">
					<tbody>
						<tr>
							<td class="field">用户编码：</td>
							<td>${user.userCode}</td>
						</tr>
						<tr>
							<td class="field">用户名称：</td>
							<td>${user.userName}</td>
						</tr>
						
						<tr>
							<td class="field">用户性别：</td>
							<td>
							<c:if test="${user.gender==1}">男</c:if>
							<c:if test="${user.gender==2}">女</c:if>
							</td>
						</tr>
						<tr>
							<td class="field">出生日期：</td>
							<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="field">用户电话：</td>
							<td>${user.phone}</td>

						</tr>
						<tr>
							<td class="field">用户地址：</td>
							<td>${user.address}</td>
						</tr>
						<tr>
							<td class="field">用户类别：</td>

							<td>
							<c:if test="${user.userType==1 }">系统管理员</c:if>
							<c:if test="${user.userType==2 }">经理</c:if>
							<c:if test="${user.userType==3 }">普通员工</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="buttons">
				<input type="button" name="button"
					id="button" onClick="history.back(-1)" value="返回"
					class="input-button">
			</div>

		
	</div>


</body>
</html>