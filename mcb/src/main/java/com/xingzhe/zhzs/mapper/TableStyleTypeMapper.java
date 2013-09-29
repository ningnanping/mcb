package com.xingzhe.zhzs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.xingzhe.framework.domain.SelectBoxObj;
import com.xingzhe.framework.mapper.SqlMapper;

public interface TableStyleTypeMapper extends SqlMapper
{
    /**
     * 根据税种Id查找表样Id
     * @return
     */
    @Select("SELECT  BDPZ_XH, BBMC FROM (SELECT  * FROM  T_ZS_CS_XXBDPZ  ORDER BY BDPZ_XH), ( SELECT DISTINCT  BY_DM, SZ_DM FROM t_zs_sz_by) WHERE BY_DM = BDPZ_XH AND sz_dm = #{revenueTypeId}")
    @Results(value = {
            @Result(column="BDPZ_XH",property="id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
            @Result(column="BBMC",property="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
    })
    List<SelectBoxObj> getRevenueSampleByRevenueType(String revenueTypeId);

}
