package com.base.database;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.content.Context;

//sqlcipher加密数据库
public class DataBaseHelper extends SQLiteOpenHelper {

	final static String DBNAME="user.db";

	final static int version=1;

	public DataBaseHelper(Context context) {
		super(context,DBNAME,null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
