package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class ResolveRepairUI extends Bpojo {

	
	/**
	 * 
	 */
	public static final long serialVersionUID = ResolveRepairUI.class.hashCode();
	@PojoUI(PojoUIName="故障现象", PojoUIOrder = 00, PojoEditor="Spinner",CanBeNull=false, SpinnerClass=TelTrbType.class, SubSpinner= "errordetail", SubField="telcode" )	
	public String	errappearance	;
	@PojoUI(PojoUIName="故障详细", PojoUIOrder = 10, PojoEditor="Spinner",SpinnerClass=MachineProblem.class)	
	public Integer	errordetail	;
	
	@PojoUI(PojoUIName="故障部位", PojoUIOrder = 20, PojoEditor="Spinner", CanBeNull=false,SpinnerClass=MachLocate.class, SubSpinner="errpart", SubField="machlctcode")	
	public String	errplace	;
	@PojoUI(PojoUIName="故障部件", PojoUIOrder = 30, PojoEditor="Spinner", CanBeNull=false,SpinnerClass=MachLocatepart.class)
	public String	errpart	;
	
	@PojoUI(PojoUIName="故障描述", PojoUIOrder = 40, PojoEditor="EditText")
	public String	trbldetail	;
	
	@PojoUI(PojoUIName="处理过程", PojoUIOrder = 50, PojoEditor="EditText")
	public String	process	;

	@PojoUI(PojoUIName="清洁项目", PojoUIOrder = 60, PojoEditor="Spinner", SpinnerClass=SelItems.class)
	public String	clearitem	;
	
	@PojoUI(PojoUIName="固件号", PojoUIOrder = 70, PojoEditor="EditText")
	public String	partno	;


}
