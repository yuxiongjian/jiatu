package config;

import com.jt.pojo.MobileLocation;
import com.jt.pojo.PODetail;
import com.jt.pojo.RecordCopy;
import com.jt.pojo.WorkOrder;
import com.jt.pojo.WorkRequest;

import project.config.Config;
import project.config.DebugSetting;
import project.config.DebugSetting.DEBUG;



public class MyConfig extends Config {
	
	
	public static  Class<?>[] DBCLASS ={ MobileLocation.class, WorkOrder.class,WorkRequest.class, RecordCopy.class, PODetail.class};
	;
	
	public static void init() {
		
		
		DebugSetting.debug = DEBUG.RELEASE; //Config.真实模拟id = "99000458448801";
		
		Config.DBCLASS =DBCLASS;
		Config.DBNAME = "com_jt_jiatuapp";
		Config.DBVERSION = 53;
		Config.mobileType = "mobile";
		
	}



	
}
