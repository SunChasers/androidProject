package com.liu.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.DialogView.QrcodeDialog;
import com.liu.QRCode.R;
import com.liu.addresslist.Addnewcontacter;
import com.liu.encoding.EnlistviewActivity;
import com.liu.zxing.FirstActivity;

public class Function extends Activity {
    TextView textview;
    RelativeLayout rll_addcontacter,rll_sweep,rll_qrcode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
		textview =(TextView)findViewById(R.id.title);
		textview.setText(R.string.function);
		sweep();
		qrcode();
		addcontacter();
	}
	/**
	 * 添加联系人
	 */
	private void addcontacter(){
		rll_addcontacter =(RelativeLayout)findViewById(R.id.rll_addcontacter);
		rll_addcontacter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(Function.this,Addnewcontacter.class);
				startActivity(intent);
				Toast t = Toast.makeText(Function.this,
						 R.string.tt, Toast.LENGTH_LONG);
						 t.show();
			}
		});
	}
   /**
    * 扫一扫
    */
	private void sweep(){
		rll_sweep =(RelativeLayout)findViewById(R.id.rll_sweep);
		rll_sweep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setClass(Function.this,FirstActivity.class);
				startActivity(intent);
			}
		});
	}
	/**
	 * 二维码
	 */
	private void qrcode(){
		rll_qrcode =(RelativeLayout)findViewById(R.id.rll_qrcode);
		final QrcodeDialog qrcodedialog =new QrcodeDialog(Function.this);	
		rll_qrcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				qrcodedialog.setContentView(R.layout.qrcodefunction);
				qrcodedialog.show();
				qrcodedialog.enbtn().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent =new Intent();
						intent.setClass(Function.this,EnlistviewActivity.class);
						startActivity(intent);
					}
				});
				qrcodedialog.sebtn().setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
				});
				qrcodedialog.cancelbtn();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
