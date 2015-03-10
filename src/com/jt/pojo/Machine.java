package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class Machine extends Bpojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = Machine.class.hashCode();
	protected Integer machineid; // 机器ID
	// private String machine_number;
	protected String machine_name;// 机器名称
	protected String manufact_code;// 机器编号
	protected String model;// 机器型号
	protected String custname;// 客户名称
	protected String contact;// 客户联系人
	@PojoUI(PojoUIName="机器地址", PojoUIOrder = 0, PojoEditor="TextView")
	protected String address;
	@PojoUI(PojoUIName="修理师名单", PojoUIOrder = 1,PojoEditor="EditText")
	protected String technician;
	protected String modelbrand;
	protected Integer custid;

	protected String barcode;
	protected String area;
	protected String used_type;
	protected String maintet_type;
	

	public String getManufact_code() {
		return manufact_code;
	}

	public void setManufact_code(String manufact_code) {
		this.manufact_code = manufact_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUsed_type() {
		return used_type;
	}

	public void setUsed_type(String used_type) {
		this.used_type = used_type;
	}

	public String getMaintet_type() {
		return maintet_type;
	}

	public void setMaintet_type(String maintet_type) {
		this.maintet_type = maintet_type;
	}


	public Integer getMachine_id() {
		return machineid;
	}

	public void setMachine_id(Integer machine_id) {
		this.machineid = machine_id;
	}

	public String getMachine_name() {
		return machine_name;
	}

	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCustomer_name() {
		return custname;
	}

	public void setCustomer_name(String customer_name) {
		this.custname = customer_name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((machineid == null) ? 0 : machineid.hashCode());
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
		Machine other = (Machine) obj;
		if (machineid == null) {
			if (other.machineid != null)
				return false;
		} else if (!machineid.equals(other.machineid))
			return false;
		return true;
	}

	@Override
	public Machine clone() throws CloneNotSupportedException {
		return (Machine) super.clone();
	}
}
