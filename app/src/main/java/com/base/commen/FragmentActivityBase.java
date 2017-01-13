package com.base.commen;

import com.base.push.JPUSH;
import com.base.push.MessageReceiver;
import com.base.push.JPUSH.PushCallBack;
import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class FragmentActivityBase extends FragmentActivity{
	
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
	
	//PUSH
	MessageReceiver mMessageReceiver;
	PushCallBack pushCallBack;
	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}
	
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
