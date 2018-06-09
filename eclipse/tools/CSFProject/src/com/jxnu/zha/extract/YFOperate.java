package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class YFOperate {
	
	public static String[] YDGood = {};//移动优质省份
	
	public static String[] LTGood = {};//联通优质省份
	
	public static String[] DXGood = {};//电信优质省份
	
	private final static String ydGoodProvince = "移动:";
	private final static String ltGoodProvince = "联通（只适配20元）:";
	private final static String dxGoodProvince = "电信（只适配20元）:";
	private final static String splitStr = ",";
	
	/**
	 * 实例
	 */
	private volatile static YFOperate instance;
    /**
     * 构造函数
     */
	private YFOperate(){
//        System.out.println("YFOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static YFOperate getInstance(){
        if(instance==null){
            synchronized (YFOperate.class){
                if(instance==null){
                    instance=new YFOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.YFPath);
		result = result.replaceAll("\\.", " ").replaceAll(" ", ",");
//		System.out.println(result);
		
		String ydGoodProvinceLcoal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(), result.length());
		
		YDGood = ydGoodProvinceLcoal.split(splitStr);
		LTGood = ltGoodProvinceLocal.split(splitStr);
		DXGood = dxGoodProvinceLocal.split(splitStr);
	    
//		System.out.println(ydGoodProvinceLcoal);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydGoodProvinceLcoal);
//		System.out.println(ltGoodProvinceLocal);
//		System.out.println(dxGoodProvinceLocal);
		
	}
	
	/**
	 * 测试函数
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.YFPath);
		result = result.replaceAll("\\.", " ").replaceAll(" ", ",");
		System.out.println(result);
	}
}
