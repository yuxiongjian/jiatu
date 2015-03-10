package com.jt.pojo;

import java.util.Date;

import project.pojo.Bpojo;

public class User extends project.pojo.User{
	public static User cUser ;
	/**
	 * 
	 */
	private static final long serialVersionUID = User.class.hashCode();
	protected  String userid; // 用户主键，自增长，前台无需维护，后台维护
	protected  String username;// 用户登录名，用户账号
	protected  String password;// 用户密码，冗余字段
	protected  String displayName;// 用户显示名称
	protected  String mobile;// 电话号码
	protected  String deviceid;// 手机设备ID
	protected  String imei;// sim卡串号
	protected  Boolean locked; // 锁定标志，
	protected  Date created_date;// 创建日期
	protected  Date updated_date;// 最后修改日期
	protected String sid="";
	protected String groupheader_mobile;
	protected String techleader_tel;
	protected String techleader_mobile;
	protected String groupheader_tel;//调度电话
	
	protected Integer usertype;
	protected String usertypename;
	protected String tracking_begin_time;
	protected String tracking_end_time;
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the groupheader_mobile
	 */
	public String getGroupheader_mobile() {
		return groupheader_mobile;
	}

	/**
	 * @param groupheader_mobile the groupheader_mobile to set
	 */
	public void setGroupheader_mobile(String groupheader_mobile) {
		this.groupheader_mobile = groupheader_mobile;
	}

	/**
	 * @return the techleader_tel
	 */
	public String getTechleader_tel() {
		return techleader_tel;
	}

	/**
	 * @param techleader_tel the techleader_tel to set
	 */
	public void setTechleader_tel(String techleader_tel) {
		this.techleader_tel = techleader_tel;
	}

	/**
	 * @return the techleader_mobile
	 */
	public String getTechleader_mobile() {
		return techleader_mobile;
	}

	/**
	 * @param techleader_mobile the techleader_mobile to set
	 */
	public void setTechleader_mobile(String techleader_mobile) {
		this.techleader_mobile = techleader_mobile;
	}

	/**
	 * @return the groupheader_tel
	 */
	public String getGroupheader_tel() {
		return groupheader_tel;
	}

	/**
	 * @param groupheader_tel the groupheader_tel to set
	 */
	public void setGroupheader_tel(String groupheader_tel) {
		this.groupheader_tel = groupheader_tel;
	}

	/**
	 * @return the usertype
	 */
	public Integer getUsertype() {
		return usertype;
	}

	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	/**
	 * @return the usertypename
	 */
	public String getUsertypename() {
		return usertypename;
	}

	/**
	 * @param usertypename the usertypename to set
	 */
	public void setUsertypename(String usertypename) {
		this.usertypename = usertypename;
	}

	/**
	 * @return the tracking_begin_time
	 */
	public String getTracking_begin_time() {
		return tracking_begin_time;
	}

	/**
	 * @param tracking_begin_time the tracking_begin_time to set
	 */
	public void setTracking_begin_time(String tracking_begin_time) {
		this.tracking_begin_time = tracking_begin_time;
	}

	/**
	 * @return the tracking_end_time
	 */
	public String getTracking_end_time() {
		return tracking_end_time;
	}

	/**
	 * @param tracking_end_time the tracking_end_time to set
	 */
	public void setTracking_end_time(String tracking_end_time) {
		this.tracking_end_time = tracking_end_time;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhonenumber() {
		return mobile;
	}

	public void setPhonenumber(String phonenumber) {
		this.mobile = phonenumber;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		User other = (User) obj;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", displayName=" + displayName + ", phonenumber="
				+ mobile + ", deviceid=" + deviceid + ", imei=" + imei
				+ "]";
	}

	@Override
	public User clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (User) super.clone();
	}

}
