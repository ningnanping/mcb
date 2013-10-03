package com.xinzhe.framework.common;

public class RestfulConstants {
	
	public static final String RESTFUL_RESULT_STATUS_SUCCESS = "SUCCESS";
	public static final String RESTFUL_RESULT_STATUS_FAIL = "FAIL";
	
	/*
	 * ========================================
	 * 请求参数异常 100 -- 199
	 * ========================================
	 */
	/**
	 * JSON解析错误
	 */
	public static final int JSON_PARSE_ERROR = 100;
	
	/**
	 * 请求参数错误
	 */
	public static final int REQUEST_PARAMS_ERROR = 101;
	
	/*
	 * ========================================
	 * 工作流处理异常 200 -- 299
	 * ========================================
	 */
	/**
	 * 流程定义不存在
	 */
	public static final int NOT_EXISTED_PROCESSDEFINED_ID = 200;
	
	/**
	 * 流程实例不存在
	 */
	public static final int NOT_EXISTED_PROCESSINSTANCE_ID = 201;
	
	/**
	 * 流程任务不存在或已处理
	 */
	public static final int NOT_EXISTED_TASK_ID = 202;
	
	/**
	 * 流程用户不存在
	 */
	public static final int NOT_EXISTED_USR_ID = 203;
	
	/*
	 * ========================================
	 * 系统其他异常 500 -- 599
	 * ========================================
	 */
	/**
	 * 不可知的系统异常
	 */
	public static final int UNKNOW_SYS_ERROR = 500;
	

}
