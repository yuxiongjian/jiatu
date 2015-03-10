package com.jt.pojo;

import project.pojo.Bpojo;

public class CustomerContact extends Bpojo {
	/**
	 * 
	 */
	public static final long serialVersionUID = CustomerContact.class.hashCode();
	public java.lang.Integer getContactid() {
		return contactid;
	}
	public void setContactid(java.lang.Integer contactid) {
		this.contactid = contactid;
	}
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getGender() {
		return gender;
	}
	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}
	public java.lang.String getPassword() {
		return password;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	protected java.lang.Integer contactid; // lk_index  联系人序号
	protected java.lang.String email; // lk_email  Email地址
	protected java.lang.String name; // lk_name  姓名
	protected java.lang.String gender; // lk_sex  性别
	protected java.lang.String password; // lk_pwd  密码
	protected java.lang.String status; // lk_status   状态
	protected java.lang.Boolean auto_email;
	public java.lang.Boolean getAuto_email() {
		return auto_email;
	}
	public void setAuto_email(java.lang.Boolean auto_email) {
		this.auto_email = auto_email;
	} 

}
