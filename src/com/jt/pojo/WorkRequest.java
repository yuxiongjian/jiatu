package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.util.sqldb.DBHelper;
import project.util.sqldb.dao.impl.BaseDaoImpl;
import android.content.Context;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

/**
 * 工单请求，安排表，对应v_worksheet表
 * 
 */
@Table(name = "t_workrequest")
public class WorkRequest extends Bpojo {

	public static class WorkRequestImpl extends BaseDaoImpl<WorkRequest> {
		public WorkRequestImpl(Context context) {
			super(new DBHelper(context), WorkRequest.class);
		}
	}

	/**
	 * 
	 */
	public static final long serialVersionUID = WorkRequest.class.hashCode();

	// @PojoUI(PojoUIName="登记时间", PojoUIOrder = 0, PojoEditor="TextView")

	@Id
	@Column(name = "sysid")
	protected java.lang.Integer sysid;
	/** sysid 工单序号 **/

	@Column(name = "calltime")
	protected java.util.Date calltime;
	/** calltime 召唤时间 **/

	@Column(name = "technician_id")
	protected java.lang.String technician_id;
	/** technician_id 派工技术员 **/

	@Column(name = "technician2")
	@PojoUI(PojoUIName = "技术人员", PojoUIOrder = 00, PojoEditor = "TextView")
	protected java.lang.String technician2;
	/** technician2 派工技术员 **/

	@Column(name = "worktypename")
	@PojoUI(PojoUIName = "工作类型", PojoUIOrder = 01, PojoEditor = "TextView")
	protected java.lang.String worktypename;
	/** worktypename 工作类型 **/

	@Column(name = "barcode")
	@PojoUI(PojoUIName = "机器条码", PojoUIOrder = 10, PojoEditor = "TextView", Span = 3)
	protected java.lang.String barcode;
	/** barcode 机器条码 **/

	@Column(name = "acceptmemo")
	@PojoUI(PojoUIName = "受理备注", PojoUIOrder = 21, PojoEditor = "TextView", Span = 3)
	protected java.lang.String acceptmemo;
	/** acceptmemo 受理备注 **/

	@Column(name = "planedday")
	@PojoUI(PojoUIName = "预定日期", PojoUIOrder = 40, PojoEditor = "TextView")
	protected java.sql.Date planedday;
	/** planedday 预定日 **/

	@Column(name = "planedtime")
	@PojoUI(PojoUIName = "预定时间", PojoUIOrder = 41, PojoEditor = "TextView")
	protected java.sql.Time planedtime;
	/** planedtime 预定时间 **/

	@Column(name = "mtype")
	@PojoUI(PojoUIName = "机器型号", PojoUIOrder = 60, PojoEditor = "TextView")
	protected java.lang.String mtype;
	/** mtype 机型 **/
	@Column(name = "manufactcode")
	@PojoUI(PojoUIName = "机号", PojoUIOrder = 61, PojoEditor = "TextView")
	protected java.lang.String manufactcode;
	/** manufactcode 机号 **/

	@Column(name = "teltrbtype")
	@PojoUI(PojoUIName = "报修内容", PojoUIOrder = 80, PojoEditor = "TextView", Span = 3)
	protected java.lang.String teltrbtype;
	/** teltrbtype 报修内容 **/

	@Column(name = "probcode")
	@PojoUI(PojoUIName = "故障详细", PojoUIOrder = 91, PojoEditor = "TextView", Span = 3)
	protected java.lang.String probcode;
	/** probcode 故障详细 **/

	// @Column(name = "sudo_customer")
	@PojoUI(PojoUIName = "客户", PojoUIOrder = 100, PojoEditor = "TextView", Span = 3)
	protected java.lang.String sudo_customer;
	/**  **/

	// @Column(name = "sudo_address")
	@PojoUI(PojoUIName = "地址", PojoUIOrder = 120, PojoEditor = "TextView", Span = 3)
	protected java.lang.String sudo_address;
	/**  **/

	@Column(name = "maintetypename")
	protected java.lang.String status;
	/** msgsts 消息状态 **/
	@PojoUI(PojoUIName = "机器类型 ", PojoUIOrder = 140, PojoEditor = "TextView")
	protected java.lang.String maintetypename;
	/** maintetypename 机器类型 **/

	@Column(name = "contact")
	@PojoUI(PojoUIName = "客户联系人", PojoUIOrder = 141, PojoEditor = "TextView")
	protected java.lang.String contact;
	/** contact 联系人 **/

	@Column(name = "tel")
	@PojoUI(PojoUIName = "客户电话", PojoUIOrder = 160, PojoEditor = "TextView", System = "call", Span = 3)
	protected java.lang.String tel;
	/** tel 联系电话 **/

	@Column(name = "subtel")
	@PojoUI(PojoUIName = "联系人电话", PojoUIOrder = 180, PojoEditor = "TextView", System = "call", Span = 3)
	protected java.lang.String subtel;
	/** subtel 联系电话分机 **/
	
	// thy-id 2014-1224-1128
	@Column(name = "contactinfo")
	@PojoUI(PojoUIName = "联系人信息", PojoUIOrder = 200, PojoEditor = "TextView",  Span = 3)
	protected java.lang.String contactInfo;
	/** contactInfo 联系人信息 **/

	protected java.lang.String messageStatus;
	protected java.lang.String worktype;

	/**
	 * @return the worktype
	 */
	public java.lang.String getWorktype() {
		return worktype;
	}

	/**
	 * @param worktype
	 *            the worktype to set
	 */
	public void setWorktype(java.lang.String worktype) {
		this.worktype = worktype;
	}

	public java.lang.String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(java.lang.String messageStatus) {
		this.messageStatus = messageStatus;
	}

	protected java.lang.String usedname;
	/** usedname 使用类型 **/

	protected java.lang.String endflag;
	/** endflag 工单状态 **/
	protected java.lang.String groupid;
	/** groupid 派工技术员组Id **/
	protected java.lang.Integer machineid;
	/** machineid 机器序号 **/
	protected java.lang.Integer max_totalcopynum;
	/** max_TotalCopyNum 总复印张数 **/
	protected java.lang.Integer max_blacknum;
	/** max_BlackNum 机器序号 **/
	protected java.lang.Integer max_numzydc;
	/** max_NumZydc 专业点彩 **/
	protected java.lang.Integer max_numptdc;
	/** max_NumPtdc 普通点彩 **/
	protected java.lang.Integer max_colornum1;
	/** max_ColorNum1 专彩张数 **/
	protected java.lang.Integer max_colornum2;
	/** max_ColorNum1 普彩张数 **/
	protected java.lang.Integer max_totalplatenum;
	/** max_TotalPlateNum 总制版数 **/
	protected java.lang.Integer max_chokedpapernum;
	/** max_chokedPaperNum 卡纸数 **/
	protected Boolean needreader;// 是否需要抄张

	/**
	 * @return the needReader
	 */
	public Boolean getNeedReader() {
		return needreader;
	}

	/**
	 * @param needReader
	 *            the needReader to set
	 */
	public void setNeedReader(Boolean needReader) {
		this.needreader = needReader;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sysid == null) ? 0 : sysid.hashCode());
		return result;
	}

	public java.lang.Integer getSysid() {
		return sysid;
	}

	public void setSysid(java.lang.Integer sysid) {
		this.sysid = sysid;
	}

	public java.util.Date getCalltime() {
		return calltime;
	}

	public void setCalltime(java.util.Date calltime) {
		this.calltime = calltime;
	}

	public java.lang.String getTechnician_id() {
		return technician_id;
	}

	public void setTechnician_id(java.lang.String technician_id) {
		this.technician_id = technician_id;
	}

	public java.lang.String getTechnician2() {
		return technician2;
	}

	public void setTechnician2(java.lang.String technician2) {
		this.technician2 = technician2;
	}

	public java.lang.String getTeltrbtype() {
		return teltrbtype;
	}

	public void setTeltrbtype(java.lang.String teltrbtype) {
		this.teltrbtype = teltrbtype;
	}

	public java.lang.String getProbcode() {
		return probcode;
	}

	public void setProbcode(java.lang.String probcode) {
		this.probcode = probcode;
	}

	public java.lang.String getAcceptmemo() {
		return acceptmemo;
	}

	public void setAcceptmemo(java.lang.String acceptmemo) {
		this.acceptmemo = acceptmemo;
	}

	public java.sql.Date getPlanedday() {
		return planedday;
	}

	public void setPlanedday(java.sql.Date planedday) {
		this.planedday = planedday;
	}

	public java.sql.Time getPlanedtime() {
		return planedtime;
	}

	public void setPlanedtime(java.sql.Time planedtime) {
		this.planedtime = planedtime;
	}

	public java.lang.String getManufactcode() {
		return manufactcode;
	}

	public void setManufactcode(java.lang.String manufactcode) {
		this.manufactcode = manufactcode;
	}

	public java.lang.String getBarcode() {
		return barcode;
	}

	public void setBarcode(java.lang.String barcode) {
		this.barcode = barcode;
	}

	public java.lang.String getMtype() {
		return mtype;
	}

	public void setMtype(java.lang.String mtype) {
		this.mtype = mtype;
	}

	public java.lang.String getSudo_customer() {
		return sudo_customer;
	}

	public void setSudo_customer(java.lang.String sudo_customer) {
		this.sudo_customer = sudo_customer;
	}

	public java.lang.String getSudo_address() {
		return sudo_address;
	}

	public void setSudo_address(java.lang.String sudo_address) {
		this.sudo_address = sudo_address;
	}

	public java.lang.String getWorktypename() {
		return worktypename;
	}

	public void setWorktypename(java.lang.String worktypename) {
		this.worktypename = worktypename;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getMaintetypename() {
		return maintetypename;
	}

	public void setMaintetypename(java.lang.String maintetypename) {
		this.maintetypename = maintetypename;
	}

	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public java.lang.String getUsedname() {
		return usedname;
	}

	public void setUsedname(java.lang.String usedname) {
		this.usedname = usedname;
	}

	public java.lang.String getContact() {
		return contact;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getSubtel() {
		return subtel;
	}

	public void setSubtel(java.lang.String subtel) {
		this.subtel = subtel;
	}

	public java.lang.String getEndflag() {
		return endflag;
	}

	public void setEndflag(java.lang.String endflag) {
		this.endflag = endflag;
	}

	public java.lang.String getGroupid() {
		return groupid;
	}

	public void setGroupid(java.lang.String groupid) {
		this.groupid = groupid;
	}

	public java.lang.Integer getMachineid() {
		return machineid;
	}

	public void setMachineid(java.lang.Integer machineid) {
		this.machineid = machineid;
	}

	public java.lang.Integer getMax_totalcopynum() {
		return max_totalcopynum;
	}

	public void setMax_totalcopynum(java.lang.Integer max_totalcopynum) {
		this.max_totalcopynum = max_totalcopynum;
	}

	public java.lang.Integer getMax_blacknum() {
		return max_blacknum;
	}

	public void setMax_blacknum(java.lang.Integer max_blacknum) {
		this.max_blacknum = max_blacknum;
	}

	public java.lang.Integer getMax_numzydc() {
		return max_numzydc;
	}

	public void setMax_numzydc(java.lang.Integer max_numzydc) {
		this.max_numzydc = max_numzydc;
	}

	public java.lang.Integer getMax_numptdc() {
		return max_numptdc;
	}

	public void setMax_numptdc(java.lang.Integer max_numptdc) {
		this.max_numptdc = max_numptdc;
	}

	public java.lang.Integer getMax_colornum1() {
		return max_colornum1;
	}

	public void setMax_colornum1(java.lang.Integer max_colornum1) {
		this.max_colornum1 = max_colornum1;
	}

	public java.lang.Integer getMax_colornum2() {
		return max_colornum2;
	}

	public void setMax_colornum2(java.lang.Integer max_colornum2) {
		this.max_colornum2 = max_colornum2;
	}

	public java.lang.Integer getMax_totalplatenum() {
		return max_totalplatenum;
	}

	public void setMax_totalplatenum(java.lang.Integer max_totalplatenum) {
		this.max_totalplatenum = max_totalplatenum;
	}

	public java.lang.Integer getMax_chokedpapernum() {
		return max_chokedpapernum;
	}

	public void setMax_chokedpapernum(java.lang.Integer max_chokedpapernum) {
		this.max_chokedpapernum = max_chokedpapernum;
	}

}
