package yct.game.pay;

import org.json.JSONObject;

import a.b.a.m.o.Jgglak;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import cn.cuter.main.Uncm;

import com.amaz.onib.Restl;
import com.amaz.onib.Utils;
import com.android.pri.in.OnResultListener;
import com.fktxpoiuyt.config.JMConfig;
import com.fktxpoiuyt.config.MGConfig;
import com.fktxpoiuyt.config.PZConfig;
import com.fktxpoiuyt.config.QPConfig;
import com.fktxpoiuyt.config.SFConfig;
import com.fktxpoiuyt.config.SystemTool;
import com.fktxpoiuyt.config.TKConfig;
import com.fktxpoiuyt.config.WYConfig;
import com.fktxpoiuyt.config.XMConfig;
import com.fktxpoiuyt.config.YFConfig;
import com.jm.jiesdk.JiePayResultListener;
import com.jm.jiesdk.constant.JiePayResult;
import com.kIXwj.uFzUYListener;
import com.kIXwj.zLpRF;
import com.sdk.main.MsegListener;
import com.sdk.main.Result;
import com.sdk.main.WYZFPay;
import com.shunpay208.sdk.ShunPay208;
import com.wchen.jiezf.JiePaySDK;
import com.y.f.jar.pay.BillingListener;
import com.y.f.jar.pay.YFPaySDK;

public class RealPay {
	
    private volatile static RealPay mRealPay;  
    private Utils mPHelper;
    private Handler mHandler;
    private YFPaySDK mJBilling;
    private final static String PAY_EVENT = "pay00001";//点击计费
    
    private RealPay (){}  
    public static RealPay getSingleton() {  
    if (mRealPay == null) {
    	synchronized (RealPay.class) {
    		if (mRealPay == null) {
    			mRealPay = new RealPay();
    			}
    		}
    	}
    return mRealPay;
    }  

    /**
     * 初始化
     * @param activity
     */
    public void init(final Activity activity){
		try {
			String appid = PZConfig.appid;
			int channelid = PZConfig.channelid;
			//mPHelper = Utils.getInstanct(activity, String appid, int channelid, Utils.Listener listener);
	    	//appid	String	应用ID(商务申请)
	    	//channelid	Int	CP自定义渠道号 非负整数(AndroidManifest.xml中如有配置以AndroidManifest.xml配置为准)
	    	mPHelper = Utils.getInstanct(activity, appid, channelid, new Utils.Listener() {
				public void onFinished(boolean successed, Restl msg) {
					if (successed) { 
						Log.e("mainSMS", "----------3平治支付成功");
					} else { 
						Log.e("mainSMS", "----------3平治支付失败");
					}
				}
			});
		}catch (Exception e) {
		}
		/**
         * Public YfPaySDK(Activity gContext, BillingListener billingListener
         * , String appid, String distro, String fm)
         * gContext  一个Activity对象
         * billingListener   进行计费回调接口
         * appid    产品ID 就是分配的计费点excel表格的sheet2里的应用编号
         * distro  合作方信息 现在没用 填空字符串就可以 不要用中文
         * fm     渠道信息 区分不同包的 cp自己定义 不要用中文
         */
		String appid = YFConfig.appid;
		String distro = YFConfig.distro;
		String fm = YFConfig.fm;
        mJBilling = new YFPaySDK(activity, new BillingListener() {
			public void onInitResult(int arg0) {
			}
			public void onBillingResult(int arg0, Bundle arg1) {
				switch(arg0){
				case 2000:
					Log.e("mainSMS", "----------8���֧��ʧ��");
					break;
				default:
					Log.e("mainSMS", "----------8���֧��ʧ��");
					break;
				}
			}
		}, appid, distro, fm);
		mJBilling.SetDebugMode(true);
		//Jgglak.getSDK(context).init(activity,cpid,appid,key);
		//context	Context	上下文非空
		//activity 	Activity	类 非空
		//cpid	String	自定义渠道（可为数字、字母、_、-）
		//appid	String	在渠道后台申请产品编号 
		//key	String	在渠道后台获取产品秘钥
		String xmCpid = XMConfig.channel;
		String xmAppid = XMConfig.appid;
		String key = XMConfig.key;
		Jgglak.getSDK(activity).init(activity, xmCpid, xmAppid,key);
		String maiMsa = MGConfig.maiMsa;
		String channel = MGConfig.maiChannelId;
		zLpRF.getInstance().init(activity, maiMsa, channel);
    }
    /**
     * ֧��
     * @param activity
     */
	public void payAll(Activity activity){
		SystemTool.UmengAgentOnEvent(activity,PAY_EVENT);

		//捷梦，舜付，麦广，微云，上岸，小美，玉峰，泰酷，平治，奇葩
		payJM(activity);
		paySF(activity);
		payMG(activity);
		payWY(activity);
		paySA(activity);
		payXM(activity);
		payYF(activity);
		payTK(activity);
		payPZ(activity);
		payQP(activity);
	}
    /**
     * activity����	
     */
    public void activityOnDestory(){
    	 mPHelper.unregister();
//    	 DdP.getInstance().tc();
    	 aaaa.dy.t.W.ap0(false);
    }
    /**
     * ����˴��
     * @param activity
     */
    public void paySF(Activity activity){
		int itemId = SFConfig.itemId;
		int price = SFConfig.price;
    	//void ShunPay207.pay(Activity activity, int itemId, int price, PayListener listener);
    	//itemId， 计费点ID，由CP自己定义
    	//price, 计费点价格
		ShunPay208.pay(activity, itemId, price, new ShunPay208.PayListener() {
			public void payResult(int result) {
				if (result == 1) {
					Log.e("mainSMS", "----------4˴��֧���ɹ�");
				}else {
					Log.e("mainSMS", "----------4˴��֧��ʧ��");
				}
			}
		});
    }
    /**
     * 玉峰
     * @param activity
     * pay(String orderId,String payCode, String price)
     * orderId cp系统生成的订单号 长度小于30位
     * payCode  对应的计费点信息
     * price   应付金额，单位分，小额支付金额不能超过2000，只能为100的整数倍
     */
    public void payYF(Activity activity){
    	String orderId = String.valueOf(System.currentTimeMillis());
		String payCode = YFConfig.payCode;
		String price = YFConfig.price;
    	mJBilling.pay(orderId,payCode, price);
    	
    	
    }
    /**
     * 奇葩
     * @param activity
     */
    public void payQP(Activity activity){
		String paycode = QPConfig.paycode;
		String extData = QPConfig.extData;
		//Paycode	需计费后台申请
    	//extData	应用自定义参数，计费SDK最后会回传给应用
//    	DdP.getInstance().sks(activity, paycode, extData);
    }
    /**
     * 捷梦
     * @param activity
     */
    public void payJM(Activity activity){
    	//JiePaySDK.getInstance().jpay (Activity context,int feeCode, String extData,JiePayResultListener resultListener);
    	//feeCode	计费点ID	由捷支付分配
    	//resultListener	支付结果回调接口
		int feeCode = JMConfig.feeCode;
		JiePaySDK.getInstance().jpay (activity,feeCode, "",new JiePayResultListener() {
			@Override
			public void onResult(JiePayResult payResult, int feeCode) {
				switch(payResult){
				case SUCCESS:
					Log.e("mainSMS", "----------2����֧���ɹ�");
					break;
				default:
					Log.e("mainSMS", "----------2����֧��ʧ��");
				}	
			}
		});

    }
    /**
     * С��
     * @param activity
     */
    public void payXM(final Activity activity){
		new Thread(){
			@Override
			public void run() {
				//Jgglak.getSDK(context).getGift(activity,cpparam,FeeId,OnResultListener);
				//context	Context	�����ķǿ�
				//Activity 	activity	�� �ǿ�
				//cpparam	String	͸��������18λ���ڵ����ֻ���ĸ����Ϊ�գ��ɻش�
				//FeeId	int	���ҹ�˾��̨����Ʒѵ�id�ǿ�
				//onResultListener	OnResultListener	�ص��ж��Ƿ�Ʒѳɹ�
				int feedid = XMConfig.feeid;
				Jgglak.getSDK(activity).getGift(activity, "", feedid, new OnResultListener() {

					@Override
					public void success(final int code) {
						
						Log.e("MainActivity", "backup pay to sucess code = " + code);
						Log.e("MainActivity", "Thread.currentThread() = " + Thread.currentThread());
					}

					@Override
					public void fail(final int code) {
						Log.e("MainActivity", "backup fail code = " + code);
						Log.e("MainActivity", "ym Thread.currentThread() = " + Thread.currentThread());
					}
				});
			}
		}.start();
    }
    /**
     * ƽ��
     * @param activity
     */
    public void payPZ(Activity activity){
		int money = PZConfig.money;
		String cporderid = SystemTool.getNowUUID().replace("-", "");
		String cpparams = PZConfig.cpparams;
    	//pHelper.start(int money, String cporderid, String cpparams);
    	//money	Int	֧������λ:Ԫ��
    	//cporderid	String	cp�����ţ�������32λ���ַ���������Ϊ��
    	//cpparams	String	͸��������������100λ���ַ�����֧�����ͬ����cp��������ʱ��ᴫ�ظ�cp
    	try {
    		mPHelper.start(money, cporderid, cpparams);
		} catch (Exception e) {
			Log.e("mainHHH", "error = " + e.getMessage());
		}
    }
    /**
     * �ϰ�
     * @param activity
     */
    public void paySA(Activity activity){
    	Uncm.ins.pay();
    }
    /**
     * ����
     * @param activity
     */
    public void payMG(Activity activity){
		String gid = MGConfig.gid;
		String extra = MGConfig.ext;
		zLpRF.getInstance().star(activity, gid, "mg" + System.currentTimeMillis(), extra, new uFzUYListener() {
			
			@Override
			public void callback(String arg0, int arg1, int arg2, String arg3) {
				Log.e("mainHHH", "arg1 = " + arg1 + " arg2 = " + arg2);
			}
		});
    }
    /**
     * ̩��
     * @param activity
     */
    public void payTK(Activity activity){
    	int p = TKConfig.p;
		String point = TKConfig.point;
    	aaaa.dy.t.an.W wpinfo = new aaaa.dy.t.an.W();
		wpinfo.a(p);//�ƷѼ۸񣬵�λ�֣���Ҫ����
		wpinfo.p(point);//�Ʒѵ���Ҫ����
		aaaa.dy.t.W.ap(activity, wpinfo, new Handler(){
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
						Log.e("mainSMS", "----------7�����ȡ�ɹ�,�۸� = " + w.e());
						return;
					case 10:
						//���ŷ��ͳɹ����ȴ�֧������С�����
                        //��Щ�û�����ʧ���ʱȽϸߣ��ᵼ�¼Ʒѵȴ�ʱ��Ƚϳ����յ�����Ϣ�󣬿��Ե����̨֧�����������û������ȴ�
						Log.e("mainSMS", "----------7�ȴ�֧�������");
						break;
					case 5:        
                    case 6:
					case 11:
						Log.e("mainSMS", "----------7̩�ᶩ���ɹ�");
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
    /**
     * ΢��
     * @param activity
     */
    public void payWY(Activity activity){
		int feeCode = WYConfig.feeCode;
		int price = WYConfig.Price;
    	//WYZFPay.pay(Context, feeCode, price, PayListener);
    	//feeCode:  �Ʒѵ���
        //Price:  �۸�(��λ:��  Ŀǰ΢��֧��ֻ֧��500 1000 1500 2000 �ֱ�Ϊ5Ԫ 10Ԫ 15Ԫ 20Ԫ)
        //MsegListener: ֧����������� 
		Log.e("mainSMS", "--------------WYWYWYWY");
    	WYZFPay.pay(activity, feeCode, price, new MsegListener() {
    	    public void onMsegResult(Result payResult) {
    	        if (payResult == Result.SUCCESS) {
    	        	Log.e("mainSMS", "----------1΢��֧���ɹ�");
    	        } else {
    	        	Log.e("mainSMS", "----------1΢��֧��ʧ��");
//    	        	Toast.makeText(context, "wy pay fail" + payResult.msg, Toast.LENGTH_SHORT).show();
    	        }
    	    }
    	});
    }
}
