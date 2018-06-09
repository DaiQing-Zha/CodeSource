package com.meiqu.beauty;



import bvcnm.qasdf.xzbsdf.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.meiqu.beauty.abc.nm;
import com.pay.sdk.alan.main.MoSDK;
import com.pay.sdk.alan.tool.SystemTool;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.UMConfigure;

public class SplashActivity extends Activity {

	//播放Android项目工程里的视频文件 https://blog.csdn.net/keyue0459/article/details/8659389
    private boolean a;
    private Handler b;
    ImageView iv_notices_img;
    VideoView videoView;
    
    static boolean a(SplashActivity arg1) {
        return arg1.a;
    }

    static boolean a(SplashActivity arg0, boolean arg1) {
        arg0.a = arg1;
        return arg1;
    }

    static Handler b(SplashActivity arg1) {
        return arg1.b;
    }

    protected void onCreate(Bundle arg6) {
        super.onCreate(arg6);
        this.setContentView(2130968578);
        videoView = (VideoView) findViewById(R.id.videoView);
		//设置视频路径
		String uri = "android.resource://" + getPackageName() + "/" + R.raw.show;
		//用来设置要播放的mp4文件
		videoView.setVideoURI(Uri.parse(uri));
		//用来设置控制台样式
		videoView.setMediaController(new MediaController(this));
		//用来设置起始播放位置，为0表示从开始播放
		videoView.seekTo(0);
		//用来设置mp4播放器是否可以聚焦
		videoView.requestFocus();
		//开始播放
		videoView.start();
		

        
//        iv_notices_img = (ImageView) findViewById(R.id.iv_notices_img);
//		((AnimationDrawable) iv_notices_img.getBackground()).start();  
        nm.nim(this);
		/**
		* 设置组件化的Log开关
		* 参数: boolean 默认为false，如需查看LOG设置为true
		*/
		UMConfigure.setLogEnabled(true);
		/**
		* 设置日志加密
		* 参数：boolean 默认为false（不加密）
		*/
		UMConfigure.setEncryptEnabled(true);
		MobclickAgent.setScenarioType(this, EScenarioType.E_UM_NORMAL);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                       startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                       finish();
                       videoView.pause();
                    }
                }, 1000);
            }
        }, 4000);
        MoSDK.doSDKForce(this);
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        boolean v0 = arg2 == 4 ? false : super.onKeyDown(arg2, arg3);
        return v0;
    }
    
   @Override
   protected void onResume() {
	   super.onResume();
	   SystemTool.UmengAgentOnPause(this);
   }
   @Override
   protected void onPause() {
	   super.onPause();
	   SystemTool.UmengAgentOnResume(this);
   }
}


