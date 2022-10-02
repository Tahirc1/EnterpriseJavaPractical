<%-- 
    Document   : e
    Created on : 26 Sep, 2022, 6:21:04 PM
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
       
       <p> ${param.name} </p>
       <p> ${sessionScope.user} </p> 
       <p> ${cookie.name.value} </p>
    </body>
</html>
