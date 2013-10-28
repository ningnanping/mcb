package com.xingzhe.mcb.mapper;

import java.util.List;
import java.util.Map;

import com.xingzhe.framework.mapper.SqlMapper;
import com.xingzhe.mcb.domain.Product;

public interface ProductMapper extends SqlMapper {
	
	List<Product> selectProductListForPage(Map<String,Object> map);
	int selectProductListForTotal(Map<String,Object> map);

}
