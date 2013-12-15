package com.liu.encoding;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.liu.QRCode.R;

public class ShowQrcodeActivity extends Activity {
	private ImageView qrcodeimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showqrcode);
		qrcodeimg = (ImageView) findViewById(R.id.showqrcodeimg);
		Bundle extras = getIntent().getExtras();
		String str = extras.getString("qrcode");
		Encode encode = new Encode(str);
		qrcodeimg.setImageBitmap(encode.createImage());
	}

}
