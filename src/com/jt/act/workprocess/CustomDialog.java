package com.jt.act.workprocess;

import java.sql.Date;
import java.util.Calendar;

import org.alexd.jsonrpc.JSONRPCException;

import project.ui.ProjectActivity;
import project.ui.ProjectActivity.MyHandler;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.ReArrange;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder;

public class CustomDialog {
	
	public static void VerifyToOpenWorkOrder(final ProjectActivity act,
			final String barcode, String title, String msg, final WorkOrder wd) {
		Dialog alertDialog = new AlertDialog.Builder(act).setTitle(title)
				.setMessage(msg).setIcon(R.drawable.ic_launcher)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						//OpenWorkOrder(ctx, null, barcode, wd.getSysid());
						MyHandler hh = act.getMsgHandler();
						//Message msg2 = hh.obtainMessage("VerifyToOpenWorkOrder".hashCode());
						Message msg =  Message.obtain(hh,"VerifyToOpenWorkOrder".hashCode());
						msg.arg1 = 1;
						
						Bundle data = new Bundle();
						data.putSerializable("workorder", wd);
						data.putString("barcode", barcode);
						msg.setData(data);
						
						
						msg.sendToTarget();

					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();
		alertDialog.show();
	}

	public static void OpenReArrangeDialog(final ProjectActivity act,final int  wdid) {
		// TODO Auto-generated method stub
		final Dialog alertDialog = new AlertDialog.Builder(act)
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
							JtService.delayWorkOrder(User.cUser.getSid(), User.cUser.getUsername(), wdid,
									cd.getTime(), tvr.getText().toString());
						} catch (InterruptedException e) {
							Toast.makeText(v.getContext(), msgstr + e.getMessage(), Toast.LENGTH_LONG)
							.show();
						} catch (JSONRPCException e) {
							Toast.makeText(v.getContext(), msgstr + e.getMessage(), Toast.LENGTH_LONG)
							.show();
						}						
								
				 
						Message msg  = Message.obtain(act.getMsgHandler(), "OpenReArrangeDialog".hashCode());
						msg.arg1 = 1;
						msg.sendToTarget();//To-Learn: 一定要用 sendtotarget
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
		
		
		alertDialog.show();
		//alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0)); 
		alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		alertDialog.setContentView(verify_v);
	}
}
