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
     * 兑换时间段
     */
    private Date startTime;
    private Date endTime;
    
}
