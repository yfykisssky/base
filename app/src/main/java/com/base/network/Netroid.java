package com.base.network;

import java.io.File;

import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.base.costants.HttpRequestData.ActionData;
import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.Network;
import com.duowan.mobile.netroid.RequestQueue;
import com.duowan.mobile.netroid.cache.DiskCache;
import com.duowan.mobile.netroid.request.JsonObjectRequest;
import com.duowan.mobile.netroid.stack.HurlStack;
import com.duowan.mobile.netroid.toolbox.BasicNetwork;
import com.google.gson.Gson;

public class Netroid {

	private static RequestQueue mRequestQueue;
	
	private Netroid() {
	}

	public static void init(Context ctx) {
		if (mRequestQueue != null)
			throw new IllegalStateException("initialized");

		// 创建Netroid主类，指定硬盘缓存方案
		Network network = new BasicNetwork(
				new HurlStack(Const.USER_AGENT, null), HTTP.UTF_8);
		mRequestQueue = new RequestQueue(network, 4, new DiskCache(new File(
				ctx.getCacheDir(), Const.HTTP_DISK_CACHE_DIR_NAME),
				Const.HTTP_DISK_CACHE_SIZE));

		mRequestQueue.start();
	}

	public static void request(ActionData actionData,RequestBaseModel data, Listener<JSONObject> listener) {
		
		int method=actionData.getRequestKind(); 
		String url=actionData.getUrl();
		
		Gson gson=new Gson();
		JSONObject jsonRequest;
		try {
			jsonRequest = new JSONObject(gson.toJson(data));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		mRequestQueue.add(new JsonObjectRequest(method, url, jsonRequest,listener));
	}

	public class Const {
		// http parameters
		public static final int HTTP_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 2MB
		public static final int HTTP_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
		public static final String HTTP_DISK_CACHE_DIR_NAME = "netroid";
		public static final String USER_AGENT = "netroid.cn";
	}

}