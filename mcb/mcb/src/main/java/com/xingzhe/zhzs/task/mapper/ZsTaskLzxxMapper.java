package com.xingzhe.zhzs.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.zhzs.task.domain.ZsTaskLzxx;

public interface ZsTaskLzxxMapper extends SqlMapper {
	
	@Insert("INSERT INTO T_ZS_TASK_LZXX (RWSLH, BLRY_MC, BLRSWJG_DM, BLRSWJG_MC, BL_DATE, SHSP_YJ, HJBJ, BLRY_DM ,URL) "
			+ "VALUES (#{rwslh}, #{blryMc}, #{blrswjgDm}, #{blrswjgMc}, SYSDATE, #{shspYj}, #{hjbj}, #{blryDm},#{url})")
	void saveZsTaskLzxx(ZsTaskLzxx zsTaskLzxx);
	
	@Update("UPDATE T_ZS_TASK_LZXX SET URL = #{url},ISREAD = #{isread} WHERE BLRY_DM = #{blryDm} AND RWSLH = #{rwslh}")
	void updateZsTaskLzxx(ZsTaskLzxx zsTaskLzxx);
	
	@Select("SELECT COUNT(1) FROM 	T_ZS_TASK_LZXX	 WHERE RWSLH = #{rwslh} AND HJBJ = '3'")
	int getBanliCount(ZsTaskLzxx zsTaskLzxx);
	
	@Select("SELECT COUNT(1) FROM 	T_ZS_TASK_LZXX	 WHERE RWSLH = #{rwslh} AND HJBJ = '3' AND ISREAD = '1'")
	int getBanliFinishCount(ZsTaskLzxx zsTaskLzxx);

	List<ZsTaskLzxx> getZsTaskLzxxForPage(Map<String,Object> map);
	
	int  getZsTaskLzxxForTotalCount(Map<String,Object> map);
}
