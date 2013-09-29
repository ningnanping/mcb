package com.xingzhe.system.execle.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xingzhe.system.execle.domain.Excel;
import com.xingzhe.system.execle.service.ExcelService;

@Component
public class DynamicTableUtil {

	private static final Logger logger = LoggerFactory.getLogger(DynamicTableUtil.class);

	@Autowired
	private ExcelService excelService;

	public Map<String, Object> getDynamicTableInfo(String id) {
		long l = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Excel> list = excelService.getExcel(id);
		if (list != null && list.size() != 0) {
			StringBuffer buffer = new StringBuffer(200);
			buffer.append("INSERT INTO ").append(list.get(0).getTableName()).append(" (");
			Map<Integer, Integer> dataFrom1 = new HashMap<Integer, Integer>();
			Map<String, Integer> dataFrom2 = new HashMap<String, Integer>();
			Map<Integer, String> dataFrom3 = new HashMap<Integer, String>();
			Map<String, String> dataFrom4 = new HashMap<String, String>();
			StringBuffer buffer2 = new StringBuffer(200).append(" VALUES (");
			StringBuffer sbSelect=new StringBuffer(100).append("SELECT ");
			Excel wjdrExcel = null;
			for (int i = 0; i < list.size(); i++) {
				wjdrExcel = list.get(i);
				buffer.append(wjdrExcel.getColName()).append(",");
				//拼接查询SQl
				//System.out.println(wjdrExcel.getOutCol());
				if(wjdrExcel.getOutCol()==1){
					sbSelect.append(wjdrExcel.getColName()).append(",");
					dataFrom4.put(wjdrExcel.getColName(),wjdrExcel.getColType());
				}
				
				//拼接增加SQl
				if (wjdrExcel.getDataSour() == 1) {
					buffer2.append("?");
					dataFrom1.put(i + 1, wjdrExcel.getSxh());
				} else if (wjdrExcel.getDataSour() == 2) {
					buffer2.append("?");
					if ("SSQ".equalsIgnoreCase(wjdrExcel.getMrz())) {
						dataFrom2.put("SSQ", i + 1);
					} else if ("strWjlx".equalsIgnoreCase(wjdrExcel.getMrz())) {
						dataFrom2.put("strWjlx", i + 1);
					} else if ("GUID".equalsIgnoreCase(wjdrExcel.getMrz())) {
						dataFrom2.put("GUID", i + 1);
					} else if ("czrydm".equalsIgnoreCase(wjdrExcel.getMrz())) {
						dataFrom2.put("czrydm", i + 1);
					} else if ("sjfw".equalsIgnoreCase(wjdrExcel.getMrz())) {
						dataFrom2.put("sjfw", i + 1);
					}

				} else if (wjdrExcel.getDataSour() == 3) {
					if ("STRING".equalsIgnoreCase(wjdrExcel.getColType())) {
						buffer2.append(wjdrExcel.getMrz());
					} else if ("GUID".equalsIgnoreCase(wjdrExcel.getColType())) {
						buffer2.append("?");
						dataFrom3.put(i + 1,"GUID");
					}
				}
				buffer2.append(",");
			}
			// System.out.println(buffer);
			// System.out.println(buffer2);
			StringBuffer sb = new StringBuffer(400);
			sb.append(buffer.toString().substring(0, buffer.toString().length() - 1)).append(")");
			sb.append(buffer2.toString().substring(0, buffer2.toString().length() - 1)).append(")");
			
			StringBuffer select = new StringBuffer(200);
			select.append(sbSelect.toString().substring(0, sbSelect.toString().length() - 1)).append(" FROM ").append(list.get(0).getTableName());//.append(" WHERE startDate=? and endDate=?");
			map.put("ADDSQL", sb.toString());
			map.put("SELECTSQL", select.toString());
			map.put("dataFrom1", dataFrom1);
			map.put("dataFrom2", dataFrom2);
			map.put("dataFrom3", dataFrom3);
			map.put("dataFrom4", dataFrom4);
		} else {
			return null;
		}
		long l2 = System.currentTimeMillis();
		logger.debug("转换对象耗时{}", l2 - l);
		return map;
	}

}
