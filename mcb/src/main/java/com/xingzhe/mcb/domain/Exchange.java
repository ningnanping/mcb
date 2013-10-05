package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

public class Exchange extends BaseObj
{
    /**
     * 
     */
    private static final long serialVersionUID = -1694652385911758104L;

    private int id;
    
    /**
     * 客户id
     */
    private int  customerId;
    
    /**
     * 产品id
     */
    private int productId;
    
    /**
     * 话费积分数
     */
    private int costScore;
    
    /**
     * 兑换时间
     */
    private Date actionTime;
    
    /**
     * 兑换产品规则ID
     */
    private int productCutId;

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

    public int getCostScore()
    {
        return costScore;
    }

    public void setCostScore(int costScore)
    {
        this.costScore = costScore;
    }

    public Date getActionTime()
    {
        return actionTime;
    }

    public void setActionTime(Date actionTime)
    {
        this.actionTime = actionTime;
    }

    public int getProductCutId()
    {
        return productCutId;
    }

    public void setProductCutId(int productCutId)
    {
        this.productCutId = productCutId;
    }
    
    
}
