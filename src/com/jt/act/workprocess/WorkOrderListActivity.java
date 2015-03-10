package com.jt.act.workprocess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.alexd.jsonrpc.JSONRPCException;

import project.config.Config;
import project.config.DebugSetting;
import project.ui.IListDataItem;
import project.ui.MenuHorizontalScrollView;
import project.ui.ProjectActivity;
import project.ui.QuickAlphabeticBar;
import project.ui.SizeCallBackForMenu;
import project.ui.TabInitData;
import project.util.MyLog;
import project.util.ProjectLocationListener;
import project.util.ProjectThread;
import project.util.zxing.act.CaptureActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.adapter.AlListViewAdapter;
import com.jt.adapter.TreeViewAdapter;
import com.jt.appservice.JtService;
import com.jt.pojo.GroupBean;
import com.jt.pojo.SignOrder;
import com.jt.pojo.TreeElement;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder;

public class WorkOrderListActivity extends ProjectActivity implements
		SearchView.OnQueryTextListener {

	/**
	 * 
	 */
	public static final long serialVersionUID = WorkOrderListActivity.class
			.hashCode();

	private MenuHorizontalScrollView leftScrollView;

	private String curName = null;
	// private SlidingLinearLayout leftScrollView;


	public int OrderType;

	public WorkOrderListActivity() {

		super();

	}

	// private Activity ThisAct = WorkOrderListActivity.this;

	private AlListViewAdapter adapter;
	private ListView rightListView;

	private List<IListDataItem> IListDataItemList = new ArrayList<IListDataItem>();



	private SearchView mSearchView;

	// public Button signbt;

	private SignOrder so;

	private String barcode;

	private IListDataItem[] wl={};

	private View mFormView;
    private ProjectThread fetchThread= null;
	private View mProgressView;
	MyHandler mh = new MyHandler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			IListDataItemList = filterItemArray(wl, curName);
			setDataListViewAdapter(IListDataItemList);
		//	if( mMenuOK != null )
			setSignBT();
			showProgress(false);
			
		}
		
	};

	private Menu mBarMenu;

	private MenuItem mMenuOK;

	private Handler mPHandle;


	private static View getRootView(Activity context) {
		return ((ViewGroup) context.findViewById(android.R.id.content))
				.getChildAt(0);
	}

	public void onCreate(Bundle savedInstanceState) {
		// rs = this.getBaseContext().getResources();
		super.onCreate(savedInstanceState);
		//inflater = LayoutInflater.from(this);
		setContentView(R.layout.home_list_page);

		this.setAu(User.cUser);
		View v = getRootView(this);
		OrderType = this.getIntent().getIntExtra("ordertype", 1);
		MyLog.Log("" + v.getId());
		tabInitData = (TabInitData) getIntent().getSerializableExtra(
				"" + TabInitData.serialVersionUID);
		if (tabInitData != null){
			mPHandle = tabInitData.pHandler;
		
			tabInitData.tabActivity = this;
		}
		onInitMenuList();
		this.showProgress(true);

	}

	public void onResume() {

		initList(curName);
		super.onResume();
		// 代码
	}

	/**
	 * @author thomasy
	 * 通过 mphandle 更新tabhostActivity的功能键
	 */
	public void refreshActionBar(){
		if ( mPHandle != null )
			mPHandle.obtainMessage(0,this).sendToTarget();
	}
	public void refreshActionBar(String str){
		if ( mPHandle != null )
			mPHandle.obtainMessage(1,str).sendToTarget();
	}

	/**
	 * @author thomasy
	 * 
	 */

	protected void onPostExecute(Handler handle) {

		handle.sendEmptyMessage(100);

	}
	
	

	private void initList(String username) {
		fetchThread= new ProjectThread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				wl = getListDataItemList( OrderType, thisAct );
				mh.obtainMessage(0).sendToTarget();
			}
	    	
	    };
		//wl = getListDataItemList( OrderType, thisAct );
		fetchThread.start();
	
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//mi.inflate(R.menu.main, (ViewGroup) menu);
		mBarMenu = menu;
		getMenuInflater().inflate(R.menu.workorder_process, menu);
		MenuItem search = menu.findItem(R.id.search);
		mMenuOK = menu.findItem(R.id.BT_OK);
		mMenuOK.setVisible(false);
		setSignBT();
		mMenuOK.getActionView();
	//	mOKView.set
		search.collapseActionView();
		mSearchView = (SearchView) search.getActionView();
		mSearchView.setIconifiedByDefault(true);
		SearchManager mSearchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchableInfo info = mSearchManager
				.getSearchableInfo(getComponentName());
		mSearchView.setSearchableInfo(info); // 需要在Xml文件加下建立searchable.xml,搜索框配置文件
		return true;
	}*/

	private void onInitMenuList() {


		//horizonSlideBtn = (Button)findViewById(R.id.horizonSlideBtn);

		rightListView = (ListView) findViewById(R.id.rightpage_list);
		rightListView.setBackgroundResource(R.drawable.list_background);
		rightListView.setOnTouchListener(this);

		

	}

	@Override
	public void OnOK(View v) {

		// MyLog.Assert(!checkIDs.isEmpty(), "选择的待签为空！");
		Boolean ret = false;
		String msg = "";
		try {

			Intent _intent;
			switch (OrderType) {
			case WorkOrder.保养:
				msg = "保养计划";
				ret = JtService.acceptPredict(AppLoginActivity.getUser()
						.getSid(), AppLoginActivity.getUser().getUsername(),
						"BY", checkIDs.toArray(new Integer[0]));

				break;
			case WorkOrder.抄张:
				msg = "抄张计划";
				ret = JtService.acceptPredict(AppLoginActivity.getUser()
						.getSid(), AppLoginActivity.getUser().getUsername(),
						"CZ", checkIDs.toArray(new Integer[0]));

				break;
			case WorkOrder.已启动:
				_intent = new Intent(thisAct, CaptureActivity.class);
				thisAct.startActivityForResult(_intent,
						(int) CaptureActivity.serialVersionUID);

				return;
			case WorkOrder.已提交:
				_intent = new Intent(this, SignWorkOrderActivity.class);
				so = new SignOrder();
				so.workorders = checkIDs.toArray(new Integer[0]);
				_intent.putExtra("" + SignOrder.serialVersionUID, so);
				startActivityForResult(_intent,
						(int) SignWorkOrderActivity.serialVersionUID);
				return;
			default:
				_intent = new Intent(this, CaptureActivity.class).putExtra(
						"from", "onQueryTextSubmit");

				this.startActivityForResult(_intent,
						(int) CaptureActivity.serialVersionUID);
				return;

			}
			if (ret) {
				Toast to = Toast.makeText(thisAct, msg + "提交成功",
						Toast.LENGTH_LONG);
				to.setGravity(Gravity.CENTER, 0, 0);
				to.show();

				checkIDs.clear();
				initList(curName);
				setSignBT();
				//mMenuOK.setVisible(false);
				//btok.setVisibility(View.GONE);
			} else
				Toast.makeText(thisAct, msg + "提交失败", Toast.LENGTH_LONG).show();
		} /*
		 * catch (ClassCastException e) {
		 * 
		 * Toast.makeText(this, msg + "调用异常,Code=" + ret, Toast.LENGTH_LONG)
		 * .show(); }
		 */catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, msg + "打开失败:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
			// MyLog.Loge(e);

		}

	}

	@Override
	public void OnCancel(View v) {

		super.OnCancel(v);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		//horizonSlideBtn.setVisibility(View.INVISIBLE);
	
		// signbt = (Button) rightPage.findViewById(R.id.BT_OK);
		//btok.setVisibility(View.GONE);
		//
		if( mMenuOK != null ){
			mMenuOK.setVisible(false);
			setSignBT();// call on onresume later.
		}
	}

	private List<IListDataItem> filterItemArray(IListDataItem[] wl,
			String curname) {

		List<IListDataItem> listl = new ArrayList<IListDataItem>();
		if (wl == null)
			return listl;
		for (IListDataItem wo : wl) {
			if (wo.compare(curname))
				listl.add(wo);
		}
		return listl;

	}

	@SuppressWarnings("unused")
	private List<IListDataItem> sort(List<IListDataItem> inList) {

		Collections.sort(inList, new Comparator<IListDataItem>() {
			public int compare(IListDataItem arg0, IListDataItem arg1) {

				int ret;
				if (arg1 == null || arg1.getSortKey() == null)
					ret = 1;
				else if (arg0 == null || arg0.getSortKey() == null)
					ret = -1;
				else
					ret = arg0.getSortKey().compareTo(arg1.getSortKey());
				if (arg0.isReverse()) {
					return -ret;
				} else
					return ret;
			}
		});

		return inList;

	}

	public int getListCount() {
		int ret = 0;

		try {
			ret = JtService.getAssignedWorkOrderCount(AppLoginActivity
					.getUser().getSid(), AppLoginActivity.getUser()
					.getUsername(), OrderType);
		} catch (InterruptedException e) {

		} catch (JSONRPCException e) {

		}
		return ret;

	}
	
	protected static IListDataItem[] getListDataItemList(int OrderType, Activity thisAct) {

		Double latitude = 0.0;
		Double longitude = 0.0;

		IListDataItem[] lwl = {};
		Location location = ProjectLocationListener.getCurLoc();
		if (location == null) {
			if (DebugSetting.isDebug()) {

				latitude = Config.lat;
				longitude = Config.lot;
			} else {
				// return IListDataItemList;
			}
		} else {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
		// new ArrayList<IListDataItem>();

		try {

			switch (OrderType) {

			case WorkOrder.调度:
				lwl = JtService.findDispatchingWorkOrder(AppLoginActivity
						.getUser().getSid(), AppLoginActivity.getUser()
						.getUsername(), "", 1);
				break;

			case WorkOrder.消息:
				lwl = JtService.findMessage(
						AppLoginActivity.getUser().getSid(), AppLoginActivity
								.getUser().getUsername());
				break;
			case WorkOrder.保养:
				lwl = JtService.findAroundMachinePredictList(AppLoginActivity
						.getUser().getSid(), AppLoginActivity.getUser()
						.getUsername(), "BY", latitude, longitude);
				break;
			case WorkOrder.抄张:

				lwl = JtService.findAroundMachinePredictList(AppLoginActivity
						.getUser().getSid(), AppLoginActivity.getUser()
						.getUsername(), "CZ", latitude, longitude);
				break;
			case WorkOrder.已到达:
			case WorkOrder.已启动:
			case WorkOrder.已派:
			case WorkOrder.已提交:
			case WorkOrder.已签字:

				lwl = JtService.findAssignedWorkOrder(AppLoginActivity
						.getUser().getSid(), AppLoginActivity.getUser()
						.getUsername(), OrderType);

				break;
			default:
			}

		} catch (Exception e) {
			
			String emsg = (e.getCause()==null?e.getMessage():e.getCause().getMessage());
			Toast.makeText(thisAct, "数据列表获取错误:"+ emsg, Toast.LENGTH_LONG).show();

		/*	Dialog dialog = new AlertDialog.Builder(thisAct)
					.setTitle("系统错误")
					.setMessage(e.getMessage())
					.setPositiveButton("确认",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).create();
			dialog.show();*/
		} finally {

		}

		return lwl;

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (MenuHorizontalScrollView.menuOut == true)
				this.leftScrollView.slideMenu();
			else
				this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 数据库异步查询类AsyncQueryHandler
	 * 
	 * @author administrator
	 * 
	 */
	@SuppressLint("HandlerLeak")
	private class MyAsyncQueryHandler extends AsyncQueryHandler {

		@SuppressLint("HandlerLeak")
		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
			;
		}

		/**
		 * 查询结束的回调函数
		 */
		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {

		}

	}

	private void setDataListViewAdapter(List<IListDataItem> list) {
		
		
		adapter = new AlListViewAdapter(this, list, null, OrderType);
		// adapter = new T9Adapter(this, list, alpha);

		rightListView.setVisibility(View.VISIBLE);
		rightListView.setAdapter(adapter);
		
		rightListView.setOnItemClickListener(adapter.lsn);
		if( list.size()==0)
			Toast.makeText(thisAct, "查无数据", Toast.LENGTH_LONG).show();

	}

	/**
	 * 
	 * 查询所有群组 返回值List<ContactGroup>
	 * 
	 * @throws SQLException
	 */
	public List<GroupBean> queryDeptGroup() throws SQLException {

		List<GroupBean> list = new ArrayList<GroupBean>();

		new GroupBean();

		return list;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IListDataItem findItem = null;
		if ((int) ReadWorkOrderActivity.serialVersionUID == requestCode) {
			initList(curName);
		} else if ((int) SignWorkOrderActivity.serialVersionUID == requestCode) {

			if (resultCode == Activity.RESULT_OK) {
				this.checkIDs.clear();

				initList(curName);
				mMenuOK.setVisible(false);
				//if ( btok != null )
					//btok.setVisibility(View.GONE);
			}
		} else if (requestCode == (int) CaptureActivity.serialVersionUID) {
			if (resultCode == Activity.RESULT_OK) {
				barcode = data.getStringExtra("barcode");
				if (data.getCharSequenceExtra("from").equals(
						"onQueryTextSubmit")) {
					refreshActionBar(barcode);
					//this.mSearchView.setQuery(barcode, true);
				} else {
					for (IListDataItem item : IListDataItemList) {
						if (item.getBarcode() != null
								&& item.getBarcode().equals(barcode)) {
							findItem = item;
							break;
						}
					}
					if (findItem != null) {
						CustomDialog.VerifyToOpenWorkOrder(thisAct, barcode,
								"扫描成功", "您确定开始维修编码为：\r\n" + barcode
										+ " \r\n的打印机么? ", (WorkOrder) findItem);

					} else
						Toast.makeText(thisAct, "查不到该条码的工单:" + barcode,
								Toast.LENGTH_LONG).show();

				}
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void handleProjectMessage(Message msg) {
		super.handleProjectMessage(msg);
		// TODO Auto-generated method stub
		if (msg.what == "VerifyToOpenWorkOrder".hashCode())
			if (msg.arg1 == 1) {
				WorkProcessTabHostActivity woa = (WorkProcessTabHostActivity) tabInitData.rootAct;
				int i = woa.mTabHost.getCurrentTab();
				woa.mTabHost.setCurrentTab(i + 1);
				Activity act = woa.tabdata.get(i + 1).tabActivity;
				Intent _intent = new Intent(act,
						ResolveOrderTabHostActivity.class);
				_intent.putExtra("" + WorkOrder.serialVersionUID,
						(WorkOrder) msg.obj);
				act.startActivityForResult(_intent,
						(int) ResolveOrderTabHostActivity.serialVersionUID);

			}

	}

	protected void onDestroy() {
		super.onDestroy();

	}

	/**
	 * 打开接收器
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.SearchView.OnQueryTextListener#onQueryTextSubmit(java.
	 * lang.String)
	 */
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.SearchView.OnQueryTextListener#onQueryTextChange(java.
	 * lang.String)
	 */
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		List<IListDataItem> result = this.filterItemArray(wl, newText);
		curName = newText;
		setDataListViewAdapter(result);
		setSignBT();
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	
		
		private void showProgress(final boolean show) {
			
			mProgressView = this.findViewById(R.id.process_status);
			mFormView = this.findViewById(R.id.list_form);
			TextView mLoginStatusMessageView = (TextView) findViewById(R.id.load_data_message);
			
			mLoginStatusMessageView.setText(R.string.load_data);
			// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
			// for very easy animations. If available, use these APIs to fade-in
			// the progress spinner.
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
				int shortAnimTime = getResources().getInteger(
						android.R.integer.config_shortAnimTime);

				mProgressView.setVisibility(View.VISIBLE);
				mProgressView.animate().setDuration(shortAnimTime)
						.alpha(show ? 1 : 0)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mProgressView.setVisibility(show ? View.VISIBLE
										: View.GONE);
							}
						});

				mFormView.setVisibility(View.VISIBLE);
				mFormView.animate().setDuration(shortAnimTime)
						.alpha(show ? 0 : 1)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								mFormView.setVisibility(show ? View.GONE
										: View.VISIBLE);
							}
						});
			} else {
				// The ViewPropertyAnimator APIs are not available, so simply show
				// and hide the relevant UI components.
				mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
				mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			}
	
	}
	public void setSignBT(){
		 refreshActionBar();
		
	}
	public void setSignBT(WorkOrderListActivity wo) {
		//MyLog.Assert(btok != null, "Sign 按钮未初始化");
		switch (wo.OrderType) {

		case WorkOrder.已到达:

		case WorkOrder.已派:
		case WorkOrder.已启动:
		case WorkOrder.已签字:

		//	btok.setText("");
			mMenuOK.setIcon(R.drawable.sign_bk_bmp);
			//btok.setVisibility(View.VISIBLE);
			//btok.setBackgroundResource(R.drawable.sign_bk_bmp);
			// btok.setWidth(60);
			break;

		case WorkOrder.保养:
			//this.btok.setText("接受保养");
			mMenuOK.setTitle("接受保养");
			//btok.setBackgroundResource(R.drawable.button_style1);
			if ( wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);//btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);//btok.setVisibility(View.GONE);
			break;
		case WorkOrder.抄张:
			//this.btok.setText("接受抄张");
			mMenuOK.setTitle("接受抄张");
			//btok.setBackgroundResource(R.drawable.button_style1);
			if ( wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);//btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);//btok.setVisibility(View.GONE);
			break;
		case WorkOrder.已提交:
			//this.btok.setText("客户签字");
			mMenuOK.setTitle("客户签字");
			//btok.setBackgroundResource(R.drawable.button_style1);
			if ( wo.checkIDs != null && wo.checkIDs.size() > 0)
				mMenuOK.setVisible(true);//btok.setVisibility(View.VISIBLE);
			else
				mMenuOK.setVisible(false);//btok.setVisibility(View.GONE);
			break;
		default:
			mMenuOK.setVisible(false);
			//btok.setBackgroundResource(0);
			//btok.setVisibility(View.GONE);
			break;
		}

	}

	
}
