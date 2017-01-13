package com.base.ndk;

import android.content.Context;
//jni调用
public class JNICall {

	static Context context;
	
	//加载so包
	public static void initJNI(Context context){
		System.loadLibrary("base");
		JNICall.context=context;
	}
	
	//调用函数
	native static String getDatabaseKey(Context context);
	native static String getDataKey(Context context);
	
	public static String getDBkey(){
		return getDatabaseKey(context);
	}
	
	public static String getKey(){
		return getDataKey(context);
	}

}
