package com.xingzhe.zhzs.task.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xingzhe.zhzs.task.domain.UserDept;
import com.xingzhe.zhzs.task.domain.ZsTask;
import com.xingzhe.zhzs.task.domain.ZsTaskLzxx;
import com.xingzhe.zhzs.task.mapper.UserDeptMapper;
import com.xingzhe.zhzs.task.mapper.ZsTaskLzxxMapper;
import com.xingzhe.zhzs.task.mapper.ZsTaskMapper;

@Repository
public class ZsTaskDao {

	@Autowired
	private ZsTaskMapper zsTaskMapper;

	@Autowired
	private ZsTaskLzxxMapper zsTaskLzxxMapper;

	@Autowired
	private UserDeptMapper userDeptMapper;

	public void saveZsTask(ZsTask zsTask) {
		zsTaskMapper.saveZsTask(zsTask);
	}

	public void saveZsTaskLzxx(ZsTaskLzxx zsTaskLzxx) {
		zsTaskLzxxMapper.saveZsTaskLzxx(zsTaskLzxx);
	}
	
	public void updateZsTaskLzxx(ZsTaskLzxx zsTaskLzxx) {
		zsTaskLzxxMapper.updateZsTaskLzxx(zsTaskLzxx);
	}
	
	public int updateZsTaskState(ZsTask zsTask){
		return zsTaskMapper.updateZsTaskState(zsTask);
	}
	
	public boolean isBanLiFinish(ZsTaskLzxx zsTaskLzxx){
		if(zsTaskLzxxMapper.getBanliFinishCount(zsTaskLzxx) == zsTaskLzxxMapper.getBanliCount(zsTaskLzxx)){
			return true;
		}else {
			return false;
		}
	}
	
	public UserDept getUserDeptById(String id) {
		return userDeptMapper.getUserDeptById(id);
	}
	
	public List<UserDept> getUsersByDeptId(String id) {
		return userDeptMapper.getUsersByDeptId(id);
	}
	
	public List<UserDept> getDeptsBySjDeptId(String id) {
		return userDeptMapper.getDeptsBySjDeptId(id);
	}

	public List<ZsTask> getZsTaskForPage(Map<String, Object> map){
		return zsTaskMapper.getZsTaskForPage(map);
	}

	public int getZsTaskForTotalCount(Map<String, Object> map){
		return zsTaskMapper.getZsTaskForTotalCount(map);
	}
	
	public List<ZsTaskLzxx> getZsTaskLzxxForPage(Map<String, Object> map){
		return zsTaskLzxxMapper.getZsTaskLzxxForPage(map);
	}

	public int getZsTaskLzxxForTotalCount(Map<String, Object> map){
		return zsTaskLzxxMapper.getZsTaskLzxxForTotalCount(map);
	}
}
