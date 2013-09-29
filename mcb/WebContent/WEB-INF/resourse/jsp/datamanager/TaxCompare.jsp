<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.xingzhe.framework.util.DateUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>税源数据比对</title>
<%
	String start=DateUtil.getFirstDay(false);
	String end=DateUtil.getLastDay(false);
%>
<%@ include file="/base.jsp"%>
</head>
<body>
	<table id="dg" toolbar="#toolbar">
		<thead>
			<tr>
				<th data-options="field:'pcxh',align:'center',width:200,hidden:true"></th>
				<th data-options="field:'wjlx',align:'center',width:200">文件类型</th>
				<th data-options="field:'drsj',align:'center',width:200">文件导入时间</th>
				<th data-options="field:'drs',align:'center',width:200">文件总数</th>
				<th data-options="field:'wfds',align:'center',width:200">下发数</th>
				<th data-options="field:'sjfw',align:'center',width:200">导入机关</th>
				<th data-options="field:'lrry',align:'center',width:200">导入人员</th>
				<th data-options="field:'pdzt',align:'center',width:200">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<h2 align="center">税源数据比对</h2>
		<fieldset>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryData()">查询</a> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="querDetailyData()">查询明细</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="compareData()">执行比对</a> 
			</div>
		</fieldset>
		<br>
		<fieldset>
			 <legend>查询条件</legend>
			<table  style="table-layout: fixed">
				<tr align="left">
					<td  align="right" ><label>税种</label></td>
					<td  align="left" ><select class="easyui-combobox" name="taxType"
						style="width: 200px;" id="taxType">
					</select></td>
					<td  align="right" ><label>部门</label></td>
					<td  align="left" ><select id="dept" class="easyui-combobox"
						name="dept" style="width: 100px;">
					</select></td>
					<td  align="right" ><label>表样</label></td>
					<td  align="left" ><select id="tableType" class="easyui-combobox"
						name="tableType" style="width: 300px;">
					</select></td>
				</tr>
				<tr align="left">
					<td width="25%" align="right">数据时间起</td>
					<td width="25%" align="left"><input class="easyui-datebox" id="start"></input></td>
					<td width="25%" align="right">数据时间止</td>
					<td width="25%" align="left"><input class="easyui-datebox" id="end"></input></td>
				</tr>
			</table>
		</fieldset>
		<br/>
	</div>
</body>

<script type="text/javascript">

	$(document).ready(function(){
		//部门和文件类型的获取
		$("#taxType").combobox({
			url:'<%=path%>/common/tax.json',
			valueField:'id',
			textField:'name',
			onSelect: function(rec){
				var id=rec.id;
				
				var url="<%=path%>/common/tax/"+id+"/dept.json";			
				$("#dept").combobox({
					url:url,
					valueField:'id',
					textField:'name'
				});
				
				url="<%=path%>/common/tax/"+id+"/tableType.json";	
				$("#tableType").combobox({
					url:url,
					valueField:'id',
					textField:'name'
				});
			}
		});
		
		//初始化日期的格式
		$("#start").datebox('setValue',"<%=start%>");
		$("#end").datebox('setValue',"<%=end%>");
		
	});

	$('#dg').datagrid({
		width:$(this).width()*0.98,
	    rownumbers:true,
	    singleSelect:true,
	    pagination:true,
	    fit:true,
	    fitColumns:true,
	    height: 'auto',  
	    url: "<%=path%>/register/list.json",
			pageSize : 15,
			pageList : [ 15, 20, 25, 35 ]
	});
	
	function queryData(){
		
		var fileType=$('#filetype').combobox('getValue');
		var sfjg=$('#sfjg').combobox('getValue');
		if(sfjg==-1&&sfjg=="-1"){
			sfjg=null;
		}
		var start=$("#start").datebox('getValue');
		var end=$("#end").datebox('getValue');
		
		if(fileType!=null&&fileType!=""){
			$('#dg').datagrid('reload', { wjlx: fileType,sfjg:sfjg,startDate:start,endDate:end});
		}else{
			 $.messager.alert('Error',"请选择文件类型", 'Error');
		}
	}
	
	
	function querDetailyData(){
		 $.messager.alert('Info',"该功能等待开发", 'Info');
	}
	
	
	function tongyuhuji(){
		var row = $('#dg').datagrid('getSelected');
		var fileType=$('#filetype').combobox('getValue');
		var bdfiletype=$('#bdfiletype').combobox('getValue');
		if(fileType!=null&&fileType!=""&&bdfiletype!=null&&bdfiletype!=""){
			if (row){
				$.post("<%=path%>/register/unifiedRegistration.json", 
						 {xh : row.pcxh,wjlx:fileType,bdwjlx:bdfiletype}, 
						function(result) {
							if (result.code == 1000) {
							queryData();
							//$('#dg').datagrid('reload',{ wjlx: fileType});
							setTimeout(function() {
								$.messager.show({
									title : 'INFO',
									msg : result.mess
								});
							}, 1000);
						} else {
							$.messager.show({
								title : 'Error',
								msg : result.mess
							});
						}
					}, 'json');
			}
		}else{
			 $.messager.alert('Error',"选择比对文件类型", 'Error');
		}
	}
</script>
</html>