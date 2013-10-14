<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>外部数据导入</title>
    <%@ include file="/base.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/zhzs.css">
</head>
<body>
<table id="dg" toolbar="#toolbar">
    <thead>
    <tr>
        <th data-options="field:'pcxh',align:'center',width:200,hidden:true"></th>
        <th data-options="field:'bmmc',align:'center',width:200">部门</th>
        <th data-options="field:'wjlx',align:'center',width:200">文件类型</th>
        <th data-options="field:'drsj',align:'center',width:200">文件导入时间</th>
        <th data-options="field:'drs',align:'center',width:200">文件总数</th>
        <th data-options="field:'cgs',align:'center',width:200">导入成功数</th>
        <th data-options="field:'sbs',align:'center',width:200">导入失败数</th>
        <th data-options="field:'sjfw',align:'center',width:200">导入机关</th>
        <th data-options="field:'lrry',align:'center',width:200">导入人员</th>
        <th data-options="field:'pdzt',align:'center',width:200">状态</th>
    </tr>
    </thead>
</table>

<div id="toolbar">
    <div><h2 align="center">外部数据导入</h2></div>
    <div>
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="importData()">导入</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryLogData()">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="hasNodata()">本月无数据</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="queryDetail()">查询明细</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="destroyData()">作废</a>
    </div>
    <br/>

    <div>
        <fieldset>
            <legend>操作区</legend>
            <form action="<%=path %>/dataImport/addlog.html" method="post" enctype="multipart/form-data" id="fileFrom">

                <label>部门:</label> <select class="easyui-combobox"
                                           name="dept" style="width: 200px;" id="dept">
            </select>

                <label>文件类型:</label> <select id="filetype" class="easyui-combobox"
                                             name="filetype" style="width: 200px;">
            </select>

                <label>文件路径:</label> <input type="file" name="uploadify" id="file_upload"/>
            </form>
        </fieldset>
    </div>
    <br/>
</div>
</body>

<script type="text/javascript">

    $(document).ready(function () {
        $("#dept").combobox({
            url: '<%=path%>/common/dept.json',
            valueField: 'id',
            textField: 'name',
            onSelect: function (rec) {
                var id = rec.id;
                var url = "<%=path%>/common/dept/" + id + "/filetype.json";
                $("#filetype").combobox({
                    url: url,
                    valueField: 'id',
                    textField: 'name',
                    onSelect: function (rec) {
                        var fid = rec.id;
                        if (fid != null && fid != "" && fid != "null") {
                            $('#dg').datagrid('reload', { wjlx: fid});
                        }
                    }
                });
            }
        });

    });


    $('#dg').datagrid({
        width: $(this).width() * 0.98,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        fit: true,
        fitColumns: true,
        height: 'auto',
        url: "<%=path%>/dataImport/log.json",
        pageSize: 20,
        pageList: [15, 20, 25, 35]
    });


    //查询
    function queryLogData() {
        var fileType = $('#filetype').combobox('getValue');

        if (fileType != null && fileType != "") {
            $('#dg').datagrid('reload', { wjlx: fileType});
        } else {
            $.messager.alert('Error', "请选择文件类型", 'Error');
        }
    }

    //本月无数据
    function hasNodata() {
        var fileType = $('#filetype').combobox('getValue');

        if (fileType != null && fileType != "") {

            $.post("<%=path%>/dataImport/nodata.json",
                    { wjlx: fileType}, function () {
                        $('#dg').datagrid('reload', { wjlx: fileType});
                    }, 'JSON');

        } else {
            $.messager.alert('Error', "请选择文件类型", 'Error');
        }
    }


    //作废数据
    function destroyData() {
        var row = $('#dg').datagrid('getSelected');
        var fileType = $('#filetype').combobox('getValue');
        if (row) {
            //alert(row.zt);
            var zt = row.zt;
            if (zt == 4 || zt == "4" || zt == 5 || zt == "5") {
                $.messager.alert('Error', "以统一户籍户籍的数据不能作废", 'Error');
            } else {
                $.post("<%=path%>/dataImport/deldata.json",
                        {xh: row.pcxh},
                        function (result) {
                            if (result.code == 1000) {
                                $('#dg').datagrid('reload', { wjlx: fileType});
                                setTimeout(function () {
                                    $.messager.show({
                                        title: 'INFO',
                                        msg: result.mess
                                    });
                                }, 1000);
                            } else {
                                $.messager.show({
                                    title: 'Error',
                                    msg: result.mess
                                });
                            }
                        }, 'json');
            }
        }
    }

    function importData() {
        $('#fileFrom').form('submit', {
            success: function (result) {
                result = eval("(" + result + ")");
                if (result.code == 1000) {
                    $.messager.alert('Info', result.code, 'INFO');
                    $('#fileFrom').form('clear');
                } else {
                    $.messager.alert('Error', result.code, 'Error');
                }
            }
        });

    }

</script>
</html>