package com.xingzhe.framework.common.response;

import com.alibaba.fastjson.JSON;

/**
 * 
 * restful 异常返回
 * 使用方式： RestfulExceptions.REQUEST_PARAMS_ERROR.setErrMess("ERRORMESSAGE").getResponse()
 * 
 * @author hoopy
 *
 */
public enum RestfulExceptions {
	
	/*
	 * 错误异常代码
	 */
	JSON_PARSE_ERROR(RestfulConstants.JSON_PARSE_ERROR),
	REQUEST_PARAMS_ERROR(RestfulConstants.REQUEST_PARAMS_ERROR),
	NOT_EXISTED_PROCESSDEFINED_ID(RestfulConstants.NOT_EXISTED_PROCESSDEFINED_ID),
	NOT_EXISTED_PROCESSINSTANCE_ID(RestfulConstants.NOT_EXISTED_PROCESSINSTANCE_ID),
	NOT_EXISTED_TASK_ID(RestfulConstants.NOT_EXISTED_TASK_ID),
	NOT_EXISTED_USR_ID(RestfulConstants.NOT_EXISTED_USR_ID),
	UNKNOW_SYS_ERROR(RestfulConstants.UNKNOW_SYS_ERROR);
	
    private int context;
    
    private String errMess;
    
    public String getResponse(){
   	 	return parseErrMess(this.context);
    }
    
    /**
     * enum构造器
     * @param context
     */
    private RestfulExceptions(int context){
   	 	this.context = context;
    }
    
    /**
     * 设置错误信息
     * @param errMess
     * @return RestfulExceptions
     */
    public RestfulExceptions setErrMess(String errMess){
    	this.errMess = errMess;
    	return this;
    }
    
    /**
     * 根据错误代码，返回restful结果
     * @param context
     * @return error response
     */
    private String parseErrMess(int context){
    	BaseResponse baseRes = new BaseResponse();
    	baseRes.setResStatus(RestfulConstants.RESTFUL_RESULT_STATUS_FAIL);
    	baseRes.setErrCode(String.valueOf(context));
    	baseRes.setErrMess(errMess);
    	baseRes.setContent(null); // 不需要content内容
    	return JSON.toJSONString(baseRes);
    }
    
    public static void main(String[] args){
   	 	System.out.println(RestfulExceptions.REQUEST_PARAMS_ERROR.setErrMess("1233333").getResponse());
    }
    
}
