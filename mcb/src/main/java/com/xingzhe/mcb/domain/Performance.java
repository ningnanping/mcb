package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 绩效列表
 * @author LuTang
 *
 */
public class Performance extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 8376238992239289603L;
    
    private int id;
    private int employeeId;
    /**
     * 绩效时间
     */
    private Date createTime;
    
    /**
     * 绩效提成
     */
    private double sumMoney;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public double getSumMoney()
    {
        return sumMoney;
    }
    public void setSumMoney(double sumMoney)
    {
        this.sumMoney = sumMoney;
    }
    
}
