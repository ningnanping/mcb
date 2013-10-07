package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 订单list
 * @author LuTang
 *
 */
public class Order extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = -4744201529709874363L;
    
    
    private int id;
    private  Date createTime;
    /**
     * 结算方式
     */
    private int settlemen;
    private double total;
    private int customerId;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public int getSettlemen()
    {
        return settlemen;
    }
    public void setSettlemen(int settlemen)
    {
        this.settlemen = settlemen;
    }
    public double getTotal()
    {
        return total;
    }
    public void setTotal(double total)
    {
        this.total = total;
    }
    public int getCustomerId()
    {
        return customerId;
    }
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    
    
    
}
