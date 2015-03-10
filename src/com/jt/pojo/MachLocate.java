package com.jt.pojo;

import com.jt.appservice.JtService;


import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

public class MachLocate extends Bpojo {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = MachLocate.class.hashCode();
	@PojoMap( key = 1)
	public	String	machlctcode	;
	@PojoMap( key= 0 )
	public	String	machlctname	;
	public String memo;
	
	

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	static MachLocate [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findMachLocate(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
	}
/*
	@Override
	public String[] getListItemValues() {
		
		String[] ret={};
		try {
			ItemList = JtService.findMachLocate(AppLoginActivity.cUser.getSid(), AppLoginActivity.cUser.getUsername());
			ret = new String[ItemList.length];
			int i=0;
			for( MachLocate m: ItemList)
				ret[i++]= m.machlctname;
		} catch (Exception e) {
			//thy-todo TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return ret;
	}
	
*/


}
