package com.xingzhe.mcb.domain;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 绩效明细
 * @author LuTang
 *
 */
public class PerformanceList extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 3443099921875970633L;
    
    private int id;
    
    /**
     * 绩效Id
     */
    private int performanceId;
    
    /**
     * 那种提成 
     */
    private int performanceType;
    
    /**
     * 提成金额
     */
    private double monery;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPerformanceId()
    {
        return performanceId;
    }

    public void setPerformanceId(int performanceId)
    {
        this.performanceId = performanceId;
    }

    public int getPerformanceType()
    {
        return performanceType;
    }

    public void setPerformanceType(int performanceType)
    {
        this.performanceType = performanceType;
    }

    public double getMonery()
    {
        return monery;
    }

    public void setMonery(double monery)
    {
        this.monery = monery;
    }
    
    
    
}
