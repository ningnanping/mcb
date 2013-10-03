package com.xingzhe.framework.controller;

public abstract class BaseController {

	/**
	 * 使用.html作为后缀的时候,并且返回是String格式的数据
	 */
	public static final String TEXT_HTML_PRODUCES = "text/html;charset=UTF-8";

	public static final String TEXT_JSON_PRODUCES = "text/json;charset=UTF-8";

	public static final String TEXT_PLAIN_PRODUCES = "text/plain;charset=UTF-8";

	public static final String APPLICATION_JSON_PRODUCES = "application/json;charset=UTF-8";

	public static final String SESSION_SLRY = "SLRY";

	// @ResponseBody
	// @RequestMapping(value = "/list.json", produces =
	// "application/json;charset=UTF-8")
	// public abstract Object getDataForPage(@RequestParam(value = "page",
	// required = false, defaultValue = "0") int page,
	// @RequestParam(value = "rows", required = false, defaultValue = "20") int
	// rows);
	//
	// @RequestMapping(value = "/save.json", produces =
	// {"application/json;charset=UTF-8"})
	// public abstract String save(Object o);
	//
	// @RequestMapping(value = "/update.json", produces =
	// {"application/json;charset=UTF-8"})
	// public abstract String update(IdentityInfo o);
	//
	// @RequestMapping(value = "/delete.json", produces =
	// {"application/json;charset=UTF-8"})
	// public abstract String del(int id);

	/**
	 * 分页起始行
	 * 
	 * @param page
	 *            页码
	 * @param rows
	 *            每页行数
	 * @return
	 */
	protected int getStart(int page, int rows) {
		int start = (page - 1) * rows + 1;
		return start;
	}

	/**
	 * 分页结束行
	 * 
	 * @param total
	 * @param page
	 * @param rows
	 * @return
	 */
	protected int getEnd(int total, int page, int rows) {
		int end = page * rows;
		if (end > (total - 1)) {
			end = total;
		}
		return end;
	}
	
	
	protected String getStartTimeHH24MISS(String startYYYYMMDD){
	    return startYYYYMMDD+" 00:00:00";
	}
	
	protected String getEndTimeHH24MISS(String endYYYYMMDD){
        return endYYYYMMDD+" 23:59:59";
    }
}
