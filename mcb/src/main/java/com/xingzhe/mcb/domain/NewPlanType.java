package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

public class NewPlanType extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 6987237961860435731L;
    
    private int id;
    
    /**
     * 商品Id
     */
    private int prodectId;
    
    /**
     * 提成金额
     */
    private double cut;
    
    /**
     * 不间断次数
     */
    private int notLostCount;
    
    /**
     * 开始时间
     */
    private Date startTime;
    
    /**
     * 结束时间
     */
    private Date endTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getProdectId()
    {
        return prodectId;
    }

    public void setProdectId(int prodectId)
    {
        this.prodectId = prodectId;
    }

    public double getCut()
    {
        return cut;
    }

    public void setCut(double cut)
    {
        this.cut = cut;
    }

    public int getNotLostCount()
    {
        return notLostCount;
    }

    public void setNotLostCount(int notLostCount)
    {
        this.notLostCount = notLostCount;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    
    
    
}
