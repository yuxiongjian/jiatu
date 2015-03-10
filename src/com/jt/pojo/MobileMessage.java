package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.ui.IListDataItem;
import project.util.Func;

public class MobileMessage extends Bpojo implements IListDataItem{

	/**
	 * 
	 */
	public static final long serialVersionUID = MobileMessage.class.hashCode();
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.getName().toString();
	}
	@Override
	public boolean isReverse() {
		// TODO Auto-generated method stub
		return true;
	}
	protected Integer msindex;
	@PojoUI(PojoUIName="消息主题", PojoUIOrder = 00, PojoEditor="TextView")	
	protected String mtheme;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.mtheme;
	}
	@Override
	public String getBref() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return Func.dateToString(sendtime);
	}
	@Override
	public String getSortKey() {
		// TODO Auto-generated method stub
		return (String) (this.rectype==true?" "+getBref():getBref());
	}
	@Override
	public Object getID() {
		// TODO Auto-generated method stub
		return msindex;
	}
	@Override
	public CharSequence getAddress() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public boolean isUnRead() {
		// TODO Auto-generated method stub
		return this.rectype==false;
	}
	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@PojoUI(PojoUIName="消息内容", PojoUIOrder = 10, PojoEditor="TextView")
	protected String mcontent;
	protected java.util.Date rectime;
	protected String senderid;
	@PojoUI(PojoUIName="发送时间", PojoUIOrder = 20, PojoEditor="TextView")
	protected java.util.Date sendtime;
	protected Integer mstype;
	protected String receiverid;
	protected Boolean rectype;

	@Override
	public CharSequence getType() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public boolean compare(String value) {
		// TODO Auto-generated method stub
		return search(value);
	}

}
