package com.base.commen;

import com.base.push.JPUSH;
import com.base.push.MessageReceiver;
import com.base.push.JPUSH.PushCallBack;
import com.umeng.analytics.MobclickAgent;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityBase extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	//toast显示
	void showToast(String msg){
		Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
	}
	
	//统计
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getName());
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getName());
        MobclickAgent.onPause(this);
    }
	//统计
	
	//PUSH注册和回调
	MessageReceiver mMessageReceiver;
	PushCallBack pushCallBack;
	@Override
	protected void onDestroy() {
		
		//退出时销毁监听器
		if(mMessageReceiver!=null){
			unregisterReceiver(mMessageReceiver);
		}

		super.onDestroy();
	}
	
	//注册监听器
	public void registerPushReceiver(Context mContext,String methodID){
		
		pushCallBack=new PushCallBack() {
			
			@Override
			public void onPushReceive(Object object) {
				// TODO Auto-generated method stub
				onPushMsgReceive(object);
			}
		};
		
		JPUSH.registerPushReceiver(mContext, methodID, pushCallBack);
	}
	
	public void onPushMsgReceive(Object object) {
		// TODO Auto-generated method stub
	}
	//PUSH
}
