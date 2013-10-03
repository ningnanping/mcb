package com.xingzhe.zhzs.task.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.common.BaseResponse;
import com.xingzhe.framework.common.RestfulExceptions;
import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.framework.domain.DataGrid;
import com.xingzhe.zhzs.task.domain.UserDept;
import com.xingzhe.zhzs.task.domain.ZsTask;
import com.xingzhe.zhzs.task.domain.ZsTaskLzxx;
import com.xingzhe.zhzs.task.domain.ZsTaskSearch;
import com.xingzhe.zhzs.task.service.ZsTaskService;

@Controller
@RequestMapping("/task")
public class ZsTaskController extends BaseController {

	@Autowired
	private ZsTaskService zsTaskService;

	/**
	 * 交办开始界面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/start")
	public String start(@RequestParam(defaultValue = "", required = true, value = "id") String id, HttpServletRequest request) {
		if (StringUtils.isBlank(id)) {
			return "ERROR";
		} else {
			UserDept userInfo = zsTaskService.getUserDeptById(id);
			if (userInfo == null) {
				return "ERROR";
			}
			request.getSession().setAttribute(SESSION_SLRY, userInfo);
			// 当前 综合治税办公室 12410000000
			if ("12410000000".equalsIgnoreCase(userInfo.getJgdm())) {
				request.setAttribute("flag", "0");
				request.setAttribute("hjbj", "0");
			}
			// 征管科
			else if ("12410100001".equalsIgnoreCase(userInfo.getJgdm())) {
				request.setAttribute("flag", "1");
				request.setAttribute("hjbj", "1");
			}
			// 地税所
			else if ("12410100001".equalsIgnoreCase(userInfo.getSjswjgdm()) && userInfo.getRymc().indexOf("所长") > -1) {
				request.setAttribute("flag", "2");
				request.setAttribute("hjbj", "2");

				request.setAttribute("curJgdm", userInfo.getJgdm());
				request.setAttribute("curJgmc", userInfo.getJgmc());
			}
			// 税管员
			else {
				request.setAttribute("flag", "3");
				request.setAttribute("hjbj", "3");
			}
			request.setAttribute("curId", id);
			return "resourse/jsp/task/zsTaskGrid";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/tasklist.json", produces = APPLICATION_JSON_PRODUCES)
	public Object getTaskDataForPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @ModelAttribute("zsTaskSearch") ZsTaskSearch zsTaskSearch, HttpServletRequest request) {

		UserDept userDept = (UserDept) request.getSession().getAttribute(SESSION_SLRY);
		String blry = null;
		if (userDept != null) {
			blry = userDept.getRydm();
		}

		if (StringUtils.isBlank(blry)) {
			return "ERROR";
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("blry", blry);
			
			//Date startDate = DateUtil.strToDate(zsTaskSearch.getStartDate(), DateUtil.YYYY_MM_DD);
			//Date endDate = DateUtil.strToDate(zsTaskSearch.getEndDate(), DateUtil.YYYY_MM_DD);
			
			if(StringUtils.isNotEmpty(zsTaskSearch.getStartDate())){
				map.put("startDate", zsTaskSearch.getStartDate() + " 00:00:00");
			}
			if(StringUtils.isNotEmpty(zsTaskSearch.getEndDate())){
				map.put("endDate", zsTaskSearch.getEndDate() + " 23:59:59");
			}
			
//			map.put("startDate", zsTaskSearch.getStartDate() + " 00:00:01");
//			map.put("endDate", zsTaskSearch.getEndDate() + " 24:00:00");
			try {
				if (StringUtils.isNotBlank(zsTaskSearch.getTitle())) {
					map.put("title", URLDecoder.decode(zsTaskSearch.getTitle(), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("state", zsTaskSearch.getState());
			int total = zsTaskService.getZsTaskForTotalCount(map);
			map.put("start", getStart(page, rows));
			map.put("end", getEnd(total, page, rows));
			return new DataGrid(total, zsTaskService.getZsTaskForPage(map));
		}
	}

	/**
	 * 新增交办
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = TEXT_HTML_PRODUCES)
	public String add(@ModelAttribute("zsTask") ZsTask zsTask, @ModelAttribute("zsTaskLzxx") ZsTaskLzxx zsTaskLzxx, HttpServletRequest request) {

		UserDept userDept = (UserDept) request.getSession().getAttribute(SESSION_SLRY);
		String jgdm = null;
		if (userDept != null) {
			jgdm = userDept.getJgdm();
		}

		if (!"12410000000".equalsIgnoreCase(jgdm)) {
			return RestfulExceptions.UNKNOW_SYS_ERROR.getResponse();
		} else {
			zsTask.setSlryDm(userDept.getRydm());
			zsTask.setSlswjgDm(userDept.getSjswjgdm());
			zsTask.setState("0");

			ZsTaskLzxx zsTaskLzxxInit = new ZsTaskLzxx();
			zsTaskLzxxInit.setBlryDm(userDept.getRydm());
			zsTaskLzxxInit.setBlryMc(userDept.getRymc());
			zsTaskLzxxInit.setBlrswjgDm(userDept.getJgdm());
			zsTaskLzxxInit.setBlrswjgMc(userDept.getJgmc());
			zsTaskLzxxInit.setShspYj(zsTaskLzxx.getShspYj());
			zsTaskLzxxInit.setUrl(zsTask.getFjurl());
			zsTaskLzxxInit.setHjbj("0");

			// 初始化任务
			zsTaskService.initZsTask(zsTask, zsTaskLzxxInit);

			// 正常流转
			zsTaskService.handleZsTask(zsTask, zsTaskLzxx);

			BaseResponse br = new BaseResponse();
			return JSON.toJSONString(br);
		}
	}

	/**
	 * 流转交办
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/handle", method = RequestMethod.POST, produces = TEXT_HTML_PRODUCES)
	public String handle(@ModelAttribute("zsTask") ZsTask zsTask, @ModelAttribute("zsTaskLzxx") ZsTaskLzxx zsTaskLzxx, HttpServletRequest request) {
		// 正常流转
		zsTaskService.handleZsTask(zsTask, zsTaskLzxx);
		
		BaseResponse br = new BaseResponse();
		return JSON.toJSONString(br);
	}

	/**
	 * 获取机构人员信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/swjgUsers/swjgdm/{swjgDm}", produces = APPLICATION_JSON_PRODUCES)
	public String queryDeptUsers(@PathVariable("swjgDm") String swjgDm, HttpServletRequest request) {
		List<UserDept> userDeptList = zsTaskService.getUsersByDeptId(swjgDm);
		return JSON.toJSONString(userDeptList);
	}

	/**
	 * 获取机构信息 -- 根据上次机构代码
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/swjgUsers/sjswjgdm/{sjswjgDm}", produces = APPLICATION_JSON_PRODUCES)
	public String queryDepts(@PathVariable("sjswjgDm") String sjswjgDm, HttpServletRequest request) {
		List<UserDept> userDeptList = zsTaskService.getDeptsBySjDeptId(sjswjgDm);
		return JSON.toJSONString(userDeptList);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "uploadtest\\";
		// 创建文件夹
		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String newFileName = null;
		String encodeFileName = null;
		String name=null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
			String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
			name = fileName.indexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf(".")) : null;
			newFileName = name + uuid + (suffix != null ? suffix : "");// 构成新文件名。
			//newFileName = name + (suffix != null ? suffix : "");// 构成新文件名。
			
			File uploadFile = new File(ctxPath + newFileName);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			encodeFileName = URLEncoder.encode(newFileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeFileName;
		//return "\\uploadtest\\" + newFileName
	}
	
	@RequestMapping(value = "/download/{fname}/file")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fname") String fname) {
		try {		
			String fileName = URLDecoder.decode(fname, "UTF-8");
			
			String ctxPath = request.getSession().getServletContext().getRealPath("/") + "uploadtest\\";
			
			// path是指欲下载的文件的路径。
			File file = new File(ctxPath + fileName);
			// 取得文件名。
//			String filename = file.getName();
			// 取得文件的后缀名。
//			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(ctxPath + fileName));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" 
					+ new String(fileName.getBytes("gbk"),"iso-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	@ResponseBody
	@RequestMapping(value = "/lzxxlist.json", produces = APPLICATION_JSON_PRODUCES)
	public Object getDataForPage(@RequestParam(defaultValue = "", required = true, value = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rwslh", id);
		//int total = zsTaskService.getZsTaskLzxxForTotalCount(map);
		return new DataGrid(0, zsTaskService.getZsTaskLzxxForPage(map));
	}

	public static void main(String[] args) {
		System.out.println("CJDS00002".substring("CJDS00001".length() - 1, "CJDS00001".length()));
	}
}
