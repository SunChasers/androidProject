package com.liu.localaddresslist;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.liu.resource.Resource;
import com.liu.sqlite.BinaryChang;
import com.liu.sqlite.DbOpenHelper;
import com.liu.sqlite.PerinformationDAO;

public class AddLocalContacterToDb {
	private DbOpenHelper pi;
	private SQLiteDatabase db;
	private Context context;
	private LocalAddresslist lal;
	/** 联系人名称 **/
	private ArrayList<String> mContactsName = new ArrayList<String>();
	/** 联系人号码 **/
	private ArrayList<String> mContactsNumber = new ArrayList<String>();
	/** 联系人头像 **/
	private ArrayList<Bitmap> mContactsPhoto = new ArrayList<Bitmap>();

	public AddLocalContacterToDb(Context context) {
		this.context = context;
		pi = new DbOpenHelper(context);
		db = pi.getWritableDatabase();
		lal = new LocalAddresslist(context);
		lal.getPhoneContacts();
		PerinformationDAO perinformationdao = new PerinformationDAO(context);
		mContactsName = lal.getmContactsName();
		mContactsNumber = lal.getmContactsNumber();
		mContactsPhoto = lal.getmContactsPhoto();
		Cursor cursor1 = db.rawQuery("select phone from perinformation", null);
		BinaryChang binary = new BinaryChang();// 图片与二进制转换类
		int t = 0;
		Cursor cursor2;
		for (int index = 0; index < mContactsNumber.size(); index++) {
			t=0;
			cursor2 = cursor1;
			while (cursor2.moveToNext()) {
				if (cursor2.getString(0).equals(mContactsNumber.get(index)))
					{t = 1;break;}
			}
			if (t==0) {
				ContentValues values = new ContentValues();
				byte[] bhead = binary.imageToBinary(mContactsPhoto.get(index));
				values.put(Resource.NAME, mContactsName.get(index));
				values.put(Resource.PHONE, mContactsNumber.get(index));
				values.put(Resource.HEADIMAGE, bhead);
				perinformationdao.add(values);
			}
		}

	}
}
