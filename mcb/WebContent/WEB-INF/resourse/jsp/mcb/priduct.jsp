<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商品列表</title>
    <%@ include file="../base.jsp"%>
</head>
<body>
<table id="dg">
    <thead>
    <tr>
        <th data-options="field:'id',align:'center',width:200,hidden:true"></th>
        <th data-options="field:'name',align:'center',width:200">名称</th>
        <th data-options="field:'uuid',align:'center',width:200">唯一识别码</th>
        <th data-options="field:'priceText',align:'center',width:200">价格</th>
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
        url: "<%=path%>/product/list.json",
        pageSize : 15,
        pageList : [ 15, 20, 25, 35 ]
    });

</script>
</html>