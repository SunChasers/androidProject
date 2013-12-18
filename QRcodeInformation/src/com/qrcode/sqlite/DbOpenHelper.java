package com.liu.sqlite;

import com.liu.resource.Resource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		System.out.println("2");
		// TODO Auto-generated constructor stub
	}

	public DbOpenHelper(Context context) {
		this(context, Resource.DBNAME, null, Resource.VERSION);
		System.out.println("1");
	}

	public String getdbname() {
		return Resource.DBNAME;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("成功创建数据库，表");
		db.execSQL("CREATE TABLE perinformation (ID integer NOT NULL PRIMARY KEY,name varchar(20) NOT NULL,phone varchar(30) NOT NULL,QQ varchar(30),weixin varchar(50),microblog varchar(50),maillbox varchar(50),job varchar(20),addresslistgroup varchar(20),sex varchar(10),address varchar(50),remarks varchar(50),headimage blob,qrcodeimage blob,isqrcodepro int)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
