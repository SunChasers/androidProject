package com.liu.Adapter;

import java.util.ArrayList;

import com.liu.QRCode.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AddresslistListVewAdepter extends BaseAdapter {
	/** 联系人名称 **/
	private ArrayList<String> mContactsName = new ArrayList<String>();
	/** 联系人头像 **/
	private ArrayList<String> mContactsNumber = new ArrayList<String>();
	/** 联系人头像 **/
	private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();
	private Context context;

	public AddresslistListVewAdepter(Context context,
			ArrayList<String> mContactsName, ArrayList<String> mContactsNumber,
			ArrayList<Bitmap> mContactsPhonto) {
		this.context = context;
		this.mContactsName = mContactsName;
		this.mContactsNumber = mContactsNumber;
		this.mContactsPhonto = mContactsPhonto;
	}

	class mViewHolder {
		ImageView imagehead;
		TextView name;
		TextView phone;
		ImageView imagemm;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContactsPhonto.size();
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
		mViewHolder viewholder = new mViewHolder();
		if (convertView == null || position < mContactsNumber.size()) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.addresslist_listitem, null);
			viewholder.imagehead = (ImageView) convertView
					.findViewById(R.id.addresslisthead);
			viewholder.name = (TextView) convertView
					.findViewById(R.id.addresslistname);
			viewholder.phone = (TextView) convertView
					.findViewById(R.id.addresslistphone);
			viewholder.imagemm = (ImageView) convertView
					.findViewById(R.id.addresslistmm);
		}
		// 绘制联系人名称
		viewholder.name.setText(mContactsName.get(position));
		// 绘制联系人号码
		viewholder.phone.setText(mContactsNumber.get(position));
		// 绘制联系人头像
		viewholder.imagehead.setImageBitmap(mContactsPhonto.get(position));
		viewholder.imagemm.setImageResource(R.drawable.submenu);
		return convertView;

	}

}
