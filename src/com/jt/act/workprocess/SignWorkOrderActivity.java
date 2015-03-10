package com.jt.act.workprocess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.jt.R;
import com.jt.act.TuYaActivity;
import com.jt.appservice.JtService;
import com.jt.pojo.SignOrder;
import com.jt.pojo.User;

import project.util.ProjectLocationListener;

public class SignWorkOrderActivity extends TuYaActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = SignWorkOrderActivity.class
			.hashCode();
	private Boolean nextOpen;

	public void OnPostOK() {

		VerifyToOpenNext("客户签字", "是否自动开启下一条工单?");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * project.ui.tuya.TuYaActivity#handleProjectMessage(android.os.Message)
	 */
	@Override
	public void handleProjectMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleProjectMessage(msg);
		
		if (msg.what == "VerifyToOpenNext".hashCode()) {

			CloseWorkOrder();

		}
//		super.handleMessage(msg);
	}


	public void CloseWorkOrder() {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Boolean ret = true;
		try {
			// handWrite.saveImage()

			HashMap<String, String> retMap = null;
			SignOrder so;
			retMap = refreshData(null);
			if (retMap.size() > 0) {
				Toast.makeText(this, retMap.toString(), Toast.LENGTH_LONG)
						.show();
			} else {
				so = (SignOrder) getFormPojo();

				ret = JtService.closeWorkOrder(User.cUser.getSid(),
						User.cUser.getUsername(), so, nextOpen,
						ProjectLocationListener.getCurLoc().getLatitude(),
						ProjectLocationListener.getCurLoc().getLongitude());
				if (ret == false)
					throw new Exception("服务器返回False");
				else
					Toast.makeText(thisAct, "工单关闭提交成功", Toast.LENGTH_LONG)
							.show();
				handWrite.clearImge();
				finish();
			}

		} catch (Exception e) {
			Toast.makeText(thisAct, "工单关闭提交失败:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void VerifyToOpenNext(String title, String msg) {
		final Dialog alertDialog = new AlertDialog.Builder(thisAct)
				.setTitle(title).setMessage(msg)
				.setIcon(R.drawable.ic_launcher)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						nextOpen = true;
						Message msg = Message.obtain(getMsgHandler(), "VerifyToOpenNext".hashCode());
						// hh.sendMessage(msg1);
						msg.sendToTarget();
						// dialog.dismiss();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						nextOpen = false;

						// msg = Message.obtain(getHh(), 5);
						// hh.sendMessage(msg1);
						Message msg = Message.obtain(getMsgHandler(), "VerifyToOpenNext".hashCode());
						msg.sendToTarget();
						// dialog.dismiss();
					}
				}).create();
		alertDialog.show();
	}

}
