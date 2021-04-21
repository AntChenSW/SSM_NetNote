<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="500"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" type="text/css" >
<title>登录</title>
<script type="text/javascript">
function check(form) {
	var method=null;
	var obj=document.getElementsByName("isAdmin");
	for (var i=0;i<obj.length;i++){
		   if(obj[i].checked){
			   method=obj[i].value;
			}
	}
	if(method=="no"){
		form.action="user.login";
	}else{
		form.action="admin.login";
	}
	alert("正在验证用户...");
	form.submit();
}
</script>

</head>
<body >

<!-- 登录 -->
<section>
<div align="center">
<form  method="post" >
<table>
    <thead>
        <tr >
            <td colspan="3" >
                <h2>登录</h2>
                <p>&ensp;&ensp;<span style="color: red">${msg}</span></p>
                <%session.removeAttribute("msg") ;%>
            </td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td  width="50px">账号:</td>
            <td><input type="text" id="username" name="name" maxlength="16" size="20"></td>
            <td><a href="register">没有账号?</a></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" id="userpass" name="password" maxlength="16" size="20"></td>
            <td><a href="getpass">找回密码!</a></td>
        </tr>
         <tr>
            <td></td>
            <td>
                <input type="radio" id="user_" checked="checked" name="isAdmin" value="no">
                <label for="user_">用户</label> 
                <input type="radio" id="admin_" name="isAdmin" value="yes">
                <label for="admin_">管理员</label></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td> <input type="button" value="  登录  " onclick="check(this.form)"></td>
            <td></td>
        </tr>
    </tbody>
</table>
</form>
</div>
</section>


</body>
</html>