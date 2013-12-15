package com.liu.Adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.liu.QRCode.R;

public class EnlistviewAdapter extends BaseAdapter {
	private Context context;
	/** 联系人名称 **/
	private ArrayList<String> mContactsName = new ArrayList<String>();

	public EnlistviewAdapter(Context context, ArrayList<String> mContactsName) {
		this.context = context;
		this.mContactsName = mContactsName;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContactsName.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MViewHolder viewholder = new MViewHolder();
		if(convertView !=null){
			viewholder =(MViewHolder) convertView.getTag();// 返回视图标签
		}
		else {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.enlistview, null);
			viewholder.mm = (ImageView) convertView
					.findViewById(R.id.enlistview_mm);
			viewholder.name = (TextView) convertView
					.findViewById(R.id.enlistview_name);
			viewholder.add = (TextView) convertView
					.findViewById(R.id.enlistview_add);
			convertView.setTag(viewholder);
		}
		// 绘制联系人名称
		viewholder.name.setText(mContactsName.get(position).toString());
		viewholder.mm.setImageResource(R.drawable.submenu);
		viewholder.add.setText(R.string.listview_add);
		return convertView;
	}

}
