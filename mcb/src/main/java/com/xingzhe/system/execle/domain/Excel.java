package com.xingzhe.system.execle.domain;

import com.xingzhe.framework.domain.BaseObj;

public class Excel extends BaseObj{

	private static final long serialVersionUID = 2863035117774061359L;
	private String id;
	private String tableName;
	private String colName;
	private int sxh;
	private String mrz;
	private int dataSour;
	private String colType;
	private String dateFormat;
	private int outCol;
	public int getOutCol() {
		return outCol;
	}
	public void setOutCol(int outCol) {
		this.outCol = outCol;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public int getSxh() {
		return sxh;
	}
	public void setSxh(int sxh) {
		this.sxh = sxh;
	}
	public String getMrz() {
		return mrz;
	}
	public void setMrz(String mrz) {
		this.mrz = mrz;
	}
	public int getDataSour() {
		return dataSour;
	}
	public void setDataSour(int dataSour) {
		this.dataSour = dataSour;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
