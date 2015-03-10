package com.jt.act.workprocess;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.CustomerMachine;
import com.jt.pojo.ReArrange;
import com.jt.pojo.WorkOrder;
//import com.jt.pojo.WorkOrderMessage;
import com.jt.pojo.WorkRequest;

import project.pojo.Bpojo;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;

public class ArrangeWorkOrderActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ArrangeWorkOrderActivity.class
			.hashCode();
	private WorkOrder wd;

	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		super.onCreate(savedInstanceState);
		wd = null;
		wd = (WorkOrder) getIntent().getSerializableExtra(
				"" + WorkOrder.getSerialversionuid());
		setContentView(R.layout.rearrange_workorder);
		// workorderForm = new ProjectTableForm();
		WorkRequest wr = wd.getWorkRequest();
		// WorkOrderMessage wm = wd.getWorkMessage();
		CustomerMachine wc = wd.getCustomerMachine();
		wr.setSudo_address(wc.getAddress());
		wr.setSudo_customer(wc.getCustomer_name());
		ReArrange ra = new ReArrange();
		formPojo = ra;
		Bpojo bjs[]={ wd.getWorkRequest(),ra};
		formPojos = bjs;
		mViewMaps = ProjectTableForm.initFormLayout(thisAct, R.id.pojotable,
				formPojos, null);
		mViewMap = mViewMaps.get(1);

	}

	public void onResume() {
		super.onResume();
		// 代码
	}

	@Override
	public void OnOK(View v) {
		Boolean ret = null;
		super.OnOK(v);
		 HashMap<String, String> checkmap;
		try {
			checkmap = thisAct.refreshDatas(null);
			if (checkmap.size() > 0) {
				Toast.makeText(this, checkmap.toString(), Toast.LENGTH_LONG).show();
				return;
			}
			ReArrange ra = ((ReArrange)formPojo);
			ret = JtService.assignWorkOrder(getAu().getSid(), getAu()
						.getUsername(), wd.getSysid(), ra.getTechid(), new java.util.Date(ra.getNewDate().getTime()));
				if (ret == true) {
					Toast.makeText(thisAct, "调度成功", Toast.LENGTH_SHORT).show();
				}
				finish();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(thisAct, "调度失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {

		}

		// TODO Auto-generated method stub

	}

	

	@SuppressLint("ShowToast")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}

	public void handleProjectMessage(Message msg) {
		super.handleProjectMessage(msg);
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void OnCancel(View v) {
		super.OnCancel(v);
	}	

}
