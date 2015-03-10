package com.jt.act.workprocess;

import hardware.config.Handphone;

import java.util.HashMap;

import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.ui.ProjectTableForm.TableFormLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.pojo.ListInfo;



public class CheckHistoryActivity extends ProjectActivity {

	/**
	 * 
	 */
	protected String url = "";
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static final long serialVersionUID = CheckHistoryActivity.class.hashCode() ;
	//private View webview;
	
	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.check_history);
		//url = (String) getIntent().getSerializableExtra("url");
		formPojo = (ListInfo) getIntent().getSerializableExtra(""+ListInfo.serialVersionUID);
		//ListInfo lf = (ListInfo) formPojo;
		//lf.setUrl(String.format("%s?sid=%s&userid=%s&barcode=%s", url,au.getSid(),au.getUserid(),lf.getBarCode()));
		TableFormLayout tl = new TableFormLayout();
		tl.singleRow=true;
		tl.tbl.height= TableRow.LayoutParams.MATCH_PARENT;
		tl.wtxtl.width = Handphone.handphone.outSize.right;
		tl.wtxtl.height=850;
		HashMap<String,Bpojo.ViewHolder> hs = ProjectTableForm.initFormLayout(thisAct, R.id.check_history_table,	getFormPojo(), tl);
		setViewMap(hs);	
		//webview = getViewMap().get("url").ev;
		

	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//ViewGroup.LayoutParams layoutParams =  (LayoutParams) webview.getLayoutParams(); 
		//MyLog.Assert(layoutParams!=null, "");
		//TableRow.LayoutParams nl = new TableRow.LayoutParams(layoutParams);
		//nl.width = Handphone.handphone.outSize.right; 
		//nl.height = TableRow.LayoutParams.MATCH_PARENT; 
		//ViewParent v = webview.getParent();
		
		//webview.setLayoutParams(nl);
		//webview.refreshDrawableState();
		super.onPostCreate(savedInstanceState);
	}
	@Override
	public void OnCancel(View v) {
		// TODO Auto-generated method stub
		refreshData(null);
		super.OnCancel(v);
		finish();
	}
}
