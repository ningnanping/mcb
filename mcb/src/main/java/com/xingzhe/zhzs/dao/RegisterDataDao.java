package com.xingzhe.zhzs.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xingzhe.framework.domain.CallableStatementObj;
import com.xingzhe.zhzs.domain.WjdrRzxsVO;
import com.xingzhe.zhzs.mapper.WjdrRzxsVOMapper;

@Repository
public class RegisterDataDao
{
    
    private static final Logger log = LoggerFactory.getLogger(WjdrRzxsVODao.class);

    @Autowired
    private WjdrRzxsVOMapper wjdrRzxsVOMapper;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 登记数据对比分页查询
     * 
     * @param map wjlx 文件类型 startDate 开始时间 endDate 结束时间 sfjg 是否加工 sjfw 编号
     * 
     *            start 开始行数 end 结束行数
     * @return
     */
    public List<WjdrRzxsVO> sjdTyhjcxForPage(Map<String, Object> map)
    {
        return wjdrRzxsVOMapper.sjdTyhjcxForPage(map);
    }
    
    /**
     * 登记数据对比分页查询
     * 
     * @param map wjlx 文件类型 startDate 开始时间 endDate 结束时间 sfjg 是否加工 sjfw 编号
     * 
     *            start 开始行数 end 结束行数
     * @return
     */
    public int sjdTyhjcxTotalCount(Map<String, Object> map)
    {
        return wjdrRzxsVOMapper.sjdTyhjcxTotalCount(map);
    }
    
    /**
     * 统一户籍
     */
    public CallableStatementObj  unifiedRegistration (final String wjlx,final String bdwjlx,final String pcxh,final String czrydm,final String swjdm){
        return jdbcTemplate.execute(new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {

                String storedProc = "{call P_JP_ZHZS_TYHJ_KZ(?,?,?,?,?,?,?,?)}";
                log.debug(storedProc);
                CallableStatement cs = con.prepareCall(storedProc);
                cs.setString(1, pcxh);// 设置输入参数的值
                cs.setString(2, "");// 设置输入参数的值
                cs.setString(3, wjlx);// 设置输入参数的值
                cs.setString(4, bdwjlx);// 设置输入参数的值
                cs.setString(5, swjdm);// 设置输入参数的值
                cs.setString(6, czrydm);// 设置输入参数的值
                cs.registerOutParameter(7, OracleTypes.VARCHAR);// 注册输出参数的类型
                cs.registerOutParameter(8, OracleTypes.VARCHAR);// 注册输出参数的类型
                return cs;
            }
        }, new CallableStatementCallback<CallableStatementObj>() {
            public CallableStatementObj doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.execute();
                return new CallableStatementObj(cs.getString(7),cs.getString(8));
            }
        });
    }
}
