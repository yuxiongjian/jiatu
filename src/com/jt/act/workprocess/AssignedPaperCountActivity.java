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
import com.jt.pojo.RecordCopy;
import com.jt.pojo.WorkOrder;
import com.jt.pojo.WorkRequest;

import project.pojo.Bpojo;
import project.ui.AnimationTabHost;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.ui.TabInitData;



/**
 * @author thomasy
 * @送货页面
 * @
 *
 */
public class AssignedPaperCountActivity extends ProjectActivity {

	/**
	 * @author thomasy
	 * {@docRoot}
	 */
	public static final long serialVersionUID = AssignedPaperCountActivity.class.hashCode();
	private AnimationTabHost mTabHost;
	private int tabid;
	WorkOrder wd = null;
	private void initTabData() {
		tabInitData = (TabInitData) getIntent()
				.getSerializableExtra("" + TabInitData.serialVersionUID);
		tabInitData.tabActivity = this;
		mTabHost = ((ResolveOrderTabHostActivity)tabInitData.rootAct).mTabHost;
	
		tabid = mTabHost.getCurrentTab();
	}
	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		setContentView(R.layout.replenish_info);

		super.onCreate(savedInstanceState);
		initTabData();
		wd = (WorkOrder) (formPojo = (WorkOrder) getIntent().getSerializableExtra(""+WorkOrder.getSerialversionuid()));
		
		formPojo = wd;
		formPojos = wd.getRecordList();
		for ( Bpojo b : formPojos )
			{ b.setEditType(wd.getEditType());
			  RecordCopy rc= (RecordCopy) b;
			  if( rc.getCurnum()!=null && rc.getCurnum()==0)
				  rc.setCurnum(null);
			}
		mViewMaps = ProjectTableForm.initFormLayout(thisAct,R.id.bpojotable, formPojos,null);

	}
	/* (non-Javadoc)
	 * @see project.util.ProjectActivity#OnCancel(android.view.View)
	 */
	@Override
	public void OnCancel(View v) {
		// TODO Auto-generated method stub
		super.OnCancel(v);
		finish();
	}
	
	@Override
	public HashMap<String, String> refreshData(HashMap<String, String> retMap) {
		// TODO Auto-generated method stub
		this.formPojos =   new RecordCopy[mViewMaps.size()];
		
		for(int i=0;i<mViewMaps.size();i++)
			formPojos[i]= new RecordCopy(wd.getSysid());
			
		return super.refreshDatas(retMap);
	}
}
