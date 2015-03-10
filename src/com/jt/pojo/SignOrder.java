package com.jt.pojo;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;
import project.pojo.Bpojo.ViewHolder;

public class SignOrder extends Bpojo {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = SignOrder.class.hashCode();

	@PojoUI(PojoUIName="用户建议", PojoUIOrder = 00, CanBeNull=true, PojoEditor="EditText",SingleRow=true)
	public String	suggest	;
	@PojoUI(PojoUIName="满意度评价", PojoUIOrder = 10, CanBeNull=false,PojoEditor="Spinner", SpinnerClass=TrueFalseInt.class,SingleRow=true)
	public Integer	custsati=1	;

	@PojoUI(PojoUIName="用户签字", PojoUIOrder = 20, CanBeNull=false,PojoEditor="project.ui.tuya.TuYaView",SingleRow=true)
	volatile public Bitmap signBmp;
	public String signature;
	public void decodeSigatureToBmp(){
		if( signature==null || signature.length()==0)
			return;
		byte[] bmpByteArray = Base64.decode(signature, Base64.DEFAULT);
		signBmp=   BitmapFactory.decodeByteArray(bmpByteArray, 0, bmpByteArray.length); 
	}
	
	/* (non-Javadoc)
	 * @see project.pojo.Bpojo#refreshData(java.util.HashMap, java.util.HashMap)
	 */
	@Override
	public HashMap<String, String> refreshData(
			HashMap<String, Bpojo.ViewHolder> viewMap, HashMap<String, String> retMap) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// TODO Auto-generated method stub
		HashMap<String, String> retMap1 =  super.refreshData(viewMap, retMap);
		signBmp.compress(Bitmap.CompressFormat.PNG, 100, out);
		int count = signBmp.getByteCount();
		byte[] bmpByteArray;
		bmpByteArray = out.toByteArray();
		signBmp = null;
		signature = Base64.encodeToString( bmpByteArray ,	Base64.DEFAULT);
		if( suggest==null)
			suggest="";
		
		return retMap1;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}


	public Integer[] workorders;

}
