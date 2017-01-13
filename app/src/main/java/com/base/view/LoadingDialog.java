package com.base.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;
import com.base.base.R;
import com.base.tools.APPHelper;

public class LoadingDialog extends Dialog {

	GifView gifView;

	public LoadingDialog(Context context) {
		super(context);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.base_dialog_loading);

		int width=APPHelper.getWindowWidth(context)/3;
		int height=width;
		
		Window window=getWindow();
		
		LayoutParams lp=window.getAttributes();
		
		lp.width=width;
		
		lp.height=height;
		
		window.setAttributes(lp);

		window.setBackgroundDrawable(new ColorDrawable(0));
		
		gifView=(GifView)findViewById(R.id.dialog_loading_gifview);

		gifView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
		
		// 设置Gif图片源
		gifView.setGifImage(R.drawable.loading);

		// 设置显示的大小，拉伸或者压缩
		gifView.setShowDimension(width,height);
		// 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
		gifView.setGifImageType(GifImageType.COVER);

	}

}
