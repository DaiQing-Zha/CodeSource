package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class TKOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ���ͨʡ��
	public static String[] YDInsufficient = {};//�ƶ����ز���ʡ��
	public static String[] YDShield = {};//�ƶ�����ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ��ͨʡ��
	public static String[] LTInsufficient = {};//��ͨ���ز���ʡ��
	public static String[] LTShield = {};//��ͨ����ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//������ͨʡ��
	public static String[] DXInsufficient = {};//���ų��ز���ʡ��
	public static String[] DXShield = {};//��������ʡ��
	
	private final static String splitStr = " ";
	private final static String goodProvince = "һ��:";
	private final static String secondLevelProvince = "����:";
	private final static String thirdLevelProvince= "����:";
	private final static String shielYDdProvince = "�ƶ�����:";
	private final static String shielLTdProvince = "��ͨ����:";
	private final static String shieldDXProvince = "��������:";
	private final static String ydOpen = "�ƶ���ͨ:";
	private final static String ltOpen = "��ͨ��ͨ:";
	private final static String dxOpen = "���ſ�ͨ:";
	
	/**
	 * ʵ��
	 */
	private volatile static TKOperate instance;
    /**
     * ���캯��
     */
	private TKOperate(){
//        System.out.println("TKOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
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
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.TKPath);
//		System.out.println(result);
		
		int goodFirstPos = StringUtil.findNumber(result, goodProvince, 1);//һ��ʡ�ݵ�һ�γ��ֵ�λ��
		int secondLevelFirstPos = StringUtil.findNumber(result, secondLevelProvince, 1);//����ʡ�ݵ�һ�γ��ֵ�λ��
		int thirdLevelFirstPos = StringUtil.findNumber(result, thirdLevelProvince, 1);//���ز���ʡ�ݵ�һ�γ��ֵ�λ��
		int ydShieldPos = result.indexOf(shielYDdProvince);//�ƶ����γ��ֵ�λ��
		
		int goodSecondPos = StringUtil.findNumber(result, goodProvince, 2);//һ��ʡ�ݵڶ��γ��ֵ�λ��
		int secondLevelSecondPos = StringUtil.findNumber(result, secondLevelProvince, 2);//����ʡ�ݵڶ��γ��ֵ�λ��
		int thirdLevelSecondPos = StringUtil.findNumber(result, thirdLevelProvince, 2);//���ز���ʡ�ݵ�һ�γ��ֵ�λ��
		int ltShieldPos = result.indexOf(shielLTdProvince);//��ͨ���γ��ֵ�λ��
		
		int goodThirdPos = StringUtil.findNumber(result, goodProvince, 3);//һ��ʡ�ݵ����γ��ֵ�λ��
		int secondLevelThirdPos = StringUtil.findNumber(result, secondLevelProvince, 3);//����ʡ�ݵ����γ��ֵ�λ��
		int thirdLevelThirdPos = StringUtil.findNumber(result, thirdLevelProvince, 3);//���ز���ʡ�ݵ����γ��ֵ�λ��
		int dxShieldPos = result.indexOf(shieldDXProvince);//�������γ��ֵ�λ��
		
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
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.TKPath);
		System.out.println(result);
	}
}
