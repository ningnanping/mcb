package com.xingzhe.common.domain;


import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.alibaba.fastjson.annotation.JSONField;
import com.xingzhe.framework.domain.BaseObj;

public class Tree extends BaseObj
{
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("id")
    @JsonSerialize(include=Inclusion.NON_EMPTY)
    private int id;
    
    @JsonProperty("text")
    @JsonSerialize(include=Inclusion.NON_EMPTY)
    private String text;
    
    @JsonProperty("state")
    @JsonSerialize(include=Inclusion.NON_EMPTY)
    private String state;

	@JsonProperty("iconCls")
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	private String iconCls;

	@JsonIgnore
	@JSONField(serialize=false)
	private int parentId;

	@JsonIgnore
	@JSONField(serialize=false)
	private int sortNum;

	@JsonIgnore
	@JSONField(serialize=false)
	private String treeName;
	
	@JsonProperty("children")
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	private List<Tree> listTree;
	
	@JsonProperty("attributes")
	@JsonSerialize(include=Inclusion.NON_EMPTY)
	private String  extend;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public List<Tree> getListTree() {
		return listTree;
	}

	public void setListTree(List<Tree> listTree) {
		this.listTree = listTree;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
   
}
