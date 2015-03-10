package com.jt.act.workprocess;

import java.util.HashMap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;
import project.ui.*;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.pojo.*;

import project.ui.AnimationTabHost;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.ui.TabInitData;
import project.util.MyLog;

public class ResolveOrderActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ResolveOrderActivity.class
			.hashCode();
	private WorkOrder wd = null;

	public ResolveOrderActivity() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	private void initTabData() {
		tabInitData = (TabInitData) getIntent()
				.getSerializableExtra("" + TabInitData.serialVersionUID);
		tabInitData.tabActivity = this;
		mTabHost = ((ProjectTabHostActivity)tabInitData.rootAct).mTabHost;
		tabid = mTabHost.getCurrentTab();
	}

	/* (non-Javadoc)
	 * @see project.util.ProjectActivity#refreshData(java.util.HashMap)
	 */
	@Override
	public HashMap<String, String> refreshData(HashMap<String, String> retMap) {
		// TODO Auto-generated method stub
		HashMap<String, String> tmap =  super.refreshData(retMap);
		if(this.getFormPojo().getClass().equals(ResolvePaperCountUI.class)){
			ResolvePaperCountUI pc = ( ResolvePaperCountUI) getFormPojo();
			for (int i=0;i< fds.length;i++) {
				String fd=fds[i];
				
				Object v=null;
				Object mv=null;
				try {
					String mfd=maxfds[i];
					mv = pc.getFieldValue(mfd);
					v = pc.getFieldValue(fd);
				} catch (Exception e) {
					MyLog.False(e);
				}
				Integer vi= v==null?null:Integer.valueOf(v.toString());
				
			
				Integer mvi= mv==null?0:Integer.valueOf(mv.toString());
				if( vi!= null && vi<mvi  ){
					Bpojo.ViewHolder os = getViewMap().get(fd);
					tmap.put(os.pojoUI.PojoUIName(), "数量过小，不正确");
				}else if(mvi==0 && vi==null){
					Bpojo.ViewHolder os = getViewMap().get(fd);
					tmap.remove(os.pojoUI.PojoUIName());
				}
			}
		}else if(this.getFormPojo().getClass().equals(com.jt.pojo.ResolveResultUI.class)){
			ResolveResultUI pc = ( ResolveResultUI) getFormPojo();
			Bpojo.ViewHolder os = getViewMap().get("unrepairedreason");
			if(  pc.ismachineok !=null && pc.ismachineok   )
				tmap.remove(os.pojoUI.PojoUIName());
		}
			
		return tmap;
	}
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		MyLog.Log(arg0);
		String fn = (String) arg0
				.getTag("fieldname".hashCode());
		
		MyLog.Log(fn);
		if( fn.equals("ismachineok")){
			if ( arg2 ==1 ){
				this.getViewMap().get("unrepairedreason").tv.setTextColor(Color.BLACK);
			}
			else this.getViewMap().get("unrepairedreason").tv.setTextColor(Color.RED);
		}
		
		return;
	}
	public  Integer tabid ;
	private AnimationTabHost mTabHost;

	// Bpojo rp=null;
	public void onCreate(Bundle savedInstanceState) {

		setAu(AppLoginActivity.getUser());
		super.onCreate(savedInstanceState);
		
		@SuppressWarnings("unchecked")
		Class<? extends Bpojo> classtype = (Class<? extends Bpojo>) getIntent()
				.getSerializableExtra("" + Class.class.hashCode());
		wd = (WorkOrder) getIntent().getSerializableExtra(
				"" + WorkOrder.getSerialversionuid());
		initTabData();
		if (classtype.equals(WorkRequest.class)) {
			setContentView(R.layout.view_workorder);
			wd.getWorkRequest().setEditType(0);
			setFormPojo(wd.getWorkRequest());
			setViewMap(ProjectTableForm.initFormLayout(thisAct,
					R.id.viewworkordertable, getFormPojo(), null));
			tabInitData.tabActivity = null; // to skip refreshUI;
		} else {
			setContentView(R.layout.resolve_order);
			if (classtype.equals(ResolveRepairUI.class)) {
				setFormPojo(new ResolveRepairUI());
			} else if (classtype.equals(ResolvePaperCountUI.class)) {
				setFormPojo(new ResolvePaperCountUI());
				getFormPojo().copyValue(wd.getWorkRequest());
				this.setbWatchble(true);
			} else if (classtype.equals(ResolveResultUI.class)) {
				setFormPojo(new ResolveResultUI());
			} else if (classtype.equals(ResolveP4UI.class)) {
				setFormPojo(new ResolveP4UI());
			}

			getFormPojo().copyValue(wd);
			getFormPojo().setEditType(wd.getEditType());
			setViewMap(ProjectTableForm.initFormLayout(thisAct,
					R.id.solveorderU1table, getFormPojo(), null));
		}
		// this.findViewById(id)
		// rp.refreshData(getViewMap());
	}

	static String[] fdscount = { "blacknum", "numzydc", "numptdc", "colornum1",
			"colornum2"};
	static String[] fds = { "blacknum", "numzydc", "numptdc", "colornum1",
		"colornum2", "chokedpapernum","totalplatenum"};
	static String[] maxfds = { "max_blacknum", "max_numzydc", "max_numptdc",
			"max_colornum1", "max_colornum2","max_chokedpapernum","max_totalplatenum" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.util.ProjectActivity#onFocusChange(android.view.View,
	 * boolean)
	 */
	@Override
	public void onFocusChange(View mViewin, boolean hasFocus) {
		
		super.onFocusChange(mViewin, hasFocus);
		Bpojo bp = this.getFormPojo();
		String fn = (String) mViewin.getTag("fieldname".hashCode());
		MyLog.Log(fn+":focused:+"+hasFocus);
		if (!bp.getClass().equals(ResolvePaperCountUI.class) || fn == null
				|| fn.length() == 0)
			return;
		EditText mView = (EditText)mViewin;
		if( hasFocus){
			//mView.setError(null);
			return;
		}
		Integer v = null;
		Integer mv = null;
		String s = null;
		String ms = null;
		int i=0;
		for (;i< fds.length;i++) {
			String fd=fds[i];
			if (fn.equals(fd)) {
				Object o = bp.getViewValue(mView, null, null);
				if (o != null&& (s=o.toString()).length()>0) {
					v = Integer.valueOf(s);
					Object mo=null;
					try {
						mo = bp.getFieldValue(maxfds[i]);
					} catch (Exception e) {
						MyLog.False(e);
					} 
					if ( mo != null && (ms=mo.toString()).length()>0 ){
							mv = Integer.valueOf(ms);
							if( v<mv ){
								new MyHandler().post(new MyRunnable( mView,mTabHost,tabid ){
									public void run() {
										((EditText)objs[0]).setError("数量太少，请重新输入");
										//((EditText)objs[0]).requestFocus();
										AnimationTabHost mTabHost=	((AnimationTabHost)objs[1]);
										mTabHost.setCurrentTab( (Integer)objs[2]);
										
									}
								});
								
								
							}
							else if( v>= 10*mv && mv>0){
								new MyHandler().post(new MyRunnable( mView, mTabHost,tabid  ){
									public void run() {
										((EditText)objs[0]).setError("数量过多，请注意！");
										AnimationTabHost mTabHost=	((AnimationTabHost)objs[1]);
										mTabHost.setCurrentTab( (Integer)objs[2]);	
									}
								});
								//Toast.makeText(thisAct, "数量过多，请注意！", Toast.LENGTH_LONG).show();
							}
						
					}
				}
				break;
			}
			
		}
		// TODO Auto-generated method stub

	}

	public void afterTextChanged(View mView, Editable s) {
		// TODO Auto-generated method stub
		Bpojo bp = this.getFormPojo();
		boolean find=false;
		if (bp.getClass().equals(ResolvePaperCountUI.class)) {
			String fn = (String) mView.getTag("fieldname".hashCode());
			
			for(String fns : fdscount){
				
				if ( fn.equals(fns) ){
					find = true;
				
					break;
				}
			}
			if ( !find )
				return;
			MyLog.Log(fn);
			// this.viewMaps.get(fn).ev.
			int total = 0;
			int i = 0 ;
			for (String fd : fdscount) {
				View vm = getViewMap().get(fd).ev;
				Object o = bp.getViewValue(vm, null, null);
				if (o != null) {
					String s1 = o.toString();
					if (s1.length() > 0)
						total += Integer.valueOf(s1);
				}
				
			}
			TextView vm = (TextView) getViewMap().get("totalcopynum").ev;
			vm.setText("" + total);
		}

	}

	public void onResume() {
		super.onResume();
		// 代码
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Intent re = new Intent();
		re.putExtra("" + getFormPojo().getClass().hashCode(), getFormPojo());
		setResult(Class.class.hashCode(), re);
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	private ThyTextWatcher watcher = null;

}
