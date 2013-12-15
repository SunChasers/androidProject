package com.liu.Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.liu.QRCode.R;
import com.liu.resource.Resource;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
	

	// �ڲ���
	private class ViewHolder {
		ImageView mImage;
		TextView mText;
	}

	Context mContext;
	ViewHolder mViewHolder, mGroupViewHolder;
	List<String> mGroupTitle = new ArrayList<String>();
	List<List<HashMap<String, Object>>> mChild = new ArrayList<List<HashMap<String, Object>>>();

	public ExpandableListViewAdapter(Context context, List<String> groupTitle,
			List<List<HashMap<String, Object>>> child) {
		this.mContext = context;
		this.mGroupTitle = groupTitle;
		this.mChild = child;
		System.out.println("�Ѵ���adpter");
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mChild.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("getChildView");
		if (convertView != null) {

			mViewHolder = (ViewHolder) convertView.getTag();// getTag()����������ͼ�ı�ǩ
		} else {
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = mInflater.inflate(R.layout.child, null);// ��ȡ����ͼ�Ĳ���
			mViewHolder = new ViewHolder();
			// Ϊ����ͼ�е�TextView��ImageView�ؼ�
			mViewHolder.mImage = (ImageView) convertView
					.findViewById(R.id.child_img_item);
			mViewHolder.mText = (TextView) convertView
					.findViewById(R.id.child_textview_item);
			convertView.setTag(mViewHolder);
		}
		// ���ÿؼ�����
		HashMap<String, Object> mData = mChild.get(groupPosition).get(
				childPosition);
		mViewHolder.mImage.setImageResource((Integer) mData.get(Resource.IMAGE));
		mViewHolder.mText.setText((String) mData.get(Resource.TEXT));

		return convertView;// ������ͼ
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mChild.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mGroupTitle.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroupTitle.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("getGroupView");
		if (convertView != null) {
			mGroupViewHolder = (ViewHolder) convertView.getTag();// ������ͼ��ǩ
		} else {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.group, null);// ��ȡ����ͼ����
			mGroupViewHolder = new ViewHolder();
			mGroupViewHolder.mText = (TextView) convertView
					.findViewById(R.id.group_textview_item);
			convertView.setTag(mGroupViewHolder);
		}
		String mData = mGroupTitle.get(groupPosition);
		mGroupViewHolder.mText.setText(mData);

		return convertView;
		// String string = mGroupTitle.get(groupPosition);
		// return getGenericView(string);

	}

	/*
	 * ��̬��Ӳ��ֺͿؼ�
	 */
	private TextView getGenericView(String string) {
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView textView = new TextView(mContext);// �Զ���TextView�ؼ�
		textView.setLayoutParams(layoutParams);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setPadding(60, 10, 0, 10);
		textView.setTextSize(20);

		textView.setText(string);

		return textView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
