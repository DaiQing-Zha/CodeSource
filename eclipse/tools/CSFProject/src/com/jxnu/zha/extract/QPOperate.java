package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class QPOperate {
	
	public static String[] YDGood = {};//�ƶ�һ������ʡ��
	public static String[] YDOrdinary = {};//�ƶ���������ʡ��
	public static String[] YDShield = {};//�ƶ��޳���ʡ��
	
	public static String[] LTGood = {};//��ͨһ������ʡ��
	public static String[] LTOrdinary = {};//��ͨ��������ʡ��
	public static String[] LTShield = {};//��ͨ�޳���ʡ��
	
	public static String[] DXGood = {};//����һ������ʡ��
	public static String[] DXOrdinary = {};//���Ŷ�������ʡ��
	public static String[] DXShield = {};//�����޳���ʡ��
	
	private final static String wczCharset = "�޳���:";
	private final static String czbzCharset = "���ز���:";
	private final static String yjCharset = "һ������ʡ��:";
	private final static String ejCharset = "��������ʡ��:";
	private final static String dxCharset = "����";
	private final static String ltCharset = "��ͨ";
	private final static String splitStr = ",";
	/**
	 * ʵ��
	 */
	private volatile static QPOperate instance;
    /**
     * ���캯��
     */
	private QPOperate(){
//        System.out.println("Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
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
		int wczFirstPos = StringUtil.findNumber(result, wczCharset, 1);//�޳��ص�һ�γ��ֵ�λ��
		int czbzFirstPos = StringUtil.findNumber(result, czbzCharset, 1);//���ز����һ�γ��ֵ�λ��
		int yjFirstPos = StringUtil.findNumber(result, yjCharset, 1);//һ��������һ�γ��ֵ�λ��
		int ejFirstPos = StringUtil.findNumber(result, ejCharset, 1);//����������һ�γ��ֵ�λ��
		int wczSecondPos = StringUtil.findNumber(result, wczCharset, 2);//�޳��صڶ��γ��ֵ�λ��
		int czbzSecondPos = StringUtil.findNumber(result, czbzCharset, 2);//���ز���ڶ��γ��ֵ�λ��
		int yjSecondPos = StringUtil.findNumber(result, yjCharset, 2);//һ�������ڶ��γ��ֵ�λ��
		int ejSecondPos = StringUtil.findNumber(result, ejCharset, 2);//���������ڶ��γ��ֵ�λ��
		int wczThirdPos = StringUtil.findNumber(result, wczCharset, 3);//�޳��ص����γ��ֵ�λ��
		int czbzThirdPos = StringUtil.findNumber(result, czbzCharset, 3);//���ز���ڶ��γ��ֵ�λ��
		int yjThirdPos = StringUtil.findNumber(result, yjCharset, 3);//һ�����������γ��ֵ�λ��
		int ejThirdPos = StringUtil.findNumber(result, ejCharset, 3);//�������������γ��ֵ�λ��
		int dxPos = StringUtil.findNumber(result, dxCharset, 1);//���ų��ֵ�λ��
		int ltPos = StringUtil.findNumber(result, ltCharset, 1);//��ͨ���ֵ�λ��
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
