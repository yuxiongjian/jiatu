package com.jt.act.workprocess;

import com.jt.R;
import com.jt.pojo.WorkOrder;

import android.content.Intent;
import android.os.Bundle;


public class MaintainTabHostActivity extends WorkProcessTabHostActivity {

	/**
	 * 
	 */
	private static final long serialVersionUID = MaintainTabHostActivity.class.hashCode();

public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.main_hometabhost);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void onInitTabs() {
		int i=0;
	
		setIndicator(R.string.checkpaper, i++,
				new Intent(this, WorkOrderListActivity.class).putExtra(
						"ordertype", WorkOrder.抄张), 1); //$NON-NLS-1$
		setIndicator(R.string.maintain, i++,
				new Intent(this, WorkOrderListActivity.class).putExtra(
						"ordertype", WorkOrder.保养), 0); //$NON-NLS-1$
		mTabHost.setOpenAnimation(true);
		mTabHost.setCurrentTab(0);
		mLastSelect = 0;
		// onPageSelected(1);
	}

	

}