<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户列表</title>
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
                  {field:'name',align:'center',width:200,title:'姓名'},
                  {field:'customerLevelIdText',align:'center',width:200,title:'客户等级'},
                  {field:'sexText',align:'center',width:200,title:'性别'},
                  {field:'phoneNumber',align:'center',width:200,title:'电话号码'},
                  {field:'createTimeText',align:'center',width:200,title:'创建时间'},
                  {field:'email',align:'center',width:200,title:'电子邮箱'},
                  {field:'agentText',align:'center',width:200,title:'代理人'},
                  {field:'score',align:'center',width:200,title:'积分'},
                  {field:'handText',align:'center',width:200,title:'经手人'},
                  {field:'babyMonth',align:'center',width:200,title:'宝贝月份'},
                  {field:'vipId',align:'center',width:200,title:'会员号'}
                  ]],
        fitColumns:true,
        height: 'auto',
        url: "<%=path%>/customer/list.json",
        pageSize : 15,
        pageList : [ 15, 20, 25, 35 ]
    });

</script>
</html>