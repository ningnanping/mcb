<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>妈妈带宝宝</title>
<%@ include file="../base.jsp"%> 
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
.STYLE2 {color: #064684}
a{TEXT-DECORATION:none}
-->
</style>
<script type="text/javascript">


</script>
<script type="text/JavaScript">
</script>
</head>
<body class="easyui-layout" style="text-align:left">
	
	<!-- 标题区域 -->
	<div region="north" border="false" text-align: center">

		<div id="header-inner">
			<table cellpadding="0" cellspacing="0" style="width: 100%;">
				<tr>
					<td style="height: 52px;">
						<div style="color: #fff; font-size: 22px; font-weight: bold;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="46%" valign="top">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											background="<%=path%>/img/backggroup1.gif">
											<tr>

												<td width="499"><img src="<%=path%>/img/bt.jpg" width="499"
													height="69" /></td>
											</tr>
										</table>
										<table width="100%" height="29" border="0" cellpadding="0"
											cellspacing="0" background="<%=path%>/img/bg_3.gif">
											<tr>
												<td valign="bottom"><table width="390" height="27"
														border="0" cellpadding="0" cellspacing="0">
														<tr>

														</tr>
													</table></td>
											</tr>
										</table>
									</td>
									<td width="54%" valign="top" bgcolor="#a8d7ea">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="26" background=""><table width="100%"
														border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td align="center" background="<%=path%>/img/top_bg1.gif"><span
																class="STYLE2">欢迎 【${userName}】登录</span></td>
															<td width="48"><img src="<%=path%>/img/jiao.gif" width="48"
																height="26" /></td>
														</tr>
													</table></td>
											</tr>
											<tr>
												<td valign="top"><table width="100%" height="72"
														border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td background="<%=path%>/img/bg_4.gif">&nbsp;</td>
															<td width="73"><img src="<%=path%>/img/dh_0.gif" width="73"
																height="72" /></td>
															<td width="360" valign="top" background="<%=path%>/img/bg_5.gif"><table
																	width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td><a
																			href="javascript:openwin('\modifypassword.jsp');"
																			title='设置' onmouseout="MM_swapImgRestore()"
																			onmouseover="MM_swapImage('Image10','','<%=path%>/img/index1_04.gif',1)"><img
																				src="<%=path%>/img/index2_04.gif" name="Image10" width="55"
																				height="72" border="0" id="Image10" /></a></td>
																		<td><a title='打开公告'
																			onmouseout="MM_swapImgRestore()"
																			onmouseover="MM_swapImage('Image11','','<%=path%>/img/index1_05.gif',1)"><img
																				src="<%=path%>/img/index2_05.gif" name="Image11" width="59"
																				height="72" border="0" id="Image11" /></a></td>
																		<td><a title='打开帮助'
																			onmouseout="MM_swapImgRestore()"
																			onmouseover="MM_swapImage('Image12','','<%=path%>/img/index1_06.gif',1)"><img
																				src="<%=path%>/img/index2_06.gif" name="Image12" width="57"
																				height="72" border="0" id="Image12" /></a></td>
																		<td><a href="javascript:goquest()" title='退出系统'
																			onmouseout="MM_swapImgRestore()"
																			onmouseover="MM_swapImage('Image13','','<%=path%>/img/index1_07.gif',1)"><img
																				src="<%=path%>/img/index2_07.gif" name="Image13" width="66"
																				height="72" border="0" id="Image13" /></a></td>
																		<!-- javascript:openwin2('\gg.html');     javascript:gohelp()-->
																	</tr>
																</table></td>
														</tr>
													</table></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 菜单区域 -->
	<div region="west" border="false" split="true" title="系统功能"
		style="width: 250px; padding: 5px;">
		<ul class="easyui-tree" id="tree"></ul>
	</div>
	
	<!-- 内容区域 -->
	<div region="center" border="false">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"
			plain="true">
			<div title="欢迎"></div>
		</div>
	</div>

</body>
<script>
$(function(){
	$("#tree").tree({
	    url:"<%=path%>/tree/123456/index.json",
	    animate:true,
	    dnd:false,
	    checkbox:false,
	    lines:true,
	    onlyLeafCheck:true,
	    onBeforeExpand:function(node,param){
	        $("#tree").tree("options").url = "<%=path%>/tree/123456/"+ node.id + "/child.json";
	    },
		onDblClick : treeOnDbClick
	});
});

function treeOnDbClick(node) {
	 //$.messager.alert("树扩展信息内容", node.id, "info");
	 var extend=node.attributes;
	 extend=eval("("+extend+")");
	 if(extend.type=="tabs"){
		 var s="<%=path%>"+extend.url;
		 var url="<iframe scrolling='yes' frameborder='0'  src='";
		 url+=s;
		 url+="'  style='width:100%;height:620px;'></iframe>'";
		 addTab(node.text, url);
	}else{
		$.messager.alert("Info","其他功能在开发中。。。。。", "info");
	}
}
	
/*******************************************************************************
 * 添加tab
 * 
 * @param subtitle
 *            标题
 * @param url
 *            内容
 */
function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : url,
			closable : true
		});
	} else {
		$('#tabs').tabs('select', subtitle);
	}
}

</script>
</html>