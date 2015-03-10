package com.jt.pojo;

import java.util.ArrayList;

public class GroupBean {

	private int id;
	private String deptName;
	private ArrayList<WorkOrder> workOrders;
	private int count;
	private String compname;

	/**
	 * @return the compname
	 */
	
	public GroupBean(String comp,String dept){
		
		deptName=dept;
		compname=comp;
		id=0;
		
	}
	public GroupBean(){
		
		deptName=null;
		compname=null;
		id=0;
		
	}
	public String getCompname() {
		return compname;
	}
	/**
	 * @param compname the compname to set
	 */
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String name) {
		this.deptName = name;
	}
	public ArrayList<WorkOrder> getContact() {
		return workOrders;
	}
	public void setContact(ArrayList<WorkOrder> contact) {
		this.workOrders = contact;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
