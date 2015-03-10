package com.jt.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import project.config.Config;
import project.ui.IListDataItem;
import project.ui.ProjectActivity;
import project.ui.QuickAlphabeticBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.jt.R;
import com.jt.appservice.JtService;
import com.jt.act.workprocess.ArrangeWorkOrderActivity;
import com.jt.act.workprocess.CheckHistoryActivity;
import com.jt.act.workprocess.ReadMaintainActivity;
import com.jt.act.workprocess.ReadMessageActivity;
import com.jt.act.workprocess.ReadWorkOrderActivity;
import com.jt.act.workprocess.ResolveOrderTabHostActivity;
import com.jt.act.workprocess.WorkOrderListActivity;
import com.jt.pojo.ListInfo;
import com.jt.pojo.MobileMessage;
import com.jt.pojo.PredictOrder;
import com.jt.pojo.User;
import com.jt.pojo.WorkOrder;
import com.jt.pojo.WorkOrder.WorkOrderImpl;

public class AlListViewAdapter extends BaseAdapter  implements OnGestureListener,OnTouchListener{
	private LayoutInflater inflater;
	private List<IListDataItem> DataList;
	private HashMap<String, Integer> alphaIndexer;
	private String[] sections;
	private ProjectActivity ctx;
	QuickAlphabeticBar alpha;
	public HashSet<Integer> checkIDs = new HashSet<Integer>();
	int OrderType;
	public ListViewLsn lsn;
	static private int[] viewMenu = new int[] { R.string.工单详情,
			R.string.repairhistory, R.string.送货历史, R.string.维修经验, R.string.故障帮助 };
	static private int[] viewMenu2 = new int[] { R.string.工单详情,
		R.string.repairhistory, R.string.送货历史, R.string.维修经验, R.string.故障帮助};
	static private String url[] = Config.getUrls();
	private OnCheckedChangeListener checkBoxListener;
	private GestureDetector gestureDetector;

	public AlListViewAdapter(ProjectActivity context, List<IListDataItem> list,
			QuickAlphabeticBar alpha, int OrderType) {

		this.ctx = context;
		this.inflater = LayoutInflater.from(context);
		this.DataList = list;
		this.OrderType = OrderType;
		this.sections = new String[list.size()];
		this.alpha = alpha;
		lsn = new ListViewLsn();
		checkBoxListener = new CheckBoxListener();
		gestureDetector = new GestureDetector(ctx,this); 
		
		if (alpha != null) {
			this.alphaIndexer = new HashMap<String, Integer>();
			for (int i = 0; i < list.size(); i++) {
				String name = getAlpha(list.get(i).getSortKey());
				if (!alphaIndexer.containsKey(name)) {
					alphaIndexer.put(name, i);
				}
			}

			Set<String> sectionLetters = alphaIndexer.keySet();
			ArrayList<String> sectionList = new ArrayList<String>(
					sectionLetters);
			Collections.sort(sectionList);
			sections = new String[sectionList.size()];
			sectionList.toArray(sections);

			alpha.setAlphaIndexer(alphaIndexer);
		}
		this.checkIDs = context.checkIDs;
	}

	@Override
	public int getCount() {
		return DataList.size();
	}

	@Override
	public Object getItem(int position) {
		return DataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void remove(int position) {
		DataList.remove(position);
	}

	public class TextLsnObject {
		int type;
		int id;
		String phone;
	}

	public class CheckBoxListener implements OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				// Toast
				checkIDs.add((Integer) buttonView.getTag());
				// Toast.makeText(ctx,
				// buttonView.getText()+"选择",Toast.LENGTH_LONG ).show();

			} else {
				checkIDs.remove((Integer) buttonView.getTag());

				// Toast.makeText(ctx,
				// buttonView.getText()+"取消选择",Toast.LENGTH_SHORT ).show();
			}
			WorkOrderListActivity wolact = (WorkOrderListActivity) ctx;
			wolact.setSignBT();

		}
	}

	public final class ListViewLsn implements OnItemClickListener {
		ProjectActivity pat;

		public ListViewLsn() {
			super();
			pat = ctx;

		}

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			// notifyDataSetChanged();
			onListItemClick(parent, view, position, id);

		}

	}

	static ListTextLsn textLsn;
	{
		textLsn = new ListTextLsn();
	}

	public final class ListTextLsn implements OnClickListener {
		Activity pat;

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {


		}

	}

	public void onListItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// pat.onItemClick(parent, view, position, id);
		IListDataItem cb = (IListDataItem) getItem(position);
		switch(OrderType){
		case WorkOrder.消息:
			openItemDetail(cb);
			break;
		case WorkOrder.调度:
			showMenuDialog(viewMenu2, cb);
			break;
		default:
			showMenuDialog(viewMenu, cb);
		}
	}

	public void showMenuDialog(final int[] argid, final IListDataItem cb) {

		String[] args = new String[argid.length];
		Resources rs = ctx.getResources();
		for (int i = 0; i < argid.length; i++) {
			args[i] = rs.getString(argid[i]);
		}
		new AlertDialog.Builder(ctx).setTitle(cb.getTitle())
				.setItems(args, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						int helptype = (which == 4 ? 0 : 1);
						Intent _intent;
						switch (which) {
						case 4:// 故障代码
						case 3:// 维修经验

						case 1:// 维修
						case 2:// 送货

							ListInfo lf = new ListInfo();
							lf.setBarCode(cb.getBarcode());

							lf.setUrl(String
									.format("%s?sid=%s&userid=%s&barcode=%s&helptype=%d",
											url[which], ctx.getAu().getSid(),
											((com.jt.pojo.User)ctx.getAu()).getUserid(),
											cb.getBarcode(), helptype));
							_intent = new Intent(ctx,
									CheckHistoryActivity.class);
							_intent.putExtra("" + ListInfo.serialVersionUID, lf);
							ctx.startActivityForResult(_intent,
									(int) CheckHistoryActivity.serialVersionUID);

							break;

						case 0:
							openItemDetail(cb);
							break;
						}

					}
				}).show();
	}

	public void openItemDetail(IListDataItem cb) {
		// IListDataItem cb = (IListDataItem) getItem(position);

		notifyDataSetChanged();
		Intent _intent = null;
		if (cb != null && cb.getClass().equals(WorkOrder.class)) {
			try {
				cb = JtService.loadWorkOrder(User.cUser.getSid(), User.cUser.getUsername(), (Integer) cb.getID());
			} catch (Exception e) {
				
				Toast.makeText(ctx, "打开工单错误：" + cb.getID()+":"+e.getMessage(), Toast.LENGTH_LONG)
						.show();
				return;
			}
		}
		switch (OrderType) {
		case WorkOrder.消息:
			_intent = new Intent(ctx, ReadMessageActivity.class);
			_intent.putExtra("" + MobileMessage.serialVersionUID,
					(MobileMessage) cb);
			ctx.startActivityForResult(_intent,
					(int) ReadMessageActivity.serialVersionUID);
			break;
		case WorkOrder.保养:
		case WorkOrder.抄张:
			_intent = new Intent(ctx, ReadMaintainActivity.class);
			_intent.putExtra("" + PredictOrder.serialVersionUID,
					(PredictOrder) cb);
			ctx.startActivityForResult(_intent,
					(int) ReadMaintainActivity.serialVersionUID);
			break;
		case WorkOrder.调度:
			_intent = new Intent(ctx, ArrangeWorkOrderActivity.class);
			_intent.putExtra("" + WorkOrder.serialVersionUID, (WorkOrder) cb);
			ctx.startActivityForResult(_intent,
					(int) ArrangeWorkOrderActivity.serialVersionUID);
			break;
		case WorkOrder.已派:
		case WorkOrder.已启动:
			_intent = new Intent(ctx, ReadWorkOrderActivity.class);
			_intent.putExtra("" + WorkOrder.serialVersionUID, (WorkOrder) cb);
			ctx.startActivityForResult(_intent,
					(int) ReadWorkOrderActivity.serialVersionUID);
			break;
		case WorkOrder.已签字:
		case WorkOrder.已提交:
		case WorkOrder.已到达:
			_intent = new Intent(ctx, ResolveOrderTabHostActivity.class);
			WorkOrderImpl wdDao = new WorkOrderImpl(ctx);
			WorkOrder wdindb = wdDao.get((Integer) cb.getID());
			if (wdindb != null && OrderType == WorkOrder.已到达) {
				WorkOrder wd = (WorkOrder) cb;
				wdindb.setWorkMessage(wd.getWorkMessage());
				wdindb.setWorkRequest(wd.getWorkRequest());
				wdindb.setWorkorderMessageID(wd.getWorkorderMessageID());
				_intent.putExtra("" + WorkOrder.serialVersionUID, wdindb);
			} else
				_intent.putExtra("" + WorkOrder.serialVersionUID,
						(WorkOrder) cb);
			ctx.startActivityForResult(_intent,
					(int) ResolveOrderTabHostActivity.serialVersionUID);
			break;

		default:
			;
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		IListDataItem itemData = DataList.get(position);

		if (convertView == null) {
			switch (OrderType) {
			case WorkOrder.消息:
				convertView = inflater.inflate(R.layout.home_list_item_info,
						null);
				break;
			case WorkOrder.已提交:
				convertView = inflater.inflate(R.layout.home_list_item_sign,
						null);
				break;
			case WorkOrder.抄张:
			case WorkOrder.保养:
				convertView = inflater.inflate(
						R.layout.home_list_item_maintain, null);
				break;
			default:
				convertView = inflater.inflate(R.layout.home_list_item, null);
				break;
			}
			// if(this.selectid == position){
			// convertView.setBackgroundColor(Color.DKGRAY);
			// }
			// else
			// convertView.setBackgroundColor(Color.LTGRAY);
			holder = new ViewHolder();
			holder.address = (TextView) convertView.findViewById(R.id.address);
			holder.alpha = (TextView) convertView.findViewById(R.id.alpha);

			holder.name = (TextView) convertView
					.findViewById(R.id.ListItemName);
			holder.name.setSingleLine(false);
			holder.orderTime = (TextView) convertView
					.findViewById(R.id.ListItemTime);
			holder.type = (TextView) convertView
					.findViewById(R.id.type);
			holder.bref = (TextView) convertView
					.findViewById(R.id.ListItemBref);
			holder.cb = (CheckBox) convertView.findViewById(R.id.signBox);
			
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.orderTime.setText(itemData.getTime() == null ? "" : (itemData
				.getTime()));
		holder.name.setText(itemData.getName());

		if (holder.bref != null)
			holder.bref.setText(itemData.getBref());
		if (holder.type != null)
			holder.type.setText(itemData.getType());
		if (holder.address != null)
			holder.address.setText(itemData.getAddress());
		
		if ((OrderType == WorkOrder.已派 || OrderType == WorkOrder.消息)
				&& itemData.isUnRead()) {
			holder.name.setTextColor(Color.RED);
			if (OrderType == WorkOrder.已派)
				holder.orderTime.setTextColor(Color.RED);

		}

		if (OrderType == WorkOrder.已提交 || OrderType == WorkOrder.保养
				|| OrderType == WorkOrder.抄张) {

			holder.cb.setTag(itemData.getID());
			if (checkIDs.contains(itemData.getID()))
			{
				holder.cb.setChecked(true);
				
			
			}
			else
				holder.cb.setChecked(false);

			// holder.cb.setActivated(true);
			holder.cb.setVisibility(View.VISIBLE);
			holder.cb.setOnCheckedChangeListener(checkBoxListener);

		} else if (holder.cb != null)
			holder.cb.setVisibility(View.INVISIBLE);
		convertView.setTag(holder);

		if (alpha != null) {
			String currentStr = getAlpha(itemData.getSortKey());

			String previewStr = (position - 1) >= 0 ? getAlpha(DataList.get(
					position - 1).getSortKey()) : " ";

			if (!previewStr.equals(currentStr)) { //
				holder.alpha.setVisibility(View.VISIBLE);
				holder.alpha.setText(currentStr);
			} else {
				holder.alpha.setVisibility(View.GONE);
			}
		}
		return convertView;
	}

	private static class ViewHolder {
		public TextView address;

		TextView alpha;
		TextView name;
		TextView orderTime;
		TextView bref;
		TextView type;
		CheckBox cb;
	}

	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);

		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return ("" + c + "").toUpperCase(Locale.US); //
		} else {
			return "#";
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		return ctx.onFling(e1, e2, velocityX, velocityY);
	}

	@Override
	public void onLongPress(MotionEvent e) {
		
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
	
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		return gestureDetector.onTouchEvent(event);
	}
}
