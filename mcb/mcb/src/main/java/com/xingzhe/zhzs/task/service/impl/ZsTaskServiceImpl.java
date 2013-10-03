package com.xingzhe.zhzs.task.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingzhe.zhzs.task.dao.ZsTaskDao;
import com.xingzhe.zhzs.task.domain.UserDept;
import com.xingzhe.zhzs.task.domain.ZsTask;
import com.xingzhe.zhzs.task.domain.ZsTaskLzxx;
import com.xingzhe.zhzs.task.service.ZsTaskService;

@Service("zsTaskService")
public class ZsTaskServiceImpl implements ZsTaskService {

	@Autowired
	private ZsTaskDao zsTaskDao;

	public void initZsTask(ZsTask zsTask, ZsTaskLzxx zsTaskLzxx) {
		// 保存任务主表
		zsTaskDao.saveZsTask(zsTask);
		
		// 完成初始化任务交办
		zsTaskLzxx.setRwslh(zsTask.getRwslh());
		zsTaskDao.saveZsTaskLzxx(zsTaskLzxx);
	}
	
	public void handleZsTask(ZsTask zsTask, ZsTaskLzxx zsTaskLzxx) {
		String currentState = zsTaskLzxx.getHjbj();
		// 跳过 4	数据管理所长复核
		if(!"3".equals(currentState)){
			currentState = String.valueOf(Integer.parseInt(currentState) + 1);
		}else {
			currentState = String.valueOf(Integer.parseInt(currentState) + 2);
		}
		
		
		if("5".equals(currentState)) {
			// 保存交办信息，流转当前任务 -- 任务结束，只更新主表环节（前提所有环节都已经完成）
			zsTaskLzxx.setRwslh(zsTask.getRwslh());
			zsTaskLzxx.setHjbj(currentState);
			zsTaskLzxx.setIsread("1");
			zsTaskDao.updateZsTaskLzxx(zsTaskLzxx);
			
			// 更新当前任务环节
			// 办事员都完成，才可更新主表状态
			if(zsTaskDao.isBanLiFinish(zsTaskLzxx)){
				zsTask.setState(currentState);
				zsTaskDao.updateZsTaskState(zsTask);
			}
		}else {
			zsTaskLzxx.setRwslh(zsTask.getRwslh());
			zsTaskLzxx.setHjbj(currentState);
			zsTaskLzxx.setIsread("1");
			if("2".equals(currentState)){
				// 保存交办信息，流转当前任务 --交办单个
				zsTaskDao.saveZsTaskLzxx(zsTaskLzxx);
			}else{
				// 保存交办信息，流转当前任务 --交办多个
				String blryDm = zsTaskLzxx.getBlryDm();
				String blryMc = zsTaskLzxx.getBlryMc();
				
				String[] blryDmArr = blryDm.split(",");
				String[] blryMcArr = blryMc.split(",");
				
				for(int i = 0;i < blryDmArr.length;i ++ ){
					zsTaskLzxx.setBlryDm(blryDmArr[i]);
					zsTaskLzxx.setBlryMc(blryMcArr[i]);
					zsTaskDao.saveZsTaskLzxx(zsTaskLzxx);
				}
			}
			// 更新当前任务环节
			zsTask.setState(currentState);
			zsTaskDao.updateZsTaskState(zsTask);
		}
		
	}
	
	public UserDept getUserDeptById(String id) {
		return zsTaskDao.getUserDeptById(id);
	}
	
	public List<UserDept> getUsersByDeptId(String id) {
		return zsTaskDao.getUsersByDeptId(id);
	}
	
	public List<UserDept> getDeptsBySjDeptId(String id) {
		return zsTaskDao.getDeptsBySjDeptId(id);
	}

	public void addZsTaskLzxx(String id) {

	}	
	
	public List<ZsTaskLzxx> getZsTaskLzxxForPage(Map<String, Object> map){
		return zsTaskDao.getZsTaskLzxxForPage(map);
	}

	public int getZsTaskLzxxForTotalCount(Map<String, Object> map){
		return zsTaskDao.getZsTaskLzxxForTotalCount(map);
	}

	public List<ZsTask> getZsTaskForPage(Map<String, Object> map) {
		return zsTaskDao.getZsTaskForPage(map);
	}

	public int getZsTaskForTotalCount(Map<String, Object> map) {
		return zsTaskDao.getZsTaskForTotalCount(map);
	}

}
