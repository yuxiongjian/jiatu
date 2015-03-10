package com.jt.act.workprocess;

import java.sql.Date;
import java.util.Calendar;

import org.alexd.jsonrpc.JSONRPCException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.CustomerMachine;
import com.jt.pojo.ReArrange;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder;
//import com.jt.pojo.WorkOrderMessage;
import com.jt.pojo.WorkRequest;

import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.util.MyLog;
import project.util.ProjectLocationListener;
import project.util.zxing.act.CaptureActivity;

public class ReadWorkOrderActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ReadWorkOrderActivity.class
			.hashCode();
	private WorkOrder wd;

	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		super.onCreate(savedInstanceState);
		wd = null;
		wd = (WorkOrder) getIntent().getSerializableExtra(
				"" + WorkOrder.getSerialversionuid());
		if (wd.getStatus() == WorkOrder.已派)
			setContentView(R.layout.read_workorder);
		else if (wd.getStatus() == WorkOrder.已启动)
			setContentView(R.layout.open_workorder);
		// workorderForm = new ProjectTableForm();
		WorkRequest wr = wd.getWorkRequest();
		// WorkOrderMessage wm = wd.getWorkMessage();
		CustomerMachine wc = wd.getCustomerMachine();
		wr.setSudo_address(wc.getAddress());
		wr.setSudo_customer(wc.getCustomer_name());

		ProjectTableForm.initFormLayout(thisAct, R.id.readworkordertable,
				wd.getWorkRequest(), null);

	}

	public void onResume() {
		super.onResume();
		// 代码
	}

	@Override
	public void OnOK(View v) {
		Boolean ret = null;
		super.OnOK(v);
		try {
			if (wd.getStatus() == WorkOrder.已派)// 已派
			{
				ret = JtService.pickUpWorkOrder(getAu().getSid(), getAu()
						.getUsername(), wd.getSysid());
				if (ret == true) {
					Toast.makeText(thisAct, "启动成功", Toast.LENGTH_SHORT).show();
				}
				finish();
			} else if (wd.getStatus() == WorkOrder.已启动)// 已经pickup
			{

				MyLog.Log(wd.getWorkRequest().getBarcode());
				Intent _intent = new Intent(thisAct, CaptureActivity.class);
				_intent.putExtra("testbarcode", wd.getWorkRequest()
						.getBarcode());
				thisAct.startActivityForResult(_intent,
						(int) CaptureActivity.serialVersionUID);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(thisAct, "启动失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {

		}

		// TODO Auto-generated method stub

	}

	public static int OpenWorkOrder(Activity thisAct, String bc,
			String barcode, int wdsysid) {

		Boolean ret = false;
		if (bc == null || bc.compareTo(barcode) == 0) {

			Object reto;
			try {
				reto = JtService.openWorkOrder(AppLoginActivity.getUser()
						.getSid(), AppLoginActivity.getUser().getUsername(),
						wdsysid, ProjectLocationListener.getCurLoc()
								.getLatitude(), ProjectLocationListener
								.getCurLoc().getLongitude());
				ret = (Boolean) reto;
				if (!ret)
					Toast.makeText(thisAct, "后台操作失败", Toast.LENGTH_LONG).show();
				else {
					Toast.makeText(thisAct, "维修启动成功", Toast.LENGTH_LONG).show();

					// thisAct.finish();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(thisAct, "调用异常:" + e.getMessage(),
						Toast.LENGTH_LONG).show();

				return 1;

			}

		} else
			Toast.makeText(thisAct, "扫描条码 " + bc + " 不符合 " + barcode,
					Toast.LENGTH_LONG).show();

		return 1;
	}

	@SuppressLint("ShowToast")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		final String barcode = wd.getWorkRequest().getBarcode();
		if (requestCode == (int) CaptureActivity.serialVersionUID) {
			if (resultCode == Activity.RESULT_OK) {

				String bc = data.getStringExtra("barcode");
				OpenWorkOrder(thisAct, bc, barcode, wd.getSysid());

			} else if (resultCode == Activity.RESULT_CANCELED) {

				CustomDialog.VerifyToOpenWorkOrder(thisAct, barcode, "扫描失败",
						"您确定开始维修编码为：\r\n" + barcode
								+ " \r\n的打印机么? 后台将记录此次非扫描的启动!", wd);

			}

		}
	}

	public void handleProjectMessage(Message msg) {
		super.handleProjectMessage(msg);
		// TODO Auto-generated method stub
		if (msg.what == "VerifyToOpenWorkOrder".hashCode()) {
			if (msg.arg1 == 1) {
				Bundle data = msg.getData();
				MyLog.Assert(data!=null, "");
				String barcode = data.getString("barcode");
				wd = (WorkOrder) data.getSerializable("workorder");
				
				
				OpenWorkOrder(this, null, barcode, wd.getSysid());
				finish();

			}
		} else if (msg.what == "OpenReArrangeDialog".hashCode())
			if (msg.arg1 == 1) {
				//ReArrange ra = (ReArrange) msg.obj;
				//String msgstr = "重新安排工单出错:";
				
				finish();

			}
	}

	
	@Override
	public void OnCancel(View v) {
		super.OnCancel(v);
		Boolean ret = false;
		String msg = "";
		// TODO Auto-generated method stub
		try {
			if (wd.getStatus() == WorkOrder.已派) {// 已派
				msg = "工单读取失败，";
				if (wd.isUnRead()) // 未读
					ret = JtService.readWorkOrder(getAu().getSid(), getAu()
							.getUsername(), wd.getSysid());
				finish();
			} else if (wd.getStatus() == WorkOrder.已启动) {

				msg = "工单无法到达设置失败，";
				CustomDialog.OpenReArrangeDialog(this,wd.getSysid());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, msg + e.getMessage(), Toast.LENGTH_LONG)
					.show();
			// MyLog.Loge(e);

		} finally {
			// finish();
		}

	}

	private void OpenReArrangeDialog() {
		// TODO Auto-generated method stub
		final Dialog alertDialog = new AlertDialog.Builder(thisAct)
				.setTitle("无法到达").setMessage("!!!")
				.setIcon(R.drawable.ic_launcher).create();
		View verify_v = View.inflate(alertDialog.getContext(),
				R.layout.verify_rearrange, null);
		final TextView tvr = (TextView) verify_v.findViewById(R.id.reason);
		final DatePicker datePicker = (DatePicker) verify_v.findViewById(R.id.nextdate);
		final TimePicker timePicker = (TimePicker) verify_v.findViewById(R.id.nexttime);
		verify_v.findViewById(R.id.BT_OK).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if( tvr.getText()==null || tvr.length()==0 ){
							Toast.makeText(v.getContext(), "请填写原因", Toast.LENGTH_LONG).show();
							return;
						}

						Message msg = new Message();
						msg = Message.obtain(msgHandler, "OpenReArrangeDialog".hashCode());
						msg.arg1 = 5;
						ReArrange ro = new ReArrange();
						ro.setReason(tvr.getText().toString());
						int month = datePicker.getMonth();
						int year = datePicker.getYear();
						int day = datePicker.getDayOfMonth();
						String msgstr = "工单无法到达设置失败，";
						Calendar cd = Calendar.getInstance();
						cd.clear();
						int hour = timePicker.getCurrentHour();
						int minute = timePicker.getCurrentMinute();
						
						cd.set(year, month, day,hour,minute);
						java.util.Date dt = new Date(cd.getTime().getTime());
						try {
							JtService.delayWorkOrder(User.cUser.getSid(), User.cUser.getUsername(), wd.getSysid(),
									cd.getTime(), tvr.getText().toString());
						} catch (InterruptedException e) {
							Toast.makeText(v.getContext(), msgstr + e.getMessage(), Toast.LENGTH_LONG)
							.show();
						} catch (JSONRPCException e) {
							Toast.makeText(v.getContext(), msgstr + e.getMessage(), Toast.LENGTH_LONG)
							.show();
						}						
								
						//ro.setNewDate(dt);
						//msg.obj= ro;
						// hh.sendMessage(msg1); //To-Learn: 一定要用 sendtotarget
						msg.sendToTarget();
						// dialog.dismiss();
					}

				});
		verify_v.findViewById(R.id.BT_CANCEL).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						alertDialog.dismiss();
					}
				});
		// HashMap<String, ViewHolder> view =
		// ProjectTableForm.initFormLayout(alertDialog, R.id.formtable, new
		// ReArrange(), null)
		
		alertDialog.show();
		//alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); 
		alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		alertDialog.setContentView(verify_v);
	}

}
