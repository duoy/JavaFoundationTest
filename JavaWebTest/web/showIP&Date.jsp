<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: recha
  Date: 2019/4/12
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showIP&Date</title>
</head>
<body>
<h2>
    <%
        out.write("IP: "+request.getRemoteHost());
        out.write(LocalDateTime.now().toString());

    %>
</h2>
</body>
</html>
