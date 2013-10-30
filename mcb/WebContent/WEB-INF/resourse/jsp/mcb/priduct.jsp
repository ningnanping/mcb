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
	<table id="dg"> </table>
</body>

<script type="text/javascript">
    $('#dg').datagrid({
        width:$(this).width()*0.98,
        rownumbers:true,
        singleSelect:true,
        pagination:true,
        fit:true,
        fitColumns:true,
        columns:[[{field:'id',align:'center',width:200,hidden:true},
                  {field:'name',align:'center',width:200,title:'名称'},
                  {field:'uuid',align:'center',width:200,title:'唯一识别码'},
                  {field:'priceText',align:'center',width:200,title:'价格',
                	  formatter:function(value,rowData,rowIndex){
                		  return "<a href='javacript:;' onclick='queryVipPrice();'>"+value+"</a>";
                		  }
                	  }
                 ]],
        height: 'auto',
        url: "<%=path%>/product/list.json",
        pageSize : 15,
        pageList : [ 15, 20, 25, 35 ]
    });

    function queryVipPrice(){
    	alert(123);
    }
</script>
</html>