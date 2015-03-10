package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

import com.jt.appservice.JtService;


public class MachLocatepart extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = MachLocatepart.class.hashCode();
	@PojoMap( key= 1)
	public	String	machlctpartcode	;
	
	public	String	machlctcode	;
	@PojoMap( key= 0 )
	public	String	machlctpartname	;
	static MachLocatepart [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		
		
			ItemList = JtService.findMachLocatePart(User.cUser.getSid(), User.cUser.getUsername());
		
		return ItemList;
	}


}
