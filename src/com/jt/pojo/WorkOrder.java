package com.jt.pojo;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jt.pojo.PODetail.PODetailImpl;
import com.jt.pojo.RecordCopy.RecordCopyImpl;
import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

import android.content.Context;
import project.pojo.Bpojo;
import project.ui.IListDataItem;
import project.util.Func;
import project.util.MyLog;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.impl.BaseDaoImpl;

@Table(name = "t_workorder")
public class WorkOrder extends Bpojo implements IListDataItem {
	private CustomerContact[] customerContact;

	@Override
	public String getBarcode() {
		// TODO Auto-generated method stub
		return Func.toString(getWorkRequest().barcode);
	}

	public static class WorkOrderImpl extends BaseDaoImpl<WorkOrder> {
		Context ctx;

		public WorkOrderImpl(Context context) {
			super(new DBHelper(context), WorkOrder.class);
			ctx = context;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see project.util.sqldb.dao.impl.BaseDaoImpl#get(int)
		 */
		@Override
		public WorkOrder get(Object id) {

			WorkOrder wd = super.get(id);
			if (wd == null)
				return null;
			RecordCopyImpl rc = new RecordCopyImpl(ctx);
			try {
				// find(new String[] { "id", "name" }, " id = ? ", new String[]
				// {
				// studentId2.toString() }, null, null, null, null);
				List<RecordCopy> rclist = rc.find(new String[] { "*" },
						" wssysid= ? ",
						new String[] { wd.getSysid().toString() }, null, null,
						null, null);

				if (rclist != null && rclist.size() > 0) {
					wd.setRecordList(rclist.toArray(new RecordCopy[0]));
				}

				PODetailImpl poi = new PODetailImpl(ctx);
				List<PODetail> polist = poi.find(new String[] { "*" },
						" wssysid= ? ",
						new String[] { wd.getSysid().toString() }, null, null,
						null, null);

				if (polist != null && polist.size() > 0) {
					wd.setDetailList(polist.toArray(new PODetail[0]));
				}
			} catch (Exception e) {
				MyLog.False(e);
			}
			wd.setFromDB(true);
			return wd;
		}

	}

	public long saveLocal(Context ctx) {

		WorkOrderImpl wdDao = new WorkOrderImpl(ctx);
		Date lod = getLocalDBTime();
		setLocalDBTime(new Date());
		long ret = 0;
		if (wdDao.get(getSysid()) == null)
			ret = wdDao.insert(this, false);
		else
			ret = wdDao.update(this);

		if (ret < 0) {
			setLocalDBTime(lod);
			return ret;
		}
		PODetail[] pl = getDetailList();
		if (pl != null)
			for (PODetail po : pl) {
				if (po != null)
					PODetail.saveLocal(ctx, po);
			}

		if (this.getRecordList() != null)
			for (RecordCopy po : getRecordList()) {
				if (po != null)
					RecordCopy.saveLocal(ctx, po);
			}

		return ret;
	}

	public final static int 调度 = -4;
	public final static int 保养 = -3;
	public final static int 抄张 = -2;
	public final static int 消息 = -1;
	public final static int 未派 = 0;
	public final static int 已派 = 1;
	public final static int 已启动 = 2;
	public final static int 已到达 = 3;
	public final static int 已提交 = 4;
	public final static int 已签字 = 5;

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return getBarcode();// +"\r\n"+this.getCustomerMachine().model+"   "+getCustomerMachine().getManufact_code();
	}

	@Override
	public boolean isReverse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean isUnRead() {
		// TODO Auto-generated method stub
		if (getWorkMessage() == null || getWorkMessage().getStatus() == null)
			return true;
		// MyLog.Assert(getWorkMessage()!=null &&
		// getWorkMessage().getStatus()!=null,"");
		return getWorkMessage().getStatus().compareTo("0") == 0;

	}

	/**
	 * 
	 */
	public static final long serialVersionUID = WorkOrder.class.hashCode();
	public static final String Tag = "WorkOrder";

	@Id
	@Column(name = "sysid")
	Integer sysid;

	protected RecordCopy[] recordList; // 抄张

	/**
	 * @return the recordList
	 */
	public RecordCopy[] getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList
	 *            the recordList to set
	 */
	public void setRecordList(RecordCopy[] recordList) {
		this.recordList = recordList;
	}

	/**
	 * @return the detailList
	 */
	public PODetail[] getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList
	 *            the detailList to set
	 */
	public void setDetailList(PODetail[] detailList) {
		this.detailList = detailList;
	}

	protected PODetail[] detailList;// 送货
	@Column(name = "errappearance")
	protected java.lang.String errappearance;
	/** errappearance 故障现象 **/
	@Column(name = "errordetail")
	protected java.lang.Integer errordetail;
	/** errordetail 故障详细 **/
	@Column(name = "errplace")
	protected java.lang.String errplace;
	/** errplace 故障部位 **/

	@Column(name = "errpart")
	protected java.lang.String errpart;
	/** errpart 故障部件 **/
	@Column(name = "trbldetail")
	protected java.lang.String trbldetail;
	/** trbldetail 故障描述 **/
	@Column(name = "process")
	protected java.lang.String process;
	/** process 处理过程 **/
	@Column(name = "workresult")
	protected java.lang.String workresult;
	/** workresult 修理结果 **/
	@Column(name = "clearitem")
	protected java.lang.String clearitem;
	/** clearitem 清洁项目 **/
	@Column(name = "partno")
	protected java.lang.String partno;
	/** partno 固件号 **/
	@Column(name = "ismachineok", type = "INTEGER")
	protected java.lang.Boolean ismachineok;
	/** ismachineok 是否修复 **/
	@Column(name = "reservetime", type = "TEXT")
	protected java.util.Date reservetime;
	/** reservetime 下次预约时间 **/
	@Column(name = "unrepairedreason")
	protected java.lang.String unrepairedreason;
	/** unrepairedreason 未修复原因 **/
	@Column(name = "totalcopynum")
	protected java.lang.Integer totalcopynum;
	/** totalcopynum 总复印张数 **/
	@Column(name = "blacknum")
	protected java.lang.Integer blacknum;
	/** blacknum 黑白张数 **/
	@Column(name = "numzydc")
	protected java.lang.Integer numzydc;
	/** numzydc 专业点彩 **/
	@Column(name = "numptdc")
	protected java.lang.Integer numptdc;
	/** numptdc 普通点彩 **/
	@Column(name = "colornum1")
	protected java.lang.Integer colornum1;
	/** colornum1 专彩张数 **/
	@Column(name = "colornum2")
	protected java.lang.Integer colornum2;
	/** colornum2 普彩张数 **/
	@Column(name = "totalplatenum")
	protected java.lang.Integer totalplatenum;
	/** totalplatenum 总制版数 **/
	@Column(name = "chokedpapernum")
	protected java.lang.Integer chokedpapernum;
	/** chokedpapernum 卡纸数 **/
	@Column(name = "suggest")
	protected java.lang.String suggest;
	/** suggest 商家建议【用户建议】 **/
	@Column(name = "sheetmemo")
	protected java.lang.String sheetmemo;
	/** sheetmemo 工单备注 **/
	@Column(name = "starttime")
	protected java.util.Date starttime;
	/** starttime 启动时间 **/
	@Column(name = "arrivetime")
	protected java.util.Date arrivetime;
	/** arrivetime 到达时间 **/
	@Column(name = "signature")
	protected java.util.Date departtime;
	/** departtime 离开时间 **/
	@Column(name = "signature")
	protected java.lang.String signature;
	/** signature 客户签名文件名 **/
	@Column(name = "resolvetime")
	protected java.util.Date resolvetime;
	/** systime 提交工单时间 **/
	@Column(name = "custsati")
	protected Integer custsati;
	/** custsati 满意度评价 **/
	@Column(name = "status")
	protected java.lang.Integer status;

	public int getWorkRequestID() {
		return workRequestID;
	}

	public void setWorkRequestID(int workRequestID) {
		this.workRequestID = workRequestID;
	}

	public int getWorkorderMessageID() {
		return workorderMessageID;
	}

	public void setWorkorderMessageID(int workorderMessageID) {
		this.workorderMessageID = workorderMessageID;
	}

	public int getCustomerMachineID() {
		return customerMachineID;
	}

	public void setCustomerMachineID(int customerMachineID) {
		this.customerMachineID = customerMachineID;
	}

	/** iEndFlag 工单状态 **/
	@Column(name = "workRequestID")
	private int workRequestID;
	protected WorkRequest workRequest;
	/** 报修单对象 ***/

	@Column(name = "workorderMessageID")
	private int workorderMessageID;
	protected WorkOrderMessage workMessage;
	/** 工单消息对象 ***/

	@Column(name = "customerMachineID")
	private int customerMachineID;
	protected CustomerMachine customerMachine;

	/** 客户机器对象 ***/

	public void copyValue(ResolveRepairUI p1, ResolvePaperCountUI p2,
			ResolveResultUI p3, ResolveP4UI p4) {

		if (p1 != null) {
			this.copyValue(p1);
			/*
			 * this.clearitem = p1.clearitem; this.errappearance =
			 * p1.errappearance; this.errordetail=p1.errordetail; this.errpart=
			 * p1.errpart; this.errplace=p1.errplace; this.partno = p1.partno;
			 * this.process = p1.process; this.trbldetail = p1.trbldetail;
			 */

		}
		if (p2 != null) {
			this.blacknum = p2.blacknum;
			this.chokedpapernum = p2.chokedpapernum;
			this.colornum1 = p2.colornum1;
			this.colornum2 = p2.colornum2;
			this.numptdc = p2.numptdc;
			this.numzydc = p2.numzydc;
			this.totalcopynum = p2.totalcopynum;
			this.totalplatenum = p2.totalplatenum;

		}

		if (p3 != null) {
			this.ismachineok = p3.ismachineok;
			this.reservetime = p3.reservetime;
			this.sheetmemo = p3.sheetmemo;
			this.unrepairedreason = p3.unrepairedreason;
			this.workresult = p3.workresult;

		}

		if (p4 != null) {
			this.custsati = p4.custsati;
			this.suggest = p4.suggest;

		}
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return Func.toString(getCustomerMachine().getAddress());
	}

	@Override
	public String getBref() {
		// TODO Auto-generated method stub
		return Func.toString(getWorkRequest().getProbcode()) + ":"
				+ Func.toString(getWorkRequest().getAcceptmemo());
	}

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		java.sql.Time t = getWorkRequest().getPlanedtime();
		return t == null ? "" : t.toString().substring(0, 5);
	}

	@Override
	public Object getID() {
		// TODO Auto-generated method stub
		return this.getSysid();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return getCustomerMachine().custname;
	}

	@Override
	public String getSortKey() {
		// TODO Auto-generated method stub
		Time pt = getWorkRequest().getPlanedtime();
		return pt == null ? "" : pt.toString();
	}

	public Integer getSysid() {
		return sysid;
	}

	public void setSysid(Integer sysid) {
		this.sysid = sysid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WorkRequest getWorkRequest() {
		return workRequest;
	}

	public java.lang.String getErrappearance() {
		return errappearance;
	}

	public void setErrappearance(java.lang.String errappearance) {
		this.errappearance = errappearance;
	}

	public java.lang.Integer getErrordetail() {
		return errordetail;
	}

	public void setErrordetail(java.lang.Integer errordetail) {
		this.errordetail = errordetail;
	}

	public java.lang.String getErrplace() {
		return errplace;
	}

	public void setErrplace(java.lang.String errplace) {
		this.errplace = errplace;
	}

	public java.lang.String getErrpart() {
		return errpart;
	}

	public void setErrpart(java.lang.String errpart) {
		this.errpart = errpart;
	}

	public java.lang.String getTrbldetail() {
		return trbldetail;
	}

	public void setTrbldetail(java.lang.String trbldetail) {
		this.trbldetail = trbldetail;
	}

	public java.lang.String getProcess() {
		return process;
	}

	public void setProcess(java.lang.String process) {
		this.process = process;
	}

	public java.lang.String getWorkresult() {
		return workresult;
	}

	public void setWorkresult(java.lang.String workresult) {
		this.workresult = workresult;
	}

	public java.lang.String getClearitem() {
		return clearitem;
	}

	public void setClearitem(java.lang.String clearitem) {
		this.clearitem = clearitem;
	}

	public java.lang.String getPartno() {
		return partno;
	}

	public void setPartno(java.lang.String partno) {
		this.partno = partno;
	}

	public java.lang.Boolean getIsmachineok() {
		return ismachineok;
	}

	public void setIsmachineok(java.lang.Boolean ismachineok) {
		this.ismachineok = ismachineok;
	}

	public java.util.Date getReservetime() {
		return reservetime;
	}

	public void setReservetime(java.util.Date reservetime) {
		this.reservetime = reservetime;
	}

	public java.lang.String getUnrepairedreason() {
		return unrepairedreason;
	}

	public void setUnrepairedreason(java.lang.String unrepairedreason) {
		this.unrepairedreason = unrepairedreason;
	}

	public java.lang.Integer getTotalcopynum() {
		return totalcopynum;
	}

	public void setTotalcopynum(java.lang.Integer totalcopynum) {
		this.totalcopynum = totalcopynum;
	}

	public java.lang.Integer getBlacknum() {
		return blacknum;
	}

	public void setBlacknum(java.lang.Integer blacknum) {
		this.blacknum = blacknum;
	}

	public java.lang.Integer getNumzydc() {
		return numzydc;
	}

	public void setNumzydc(java.lang.Integer numzydc) {
		this.numzydc = numzydc;
	}

	public java.lang.Integer getNumptdc() {
		return numptdc;
	}

	public void setNumptdc(java.lang.Integer numptdc) {
		this.numptdc = numptdc;
	}

	public java.lang.Integer getColornum1() {
		return colornum1;
	}

	public void setColornum1(java.lang.Integer colornum1) {
		this.colornum1 = colornum1;
	}

	public java.lang.Integer getColornum2() {
		return colornum2;
	}

	public void setColornum2(java.lang.Integer colornum2) {
		this.colornum2 = colornum2;
	}

	public java.lang.Integer getTotalplatenum() {
		return totalplatenum;
	}

	public void setTotalplatenum(java.lang.Integer totalplatenum) {
		this.totalplatenum = totalplatenum;
	}

	public java.lang.Integer getChokedpapernum() {
		return chokedpapernum;
	}

	public void setChokedpapernum(java.lang.Integer chokedpapernum) {
		this.chokedpapernum = chokedpapernum;
	}

	public java.lang.String getSuggest() {
		return suggest;
	}

	public void setSuggest(java.lang.String suggest) {
		this.suggest = suggest;
	}

	public java.lang.String getSheetmemo() {
		return sheetmemo;
	}

	public void setSheetmemo(java.lang.String sheetmemo) {
		this.sheetmemo = sheetmemo;
	}

	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}

	public java.util.Date getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(java.util.Date arrivetime) {
		this.arrivetime = arrivetime;
	}

	public java.util.Date getDeparttime() {
		return departtime;
	}

	public void setDeparttime(java.util.Date departtime) {
		this.departtime = departtime;
	}

	public java.lang.String getSignature() {
		return signature;
	}

	public void setSignature(java.lang.String signature) {
		this.signature = signature;
	}

	public java.util.Date getResolvetime() {
		return resolvetime;
	}

	public void setResolvetime(java.util.Date resolvetime) {
		this.resolvetime = resolvetime;
	}

	public Integer getCustsati() {
		return custsati;
	}

	public void setCustsati(Integer custsati) {
		this.custsati = custsati;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public WorkOrderMessage getWorkMessage() {
		return workMessage;
	}

	public void setWorkMessage(WorkOrderMessage workMessage) {
		this.workMessage = workMessage;
	}

	public CustomerMachine getCustomerMachine() {
		return customerMachine;
	}

	public void setCustomerMachine(CustomerMachine customerMachine) {
		this.customerMachine = customerMachine;
	}

	public void setWorkRequest(WorkRequest workRequest) {
		this.workRequest = workRequest;
	}

	@Override
	public CharSequence getType() {
		// TODO Auto-generated method stub
		WorkRequest wr = workRequest;
		if (workRequest != null)
			return workRequest.getMtype() + ":" + workRequest.getManufactcode();
		else
			return "无";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.pojo.Bpojo#search(java.lang.String)
	 */
	@Override
	public boolean search(String value) {
		// TODO Auto-generated method stub
		if (super.search(value) == false)
			if (false == workRequest.search(value))
				return customerMachine.search(value);
		return true;
	}

	public CustomerContact[] getCustomerContact() {
		return customerContact;
	}
	// thy-id 2014-1224-1128
	public void setCustomerContact(CustomerContact[] customerContact) {
		this.customerContact = customerContact;
		String str = "";
		for (CustomerContact cc : customerContact) {
			if (str.length() != 0)
				str += "\r\n";
			str += cc.getName() + ", " + cc.getEmail() + ", "
					+ ( cc.getAuto_email()==false?"不接受":"自动接受");
		}
		//str+="\r\n OK";
		this.workRequest.contactInfo = str;
	}

}
