package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.ui.IListDataItem;
import project.util.Func;

public class PredictOrder extends Bpojo implements IListDataItem {
	/**
	 * 
	 */
	public  static final long serialVersionUID = PredictOrder.class.hashCode();
	protected  java.lang.Integer machineid; // machineid
	@PojoUI(PojoUIName="客户名称", PojoUIOrder = 10, PojoEditor="TextView", Span=3)
	protected  java.lang.String custname; // custname
	@PojoUI(PojoUIName="合同序号", PojoUIOrder = 100, PojoEditor="TextView", Span=3)
	protected java.lang.Integer barsysid; // barsysid合同序号
	@PojoUI(PojoUIName="机器条码", PojoUIOrder = 200, PojoEditor="TextView", Span=3)
	protected  java.lang.String barcode; // barcode
	
	@PojoUI(PojoUIName="计划日期", PojoUIOrder = 300, PojoEditor="TextView")
	protected  java.sql.Date schedule_date; // by_day
	
	@PojoUI(PojoUIName="服务类型", PojoUIOrder = 302, PojoEditor="TextView")
	protected  java.lang.String worktype; // worktype
	
	@PojoUI(PojoUIName="机号", PojoUIOrder = 400, PojoEditor="TextView")
	protected  java.lang.String manufactcode; // manufactcode
	
	@PojoUI(PojoUIName="机型", PojoUIOrder = 402, PojoEditor="TextView")
	protected  java.lang.String mtype; // mtype
	
	@PojoUI(PojoUIName="区域", PojoUIOrder = 500, PojoEditor="TextView")
	protected  java.lang.String area; // area
	
	@PojoUI(PojoUIName="技术员", PojoUIOrder = 502, PojoEditor="TextView")
	protected  java.lang.String techname; // techname
	
	@PojoUI(PojoUIName="地址", PojoUIOrder = 600, PojoEditor="TextView", Span=3)
	protected  java.lang.String mdepart; // mdepart

	
	protected  java.lang.String custid; // custid

	protected  java.lang.Double latitude; // latitude
	protected  java.lang.Double longitude; // longitude

	protected  java.lang.String techid; // techid
	protected  java.lang.String groupid; // groupid
	protected int predictid;


	public java.lang.Integer getMachineid() {
		return machineid;
	}

	public void setMachineid(java.lang.Integer machineid) {
		this.machineid = machineid;
	}

	public java.lang.String getWorktype() {
		return worktype;
	}

	public void setWorktype(java.lang.String worktype) {
		this.worktype = worktype;
	}

	public java.sql.Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(java.sql.Date schedule_date) {
		this.schedule_date = schedule_date;
	}

	public java.lang.String getManufactcode() {
		return manufactcode;
	}

	public void setManufactcode(java.lang.String manufactcode) {
		this.manufactcode = manufactcode;
	}

	public java.lang.String getMtype() {
		return mtype;
	}

	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}

	public java.lang.String getArea() {
		return area;
	}

	public void setArea(java.lang.String area) {
		this.area = area;
	}

	public java.lang.String getMdepart() {
		return mdepart;
	}

	public void setMdepart(java.lang.String mdepart) {
		this.mdepart = mdepart;
	}

	public java.lang.String getCustid() {
		return custid;
	}

	public void setCustid(java.lang.String custid) {
		this.custid = custid;
	}

	public java.lang.String getCustname() {
		return custname;
	}

	public void setCustname(java.lang.String custname) {
		this.custname = custname;
	}

	public java.lang.Double getLatitude() {
		return latitude;
	}

	public void setLatitude(java.lang.Double latitude) {
		this.latitude = latitude;
	}

	public java.lang.Double getLongitude() {
		return longitude;
	}

	public void setLongitude(java.lang.Double longitude) {
		this.longitude = longitude;
	}

	public java.lang.String getTechname() {
		return techname;
	}

	public void setTechname(java.lang.String techname) {
		this.techname = techname;
	}

	public java.lang.String getTechid() {
		return techid;
	}

	public void setTechid(java.lang.String techid) {
		this.techid = techid;
	}

	public java.lang.String getGroupid() {
		return groupid;
	}

	public void setGroupid(java.lang.String groupid) {
		this.groupid = groupid;
	}

	public java.lang.String getBarcode() {
		return barcode;
	}

	public void setBarcode(java.lang.String barcode) {
		this.barcode = barcode;
	}

	

	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Func.toString(custname);
	}

	@Override
	public String getBref() {
		// TODO Auto-generated method stub
		return Func.toString(manufactcode);
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return Func.dateToString(schedule_date);
	}

	@Override
	public String getSortKey() {
		// TODO Auto-generated method stub
		return Func.dateToString(schedule_date);
	}

	@Override
	public Object getID() {
		// TODO Auto-generated method stub
		return predictid;
	}

	@Override
	public CharSequence getAddress() {
		// TODO Auto-generated method stub
		return mdepart;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return barcode;
	}

	@Override
	public boolean isUnRead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReverse() {
		// TODO Auto-generated method stub
		return false;
	}

	public static long getSerialversionuid() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

	@Override
	public CharSequence getType() {
		// TODO Auto-generated method stub
		return "";
	}

	
	
}
