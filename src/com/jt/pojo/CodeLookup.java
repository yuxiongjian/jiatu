package com.jt.pojo;

import project.pojo.Bpojo;



/**
 * 功能状态值，对应t_codelkup表
 *
 */
public class CodeLookup extends Bpojo{
	protected String code;//代码
	protected String value;//值
	protected String description;//值含义

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		CodeLookup other = (CodeLookup) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodeLookup [code=" + code + ", value=" + value + ", description=" + description + "]";
	}

	@Override
	public CodeLookup clone() throws CloneNotSupportedException {
		return (CodeLookup) super.clone();
	}

}
