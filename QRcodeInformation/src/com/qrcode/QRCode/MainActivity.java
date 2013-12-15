package com.liu.QRCode;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.liu.localaddresslist.AddLocalContacterToDb;
import com.liu.sqlite.DbOpenHelper;
import com.liu.tab.AddressList;
import com.liu.tab.Function;
import com.liu.tab.QRCode;
import com.liu.tab.Setting;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup implements OnClickListener {
	/***
	 * �˳�Ӧ�õĹ㲥��ʶ
	 */
	public static final String ACTION_EXIT = "cn.itcast.weixin.exit";

	LinearLayout tabBar;
	ImageView bottomImage;
	ViewPager pager;
	/**
	 * ���յ�ACTION_EXIT�Ĺ㲥���˳�activity
	 */
	BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��������̲�Ĭ�ϵ���
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.main);
		createdb();
		/*
		 * 
		 * /** ���ײ��������󶨼�����
		 */
		LinearLayout tabBar = (LinearLayout) findViewById(R.id.tab_bar);// �ò����ϴ����ĸ�text�ؼ�
		int count = tabBar.getChildCount();// ��ȡ�ò����Ͽؼ��ĸ���
		for (int index = 0; index < count; index++) {
			View view = tabBar.getChildAt(index);
			view.setTag(index);
			view.setOnClickListener(this);
			view.setEnabled(index != 0);
		}
		this.tabBar = tabBar;

		/**
		 * �޸ĵײ���ɫImageView�Ŀ��
		 */
		ImageView bottomImage = (ImageView) findViewById(R.id.bottom_image);
		LayoutParams params = (LayoutParams) bottomImage.getLayoutParams();
		params.width = getWindowManager().getDefaultDisplay().getWidth()
				/ count;
		bottomImage.setLayoutParams(params);
		this.bottomImage = bottomImage;

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new MainAdapter(this));
		pager.setOnPageChangeListener(new PageListener());
		this.pager = pager;

		/**
		 * ע��㲥
		 */
		registerReceiver(receiver, new IntentFilter(ACTION_EXIT));
	}

	protected void onDestroy() {
		super.onDestroy();
		/**
		 * ȡ��ע��
		 */
		unregisterReceiver(receiver);
	}

	private class PageListener implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		int currentItem;

		public void onPageSelected(int position) {
			// TODO �ײ���ɫImageView��ƽ�ƶ��� currentItem->position
			TranslateAnimation animation = new TranslateAnimation(
					TranslateAnimation.RELATIVE_TO_SELF, currentItem,
					TranslateAnimation.RELATIVE_TO_SELF, position,
					TranslateAnimation.ABSOLUTE, 0,
					TranslateAnimation.ABSOLUTE, 0);
			animation.setFillAfter(true);
			animation.setDuration(100);
			bottomImage.startAnimation(animation);

			LinearLayout tabBar = MainActivity.this.tabBar;

			// �õ�ǰ��TextView���ɫ
			tabBar.getChildAt(currentItem).setEnabled(true);

			// ���µ�TextView����ɫ
			tabBar.getChildAt(position).setEnabled(false);

			currentItem = position;
		}
	}

	/**
	 * ΪViewPager����Adapter��PagerAdapter
	 * 
	 * @author ������
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private class MainAdapter extends PagerAdapter {
		Class[] cs = { QRCode.class, AddressList.class, Function.class,
				Setting.class };
		ArrayList<View> activityViews = new ArrayList<View>();

		public MainAdapter(Context context) {
			int length = cs.length;
			for (int i = 0; i < length; i++) {
				Class c = cs[i];
				// TODO �õ�activity��Ӧ��View
				View view = getLocalActivityManager().startActivity(i + "",
						new Intent(context, c)).getDecorView();
				activityViews.add(view);
			}
		}

		public int getCount() {
			return cs.length;
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		public Object instantiateItem(ViewGroup container, int position) {
			View view = activityViews.get(position);
			container.addView(view);
			return view;
		}
	}

	/**
	 * �����ײ�TabBar�ĵ��
	 */
	public void onClick(View v) {
		int index = (Integer) v.getTag();
		// �ᴥ��OnPageChangeListener��onPageSelected(index)
		pager.setCurrentItem(index);
	}

	/**
	 * ActivityGroup�����������
	 */
	static final int DIALOG_EXIT_ASK = 1;

	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		int action = event.getAction();

		if (keyCode == KeyEvent.KEYCODE_BACK && action == KeyEvent.ACTION_UP) {
			showDialog(DIALOG_EXIT_ASK);
			return true; // ���Ǹ����Ĭ����Ϊ
		}
		return super.dispatchKeyEvent(event);
	}

	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_EXIT_ASK) {
			final Dialog dialog = new Dialog(this, R.style.ExitDialog);
			dialog.setContentView(R.layout.exit_dialog);

			View.OnClickListener listener = new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();

					if (v.getId() == R.id.exit) {
						finish();
					}
				}
			};

			dialog.findViewById(R.id.exit).setOnClickListener(listener);
			dialog.findViewById(R.id.cancel).setOnClickListener(listener);
			return dialog;
		}
		return super.onCreateDialog(id);
	}

	private void createdb() {
		AddLocalContacterToDb addlocalcontactertodb = new AddLocalContacterToDb(
				MainActivity.this);

	}
}
