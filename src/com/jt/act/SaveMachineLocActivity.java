package com.jt.act;

import java.util.Date;

import project.config.Config;
import project.config.DebugSetting;
import project.ui.ProjectActivity;
import project.util.ProjectLocationListener;
import project.util.zxing.act.CaptureActivity;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.CustomerMachine;
import com.jt.pojo.MachineLocation;
import com.jt.pojo.User;

public class SaveMachineLocActivity extends ProjectActivity {

	/**
	 * 
	 */
	private static final long serialVersionUID = SaveMachineLocActivity.class
			.hashCode();

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.ui.BaiduLocationOverlay#onUpdateLocation(com.baidu.location.
	 * BDLocation)
	 */

	public void onUpdateLocation(BDLocation location) {
		if (location == null)
			return;
		// TODO Auto-generated method stub
		// super.onUpdateLocation(location);
		if (machine_loc == null)
			machine_loc = new Location("");
		machine_loc.setLatitude(location.getLatitude());
		machine_loc.setLongitude(location.getLongitude());
		machine_loc.setProvider(location.getProvince());
		// Gson gs = project.util.Func.createGson();

		// String ls = location.toJsonString();
		// machine_loc = gs.fromJson( ls, Location.class);
		updateUILocation();
	}

	public Location machine_loc;
	private TextView barcode_view;
	private ImageButton bt_scan;
	TextView provider_view;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// setContentView(R.layout.main);
		
		setContentView(R.layout.set_machine_location);
		//setContentView(new  test.BaiduLocationOverlayView(this, getBaseContext(), null, null).createView());
		super.onCreate(savedInstanceState);
		machine_loc = ProjectLocationListener.getCurLoc();
		barcode_view = (TextView) this.findViewById(R.id.printercode);
		provider_view = (TextView) this.findViewById(R.id.provider);
		// barcode_view.
		bt_scan = (ImageButton) this.findViewById(R.id.BT_SCAN);
		if (bt_scan != null)
			bt_scan.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent _intent = new Intent(thisAct, CaptureActivity.class);
					thisAct.startActivityForResult(_intent, 1000);
				}
			});
		if (DebugSetting.isDebug()&& barcode_view!=null)
			barcode_view.setText(Config.BARCODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1000) {
			if (resultCode == Activity.RESULT_OK)
				barcode_view.setText(data.getStringExtra("barcode"));

		}
	}

	/*
	 * _intent = new Intent(ThisAct, SaveMachineLocActivity.class);
	 * ThisAct.startActivityForResult(_intent, 0);
	 */
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		// ProjectLocationListener.regHandle(msgHandler);
		// updateUILocation();

	}

	void updateUILocation() {

		CharSequence barcode = this.barcode_view.getText();
		TextView tv1 = (TextView) this.findViewById(R.id.latitude);
		TextView tv = (TextView) this.findViewById(R.id.longitude);
		if (this.machine_loc == null || barcode == null
				|| barcode.toString().trim().length() < 5) {
			if (btok != null)
				this.btok.setEnabled(false);
		} else if (btok != null)
			this.btok.setEnabled(true);

		if (machine_loc == null) {
			tv.setText("");

			tv1.setText("");
			provider_view.setText("");

		} else {

			tv.setText("" + machine_loc.getLongitude());

			tv1.setText("" + machine_loc.getLatitude());

			// GeoPoint p= new GeoPoint(
			// (int)(machine_loc.getLatitude()*1E6),(int)(machine_loc.getLongitude()*1E6));

			// mMapController.setCenter(p);
			if (machine_loc.getProvider() != null)
				provider_view.setText(machine_loc.getProvider());
		}

	}

	public void handleLocationMsg(Object l) {
		BDLocation bd = new BDLocation();
		if (BDLocation.class.isInstance(l)) {
			bd = (BDLocation) l;
		} else if (Location.class.isInstance(l)) {
			machine_loc = (Location) l;

			bd.setLatitude(machine_loc.getLatitude());
			bd.setLongitude(machine_loc.getLongitude());
			bd.setRadius(machine_loc.getAccuracy());
			bd.setDerect(machine_loc.getBearing());
		}
		// this.myListener.onReceiveLocation(bd);
		onUpdateLocation(bd);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.save_machine_loc, menu);
		return true;
	}

	

	/**
	 * A placeholder fragment containing a simple view.
	 */

	String Submit_ok = "保存位置提交成功";// MyAct.getResources().getString(R.string.submit_error);
	String Submit_error = "保存位置提交失败";// MyAct.getResources().getString(R.string.submit_ok);

	@Override
	public void OnOK(View v) {
		User au = AppLoginActivity.getUser();
		// TODO Auto-generated method stub
		String barcode;
		TextView tv = (TextView) this.findViewById(R.id.printercode);

		CustomerMachine ma;
		try {
			barcode = (String) tv.getText().toString();
			Object rt = JtService.findMachineByBarcode(au.getSid(), barcode);
			if (rt == null || rt.getClass().isAssignableFrom(Integer.class)) {
				Toast.makeText(thisAct, "查询不到此编码的设备", 5000).show();
				return;
			}
			ma = (CustomerMachine) rt;
			MachineLocation ml = new MachineLocation();

			ml.setLocaton(this.machine_loc);

			ml.setBarcode(ma.getBarcode());
			ml.setMachine_id(ma.getMachineid());
			ml.setLoc_time(new Date());

			Boolean ret = (Boolean) JtService.saveMachineLocation(au.getSid(),
					ml);

			if (ret) {
				Toast.makeText(this, Submit_ok, Toast.LENGTH_LONG).show();
				ProjectLocationListener.unRegHandle(msgHandler);
				finish();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, Submit_error + ":" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		// .CallBpmService("CompleteStep", TaskID, new Integer(1), st);
		// ret = BpmService.CompleteStep(TaskID, 1, st);
	}

	@Override
	public void OnCancel(View v) {

		// TODO Auto-generated method stub
		super.OnCancel(v);
		ProjectLocationListener.unRegHandle(msgHandler);
		finish();
	}

}
