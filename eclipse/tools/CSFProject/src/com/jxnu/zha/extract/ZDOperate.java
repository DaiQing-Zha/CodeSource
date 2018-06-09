package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class ZDOperate {

	public static String[] YDGood = {};//移动优质省份
	public static String[] YDOrdinary = {};//移动次级省份
	
	public static String[] LTGood = {};//联通优质省份
	public static String[] LTOrdinary = {};//联通次级省份
	
	public static String[] DXGood = {};//电信优质省份
	public static String[] DXOrdinary = {};//电信次级省份
	
	private final static String ydGoodProvince = "移动优质:";
	private final static String ltGoodProvince = "联通优质:";
	private final static String dxGoodProvince = "电信优质:";
	private final static String ydOpenProvince = "移动开通:";
	
	private final static String splitStr1 = ",";
	private final static String splitStr2 = " ";
	 
	/**
	 * 实例
	 */
	private volatile static ZDOperate instance;
    /**
     * 构造函数ss
     */
	private ZDOperate(){
//        System.out.println("ZDOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static ZDOperate getInstance(){
        if(instance==null){
            synchronized (ZDOperate.class){
                if(instance==null){
                    instance=new ZDOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.ZDPath);
//		System.out.println(result);
		
		int ydOpenPos = StringUtil.findNumber(result, ydOpenProvince, 1);//一级省份第一次出现的位置
		
		String ydGoodProvinceLcoal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(),ydOpenPos);
		
		
		YDGood = ydGoodProvinceLcoal.split(splitStr1);
		LTGood = ltGoodProvinceLocal.split(splitStr1);
		DXGood = dxGoodProvinceLocal.split(splitStr2);
		
//		for (int i = 0; i < YDGood.length; i++) {
//			System.out.print(YDGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < LTGood.length; i++) {
//			System.out.print(LTGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < DXGood.length; i++) {
//			System.out.print(DXGood[i] + " ");
//		}
	}
	
	/**
	 * 测试函数
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.ZDPath);
		System.out.println(result);
	}
}
