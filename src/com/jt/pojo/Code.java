package com.jt.pojo;

import java.util.List;

import project.pojo.Bpojo;

/**
 * 功能状态对象，对应t_code表
 *
 */
public class Code extends Bpojo{
	protected String code;//代码
	protected String codename;//代码名称
	protected String description;//代码含义
	
	private List<CodeLookup> lookupList ;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	


	public List<CodeLookup> getLookupList() {
		return lookupList;
	}
	public void setLookupList(List<CodeLookup> lookupList) {
		this.lookupList = lookupList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Code other = (Code) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	
	
	@Override
	public Code clone() throws CloneNotSupportedException {
		return (Code) super.clone();
	}
	
}
