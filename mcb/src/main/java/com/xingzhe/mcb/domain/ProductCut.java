package com.xingzhe.mcb.domain;


import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 商品提成
 * @author LuTang
 *
 */
public class ProductCut extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private int id;
    /**
     * 商品iD
     */
    private int productId;
    
    /**
     * 商品提成
     */
    private double cut;
    
    
    /**
     * 有效时间范围
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
    public double getCut()
    {
        return cut;
    }
    public void setCut(double cut)
    {
        this.cut = cut;
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
