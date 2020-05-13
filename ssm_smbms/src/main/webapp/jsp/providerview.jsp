<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
			<div class="title">供应商管理&gt;&gt;</div>

		</div>
			<div class="content">
				<table class="box">
					<tbody>
						<tr>
							<td class="field">供应商名称：</td>
							<td>${provider.proCode}</td>
						</tr>
						<tr>
							<td class="field">联系人：</td>
							<td>${provider.proName}</td>
						</tr>
						
						<tr>
							<td class="field">联系电话：</td>
							<td> ${provider.proPhone}  </td>
						</tr>
						<tr>
							<td class="field">联系地址：</td>
							<td>${provider.proAddress}</td>
						</tr>
						<tr>
							<td class="field">传真：</td>
							<td>${provider.proFax}</td>

						</tr>
						<tr>
							<td class="field">描述：</td>
							<td>${provider.proDesc}</td>
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