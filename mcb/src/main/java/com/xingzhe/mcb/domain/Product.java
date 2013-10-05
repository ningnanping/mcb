package com.xingzhe.mcb.domain;

import com.xingzhe.framework.domain.BaseObj;

public class Product extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = -3629174520066479144L;
    
    
    private int id;
    private String name;
    
    /**
     * 价格
     */
    private double price;
    
    /**
     * 会员价
     */
    private double vipPrice;
    
    /**
     * 商品的条形码 唯一识别码
     */
    private String uuid;
    
    private int customerLevelId;
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
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
    public String getUuid()
    {
        return uuid;
    }
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
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
