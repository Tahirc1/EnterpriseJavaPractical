<%-- 
    Document   : intrinsic
    Created on : 25 Jul, 2022, 4:11:44 PM
    Author     : win10
--%>
<%--  page directive --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Use of Intrinsic Object in JSP </h1>
        
        <h1> Request Object </h1>
        Query String: <%=request.getQueryString()  %> <br>
        Context Path: <%=request.getContextPath()  %> <br>
        Remote host: <%=request.getRemoteHost() %> <br>
        
        <h1> Response Object </h1>
        Character Encoding: <%=response.getCharacterEncoding() %> <br>
        Content-Type: <%=response.getContentType() %> <br>
        Locale: <%=response.getLocale() %>
        
        <h1> Session object</h1>
        ID: <%=session.getId() %> <br>
        Creation Time: <%=session.getCreationTime() %> <br>
        Last Access Time: <%=session.getLastAccessedTime() %> <br>
    </body>
</html>
