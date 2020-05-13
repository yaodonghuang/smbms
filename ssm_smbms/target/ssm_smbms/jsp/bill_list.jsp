<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <link type="text/css" rel="stylesheet" href="css/style.css" />
  </head> 
<body>
<div class="menu">
	<form method="post" action="${pageContext.request.contextPath}/bill.do">
		商品名称：<input type="text" name="productName" class="input-text" />&nbsp;&nbsp;&nbsp;&nbsp;
		是否付款：<select name="payStatus">			
			<option value="1" selected="selected">已付款</option>
			<option value="2">未付款</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="submit" value="组合查询" class="button" />
	</form>
</div>
<div class="main">
	<div class="optitle clearfix">
		<em><input type="button" name="button" value="添加数据" class="input-button" onclick="location.href='showProviderServlet'" /></em>
		<div class="title">账单管理&gt;&gt;</div>
	</div>
	<div class="content">
		<table class="list"><tbody>
			<tr>
				<td>账单编号</td>
				<td>商品名称</td>
				<td>商品数量</td>
				<td>交易金额</td>
				<td>是否付款</td>
				<td>供应商名称</td>
				<td>商品描述</td>
				<td>账单时间</td>
			</tr>
			<c:forEach var="bill" items="${list}">
			<tr>
				<td>${bill.billId}</td>
				<td><a href="showBillInfoServlet?billId=${bill.billId}">${bill.salesName}</a></td>
				<td>${bill.amount}</td>
				<td>${bill.count}</td>
				<td><c:if test="${bill.payment==1}">已付款</c:if><c:if test="${bill.payment==2}">未付款</c:if>
				</td>
				<td>${bill.proName}</td>
				<td>${bill.salesdescription}</td>
				<td>${bill.billDate}</td>
			</tr>
			</c:forEach>
		</tbody></table>
	</div>
</div>
</body>
</html>
