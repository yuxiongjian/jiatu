package com.jt.act;

import hardware.config.Handphone;

import java.util.HashMap;

import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;
import project.ui.ProjectTableForm.TableFormLayout;
import project.ui.tuya.TuYaView;
import project.util.MyLog;
import com.jt.R;
import com.jt.pojo.SignOrder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TuYaActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = TuYaActivity.class.hashCode();

	/** Called when the activity is first created. */
	protected TuYaView handWrite = null;
	private Button clear = null;

	

	

	// private ImageView saveImage = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_order);
		
		setFormPojo((SignOrder) getIntent().getSerializableExtra(""+SignOrder.serialVersionUID));
		//so.workorder = ids.toArray(new Integer[0]);
		TableFormLayout tl = new TableFormLayout();
		tl.singleRow=true;
		HashMap<String,Bpojo.ViewHolder> hs = ProjectTableForm.initFormLayout(thisAct, R.id.signordertable,
				getFormPojo(), tl);
		setViewMap(hs);
		handWrite = (TuYaView) getViewMap().get("signBmp").ev;
		
		clear = (Button) findViewById(R.id.clear);
		clear.setOnClickListener(new clearListener());
		// save.setOnClickListener(new saveListener());
		//handerl();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		ViewGroup.LayoutParams layoutParams=handWrite.getLayoutParams(); 
		MyLog.Assert(layoutParams!=null, "");
		Message msg = handWrite.handler.obtainMessage();
		msg.arg1 = Handphone.handphone.outSize.right;
		msg.arg2=800;
		msg.what=4;
		msg.obj = handWrite;
		msg.sendToTarget();
		btok.setEnabled(false);
		
	}

	@Override
	public void OnOK(View v) {
		Message msg = new Message();
		msg = Message.obtain(handWrite.handler, 2);
		msg.obj = handWrite;
		handWrite.handler.sendMessage(msg);

	}
		// TODO Auto-generated method stub
	public void OnPostOK() {
		
		
		
	}
	

	@Override
	public void OnCancel(View v) {
		// TODO Auto-generated method stub
		finish();
		super.OnCancel(v);
	}

	private class clearListener implements OnClickListener {

		public void onClick(View v) {
			handWrite.clear();
			btok.setEnabled(false);
			//btok.setVisibility(View.INVISIBLE);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see project.ui.ProjectActivity#handleProjectMessage(android.os.Message)
	 */
	@Override
	public void handleProjectMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleProjectMessage(msg);
		int what = msg.what;
		if( what ==4){
			btok.setEnabled(true);
			
			
		}
		else if (what == 3) {
			
			OnPostOK();
			
			
			
		}
	}
	/*
	public void handleMessage(Message msg){
		int what = msg.what;
		if( what ==4){
			btok.setEnabled(true);
			
			
		}
		else if (what == 3) {
			
			OnPostOK();
			
			
			
		}
		
	}
	
	private static Handler hh;

	public void handerl() {
		setHh(new Handler() {
			@Override
			public void handleMessage(Message msg) {
				handleMessage(msg);
				super.handleMessage(msg);
			}

		});
	}

	public static Handler getHh() {
		return hh;
	}

	public static void setHh(Handler hh) {
		TuYaActivity.hh = hh;
	}

	private class saveListener implements OnClickListener {

		@Override
		public void onClick(View v) {

			Message msg = new Message();
			
			msg = Message.obtain(TuYaView.handler, 2);
			msg.obj = handWrite;
			TuYaView.handler.sendMessage(msg);

		}

	}
	*/
	
}
