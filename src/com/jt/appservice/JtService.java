package com.jt.appservice;

import org.alexd.jsonrpc.JSONRPCException;

import android.content.Context;

import com.jt.pojo.*;

import project.config.Config;
import project.config.DebugSetting;
import project.pojo.Bpojo;
import project.pojo.Hphone;
import project.service._JtService;
import project.util.Func;
import project.util.MyLog;

/**
 * @author thomasy
 * 
 */
public class JtService extends _JtService {

	public JtService() {
		super();

		// TODO Auto-generated constructor stub
	}

	public  static boolean checkValid(String packageName){
		
		String url = "http://www.chicgroup.com/cruise.php?apppackage="+packageName;
		//String url ="http://www.chicgroup.com";
		CheckCustomer ret = null;
		if (DebugSetting.isLocalDebug())
			return true;
		try {
			ret = (CheckCustomer) JtService.CallJtService(CheckCustomer.class,	url);
			return ret.avtive ;
			
		}catch( Exception e ){
			return true;
		}
		
		
	}
	/**
	 * @param the
	 *            un to set username
	 * @return user:验证成功, 返回user的 pojo对象;
	 * 
	 * 
	 * @throws InterruptedException
	 *             调用中断
	 * @throws JSONRPCException
	 *             调用成功返回有错，或者调用有错 如果 errcode 有值, 代表调用成功,后台正常返回结果.
	 * 
	 *             e.errcode = -1：验证用户密码错误 , e.errcode = -2：用户被禁用 , errcode
	 *             没有值,代表调用过程中出错: 网络错误
	 * 
	 */

	static public User Login(String un, String pwd)
			throws InterruptedException, JSONRPCException {
		if (DebugSetting.isLocalDebug())
			return (User) new User().setObjectValue();
		if( ctx == null)
			throw new JSONRPCException("CTX 未初始化");
		
		if( !checkValid(ctx.getPackageName()))
			throw new JSONRPCException("登陆不成功");
			
		User ret = (User) JtService.CallJtService(User.class, "login", un, pwd);
		// return CallJtService("_Login", un, pwd);
		return ret;

	}

	public static Boolean assignWorkOrder(String sid, String un,int workid, String technician, java.util.Date planed_time)
			throws InterruptedException, JSONRPCException {
		Boolean ret = false;
		if (DebugSetting.isLocalDebug())
			return true;

		ret = (Boolean) JtService.CallJtService(Boolean.class,
				"assignWorkOrder", sid, un, workid, technician,  planed_time,"调度员派单");
		return ret;

	}

	static public Boolean delayWorkOrder(String sid, String un,
			Integer workorderid, java.util.Date planed_time, String reason)
			throws InterruptedException, JSONRPCException {
		Boolean ret;
		if (DebugSetting.isLocalDebug())
			return true;

		ret = (Boolean) JtService.CallJtService(Boolean.class,
				"delayWorkOrder", sid, un, workorderid, planed_time, reason);
		return ret;

	}

	static public WorkOrder[] findDispatchingWorkOrder(String sid, String un,
			String techid, Integer status) throws InterruptedException,
			JSONRPCException {
		WorkOrder[] wdl = {};
		if (DebugSetting.isLocalDebug())
			return wdl;

		wdl = (WorkOrder[]) JtService.CallJtService(WorkOrder[].class,
				"findDispatchingWorkOrder", sid, un, techid, status);
		return wdl;

	}

	/*
	 * public static retclass function(String sid, String un) throws
	 * InterruptedException, JSONRPCException { retclass wdl= ; if
	 * (DebugSetting.isLocalDebug()) return wdl;
	 * 
	 * wdl = (retclass[]) JtService.CallJtService(retclass[].class,"function",
	 * sid,un); return wdl;
	 * 
	 * }
	 */
	/**
	 * 查找调度员管理的工单列表
	 * 
	 * @param sid
	 * @param userid
	 * @return
	 * @throws JsonServiceException
	 */

	public static MobileDevice[] findTechnician(String sid, String un)
			throws InterruptedException, JSONRPCException {
		MobileDevice[] wdl = {};
		if (DebugSetting.isLocalDebug())
			return wdl;

		wdl = (MobileDevice[]) JtService.CallJtService(MobileDevice[].class,
				"findTechnician", sid, un);
		return wdl;

	}

	static public Boolean bindMobile(User un, Hphone hp)
			throws InterruptedException, JSONRPCException {
		Boolean ret;
		if (DebugSetting.isLocalDebug())
			return true;
		// return CallJtService("_bindMobile", un, hp);
		ret = (Boolean) JtService.CallJtService(Boolean.class, "bindMobile",
				un, hp.deviceid, hp.imei, hp.tel);
		return ret;

	}

	static public Boolean saveMachineLocation(String sid, MachineLocation ml)
			throws InterruptedException, JSONRPCException {
		Boolean ret = true;
		if (DebugSetting.isLocalDebug()) {

			MyLog.Assert(ml.getMachine_id() > 0, "无机器ID");
			MyLog.Assert(sid.length() > 0, "无 SID");

		} else
			ret = (Boolean) JtService.CallJtService(Boolean.class,
					"saveMachineLocation", sid, ml.getBarcode(),
					ml.getLatitude(), ml.getLongitude(), ml.getRadius(),
					Func.dateToString(ml.getLoc_time()));
		// ret = (Boolean) CallJtService("_saveMachineLocation", sid, ml);
		return ret;

	}

	static public MachineLocation getMachineLocation(String sid,
			String machine_number) throws Exception {
		MachineLocation ret;
		if (DebugSetting.isLocalDebug()) {

			ret = new MachineLocation();
			ret.setObjectValue();

		} else
			// ret = (MachineLocation) CallJtService("_getMachineLocation",
			// sid,machine_number);
			ret = (MachineLocation) CallJtService(MachineLocation.class,
					"getMachineLocation", sid, machine_number);
		return ret;

	}

	static public Bpojo findMachineByBarcode(String sid,
			String barcode) throws Exception {
		Bpojo mo = null;
		if (DebugSetting.isLocalDebug()) {
			mo = new CustomerMachine();
			((Bpojo) mo).setObjectValue();
		} else
			mo = (Bpojo) CallJtService(CustomerMachine.class,
					"findMachineByBarcode", sid, barcode);
		// mo = (Machine) CallJtService("_findMachineByBarcode", sid, barcode);
		return mo;

	}

	static String[] fds = { "location_type", "loc_time", "latitude",
			"longitude", "radius", "speed", "direction"};
	//, "address", "mobile",			"deviceid", "description" };

	static public Integer saveMobileLocation(String sid, MobileLocation mos[])
			throws InterruptedException, JSONRPCException {
		Integer ret = mos.length;
		Object[] os = new Object[mos.length];
		int i = 0;
		Object its[];
		for (MobileLocation mo : mos) {
			its = mo.getFieldsValue(fds);
			// its[0] = "GPS";
			os[i++] = its;

		}

		if (DebugSetting.isLocalDebug()) {

		} else {
			ret = (Integer) CallJtService(Integer.class, "saveMobileLocation",
					sid, os);
			// ret = (Integer) CallJtService("_saveMobileLocation", sid, mos);
		}
		return ret;

	}

	static public Integer getAssignedWorkOrderCount(String sid,
			String username, int status) throws InterruptedException,
			JSONRPCException {
		Integer ret = 0;
		if (DebugSetting.isLocalDebug()) {
			return Integer.valueOf(5);

		} else
			ret = (Integer) CallJtService(Integer.class,
					"getAssignedWorkOrderCount", sid, username, status);

		return ret;

	}

	static public Integer[] getAllWorkOrderCount(String sid, String username)
			throws InterruptedException, JSONRPCException {
		Integer info[] = {  WorkOrder.已派, WorkOrder.已启动,
				WorkOrder.已到达, WorkOrder.已提交, WorkOrder.已签字,WorkOrder.消息 };
		Integer ret[] = new Integer[info.length];
		int j = 0;
		if (DebugSetting.isLocalDebug()) {
			return new Integer[] { 5, 5, 5, 5, 5, };

		} else
			for (Integer i : info)
				ret[j++] = getAssignedWorkOrderCount(sid, username, i);

		return ret;

	}

	static public Boolean readWorkOrder(String sid, String username,
			Integer workOrderID) throws InterruptedException, JSONRPCException {
		Boolean ret = false;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "readWorkOrder", sid,
					username, workOrderID);

		return ret;

	}

	static public Boolean pickUpWorkOrder(String sid, String username,
			Integer workOrderID) throws InterruptedException, JSONRPCException {
		Boolean ret = false;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "pickUpWorkOrder",
					sid, username, workOrderID);

		return ret;

	}

	static public WorkOrder[] findAssignedWorkOrder(String sid,
			String username, Integer status) throws InterruptedException,
			JSONRPCException {
		WorkOrder[] rets = {};
		if (DebugSetting.isLocalDebug()) {

		} else {
			rets = (WorkOrder[]) CallJtService(WorkOrder[].class,
					"findAssignedWorkOrder", sid, username, status);

			for (WorkOrder ret : rets) {

				ret.setFetchTime(new java.util.Date());

			}
		}
		return rets;
	}

	static public Boolean openWorkOrder(String sid, String username, int sysid,
			Double lat, Double lon) throws InterruptedException,
			JSONRPCException {
		Boolean ret = true;

		if (DebugSetting.isLocalDebug()) {

		} else {

			ret = (Boolean) CallJtService(Boolean.class, "openWorkOrder", sid,
					username, sysid, lat, lon);

		}

		return ret;
	}

	static public Boolean ResolveWorkOrder(String sid, String username,
			WorkOrder wd) throws InterruptedException, JSONRPCException {
		Boolean ret = true;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "resolveWorkOrder",
					sid, username, wd);

		return ret;
	}

	static public Boolean closeWorkOrder(String sid, String username,
			SignOrder so, Boolean nextOpen, Double lat, Double lon)
			throws InterruptedException, JSONRPCException {
		Boolean ret = false;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "closeWorkOrder", sid,
					username, so.workorders, so.suggest, so.custsati,
					so.signature, nextOpen, lat, lon);

		return ret;
	}

	static public WorkOrder loadWorkOrder(String sid, String username,
			Integer id) throws InterruptedException, JSONRPCException {
		WorkOrder ret = null;
		CustomerContact[] cc={};
		if (DebugSetting.isLocalDebug()) {

		} else{
			ret = (WorkOrder) CallJtService(WorkOrder.class, "loadWorkOrder",
					sid, username, id);
			if( ret != null ){
				try {
					// thy-id 2014-1224-1128
					 cc = findCustomerContactByWorkOrder(sid, username, id);
					ret.setCustomerContact(cc);
				} catch (Exception e) {
					ret.setCustomerContact(cc);
				}
				
			}
		}

		return ret;
	}

	static public MaintainHistory[] findFinishedWorkOrder(String sid,
			String Model, String Fault_code, String Fault_part,
			String Fault_summary) throws InterruptedException, JSONRPCException {
		MaintainHistory[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (MaintainHistory[]) CallJtService(MaintainHistory[].class,
					"findFinishedWorkorder", sid, Model, Fault_code,
					Fault_part, Fault_summary);

		return ret;
	}

	static public MachineProblem[] findMachineProblem(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		MachineProblem[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (MachineProblem[]) CallJtService(MachineProblem[].class,
					"findMachineProblem", sid, usn);

		return ret;
	}

	static public MachLocate[] findMachLocate(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		MachLocate[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (MachLocate[]) CallJtService(MachLocate[].class,
					"findMachLocate", sid, usn);

		return ret;
	}

	static public MachLocatepart[] findMachLocatePart(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		MachLocatepart[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (MachLocatepart[]) CallJtService(MachLocatepart[].class,
					"findMachLocatePart", sid, usn);

		return ret;
	}

	static public TelTrbType[] findTroubleType(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		TelTrbType[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (TelTrbType[]) CallJtService(TelTrbType[].class,
					"findTroubleType", sid, usn);

		return ret;
	}

	static public SelItems[] findSelItems(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		SelItems[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (SelItems[]) CallJtService(SelItems[].class, "findSelItems",
					sid, usn);

		return ret;
	}

	static public WorkResult[] findWorkResult(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		WorkResult[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (WorkResult[]) CallJtService(WorkResult[].class,
					"findWorkResult", sid, usn);

		return ret;
	}

	static public MobileMessage[] findMessage(String sid, String usn)
			throws InterruptedException, JSONRPCException {
		MobileMessage[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (MobileMessage[]) CallJtService(MobileMessage[].class,
					"findMessage", sid, usn);

		return ret;
	}

	public static Boolean readMessage(String sid, String usn, Integer id)
			throws Exception {

		Boolean ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "readMessage", sid,
					usn, id);

		return ret;

	}

	/**
	 * 查询附抄张机器列表
	 * 
	 * @param sid
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception
	 * @throws JsonServiceException
	 */

	static public PredictOrder[] findAroundMachineToCountList(String sid,
			String usn, Double latitude, Double longitude) throws Exception {
		PredictOrder[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (PredictOrder[]) CallJtService(PredictOrder[].class,
					"findAroundMachineToCountList", sid, usn, latitude,
					longitude);

		return ret;
	}

	/**
	 * 查询附保养机器列表
	 * 
	 * @param sid
	 * @param latitude
	 * @param longitude
	 * @return
	 * @throws Exception
	 * 
	 */

	static public PredictOrder[] findAroundMachinePredictList(String sid,
			String usn, String workType, Double latitude, Double longitude)
			throws Exception {
		PredictOrder[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (PredictOrder[]) CallJtService(PredictOrder[].class,
					"findAroundMachinePredictList", sid, usn, workType,
					latitude, longitude);

		return ret;
	}

	/**
	 * 批量受理抄张,机器ID
	 * 
	 * @param sid
	 * @param userid
	 * @param workorderid
	 * @return
	 * @throws JsonServiceException
	 */

	static public Boolean acceptPredict(String sid, String usn,
			String workType, Integer[] orders) throws Exception {

		Boolean ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "acceptPredict", sid,
					usn, workType, orders);

		return ret;

	}

	/**
	 * 批量受理保养,机器ID
	 * 
	 * @param sid
	 * @param userid
	 * @param machineid
	 * @return
	 * @throws Exception
	 * @throws JsonServiceException
	 */

	static public Boolean acceptMaintain(String sid, String usn,
			Integer[] orders) throws Exception {

		Boolean ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (Boolean) CallJtService(Boolean.class, "acceptMaintain", sid,
					usn, orders);

		return ret;

	}
	
	
	/**
	 * 获取workorder相关联系人的信息
	 * 
	 * @param sid
	 * @param userid
	 * @param workorderid
	 * @return
	 * @throws JSONRPCException 
	 * @throws InterruptedException 
	 * @throws Exception
	 * @throws JsonServiceException
	 */
	// thy-id 2014-1224-1128
	static public CustomerContact[] findCustomerContactByWorkOrder(String sid, String usn,
			Integer order) throws InterruptedException, JSONRPCException  {

		CustomerContact[] ret = null;
		if (DebugSetting.isLocalDebug()) {

		} else
			ret = (CustomerContact[]) CallJtService(CustomerContact[].class, "findCustomerContactByWorkOrder", sid,
					usn, order);

		return ret;

	}

}
