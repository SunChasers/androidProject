package com.liu.DialogView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.liu.QRCode.R;

/*
 * ���Ǽ̳���Dialog�Ļ�������ͬ��Ҳ�̳���DialogInterface����ӿڵ�ONclickListner����
 * ���������ٸ�button����onclicklistner��ʱ��ͻ������Ϊ
 * ������ǲ�ͬ���Ķ��������ڷŵ�һ��������϶��ͻ����
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
