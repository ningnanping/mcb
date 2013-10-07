package com.xingzhe.mcb.domain;

import com.xingzhe.framework.domain.BaseObj;

public class ProductPrice extends BaseObj
{
    /**
     * 
     */
    private static final long serialVersionUID = -9005318397622209252L;
    
    private int id;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    /**
     * 价格
     */
    private double price;
    
    /**
     * 会员价
     */
    private double vipPrice;
    
    
    /**
     * 客户等级
     */
    private int customerLevelId;
    
    
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public double getVipPrice()
    {
        return vipPrice;
    }
    public void setVipPrice(double vipPrice)
    {
        this.vipPrice = vipPrice;
    }
    
    public int getCustomerLevelId()
    {
        return customerLevelId;
    }
    public void setCustomerLevelId(int customerLevelId)
    {
        this.customerLevelId = customerLevelId;
    }
    
}
