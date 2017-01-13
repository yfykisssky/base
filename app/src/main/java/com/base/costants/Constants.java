package com.base.costants;

import com.base.ndk.JNICall;

public class Constants {
	
	//初始化提取数据库秘钥
	public static void init(){
		DBKEY=JNICall.getDBkey();
	}
	
	//文件下载地址
	public static final String UPDATE_DOWNLOAD_FILE_PATH="";
	
	//数据库名称
	public static final String DBNAME="";
	
	//数据库秘钥
	public static String DBKEY="";
	
}
