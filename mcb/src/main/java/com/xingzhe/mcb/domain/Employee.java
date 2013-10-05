package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

public class Employee extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 6300965268070769377L;
    
    private int id;
    
    /**
     * 职工名称
     */
    private String name;
    
    /**
     * 性别
     */
    private int sex;
    
    /**
     * 电话号码
     */
    private String phoneNumber;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 是否删除
     */
    private int isDel;
    
    /**
     * 用户Id
     */
    private int userId;
    
    /**
     * 职工类型
     */
    private int typeId;

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

    public int getSex()
    {
        return sex;
    }

    public void setSex(int sex)
    {
        this.sex = sex;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public int getIsDel()
    {
        return isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }
    
    
    
    
}
