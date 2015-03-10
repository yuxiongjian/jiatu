package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

import com.jt.appservice.JtService;


public class TelTrbType extends Bpojo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = TelTrbType.class.hashCode();
	@PojoMap( key= 1 )
	public	String	telcode	;
	@PojoMap( key= 0 )
	public	String	telmemo	;
	static TelTrbType [] ItemList={};
	@Override
	public Bpojo[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		ItemList = JtService.findTroubleType(User.cUser.getSid(), User.cUser.getUsername());
		return ItemList;
	}
/*
	@Override
	public String[] getListItemValues() {
		
		String[] ret={};
		try {
			ItemList = JtService.findTroubleType(AppLoginActivity.cUser.getSid(), AppLoginActivity.cUser.getUsername());
			ret = new String[ItemList.length];
			int i=0;
			for( TelTrbType m: ItemList)
				ret[i++]= m.telmemo;
		} catch (Exception e) {
			//thy-todo TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return ret;
	}*/



}
