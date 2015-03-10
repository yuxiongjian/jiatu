/**
 * 
 */
package com.jt.act.workprocess;

import android.os.Bundle;
import android.view.View;

import com.jt.AppLoginActivity;
import com.jt.R;
import com.jt.pojo.PredictOrder;
import project.ui.ProjectActivity;
import project.ui.ProjectTableForm;



/**
 * @author thomasy
 *
 */
public class ReadMaintainActivity extends ProjectActivity {

	/**
	 * 
	 */
	public static final long serialVersionUID = ReadMaintainActivity.class.hashCode();
	public void onCreate(Bundle savedInstanceState) {
		setAu(AppLoginActivity.getUser());
		setContentView(R.layout.read_maintain);
	
		super.onCreate(savedInstanceState);
		PredictOrder wd = null;
		wd = (PredictOrder) (formPojo = (PredictOrder) getIntent().getSerializableExtra(""+PredictOrder.getSerialversionuid()));
		if( wd.getWorktype().equals("CZ"))
			this.setTitle("抄张");
		else
			this.setTitle("保养");
		ProjectTableForm.initFormLayout(thisAct,R.id.bpojotable, getFormPojo(),null);

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

}
