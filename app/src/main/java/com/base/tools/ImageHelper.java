package com.base.tools;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class ImageHelper {

	//设置iamgeview对于屏幕的比例大小
	public static void setHeightPercent(int xChild,int xParent,int x,int y,ImageView imageView,Context context){

		int width=APPHelper.getWindowWidth(context)*xChild/xParent;

		LayoutParams params=new LayoutParams(0,0);
		params.width=width;
		params.height=width*y/x;

		imageView.setLayoutParams(params);
	}

	//缩放bitmap对象
	public static Bitmap zoomBitmap(Bitmap bitmap,int x,int y){

		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);  
		InputStream is = new ByteArrayInputStream(baos.toByteArray()); 

		// 图片源
		Bitmap bm = BitmapFactory.decodeStream(is);
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 设置想要的大小
		int newWidth =x;
		int newHeight =y;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,true);
		// 放在画布上
		return newbm;
	}

	public Bitmap compressBmpFromBmp(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > 100) { 
			baos.reset();
			options -= 10;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	@SuppressWarnings("deprecation")
	public Bitmap compressImageFromFile(String srcPath,int width,int height) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;//只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh =width;//
		float ww =height;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置采样率

		newOpts.inPreferredConfig = Config.ARGB_8888;//该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		//		return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
		//其实是无效的,大家尽管尝试
		return bitmap;
	}

}
