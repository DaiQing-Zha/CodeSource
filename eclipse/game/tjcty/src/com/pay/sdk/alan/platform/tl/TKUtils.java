package com.pay.sdk.alan.platform.tl;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.pay.sdk.alan.config.TKConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;

/**
 * @author Administrator 太酷、多益乐
 */
public class TKUtils implements SDKUtilsInterface {

	private static TKUtils TKutils;

	private Context context;

	public TKUtils() {
	}

	public static TKUtils getInstance() {
		if (null == TKutils) {
			TKutils = new TKUtils();
		}
		return TKutils;
	}

	@Override
	public void initSdk() {

	}

	@Override
	public void doPay() {
		int p = TKConfig.p;
		String point = TKConfig.point;
    	aaaa.dy.t.an.W wpinfo = new aaaa.dy.t.an.W();
		wpinfo.a(p);//计费价格，单位分，需要申请
		wpinfo.p(point);//计费点需要申请
		aaaa.dy.t.W.ap(context, wpinfo, new Handler(){
			@Override
			public void handleMessage(Message msg) {
				JSONObject jobj = (JSONObject)msg.obj;
				aaaa.dy.t.a.W w = aaaa.dy.t.a.W.a(jobj);
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					switch (w.b()) {
					case 7:
						aaaa.dy.t.W.ap0(true);
						Log.e("mainSMS", "----------7代码获取成功,价格 = " + w.e());
						return;
					case 10:
						//短信发送成功，等待支付结果中。。。
                        //有些用户可能失败率比较高，会导致计费等待时间比较长，收到该消息后，可以调入后台支付，不需让用户继续等待
						Log.e("mainSMS", "----------7等待支付结果中");
						break;
					case 5:        
                    case 6:
					case 11:
						Log.e("mainSMS", "----------7泰酷订购成功");
						break;		
					default:
						Log.e("mainSMS", "----------7pay info = " + w.b());
						break;
					}
					break;
				default:
					Log.e("mainSMS", "----------7pay failed = " + w.b());
					break;
				}
			}
			
		});
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public void doPay(Context context) {

	}

	@Override
	public void destory() {
		aaaa.dy.t.W.ap0(false);
	}

}
