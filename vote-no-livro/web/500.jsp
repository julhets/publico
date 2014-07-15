<%-- 
    Document   : 500
    Created on : 14/07/2014, 18:12:47
    Author     : Júlio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/home.css"/>
        <title>Vote no Livro - Erro</title>
    </head>
    <body>
        <div class="header">
            <h1>Vote no Livro - Erro</h1>
        </div>
        <div class="content">
            <br/>
            <h3>Ocorreu um erro na aplicação</h3>
            <br/>
            <div><%= exception.toString()%><br>  </div>

            <%
                out.println("");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                exception.printStackTrace(pw);
                out.print(sw);
                sw.close();
                pw.close();
                out.println("");
            %>
        </div>
    </body>
</html>
