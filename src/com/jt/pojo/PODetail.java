package com.jt.pojo;

import android.content.Context;

import com.jt.pojo.WorkOrder.WorkOrderImpl;
import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.ui.IListDataItem;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.impl.BaseDaoImpl;
@Table(name = "t_podetail")
public class PODetail extends Bpojo implements IListDataItem  {
	
	/**
	 * @author thomasy
	 * @送货的POJO
	 */
	public static final long serialVersionUID = PODetail.class.hashCode();
	@Id
	@Column(name = "id")
	protected  Integer id; // poindex  主键自增
	
	
	@Column(name = "wssysid")
	protected  java.lang.Integer wssysid; // wssysid  工单序号
	//@PojoUI(PojoUIName="单据编号", PojoUIOrder = 10, PojoEditor="TextView")
	protected  java.lang.String receiptid; // receiptid  单据号
	
	public static class PODetailImpl extends BaseDaoImpl<PODetail> {
		public PODetailImpl(Context context) {
			super(new DBHelper(context), PODetail.class);
		}
	}
	
	
	public PODetail(Integer wssysid) {
		super();
		this.wssysid = wssysid;
	}
	public PODetail() {
		// TODO Auto-generated constructor stub
	}
	protected  java.lang.Integer orderno; // orderno  单据分录号
	//@PojoUI(PojoUIName="更换类型", PojoUIOrder = 20, PojoEditor="TextView")
	protected  java.lang.String advice; // advice  更换类型
	@Column(name = "poname")
	@PojoUI(PojoUIName="物料名称", PojoUIOrder = 30, PojoEditor="EditText")
	protected  java.lang.String matname; // matname 
	@Column(name = "pocount")
	@PojoUI(PojoUIName="使用数量", PojoUIOrder = 32, PojoEditor="EditText")
	protected  java.lang.Integer num; // num  数量
	@Override
	public boolean compare(String value) {
		// TODO Auto-generated method stub
		return search(value);
	}
	
	
	public java.lang.Integer getWssysid() {
		return wssysid;
	}
	public void setWssysid(java.lang.Integer wssysid) {
		this.wssysid = wssysid;
	}
	public java.lang.String getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(java.lang.String receiptid) {
		this.receiptid = receiptid;
	}
	public java.lang.Integer getOrderno() {
		return orderno;
	}
	public void setOrderno(java.lang.Integer orderno) {
		this.orderno = orderno;
	}
	public java.lang.String getAdvice() {
		return advice;
	}
	public void setAdvice(java.lang.String advice) {
		this.advice = advice;
	}
	public java.lang.String getMatname() {
		return matname;
	}
	public void setMatname(java.lang.String matname) {
		this.matname = matname;
	}
	public java.lang.Integer getNum() {
		return num;
	}
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return advice;
	}
	@Override
	public String getBref() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getSortKey() {
		// TODO Auto-generated method stub
		return receiptid;
	}
	
	@Override
	public CharSequence getAddress() {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isUnRead() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isReverse() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static long saveLocal(Context ctx,PODetail po) {
		PODetailImpl wdDao = new PODetailImpl(ctx);
		long ret;
		if ( po == null )
			return -1;
		if ( po.id ==null)
			po.id = po.getWssysid()+po.hashCode();
		if (  wdDao.get( po.getID()) == null)
			ret = wdDao.insert(po, false);
		else
			ret = wdDao.update(po);
		return ret;
		
	}
	@Override
	public CharSequence getType() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public  Object getID() {
		return id;
		//return ""+this.getWssysid()+this.matname;
	}
	
}
