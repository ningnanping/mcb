<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="/base.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/zhzs.css">
<script type="text/javascript" >
	$((function(){
		$("#dept").combobox({
			url:'<%=path%>/common/dept.json',
			valueField:'id',
			textField:'name',
			onSelect: changeBydept(rec)
		});
	}

	function changeBydept(rec){
		$("#filetype").combobox({
			url:'<%=path%>/common/dept.json',
			valueField:'id',
			textField:'name'
		});
	}
</script>
</head>
<body>
	<h2 align="center">外部信息导入</h2>
	<fieldset style="width: 98%">
		<legend>操作区</legend>
		
		<div class="fitem">
			<label>部门:</label> <select  class="easyui-combobox"
				name="dept" style="width: 200px;" id="dept"
				data-options="url:'<%=path%>/common/dept.json', valueField:'id', textField:'name'">
			</select>
		</div>

		<div class="fitem">
			<label>文件类型:</label> <select id="filetype" class="easyui-combobox"
				name="filetype" style="width: 200px;">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
			</select>
		</div>

		<div class="fitem">
			<label>文件路径:</label> <input type="file">
		</div>
	</fieldset>

	<br />
	<fieldset style="width: 98%">
		<legend>数据上传日志</legend>
	</fieldset>
</body>

</html>