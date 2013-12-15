package com.liu.Adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.QRCode.R;
import com.liu.resource.Resource;

public class ListViewAdapter extends BaseAdapter {

	private Context mcontext;
	private List<Map<String, Object>> listitems;
	private LayoutInflater inflater;// 视图容器

	class ViewHolder {
		ImageView head_imge;
		TextView name_tv;
		ImageView mm;
	}

	public ListViewAdapter(Context mcontext, List<Map<String, Object>> listitems) {// 构造函数，自定义Adapter
		System.out.println("成功创建listView Adapter");
		this.mcontext = mcontext;
		this.listitems = listitems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitems.size();// 系统在运行的过程中需要获取item的属性，所以这个函数一定要做修改
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("listview--getView");
		ViewHolder mViewHolder = null;
		if (convertView != null) {
			mViewHolder = (ViewHolder) convertView.getTag();
		} else {
			mViewHolder = new ViewHolder();
			inflater = (LayoutInflater) mcontext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.qrcode_listitem, null);
			mViewHolder.head_imge = (ImageView) convertView
					.findViewById(R.id.head_image);
			mViewHolder.name_tv = (TextView) convertView
					.findViewById(R.id.name_tv);
			mViewHolder.mm = (ImageView) convertView
					.findViewById(R.id.mm);
			convertView.setTag(mViewHolder);
		}
		Map<String, Object> mData = listitems.get(position);
		mViewHolder.head_imge.setImageBitmap((Bitmap) mData
				.get(Resource.HEADIMAGE));
		mViewHolder.name_tv.setText((String) mData.get(Resource.NAME));
		mViewHolder.mm.setImageResource(R.drawable.submenu);

		return convertView;
	}
}
