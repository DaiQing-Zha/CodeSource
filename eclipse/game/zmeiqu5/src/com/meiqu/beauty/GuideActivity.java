/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.meiqu.beauty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.meiqu.beauty.abc.fab;
import com.meiqu.beauty.c.e;
import com.meiqu.beauty.d.c;
import com.meiqu.beauty.model.Image;

public class GuideActivity extends Activity {
	private ScratchView scViewA;
	private ImageView imgB;
	private View c;
	private ImageView imgD;
	private View e;
	private TranslateAnimation fa;
	private float g = 0.0F;
	private float h = 0.0F;
	private float i = 0.0F;
	private float j = 0.0F;
	private int k = 0;
	private boolean l = false;
	private Thread m = new Thread() {
		public void run() {
			while (true) {
				if (GuideActivity.g(GuideActivity.this).hasStarted()) {
					Object localObject = new Transformation();
					GuideActivity.g(GuideActivity.this).getTransformation(AnimationUtils.currentAnimationTimeMillis(),
							(Transformation) localObject);
					localObject = ((Transformation) localObject).getMatrix();
					float[] arrayOfFloat = new float[9];
					((Matrix) localObject).getValues(arrayOfFloat);
					GuideActivity.a(GuideActivity.this, arrayOfFloat[2]);
					GuideActivity.b(GuideActivity.this, arrayOfFloat[5]);
					GuideActivity.this.runOnUiThread(new Runnable() {
						public void run() {
							if (!(GuideActivity.g(GuideActivity.this).hasStarted()))
								return;
							GuideActivity.f(GuideActivity.this).b(GuideActivity.d(GuideActivity.this),
									GuideActivity.e(GuideActivity.this));
						}
					});
				}
				try {
					Thread.sleep(50L);
				} catch (InterruptedException localInterruptedException) {
					localInterruptedException.printStackTrace();
				}
			}
		}
	};
	private Handler n = new Handler() {
		public void handleMessage(Message arg7) {
            if(arg7 != null && arg7.what == 1001) {
                Object v0 = arg7.obj;
                if(v0 != null && ((c)v0).a.a == 0 && ((c)v0).f != null) {
                    if(((c)v0).d != 6) {
                    }
                    else {
                        fab.L = new Image[((c)v0).f.length];
                        int v1;
                        for(v1 = 0; v1 < fab.L.length; ++v1) {
                            fab.L[v1] = new Image(((c)v0).f[v1].a, ((c)v0).f[v1].g);
                            Log.e("==img", "id:" + ((c)v0).f[v1].a + " url:" + ((c)v0).f[v1].g);
                        }
                    }
                }
            }
        }
	};

	private void a(int paramInt) {
		if (paramInt == 1) {
			this.k = 1;
			this.imgD.setImageResource(2130837523);
			this.i = ((float)a((this), 100f));
            this.j = ((float)a((this), 290f));
			this.g = this.i;
			this.h = this.j;
			this.fa = new TranslateAnimation(this.i, this.i + a(this, 220.0F), this.j, this.j);
			this.fa.setDuration(3000L);
			this.fa.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationEnd(Animation paramAnimation) {
					GuideActivity.f(GuideActivity.this).a();
					GuideActivity.f(GuideActivity.this).b();
					GuideActivity.h(GuideActivity.this).startAnimation(GuideActivity.g(GuideActivity.this));
				}

				public void onAnimationRepeat(Animation paramAnimation) {
				}

				public void onAnimationStart(Animation paramAnimation) {
					GuideActivity.a(GuideActivity.this, GuideActivity.b(GuideActivity.this));
					GuideActivity.b(GuideActivity.this, GuideActivity.c(GuideActivity.this));
					GuideActivity.f(GuideActivity.this).a(GuideActivity.d(GuideActivity.this),
							GuideActivity.e(GuideActivity.this));
				}
			});
			this.imgB.setVisibility(0);
			this.imgB.startAnimation(this.fa);
			this.m.start();
			new Handler().postDelayed(new Runnable() {
				public void run() {
					if (GuideActivity.a(GuideActivity.this) >= 2)
						return;
					GuideActivity.a(GuideActivity.this, 2);
				}
			}, 4000L);
		}
		else if(paramInt == 2) {
            this.k = 2;
            this.c.setVisibility(8);
            this.e.setVisibility(0);
            this.l = true;
            }
		
	}
	
	public static Activity mActivity;
	
// 程序一启动调用此方法
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		mActivity = this;
		
		setContentView(2130968576);
		getWindow().addFlags(128);
		this.scViewA = ((ScratchView) findViewById(2131296257));
		this.scViewA.setEraserSize(150.0F);
		this.scViewA.setMaskColor(0);
		this.scViewA.setWatermark(2130837517);
		this.scViewA.setCanErase(false);
		this.c = findViewById(2131296259);
		this.imgD = ((ImageView) findViewById(2131296260));
		this.imgB = ((ImageView) findViewById(2131296258));
		this.e = findViewById(2131296261);
		this.imgB.setVisibility(8);
		this.e.setVisibility(8);
		
		if ((fab.L == null) || (fab.L.length == 0))
			new e(this, this.n, 6, 0).d();
		this.k = 0;
		new Handler().postDelayed(new Runnable() {
			public void run() {
				if (GuideActivity.a(GuideActivity.this) >= 1)
					return;
				GuideActivity.a(GuideActivity.this, 1);
			}
		}, 4000L);
	}
	
	public static void pay(){
		Log.e("==pay", "=====pay pay pay");
		// 只能在这里加支付
		
		// 初始化
		// 支付
		// 写在 		onCreate方法中
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if (paramInt == 4) {
			pay();
			return false;
		}
		return super.onKeyDown(paramInt, paramKeyEvent);
	}

	//点击3下跳转至主activity
	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		if (paramMotionEvent.getActionMasked() == 1) {
			if (this.k == 0) {
				a(1);
				return false;
			}
			if (this.k == 1) {
				a(2);
				return false;
			}
			if (this.l) {
				startActivity(new Intent(this, MainActivity.class));
				finish();
				return false;
			}
		}
		return super.onTouchEvent(paramMotionEvent);
	}
	
	 static void a(GuideActivity arg0, int arg1) {
	        arg0.a(arg1);
	    }

	    static float b(GuideActivity arg1) {
	        return arg1.i;
	    }

	    static float b(GuideActivity arg0, float arg1) {
	        arg0.h = arg1;
	        return arg1;
	    }

	    static float c(GuideActivity arg1) {
	        return arg1.j;
	    }

	    static float d(GuideActivity arg1) {
	        return arg1.g;
	    }

	    static float e(GuideActivity arg1) {
	        return arg1.h;
	    }

	    static ScratchView f(GuideActivity arg1) {
	        return arg1.scViewA;
	    }

	    static TranslateAnimation g(GuideActivity arg1) {
	        return arg1.fa;
	    }

	    static ImageView h(GuideActivity arg1) {
	        return arg1.imgB;
	    }
	    
	    static float a(GuideActivity arg0, float arg1) {
	        arg0.g = arg1;
	        return arg1;
	    }
	    
	    static int a(GuideActivity arg1) {
	        return arg1.k;
	    }
	    
		public static int a(Context paramContext, float paramFloat) {
			return (int) (paramContext.getResources().getDisplayMetrics().density * paramFloat + 0.5F);
		}
}