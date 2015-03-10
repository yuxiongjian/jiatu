package com.jt.pojo;

import com.jt.pojo.PODetail.PODetailImpl;
import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

import android.content.Context;
import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.impl.BaseDaoImpl;

/**
 * @author thomasy
 * @category 画幅抄张记录接口 
 *
 */

@Table(name = "t_recordcopy")
public class RecordCopy extends Bpojo{

	/**
	 * 
	 */
	public static final long serialVersionUID = RecordCopy.class.hashCode();
	public static class RecordCopyImpl extends BaseDaoImpl<RecordCopy> {
		public RecordCopyImpl(Context context) {
			super(new DBHelper(context), RecordCopy.class);
		}
	}
	
	@Id
	@Column(name = "id")
	public String id;
	@Column(name = "barsysid")
	protected java.lang.Integer barsysid; // barsysid  合同序号
	
	
	@PojoUI(PojoUIName="幅面", PojoUIOrder = 10, PojoEditor="TextView")
	@Column(name = "barsysid")
	protected java.lang.String hctype; // hctype  幅面
	
	@Column(name = "curnum")
	@PojoUI(PojoUIName="当前读数", PojoUIOrder = 40, PojoEditor="EditText", CanBeNull=false)
	protected java.lang.Integer curnum; // curnum  当前读数
	
	@Column(name = "machineid")
	protected java.lang.Integer machineid; // machineid 机器序号  
	@Column(name = "barcode")
	protected java.lang.String barcode; // barcode  机器条码
	@Column(name = "cz_day")
	protected java.sql.Date cz_day; // cz_day  抄张日期
	@Column(name = "wssysid")
	protected java.lang.Integer wssysid; // wssysid 工单号码  

	public RecordCopy(Integer sysid) {
		
		wssysid=sysid;
		// TODO Auto-generated constructor stub
	}
	
	public RecordCopy() {
		
		// TODO Auto-generated constructor stub
	}
	public java.lang.Integer getBarsysid() {
		return barsysid;
	}
	public void setBarsysid(java.lang.Integer barsysid) {
		this.barsysid = barsysid;
	}
	public java.lang.String getHctype() {
		return hctype;
	}
	public void setHctype(java.lang.String hctype) {
		this.hctype = hctype;
	}
	public java.lang.Integer getMachineid() {
		return machineid;
	}
	public void setMachineid(java.lang.Integer machineid) {
		this.machineid = machineid;
	}
	public java.lang.String getBarcode() {
		return barcode;
	}
	public void setBarcode(java.lang.String barcode) {
		this.barcode = barcode;
	}
	public java.sql.Date getCz_day() {
		return cz_day;
	}
	public void setCz_day(java.sql.Date cz_day) {
		this.cz_day = cz_day;
	}
	public java.lang.Integer getWssysid() {
		return wssysid;
	}
	public void setWssysid(java.lang.Integer wssysid) {
		this.wssysid = wssysid;
	}
	public java.lang.Integer getCurnum() {
		return curnum;
	}
	public void setCurnum(java.lang.Integer curnum) {
		this.curnum = curnum;
	}
	
	public static long saveLocal(Context ctx, RecordCopy po) {
		RecordCopyImpl wdDao = new RecordCopyImpl(ctx);
		long ret;
		
		if ( po == null )
			return -1;
		po.id = po.getID().toString();
		if (  po.getID()==null || wdDao.get(po.getID()) == null)
			ret = wdDao.insert(po, false);
		else
			ret = wdDao.update(po);
		return ret;
		
	}
	
	public  Object getID() {
		// TODO Auto-generated method stub
		return ""+this.getWssysid()+this.hctype;
	}
	
	
	
	
	
}
