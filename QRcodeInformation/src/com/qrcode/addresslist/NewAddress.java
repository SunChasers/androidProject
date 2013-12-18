package com.liu.addresslist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.liu.QRCode.R;

public class NewAddress extends Activity {
	private EditText edit;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editenew);
		edit = (EditText) findViewById(R.id.edit);
		tv = (TextView) findViewById(R.id.title);
		tv.setText(R.string.address);

	}
}
