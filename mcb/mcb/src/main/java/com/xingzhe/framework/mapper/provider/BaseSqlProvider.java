package com.xingzhe.framework.mapper.provider;

/**
 * @Title ：
 * @Description ：
 * @author ：LuFengLiang
 * @Copyright: Copyright (c) @2012-8-10
 * @version ： 1.0
 */
public abstract class BaseSqlProvider {

	/**
	 * 生成分页查询sql
	 * 
	 * @param sql
	 *            查询的sql
	 * @return
	 */
	protected String forPage(String... sql) {
		StringBuilder s = new StringBuilder(200).append("select * from (select b.*,rownum rn from (");
		for (int i = 0; i < sql.length; i++) {
			s.append(sql[i]);
		}
		return s.append(") b)where rn >=#{start} and rn <=#{end}").toString();
	}

	protected String getTotalCount(String... sql) {
		StringBuilder s = new StringBuilder(200).append("with tmp_a as (");
		for (int i = 0; i < sql.length; i++) {
			s.append(sql[i]);
		}
		return s.append(") select count(*) as temp_count from tmp_a").toString();
	}

}
