<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//request.getRequestDispatcher("/index.html").forward(request, response);
//response.sendRedirect(basePath + "index.html");
response.sendRedirect(basePath + "user/toLogin.html");
%>