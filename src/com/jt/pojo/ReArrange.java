package com.jt.pojo;

import java.util.List;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

/**
 * 功能状态对象，对应t_code表
 *
 */
public class ReArrange extends Bpojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = ReArrange.class.hashCode();
	@PojoUI(PojoUIName="计划日期", PojoUIOrder = 10, PojoEditor="DatePicker",CanBeNull=false, Span=3)
	protected java.util.Date newDate;
	//@PojoUI(PojoUIName="原因", PojoUIOrder = 20, PojoEditor="EditText",span=3)
	protected String reason;//
	@PojoUI(PojoUIName="技术员", PojoUIOrder = 30,PojoEditor="Spinner",CanBeNull=false,SpinnerClass=MobileDevice.class, Span=3)
	protected String techid;//技术员
	/**
	 * @return the techid
	 */
	public String getTechid() {
		return techid;
	}
	/**
	 * @param techid the techid to set
	 */
	public void setTechid(String techid) {
		this.techid = techid;
	}
	/**
	 * @return the newDate
	 */
	public java.util.Date getNewDate() {
		return newDate;
	}
	/**
	 * @param newDate the newDate to set
	 */
	public void setNewDate(java.util.Date newDate) {
		this.newDate = newDate;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

	
}
