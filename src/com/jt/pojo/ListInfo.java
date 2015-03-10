package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class ListInfo extends Bpojo {

	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 
	 */
	public static final long serialVersionUID = ListInfo.class.hashCode();
	
	protected String barCode;
	@PojoUI(PojoUIName="", PojoUIOrder = 00, PojoEditor="WebView")	
	//@PojoUI(PojoUIName="查看历史", PojoUIOrder = 00, PojoEditor="EditText")	
	protected String url;
	protected int helptype;
	public int getHelptype() {
		return helptype;
	}
	public void setHelptype(int helptype) {
		this.helptype = helptype;
	}

}
