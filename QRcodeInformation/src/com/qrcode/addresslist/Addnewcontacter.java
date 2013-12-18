package com.liu.addresslist;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.DialogView.ChooseWayDialog;
import com.liu.QRCode.R;
import com.liu.resource.Resource;
import com.liu.sqlite.BinaryChang;
import com.liu.sqlite.Perinformation;
import com.liu.sqlite.PerinformationDAO;

public class Addnewcontacter extends Activity {
	private static final int RESULT_LOAD_IMAGE = 1;
	private static final int TAKEPICTRUE = 2;

	private TextView textview_tital;
	private Button savebtn;
	private ImageView newhead_img;// 获取图片按钮
	private RelativeLayout rll_head, rll_name, rll_phone, rll_QQ, rll_weixin,
			rll_microblog, rll_mail, rll_job, rll_addresslistgroup, rll_sex, rll_address,
			rll_remarks;
	Dialog mNameDlg, mPhoneDlg;
	private TextView newname_tv, newphone_tv, newQQ_tv, newweixin_tv,
			newmicroblog_tv, newmaillbox_tv, newjob_tv, newaddresslistgroup_tv, newsex_tv,
			newaddress_tv, newremarks_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_contacter);
		textview_tital = (TextView) findViewById(R.id.title);
		textview_tital.setText(R.string.createcontacter);
		findId();
		setname();
		setphone();
		sethead();
		setsavebtn();
		setQQ();
		setweixin();
		setmicroblog();
		setmaill();
		setjob();
		setgroup();
		setsex();
		setaddress();
		setremarks();
	}

	/*
	 * 视图Id
	 */
	private void findId() {
		newhead_img = (ImageView) findViewById(R.id.newhead_img);
		savebtn = (Button) findViewById(R.id.savebtn);
		rll_head = (RelativeLayout) findViewById(R.id.rll_head);
		rll_name = (RelativeLayout) findViewById(R.id.rll_name);
		rll_phone = (RelativeLayout) findViewById(R.id.rll_phone);
		rll_QQ = (RelativeLayout) findViewById(R.id.rll_QQ);
		rll_weixin = (RelativeLayout) findViewById(R.id.rll_weixin);
		rll_microblog = (RelativeLayout) findViewById(R.id.rll_microblog);
		rll_mail = (RelativeLayout) findViewById(R.id.rll_mail);
		rll_job = (RelativeLayout) findViewById(R.id.rll_job);
		rll_addresslistgroup = (RelativeLayout) findViewById(R.id.rll_addresslistgroup);
		rll_sex = (RelativeLayout) findViewById(R.id.rll_sex);
		rll_address = (RelativeLayout) findViewById(R.id.rll_address);
		rll_remarks = (RelativeLayout) findViewById(R.id.rll_remarks);

		newname_tv = (TextView) findViewById(R.id.newname_tv);
		newphone_tv = (TextView) findViewById(R.id.newphone_tv);
		newQQ_tv = (TextView) findViewById(R.id.newQQ_tv);
		newweixin_tv = (TextView) findViewById(R.id.newweixin_tv);
		newmicroblog_tv = (TextView) findViewById(R.id.newmicroblog_tv);
		newmaillbox_tv = (TextView) findViewById(R.id.newmail_tv);
		newjob_tv = (TextView) findViewById(R.id.newjob_tv);
		newaddresslistgroup_tv = (TextView) findViewById(R.id.newaddresslistgroup_tv);
		newsex_tv = (TextView) findViewById(R.id.newsex_tv);
		newaddress_tv = (TextView) findViewById(R.id.newaddress_tv);
		newremarks_tv = (TextView) findViewById(R.id.newremarks_tv);

	}

	/**
	 * 头像
	 */
	private void sethead() {

		rll_head.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ChooseWayDialog chooseway = new ChooseWayDialog(
						Addnewcontacter.this);
				chooseway.setContentView(R.layout.bitmapway);
				chooseway.show();
				chooseway.choose().setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Intent i = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(i, RESULT_LOAD_IMAGE);
						chooseway.dismiss();
					}
				});
				chooseway.takeaphoto().setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								startActivityForResult(intent,
										TAKEPICTRUE);
								chooseway.dismiss();
							}
						});
				chooseway.cancel();

			}
		});
	}

	/**
	 * 如果requestCode>1将系统调用该函数
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);// 调用超类
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();// 获取被选中的图像资源
			String[] ImageFilePath = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					ImageFilePath, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(ImageFilePath[0]);
			String picturepath = cursor.getString(columnIndex);
			cursor.close();

			newhead_img.setImageBitmap(BitmapFactory.decodeFile(picturepath));
		}
		if(requestCode == TAKEPICTRUE){
			if(resultCode == RESULT_OK){
				Bitmap pictrue =(Bitmap)data.getExtras().get("data");
				newhead_img.setImageBitmap(pictrue);
			}
		}
	}

	/**
	 * 姓名
	 */
	private void setname() {

		rll_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("myname", newname_tv.getText().toString());
				intent.setClass(Addnewcontacter.this, NewName.class);
				startActivity(intent);

			}
		});

	}

	/**
	 * 电话
	 */
	private void setphone() {
		rll_phone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("myphone", newphone_tv.getText().toString());
				intent.setClass(Addnewcontacter.this, NewPhone.class);
				startActivity(intent);
			}
		});

	}

	/*
	 * QQ
	 */
	private void setQQ() {
		rll_QQ.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("myQQ", newQQ_tv.getText().toString());
				intent.setClass(Addnewcontacter.this, NewQQAccount.class);
				startActivity(intent);
			}
		});
	}

	/*
	 * 微信
	 */
	private void setweixin() {
		rll_weixin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("mywexin", newweixin_tv.getText().toString());
				intent.setClass(Addnewcontacter.this, NewWeixin.class);
				startActivity(intent);
			}
		});

	}

	/*
	 * 微博
	 */
	private void setmicroblog() {
		rll_microblog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("mymicroblog", newmicroblog_tv.getText()
						.toString());
				intent.setClass(Addnewcontacter.this, NewMicroblog.class);
				startActivity(intent);
			}
		});

	}

	/*
	 * 邮箱
	 */
	private void setmaill() {
		rll_mail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("mymaill", newmaillbox_tv.getText().toString());
				intent.setClass(Addnewcontacter.this, NewMailbox.class);
				startActivity(intent);
			}
		});

	}

	/*
	 * 职业
	 */
	private void setjob() {

	}

	/*
	 * 通讯录组
	 */
	private void setgroup() {

	}

	/*
	 * 性别
	 */
	private void setsex() {

	}

	/*
	 * 通讯录地址
	 */
	private void setaddress() {

	}

	/*
	 * 备注
	 */
	private void setremarks() {
	}

	/**
	 * 保存
	 */
	 private void setsavebtn() {
	 savebtn.setOnClickListener(new OnClickListener() {
	
	 @Override
	 public void onClick(View v) {
	 // TODO Auto-generated method stub
	 PerinformationDAO perinformationdao = new PerinformationDAO(
	 Addnewcontacter.this);
	
	 BinaryChang binary = new BinaryChang();// 图片与二进制转换类
	 Bitmap bitmap = ((BitmapDrawable) newhead_img.getDrawable())
	 .getBitmap();
	 byte[] bhead = binary.imageToBinary(bitmap);
	 ContentValues values = new ContentValues();
	 values.put(Resource.NAME, newname_tv.getText().toString());
	 values.put(Resource.PHONE, newphone_tv.getText().toString());
	 values.put(Resource.WEIXIN, newweixin_tv.getText().toString());
	 values.put(Resource.MICROBLOG, newmicroblog_tv.getText().toString());
	 values.put(Resource.MAILLBOX, newmaillbox_tv.getText().toString());
	 values.put(Resource.JOB, newjob_tv.getText().toString());
	 values.put(Resource.ADDRESSLISTGROUP, newaddresslistgroup_tv.getText().toString());
	 values.put(Resource.SEX, newsex_tv.getText().toString());
	 values.put(Resource.ADDRESS, newaddress_tv.getText().toString());
	 values.put(Resource.REMARKS, newremarks_tv.getText().toString());
	 values.put(Resource.HEADIMAGE, bhead);
	 perinformationdao.add(values);
	 Toast t = Toast.makeText(Addnewcontacter.this,
	 R.string.savesuccess, Toast.LENGTH_LONG);
	 t.show();
	 }
	 });
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

}
