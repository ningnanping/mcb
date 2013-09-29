package com.xingzhe.zhzs.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.xingzhe.framework.controller.BaseController;
import com.xingzhe.framework.domain.DataGrid;
import com.xingzhe.framework.domain.ResultObj;
import com.xingzhe.framework.util.DateUtil;
import com.xingzhe.framework.util.UuidUtil;
import com.xingzhe.system.execle.util.ExcelOperateUtil;
import com.xingzhe.zhzs.service.WjdrRzxsVOService;

@Controller
@RequestMapping("/dataImport")
public class DataImportController extends BaseController {

	@Autowired
	private WjdrRzxsVOService wjdrRzxsVOService;

	@Autowired
	private ExcelOperateUtil excelOperateUtil;

	@ResponseBody
	@RequestMapping(value = "/log.json")
	public Object getImportDataLog(
			@RequestParam(defaultValue = "", required = true, value = "wjlx") String wjlx,
			@RequestParam(defaultValue = "1", required = true, value = "page") int page,
			@RequestParam(defaultValue = "10", required = true, value = "rows") int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wjlx", wjlx);
		int total = wjdrRzxsVOService.getImportDataLogCount(map);
		map.put("start", getStart(page, rows));
		map.put("end", getEnd(total, page, rows));
		return new DataGrid(total, wjdrRzxsVOService.getImportDataLog(map));
	}

	@ResponseBody
	@RequestMapping(value = "/nodata.json")
	public Object getNoData(
			@RequestParam(defaultValue = "", required = true, value = "wjlx") String wjlx) {
		String xh = UuidUtil.getUUid();
		String sjfw = "12410000000";
		String lrry = "CZ00005";
		wjdrRzxsVOService.hasNodataAdd(xh, wjlx, sjfw, lrry);
		return new ResultObj(1000);
	}

	@ResponseBody
	@RequestMapping(value = "/deldata.json")
	public Object delData(
			@RequestParam(defaultValue = "", required = true, value = "xh") String xh) {
		String lrry = "CZ00005";
		wjdrRzxsVOService.delWjdrRzxsVOLog(lrry, xh);
		return new ResultObj(1000);
	}

	@ResponseBody
	@RequestMapping(value = "/addlog.html", produces = TEXT_HTML_PRODUCES)
	public String addDataLog(
			@RequestParam(defaultValue = "", required = true, value = "filetype") String wjlx,
			HttpServletRequest request) {
		BufferedInputStream in = null;
//		try {
//			if (ServletFileUpload.isMultipartContent(request)) {
//				DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
//				dff.setSizeThreshold(1024000);// 指定在内存中缓存数据大小,单位为byte
//				ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
//				sfu.setFileSizeMax(999999999);// 指定单个上传文件的最大尺寸
//				sfu.setSizeMax(999999999);// 指定一次上传多个文件的 总尺寸
//				FileItemIterator fii = sfu.getItemIterator(request);// 解析request
//				// 并返回FileItemIterator集合
//				while (fii.hasNext()) {
//					FileItemStream fis = fii.next();// 从集合中获得一个文件流
//					if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域
//						in = new BufferedInputStream(fis.openStream());// 获得文件输入流
//					}
//				}
//			}
//			System.out.println(in);
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			try {
				in=new BufferedInputStream(mf.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println(in);
		//System.out.println(fileMap);
		String xh = UuidUtil.getUUid();
		String sjfw = "12410000000";
		String lrry = "CZ00005";

		Map<String, String> systemMap = new HashMap<String, String>();
		systemMap.put("ssq",
				DateUtil.dateToStr(new Date(), DateUtil.YYYY_MM_DD));
		systemMap.put("strWjlx", wjlx);
		systemMap.put("GUID", xh);
		systemMap.put("czrydm", lrry);
		systemMap.put("sjfw", sjfw);
		Map<String, String> resultmap = null;
		try {
			resultmap = excelOperateUtil.readfile(in,systemMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		 wjdrRzxsVOService.addImportDataLog(xh, wjlx, sjfw, lrry,
		 resultmap.get("DRS"), resultmap.get("CGS"),
		 resultmap.get("SBS"), "1", "0");
		return JSON.toJSONString(new ResultObj(1000));
	}

//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	@ResponseBody
//	public String upload(HttpServletRequest request,
//			HttpServletResponse response) {
//
//		String responseStr = "";
//		String name = "";
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		// 获取前台传值
//		String[] userNames = multipartRequest.getParameterValues("userName");
//		String[] contents = multipartRequest.getParameterValues("content");
//		String userName = "";
//		String content = "";
//		if (userNames != null) {
//			userName = userNames[0];
//		}
//		if (contents != null) {
//			content = contents[0];
//		}
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		// String ctxPath =
//		// request.getSession().getServletContext().getRealPath("/")+ "\\" +
//		// "images\\";
//		String ctxPath = request.getSession().getServletContext()
//				.getRealPath("/")
//				+ "uploadtest\\";
//		// String ctxPath= HNAServletContextListener.getSYS_UPLOADPATH_PATH();
//		// String ctxPath = "http:\\\\localhost:8080\\kft\\uploadtest\\";
//		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		// String ymd = sdf.format(new Date());
//		// ctxPath += ymd + "/";
//		// 创建文件夹
//		File file = new File(ctxPath);
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//		String fileName = null;
//		String path = null;
//		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//			// 上传文件名
//			// System.out.println("key: " + entity.getKey());
//			MultipartFile mf = entity.getValue();
//			fileName = mf.getOriginalFilename();
//			// String fileExt = fileName.substring(fileName.lastIndexOf(".") +
//			// 1).toLowerCase();
//			// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//			// String newFileName = df.format(new Date()) + "_" + new
//			// Random().nextInt(1000) + "." + fileExt;
//
//			// String strEnc = DesEncrypt.aircrewhealthGetEncString(fileName);//
//			// 加密字符串,返回String的密文
//			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
//			String suffix = fileName.indexOf(".") != -1 ? fileName.substring(
//					fileName.lastIndexOf("."), fileName.length()) : null;
//			name = fileName.indexOf(".") != -1 ? fileName.substring(0,
//					fileName.lastIndexOf(".")) : null;
//			String newFileName = name + "-" + uuid
//					+ (suffix != null ? suffix : "");// 构成新文件名。
//
//			File uploadFile = new File(ctxPath + newFileName);
//			try {
//				FileCopyUtils.copy(mf.getBytes(), uploadFile);
//				path = ctxPath + newFileName;
//				responseStr = "上传成功";
//			} catch (IOException e) {
//				responseStr = "上传失败";
//				e.printStackTrace();
//			}
//		}
//
//		return path + ";" + fileName;
//	}

}
