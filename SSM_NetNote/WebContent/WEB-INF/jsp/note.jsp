
<%@page import="cn.antchensw.netnote.bean.Note"%>
<%@page import="cn.antchensw.netnote.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body align="center" onbeforeunload="checkLeave()">

	<%
	    User user = (User) session.getAttribute("user_session");
	   Note editNote=(Note)session.getAttribute("note_session");
	
	%>

	<nav class="center_content">
		<div align="center">
			<h1>我的笔记</h1>
			<h5>
				欢迎，<%=user.getName()%>
				<br> <a href="userinfo"> 我的信息 </a><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><a
					href="${pageContext.request.contextPath }/user.logout"> 注销 </a>
			</h5>
			<h4>
				<a href="index">所有笔记</a>
			</h4>
		</div>
	</nav>


	<section>
		<div align="center">
			<form action="<%if(editNote==null){
			    editNote=new Note();%>
			    ${pageContext.request.contextPath }/note.add
			    <%}else{ %>
			    ${pageContext.request.contextPath }/note.update?id=<%=editNote.getId() %>
			    <%}%>
			
			" method="post">
				<table>
					<thead>
						<tr>
							<td colspan="2">
								<h2>新建笔记</h2>
								<p>
									&ensp;&ensp;<span style="color: red">${msg_newnote}</span>
									<%session.removeAttribute("msg_newnote") ;%>
								</p>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>标题:</td>
							<td><input type="text" name="title" value="<%=editNote.getTitle()%>"></td>
						</tr>
						<tr>
							<td>重要程度:</td>
							<td height="30px">
							<select name="level" style="height: 24px;" >
							     <%if(editNote.getLevel()==1){ %>
									<option value="0">一般</option>
									<option value="1"  selected="selected">重要</option>
									<option value="2">非常重要</option>
									<option value="3">不重要</option>
									<%}else if(editNote.getLevel()==2){ %>
                                    <option value="0">一般</option>
                                    <option value="1">重要</option>
                                    <option value="2"  selected="selected">非常重要</option>
                                    <option value="3">不重要</option>
                                    <%}else if(editNote.getLevel()==3){ %>
                                    <option value="0">一般</option>
                                    <option value="1">重要</option>
                                    <option value="2">非常重要</option>
                                    <option value="3" selected="selected">不重要</option>
                                    <%}else { %>
                                    <option value="0" selected="selected">一般</option>
                                    <option value="1">重要</option>
                                    <option value="2">非常重要</option>
                                    <option value="3">不重要</option>
                                    <%}%>
							</select></td>
							

						</tr>
						<tr>
							<td>内容:</td>
							<td><textarea name="content" placeholder="输入内容" cols="50"
									rows="15" style="padding: 10px; resize: none;"><%=editNote.getContent() %></textarea></td>


						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="保存"></td>


						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</section>
	<%session.removeAttribute("note_session"); %>
	
</body>
</html>