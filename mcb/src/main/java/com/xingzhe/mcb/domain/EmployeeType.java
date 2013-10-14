package com.xingzhe.mcb.domain;

import com.xingzhe.framework.domain.BaseObj;

public class EmployeeType extends BaseObj
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;
    
    /**
     * 员工类型
     */
    private String name;
    
    private int isDel;

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

    public int getIsDel()
    {
        return isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }
    
    
    
}
