package com.jt.act.workprocess;

import java.util.HashMap;

import project.ui.ProjectActivity;
import project.ui.ProjectTabHostActivity;
import project.ui.TabInitData;
import project.util.MyLog;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResolveOrderTabHostActivity extends ProjectTabHostActivity {

	/**
	 * 
	 */

	public static final long serialVersionUID = ResolveOrderTabHostActivity.class
			.hashCode();

	private WorkOrder wd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.ui.ProjectTabHostActivity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle icicle) {

		super.onPostCreate(icicle);
		
	}

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.resolve_tabhost);

		// SystemScreenInfo.getSystemInfo(HomeTabHostAcitivity.this);

		wd = (WorkOrder) getIntent().getSerializableExtra(
				"" + WorkOrder.getSerialversionuid());
		if (wd.getStatus().equals(WorkOrder.已提交)
				|| wd.getStatus().equals(WorkOrder.已签字)) {
			findViewById(R.id.BT_OK).setVisibility(View.INVISIBLE);
			((Button) findViewById(R.id.BT_CANCEL)).setText("返回");
			wd.setEditType(0);
		}
		super.onCreate(savedInstanceState);

	}

	public void onInitTabs() {
		int id = -1;
		String wt = "";
		boolean needRead;
		WorkRequest rq = wd.getWorkRequest();
		if (rq != null) {
			wt = rq.getWorktype();
			needRead = rq.getNeedReader() == null ? false : rq.getNeedReader();
		} else {
			MyLog.False("没找到Request");
			return;
		}

		setIndicator(
				R.string.resolveui0,
				++id,
				new Intent(this, ResolveOrderActivity.class)
						.putExtra("" + WorkOrder.serialVersionUID, wd).putExtra("" + Class.class.hashCode(), WorkRequest.class), 1); //$NON-NLS-1$
		mTabHost.setCurrentTab(id);
		if (wt != null
				&& (wt.equals("WX") || wt.equals("JZ") || wt.equals("JX"))) {

			setIndicator(
					R.string.resolveui1,
					++id,
					new Intent(this, ResolveOrderActivity.class)
							.putExtra("" + WorkOrder.serialVersionUID, wd).putExtra("" + Class.class.hashCode(), ResolveRepairUI.class), 0); //$NON-NLS-1$
		}
		mTabHost.setCurrentTab(id);
		if (wd.getWorkRequest().getWorktype().equals("CZ")) {
//			MyLog.Assert(!wd.getWorkRequest().getWorktype().equals("CZ"),"同时两种抄张方式");


			setIndicator(
					R.string.resolveui2,
					++id,
					new Intent(this, AssignedPaperCountActivity.class)
							.putExtra("" + WorkOrder.serialVersionUID, wd)
							.putExtra("" + Class.class.hashCode(),
									ResolvePaperCountUI.class), 0);
		}else if (needRead) {
			
			setIndicator(
					R.string.resolveui2,
					++id,
					new Intent(this, ResolveOrderActivity.class)
							.putExtra("" + WorkOrder.serialVersionUID, wd).putExtra("" + Class.class.hashCode(), ResolvePaperCountUI.class), 0); //$NON-NLS-1$
		} 
		mTabHost.setCurrentTab(id);

		setIndicator(
				R.string.resolveui3,
				++id,
				new Intent(this, ResolveOrderActivity.class)
						.putExtra("" + WorkOrder.serialVersionUID, wd).putExtra("" + Class.class.hashCode(), ResolveResultUI.class), 0); //$NON-NLS-1$
		mTabHost.setCurrentTab(id);

		setIndicator(
				R.string.resolveui4,
				++id,
				new Intent(this, ReplenishActivity.class).putExtra(
						"" + WorkOrder.serialVersionUID, wd).putExtra(
						"" + Class.class.hashCode(), ResolveP4UI.class), 0); //$NON-NLS-1$
		mTabHost.setCurrentTab(id);
		// mTabHost.setup();
		// mTabHost.setup(this.getLocalActivityManager());
		mTabHost.setOpenAnimation(true);
		mTabHost.setCurrentTab(0);

		// onPageSelected(1);
	}

	public HashMap<String, String> refreshUI() {

		HashMap<String, String> retMap = new HashMap<String, String>();
		for (int i = 0; i < tabdata.size(); i++) {
			int key = tabdata.keyAt(i);
			TabInitData iddata = tabdata.get(key);

			if (iddata == null)
				continue;
			ProjectActivity act = (ProjectActivity) iddata.tabActivity;
			if (act == null)
				continue;

			act.refreshData(retMap);
			if (act.getClass().equals(ReplenishActivity.class))
				wd.setDetailList((PODetail[]) act.getFormPojos());
			else if (act.getClass().equals(AssignedPaperCountActivity.class))
				wd.setRecordList((RecordCopy[]) act.getFormPojos());
			// act.getFormPojo().refreshData(act.getViewMap(), retMap);
			else
				wd.copyValue(act.getFormPojo());
			if (wd.getSuggest() == null)
				wd.setSuggest("");
			// MyLog.Log(wd);
		}
		return retMap;
	}

	@Override
	public void OnOK(View v) {

		HashMap<String, String> retMap = refreshUI();
		if (retMap.size() > 0 ) {
			Toast.makeText(this, retMap.toString(), Toast.LENGTH_LONG).show();
		} else {

			Intent it = new Intent();
			it.putExtra("" + WorkOrder.getSerialversionuid(), wd);
			setResult(RESULT_OK, it);
			boolean ret = false;
			try {
				ret = JtService.ResolveWorkOrder(
						AppLoginActivity.getUser().getSid(),
						AppLoginActivity.getUser().getUsername(), wd);
				if (ret) {

					Toast.makeText(this, "工单完工提交成功", Toast.LENGTH_LONG).show();
					finish();
				} else {
					Toast.makeText(this, "工单完工提交失败", Toast.LENGTH_LONG).show();
					return;
				}

			} catch (ClassCastException e) {

				Toast.makeText(this, "工单完工调用异常", Toast.LENGTH_LONG).show();
			} catch (Exception e) {

				Toast.makeText(this, "工单完工失败:" + e.getMessage(),
						Toast.LENGTH_LONG).show();
				//MyLog.Loge(e);

			}

		}
		return;

	}

	@Override
	public void OnCancel(View v) {
		if (wd.getStatus().equals(WorkOrder.已提交)
				|| wd.getStatus().equals(WorkOrder.已签字)) {
			finish();
			return;
		} else if (wd.getStatus().equals(WorkOrder.已到达)) {
			SaveWorkOrder();

		}

	}

	private long SaveWorkOrder() {

		long ret = 0;
		@SuppressWarnings("unused")
		HashMap<String, String> retMap = refreshUI();
		ret = wd.saveLocal(this);

		if (ret > 0) {

			Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();

		} else {

			Toast.makeText(this, "保存失败", Toast.LENGTH_LONG).show();
			return ret;
		}

		return ret;
	}

}