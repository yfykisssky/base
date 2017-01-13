package com.base.push;

import com.base.push.JPUSH.PushCallBack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {

	String methodID;
	PushCallBack pushCallBack;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Bundle bundle=intent.getExtras();
		
		Log.e("msgout",bundle.toString());
		
		if(methodID.equals(bundle.getString("methodID"))){
			PushReceiveModel pushReceiveModel=(PushReceiveModel)bundle.getSerializable("data");
			pushCallBack.onPushReceive(pushReceiveModel.getObject());
		}

	}

	public PushCallBack getPushCallBack() {
		return pushCallBack;
	}
	public void setPushCallBack(PushCallBack pushCallBack) {
		this.pushCallBack = pushCallBack;
	}

	public String getMethodID() {
		return methodID;
	}

	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}
	
}
