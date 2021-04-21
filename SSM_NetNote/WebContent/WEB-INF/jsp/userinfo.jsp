
<%@page import="java.net.URLEncoder"%>
<%@page import="cn.antchensw.netnote.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="500"%>
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
	
	%>

	<nav class="center_content">
		<div align="center">
			<h1>我的信息</h1>
			<h5>
				欢迎，<%=user.getName()%>
				<br> <a href="index"> 我的笔记 </a><span>
			</h5>

		</div>
	</nav>

	<!-- 用户资料 -->
	<section>
		<div align="center">
            
			<table style="text-align: left;">
				<thead>
					<tr>
						<td colspan="6"><h3>注册资料</h3></td>
					</tr>
				</thead>
				<tbody>
				    <tr>
                    <td>头像:</td>
                    <td>
                    <%if(user.getPhoto()==1){ %>
                    <a href="${pageContext.request.contextPath }/user.downloadphoto?photoname=<%=URLEncoder.encode(user.getId()+".png", "UTF-8")%>">
                        <img src="img/<%=user.getId() %>.png" width="50px" height="50px"/>
                        </a>
                    <%}else{ %>
                        <form action="${pageContext.request.contextPath }/user.uploadphoto" enctype="multipart/form-data" method="post" > 
                            <input type="file" name="photofile">
                            <input type="submit" value="上传"/> 
                            
                         </form> 
                    
                    <%} %>
                    <span style="color: red;">${msg_savephoto}</span>
                            <%session.removeAttribute("msg_savephoto"); %>
                    </td>
                    </tr>
                    <tr>
                    <td>ID:</td><td><%=user.getId() %></td>
                    </tr>
                    <tr>
                    <td>用户名:</td><td>
                    <form action="${pageContext.request.contextPath }/user.updateusername" method="post">
                    <input type="text" name="name" maxlength="16" size="10"  placeholder="用户名"
                                pattern="[a-zA-Z0-9_]{2,16}" title="请输入2-16位英文,数字或者下划线!" value="<%=user.getName() %>" required="required"><input type="submit" value="修改">
                                </form>
                                <span style="color: red;">${msg_update}</span></td>
                                <%session.removeAttribute("msg_update") ;%>
                    </tr>
                    <tr>
                    <td>邮箱:</td><td><%=user.getEmail() %></td>
                    </tr>
                    <tr>
                    <td>账号等级:</td><td><%=user.getLevel() %></td> 
                    </tr>
                    
				</tbody>
			</table>
		</div>
	</section>

	<section>
		<div align="center">
			<table style="text-align: left;">
                <thead>
                    <tr>
                        <td colspan="3"><h3>笔记详情</h3></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <td>笔记总记</td></td>
                    </tr>
                    
                </tbody>
            </table>
		</div>
	</section>


</body>
</html>