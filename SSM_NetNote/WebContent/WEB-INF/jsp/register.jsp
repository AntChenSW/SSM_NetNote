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
<body align="center">
    <%User user_register_session=(User) session.getAttribute("user_register_session"); %>
    
	<!-- 注册 -->
	<section>
		<div align="center">
			<form action="${pageContext.request.contextPath }/user.register" method="post">
				<table>
					<thead>
						<tr>
							<td colspan="2">
								<h2>注册</h2>
								<p>
									&ensp;&ensp;<span style="color: red">${msg_register}</span>
									<%session.removeAttribute("msg_register") ;%>
								</p>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="100px">用&nbsp;户&nbsp;名:</td>
							<td><input type="text" name="name" maxlength="16" size="20"  placeholder="用户名"
                                pattern="[a-zA-Z0-9_]{2,16}" title="请输入2-16位英文,数字或者下划线!"
                                <%if(user_register_session!=null){ %>
                                value="<%=user_register_session.getName() %>"
                                <%} %>
                                 required="required"></td>
						</tr>
						<tr>
							<td>邮箱地址:</td>
							<td><input type="text" name="email" pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
                                title="请输入正确的邮箱格式!" maxlength="32" size="20" placeholder="邮箱"
                                <%if(user_register_session!=null){ %>
                                value="<%=user_register_session.getEmail() %>"
                                <%} %>
                                 required="required"></td>
						</tr>
						<tr>
							<td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
							<td><input type="password" name="password1" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"
                                title="请输入以英文字母开头的6-16位的英文或者数字!" maxlength="20"
								size="17" placeholder="密码" 
								<%if(user_register_session!=null){ %>
                                value="<%=user_register_session.getPassword() %>"
                                <%} %>
								 required="required"></td>
						</tr>
						<tr>
							<td>确认密码:</td>
							<td><input type="password" name="password2" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$"
                                title="请输入以英文字母开头的6-16位的英文或者数字!" maxlength="16"
								size="20" placeholder="确认密码"
								<%if(user_register_session!=null){ %>
                                value="<%=user_register_session.getPassword() %>"
                                <%} %>
								 required="required"></td>
						</tr>


						<tr>
							<td><a href="login">去登陆</a></td>
							<td><input type="submit" value="  注册  " onclick="return checkPass()"></td>
							
							
							<td></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</section>
    <script type="text/javascript">
             function checkPass() {
            	 var pass1=document.getElementsByName("password1")[0].value;
            	 var pass2=document.getElementsByName("password2")[0].value;  
            	 if(pass1==pass2){
            		 return true;
            	 }else{
            		 alert("两次密码不一致!请检查!")
            		 return false;
            	 }
             }
    </script>
    <%session.removeAttribute("user_register_session"); %>
</body>
</html>