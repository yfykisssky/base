package com.base.database;

import java.util.concurrent.atomic.AtomicInteger;

import com.base.costants.Constants;

import net.sqlcipher.database.SQLiteDatabase;

public class DatabaseManager {
	private AtomicInteger mOpenCounter = new AtomicInteger();

	private static DatabaseManager instance;
	private static DataBaseHelper mDatabaseHelper;
	private SQLiteDatabase mDatabase;
	
	private static String DBKEY;

	//初始化数据库
	public static synchronized void initializeInstance(DataBaseHelper helper,String DBKEY) {
		if (instance == null) {
			DatabaseManager.DBKEY=DBKEY;//加解密秘钥
			instance = new DatabaseManager();
			mDatabaseHelper = helper;
		}
		
	}

	//获取数据库实例
	public static synchronized DatabaseManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
					" is not initialized, call initializeInstance(..) method first.");
		}

		return instance;
	}

	//打开数据库
	public synchronized SQLiteDatabase openDatabase() {
		if(mOpenCounter.incrementAndGet() == 1) {
			// Opening new database
			mDatabase = mDatabaseHelper.getWritableDatabase(DBKEY);
		}
		return mDatabase;
	}

	//关闭数据库
	public synchronized void closeDatabase() {
		if(mOpenCounter.decrementAndGet() == 0) {
			// Closing database
			mDatabase.close();

		}
	}
}

