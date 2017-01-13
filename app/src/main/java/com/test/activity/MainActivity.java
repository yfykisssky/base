package com.test.activity;

import android.os.Bundle;
import com.base.base.R;
import com.base.commen.ActivityBase;
import com.base.push.JPUSH;


public class MainActivity extends ActivityBase {

	@Override
	public void onPushMsgReceive(Object object) {
		// TODO Auto-generated method stub
		super.onPushMsgReceive(object);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		JPUSH.registerPushReceiver(this,"",null);
	}

}
