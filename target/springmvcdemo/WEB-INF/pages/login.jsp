<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2016/5/4
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1 align="center">登录处理</h1>

<!--在jsp中调用javabean-->
<jsp:useBean id="login" class="com.bigwork.model.LoginBean" scope="session"/>
<jsp:setProperty name="login" property="*"/>
<%
    String name = login.getName();
    String password = login.getPassword();
    boolean result = login.checkUser(name, password);
    if(result){
        out.println("<h3 align = 'center'>你登录成功啦！</h3>");
    }else{
        out.println("<h31 align = 'center'>登录失败</h31>");
    }
%>
</body>
</html>
