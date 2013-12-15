package com.liu.encoding;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.Adapter.EnlistviewAdapter;
import com.liu.Adapter.MViewHolder;
import com.liu.QRCode.R;
import com.liu.sqlite.BinaryChang;
import com.liu.sqlite.DbOpenHelper;
import com.liu.sqlite.PerinformationDAO;

public class EnlistviewActivity extends Activity {
	private ListView listview;
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enlistviewactivity);
		textview = (TextView) findViewById(R.id.title);
		textview.setText(R.string.choosecontacter);
		setupContentView();
	}

	private void setupContentView() {
		listview = (ListView) findViewById(R.id.enlistview);
		PerinformationDAO perinformation = new PerinformationDAO(
				EnlistviewActivity.this);
		listview.setDividerHeight(0);
		listview.setAdapter(new EnlistviewAdapter(EnlistviewActivity.this,
				perinformation.getname()));
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				DbOpenHelper pi = new DbOpenHelper(EnlistviewActivity.this);
				SQLiteDatabase db;
				db = pi.getWritableDatabase();
				Cursor cursor = db
						.rawQuery(
								"select name ,phone,QQ,weixin,microblog,maillbox,job,addresslistgroup,sex,address,remarks,headimage,qrcodeimage,isqrcodepro from perinformation",
								null);
				MViewHolder viewholder = (MViewHolder) arg1.getTag();
				BinaryChang binary = new BinaryChang();
				while (cursor.moveToNext()) {
					if (viewholder.name.toString().equals(cursor.getString(0))) {
						String str = "";
						str = str + cursor.getString(0) + "#" + cursor.getString(1)
								+ "#" + cursor.getString(2) + "#"
								+ cursor.getString(3) + "#" + cursor.getString(4)
								+ "#" + cursor.getString(5) + "#"
								+ cursor.getString(6) + "#" + cursor.getString(7)
								+ "#" + cursor.getString(8) + "#"
								+ cursor.getString(9) + "#" + cursor.getString(10)
								+ "#" + cursor.getBlob(11).toString() + "#"
								+ cursor.getBlob(12) + "#"
								+ String.valueOf(cursor.getInt(13));
						Intent intent =new Intent();
						intent.putExtra("qrcode", str);
						intent.setClass(EnlistviewActivity.this, ShowQrcodeActivity.class);
						startActivity(intent);
	                   break;
					}
				}
			}
		});
	}
}
