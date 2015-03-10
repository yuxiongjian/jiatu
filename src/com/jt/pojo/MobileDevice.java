package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

import com.jt.appservice.JtService;


public class MobileDevice extends Bpojo {
	/**
	 * 
	 */
	public static final long serialVersionUID = MobileDevice.class.hashCode();
	protected  java.lang.Integer mobileid; // 设备顺序号
	protected  java.lang.String deviceid; // 设备串号
	protected  Boolean status; // 设备状态，有效1，无效0
	protected  java.lang.String password; // 加密的密码
	protected  java.lang.String imei; // SIM卡串号
	protected  java.lang.String mobile; // 手机号码
	@PojoMap( key = 1)
	protected  java.lang.String userid; // 使用者ID
	@PojoMap( key = 0)
	protected  java.lang.String username; // 使用者姓名
	protected  Boolean user_status; // 使用者状态，有效1，无效0
	protected  java.lang.String group_id; // 组编号
	protected  java.lang.String group_name; // 组名称
	protected  java.lang.String dept_id; // 部门编号
	protected  java.lang.String dept_name; // 部门名称
	
	protected  java.lang.String techleader_mobile;//使用者组技术长电话，手机
	protected  java.lang.String techleader_tel;//使用者组技术长电话，座机
	protected  java.lang.String groupheader_mobile;//使用者组调度电话,手机
	protected  java.lang.String groupheader_tel;//使用者组调度电话，座机

	static MobileDevice [] ItemList={};
	
	protected Boolean technician;
	protected Boolean manager;
	protected Boolean groupHeader;
	protected Boolean techHeader;
	protected Boolean generalManager;

	protected java.lang.Integer usertype; // usertype 用户类型
	protected java.lang.String usertype_name; // usertype_name 用户类型名称

	protected java.lang.String model; // devicemodel 设备型号
	protected java.lang.String brand; // devicebrand 设备品牌
	protected java.sql.Date buydate; // buydate 购买日期
	protected java.lang.String os; // os 操作系统
	protected java.lang.String memo; // memo 备注

	protected java.lang.String techleader_id; // techleaderid 使用者组技术长ID
	protected java.lang.String techleader_name; // techleadername 使用者组技术长电话


	protected java.lang.String groupheader_id; // groupheaderid 使用者组调度ID
	protected java.lang.String groupheader_name; // groupheadername 使用者组调度姓名

	
	
	
	
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findTechnician(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
	}
	public java.lang.Integer getMobileid() {
		return mobileid;
	}

	public void setMobileid(java.lang.Integer mobileid) {
		this.mobileid = mobileid;
	}

	public java.lang.String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(java.lang.String deviceid) {
		this.deviceid = deviceid;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getImei() {
		return imei;
	}

	public void setImei(java.lang.String imei) {
		this.imei = imei;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getUserid() {
		return userid;
	}

	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public Boolean getUser_status() {
		return user_status;
	}

	public void setUser_status(Boolean user_status) {
		this.user_status = user_status;
	}

	public java.lang.String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(java.lang.String group_id) {
		this.group_id = group_id;
	}

	public java.lang.String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(java.lang.String group_name) {
		this.group_name = group_name;
	}

	public java.lang.String getDept_id() {
		return dept_id;
	}

	public void setDept_id(java.lang.String dept_id) {
		this.dept_id = dept_id;
	}

	public java.lang.String getDept_name() {
		return dept_name;
	}

	public void setDept_name(java.lang.String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mobileid == null) ? 0 : mobileid.hashCode());
		return result;
	}

	
	

}
