package com.liu.zxing;



import com.liu.QRCode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class FirstActivity extends Activity {
	private Animation myAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initScreen();
		setContentView(R.layout.proing);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(FirstActivity.this,
						CaptureActivity.class);
				startActivity(intent);
				finish();
			}
		}, 3000);

		myAnimation = AnimationUtils.loadAnimation(FirstActivity.this,
				R.anim.aa);
		findViewById(R.id.line).startAnimation(myAnimation);
	}

	private void initScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
