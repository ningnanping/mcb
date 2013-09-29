package com.xingzhe.zhzs.service;

import java.util.List;
import java.util.Map;

import com.xingzhe.framework.domain.CallableStatementObj;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;

public interface RegisterDataService
{
    /**
     * 分页查出的结果集
     * @param map
     * @return
     */
    List<WjdrRzxsVO> sjdTyhjcxForPage(Map<String,Object> map);
    
    /**
     * 统计总数
     * @param map
     * @return
     */
    int sjdTyhjcxTotalCount(Map<String,Object> map);
    
    /**
     * 统一户籍
     * @param wjlx 文件类型
     * @param pcxh 序号
     * @param czrydm 操作人员代码
     * @param swjdm 
     * @return
     */
    CallableStatementObj unifiedRegistration (String wjlx,String bdwjlx,String pcxh,String czrydm,String swjdm);
}
