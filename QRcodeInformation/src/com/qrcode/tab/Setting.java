package com.liu.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.liu.QRCode.R;

public class Setting extends Activity{
	TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		textview =(TextView)findViewById(R.id.title);
		textview.setText(R.string.setting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
