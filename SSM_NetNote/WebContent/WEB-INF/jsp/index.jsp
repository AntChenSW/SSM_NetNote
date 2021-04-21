
<%@page import="java.net.URLEncoder"%>
<%@page import="cn.antchensw.netnote.util.Tools"%>
<%@page import="cn.antchensw.netnote.bean.Note"%>
<%@page import="java.util.List"%>
<%@page import="cn.antchensw.netnote.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>我的记事</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
	<%
	    User user = (User) session.getAttribute("user_session");
    List<Note> notes= (List<Note>)session.getAttribute("notes");
	%>

	<nav class="center_content">
		<div align="center">
			<h1>我的笔记</h1>
			<h5> <%if(user.getPhoto()==1){ %>
                    <a href="${pageContext.request.contextPath }/user.downloadphoto?photoname=<%=URLEncoder.encode(user.getId()+".png", "UTF-8")%>">
                        <img src="img/<%=user.getId() %>.png" width="50px" height="50px"/>
                        </a>
                    <%}%>
				欢迎，<%=user.getName()%>
				<br> <a href="userinfo"> 我的信息 </a><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><a
					href="${pageContext.request.contextPath }/user.logout"> 注销 </a>
			</h5>
			<h4>
				<a href="note">新建笔记</a>
			</h4>
			<section>
				<div align="center">
					<p>
						&ensp;&ensp;<span style="color: red">${msg_login}</span>
					</p>
					<%
					    session.removeAttribute("msg_login");
					%>
				</div>
			</section>
		</div>
	</nav>

	<!-- 用户显示 -->
	<section>
		<div align="center">

			<table border="1" style="text-align: center;">
				<thead>
					<tr>
						<td colspan="3" ><h3>所有笔记</h3></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="100px" height="25px">标题</td>
						<td width="120px" height="25px">创建时间</td>
						<td width="80px" height="25px">重要程度</td>
					</tr>
					<%
					    if (notes .size()>0) {
					    for (Note note : notes) {
					%>
					<tr>
					   
					   
						<td><%=note.getTitle()%></td>
						<td><%=note.getDate()%></td>

						<td><span
						  <%if(note.getLevel()==1){ %>
						   style="color: #D2691E"
						  <%}else if(note.getLevel()==2){ %>
						       style="color: #FF4500"
						  <%}else if(note.getLevel()==3){ %>
                          style="color: #B0C4DE"
                            <%} %>
						  >
						  <%=Tools.toLevel(note.getLevel())%>
						  </span>
						  
						</td>
					</tr>

					<tr>
						<td colspan="2" height="auto"><%=note.getContent()%></td>
						<td>
							<form action="${pageContext.request.contextPath}/toedit?note_id=<%=note.getId()%>" method="post">
								<input type="submit" value="编辑">
							</form>
							<form action="${pageContext.request.contextPath}/note.delete?note_id=<%=note.getId()%>" method="post">
								<input type="submit" value="删除">
							</form>
						</td>
					</tr>

					<%
					    }
					} else {
					%>
					<tr>
						<td colspan="3"><h4>暂无笔记</h4></td>
					</tr>

					<%
					    }
					%>
				</tbody>
			</table>
		</div>
	</section>



</body>
</html>