<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>超市账单管理系统-账单管理</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>
<body>

	<div class="menu">

		<table>
			<tbody>
				<tr>
					<td><form method="post" action="bill.do">
							<input name="method" value="query" class="input-text" type="hidden"> 
							商品名称：<input name="productName" class="input-text" type="text" value="">
							供应商名称：<select name="proName" item="select">
							<option>---请选择---</option>
							<c:forEach items="${provider} var=pro">
							 <option value="${bill.proName}">${bill.proName}</option>
							</c:forEach>
									</select>
							
							是否付款：<select>
										<option>---请选择---</option>
										<option>是</option>
										<option>否</option>
								</select>
							<input value="查 询" type="submit">
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="main">

		<div class="optitle clearfix">
			<em><input value="添加账单" class="input-button" onClick="window.location='billAdd.html'" type="button">
			</em>
			<div class="title">账单管理&gt;&gt;</div>
		</div>
		<div class="content">
			<table class="list">
				<tbody>
					<tr>
						<td width="70" height="29"><div class="STYLE1" align="center">编号</div>
						</td>
						<td width="80"><div class="STYLE1" align="center">商品名称</div>
						</td>
						<td width="80"><div class="STYLE1" align="center">供应商</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">账单金额</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">是否付款</div>
						</td>
						<td width="100"><div class="STYLE1" align="center">操作时间</div>
						</td>
					</tr>
					
					<tr>
						<td height="23"><span class="STYLE1">6</span>
						</td>
						<td><span class="STYLE1"><a href="billView.html" style="color:red" > 原阳大米</a>
						</span>
						</td>
						<td><span class="STYLE1"> 北京市粮油总公司</span>
						</td>
						<td><span class="STYLE1">5000.0</span>
						</td>
						<td><span class="STYLE1">
							
							 否
						</span>
						</td>
						<td><span class="STYLE1">2014-10-10</span>
						</td>
					</tr>
					
					<tr>
						<td height="23"><span class="STYLE1">8</span>
						</td>
						<td><span class="STYLE1"><a href="bill.do?method=getInfo&amp;billId=8"> 燕京啤酒-普通</a>
						</span>
						</td>
						<td><span class="STYLE1"> 顺义燕京啤酒批发3部</span>
						</td>
						<td><span class="STYLE1">6600.0</span>
						</td>
						<td><span class="STYLE1">
							
							 否
						</span>
						</td>
						<td><span class="STYLE1">2014-10-10</span>
						</td>
					</tr>
					
					<tr>
						<td height="23"><span class="STYLE1">5</span>
						</td>
						<td><span class="STYLE1"><a href="bill.do?method=getInfo&amp;billId=5"> 散装面粉</a>
						</span>
						</td>
						<td><span class="STYLE1"> 邯郸市五得利面粉厂</span>
						</td>
						<td><span class="STYLE1">750.0</span>
						</td>
						<td><span class="STYLE1">
							
							 否
						</span>
						</td>
						<td><span class="STYLE1">2014-10-10</span>
						</td>
					</tr>
					
					
				</tbody>
			</table>
		</div>
	</div>
							<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>	
							<script type="text/javascript" src="${pageContext.request.contextPath}/js/billlist.js"></script>
							<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

</body>
</html>