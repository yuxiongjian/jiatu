package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.ui.IListDataItem;

/**
 * @author thomasy
 *
 */
public class CustomerMachine extends Bpojo implements IListDataItem {
	/**
	 * @PojoUI(PojoUIName="", PojoUIOrder = , PojoEditor="TextView")
	 */
	public static final long serialVersionUID = CustomerMachine.class.hashCode();
	protected java.lang.Integer machineid; // --机器顺序号
	@PojoUI(PojoUIName="客户名称", PojoUIOrder = 00, PojoEditor="TextView")
	protected java.lang.String custname;// --客户名称
	
	@PojoUI(PojoUIName="机器地址", PojoUIOrder = 10, PojoEditor="TextView")
	protected java.lang.String address;// --机器地址
	
	@PojoUI(PojoUIName="技术员", PojoUIOrder = 100, PojoEditor="TextView")
	protected java.lang.String ptech;// --预定技术员
	@PojoUI(PojoUIName="机器编号", PojoUIOrder = 200, PojoEditor="TextView")
	protected java.lang.String manufact_code;// --机器编号
	@PojoUI(PojoUIName="机器条码", PojoUIOrder = 300 , PojoEditor="TextView")
	protected java.lang.String barcode;// --机器条码
	@PojoUI(PojoUIName="型号", PojoUIOrder = 400, PojoEditor="TextView")
	protected java.lang.String model;// --型号
	
	protected java.lang.String modelbrand;// --型号
	@PojoUI(PojoUIName="区域", PojoUIOrder = 500 , PojoEditor="TextView")
	protected java.lang.String area;// --区域
	protected java.lang.String used_type;// --使用类型
	@PojoUI(PojoUIName="维护类型", PojoUIOrder = 600, PojoEditor="TextView")
	protected java.lang.String maintet_type;// --内部维护类型
	protected java.lang.String  custid;//客户ID
	@PojoUI(PojoUIName="调度电话", PojoUIOrder = 700, PojoEditor="TextView", System="call")
	protected String dispatch_off_tel;
	protected String tech_mob_tel;
	public java.lang.Integer getMachineid() {
		return machineid;
	}

	public void setMachineid(java.lang.Integer machineid) {
		this.machineid = machineid;
	}

	public java.lang.String getCustomer_name() {
		return custname;
	}

	public void setCustomer_name(java.lang.String customer_name) {
		this.custname = customer_name;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getTechnician() {
		return ptech;
	}

	public void setTechnician(java.lang.String technician) {
		this.ptech = technician;
	}

	public java.lang.String getManufact_code() {
		return manufact_code;
	}

	public void setManufact_code(java.lang.String manufact_code) {
		this.manufact_code = manufact_code;
	}

	public java.lang.String getBarcode() {
		return barcode;
	}

	public void setBarcode(java.lang.String barcode) {
		this.barcode = barcode;
	}

	public java.lang.String getModel() {
		return model;
	}

	public void setModel(java.lang.String model) {
		this.model = model;
	}

	public java.lang.String getArea() {
		return area;
	}

	public void setArea(java.lang.String area) {
		this.area = area;
	}

	public java.lang.String getUsed_type() {
		return used_type;
	}

	public void setUsed_type(java.lang.String used_type) {
		this.used_type = used_type;
	}

	public java.lang.String getMaintet_type() {
		return maintet_type;
	}

	public void setMaintet_type(java.lang.String maintet_type) {
		this.maintet_type = maintet_type;
	}

	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return model;
		
	}

	@Override
	public String getBref() {
		
		return ptech==null||ptech.length()==0?"无信息":ptech;
		// TODO Auto-generated method stub
	
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getSortKey() {
		// TODO Auto-generated method stub
		return barcode;
	}

	@Override
	public Object getID() {
		// TODO Auto-generated method stub
		return machineid;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return custname;
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

	@Override
	public CharSequence getType() {
		// TODO Auto-generated method stub
		return barcode;
	}

	
	@Override
	public boolean compare(String value) {
		// TODO Auto-generated method stub
		return search(value);
	}

	

}
