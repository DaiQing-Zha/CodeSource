package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class YJOperate {
	//�׽�Ҫע���еě]������ʡ�ݻ���ɶ�ȷֵ�
	public static String[] YDGood = {};//�ƶ�һ��ʡ��
	public static String[] YDOrdinary = {};//�ƶ�����ʡ��
	public static String[] YDInsufficient = {};//�ƶ����벻��
	public static String[] YDShield = {};//�ƶ�����ʡ��
	
	public static String[] LTGood = {};//��ͨһ��ʡ��
	public static String[] LTOrdinary = {};//��ͨ����ʡ��
	public static String[] LTInsufficient = {};//��ͨ���벻��
	public static String[] LTShield = {};//��ͨ����ʡ��
	
	public static String[] DXGood = {};//����һ��ʡ��
	public static String[] DXOrdinary = {};//���Ŷ���ʡ��
	public static String[] DXInsufficient = {};//���Ŵ��벻��
	public static String[] DXShield = {};//��������ʡ��
	
	private final static String splitStr = ",";
	private final static String goodProvince = "һ��ʡ��:";
	private final static String secondLevelProvince = "����ʡ��:";
	private final static String insufficientProvince= "���벻��:";
	private final static String shieldProvince = "����ʡ��:";
	private final static String ltCharset = "����ͨ��";
	private final static String dxCharset = "�����š�";
	
	/**
	 * ʵ��
	 */
	private volatile static YJOperate instance;
    /**
     * ���캯��
     */
	private YJOperate(){
//        System.out.println("YJOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
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
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		
		String result = FileUtil.readFileData(Config.YJPath);
//		System.out.println(result);
		
		int goodFirstPos = StringUtil.findNumber(result, goodProvince, 1);//һ��ʡ�ݵ�һ�γ��ֵ�λ��
		int secondLevelFirstPos = StringUtil.findNumber(result, secondLevelProvince, 1);//����ʡ�ݵ�һ�γ��ֵ�λ��
		int insufficientFirstPos = StringUtil.findNumber(result, insufficientProvince, 1);//���ز���ʡ�ݵ�һ�γ��ֵ�λ��
		int shieldFirstPos = StringUtil.findNumber(result, shieldProvince, 1);//����ʡ�ݵ�һ�γ��ֵ�λ��;
		
		int goodSecondPos = StringUtil.findNumber(result, goodProvince, 2);//һ��ʡ�ݵڶ��γ��ֵ�λ��
		int secondLevelSecondPos = StringUtil.findNumber(result, secondLevelProvince, 2);//����ʡ�ݵڶ��γ��ֵ�λ��
		int insufficientSecondPos = StringUtil.findNumber(result, insufficientProvince, 2);//���ز���ʡ�ݵڶ��γ��ֵ�λ��
		int shieldSecondPos = StringUtil.findNumber(result, shieldProvince, 2);//����ʡ�ݵڶ��γ��ֵ�λ��;
		
		int goodThirdPos = StringUtil.findNumber(result, goodProvince, 3);//һ��ʡ�ݵ����γ��ֵ�λ��
		int secondLevelThirdPos = StringUtil.findNumber(result, secondLevelProvince, 3);//����ʡ�ݵ����γ��ֵ�λ��
		int insufficientThirdPos = StringUtil.findNumber(result, insufficientProvince, 3);//���ز���ʡ�ݵ����γ��ֵ�λ��
		int shieldThirdPos = StringUtil.findNumber(result, shieldProvince, 3);//����ʡ�ݵ����γ��ֵ�λ��;
		
		int ltIndex = result.indexOf(ltCharset);//��ͨ���ֵ�λ��
		int dxIndex = result.indexOf(dxCharset);//���ų��ֵ�λ��
		
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
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.YJPath);
		System.out.println(result);
	}
}
