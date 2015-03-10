package com.jt.act.workprocess;

import org.alexd.jsonrpc.JSONRPCException;

import project.ui.ProjectTabHostActivity;
import project.util.MyLog;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.pojo.WorkOrder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class WorkProcessTabHostActivity extends ProjectTabHostActivity
		 {

	private SearchView mSearchView;

	private MenuItem mMenuOK;

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		int i = mTabHost.getCurrentTab();

		WorkOrderListActivity act = (WorkOrderListActivity) (tabdata.get(i).tabActivity);
		act.onQueryTextChange(newText);

		return true;
	}

	@Override
	public void onHandleMessage(Message msg) {
		if (msg.what == 0)
			setSignBT((WorkOrderListActivity) msg.obj);
		if (msg.what == 1)
			this.mSearchView.setQuery(((String) msg.obj), true);

	}

	private static final long serialVersionUID = WorkProcessTabHostActivity.class
			.hashCode();

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.main_hometabhost);
		super.onCreate(savedInstanceState);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.workorder_process, menu);
		MenuItem search = menu.findItem(R.id.search);
		mMenuOK = menu.findItem(R.id.BT_OK);
		mMenuOK.setVisible(false);
		mMenuOK.getActionView();
		// mOKView.set
		// search.collapseActionView();
		mSearchView = (SearchView) search.getActionView();
		mSearchView.setIconifiedByDefault(true);
		
		// SearchManager mSearchManager = (SearchManager)
		// getSystemService(Context.SEARCH_SERVICE);
		// SearchableInfo info =
		// mSearchManager.getSearchableInfo(getComponentName());
		// mSearchView.setSearchableInfo(info); //
		// 需要在Xml文件加下建立searchable.xml,搜索框配置文件
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setSubmitButtonEnabled(false);
		return true;
	}

	public void setSignBT(WorkOrderListActivity wo) {
		if (mMenuOK == null) {
			MyLog.Loge("mMenuOK 按钮未初始化");
			return;
		}
		switch (wo.OrderType) {

		case WorkOrder.已到达:

		case WorkOrder.已派:
		case WorkOrder.已启动:
		case WorkOrder.已签字:

			// btok.setText("");
			mMenuOK.setVisible(true);
			mMenuOK.setIcon(R.drawable.sign_bk_bmp);
			// btok.setVisibility(View.VISIBLE);
			// btok.setBackgroundResource(R.drawable.sign_bk_bmp);
			// btok.setWidth(60);
			break;

		case WorkOrder.保养:
			// this.btok.setText("接受保养");
			mMenuOK.setIcon(0);
			mMenuOK.setTitle("接受保养");
			// btok.setBackgroundResource(R.drawable.button_style1);
			if (wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);// btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);// btok.setVisibility(View.GONE);
			break;
		case WorkOrder.抄张:
			// this.btok.setText("接受抄张");
			mMenuOK.setIcon(0);
			mMenuOK.setTitle("接受抄张");
			// btok.setBackgroundResource(R.drawable.button_style1);
			if (wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);// btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);// btok.setVisibility(View.GONE);
			break;
		case WorkOrder.已提交:
			// this.btok.setText("客户签字");
			mMenuOK.setIcon(0);
			mMenuOK.setTitle("客户签字");
			// btok.setBackgroundResource(R.drawable.button_style1);
			if (wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);// btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);// btok.setVisibility(View.GONE);
			break;
		default:
			mMenuOK.setVisible(false);
			// btok.setBackgroundResource(0);
			// btok.setVisibility(View.GONE);
			break;
		}

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.BT_OK:
			int i = mTabHost.getCurrentTab();

			WorkOrderListActivity act = (WorkOrderListActivity) (tabdata.get(i).tabActivity);
			act.OnOK(null);

			break;
		default:
			break;

		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onInitTabs() {
		int i = -1;

		setIndicator(R.string.newworkorder, ++i,
				new Intent(this, WorkOrderListActivity.class).putExtra(
						"ordertype", WorkOrder.已派), 1); //$NON-NLS-1$
		// mTabHost.setCurrentTab(i);
		setIndicator(R.string.pickworkorder, ++i, new Intent(this,
				WorkOrderListActivity.class).putExtra(
				"ordertype", WorkOrder.已启动), 0); //$NON-NLS-1$
		// mTabHost.setCurrentTab(i);
		setIndicator(R.string.openworkorder, ++i, new Intent(this,
				WorkOrderListActivity.class).putExtra(
				"ordertype", WorkOrder.已到达), 0); //$NON-NLS-1$
		// mTabHost.setCurrentTab(i);
		setIndicator(R.string.solveworkorder, ++i, new Intent(this,
				WorkOrderListActivity.class).putExtra(
				"ordertype", WorkOrder.已提交), 0); //$NON-NLS-1$
		// mTabHost.setCurrentTab(i);
		setIndicator(R.string.signworkorder, ++i, new Intent(this,
				WorkOrderListActivity.class).putExtra(
				"ordertype", WorkOrder.已签字), 0); //$NON-NLS-1$
		// mTabHost.setCurrentTab(i);
		//setIndicator(R.string.config, 3, new Intent(this,SettingsActivity.class), R.drawable.tab_settings_normal); //$NON-NLS-1$
		mTabHost.setOpenAnimation(true);
		mTabHost.setCurrentTab(0);
		mLastSelect = 0;
		// onPageSelected(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostResume()
	 */

	protected void __onPostResume() {
		// TODO Auto-generated method stub
		Integer[] counts;
		super.onPostResume();

		
		try {
			counts = JtService.getAllWorkOrderCount(
					AppLoginActivity.getUser().getSid(),
					AppLoginActivity.getUser().getUsername());
			updateTab(counts);

		} catch (InterruptedException e) {

			
		} catch (JSONRPCException e) {

		}

	}

	private void updateTab(Integer[] counts) {
		// TODO Auto-generated method stub
		
	}

}