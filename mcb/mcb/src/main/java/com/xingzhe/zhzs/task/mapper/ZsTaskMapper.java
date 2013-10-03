package com.xingzhe.zhzs.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.zhzs.task.domain.ZsTask;

public interface ZsTaskMapper extends SqlMapper {
	
	void saveZsTask(ZsTask zsTask);
	
	@Update("UPDATE T_ZS_TASK SET STATE = #{state} WHERE RWSLH = #{rwslh}")
	int updateZsTaskState(ZsTask zsTask); 
	
	int  getZsTaskForTotalCount(Map<String,Object> map);
	
	List<ZsTask> getZsTaskForPage(Map<String,Object> map);
	
}
