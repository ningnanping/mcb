package com.xingzhe.zhzs.task.service;

import java.util.List;
import java.util.Map;

import com.xingzhe.zhzs.task.domain.UserDept;
import com.xingzhe.zhzs.task.domain.ZsTask;
import com.xingzhe.zhzs.task.domain.ZsTaskLzxx;

public interface ZsTaskService {
	
	/**
	 * 初始化任务
	 * @param zsTask
	 * @param zsTaskLzxx
	 */
	public void initZsTask(ZsTask zsTask,ZsTaskLzxx zsTaskLzxx);
	
	/**
	 * 处理任务
	 * @param zsTask
	 * @param zsTaskLzxx
	 */
	public void handleZsTask(ZsTask zsTask,ZsTaskLzxx zsTaskLzxx);
	
	/**
	 * 获取用户机构信息
	 * @param id
	 * @return
	 */
	public UserDept getUserDeptById(String id);
	
	/**
	 * 获取部门用户信息
	 * @param id
	 * @return
	 */
	public List<UserDept> getUsersByDeptId(String id);
	
	/**
	 * 获取机构信息 -- 根据上级机关代码
	 * @param id
	 * @return
	 */
	public List<UserDept> getDeptsBySjDeptId(String id);
	
	/**
	 * 查询任务列表
	 * @param map
	 * @return
	 */
	List<ZsTask> getZsTaskForPage(Map<String, Object> map);
	
	/**
	 * 任务总数
	 * @param map
	 * @return
	 */
	int getZsTaskForTotalCount(Map<String, Object> map);
	
	/**
	 * 查询任务流转信息
	 * @param map
	 * @return
	 */
	List<ZsTaskLzxx> getZsTaskLzxxForPage(Map<String, Object> map);
	
	/**
	 * 任务流转信息总数
	 * @param map
	 * @return
	 */
	int getZsTaskLzxxForTotalCount(Map<String, Object> map);

}
