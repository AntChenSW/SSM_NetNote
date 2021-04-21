<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="styleSheet" href="css/style.css" />
</head>
<body align="center">
    <% String email_session=(String)session.getAttribute("email_session") ;%>
<!-- 找回密码 -->
<section>
<div  align="center">
<form action="${pageContext.request.contextPath }/user.getpass" method="post">
<table>
    <thead>
    <tr><td colspan="3">
        <h2>找回密码</h2></td></tr>
    </thead>
    <tbody>
        <tr>
            <td width="55px">邮&nbsp;&nbsp;&nbsp;箱:</td>
            <td colspan="2" width="180px"><input type="text" name="email" pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"
                                title="请输入正确的邮箱格式!"
                                <%if(email_session!=null){ %>
                                    value="<%=email_session %>"
                                <%} %>
                                <%session.removeAttribute("email_session");%>
                                
                                 maxlength="32" size="20" placeholder="邮箱" required="required"></td>
        </tr>
        <tr>
            <td>验证码:</td>
            <td><input name="vericode" placeholder="验证码" style="width: 60px" required="required"></td>
            <td>
            <img style="height: 28px" id="vericodeImg" src="${pageContext.request.contextPath }/user.imagecode?"><br>
            </td>
        </tr>
        <tr>
        <td></td><td></td>
            <td>
            <a  href="javascript:changeImg();">看不清，换一张</a></td>
        </tr>
        <tr>
            <td colspan="3">
            <p>
                                    &ensp;&ensp;<span style="color: red">${msg_pass} </span>
                                    <%session.removeAttribute("msg_pass") ;%>
                                </p></td>
            
        </tr>
        <tr>
            <td><a href="login">去登录</a></td>
            <td><input type="submit" value="  找回密码  "></td>
        </tr>
    </tbody>
</table>
</form>
</div>
</section>

<script type="text/javascript" src="lib/jquery/jquery-3.4.0.min.js"></script>
  <script type="text/javascript">
      function changeImg() { 
          //需要让每次请求的url都发生变化。否则服务器会认为访问的时一张图片，就不会刷新请求了
          //每次url一样，服务器会认为访问的url是同一张图片，没变化
    	  var img=document.getElementById("vericodeImg");
           img.src=img.src+"?"+Math.random();
      }
  </script>

</body>
</html>