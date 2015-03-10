/**
 * 
 */
package com.jt.act.workprocess;

import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.CustomerMachine;
import com.jt.pojo.PODetail;
import com.jt.pojo.PredictOrder;
import com.jt.pojo.WorkOrder;
import com.jt.pojo.WorkRequest;

import project.config.Config;
import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;
import project.ui.AnimationTabHost;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.ui.TabInitData;

/**
 * @author thomasy
 * @送货页面 @
 * 
 */
public class ReplenishActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ReplenishActivity.class
			.hashCode();
	private AnimationTabHost mTabHost;
	private int tabid;
	WorkOrder wd = null;

	/**
	 * @param
	 * 
	 * @return
	 * 
	 * 
	 * 
	 * 
	 * */
	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		setContentView(R.layout.replenish_info);

		super.onCreate(savedInstanceState);

		initTabData();
		wd = (WorkOrder) (formPojo = (WorkOrder) getIntent()
				.getSerializableExtra("" + WorkOrder.getSerialversionuid()));

		formPojo = wd;
		formPojos = wd.getDetailList();
		for (Bpojo b : formPojos)
			b.setEditType(wd.getEditType());
		mViewMaps = ProjectTableForm.initFormLayout(thisAct, R.id.bpojotable,
				formPojos, null);
		if (!wd.isFromDB() && wd.getEditType() == 1) {
			for (int i = 0; i < Config.PONUM; i++) {
				HashMap<String, Bpojo.ViewHolder> retvm = ProjectTableForm
						.initFormLayout(thisAct, R.id.bpojotable,
								new PODetail(), null);
				mViewMaps.add(retvm);

			}
		}

	}

	public ReplenishActivity() {
		super();

		// TODO Auto-generated constructor stub
	}

	private void initTabData() {
		tabInitData = (TabInitData) getIntent().getSerializableExtra(
				"" + TabInitData.serialVersionUID);
		tabInitData.tabActivity = this;
		mTabHost = ((ResolveOrderTabHostActivity) tabInitData.rootAct).mTabHost;
		tabid = mTabHost.getCurrentTab();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.util.ProjectActivity#OnCancel(android.view.View)
	 */
	@Override
	public void OnCancel(View v) {
		// TODO Auto-generated method stub
		super.OnCancel(v);
		finish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.util.ProjectActivity#refreshData(java.util.HashMap)
	 */
	@Override
	public HashMap<String, String> refreshData(HashMap<String, String> retMap) {
		// TODO Auto-generated method stub
		if (!formPojo.isFromDB() ) {
			this.formPojos = new PODetail[mViewMaps.size()];

			for (int i = 0; i < mViewMaps.size(); i++)
				formPojos[i] = new PODetail(wd.getSysid());
		}
		return super.refreshDatas(retMap);
	}

}
