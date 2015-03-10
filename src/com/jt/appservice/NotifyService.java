/**
 * 
 */
package com.jt.appservice;

import hardware.config.Handphone;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.alexd.jsonrpc.JSONRPCException;

import com.jt.appservice.JtService;
import com.jt.AppLoginActivity;
import com.jt.pojo.MobileLocation;
import com.jt.pojo.MobileLocation.MobileLocationImpl;
import com.jt.pojo.MobileMessage;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder.WorkOrderImpl;
import com.jt.R;

import project.util.Func;
import project.util.MyLog;
import project.util.ProjPref;
import project.util.ProjectLocationListener;
import project.config.DebugSetting;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author thomasy
 * 
 */
public class NotifyService extends Service {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	Context ctx = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */

	static String lasttime = "2013-05-02 14:15";
	static int set = 0;
	PendingIntent mAlarmSender = null;

	void initAlarm(Context context, long militime) {

		mAlarmSender = PendingIntent.getService(context, 0, new Intent(context,
				NotifyService.class), 0);

		long firstTime = SystemClock.elapsedRealtime();

		AlarmManager am = (AlarmManager) context
				.getSystemService(Activity.ALARM_SERVICE);
		ctx = context;
		// am.cancel(mAlarmSender);

		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
				militime, mAlarmSender);

	}

	int i = 0;
	private int iMinute = 5;

	protected void sendOutLocation(User cUser) {

		Location loc = null;
		Time ts = null;
		Time te = null;
		if (ProjectLocationListener.mLocationManager == null && ctx != null) {
			ProjectLocationListener.openLocationService(ctx);

		}
		if( cUser != null ){
		 ts = (Time) Func.toSQLTime(cUser.getTracking_begin_time());
		 te = (Time) Func.toSQLTime(cUser.getTracking_end_time());
		}
		// sid = cUser.getSid();

		long ct = Calendar.getInstance().getTime().getTime()
				% (1000 * 3600 * 24);
		String sid = Handphone.handphone.hp.deviceid;
		loc = ProjectLocationListener.getCurLoc();
		MobileLocationImpl mbDao = new MobileLocationImpl(ctx);
		List<MobileLocation> mo_save = mbDao.find();
		MobileLocation curMl = new MobileLocation(cUser, loc);
		mo_save.add(curMl);
		// MobileLocation[] mos = { new MobileLocation(cUser, loc)};
		MobileLocation[] mos_save = mo_save.toArray(new MobileLocation[0]);
		Integer ret=null;
		try {
			if (cUser == null
					|| (loc != null && ct > ts.getTime() && ct < te.getTime())
					|| DebugSetting.isDebug()) {

				ret = JtService.saveMobileLocation(sid, mos_save);
				mbDao.delete();
				

			}
		} catch (Exception e) {
			if ( ret == null )
				mbDao.insert(curMl);
			if( DebugSetting.isDebug()){
				
				Toast t = Toast.makeText(this, "sendLocation:" + e.getMessage(),
					Toast.LENGTH_SHORT);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
		}
	}

	public void showMessage(User cUser) throws InterruptedException,
			JSONRPCException {

		MobileMessage[] infoList = {};

		if (cUser != null) {
			infoList = JtService.findMessage(cUser.getSid(),
					cUser.getUsername());

			for (MobileMessage info : infoList) {
				if (info.isUnRead())
					showNotification(R.drawable.ic_launcher, info.getName()
							.toString(), info.getBref().toString(), info
							.getTime().toString());

			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Context context = getBaseContext();

		if (mAlarmSender == null) {

			initAlarm(context, 60 * 1000);
		}

		i++;
		if (DebugSetting.isDebug())
			iMinute = 1;
		if (i >= iMinute) {
			lasttime = (String) ProjPref.GetPref(this, "infotime", lasttime);

			i = 0;
			User cUser = User.cUser;
			try {

				showMessage(cUser);

				sendOutLocation(cUser);
			} catch (JSONRPCException e) {
				String s = MyLog.toString(e);
				Toast.makeText(this, "findMessage:" + e.getMessage(),
						Toast.LENGTH_LONG).show();
			} catch (InterruptedException e) {
				MyLog.Log(e);
			} catch (Exception e) {
				e.printStackTrace();
				if( DebugSetting.isDebug())
					Toast.makeText(this, "onStartCommand:" + e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		} else {
			;
		}
		// MyLog.Log("Service:" + Func.dateToString(new Date()));
		return super.onStartCommand(intent, flags, startId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		MyLog.Log("onBind");
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	public void showNotification(int icon, String tickertext, String title,
			String content) {
		// 设置一个唯一的ID，随便设置
		int notification_id = 100000;
		NotificationManager nm;
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Notification管理器
		Notification notification = new Notification(icon, tickertext,
				System.currentTimeMillis());
		// 后面的参数分别是显示在顶部通知栏的小图标，小图标旁的文字（短暂显示，自动消失）系统当前时间（不明白这个有什么用）
		notification.defaults = Notification.DEFAULT_LIGHTS
				| Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
		// 这是设置通知是否同时播放声音或振动，声音为Notification.DEFAULT_SOUND
		// 振动为Notification.DEFAULT_VIBRATE;
		// Light为Notification.DEFAULT_LIGHTS，在我的Milestone上好像没什么反应
		// 全部为Notification.DEFAULT_ALL
		// 如果是振动或者全部，必须在AndroidManifest.xml加入振动权限
		PendingIntent pt = PendingIntent.getActivity(this, 0, new Intent(this,
				AppLoginActivity.class), 0);
		// 点击通知后的动作，这里是转回main 这个Acticity
		notification.setLatestEventInfo(this, title, content, pt);
		nm.notify(notification_id, notification);

	}

}
