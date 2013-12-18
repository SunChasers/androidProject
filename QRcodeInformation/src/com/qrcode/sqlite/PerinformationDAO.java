package com.liu.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

public class PerinformationDAO {
	private DbOpenHelper pi;
	private SQLiteDatabase db;
	private Context context;
	/** 联系人名称 **/
	private ArrayList<String> mContactsName = new ArrayList<String>();
	/** 联系人号码 **/
	private ArrayList<String> mContactsNumber = new ArrayList<String>();
	/** 联系人头像 **/
	private ArrayList<Bitmap> mContactsPhoto = new ArrayList<Bitmap>();
	public PerinformationDAO(Context context) {
		this.context = context;
		pi = new DbOpenHelper(this.context);
	}

	public void add(ContentValues values) {
		System.out.println("gsdgsdfg");
		db = pi.getWritableDatabase();// 如果数据库不存在，就创建指定文件名的数据库，如果存在就获取可读可写的数据库对象
		db.insert("perinformation", null, values);
		db.close();
	}

	public List<Perinformation> getScolldata() {
		db = pi.getWritableDatabase();

		List<Perinformation> list = new ArrayList<Perinformation>();
		Cursor cursor = db
				.rawQuery(
						"select name,qrcodeimage,isqrcodepro from perinformation",
						null);
		while (cursor.moveToNext()) {
			list.add(new Perinformation(cursor.getString(0), cursor.getBlob(1),
					cursor.getInt(2)));
		}
		db.close();
		return list;
	}
	public ArrayList<String> getname(){
		db = pi.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select name from perinformation",
						null);
		while (cursor.moveToNext()) {
			mContactsName.add(cursor.getString(0));
		}
		db.close();
		return mContactsName;
	}
	public ArrayList<String> getphone(){
		db = pi.getWritableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select phone from perinformation",
						null);
		while (cursor.moveToNext()) {
			mContactsNumber.add(cursor.getString(0));
		}
		db.close();
		return mContactsNumber;
	}
	public ArrayList<Bitmap> getphoto(){
		db = pi.getWritableDatabase();
		BinaryChang binary =new BinaryChang();
		Cursor cursor = db
				.rawQuery(
						"select headimage from perinformation",
						null);
		while (cursor.moveToNext()) {
			mContactsPhoto.add(binary.binaryToImage(cursor.getBlob(0)));
		}
		db.close();
		return mContactsPhoto;
	}
}
