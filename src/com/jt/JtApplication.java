package com.jt;


import com.baidu.mapapi.SDKInitializer;

import config.MyConfig;

import android.app.Application;


public class JtApplication extends Application {

	/* (non-Javadoc)
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//strKey = "knRp54bdprkSUFdkBl8lGHXW";
		super.onCreate();
		SDKInitializer.initialize(this);
		MyConfig.init();
		
	}
}