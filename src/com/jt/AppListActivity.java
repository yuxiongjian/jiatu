package com.jt;

import java.util.ArrayList;
import java.util.HashMap;

import project.ui.ProjectActivity;
import project.util.UpdateActivity;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jt.act.SaveMachineLocActivity;
import com.jt.act.workprocess.MaintainTabHostActivity;
import com.jt.act.workprocess.NextWorkOrderActivity;
import com.jt.act.workprocess.WorkOrderListActivity;
import com.jt.act.workprocess.WorkProcessTabHostActivity;
import com.jt.pojo.WorkOrder;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class AppListActivity extends ProjectActivity {
	/**
	 * 
	 */
	private static final long serialVersionUID = AppListActivity.class.hashCode();

	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */

	private int[] mImages = { R.drawable.app01, R.drawable.app02,R.drawable.app03,R.drawable.app04,R.drawable.app05,R.drawable.app06

	};
	private int[] mTextids = { R.string.app1, R.string.app2 , R.string.app3, R.string.app4 , R.string.app5  , R.string.app6};

	Activity ThisAct = AppListActivity.this;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_jt_app);
		Resources rs = this.getBaseContext().getResources();

		if (AppLoginActivity.iLogin == 1)
			UpdateActivity.ShowUpgrade(this, 1);
		// 实例化GridView
		GridView mGridView = (GridView) findViewById(R.id.appview);
		// 生成动态数组，并且传入数据
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < mImages.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", mImages[i]);// 添加图像资源的ID    //$NON-NLS-1$
			map.put("ItemText", rs.getString(mTextids[i]));// 按序号做ItemText    //$NON-NLS-1$
			lstImageItem.add(map);
		}
		// 构建一个适配器
		SimpleAdapter simple = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item,
				new String[] { "ItemImage", "ItemText" }, new int[] { //$NON-NLS-1$ //$NON-NLS-2$
				R.id.ItemImage, R.id.ItemText });
		mGridView.setAdapter(simple);
		// 添加选择项监听事件
		mGridView.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent _intent = null;

				if (position > 0 && AppLoginActivity.iLogin != 1) {
					Toast t = Toast.makeText(ThisAct.getBaseContext(),
							R.string.action_sign_warn, Toast.LENGTH_LONG);
					t.setGravity(Gravity.CENTER, 0, 0);
					t.show();

					
					ThisAct.finish();
					return;

				}
				switch (position) {
				case 0:
					_intent = new Intent(ThisAct, SaveMachineLocActivity.class);
					ThisAct.startActivityForResult(_intent, 0);
					break;
				case 1:

					 _intent = new Intent(ThisAct, WorkProcessTabHostActivity.class);
					ThisAct.startActivityForResult(_intent, 0);
					break;

				case 2:
					 _intent = new Intent(ThisAct, WorkOrderListActivity.class).putExtra(
								"ordertype", WorkOrder.消息);
						ThisAct.startActivityForResult(_intent, 0);
					break;
				case 3:
					 _intent = new Intent(ThisAct, MaintainTabHostActivity.class);
						ThisAct.startActivityForResult(_intent, 0);
					break;
				case 4:
					 _intent = new Intent(ThisAct, NextWorkOrderActivity.class);
						ThisAct.startActivityForResult(_intent, 0);
					break;
				case 5://TODO
					 _intent = new Intent(ThisAct, WorkOrderListActivity.class).putExtra(
								"ordertype", WorkOrder.调度);
						ThisAct.startActivityForResult(_intent, 0);
					break;
				default:

				}
				
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int menuId = item.getItemId();

		switch (menuId) {
		/*
		case R.id.action_settings:
			Intent intent1 = new Intent();
			// intent1.setClass(this,
			// com.chiic.mobapp.contact.view.SettingsActivity.class);
			startActivity(intent1);

			break;
			*/

		case R.id.action_sync:
			String title = getResources().getString(R.string.download_title);
			String message = getResources()
					.getString(R.string.download_message);
			final String networkerr = getResources().getString(
					R.string.message_network_error);
			boolean indeterminate = true;
			final Dialog dialog = ProgressDialog.show(this, title, message,
					indeterminate);
			new Thread() {
				@Override
				public void run() {
					try {
						// sleep(10 * 1000);

					} catch (Exception e) {
						Toast t = Toast.makeText(ThisAct, networkerr, 5000);
						t.setGravity(Gravity.CENTER, 0, 0);
						t.show();
					} finally {
						dialog.dismiss();

					}
				}
			}.start();
			break;
		case R.id.action_upgrade:
			UpdateActivity.ShowUpgrade(this, 0);

			break;
		case R.id.action_Logout:
			finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
