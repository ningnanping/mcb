package com.xingzhe.mcb.domain;


import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 新客计划历史表
 * @author LuTang
 *
 */
/**
 * @author LuTang
 *
 */
public class NewPlanList extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 5858248009942007242L;
    
    
    private int id;
    /**
     * 用户ID
     */
    private int customerId;
    
    /**
     * 商品Id
     */
    private int productId;
    
    /**
     * 代理人
     */
    private int agentId;
    
    /**
     * 经手人
     */
    private int handId;
    
    /**
     * 商品数量
     */
    private int count;
    
    /**
     * 奖励金额
     */
    private double sum;
    
    /**
     * 新课计划类型
     */
    private int newPlanTypeId;
    
    /**
     * 产生时间
     * yyyy—MM-dd
     * 一天一条
     */
    private Date actionTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getAgentId()
    {
        return agentId;
    }

    public void setAgentId(int agentId)
    {
        this.agentId = agentId;
    }

    public int getHandId()
    {
        return handId;
    }

    public void setHandId(int handId)
    {
        this.handId = handId;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public double getSum()
    {
        return sum;
    }

    public void setSum(int sum)
    {
        this.sum = sum;
    }

    public int getNewPlanTypeId()
    {
        return newPlanTypeId;
    }

    public void setNewPlanTypeId(int newPlanTypeId)
    {
        this.newPlanTypeId = newPlanTypeId;
    }

    public Date getActionTime()
    {
        return actionTime;
    }

    public void setActionTime(Date actionTime)
    {
        this.actionTime = actionTime;
    }
    
    
    
}
