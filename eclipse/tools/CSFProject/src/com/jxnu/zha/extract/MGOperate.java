package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class MGOperate {
	
	public static String[] YDGood = {};//移动优质省份
	public static String[] YDOrdinary = {};//移动承载不足省份
	public static String[] YDShield = {};//移动无计费省份
	
	public static String[] LTGood = {};//联通优质省份
	public static String[] LTOrdinary = {};//联通承载不足省份
	public static String[] LTShield = {};//联通无计费省份
	
	public static String[] DXGood = {};//电信优质省份
	public static String[] DXOrdinary = {};//电信承载不足省份
	public static String[] DXShield = {};//电信无计费省份
	
	private final static String ydCharset = "移动	";
	private final static String ltCharset = "联通	";
	private final static String dxCharset = "电信	";
	private final static String yysCharset = "运营商";
	private final static String splitStr = "、";
	
	
	/**
	 * 实例
	 */
	private volatile static MGOperate instance;
    /**
     * 构造函数
     */
	private MGOperate(){
//        System.out.println("Singleton has loaded-----------------------------------------");
    }
    /**
     * 获取实例
     * @return
     */
	public static MGOperate getInstance(){
        if(instance==null){
            synchronized (MGOperate.class){
                if(instance==null){
                    instance=new MGOperate();
                }
            }
        }
        return instance;
    }
	
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.MGPath);
//		System.out.println(result);
		
		int ydFirstPos = StringUtil.findNumber(result, ydCharset, 1);//移动第一次出现的位置
		int ltFirstPos = StringUtil.findNumber(result, ltCharset, 1);//联通第一次出现的位置
		int dxFirstPos = StringUtil.findNumber(result, dxCharset, 1);//电信第一次出现的位置
		int ydSecondPos = StringUtil.findNumber(result, ydCharset, 2);//移动第二次出现的位置
		int ltSecondPos = StringUtil.findNumber(result, ltCharset, 2);//联通第二次出现的位置
		int dxSecondPos = StringUtil.findNumber(result, dxCharset, 2);//电信第二次出现的位置
		int ydThirdPos = StringUtil.findNumber(result, ydCharset, 3);//移动第三次出现的位置
		int ltThirdPos = StringUtil.findNumber(result, ltCharset, 3);//联通第三次出现的位置
		int dxThirdPos = StringUtil.findNumber(result, dxCharset, 3);//电信第三次出现的位置
		int yysFirstPos = StringUtil.findNumber(result, yysCharset, 1);//运营商第一次出现的位置
		int yysSecondPos = StringUtil.findNumber(result, yysCharset, 2);//运营商第二次出现的位置
		int yysThirdPos = StringUtil.findNumber(result, yysCharset, 3);//运营商第三次出现的位置
		String ydNoBillingProvince = result.substring(ydFirstPos + ydCharset.length(), ltFirstPos);
		String ltNoBillingProvince = result.substring(ltFirstPos + ltCharset.length(), dxFirstPos);
		String dxNoBillingProvince = result.substring(dxFirstPos + dxCharset.length(), yysSecondPos);
		String ydGoodProvince = result.substring(ydSecondPos + ydCharset.length(), ltSecondPos);
		String ltGoodProvince = result.substring(ltSecondPos + ltCharset.length(), dxSecondPos);
		String dxGoodProvince = result.substring(dxSecondPos + dxCharset.length(), yysThirdPos);
		String ydInsufficientProvince = result.substring(ydThirdPos + ydCharset.length(), ltThirdPos);
		String ltInsufficientProvince = result.substring(ltThirdPos + ltCharset.length(), dxThirdPos);
		String dxInsufficientProvince = result.substring(dxThirdPos + dxCharset.length(), result.length());
		YDGood = ydGoodProvince.split(splitStr);
		YDOrdinary = ydInsufficientProvince.split(splitStr);
		YDShield = ydNoBillingProvince.split(splitStr);
		LTGood = ltGoodProvince.split(splitStr);
		LTOrdinary = ltInsufficientProvince.split(splitStr);
		LTShield = ltNoBillingProvince.split(splitStr);
		DXGood = dxGoodProvince.split(splitStr);
		DXOrdinary = dxInsufficientProvince.split(splitStr);
		DXShield = dxNoBillingProvince.split(splitStr);
//		System.out.println(ydNoBillingProvince);
//		System.out.println(ltNoBillingProvince);
//		System.out.println(dxNoBillingProvince);
//		System.out.println(ydGoodProvince);
//		System.out.println(ltGoodProvince);
//		System.out.println(dxGoodProvince);
//		System.out.println(ydInsufficientProvince);
//		System.out.println(ltInsufficientProvince);
//		System.out.println(dxInsufficientProvince);
	}
}
