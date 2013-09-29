package com.xingzhe.system.execle.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

@Component
public class ExcelOperateUtil {

	private static final Logger log = LoggerFactory
			.getLogger(ExcelOperateUtil.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DynamicTableUtil dynamicTableUtil;

	/**
	 * @param file
	 * @param map
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Map<String, String> readfile(BufferedInputStream in, Map<String, String> systemMap)
			throws FileNotFoundException, IOException {

		long l = System.currentTimeMillis();
		final String[][] result = getData(in, 1);
		int rowLength = result.length;

		Map<String, Object> map = dynamicTableUtil
				.getDynamicTableInfo(systemMap.get("strWjlx"));
		log.debug(systemMap.toString());
		log.debug(map.toString());

		Map<Integer, Object> dataMap = new HashMap<Integer, Object>();
		String sql = (String) map.get("ADDSQL");

		/** 将需要从form1中的数据取出 */
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> dataForm1 = (Map<Integer, Integer>) map
				.get("dataFrom1");

		/**
		 * 默认值为GUID的参数取出
		 */
		@SuppressWarnings("unchecked")
		Map<Integer, String> dataFrom3 = (Map<Integer, String>) map
				.get("dataFrom3");

		/**
		 * 五个必填的参数取出对应的参数位置
		 */
		@SuppressWarnings("unchecked")
		Map<String, Integer> dataFrom2 = (Map<String, Integer>) map
				.get("dataFrom2");

		/**
		 * dataForm1 内所有参数的值
		 */
		Set<Integer> set1 = dataForm1.keySet();

		/**
		 * dataFrom3 内所有参数的值
		 */
		Set<Integer> set3 = dataFrom3.keySet();

		if (null != dataFrom2.get("SSQ")) {
			dataMap.put(dataFrom2.get("SSQ"), systemMap.get("ssq"));
		}
		if (null != dataFrom2.get("strWjlx")) {
			dataMap.put(dataFrom2.get("strWjlx"), systemMap.get("strWjlx"));
		}
		if (null != dataFrom2.get("GUID")) {
			dataMap.put(dataFrom2.get("GUID"), systemMap.get("GUID"));
		}
		if (null != dataFrom2.get("czrydm")) {
			dataMap.put(dataFrom2.get("czrydm"), systemMap.get("czrydm"));
		}
		if (null != dataFrom2.get("sjfw")) {
			dataMap.put(dataFrom2.get("sjfw"), systemMap.get("sjfw"));
		}

		int sum = 0;
		List<Integer> errorList = new ArrayList<Integer>();
		for (int i = 2; i < rowLength; i++) {

			for (Integer itemp : set1) {
				dataMap.put(itemp, result[i][dataForm1.get(itemp)]);
			}

			for (Integer integer : set3) {
				if ("GUID".equalsIgnoreCase(dataFrom3.get(integer))) {
					dataMap.put(integer, UUID.randomUUID().toString());
				} else {
					dataMap.put(integer, dataFrom3.get(integer));
				}
			}
			try {
				stat(sql, dataMap);
				sum += 1;
			} catch (Exception e) {
				log.error(e.toString());
				errorList.add(i + 2);
			}

		}

		long l1 = System.currentTimeMillis();
		log.debug("共花费{}毫秒,共{}条,成功{}条,失败{}条,失败的行数{}", l1 - l, rowLength - 2,
				sum, rowLength - 2 - sum, errorList);
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("DRS", "" + (rowLength - 2));
		resultMap.put("CGS", "" + sum);
		resultMap.put("SBS", "" + (rowLength - 2 - sum));
		return resultMap;
	}

	private int stat(final String sql, final Map<Integer, Object> map) {
		return jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				Set<Integer> set = map.keySet();
				for (Integer integer : set) {
					ps.setString(integer, (String) map.get(integer));
				}
				return ps;
			}
		});
	}

	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	private String[][] getData(BufferedInputStream in, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
//		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
//				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		// 第一行为标题，不取
		HSSFRow row = null;
		int tempRowSize = 0;
		String[] values = null;
		boolean hasValue = false;
		HSSFSheet st = null;
		String value = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			st = wb.getSheetAt(sheetIndex);
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				values = new String[rowSize];
				Arrays.fill(values, "");
				hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					value = "";
					cell = row.getCell((int) columnIndex);
					if (cell != null) {
						// 注意：一定要设成这个，否则可能会出现乱码
						// ((Object)
						// cell).setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}
					}

					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */

	private String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}
