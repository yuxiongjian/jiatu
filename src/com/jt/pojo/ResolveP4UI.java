package com.jt.pojo;

import java.util.Date;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class ResolveP4UI extends Bpojo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = ResolveP4UI.class.hashCode();
	@PojoUI(PojoUIName="用户建议", PojoUIOrder = 00, CanBeNull=false, PojoEditor="EditText")
	public String	suggest	;
	@PojoUI(PojoUIName="满意度评价", PojoUIOrder = 10, CanBeNull=false,PojoEditor="Spinner", SpinnerClass=TrueFalseInt.class)
	public Integer	custsati	;



}
