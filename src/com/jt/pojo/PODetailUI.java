package com.jt.pojo;

import java.util.HashMap;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;

public class PODetailUI extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = PODetailUI.class.hashCode();
	@PojoUI(PojoUIName="", PojoUIOrder = 00, PojoEditor="POJOLIST")
	protected PODetail[] detailList;//送货
	private Integer sysid=0;
	
	public HashMap<String, String> refreshData( HashMap<String, Bpojo.ViewHolder> viewMaps[],HashMap<String, String> retMap) {

		Bpojo b;
		HashMap<String, Bpojo.ViewHolder>  viewMap;
		int len = viewMaps.length;
		detailList = new PODetail[len];
		for (int i =0;i<len ;i++){
			
			b = detailList[i]= new PODetail(sysid);
			viewMap = viewMaps[i];
			
			b.refreshData(viewMap, retMap);
			
		}
		return retMap;
		// TODO Auto-generated method stub

	}
}
