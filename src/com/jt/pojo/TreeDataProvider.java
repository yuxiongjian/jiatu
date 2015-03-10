package com.jt.pojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.util.MyLog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;


/**
 * ���οؼ�����ṩ��
 * @author thomasy
 *
 */
public class TreeDataProvider implements DataProvider{
	private Context context=null;
	private  List<TreeElement> nodes = new ArrayList<TreeElement>();
	public TreeDataProvider(Context context) {
		super();
		this.context = context;
		
	}
	
	public List<GroupBean> queryDeptGroup() throws SQLException{
		
		
		List<GroupBean> list=new ArrayList<GroupBean>();
		
		GroupBean cg_all=new GroupBean();
	
		
		
	
		return list;
	}
	
	
	public void initDataSource() throws SQLException{
		
		String lstComp = "";
		TreeElement compElement=null;
		TreeElement deptElement=null;
		List<GroupBean> lo = queryDeptGroup();
		for(Object ob: lo ){
			
			GroupBean gb = (GroupBean)ob;
			String cComp = gb.getCompname();
			if (cComp.compareTo(lstComp) != 0){
				compElement = new TreeElement(cComp, cComp);   
				lstComp = cComp;
				nodes.add(compElement);
				
			}
			else{
				String dept = gb.getDeptName();
				deptElement = new TreeElement(cComp, dept);   
				compElement.addChild(deptElement);
				
			}
				
				
			
		}
	
	}
	
	private void initDataSourceSample(){	
		TreeElement fisrtelement1 = new TreeElement("01", "111");  
        TreeElement fisrtelement2 = new TreeElement("02", "222");  
        TreeElement fisrtelement3 = new TreeElement("03", "333");  
        nodes.add(fisrtelement1);  
        nodes.add(fisrtelement2);  
        nodes.add(fisrtelement3);
        
        TreeElement secondelement1 = new TreeElement("01_01", "444"); 
        TreeElement secondelement2 = new TreeElement("01_02", "555");
        TreeElement secondelement3 = new TreeElement("01_03", "555");
        fisrtelement1.addChild(secondelement1);
        fisrtelement1.addChild(secondelement2);
        fisrtelement1.addChild(secondelement3);
        
        TreeElement thirdelement1 = new TreeElement("01_01_01", "666"); 
        TreeElement  thirdelement2 = new TreeElement("01_01_02", "777");
        TreeElement  thirdelement3 = new TreeElement("01_01_03", "666");
        TreeElement  thirdelement4 = new TreeElement("01_01_04", "444");
        TreeElement  thirdelement5 = new TreeElement("01_01_05", "666");
        secondelement1.addChild(thirdelement1);
        secondelement1.addChild(thirdelement2);
        secondelement1.addChild(thirdelement3);
        secondelement1.addChild(thirdelement4);
        secondelement1.addChild(thirdelement5);
       /* 
        TreeElement fourelement1 = new TreeElement("01_01_01_01", "TextView�ؼ�",getIntent(TextViewActivity.class));
        TreeElement fourelement2 = new TreeElement("01_01_01_02", "EditTextView�ؼ�",getIntent(EditTextViewActivity.class));
        thirdelement1.addChild(fourelement1);
        thirdelement1.addChild(fourelement2);
        
        TreeElement thirdelement0301 = new TreeElement("01_03_01", "LinearLayout����ʾ��",getIntent(LinearLayoutActivity.class));
        TreeElement thirdelement0302 = new TreeElement("01_03_02", "TableLayout����ʾ��",getIntent(TableLayoutActivity.class));
        TreeElement thirdelement0303 = new TreeElement("01_03_03", "LinearLayout���Ӳ���ʾ��",getIntent(LinearLayoutComplexActivity.class));
        TreeElement thirdelement0304 = new TreeElement("01_03_04", "Linear Table ��ϲ���",getIntent(Linear_Table_LayoutComplexActivity.class));
        TreeElement thirdelement0305 = new TreeElement("01_03_05", "RelitaveLayout ����",getIntent(RelativeLayoutActivity.class));
        TreeElement thirdelement0306 = new TreeElement("01_03_06", "RelitaveLayout����ʾ��",getIntent(RelativeLayoutComplexActivity.class));
        
        secondelement3.addChild(thirdelement0301);
        secondelement3.addChild(thirdelement0302);
        secondelement3.addChild(thirdelement0303);
        secondelement3.addChild(thirdelement0304);
        secondelement3.addChild(thirdelement0305);
        secondelement3.addChild(thirdelement0306);*/
	}
	
	private Intent getIntent(Class<?> cls){
		Intent intent = new Intent();
		intent.setClass(context, cls);
		return intent;
	}
	
	
	@Override
	public void foward(String caption) throws Exception {
		
	}

	@Override
	public List<?> getDataList() {
		if(null == nodes || nodes.size()==0)
			try {
				initDataSource();
			} catch (SQLException e) {
				
				MyLog.Assert(false,"");
				// TODO Auto-generated catch block
				// ????
			}
		return   nodes;
	}

	/* (non-Javadoc)
	 * @see com.cmw.android.data.DataProvider#getDataSource()
	 */
	
	
}
