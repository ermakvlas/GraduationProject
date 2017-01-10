<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.vlas.gradpro.web.VoteServlet" %>
<%@ page import="org.springframework.context.ConfigurableApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vlas.gradpro.model.Restaurant" %>
<%--
  Created by IntelliJ IDEA.
  User: nassuka
  Date: 18.12.2016
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Console</title>
</head>
<body>

<%
    String a = "!!!!!";
%>
String a equals <%=a%>
<br>
AppContextes are: <br>
<c:forEach items="${appCont}" var="item">
    ${item}<br>
</c:forEach>
<br>
Restaurant list through RestController:
<br>

</body>
</html>
