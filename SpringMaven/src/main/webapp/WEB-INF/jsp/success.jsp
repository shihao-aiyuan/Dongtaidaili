<%--
  Created by IntelliJ IDEA.
  User: cplh
  Date: 2020/7/21
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
${successMsg } Welcome!  <shiro:principal/>
<br><br>

<shiro:hasAnyRoles name="user">
    <a href="/jsp/user.jsp">User Page</a>
</shiro:hasAnyRoles>

<br><br>

<shiro:hasAnyRoles name="admin">
    <a href="/jsp/admin.jsp">Admin Page</a>
</shiro:hasAnyRoles>

<br><br>
<a href="../test/logout.do">Logout</a>
</body>
</html>
