package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class QPOperate {
	
	public static String[] YDGood = {};//移动一级加量省份
	public static String[] YDOrdinary = {};//移动二级加量省份
	public static String[] YDShield = {};//移动无承载省份
	
	public static String[] LTGood = {};//联通一级加量省份
	public static String[] LTOrdinary = {};//联通二级加量省份
	public static String[] LTShield = {};//联通无承载省份
	
	public static String[] DXGood = {};//电信一级加量省份
	public static String[] DXOrdinary = {};//电信二级加量省份
	public static String[] DXShield = {};//电信无承载省份
	
	private final static String wczCharset = "无承载:";
	private final static String czbzCharset = "承载不足:";
	private final static String yjCharset = "一级加量省份:";
	private final static String ejCharset = "二级加量省份:";
	private final static String dxCharset = "电信";
	private final static String ltCharset = "联通";
	private final static String splitStr = ",";
	/**
	 * 实例
	 */
	private volatile static QPOperate instance;
    /**
     * 构造函数
     */
	private QPOperate(){
//        System.out.println("Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static QPOperate getInstance(){
        if(instance==null){
            synchronized (QPOperate.class){
                if(instance==null){
                    instance=new QPOperate();
                }
            }
        }
        return instance;
    }
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.QPPath);
//		System.out.println(result);
		int wczFirstPos = StringUtil.findNumber(result, wczCharset, 1);//无承载第一次出现的位置
		int czbzFirstPos = StringUtil.findNumber(result, czbzCharset, 1);//承载不足第一次出现的位置
		int yjFirstPos = StringUtil.findNumber(result, yjCharset, 1);//一级加量第一次出现的位置
		int ejFirstPos = StringUtil.findNumber(result, ejCharset, 1);//二级加量第一次出现的位置
		int wczSecondPos = StringUtil.findNumber(result, wczCharset, 2);//无承载第二次出现的位置
		int czbzSecondPos = StringUtil.findNumber(result, czbzCharset, 2);//承载不足第二次出现的位置
		int yjSecondPos = StringUtil.findNumber(result, yjCharset, 2);//一级加量第二次出现的位置
		int ejSecondPos = StringUtil.findNumber(result, ejCharset, 2);//二级加量第二次出现的位置
		int wczThirdPos = StringUtil.findNumber(result, wczCharset, 3);//无承载第三次出现的位置
		int czbzThirdPos = StringUtil.findNumber(result, czbzCharset, 3);//承载不足第二次出现的位置
		int yjThirdPos = StringUtil.findNumber(result, yjCharset, 3);//一级加量第三次出现的位置
		int ejThirdPos = StringUtil.findNumber(result, ejCharset, 3);//二级加量第三次出现的位置
		int dxPos = StringUtil.findNumber(result, dxCharset, 1);//电信出现的位置
		int ltPos = StringUtil.findNumber(result, ltCharset, 1);//联通出现的位置
		String YDUnbearableLocal = result.substring(wczFirstPos + wczCharset.length(), czbzFirstPos);
		String YDGoodLocal = result.substring(yjFirstPos + yjCharset.length(), ejFirstPos);
		String YDOrdinaryLocal = result.substring(ejFirstPos + ejCharset.length(), dxPos);
		String DXUnbearableLocal = result.substring(wczSecondPos + wczCharset.length(), czbzSecondPos);
		String DXGoodLocal = result.substring(yjSecondPos + yjCharset.length(), ejSecondPos);
		String DXOrdinaryLocal = result.substring(ejSecondPos + ejCharset.length(), ltPos);
		String LTUnbearableLocal = result.substring(wczThirdPos + wczCharset.length(), czbzThirdPos);
		String LTGoodLocal = result.substring(yjThirdPos + yjCharset.length(), ejThirdPos);
		String LTOrdinaryLcoal = result.substring(ejThirdPos + ejCharset.length(), result.length());
		
		YDGood = YDGoodLocal.split(splitStr);
		YDOrdinary = YDOrdinaryLocal.split(splitStr);
		YDShield = YDUnbearableLocal.split(splitStr);
		LTGood = LTGoodLocal.split(splitStr);
		LTOrdinary = LTOrdinaryLcoal.split(splitStr);
		LTShield = LTUnbearableLocal.split(splitStr);
		DXGood = DXGoodLocal.split(splitStr);
		DXOrdinary = DXOrdinaryLocal.split(splitStr);
		DXShield = DXUnbearableLocal.split(splitStr);
//		System.out.println(YDUnbearable);
//		System.out.println(YDGoodLocal);
//		System.out.println(YDOrdinary);
//		
//		System.out.println(DXUnbearable);
//		System.out.println(DXGoodLocal);
//		System.out.println(DXOrdinary);
//		
//		System.out.println(LTUnbearable);
//		System.out.println(LTGoodLocal);
//		System.out.println(LTOrdinary);
	}
}
