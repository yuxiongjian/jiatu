package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

import com.jt.appservice.JtService;


public class SelItems extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = SelItems.class.hashCode();
	@PojoMap( key= 1 )
	public	String	itemkey	;
	
	public	String	itemtype	;
	@PojoMap( key= 0 )
	public	String	itemval	;
	
	static SelItems [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findSelItems(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
	}

	

}
