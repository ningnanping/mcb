<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.xingzhe.framework.util.DateUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登记数据比对</title>
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
 				<th data-options="field:'qdcgs',align:'center',width:200">导入成功数</th>
 				<th data-options="field:'pdczs',align:'center',width:200">导入失败数</th>
				<th data-options="field:'wfds',align:'center',width:200">下发数</th>
				<th data-options="field:'sjfw',align:'center',width:200">导入机关</th>
				<th data-options="field:'lrry',align:'center',width:200">导入人员</th>
				<th data-options="field:'pdzt',align:'center',width:200">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="toolbar">
		<h2 align="center">登记数据比对</h2>
		<fieldset>
			<div>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryData()">查询</a> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="querDetailyData()">查询明细</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="tongyuhuji()">统一户籍</a> 
			</div>
		</fieldset>
		<br>
		<fieldset>
			 <legend>查询条件</legend>
			<table  >
				<tr>
					<td width="25%" align="right"><label>部门</label></td>
					<td width="25%" align="left"><select class="easyui-combobox" name="dept"
						style="width: 200px;" id="dept">
					</select></td>
					<td width="25%" align="right"><label>文件类型</label></td>
					<td width="25%" align="left"><select id="filetype" class="easyui-combobox"
						name="filetype" style="width: 200px;">
					</select></td>
				</tr>
				<tr>
					<td width="25%" align="right">文件导入时间起</td>
					<td width="25%" align="left"><input class="easyui-datebox" id="start"></input></td>
					<td width="25%" align="right">文件导入时间止</td>
					<td width="25%" align="left"><input class="easyui-datebox" id="end"></input></td>
				</tr>
				<tr>
					<td width="25%" align="right"><label>是否加工</label></td>
					<td width="25%" align="left"><select id="sfjg" class="easyui-combobox"
						name="sfjg" style="width: 200px;">
						<option   value="-1">全部</option>
						<option   value="1">未加工</option>
						<option   value="4">已加工</option>
					</select></td>
				</tr>
			</table>
		</fieldset>
		<br/>
		<fieldset>
			 <legend>比对部门</legend>
			 <table  >
				<tr>
					<td width="25%" align="right"><label>部门</label></td>
					<td width="25%" align="left"><select class="easyui-combobox" name="bddept"
						style="width: 200px;"   id="bddept">
					</select></td>
					<td width="25%" align="right"><label>文件类型</label></td>
					<td width="25%" align="left"><select id="bdfiletype" class="easyui-combobox"
						name="bdfiletype" style="width: 200px;">
					</select></td>
				</tr>
			</table>
		</fieldset>
		<br>
	</div>
</body>

<script type="text/javascript">

	$(document).ready(function(){
		//部门和文件类型的获取
		$("#dept").combobox({
			url:'<%=path%>/common/dept.json',
			valueField:'id',
			textField:'name',
			onSelect: function(rec){
				var id=rec.id;
				var name=rec.name;
				var json="{\"id\":\""+id+"\",\"name\":\""+name+"\"}";
				
				//$("#bddept").innerHTML="<option   value=\""+id+"\">"+name+"</option>"
				var url="<%=path%>/common/dept/"+id+"/filetype.json";			
				$("#filetype").combobox({
					url:url,
					valueField:'id',
					textField:'name',
					onSelect: function(rec){
						var fid=rec.id;
						var fname=rec.name;
						if(fid!=null&&fid!=""&&fid!="null"){
							queryData();
							//$('#dg').datagrid('reload',{ wjlx: fid});
							//$('#bdfiletype').combo('setValue', fid).combo('setText', fname).combo('hidePanel');
						}
					}
				});
				
				$('#bddept').combo('setValue', "01").combo('setText', "地税局").combo('hidePanel');
				$('#bdfiletype').combo('setValue', "C006").combo('setText', "地税户籍表").combo('hidePanel');
				
				/* $("#bdfiletype").combobox({
					url:url,
					valueField:'id',
					textField:'name',
				}); */
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