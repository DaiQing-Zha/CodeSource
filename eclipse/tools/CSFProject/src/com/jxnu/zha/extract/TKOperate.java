package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class TKOperate {
	
	public static String[] YDGood = {};//移动优质省份
	public static String[] YDOrdinary = {};//移动普通省份
	public static String[] YDInsufficient = {};//移动承载不足省份
	public static String[] YDShield = {};//移动屏蔽省份
	
	public static String[] LTGood = {};//联通优质省份
	public static String[] LTOrdinary = {};//联通普通省份
	public static String[] LTInsufficient = {};//联通承载不足省份
	public static String[] LTShield = {};//联通屏蔽省份
	
	public static String[] DXGood = {};//电信优质省份
	public static String[] DXOrdinary = {};//电信普通省份
	public static String[] DXInsufficient = {};//电信承载不足省份
	public static String[] DXShield = {};//电信屏蔽省份
	
	private final static String splitStr = " ";
	private final static String goodProvince = "一级:";
	private final static String secondLevelProvince = "二级:";
	private final static String thirdLevelProvince= "三级:";
	private final static String shielYDdProvince = "移动屏蔽:";
	private final static String shielLTdProvince = "联通屏蔽:";
	private final static String shieldDXProvince = "电信屏蔽:";
	private final static String ydOpen = "移动开通:";
	private final static String ltOpen = "联通开通:";
	private final static String dxOpen = "电信开通:";
	
	/**
	 * 实例
	 */
	private volatile static TKOperate instance;
    /**
     * 构造函数
     */
	private TKOperate(){
//        System.out.println("TKOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static TKOperate getInstance(){
        if(instance==null){
            synchronized (TKOperate.class){
                if(instance==null){
                    instance=new TKOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * 省份分类
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.TKPath);
//		System.out.println(result);
		
		int goodFirstPos = StringUtil.findNumber(result, goodProvince, 1);//一级省份第一次出现的位置
		int secondLevelFirstPos = StringUtil.findNumber(result, secondLevelProvince, 1);//二级省份第一次出现的位置
		int thirdLevelFirstPos = StringUtil.findNumber(result, thirdLevelProvince, 1);//承载不足省份第一次出现的位置
		int ydShieldPos = result.indexOf(shielYDdProvince);//移动屏蔽出现的位置
		
		int goodSecondPos = StringUtil.findNumber(result, goodProvince, 2);//一级省份第二次出现的位置
		int secondLevelSecondPos = StringUtil.findNumber(result, secondLevelProvince, 2);//二级省份第二次出现的位置
		int thirdLevelSecondPos = StringUtil.findNumber(result, thirdLevelProvince, 2);//承载不足省份第一次出现的位置
		int ltShieldPos = result.indexOf(shielLTdProvince);//联通屏蔽出现的位置
		
		int goodThirdPos = StringUtil.findNumber(result, goodProvince, 3);//一级省份第三次出现的位置
		int secondLevelThirdPos = StringUtil.findNumber(result, secondLevelProvince, 3);//二级省份第三次出现的位置
		int thirdLevelThirdPos = StringUtil.findNumber(result, thirdLevelProvince, 3);//承载不足省份第三次出现的位置
		int dxShieldPos = result.indexOf(shieldDXProvince);//电信屏蔽出现的位置
		
		int ydOpenPos = result.indexOf(ydOpen);
		int ltOpenPos = result.indexOf(ltOpen);
		int dxOpenPos = result.indexOf(dxOpen);
		
		String ydGoodProvince = result.substring(goodFirstPos + goodProvince.length(), secondLevelFirstPos);
		String ydOrdinaryProvince = result.substring(secondLevelFirstPos + secondLevelProvince.length(), thirdLevelFirstPos);
		String ydInsufficientProvince = result.substring(thirdLevelFirstPos + thirdLevelProvince.length(), ltOpenPos);
		String ydShieldProvince = result.substring(ydShieldPos + shielYDdProvince.length(), ltShieldPos);
		
		String ltGoodProvince = result.substring(goodSecondPos + goodProvince.length(), secondLevelSecondPos);
		String ltOrdianryProvince = result.substring(secondLevelSecondPos + secondLevelProvince.length(), thirdLevelSecondPos);
		String ltInsufficientProvince = result.substring(thirdLevelSecondPos + thirdLevelProvince.length(), dxOpenPos);
		String ltShieldProvince = result.substring(ltShieldPos + shielLTdProvince.length(), dxShieldPos);
	
		String dxGoodProvince = result.substring(goodThirdPos + goodProvince.length(), secondLevelThirdPos);
		String dxOrdianryProvince = result.substring(secondLevelThirdPos + secondLevelProvince.length(), thirdLevelThirdPos);
		String dxInsufficientProvince = result.substring(thirdLevelThirdPos + thirdLevelProvince.length(), result.length());
		String dxShieldProvince = result.substring(dxShieldPos + shieldDXProvince.length(), ydOpenPos);
		
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
	    
//		System.out.println(ydGoodProvince);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydGoodProvince);
//		System.out.println(ydOrdinaryProvince);
//		System.out.println(ydInsufficientProvince);
//		System.out.println(ydShieldProvince);
//		
//		System.out.println(ltGoodProvince);
//		System.out.println(ltOrdianryProvince);
//		System.out.println(ltInsufficientProvince);
//		System.out.println(ltShieldProvince);
//		
//		System.out.println(dxGoodProvince);
//		System.out.println(dxOrdianryProvince);
//		System.out.println(dxInsufficientProvince);
//		System.out.println(dxShieldProvince);
		
	}
	/**
	 * 测试函数
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.TKPath);
		System.out.println(result);
	}
}
