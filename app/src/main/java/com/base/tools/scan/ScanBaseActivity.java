package com.base.tools.scan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.base.commen.ActivityBase;

public class ScanBaseActivity extends ActivityBase{

	private final static int SCANNIN_GREQUEST_CODE = 1;  
	
	void getScanResult(String msg,Bitmap bitmap){
		
	}
	
	void startToScan(){
		
	    //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转  
	    //扫描完了之后调到该界面  
		 Intent intent = new Intent();  
         intent.setClass(this,CodeCaptureActivity.class);  
         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
         startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
         
	}
	    
    //返回扫描结果并传递给回调参数
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    super.onActivityResult(requestCode, resultCode, data);  
	    switch (requestCode) {  
	    case SCANNIN_GREQUEST_CODE:  
	        if(resultCode == RESULT_OK){  
	            Bundle bundle = data.getExtras(); 
	            String msg=bundle.getString("result");
	            Bitmap bitmap=(Bitmap) data.getParcelableExtra("bitmap");
	            if(msg!=null&&bitmap!=null){
	            	getScanResult(msg,bitmap);
	            }
	        }  
	        break;  
	    }  
	}
	
}

