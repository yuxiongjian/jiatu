/**
 * 
 */
package test;

import java.util.Date;

import org.alexd.jsonrpc.JSONRPCException;
import org.json.JSONObject;

import project.config.Config;
import project.config.DebugSetting;
import project.pojo.Bpojo;
import project.util.Func;
import project.util.MyLog;
import project.util.ProjectLocationListener;
import hardware.config.Handphone;

import com.google.gson.Gson;
import com.jt.act.TuYaActivity;
import com.jt.appservice.JtService;
import com.jt.pojo.CustomerMachine;
import com.jt.pojo.MachineLocation;
import com.jt.pojo.MobileLocation;
import com.jt.pojo.SignOrder;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

/**
 * @author thomasy
 * 
 */
@SuppressWarnings("unused")
public class Test {

	private static String s;
	private static Activity act;

	public static void testCheckValid(Context ctx)
	{
		JtService.ctx = ctx;
		boolean ret = JtService.checkValid(ctx.getPackageName());
		MyLog.Log(ret);
	}
	public static void test(Activity act) {
		// TODO Auto-generated method stub
		boolean go=true;
		//go=true;
		if ( go ) {
			//String cpu[]=Handphone.getCpuInfo();
			Test.act = act;
			//Object j = JSONObject.NULL;
			//MyLog.Log(j);
			//MyLog.Log(j.toString());
			testLogin(act);
			WorkOrder wo;
			Boolean ret;
			try {
				wo = JtService.loadWorkOrder(au.getSid(), au.getUsername(), 665834);
				ret = JtService.ResolveWorkOrder(au.getSid(), au.getUsername(),wo );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONRPCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//test调度();
			//testjtService(act);
			//testSaveMobile();
			//testCloseWorkOrder();
			// testDB.testdb(act);
			// testDB.testworkorder(act);

			// testResolve();
			// testCopy();
			// testGson();
			// testjtService(act);
			// TestAnnoation.test();
			MyLog.Log("test finish");;
		}
	}
	public static void test调度(){
		try {

			Object retworkworder = JtService.findDispatchingWorkOrder(sid,
					username,"",0);

			// retworkworder = JtService.CallJtService(WorkOrder[].class,
			// "callJSONArray", "findAssignedWorkOrder", sid, username);
			wos = (WorkOrder[]) retworkworder;

			
		} catch (Exception e) {

		}
		
		return;
	}
	public static void testCloseWorkOrder() {

		try {

			Object retworkworder = JtService.findAssignedWorkOrder(sid,
					username, WorkOrder.已提交);

			// retworkworder = JtService.CallJtService(WorkOrder[].class,
			// "callJSONArray", "findAssignedWorkOrder", sid, username);
			wos = (WorkOrder[]) retworkworder;

			Intent _intent = new Intent(act, TuYaActivity.class);
			SignOrder so = new SignOrder();
			so.workorders = new Integer[] { wos[0].getSysid() };
			_intent.putExtra("" + SignOrder.serialVersionUID, so);
			act.startActivityForResult(_intent,
					(int) TuYaActivity.serialVersionUID);
		} catch (Exception e) {

		}

	}

	public static void testSaveMobile() {
		try {
			MobileLocation mos[] = new MobileLocation[3];
			for (int i = 0; i < mos.length; i++) {

				MobileLocation mo = new MobileLocation();
				mo.setObjectValue();
				mo.setDeviceid(Config.devicid);
				mo.setMobile(Handphone.handphone.hp.getTel());
				mo.setLocation_type("GPS");
				mos[i] = mo;
			}
			Integer ret = JtService.saveMobileLocation(Config.devicid, mos);
			
			MyLog.Log("savebile test return:"+(ret==mos.length));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			MyLog.False("savebile test:"+ e1.getMessage());
		}
		
	}

	public static void testResolve() {
		try {

			Gson gs = Func.createGson();
			s = gs.toJson(new CopyA());
			MyLog.Log(s);
			JSONObject jo = new JSONObject(s);
			Object retworkworder = JtService.findAssignedWorkOrder(sid,
					username, WorkOrder.已到达);

			// retworkworder = JtService.CallJtService(WorkOrder[].class,
			// "callJSONArray", "findAssignedWorkOrder", sid, username);
			wos = (WorkOrder[]) retworkworder;

			WorkOrder wd = wos[0];

			int id = wd.getSysid();

			wd.setObjectValue();
			wd.setSysid(id);
			gs = Func.createGson();
			String ss = gs.toJson(wd);
			MyLog.Log(ss);
			Boolean ret = JtService.ResolveWorkOrder(sid, username, wd);

			/*
			 * for(WorkOrder wo:wos){ MyLog.Log(wo.checkValue()); }
			 */
			MyLog.Log(wd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyLog.False(e);
		}
	}

	public static class CopyA extends Bpojo {
		int i = 1;
		int j = 1;
		int k = 1;
		String s = "\"";
		Date d = new Date();
	}

	public static class CopyB {
		int i = 2;
		int j = 2;
		int s = 2;
	};

	public static void testCopy() {
		CopyA a = new CopyA();
		CopyB b = new CopyB();
		a.copyValue(b);
		MyLog.Log(a);

	}

	private static WorkOrder[] wos;

	static public class intgs {
		Integer i = 2;
		Integer j = 1;
	}

	public static void testGson() {
		Gson gs = Func.createGson();
		String s = gs.toJson(new intgs());
		String b = "{\"j\":1,\"i\":null}";
		intgs is = gs.fromJson(b, intgs.class);
		MyLog.Log(s);
	}

	public static String sid;
	public static String username;
	public static User au;
	private static Object retlognin;
	
	public static void testLogin(Activity ctx) {

		Handphone handphone = Handphone.getSetting(ctx);
		Object retlognin = null;
		handphone.hp.setDeviceID(Config.devicid);
		
			try {//99000458432697
				//retlognin = JtService.Login(handphone.hp.getDeviceID(), Config.Password);
				retlognin = JtService.Login("99000458440390", "123456");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONRPCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//retlognin = JtService.Login(handphone.hp.getDeviceID(), "");
		
		MyLog.Assert(retlognin.getClass().isAssignableFrom(User.class), "");
		au = (User) retlognin;
		MyLog.Assert(au.getSid() != null && au.getSid().length() > 0, "无效User");
		sid = au.getSid();
		username = au.getUsername();
		User.cUser = au;

	}

	public static void testjtService(Activity ctx) {

		Object ret = null;
		CustomerMachine ma = null;
		MachineLocation ml = null;
		Location ll = null;
		try {
			//testLogin(ctx);
			// Boolean ret = (Boolean) JtService.bindMobile(au, hp);

			try {
				ma = (CustomerMachine) JtService.findMachineByBarcode(au.getSid(),
						"MB2014040100018708");//

			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				ml = new MachineLocation();
				ProjectLocationListener.openLocationService(ctx);
				ll = ProjectLocationListener.getCurLoc();
				ml.setLocaton(ll);
				ml.setObjectValue();
				ml.setLocation_type("GPS");
				
				ml.setBarcode(ma.getBarcode());
				ml.setMachine_id(ma.getMachineid());
				JtService.saveMachineLocation(sid, ml);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				JtService.getMachineLocation(sid, ma.getBarcode());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				MobileLocation mos[] = new MobileLocation[3];
				for (int i = 0; i < mos.length; i++) {

					MobileLocation mo = new MobileLocation();
					mo.setObjectValue();
					mo.setDeviceid(au.getDeviceid());
					mo.setMobile(Handphone.handphone.hp.getTel());
					mo.setLocation_type("GPS");
					mos[i] = mo;
				}
				JtService.saveMobileLocation(sid, mos);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Integer[] count = (Integer[]) JtService.getAllWorkOrderCount(sid,
						username);
				MyLog.Log(count);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Object retworkworder = JtService.findAssignedWorkOrder(sid,
						username, 1);

				// retworkworder = JtService.CallJtService(WorkOrder[].class,
				// "callJSONArray", "findAssignedWorkOrder", sid, username);
				wos = (WorkOrder[]) retworkworder;

				/*
				 * for(WorkOrder wo:wos){ MyLog.Log(wo.checkValue()); }
				 */
				MyLog.Log(wos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MyLog.False(e);
			}

			try {

				ret = JtService.readWorkOrder(sid, username, wos[0].getSysid());

				WorkOrder wd = JtService.loadWorkOrder(sid, username,
						wos[0].getSysid());
				MyLog.Assert(wd.isUnRead() == false, "");

				MyLog.Log(ret);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				MyLog.False(e);
			}

			/*
			 * try { Intent _intent = new Intent(ctx,
			 * ReadWorkOrderActivity.class); _intent.putExtra(WorkOrder.Tag,
			 * wos[0]); ctx.startActivityForResult(_intent, 0); } catch
			 * (Exception e1) { // TODO Auto-generated catch block
			 * e1.printStackTrace(); }
			 */
			try {

				MyLog.Assert(wos[0].getStatus() == 1, "");
				ret = JtService.pickUpWorkOrder(sid, username,
						wos[0].getSysid());
				ret = JtService.loadWorkOrder(sid, username, wos[0].getSysid());
				MyLog.Assert(((WorkOrder) ret).getStatus() == 2, "");

				MyLog.Log(ret);
			} catch (Exception e) {
				MyLog.Assert(true, "");
				// TODO Auto-generated catch block
				MyLog.False(e);
			}

			try {

				ret = JtService.loadWorkOrder(sid, username, wos[0].getSysid());

				MyLog.Log(ret);
			} catch (Exception e) {
				MyLog.False(e);

			}

			try {

				ret = JtService.openWorkOrder(sid, username, wos[0].getSysid(), 0.0, 0.0);

				MyLog.Log(ret);
			} catch (Exception e) {
				MyLog.False(e);

			}
			/*
			 * try {
			 * 
			 * ret = JtService .closeWorkOrder(sid, username,
			 * wos[0].getSysid());
			 * 
			 * MyLog.Log(ret); } catch (Exception e) { MyLog.False(e);
			 * 
			 * }
			 */
			try {

				ret = JtService.findFinishedWorkOrder(sid, wos[0]
						.getWorkRequest().getMtype(), "", "", "");

				MyLog.Log(ret);
			} catch (Exception e) {
				MyLog.False(e);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
