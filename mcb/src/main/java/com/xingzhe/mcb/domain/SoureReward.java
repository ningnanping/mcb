package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 兑换规则
 * @author LuTang
 *
 */
public class SoureReward extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = -78465530595953845L;
    
    private int id;
    /**
     * 商品ID
     */
    private int productId;
    
    /**
     * 额外花费
     */
    private double extendsCost;
    
    /**
     * 兑换数量
     */
    private int count;
    
    /**
     * 耗费积分
     */
    private int soure;

    /**
     * 用户等级
     * */
    private int customerLevelId;
    
    
    /**
     * 兑换时间段
     */
    private Date startTime;
    private Date endTime;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getProductId()
    {
        return productId;
    }
    public void setProductId(int productId)
    {
        this.productId = productId;
    }
    public double getExtendsCost()
    {
        return extendsCost;
    }
    public void setExtendsCost(double extendsCost)
    {
        this.extendsCost = extendsCost;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int count)
    {
        this.count = count;
    }
    public int getSoure()
    {
        return soure;
    }
    public void setSoure(int soure)
    {
        this.soure = soure;
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

    public int getCustomerLevelId() {
        return customerLevelId;
    }

    public void setCustomerLevelId(int customerLevelId) {
        this.customerLevelId = customerLevelId;
    }


}
