package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class PZOperate {
	
	public static String[] YDGood = {};//移动优质省份
	public static String[] YDOrdinary = {};//移动次级省份
	
	public static String[] LTGood = {};//联通优质省份
	public static String[] LTOrdinary = {};//联通次级省份
	
	public static String[] DXGood = {};//电信优质省份
	public static String[] DXOrdinary = {};//电信次级省份
	
	private final static String ydGoodProvince = "移动:";
	private final static String ltGoodProvince = "联通:";
	private final static String dxGoodProvince = "电信:";
	private final static String splitStr = " ";
	/**
	 * 实例
	 */
	private volatile static PZOperate instance;
    /**
     * 构造函数
     */
	private PZOperate(){
//        System.out.println("PZOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static PZOperate getInstance(){
        if(instance==null){
            synchronized (PZOperate.class){
                if(instance==null){
                    instance=new PZOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.PZPath);
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
		String result = FileUtil.readFileData(Config.PZPath);
		System.out.println(result);
	}
}
