package com.base.network;

import org.json.JSONObject;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.NetroidError;
import com.google.gson.Gson;

public class NetroidListener extends Listener<JSONObject>{

	//回调成功，解析成实体对象返回
	@Override
	public void onSuccess(JSONObject json) {
		// TODO Auto-generated method stub
		
		Gson gson=new Gson();
		Object object=gson.fromJson(json.toString(),ResponseBaseModel.class);
		onResponseSuccess(object);
		
	}
	
	
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		super.onCancel();
	}
	
	//回调失败，提取错误信息返回处理
	@Override
	public void onError(NetroidError error) {
		// TODO Auto-generated method stub
		super.onError(error);
		onResponseFailed(error.getMessage());
	}



	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		super.onFinish();
	}



	@Override
	public void onNetworking() {
		// TODO Auto-generated method stub
		super.onNetworking();
	}



	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}



	@Override
	public void onProgressChange(long fileSize, long downloadedSize) {
		// TODO Auto-generated method stub
		super.onProgressChange(fileSize, downloadedSize);
	}



	@Override
	public void onRetry() {
		// TODO Auto-generated method stub
		super.onRetry();
	}



	@Override
	public void onUsedCache() {
		// TODO Auto-generated method stub
		super.onUsedCache();
	}

	public void onResponseSuccess(Object obj) {

	}
	
	public void onResponseFailed(String msg) {

	}

}
