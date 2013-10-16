<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.xingzhe.framework.util.DateUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单列表</title>
    <%
        String start=DateUtil.getFirstDay(false);
        String end=DateUtil.getLastDay(false);
    %>
    <%@ include file="/base.jsp"%>
</head>
<body>
<table id="dg">
    <thead>
    <tr>
        <th data-options="field:'id',align:'center',width:200,hidden:true"></th>
        <th data-options="field:'createTimeText',align:'center',width:200">创建时间</th>
        <th data-options="field:'settlemen',align:'center',width:200">付款方式</th>
        <th data-options="field:'total',align:'center',width:200">总金额</th>
        <th data-options="field:'customerId',align:'center',width:200,hidden:true"></th>
        <th data-options="field:'name',align:'center',width:200">用户名称</th>
    </tr>
    </thead>
</table>
</body>

<script type="text/javascript">
    $('#dg').datagrid({
        width:$(this).width()*0.98,
        rownumbers:true,
        singleSelect:true,
        pagination:true,
        fit:true,
        fitColumns:true,
        height: 'auto',
        url: "<%=path%>/order/list.json",
        pageSize : 15,
        pageList : [ 15, 20, 25, 35 ]
    });

</script>
</html>