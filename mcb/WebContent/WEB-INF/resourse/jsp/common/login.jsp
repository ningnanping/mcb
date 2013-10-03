<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>昌吉市综合治税信息管理系统</title>
<%@ include file="/base.jsp"%>
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
	// 修改密码
	$('#modifyform').form({
	    url:"<%=request.getContextPath()%>/modify/pwd",
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	var message = '修改密码成功！';
	    	if(data.resStatus != 'SUCCESS'){
	    		message = '修改密码失败：[error code:' + data.errCode + ']' + data.errMess;
	    	}
	    	$.messager.show({
				title:'修改密码',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
	    }
	});
	// 修改密码
	$('#modifyPwdSub').bind('click', function(){
		if($('#modifyform').form('validate')){
			$('#modifyform').submit();
		}
		
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
		<form id="loginform" action="<%=request.getContextPath()%>/login" method="post">
            <table>
                <tr>
                    <td width="60">用户名:</td>
                    <td><input class="easyui-validatebox" type="text" name="usr" 
                    		data-options="required:true,missingMessage:'请输入您的用户名'"></input>
                    </td>
                </tr>
                <tr>
                    <td width="60">密&nbsp;&nbsp;码:</td>
                    <td><input class="easyui-validatebox" type="text" name="password" 
                    		data-options="required:true,missingMessage:'请输入您的密码'"></input>
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div class="buttonSec">
            <img src="<%=path%>/img/ok.gif" id="ok" border="0" style="cursor: pointer;padding-left: 10px;">
            <img src="<%=path%>/img/cancel.gif" id="cancel" border="0" style="cursor: pointer;padding-left: 10px;">
            <img src="<%=path%>/img/xgmm.gif" id="modifypwd" border="0" style="cursor: pointer;padding-left: 10px;">
    </div>
	<div class="co">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
  				<tr>
    				<td width="200" align="right">
    					<span class="style2">注意事项：</span>
    				</td>
    				<td align="left">
    					<span class="style2">请使用IE 6.0以上版本,建议分辨率 1024×768,并将此站点加入可信站点</span>
    				</td>
  				</tr>
  				<tr height='5'><td></td><td></td></tr>
  				<tr>
    				<td width="200" align="right">
    					<span class="style2">友情提醒：</span>
    				</td>
    				<td align="left">
    					<span class="style2">请大家马上修改自己的操作口令，以防止被人盗用</span>
    				</td>
  				</tr>
  				<tr height='30'><td></td><td></td></tr>
  				<tr>
    				<td colspan=2 align="center" valign="middle">
    					<span class="style3">技术支持：</span>
    					<span class="style3">扬州紫竹软件有限公司</span>
    				</td>
  				</tr>
			</table>
	</div>
</div>

<!-- 修改密码 -->
<div id="modifyWin" class="easyui-window" title="修改密码"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:400px;height:230px;padding:10px;">
 			   
		<form id="modifyform" method="post" style="">
            <table>
                <tr>
                    <td align="right">用户名:</td>
                    <td style="padding-left: 5px"><input name="user" type="text" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入您的用户名'"></input></td>
                </tr>
                <tr>
                    <td align="right">旧密码:</td>
                    <td style="padding-left: 5px"><input name="password" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入您的旧密码'"></input></td>
                </tr>
                <tr>
                    <td align="right">新密码:</td>
                    <td style="padding-left: 5px"><input name="newpwd" id="newpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入您的新密码',validType:['length[3,15]']"></input></td>
                </tr>
                <tr>
                    <td align="right">确认新密码:</td>
                    <td style="padding-left: 5px"><input name="rnewpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请确认您的新密码'" validType="equals['#newpwd']"></input></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="padding-left: 50px;padding-top: 10px">
                    	<a id="modifyPwdSub" href="#" class="easyui-linkbutton">提交</a>
                    	<a id="cancelMP" href="#" class="easyui-linkbutton">取消</a>
                    </td>
                </tr>
            </table>
        </form>       
</div>
</body>
</html>