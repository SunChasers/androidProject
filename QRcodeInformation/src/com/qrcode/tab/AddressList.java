package com.liu.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.Adapter.AddresslistListVewAdepter;
import com.liu.QRCode.R;
import com.liu.addresslist.Addnewcontacter;
import com.liu.localaddresslist.LocalAddresslist;
import com.liu.sqlite.PerinformationDAO;

public class AddressList extends Activity {
	public static final String IMAGE = "image";
	public static final String TEXT = "text";
	private ListView listview;
	private ImageButton add_imagebutton;
	TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addresslist);
		textview = (TextView) findViewById(R.id.title);
		textview.setText(R.string.addresslist);
		setupViewCompantent();
		setclicklistner();
	}

	private void setupViewCompantent() {
		PerinformationDAO perinformation = new PerinformationDAO(
				AddressList.this);
		listview = (ListView) findViewById(R.id.listview);
		listview.setDividerHeight(0);
		listview.setAdapter(new AddresslistListVewAdepter(AddressList.this,
				perinformation.getname(), perinformation.getphone(),
				perinformation.getphoto()));
		System.out.println("fasdfsdfasdfasdf");

	}

	private void setclicklistner() {
		// 为添加联系人设置监听器
		{
			add_imagebutton = (ImageButton) findViewById(R.id.add_imgebutton);
			add_imagebutton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(AddressList.this, Addnewcontacter.class);
					AddressList.this.startActivity(intent);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
