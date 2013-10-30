package com.xingzhe.mcb.domain;

import java.util.Date;

import com.xingzhe.framework.domain.BaseObj;

public class Customer extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = -1208030727104403725L;
    
    private String  vipId;
    
    private int id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 客户等级
     */
    private int customerLevelId;
    
    /**
     * 用于关联 是否登陆
     */
    private int userId;
    
    /**
     * 性别
     */
    private int sex;
    
    /**
     * 是否删除
     */
    private int isDel;
    
    /**
     * 电话号码
     */
    private String phoneNumber;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * email
     */
    private String email;
    
    /**
     * 代理人
     */
    private int agentId;
    
    /**
     * 消费积分
     */
    private int score;
    
    /**
     * 经手人
     */
    private int handId;
    
    /**
     * 宝贝月份
     */
    private int babyMonth;
    
    private String agentText;
    
    private String handText;
    
    private String customerLevelIdText;
    
    private String  sexText;
    
    private String createTimeText;

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

    public int getCustomerLevelId()
    {
        return customerLevelId;
    }

    public void setCustomerLevelId(int customerLevelId)
    {
        this.customerLevelId = customerLevelId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getSex()
    {
        return sex;
    }

    public String getSexText() {
		return sexText;
	}

	public void setSexText(String sexText) {
		this.sexText = sexText;
	}

	public void setSex(int sex)
    {
        this.sex = sex;
    }

    public int getIsDel()
    {
        return isDel;
    }

    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getAgentId()
    {
        return agentId;
    }

    public void setAgentId(int agentId)
    {
        this.agentId = agentId;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getHandId()
    {
        return handId;
    }

    public void setHandId(int handId)
    {
        this.handId = handId;
    }

    public int getBabyMonth()
    {
        return babyMonth;
    }

    public void setBabyMonth(int babyMonth)
    {
        this.babyMonth = babyMonth;
    }

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public String getAgentText() {
		return agentText;
	}

	public void setAgentText(String agentText) {
		this.agentText = agentText;
	}

	public String getHandText() {
		return handText;
	}

	public void setHandText(String handText) {
		this.handText = handText;
	}

	public String getCustomerLevelIdText() {
		return customerLevelIdText;
	}

	public void setCustomerLevelIdText(String customerLevelIdText) {
		this.customerLevelIdText = customerLevelIdText;
	}

	public String getCreateTimeText() {
		return createTimeText;
	}

	public void setCreateTimeText(String createTimeText) {
		this.createTimeText = createTimeText;
	}
    
    
}
