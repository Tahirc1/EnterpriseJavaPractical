<%-- 
    Document   : index
    Created on : 26 Sep, 2022, 6:17:47 PM
    Author     : win10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
         Cookie ck = new Cookie("name","tahir");
         response.addCookie(ck);
         session.setAttribute("user","tahir");
        %>
         <form action="e.jsp">
            Name <input type="text" name="name"><br>
            <input type="submit"/>
        </form>
    </body>
</html>
