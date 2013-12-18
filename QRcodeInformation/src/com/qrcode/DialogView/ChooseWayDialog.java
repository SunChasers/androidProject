package com.liu.DialogView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.liu.QRCode.R;

/*
 * 我们继承了Dialog的话，我们同样也继承了DialogInterface这个接口的ONclickListner方法
 * 所以我们再给button设置onclicklistner的时候就会出错，因为
 * 本身就是不同包的东西，现在放到一个类里面肯定就会出错！
 */
public class ChooseWayDialog extends Dialog {
	private Context context;
	Button takeaphoto, choose, cancel;

	public ChooseWayDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
	}

	public Button takeaphoto() {
		takeaphoto = (Button) findViewById(R.id.takeaphoto);
		return takeaphoto;
	}

	public Button choose() {
		choose = (Button) findViewById(R.id.choose);

		return choose;
	}

	public void cancel() {
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}
}
