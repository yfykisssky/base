package com.base.commen;

import android.app.Application;

import com.base.costants.Constants;
import com.base.count.YMCount;
import com.base.database.DataBaseHelper;
import com.base.database.DatabaseManager;
import com.base.ndk.JNICall;
import com.base.network.Netroid;
import com.base.push.JPUSH;
import com.base.tools.ImageLoad;

public class ApplicationBase extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();


		initModel();
	}

	void initModel(){
		//网络初始化
		Netroid.init(this);  
		//jni模块初始化
		JNICall.initJNI(this);
		//推送初始化
		JPUSH.init(this,true);
		//友盟统计初始化
		YMCount.init();
		//常量初始化
		Constants.init();
		//数据库初始化
		DatabaseManager.initializeInstance(new DataBaseHelper(this),Constants.DBKEY);
		//imageloader初始化
		ImageLoad.init(this);
	}

}
