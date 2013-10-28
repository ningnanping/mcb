package com.xingzhe.mcb.domain;

import java.util.List;

import com.xingzhe.framework.domain.BaseObj;

/**
 * 商品列表
 * 
 * @author LuTang
 * 
 */
public class Product extends BaseObj {

	/**
     * 
     */
	private static final long serialVersionUID = -3629174520066479144L;

	private int id;
	private String name;

	/**
	 * 商品的条形码 唯一识别码
	 */
	private String uuid;

	private List<ProductPrice> listPrice;
	
	/**
	 * 价格
	 */
	private double price;

	private String priceText;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<ProductPrice> getListPrice() {
		return listPrice;
	}

	public void setListPrice(List<ProductPrice> listPrice) {
		this.listPrice = listPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

}
