package com.jt.pojo;

import java.util.Date;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class ResolveResultUI extends Bpojo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = ResolveResultUI.class.hashCode();
	@PojoUI(PojoUIName="修理结果", PojoUIOrder = 00, PojoEditor="Spinner", SpinnerClass=WorkResult.class)
	public	String	workresult	;
	
	@PojoUI(PojoUIName="是否修复", PojoUIOrder = 10, PojoEditor="Spinner",CanBeNull=false, SpinnerClass=TrueFalse.class)
	public	Boolean	ismachineok	=true;
	
	@PojoUI(PojoUIName="下次预约时间", PojoUIOrder = 20, PojoEditor="DatePicker")
	public	Date	reservetime	;
		
	@PojoUI(PojoUIName="未修复原因", PojoUIOrder = 30, PojoEditor="EditText",CanBeNull=false)
	public	String	unrepairedreason	;
	@PojoUI(PojoUIName="工单备注", PojoUIOrder = 40, PojoEditor="EditText" )
	public	String	sheetmemo	;



}
