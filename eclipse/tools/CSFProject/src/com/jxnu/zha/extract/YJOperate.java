package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class YJOperate {
	//易接要注意有的沒有屏蔽省份或者啥等分的
	public static String[] YDGood = {};//移动一级省份
	public static String[] YDOrdinary = {};//移动二级省份
	public static String[] YDInsufficient = {};//移动代码不足
	public static String[] YDShield = {};//移动屏蔽省份
	
	public static String[] LTGood = {};//联通一级省份
	public static String[] LTOrdinary = {};//联通二级省份
	public static String[] LTInsufficient = {};//联通代码不足
	public static String[] LTShield = {};//联通屏蔽省份
	
	public static String[] DXGood = {};//电信一级省份
	public static String[] DXOrdinary = {};//电信二级省份
	public static String[] DXInsufficient = {};//电信代码不足
	public static String[] DXShield = {};//电信屏蔽省份
	
	private final static String splitStr = ",";
	private final static String goodProvince = "一级省份:";
	private final static String secondLevelProvince = "二级省份:";
	private final static String insufficientProvince= "代码不足:";
	private final static String shieldProvince = "屏蔽省份:";
	private final static String ltCharset = "【联通】";
	private final static String dxCharset = "【电信】";
	
	/**
	 * 实例
	 */
	private volatile static YJOperate instance;
    /**
     * 构造函数
     */
	private YJOperate(){
//        System.out.println("YJOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static YJOperate getInstance(){
        if(instance==null){
            synchronized (YJOperate.class){
                if(instance==null){
                    instance=new YJOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		
		String result = FileUtil.readFileData(Config.YJPath);
//		System.out.println(result);
		
		int goodFirstPos = StringUtil.findNumber(result, goodProvince, 1);//一级省份第一次出现的位置
		int secondLevelFirstPos = StringUtil.findNumber(result, secondLevelProvince, 1);//二级省份第一次出现的位置
		int insufficientFirstPos = StringUtil.findNumber(result, insufficientProvince, 1);//承载不足省份第一次出现的位置
		int shieldFirstPos = StringUtil.findNumber(result, shieldProvince, 1);//屏蔽省份第一次出现的位置;
		
		int goodSecondPos = StringUtil.findNumber(result, goodProvince, 2);//一级省份第二次出现的位置
		int secondLevelSecondPos = StringUtil.findNumber(result, secondLevelProvince, 2);//二级省份第二次出现的位置
		int insufficientSecondPos = StringUtil.findNumber(result, insufficientProvince, 2);//承载不足省份第二次出现的位置
		int shieldSecondPos = StringUtil.findNumber(result, shieldProvince, 2);//屏蔽省份第二次出现的位置;
		
		int goodThirdPos = StringUtil.findNumber(result, goodProvince, 3);//一级省份第三次出现的位置
		int secondLevelThirdPos = StringUtil.findNumber(result, secondLevelProvince, 3);//二级省份第三次出现的位置
		int insufficientThirdPos = StringUtil.findNumber(result, insufficientProvince, 3);//承载不足省份第三次出现的位置
		int shieldThirdPos = StringUtil.findNumber(result, shieldProvince, 3);//屏蔽省份第三次出现的位置;
		
		int ltIndex = result.indexOf(ltCharset);//联通出现的位置
		int dxIndex = result.indexOf(dxCharset);//电信出现的位置
		
//		System.out.println("insufficientSecondPos = " + insufficientSecondPos + " shieldSecondPos = " + shieldSecondPos);
		
		String ydGoodProvince = result.substring(goodFirstPos + goodProvince.length(), secondLevelFirstPos);
		String ydOrdinaryProvince = result.substring(secondLevelFirstPos + secondLevelProvince.length(), insufficientFirstPos);
		String ydInsufficientProvince = result.substring(insufficientFirstPos + insufficientProvince.length(), shieldFirstPos);
		String ydShieldProvince = result.substring(shieldFirstPos + shieldProvince.length(), ltIndex);
		
		String ltGoodProvince = result.substring(goodSecondPos + goodProvince.length(), secondLevelSecondPos);
		String ltOrdianryProvince = result.substring(secondLevelSecondPos + secondLevelProvince.length(), insufficientSecondPos);
		String ltInsufficientProvince = result.substring(insufficientSecondPos + insufficientProvince.length(), shieldSecondPos);
		String ltShieldProvince = result.substring(shieldSecondPos + shieldProvince.length(), dxIndex);
		
		String dxGoodProvince = result.substring(goodThirdPos + goodProvince.length(), secondLevelThirdPos);
		String dxOrdianryProvince = result.substring(secondLevelThirdPos + secondLevelProvince.length(), insufficientThirdPos);
		String dxInsufficientProvince = result.substring(insufficientThirdPos + insufficientProvince.length(), shieldThirdPos);
		String dxShieldProvince = result.substring(shieldThirdPos + shieldProvince.length(), result.length());
		
		YDGood = ydGoodProvince.split(splitStr);
		YDOrdinary = ydOrdinaryProvince.split(splitStr);
		YDInsufficient = ydInsufficientProvince.split(splitStr);
		YDShield = ydShieldProvince.split(splitStr);
		
		LTGood = ltGoodProvince.split(splitStr);
		LTOrdinary = ltOrdianryProvince.split(splitStr);
		LTInsufficient = ltInsufficientProvince.split(splitStr);
		LTShield = ltShieldProvince.split(splitStr);
		
		DXGood = dxGoodProvince.split(splitStr);
		DXOrdinary = dxOrdianryProvince.split(splitStr);
		DXInsufficient = dxInsufficientProvince.split(splitStr);
		DXShield = dxShieldProvince.split(splitStr);
		
//		for (int i = 0; i < YDGood.length; i++) {
//			System.out.print(YDGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < YDOrdinary.length; i++) {
//			System.out.print(YDOrdinary[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < YDInsufficient.length; i++) {
//			System.out.print(YDInsufficient[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < LTGood.length; i++) {
//			System.out.print(LTGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < LTOrdinary.length; i++) {
//			System.out.print(LTOrdinary[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < LTInsufficient.length; i++) {
//			System.out.print(LTInsufficient[i] + " ");
//		}
	}
	
	/**
	 * 测试函数
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.YJPath);
		System.out.println(result);
	}
}
