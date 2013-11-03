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
		<div id="dlg" class="easyui-dialog" style="width:550px;height:280px;padding:10px 20px;"  closed="true" buttons="#dlg-buttons">
			<form id="fm" method="post" style="text-align:center;" >
	           	<table >
	           		<tr>
		           		<td> 姓名: </td>
		               	<td>
		               	<input name="id" type="hidden">
		               	<input name="name" class="easyui-validatebox" required="true"></td>
		                <td> 客户等级:</td>
		                <td> <input class="easyui-combobox" id="customerLevelId" name="customerLevelId" required="true" 
		                data-options="valueField:'id',textField:'name',url:'<%=path%>/common/customerLevel.json'"></td> 
	                </tr>
	                <tr>
		                <td>性别:</td>
		                <td><select id="sex" class="easyui-combobox" style="width:153px;" name="sex">
			                <option value="0">男</option>
			                <option value="1">女</option>
		               	 </select></td> 
	               		<td>电话号码:</td>
	               		<td><input name="phoneNumber" class="easyui-validatebox" required="true" prompt="输入合法的身份证号码"></td> 
	                </tr>
	                <tr>
	               		<td> Email:</td>
	               		<td><input name="email" class="easyui-validatebox" data-options="validType:'email'"></td> 
	               		<td>代理人:</td>
	               		<td><input name="agentId" class="easyui-combobox" required="true" data-options="valueField:'id',textField:'name',url:'<%=path%>/common/employee.json'"></td> 
	                </tr>
	                <tr>
	               		<td>积分:</td>
	               		<td><input name="score"  disabled="disabled" /></td> 
	               		<td>经手人:</td>
	               		<td><input name="handId" class="easyui-combobox" required="true" data-options="valueField:'id',textField:'name',url:'<%=path%>/common/employee.json'"></td> 
	                </tr>
	                <tr>
	               		<td>宝宝月份:</td>
	               		<td><input name="babyMonth"></td> 
	                </tr>
	            </table>
	        </form>
		</div>
		
		<div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCustomer()">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    	</div>
</body>

<script type="text/javascript">

	   	var url=""; 
		var toolbar = [{
		    text:'新增',
		    iconCls:'icon-add',
		    handler:function(){
		    	 $('#dlg').dialog('open').dialog('setTitle','新增客户信息');
		    	 $('#fm').form('clear');
		    	 url="<%=path%>/customer/save.html";
		    }
		},{
		    text:'修改',
		    iconCls:'icon-edit',
		    handler:function(){
		    	 var row = $('#dg').datagrid('getSelected');
		         if (row){
		        	 $('#dlg').dialog('open').dialog('setTitle','修改客户信息');
		        	 $('#fm').form('load',row);
		        	 url="<%=path%>/customer/update.html";
		         }else{
		        	 $.messager.alert("Error","选择一条数据修改","Error");
		         }
		    }
		},'-',{
		    text:'删除',
		    iconCls:'icon-cancel',
		    handler:function(){
		    	 var row = $('#dg').datagrid('getSelected');
		    	 if (row){
		    		 $.messager.confirm('Confirm','确定删除该条客户信息?',function(r){
		                 if (r){
		                     $.post("<%=path%>/customer/del.html",{id:row.id},function(result){
		                         if (result.code==1000){
		                         	$('#dg').datagrid('reload');
		                         	setTimeout(function(){
		                         		$.messager.show({
		                                     title: 'INFO',
		                                     msg: result.desc
		                                 });
		                         	},1000);
		                         } else {
		                             $.messager.show({
		                                 title: 'Error',
		                                 msg: result.desc
		                             });
		                         }
		                     },'json');
		                 }
		             });
		    	 }else{
		    		 $.messager.alert("ERROR","请选着一条数据修改","ERROR");
		    	 }
		    	
		    }
		}];
		
	    $('#dg').datagrid({
	        width:$(this).width()*0.98,
	        rownumbers:true,
	        toolbar:toolbar,
	        singleSelect:true,
	        pagination:true,
	        fit:true,
	        columns:[[{field:'id',align:'center',width:200,hidden:true},
	                  {field:'vipId',align:'center',width:200,title:'会员号'},
	                  {field:'name',align:'center',width:200,title:'姓名'},
	                  {field:'sexText',align:'center',width:200,title:'性别'},
	                  {field:'babyMonth',align:'center',width:200,title:'宝贝月份'},
	                  {field:'phoneNumber',align:'center',width:200,title:'电话号码'},
	                  {field:'email',align:'center',width:200,title:'电子邮箱'},
	                  {field:'score',align:'center',width:200,title:'积分'},
	                  {field:'customerLevelIdText',align:'center',width:200,title:'客户等级'},
	                  {field:'agentText',align:'center',width:200,title:'代理人'},
	                  {field:'handText',align:'center',width:200,title:'经手人'},
	                  {field:'createTimeText',align:'center',width:200,title:'创建时间'}
	                  ]],
	        fitColumns:true,
	        height: 'auto',
	        url: "<%=path%>/customer/list.json",
	        pageSize : 15,
	        pageList : [ 15, 20, 25, 35 ]
	    });
	    
	    function saveCustomer(){
	    	 $('#fm').form('submit',{
	         	 url:url,
	             onSubmit: function(){
	                 return $(this).form('validate');
	             },
	             success: function(result){
	             	result = eval("("+result+")");
	                 if (result.code==1000){
	                 	$('#dlg').dialog('close');        // close the dialog
	                     $('#dg').datagrid('reload');    // reload the user data
	                 } else {
	                     $.messager.alert('Error', result.desc, 'Error');
	                 }
	             }
	         });
	    }

</script>
</html>