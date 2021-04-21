<%@page import="cn.antchensw.netnote.bean.Admin"%>
<%@page import="cn.antchensw.netnote.bean.User"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理系统</title>
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
	<%
	    Admin admin = (Admin) session.getAttribute("admin_session");
	   List<User> users_session=(List<User>)session.getAttribute("users_session");
	%>

	<nav class="center_content">
		<div align="center">
			<h1>用户管理</h1>
			<h5><%=admin.getName()%>
				<a href="${pageContext.request.contextPath }/admin.logout"> 注销 </a>
				<p>&ensp;&ensp;<span style="color: red">${msg_login}</span></p>
				<%session.removeAttribute("msg_login") ;%>
			</h5>
		</div>
	</nav>

	<!-- 用户显示 -->
	<section>
		<div align="center" >
			<%
			    if (users_session != null) {
			%>
			<table border="1"  style="text-align: center;">
				<thead>
					<tr>
						<td colspan="6"><h3>所有用户</h3></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>账号</td>
						<td>用户名</td>
						<td>邮箱</td>
						<td>等级</td>
						<td colspan="2">操作</td>
					</tr>
					<%
					    for (User user : users_session) {
					%>
					<tr>
						<td><%=user.getId()%></td>
						<td><%=user.getName()%></td>
						<td><%=user.getEmail()%></td>
						<td><%=user.getLevel()%></td>
						
						<td>
							<form action="${pageContext.request.contextPath }/admin.deluser?user_id=<%=user.getId()%>" method="post">
								<input type="submit" value="删除">
							</form>
						</td>
					</tr>
					<%
					    }
					%>
				</tbody>
			</table>
			<%
			    } else {
			%>
			<h3>暂无用户!</h3>
			<%
			    }
			%>
		</div>
	</section>
	
	
	       <%session.removeAttribute("info_login"); %>
            <%session.removeAttribute("users_session"); %>

</body>
</html>