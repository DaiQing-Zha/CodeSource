package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class WYOperate {
	
	public static String[] YDGood = {};//移动优质省份
	public static String[] YDOrdinary = {};//移动普通省份
	public static String[] YDShield = {};//移动屏蔽省份
	
	public static String[] LTGood = {};//联通优质省份
	public static String[] LTOrdinary = {};//联通普通省份
	public static String[] LTShield = {};//联通屏蔽省份
	
	public static String[] DXGood = {};//电信优质省份
	public static String[] DXOrdinary = {};//电信普通省份
	public static String[] DXShield = {};//电信屏蔽省份
	
	private final static String ydGoodProvince = "移动开通（优质）:";
	private final static String ydOrdinaryProvince = "移动开通（一般）:";
	private final static String ydShieldProvince = "移动屏蔽:";
	
	private final static String ltGoodProvince = "联通开通（优质）:";
	private final static String ltOrdinaryProvince = "联通开通（一般）:";
	private final static String ltShieldProvince = "联通屏蔽:";
	
	private final static String dxGoodProvince = "电信开通（优质）:";
	private final static String dxOrdinaryProvince = "电信开通（一般）:";
	private final static String dxShieldProvince = "电信屏蔽:";
	
	private final static String splitStr = ",";
	
	/**
	 * 实例
	 */
	private volatile static WYOperate instance;
    /**
     * 构造函数
     */
	private WYOperate(){
//        System.out.println("WYOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static WYOperate getInstance(){
        if(instance==null){
            synchronized (WYOperate.class){
                if(instance==null){
                    instance=new WYOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.WYPath);
//		System.out.println(result);
		String ydGoodProvinceLocal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ydOrdinaryProvince));
		String ydOrdinaryProvinceLocal = result.substring(result.indexOf(ydOrdinaryProvince) + ydOrdinaryProvince.length(), result.indexOf(ydShieldProvince));
		String ydShieldProvinceLocal = result.substring(result.indexOf(ydShieldProvince) + ydShieldProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(ltOrdinaryProvince));
		String ltOrdinaryProvinceLocal = result.substring(result.indexOf(ltOrdinaryProvince) + ltOrdinaryProvince.length(), result.indexOf(ltShieldProvince));
		String ltShieldProvinceLocal = result.substring(result.indexOf(ltShieldProvince) + ltShieldProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(), result.indexOf(dxOrdinaryProvince));
		String dxOrdinaryProvinceLocal = result.substring(result.indexOf(dxOrdinaryProvince) + dxOrdinaryProvince.length(), result.indexOf(dxShieldProvince));
		String dxShieldProvinceLocal = result.substring(result.indexOf(dxShieldProvince) + dxShieldProvince.length(), result.length());
		
		YDGood = ydGoodProvinceLocal.split(splitStr);
		YDOrdinary = ydOrdinaryProvinceLocal.split(splitStr);
		YDShield = ydShieldProvinceLocal.split(splitStr);
		LTGood = ltGoodProvinceLocal.split(splitStr);
		LTOrdinary = ltOrdinaryProvinceLocal.split(splitStr);
		LTShield = ltShieldProvinceLocal.split(splitStr);
		DXGood = dxGoodProvinceLocal.split(splitStr);
		DXOrdinary = dxOrdinaryProvinceLocal.split(splitStr);
		DXShield = dxShieldProvinceLocal.split(splitStr);
	    
//		System.out.println(ydGoodProvinceLocal);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydGoodProvinceLocal);
//		System.out.println(ydOrdinaryProvinceLocal);
//		System.out.println(ydShieldProvinceLocal);
//		
//		System.out.println(ltGoodProvinceLocal);
//		System.out.println(ltOrdinaryProvinceLocal);
//		System.out.println(ltShieldProvinceLocal);
//		
//		System.out.println(dxGoodProvinceLocal);
//		System.out.println(dxOrdinaryProvinceLocal);
//		System.out.println(dxShieldProvinceLocal);
		
	}
	/**
	 * 测试函数
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.WYPath);
		System.out.println(result);
	}
}
