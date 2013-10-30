<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单列表</title>
    <%@ include file="../base.jsp"%>
</head>
<body>
		<table id="dg"></table>
</body>

<script type="text/javascript">
    $('#dg').datagrid({
        width:$(this).width()*0.98,
        rownumbers:true,
        singleSelect:true,
        pagination:true,
        fit:true,
        columns:[[{field:'id',align:'center',width:200,hidden:true},
                  {field:'createTimeText',align:'center',width:200,title:'创建时间'},
                  {field:'settlemen',align:'center',width:200,title:'付款方式'},
                  {field:'total',align:'center',width:200,title:'总金额'},
                  {field:'customerId',align:'center',width:200,hidden:true},
                  {field:'name',align:'center',width:200,title:'用户名称'},
                  ]],
        fitColumns:true,
        height: 'auto',
        url: "<%=path%>/order/list.json",
        pageSize : 15,
        pageList : [ 15, 20, 25, 35 ]
    });

</script>
</html>