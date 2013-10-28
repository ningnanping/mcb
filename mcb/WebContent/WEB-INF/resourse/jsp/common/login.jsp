<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>妈妈带宝宝管理系统</title>
<%@ include file="../base.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css">
<script>
$(function(){
	// 按键事件
 	$('#ok').bind('click', function(){
 		if($('#loginform').form('validate')){
 			$('#loginform').submit();
 		}
 	});
	$('#cancel').bind('click', function(){
		$('#loginform').form('clear');
	});
	$('#modifypwd').bind('click', function(){
		$('#modifyWin').window('open');
	});
	
	//cancelMP 
	$('#cancelMP').bind('click', function(){
		$('#modifyWin').window('close');
	});
	
	// extend the 'equals' rule
	$.extend($.fn.validatebox.defaults.rules, {
	    equals: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '两次输入的新密码不一致.'
	    }
	});
	
});
</script>
</head>
<body>
<div class="login">
	<div class="inputSec">
		<form id="loginform" action="<%=request.getContextPath()%>/common/login.html" method="post">
            <table>
                <tr>
                    <td width="60">用户名:</td>
                    <td><input class="easyui-validatebox" type="text" name="userName" 
                    		data-options="required:true,missingMessage:'请输入您的用户名'"></input>
                    </td>
                </tr>
                <tr>
                    <td width="60">密&nbsp;&nbsp;码:</td>
                    <td><input class="easyui-validatebox" type="password" name="password" 
                    		data-options="required:true,missingMessage:'请输入您的密码'"></input>
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div class="buttonSec">
            <img src="<%=path%>/img/ok.gif" id="ok" border="0" style="cursor: pointer;padding-left: 10px;">
            <img src="<%=path%>/img/cancel.gif" id="cancel" border="0" style="cursor: pointer;padding-left: 10px;">
<%--             <img src="<%=path%>/img/xgmm.gif" id="modifypwd" border="0" style="cursor: pointer;padding-left: 10px;"> --%>
    </div>
</div>
</body>
</html>