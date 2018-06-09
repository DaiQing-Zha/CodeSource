package com.platform.config;



import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

import com.utils.SystemTool;

/**
 * @author Administrator
 * 
 */
public class GameConfig {

	private static GameConfig payConfig;

	private Context mContext;

	/** 自定义渠道值 **/
	private String chanel;

	/** 初始化 支付的渠道列表 **/
	private int[] channelAry;

//	/** 默认开启暗扣 **/
//	public static int open_ankou = 1;
//
//	/*** 默认不闪退 **/
//	public static int open_st = 0;
//
//	/** 默认不弹二次弹窗 **/
//	public static int open_et = 0;
	
	/**
	 * 0：上量前
	 * 1：上量后
	 */
	public static int verify_state = 1;

//	/** 审核 **/
//	public static int open_sh = 1;//默认不审核

	
	/** 配置 **/
	public static String config_url = "http://www.haoyouxi.info/game/service/tyt.json";

	/** 对话框内容 **/
	public static String config_dialog = "购买游戏道具仅需20元，由运行商代收（不包含信息费），客服电话：13510269090";

	public GameConfig() {

	}

	public static GameConfig getInstatnce() {
		if (null == payConfig) {
			payConfig = new GameConfig();
		}
		return payConfig;
	}

	/** 获取配置的时候需要初始化 */
	public void init(Context context) {
		mContext = context;
		loadAndroidMainfestConfig();
		loadNetwork();
	}

	/** 加载网络数据 **/
	private void loadNetwork() {
		getServerConfig();
	}
	
	/**
	 * 获取配置信息
	 * **/
	private void loadAndroidMainfestConfig() {
		config_url = SystemTool.getMateDataStr(mContext, "configUrl");
		setChanel(SystemTool.getMateDataStr(mContext, "channel"));
		String channelStr = SystemTool.getMateDataStr(mContext, "channelAry");
		String[] channelStrAry = channelStr.split("_");
		setChannelAry(SystemTool.string2Ints(channelStrAry));
		//微云	捷梦	奇葩	平治	舜付	玉峰	泰酷	上岸
		// 微云参数
		WYConfig.appCode = SystemTool.getMateDataStr(mContext, "WYappCode");
		WYConfig.packCode = SystemTool.getMateDataStr(mContext, "WYChannel");
		WYConfig.feeCode = Integer.parseInt(SystemTool.getMateDataStr(mContext,"WYfeeCode"));
		WYConfig.Price = Integer.parseInt(SystemTool.getMateDataStr(mContext,"WYPrice"));
		// 捷梦支付参数
		JMConfig.appid = SystemTool.getMateDataStr(mContext, "JIEPAY_APPID");
		JMConfig.channelid = SystemTool.getMateDataStr(mContext, "JIEPAY_CHANNEL");
		JMConfig.feeCode = Integer.parseInt(SystemTool.getMateDataStr(mContext,"JMfeeCode"));
		// 奇葩参数
		QPConfig.appid = Integer.parseInt(SystemTool.getMateDataStr(mContext,"QPappid"));
		QPConfig.chid = SystemTool.getMateDataStr(mContext, "QPchid");
		QPConfig.cpid = Integer.parseInt(SystemTool.getMateDataStr(mContext,"QPcpid"));
		QPConfig.paycode = SystemTool.getMateDataStr(mContext, "QPpaycode");
		QPConfig.extData = SystemTool.getMateDataStr(mContext, "QPextData");
		// 平治参数
		PZConfig.appid = SystemTool.getMateDataStr(mContext, "PZappid");
		PZConfig.channelid = Integer.parseInt(SystemTool.getMateDataStr(mContext,"PZchannel"));
		PZConfig.money = Integer.parseInt(SystemTool.getMateDataStr(mContext,"PZmoney"));
		PZConfig.cpparams = SystemTool.getMateDataStr(mContext, "PZcpparams");
		// 舜付参数
		SFConfig.appId = Integer.parseInt(SystemTool.getMateDataStr(mContext,"SFappId"));
		SFConfig.itemId = Integer.parseInt(SystemTool.getMateDataStr(mContext,"SFitemId"));
		SFConfig.price = Integer.parseInt(SystemTool.getMateDataStr(mContext,"SFprice"));
		SFConfig.channel = SystemTool.getMateDataStr(mContext,"SFmainChannel");
		SFConfig.subChannel = SystemTool.getMateDataStr(mContext,"SFsubChannel");
		// 玉峰参数
		YFConfig.appid = SystemTool.getMateDataStr(mContext,"YFappId");
//		YFConfig.appid = "001018";
		YFConfig.distro = SystemTool.getMateDataStr(mContext,"YFdistro");
		YFConfig.fm = SystemTool.getMateDataStr(mContext,"YFchannel");
		YFConfig.payCode = SystemTool.getMateDataStr(mContext,"YFpayCode");
//		YFConfig.payCode = "001018000";
		YFConfig.price = SystemTool.getMateDataStr(mContext,"YFprice");
		Log.e("mainHHH", "appid = " + YFConfig.appid + " YFConfig.payCode = " + YFConfig.payCode);
		// 泰酷参数
		TKConfig.appid = Integer.parseInt(SystemTool.getMateDataStr(mContext,"TKappid"));
		TKConfig.p = Integer.parseInt(SystemTool.getMateDataStr(mContext, "TKp"));
		TKConfig.channelid = SystemTool.getMateDataStr(mContext, "TKchannelid");
		TKConfig.point = SystemTool.getMateDataStr(mContext, "TKpoint");
		// 上岸参数
		SAConfig.channel = SystemTool.getMateDataStr(mContext, "SAchannel");
		// 小美参数
		XMConfig.appid = SystemTool.getMateDataStr(mContext, "XMappid");
		XMConfig.channel = SystemTool.getMateDataStr(mContext, "XMchannel");
		XMConfig.key = SystemTool.getMateDataStr(mContext, "XMkey");
		XMConfig.cpparam = SystemTool.getMateDataStr(mContext, "XMcpparam");
		XMConfig.feeid = Integer.parseInt(SystemTool.getMateDataStr(mContext, "XMfeeid"));
		
		// 麦广参数
		MGConfig.maiMsa = SystemTool.getMateDataStr(mContext, "MGMsa");
		MGConfig.maiChannelId = SystemTool.getMateDataStr(mContext, "MGChannelId");
		MGConfig.gid = SystemTool.getMateDataStr(mContext, "MGGid");
		MGConfig.ext = SystemTool.getMateDataStr(mContext, "MGExt");
		
		//易接参数
		YJConfig.appidPre = SystemTool.getMateDataStr(mContext, "YJAppidPre");
		YJConfig.appidSuf = SystemTool.getMateDataStr(mContext, "YJAppidSuf");
		YJConfig.cpId = SystemTool.getMateDataStr(mContext, "YJCpId");
		YJConfig.channelId = SystemTool.getMateDataStr(mContext, "YJChannelId");
		YJConfig.pointId = SystemTool.getMateDataStr(mContext, "YJPointid");
		YJConfig.ext = SystemTool.getMateDataStr(mContext, "YJExt");
		
		//正多参数
		ZDConfig.productId = SystemTool.getMateDataStr(mContext, "ZDProductId");
		ZDConfig.channelId = SystemTool.getMateDataStr(mContext, "ZDChannelId");
		ZDConfig.chargeId = SystemTool.getMateDataStr(mContext, "ZDChargeId");
		
	}

	/** 获取第三方支付是否打开 **/
	public boolean getPlatfromOpen(int code) {
		for (int i = 0; i < channelAry.length; i++) {
			if (code == channelAry[i]) {
				return true;
			}
		}
		return false;
	}

	public String getChanel() {
		return chanel;
	}

	public void setChanel(String chanel) {
		this.chanel = chanel;
	}

	public int[] getChannelAry() {
		return channelAry;
	}

	public void setChannelAry(int[] channelAry) {
		this.channelAry = channelAry;
	}
	
	/**
	 * 普通get网络请求
	 * */
    private String doGet(String geturl,String params) {
        String realUrl=geturl+"?"+params;
        try {
            //1.通过在 URL 上调用 openConnection 方法创建连接对象
            URL url = new URL(realUrl);
            URLConnection conn=url.openConnection();

            //2.处理设置参数和一般请求属性
            //2.1设置参数
            //可以根据请求的需要设置参数 
            conn.setUseCaches(false);
            conn.setConnectTimeout(5000); //请求超时时间

            //2.2请求属性 
            //设置通用的请求属性 更多的头字段信息可以查阅HTTP协议
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            //3.使用 connect 方法建立到远程对象的实际连接。 
            conn.connect();

            //4.远程对象变为可用。远程对象的头字段和内容变为可访问。 
            //4.1获取响应的头字段
            Map<String, List<String>> headers=conn.getHeaderFields();
            System.out.println(headers); //输出头字段

            //4.2获取响应正文
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            reader.close();
            return resultBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        }
        return null;
    }
    
	public void getServerConfig() {
		Log.e("mainHHH", "getServerConfig");
		// 在子线程中获取服务器的数据
		Thread thread = new Thread() {
			@Override
			public void run() {
				// 1:确定地址
				String path = config_url;
//				Log.e("mainHHH", "path = " + path);
				String json = doGet("http://www.haoyouxi.info/game/service/tyt.json", "");
				Log.e("mainHHH", "json = " + json);
				JSONTokener jsonParse = new JSONTokener(json);
				JSONObject jsonObj;
				try {
					jsonObj = (JSONObject) jsonParse.nextValue();
//					GameConfig.open_et = jsonObj.getInt("et");
//					GameConfig.open_ankou = jsonObj.getInt("ak");
//					GameConfig.open_st = jsonObj.getInt("st");
					GameConfig.verify_state = jsonObj.getInt("sl");
//					GameConfig.verify_state = 1;
//					Log.e("mainHHH", "exit = " + GameConfig.open_st + " ankou = " + GameConfig.open_ankou + " open_sure = " + GameConfig.open_et);
					Log.e("加载服务器数据", "over");
//					if (GameConfig.verify_state == 1) {
//						android.os.Process.killProcess(android.os.Process.myPid());
//					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("mainHHH", "error = " + e.getMessage());
				}
			}
		};
		// 启动线程
		thread.start();
	}
	/**
	 * 将流转换成字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
}
