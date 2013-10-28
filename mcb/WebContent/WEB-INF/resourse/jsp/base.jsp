<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String basePath = request.getContextPath();
String  path= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+basePath;
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/themes/icon.css">
<script type="text/javascript" src="<%=path%>/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/easyui-lang-zh_CN.js"></script>
