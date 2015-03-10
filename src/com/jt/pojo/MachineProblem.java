package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

import com.jt.appservice.JtService;

public class MachineProblem extends Bpojo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = MachineProblem.class.hashCode();
	@PojoMap( key = 1)
	public	Integer	probcodeid	;
	public	String	telcode	;
	@PojoMap( key = 0)
	public	String	probname	;
	
	static MachineProblem [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findMachineProblem(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
	}

	
	


}
