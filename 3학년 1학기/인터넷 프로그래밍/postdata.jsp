<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>POST 방식 요청</title>
    </head>
    <body>
    <!-- JSP 문법 작성 -->
    <%
    request.setCharacterEncoding("UTF-8");
    String strName=request.getParameter("name");
    String strMajor=request.getParameter("major");
    String valOne = request.getParameter("val1");
    String valTwo = request.getParameter("val2");


    int num1 = Integer.parseInt(valOne); 
    int num2 = Integer.parseInt(valTwo);

    out.println("이름 :" + strName + "<br/>");
    out.println("학과 :" + strMajor + "<hr/>");
    out.print("두 값의 합 :");
    out.println(num1 + num2);
    %>
    </body>
</html>