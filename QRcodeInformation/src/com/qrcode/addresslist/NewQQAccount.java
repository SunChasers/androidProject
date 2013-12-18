package com.liu.addresslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liu.QRCode.R;

public class NewQQAccount extends Activity{
	private EditText edit;
	private TextView tv;
	private Button savebtn,backbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editenew);
		edit =(EditText)findViewById(R.id.edit);
		tv =(TextView)findViewById(R.id.title);
		tv.setText(R.string.QQ);
		savebtn =(Button)findViewById(R.id.save_btn);
		backbtn =(Button)findViewById(R.id.back_btn);
		Intent intent =getIntent();//获取传回来的Intent对象
		String str =intent.getStringExtra("myQQ");
		edit.setText(str);
		savebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =new Intent();
				i.putExtra("myQQ", edit.getText().toString());
				i.setClass(NewQQAccount.this,Addnewcontacter.class);
				startActivity(i);
			}
		});
		backbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =new Intent();
				i.setClass(NewQQAccount.this,Addnewcontacter.class);
				startActivity(i);
			}
		});
	}
}
