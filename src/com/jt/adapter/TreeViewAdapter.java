/**
 * 
 */
package com.jt.adapter;

import java.util.ArrayList;
import java.util.List;

import project.util.MyLog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.text.BoringLayout.Metrics;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jt.R;


import com.jt.pojo.GroupBean;
import com.jt.pojo.TreeDataProvider;
import com.jt.pojo.TreeElement;
import com.google.gson.Gson;

import android.widget.ArrayAdapter;

/**
 * @author thomasy
 * 
 */
public class TreeViewAdapter extends ArrayAdapter {

	public TreeViewAdapter(Context context1, int textViewResourceId,
			List objects) {
		super(context1, textViewResourceId, objects);
		context = context1;
		mInflater = LayoutInflater.from(context);
		nodes = onInitNodes();
		//objects;
		mIconCollapse = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.plus);
		mIconExpand = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.cut);

		tvlsn = new TreeViewlsn();
		tvtextlsn = new TreeTextlsn();

	}

	/**
	 * @param nodes 
	 * @return
	 */
	private List<TreeElement> onInitNodes() {
		// TODO Auto-generated method stub
		
		TreeDataProvider provider = new TreeDataProvider(context);
		
		List<TreeElement> nodes = (List<TreeElement>) provider.getDataList();
		
		//MyLog.Assert(nodes.size()>0,"");
		return nodes;
	}

	/**
	 * @return
	 */
	private List<GroupBean> queryDeptGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	private Context context;
	private LayoutInflater mInflater;
	private List<TreeElement> nodes;
	private Bitmap mIconCollapse;
	private Bitmap mIconExpand;
	private TreeViewlsn tvlsn;
	private int sPosition=0;
	public TreeTextlsn tvtextlsn;
	
   private void changeState(int position){
	   
	   TreeElement te;
	   if ( sPosition >= 0 )
		{
		   te = nodes.get(sPosition);
		
	    if ( te != null )
	    	te.setSelected(false);
		}
	   
	    if ( position >= 0 )
		{
	    	te = nodes.get(position);
		
	    	if ( te != null )
			te.setSelected(true);
		}
		sPosition = position;
		
		
		
	}
	public int getCount() {
		return nodes.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		final TreeElement obj = nodes.get(position);
		convertView = mInflater.inflate(R.layout.left_list_view_item, null);
		holder = new ViewHolder();
		holder.text = (TextView) convertView.findViewById(R.id.treetext);
		holder.icon = (ImageView) convertView.findViewById(R.id.icon);
		holder.viewInfo = obj;
		holder.position = position;
		holder.text.setTag(holder);

		holder.icon.setTag(holder);
		holder.icon.setOnClickListener(tvlsn);
		holder.text.setOnClickListener(tvtextlsn);

		int level = obj.getLevel();
		holder.icon.setPadding(15 * (level ), 0,0,0);
		holder.text.setText(obj.getTitle());
		if (obj.isHasChild() && (obj.isExpanded() == false)) {
			holder.icon.setImageBitmap(mIconCollapse);
		} else if (obj.isHasChild() && (obj.isExpanded() == true)) {
			holder.icon.setImageBitmap(mIconExpand);
		} else if (!obj.isHasChild()) {
			holder.icon.setImageBitmap(mIconCollapse);
			holder.icon.setVisibility(View.INVISIBLE);
		}
		AbsListView.LayoutParams lp ;
		if(obj.isSelected()){
			convertView.setBackgroundResource(R.drawable.menu_item_bg_sel);
			holder.text.setEllipsize(TextUtils.TruncateAt.MARQUEE);}
		else
			convertView.setBackgroundResource(R.drawable.menu_item_bg_sel_off);
		lp = (LayoutParams) convertView.getLayoutParams();
		
		//设置左边行高 部门列表行高
		DisplayMetrics mt = project.config.Config.getMetrics(context);
		double mti =( mt.xdpi * (1.0f/72));
		String v = android.os.Build.VERSION.RELEASE;
		if ( v.startsWith("4.2") ||  v.startsWith("4.3")|| v.startsWith("4.4.2"))
			mti=1.8;//mti =  mt.scaledDensity;//mti = (int) ( mt.xdpi * (1.0f/72));
		else
			mti = 1;	
		if (level>0){
			lp = new AbsListView.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, (int)(52*mti));
			
		}else{
			lp = new AbsListView.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, (int)(65*mti));
		}
		
		convertView.setLayoutParams(lp);
		
		//ViewGroup.LayoutParams params = convertView.getLayoutParams();
	    //params.height = 28;


	    //convertView.setLayoutParams(params);

		
		return convertView;
	}

	/**
	 * @author thomasy
	 * 
	 */

	public final class TreeTextlsn implements View.OnClickListener {
		/**
				 * 
				 */
		private final TreeElement obj;

		/**
		 * @param obj
		 */
		public TreeTextlsn() {
			this.obj = null;
		}

		@Override
		public void onClick(View v) {

			ViewHolder vh = (ViewHolder) v.getTag();

			Intent it1 = new Intent();

			if (vh == null) {
				changeState(-1);
				it1.setAction("SET_DEFAULT_SIG");
				it1.putExtra("groupbean", "");
			} else {
				TreeElement te = (TreeElement) vh.viewInfo;
				int position = vh.position;
				changeState(position);
				String dept = te.getTitle();
				String comp = te.getId();
				if (dept.compareTo(comp) == 0)
					dept = "";
				GroupBean gb = new GroupBean(comp, dept);
				Gson gson = new Gson();
				String bean = gson.toJson(gb);

				it1.setAction("SET_DEFAULT_SIG");
				it1.putExtra("groupbean", bean);
			}

			context.sendBroadcast(it1);

			notifyDataSetInvalidated();

		}

	}

	final class TreeViewlsn implements View.OnClickListener {
		/**
				 * 
				 */
		private final TreeElement obj;

		/**
		 * @param obj
		 */
		public TreeViewlsn() {
			this.obj = null;
		}

		@Override
		public void onClick(View v) {

			onListItemClick(v);

		}
	};

	protected void onListItemClick(View v) {
		// super.onListItemClick(l, v, position, id);

		ViewHolder vh = (ViewHolder) v.getTag();
		TreeElement te = (TreeElement) vh.viewInfo;
		int position = vh.position;
		Log.i("TreeView", "position:" + vh.position);
		if (!te.isHasChild()) {
			// Toast.makeText(this, nodes.get(position).getTitle(), 2000);
			return;
		}

		if (te.isExpanded()) {
			te.setExpanded(false);
			// TreeElement element = nodes.get(position);
			ArrayList<TreeElement> temp = new ArrayList<TreeElement>();

			for (int i = position + 1; i < nodes.size(); i++) {
				if (te.getLevel() >= nodes.get(i).getLevel()) {
					break;
				}
				temp.add(nodes.get(i));
			}

			nodes.removeAll(temp);

			notifyDataSetChanged();

		} else {
			TreeElement obj = nodes.get(position);
			obj.setExpanded(true);
			int level = obj.getLevel();
			int nextLevel = level + 1;

			for (TreeElement element : obj.getChilds()) {
				element.setLevel(nextLevel);
				element.setExpanded(false);
				nodes.add(position + 1, element);

			}
			notifyDataSetChanged();
		}
	}

	class ViewHolder {
		TextView text;
		ImageView icon;
		int position;
		Object viewInfo;

	}

}
