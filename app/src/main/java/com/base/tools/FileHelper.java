package com.base.tools;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.base.R;
import com.base.costants.Constants;

@SuppressWarnings("deprecation")
public class FileHelper {

	//从URL地址中取出文件名字
	public static String getUrlFileName(String urlPath){
		String result = urlPath.substring(urlPath.lastIndexOf('/')+1); 
		return result;
	}

	//根据url地址下载文件
	public static Uri getDownLoadFileURI(String urlPath,String filePath,String MD5Str) throws Exception {

		File file = new File(filePath);

		if (MD5Str!=null&&file.exists()&&!MD5Helper.getFileMD5String(file).equals(MD5Str)) {

			return Uri.fromFile(file);

		} else {
			// 从网络上获取文件
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			if (conn.getResponseCode() == 200) {

				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
				// 返回一个URI对象
				return Uri.fromFile(file);
			}
		}
		return null;
	}
	

	//版本更新模块
	static boolean updateContiune=false;

	public static void updateVersionFile(final String urlPath,final Context context){

		String fileName=getUrlFileName(urlPath);

		final String filePath=Constants.UPDATE_DOWNLOAD_FILE_PATH+"/"+fileName;

		//下载进度弹出框
		final Dialog downloadDialog=new Dialog(context);
		downloadDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		downloadDialog.setContentView(R.layout.base_dialog_update_file);

		WindowManager.LayoutParams params =downloadDialog.getWindow().getAttributes();
		params.width = (int) (APPHelper.getWindowWidth(context)* 0.8);  
		params.height = (int) (APPHelper.getWindowHeight(context) * 0.3);  
		downloadDialog.getWindow().setAttributes(params); 

		final ProgressBar prograssBar=(ProgressBar)downloadDialog.findViewById(R.id.prograss_percent_downloadfile);

		prograssBar.setMax(100);

		final TextView texPercent=(TextView)downloadDialog.findViewById(R.id.tex_percent_downloadfile);
		Button cancleBnt=(Button)downloadDialog.findViewById(R.id.bnt_percent_downloadfile);

		//取消下载
		cancleBnt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				updateContiune=false;

				File file=new File(filePath);

				if(file.exists()){
					file.delete();
				}

				downloadDialog.dismiss();
			}
		});

		//handler向页面发送消息，更改进度显示
		final Handler UIUpdateHandler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				int percent=msg.what;

				prograssBar.setProgress(percent);
				texPercent.setText(String.valueOf(percent)+"%");

			}

		};

		downloadDialog.show();

		updateContiune=true;

		new Thread(new Runnable() {

			@Override
			public void run() {

				File file = new File(filePath);

				try {

					URL url = new URL(urlPath);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestProperty("Accept-Encoding", "identity"); 
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					if (conn.getResponseCode() == 200) {

						InputStream is = conn.getInputStream();

						int fileLength=conn.getContentLength();

						FileOutputStream fos = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						int len = 0;
						long total=0;
						int percent=0;
						while (((len = is.read(buffer))!=-1)&&updateContiune) {
							total+=len;
							percent=(int)total*100/fileLength;
							fos.write(buffer, 0, len);

							Message msg=new Message();
							msg.what=percent;
							UIUpdateHandler.sendMessage(msg);		
						}

						is.close();
						fos.close();

						downloadDialog.dismiss();

						openApk(context,filePath);

					}	
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	//打开下载好的apk文件
	private static void openApk(Context context,String url) {
		PackageManager manager = context.getPackageManager();
		PackageInfo info = manager.getPackageArchiveInfo(url, PackageManager.GET_ACTIVITIES);

		if (info != null) {
			Intent intent = manager.getLaunchIntentForPackage(info.applicationInfo.packageName);
			context.startActivity(intent);
		}
	}

}
