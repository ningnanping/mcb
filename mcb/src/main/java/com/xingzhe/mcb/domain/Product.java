package com.xingzhe.mcb.domain;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 商品列表
 * @author LuTang
 *
 */
public class Product extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = -3629174520066479144L;
    
    
    private int id;
    private String name;
    
    /**
     * 商品的条形码 唯一识别码
     */
    private String uuid;
    
      
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
    
    public String getUuid()
    {
        return uuid;
    }
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
   
}
