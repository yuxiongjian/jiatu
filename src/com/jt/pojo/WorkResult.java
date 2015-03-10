package com.jt.pojo;

import com.jt.appservice.JtService;


import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

public class WorkResult extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = WorkResult.class.hashCode();
	@PojoMap( key= 1 )
	public	String	repairstatuscode	;
	@PojoMap( key= 0 )
	public	String	repairstatusname	;
	static private WorkResult[] ItemList;
	
	@Override
	public WorkResult[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findWorkResult(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
		
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		// TODO Auto-generated method stub
		return super.clone();
	}


}
