package com.base.push;

import android.content.Context;
import android.content.IntentFilter;
import cn.jpush.android.api.JPushInterface;

public class JPUSH {

	static Context mContext;
	
	public static void init(Context context,boolean debug){
        JPushInterface.setDebugMode(debug); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(context);     		// 初始化 JPush
	}
	
	//for receive customer msg from jpush server
	private static MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
/*	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";*/
	
	//jpush广播监听器
	public static void registerPushReceiver(Context mContext,String methodID,PushCallBack pushCallBack) {
		mMessageReceiver = new MessageReceiver();
		mMessageReceiver.setMethodID(methodID);
		mMessageReceiver.setPushCallBack(pushCallBack);
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		mContext.registerReceiver(mMessageReceiver, filter);
	}
	
	//参数回调函数
	public interface PushCallBack{
		void onPushReceive(Object object);
	}
}
