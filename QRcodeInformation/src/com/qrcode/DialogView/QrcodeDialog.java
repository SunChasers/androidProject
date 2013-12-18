package com.liu.DialogView;

import com.liu.QRCode.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class QrcodeDialog extends Dialog{
	private Context context;
    private Button enbtn,sebtn,cancelbtn;
	public QrcodeDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context =context;
	}
	public Button enbtn(){
		enbtn =(Button)findViewById(R.id.enbtn);
		return enbtn;
	}
    public Button sebtn(){
    	sebtn =(Button)findViewById(R.id.sebtn);
    	return sebtn;
    }
    public Button cancelbtn(){
    	cancelbtn =(Button)findViewById(R.id.cancelbtn);
    	cancelbtn.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
    	return cancelbtn;
    }
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
	}

}
