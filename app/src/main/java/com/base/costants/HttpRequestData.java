package com.base.costants;

import com.duowan.mobile.netroid.Request.Method;
//网络请求配置文件
public class HttpRequestData {
	
	//网络请求服务器地址
	static final String SERVER_URL="";
	
	//请求类型
	static final int POST=Method.POST;
	static final int GET=Method.GET;
	
	//接口版本号
	static final String VERSION10="V1.0";
	static final String VERSION11="V1.1";


	public static final ActionData LOGIN=new ActionData("",POST,VERSION10,false);

	//接口实体
	public static class ActionData{
		String url;//请求action
		int requestKind;//请求类型
		String version;//版本号
		boolean needCrypt;//是否需要加密

		ActionData(	String url,
				int requestKind,
				String version,
				boolean needCrypt){
			this.url=url;
			this.requestKind=requestKind;
			this.version=version;
			this.needCrypt=needCrypt;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getRequestKind() {
			return requestKind;
		}

		public void setRequestKind(int requestKind) {
			this.requestKind = requestKind;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public boolean isNeedCrypt() {
			return needCrypt;
		}

		public void setNeedCrypt(boolean needCrypt) {
			this.needCrypt = needCrypt;
		}


	}
}
