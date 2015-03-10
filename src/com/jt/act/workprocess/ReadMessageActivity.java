package com.jt.act.workprocess;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.MobileMessage;

import project.pojo.Bpojo;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.util.MyLog;

public class ReadMessageActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ReadMessageActivity.class
			.hashCode();

	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		super.onCreate(savedInstanceState);

		this.setFormPojo((Bpojo) getIntent().getSerializableExtra(
				"" + MobileMessage.serialVersionUID));
		setContentView(R.layout.read_message);

		setViewMap(ProjectTableForm.initFormLayout(thisAct,
				R.id.readmessagetable, this.getFormPojo(), null));

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

			MobileMessage mm = (MobileMessage) getFormPojo();
			if (mm.isUnRead()) {
				ret = JtService.readMessage(getAu().getSid(), getAu()
						.getUsername(), (Integer) mm.getID());
				if (ret == true) {
					Toast.makeText(thisAct, "读取成功", Toast.LENGTH_SHORT).show();
				}
			}
			finish();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyLog.False(e);
		} finally {

		}

		// TODO Auto-generated method stub

	}

}
