package com.xingzhe.framework.domain;

public class CallableStatementObj extends BaseObj
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 存储过程执行后返回代码
     */
    private String code;
    
    /**
     * 返回执行后的集
     */
    private String desc;

    
    
    public CallableStatementObj(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
}
