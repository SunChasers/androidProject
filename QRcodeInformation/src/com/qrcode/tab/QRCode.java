package com.liu.tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.Adapter.ListViewAdapter;
import com.liu.QRCode.R;
import com.liu.resource.Resource;
import com.liu.sqlite.BinaryChang;
import com.liu.sqlite.DbOpenHelper;
import com.liu.sqlite.Perinformation;
import com.liu.sqlite.PerinformationDAO;

public class QRCode extends Activity {
	private DbOpenHelper pi;
	private ListView listview = null;
	private TextView textview;
	private ImageButton tital_imgbtn;
	private PerinformationDAO perinformationdao;
	private BinaryChang bc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode);

		textview = (TextView) findViewById(R.id.title);
		textview.setText(R.string.QRCode);
		perinformationdao = new PerinformationDAO(QRCode.this);
		createdb();
		scandb();
		findId();
		bc = new BinaryChang();
	}

	private void findId() {
		listview = (ListView) findViewById(R.id.listview);
		tital_imgbtn = (ImageButton) findViewById(R.id.tital_imgbtn);
	}

	// 扫描数据库，添加item
	private void scandb() {
		int count = 0;
		List<Map<String, Object>> listitems = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Perinformation> list = perinformationdao.getScolldata();
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).getIsqrcodepro() == 1) {
				count++;
				map.put(Resource.HEADIMAGE,
						bc.binaryToImage(list.get(index).getQrcodeimage()));
				map.put(Resource.NAME, list.get(index).getName());
				listitems.add(map);
			}
		}
		if (count == 0) {
			Toast t = Toast.makeText(QRCode.this, R.string.noqrcode,
					Toast.LENGTH_LONG);
			t.show();
		}
		else{
		listview.setAdapter(new ListViewAdapter(this, listitems));
		}
	}

	// 创建数据库
	private void createdb() {
		pi = new DbOpenHelper(QRCode.this);
		SQLiteDatabase db = pi.getWritableDatabase();
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
