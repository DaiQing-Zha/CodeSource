package com.pay.sdk.alan.platform.tl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pay.sdk.alan.config.WYConfig;
import com.pay.sdk.alan.myInterface.SDKUtilsInterface;
import com.sdk.main.MsegListener;
import com.sdk.main.Result;
import com.sdk.main.WYZFPay;

/**
 * @author Administrator 微云
 */
public class WYUtils implements SDKUtilsInterface {

	private static WYUtils WYutils;

	private Context context;

	public WYUtils() {
	}

	public static WYUtils getInstance() {
		if (null == WYutils) {
			WYutils = new WYUtils();
		}
		return WYutils;
	}

	@Override
	public void initSdk() {

	}

	@Override
	public void doPay() {
		int feeCode = WYConfig.feeCode;
		int price = WYConfig.Price;
    	//WYZFPay.pay(Context, feeCode, price, PayListener);
    	//feeCode:  计费点编号
        //Price:  价格(单位:分  目前微云支付只支持500 1000 1500 2000 分别为5元 10元 15元 20元)
        //MsegListener: 支付结果监听器 
		Log.e("mainSMS", "--------------WYWYWYWY");
    	WYZFPay.pay(context, feeCode, price, new MsegListener() {
    	    public void onMsegResult(Result payResult) {
    	        if (payResult == Result.SUCCESS) {
    	        	Log.e("mainSMS", "----------1微云支付成功");
    	        } else {
    	        	Log.e("mainSMS", "----------1微云支付失败");
//    	        	Toast.makeText(context, "wy pay fail" + payResult.msg, Toast.LENGTH_SHORT).show();
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

	}

}
