package com.xinzhe.framework.common;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {
	
	// RESPONSE 状态
	private String resStatus= RestfulConstants.RESTFUL_RESULT_STATUS_SUCCESS;
	
	// 错误代码
	private String errCode= "";
	
	// 错误信息
	private String errMess= "";
	
	private Map<String,Object> content;

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMess() {
		return errMess;
	}

	public void setErrMess(String errMess) {
		this.errMess = errMess;
	}

	public Map<String, Object> getContent() {
		if(content == null){
			content = new HashMap<String,Object>();
		}
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

}
