package com.xingzhe.framework.domain;

import java.util.List;

public class DataGrid extends BaseObj {

	private static final long serialVersionUID = -1324387508591951971L;
	
	private int total;
	private List<?> rows;
	
	public DataGrid(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
