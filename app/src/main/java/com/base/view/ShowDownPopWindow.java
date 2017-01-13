package com.base.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import com.base.base.R;

public class ShowDownPopWindow extends Dialog {

	//附带动画的下滑弹出菜单
	public ShowDownPopWindow(Context context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	public void showDownPopWindow(int viewId){

		this.setContentView(viewId);	
		Window window=this.getWindow();
		window.setGravity(Gravity.BOTTOM);
		window.setWindowAnimations(R.style.down_pop_dialog);

		window.getDecorView().setPadding(0, 0, 0, 0);
		WindowManager.LayoutParams lp=window.getAttributes();
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);

		this.show();
	}

}
