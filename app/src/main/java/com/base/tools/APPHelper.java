package com.base.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.view.WindowManager;

public class APPHelper {

	//得到字符串型版本号
	public static String getVersion(Context context) {
		try {
			PackageManager manager =context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
			final String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//得到数字型版本号
	public static int getIntVersion(Context context){

		return Integer.parseInt(getVersion(context).replace(".",""));

	}

	//得到系统的屏幕宽度
	public static int getWindowWidth(Context context){

		return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

	}

	//得到系统的屏幕高度
	public static int getWindowHeight(Context context){

		return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();

	}

	//得到apk的签名字符数组信息
	static Signature[] getSignature(Context context) {
		String pkgname =context.getPackageName();
		/** 通过包管理器获得指定包名包含签名的包信息 **/
		PackageInfo packageInfo=null;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(pkgname, PackageManager.GET_SIGNATURES);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/******* 通过返回的包信息获得签名数组 *******/
		Signature[] signatures = packageInfo.signatures;

		return signatures;

	}

	//得到apk的签名字符串信息
	public static String getSignStr(Context context) {

		Signature[] signatures=getSignature(context);
		/******* 循环遍历签名数组拼接应用签名 *******/
		StringBuilder builder=new StringBuilder();
		for (Signature signature : signatures) {
			builder.append(signature.toCharsString());
		}

		/************** 得到应用签名 **************/
		String signature = builder.toString();
		return signature;
	}

	//得到apk的签名的hash取值
	public static int getSignHash(Context context) {
		
		final String packname = context.getPackageName();
		PackageInfo packageInfo;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(packname, PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			int code = sign.hashCode();
			return code;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
