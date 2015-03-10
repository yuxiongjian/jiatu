package com.jt.pojo;

import project.pojo.Bpojo;
import project.util.MyLog;

public class MaintainHistory extends Bpojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = MaintainHistory.class.hashCode();
	protected String	model;
	protected String	fault_code;
	protected String	fault_part;
	protected String 	fault_appearance;
	protected String	fault_reason;
	protected String	solution;
	protected String	fault_desc;

	static Bpojo [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() {
		// TODO Auto-generated method stub
		MyLog.False("");
		return ItemList;
	}

}
