<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务交办</title>
<%@ include file="/base.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/scripts/uploadify/uploadify.css" />
<script type="text/javascript" src="<%=path%>/scripts/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=path%>/scripts/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
// 日期转换
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}

// 加载datagrid
function datagrid(url){
	$('#dg').datagrid({
		width:$(this).width()*0.98,
	    rownumbers:true,
	    singleSelect:true,
	    pagination:true,
	    fit:true,
	    fitColumns:true,
	    height: 'auto',  
	    url: url,
	    pageSize : 20,
	    pageList: [10,20,30]
	 });
}

// 初始化datafrid
function initDatagrid(){
	var url = "<%=path%>/task/tasklist.json?id=<%=request.getAttribute("curId")%>";
	//alert(url);
	datagrid(url);
}

$(function(){
	//search
	$('#search').bind('click', function(){
		var state = $("#state").val();
		var title = $("#title").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		
		//alert(state + title + startDate);
		var url = "<%=path%>/task/tasklist.json?title=" + title + "&state=" + state + "&startDate=" + startDate + "&endDate=" + endDate;
		//alert(encodeURI(url));
		datagrid(encodeURI(encodeURI(url)));
	});
	
	// -----------------  begin to add  --------------------
	//add
	$('#addButton').bind('click', function(){
		$('#file_upload').uploadify('cancel', '*');
		$('#addWin').window('open');
	});

	//cancelAdd 
	$('#cancelAdd').bind('click', function(){
		$('#addWin').window('close');
	});

	// 增加交办
	$('#addform').form({
	    url:"<%=path%>/task/add",
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	var message = '交办成功！';
	    	if(data.resStatus != 'SUCCESS'){
	    		message = '交办失败：[error code:' + data.errCode + ']' + data.errMess;
	    	}
	    	
	    	$.messager.show({
				title:'任务交办',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
	    	
	    	//var rwslh = "test";
	    	
	    	$('#addWin').window('close');
	    	initDatagrid();
	    }
	});
	
	// 添加交办
	$('#addSub').bind('click', function(){
		var message = '';
		// 校验
		if($('#add_title').val() == ''){
			message = '标题  ';
		}
		/* if($('#file_upload').val() == ''){
			message += '附件  ';
		} */
		if($('#add_blryDm').val() == ''){
			message += '接受人  ';
		}
		
		if(message != ''){
			message += '不能为空!';
			$.messager.show({
				title:'任务交办提示',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
			return;
		}
		
		startUpload();
	});
	
	// -------------------- begin to 交办
	$('#jbButton').bind('click', function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			var flag = $('#flag').val();
			if(flag != row.state){
				$.messager.show({
					title:'提示信息',
					msg: '该任务已经下发！',
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
				return;
			}else {
				
				if(flag == '1' || flag == '2'){
					$('#handle_title').val(row.title);
					$('#handle_rwslh').val(row.rwslh);
					var url = '\\task\\download\\' + encodeURI(encodeURI(row.fjurl)) + '\\file';
					var createA = "<a href='<%=path%>" + url + "'>附件下载</a>";
					//$('#handle_file').apend(createA);
					$('#handle_file').html(createA);
					
					$('#handleWin').window('open');
				}else {
					return;
				}
			}
		}else {
			return;
		}
	});

	//cancelAdd 
	$('#cancelHandle').bind('click', function(){
		$('#handleWin').window('close');
	});

	// 交办 1
	$('#handleform').form({
	    url:"<%=path%>/task/handle",
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	var message = '交办成功！';
	    	if(data.resStatus != 'SUCCESS'){
	    		message = '交办失败：[error code:' + data.errCode + ']' + data.errMess;
	    	}
	    	
	    	$.messager.show({
				title:'任务交办',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
	    	
	    	$('#handleWin').window('close');
	    	initDatagrid();
	    }
	});
	
	// 添加交办
	$('#handleSub').bind('click', function(){
		
		var message = '';
		// 校验
		if($('#handle_blrswjgDm').val() == ''){
			message = '接受人税务机关  ';
		}
		if($('#handle_blryDm').val() == ''){
			message += '接受人  ';
		}
		
		if(message != ''){
			message += '不能为空!';
			$.messager.show({
				title:'任务交办提示',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
			return;
		}
		
		$('#handleform').submit();
	});

	//------------------------------ bengin to 处理
	
	$('#blButton').bind('click', function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			var flag = $('#flag').val();
			var isread = row.isread;
			
			if(flag != row.state
					|| isread == '1'){
				$.messager.show({
					title:'提示信息',
					msg: '该任务已经处理！',
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
				return;
			}else {
				
				$('#report_title').val(row.title);
				$('#report_rwslh').val(row.rwslh);
				var url = '\\task\\download\\' + encodeURI(encodeURI(row.fjurl)) + '\\file';	
				var createA = "<a href='<%=path%>" + url + "'>附件下载</a>";
				//$('#handle_file').apend(createA);
				$('#report_file').html(createA);
				$('#file_upload_report').uploadify('cancel', '*');	
				$('#reportWin').window('open');
			}
		}else {
			return;
		}
	});
	
	//cancelAdd 
	$('#cancelReport').bind('click', function(){
		$('#reportWin').window('close');
	});
	
	
	// 交办
	$('#reportform').form({
	    url:"<%=path%>/task/handle",
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	var message = '办理成功！';
	    	if(data.resStatus != 'SUCCESS'){
	    		message = '办理失败：[error code:' + data.errCode + ']' + data.errMess;
	    	}
	    	
	    	$.messager.show({
				title:'任务交办',
				msg: message,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
	    	
	    	$('#reportWin').window('close');
	    	initDatagrid();
	    }
	});
	
	// 添加交办
	$('#reportSub').bind('click', function(){
		startUpload_report();
	});
	
	// ------------------- 查看历史
	$('#ckButton').bind('click', function(){
		var row = $('#dg').datagrid('getSelected');
		if (row){
			var url = "<%=path%>/task/lzxxlist.json?id=" + row.rwslh;
			$('#his').datagrid({
				width:$(this).width()*0.98,
			    rownumbers:true,
			    singleSelect:true,
			    pagination:false,
			    fit:true,
			    fitColumns:true,
			    height: 'auto',  
			    url: url,
			 });
			$('#hisWin').window('open');
		}else {
			return;
		}
	});
	
	initDatagrid();
	//--------------------file upload
	uploadify();
	
	uploadify_report();
});

//---------------- uploadfile
function uploadify(){
    $("#file_upload").uploadify({  
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '添加附件',  
        'swf'           : '<%=path%>/scripts/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : '<%=path%>/task/upload/',  
        'auto'          : false, 
        'fileSizeLimit' : '30720KB', 
        'fileTypeExts'  : '*.doc; *.jpg; *.rar; *.txt; *.xls; *.xlsx', 
        'cancelImg' :  '<%=path%>/scripts/uploadify/uploadify-cancel.png',
        'uploadLimit' : 3, 
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){        	
        	
        	$('#add_url').val(decodeURI(data));
        	$('#addform').submit();
        },  
        'onUploadComplete':function(){  
        }  
    });  
}

function uploadify_report(){
    $("#file_upload_report").uploadify({  
        'height'        : 27,   
        'width'         : 80,    
        'buttonText'    : '添加附件',  
        'swf'           : '<%=path%>/scripts/uploadify/uploadify.swf?ver=' + Math.random(),  
        'uploader'      : '<%=path%>/task/upload/',  
        'auto'          : false, 
        'fileSizeLimit' : '30720KB', 
        'fileTypeExts'  : '*.doc; *.jpg; *.rar; *.txt; *.xls; *.xlsx', 
        'cancelImg' :  '<%=path%>/scripts/uploadify/uploadify-cancel.png',
        'uploadLimit' : 3, 
        'onUploadStart' : function(file) {
        },  
        'onUploadSuccess':function(file, data, response){  
        	$('#report_url').val(decodeURI(data));
        	$('#reportform').submit();
        },  
        'onUploadComplete':function(){  
        }  
    });  
}

function startUpload(){    
	$('#file_upload').uploadify('upload','*');  
}

function startUpload_report(){    
	$('#file_upload_report').uploadify('upload','*');  
}

</script>
</head>
<body>
<input id="flag" type="hidden" value="${flag}">
<!-- 查询页面  -->
<div class="easyui-panel" style="padding:10px;width:auto">
<table align="center">
	<tr>
		<td style="padding-left: 20px">标题:</td>
		<td><input id="title" name="title" type="text"></input></td>
		<td style="padding-left: 20px">开始时间:</td>
		<td><input class="easyui-datebox" 
			data-options="formatter:myformatter,parser:myparser,onSelect: 
				function(date){
					var y = date.getFullYear();
					var m = date.getMonth()+1;
					var d = date.getDate();
					$('#startDate').val(y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d));}"></input>
			<input id="startDate" type="hidden" />
		</td>
		<td style="padding-left: 20px">结束时间:</td>
		<td><input class="easyui-datebox" 
			data-options="formatter:myformatter,parser:myparser,onSelect: 
				function(date){
				var y = date.getFullYear();
					var m = date.getMonth()+1;
					var d = date.getDate();
				$('#endDate').val(y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d));}"></input>
			<input id="endDate" type="hidden" />
		</td>
		<td style="padding-left: 20px">任务状态：</td>
		<td style="padding-left: 10px">
			<select class="easyui-combobox" id="state" name="state" data-options="onSelect: function(ref){$('#state').val(ref.value);}">
				<option value="">所有</option>
				<option value="1">已完成</option>
				<option value="0">未完成</option>                    
			</select>
			<input id="state" type="hidden" value=""/>
		</td>
		<td style="padding-left: 20px"> 
			<a href="#" id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</td>
		<td style="padding-left: 20px">
			<c:if test="${flag=='0'}">
				<a href="#" id="addButton" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
			</c:if>
		</td>
	</tr>
</table>
</div>

<!-- dataGrid -->
<table id="dg" class="easyui-datagrid" toolbar="#toolbar" title="任务列表">
		<thead>
			<tr>
				<th data-options="field:'rwslh',hidden:true">任务ID</th>
				<th data-options="field:'state',hidden:true">当前状态</th>
				<th data-options="field:'fjurl',hidden:true">主附件</th>
				<th data-options="field:'isread',hidden:true">主附件</th>
				
				<th data-options="field:'title',width:100">任务标题</th>
				<th data-options="field:'stateStr',width:100">任务状态</th>
				<th data-options="field:'sj',width:100">办理时间</th>
			</tr>
		</thead>
</table>

<div id="toolbar">
	<c:if test="${flag=='1' or flag=='2'}">
    	<a href="javascript:void(0)" id="jbButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">交办</a>
	</c:if>
	<c:if test="${flag=='3'}">
    	<a href="javascript:void(0)" id="blButton" class="easyui-linkbutton" iconCls="icon-edit" plain="true">办理</a>
    </c:if>
    <a href="javascript:void(0)" id="ckButton" class="easyui-linkbutton" iconCls="icon-search" plain="true">查看</a>
</div>

<!-- 添加任务交办 -->
<c:if test="${flag=='0'}">
<div id="addWin" class="easyui-window" title="新增任务"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:400px;padding:10px;">
 			   
		<form id="addform" method="post" style="width:550px">
			<input type="hidden" name="hjbj" value="<c:out value="${hjbj}"/>">
            <table >
                <tr height="40px">
                    <td align="right" width="100px">标题：</td>
                    <td colspan="3" width="450px">
                    	<input id="add_title" name="title" type="text" width="350px" size="50" />
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">接受人税务机关：</td>
                    <td >
                    	<input name="blrswjgMc" type="text" class="easyui-validatebox" value="昌吉市地税征管科" readonly="readonly"></input>
                    	<input name="blrswjgDm" type="hidden" value="12410100001"></input>
                    </td>
                    <td align="right">接受人：</td>
                    <td >
                    	<input class="easyui-combobox"
                    		data-options="valueField:'rydm',textField:'combory',url:'<%=path%>/task/swjgUsers/swjgdm/12410100001',
                    		onSelect: function(rec){ $('#add_blryMc').val(rec.rymc);$('#add_blryDm').val(rec.rydm);}">
                    	<input id="add_blryMc" name="blryMc" type="hidden"></input>
                    	<input id="add_blryDm" name="blryDm" type="hidden"></input>
                    </td>
                </tr>
                 <tr height="40px">
                    <td align="right">上传附件：</td>
                    <td colspan="3">
                    		<input type="file" name="uploadify" id="file_upload" />
                    		<input name="fjurl" type="hidden" id="add_url"></input>
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">任务描述：</td>
                    <td style="padding-left: 5px" colspan="3">
                   		<textarea name="shspYj" style="width:400px;height:80px;"></textarea>
                    </td>
                </tr>
                <tr height="40px">
                    <td style="padding-left: 50px;padding-top: 10px" colspan="4" align="center">
                    	<a id="addSub" href="#" class="easyui-linkbutton">提交</a>
                    	<a id="cancelAdd" href="#" class="easyui-linkbutton">取消</a>
                    </td>
                </tr>
            </table>
        </form>       
</div>
</c:if>

<!-- 任务交办 -->
<div id="handleWin" class="easyui-window" title="任务交办"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:400px;padding:10px;">
 			   
		<form id="handleform" method="post" style="width:550px">
			<input type="hidden" name="hjbj" value="<c:out value="${hjbj}"/>">
			<input type="hidden" name="rwslh" id="handle_rwslh">
            <table >
                <tr height="40px">
                    <td align="right" width="100px">标题：</td>
                    <td colspan="3" width="450px">
                    	<input id="handle_title" type="text" width="350px" size="50" value="" disabled="disabled"/>
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">接受人税务机关：</td>
                    <td >
                    	<c:if test="${flag=='1'}">
                    	<input id="cc1" class="easyui-combobox" data-options="
        					valueField: 'jgdm',
        					textField: 'jgmc',
        					url: '<%=path%>/task/swjgUsers/sjswjgdm/12410100001',
        					onSelect: function(rec){
        						$('#handle_blrswjgMc').val(rec.jgmc);
        						$('#handle_blrswjgDm').val(rec.jgdm);
            					var urlRef = '<%=path%>/task/swjgUsers/swjgdm/' + rec.jgdm;
            					$('#cc2').combobox('clear');
            					$('#cc2').combobox('reload', urlRef);
        					}
        				">
        				<input id="handle_blrswjgMc" name="blrswjgMc" type="hidden"></input>
                    	<input id="handle_blrswjgDm" name="blrswjgDm" type="hidden"></input>
        				</c:if>
        				<c:if test="${flag=='2'}">
        					<input type="text" readonly="readonly" value="${curJgmc}"></input>
        					<input id="handle_blrswjgMc" name="blrswjgMc" type="hidden" value="${curJgmc}"></input>
                    		<input id="handle_blrswjgDm" name="blrswjgDm" type="hidden" value="${curJgdm}"></input>
        				</c:if>
                    </td>
                    <td align="right">接受人：</td>
                    <td >
                    	<c:if test="${flag=='1'}">
                    	<input id="cc2" class="easyui-combobox" data-options="valueField:'rydm',textField:'combory',
                    		onSelect: function(recRef){
        						$('#handle_blryMc').val(recRef.rymc);
        						$('#handle_blryDm').val(recRef.rydm);
        					}
        				">
        				</c:if>
        				<c:if test="${flag=='2'}">
        					<input id="cc2" class="easyui-combobox" data-options="valueField:'rydm',textField:'combory',multiple:true,
        						url:'<%=path%>/task/swjgUsers/swjgdm/' + ${curJgdm},
                   				onSelect: function(recRef){
         						$('#handle_blryMc').val($('#cc2').combobox('getText'));
        						$('#handle_blryDm').val($('#cc2').combobox('getValues'));
         					} 
        				">
        				</c:if>
                    	<input id="handle_blryMc" name="blryMc" type="hidden"></input>
                    	<input id="handle_blryDm" name="blryDm" type="hidden"></input>
                    </td>
                </tr>
                 <tr height="40px">
                    <td align="right">附件：</td>
                    <td colspan="3" id="handle_file">
                    	
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">任务描述：</td>
                    <td style="padding-left: 5px" colspan="3">
                   		<textarea name="shspYj" style="width:400px; height:80px;"></textarea>
                    </td>
                </tr>
                <tr height="40px">
                    <td style="padding-left: 50px;padding-top: 10px" colspan="4" align="center">
                    	<a id="handleSub" href="#" class="easyui-linkbutton">提交</a>
                    	<a id="cancelHandle" href="#" class="easyui-linkbutton">取消</a>
                    </td>
                </tr>
            </table>
        </form>       
</div>

<!-- 反馈 -->
<c:if test="${flag=='3'}">
<div id="reportWin" class="easyui-window" title="任务交办"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:600px;height:400px;padding:10px;">
 			   
		<form id="reportform" method="post" style="width:550px">
			<input type="hidden" name="hjbj" value="<c:out value="${hjbj}"/>">
			<input type="hidden" name="rwslh" id="report_rwslh">
            <table >
                <tr height="40px">
                    <td align="right" width="100px">标题：</td>
                    <td colspan="3" width="450px">
                    	<input id="report_title" type="text" width="350px" size="50" value="" disabled="disabled"/>
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">附件：</td>
                    <td colspan="3" id="report_file">
                    	
                    </td>
                </tr>
                <tr height="60px">
                    <td align="right">上传附件：</td>
                    <td colspan="3">
                    	<input type="file" name="uploadify" id="file_upload_report" />
                    	<input name="url" type="hidden" id="report_url"></input>
                    	<input type="hidden" name="blryDm" value="${curId}"/>
                    </td>
                </tr>
                <tr height="40px">
                    <td align="right">反馈描述：</td>
                    <td style="padding-left: 5px" colspan="3">
                   		<textarea name="shspYj" style="width:400px; height:80px;"></textarea>
                    </td>
                </tr>
                <tr height="40px">
                    <td style="padding-left: 50px;padding-top: 10px" colspan="4" align="center">
                    	<a id="reportSub" href="#" class="easyui-linkbutton">提交</a>
                    	<a id="cancelReport" href="#" class="easyui-linkbutton">取消</a>
                    </td>
                </tr>
            </table>
        </form>       
</div>
</c:if>

<!-- 查看历史 -->
<div id="hisWin" class="easyui-window" title="任务交办历史"
		data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:400px;padding:10px;">
		<table id="his" class="easyui-datagrid" title="任务列表">
		<thead>
			<tr>
				<th data-options="field:'blryMc',width:100">办理人员名称</th>
				<th data-options="field:'blrswjgMc',width:100">办理人员机构名称</th>
				<th data-options="field:'url',width:100,formatter:
					function(value,row,index){
						if(value != '' && value != null){
							var url = '\\task\\download\\' + encodeURI(encodeURI(value)) + '\\file';
							return '<a href=' + '<%=path %>' + url + '>附件</a>'
						}else {
							return '';
						}
					}">附件</th>
				<th data-options="field:'shspYj',width:100">描述</th>
				<th data-options="field:'sj',width:100">办理时间</th>
			</tr>
		</thead>
</table>
</div>
</body>
</html>