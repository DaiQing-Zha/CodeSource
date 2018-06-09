package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class SAOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ���ͨʡ��
	public static String[] YDShield = {};//�ƶ�����ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ��ͨʡ��
	public static String[] LTShield = {};//��ͨ����ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//������ͨʡ��
	public static String[] DXShield = {};//��������ʡ��
	
	private final static String ydGoodProvince = "�ƶ�����ʡ��:";
	private final static String ydOrdinaryProvince = "�ƶ���ͨʡ��:";
	private final static String ydShieldProvince = "�ƶ�����ʡ��:";//ע������ֶ�SOS
	
	private final static String ltGoodProvince = "��ͨ����ʡ��:";
	private final static String ltOrdinaryProvince = "��ͨ��ͨʡ��:";
	private final static String ltShieldProvince = "��ͨ����ʡ��:";
	
	private final static String dxGoodProvince = "��������ʡ��:";
	private final static String dxOrdinaryProvince = "������ͨʡ��:";
	private final static String dxShieldProvince = "��������ʡ��:";
	
	private final static String splitStr = "��";
	/**
	 * ʵ��
	 */
	private volatile static SAOperate instance;
    /**
     * ���캯��
     */
	private SAOperate(){
//        System.out.println("SAOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static SAOperate getInstance(){
        if(instance==null){
            synchronized (SAOperate.class){
                if(instance==null){
                    instance=new SAOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.SAPath);
//		System.out.println(result);
		
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(), result.indexOf(dxOrdinaryProvince));
		String dxOrdinaryProvinceLocal = result.substring(result.indexOf(dxOrdinaryProvince) + dxOrdinaryProvince.length(), result.indexOf(dxShieldProvince));
		String dxShieldProvinceLocal = result.substring(result.indexOf(dxShieldProvince) + dxShieldProvince.length(), result.indexOf(ydGoodProvince));
		String ydGoodProvinceLocal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ydOrdinaryProvince));
		String ydOrdinaryProvinceLocal = result.substring(result.indexOf(ydOrdinaryProvince) + ydOrdinaryProvince.length(), result.indexOf(ydShieldProvince));
		String ydShieldProvinceLocal = result.substring(result.indexOf(ydShieldProvince) + ydShieldProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLcoal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(ltOrdinaryProvince));
		String ltOrdinaryProvinceLocal = result.substring(result.indexOf(ltOrdinaryProvince) + ltOrdinaryProvince.length(), result.indexOf(ltShieldProvince));
		String ltShieldProvinceLocal = result.substring(result.indexOf(ltShieldProvince) + ltShieldProvince.length(), result.length());

		YDGood = ydGoodProvinceLocal.split(splitStr);
		YDOrdinary = ydOrdinaryProvinceLocal.split(splitStr);
		YDShield = ydShieldProvinceLocal.split(splitStr);
		LTGood = ltGoodProvinceLcoal.split(splitStr);
		LTOrdinary = ltOrdinaryProvinceLocal.split(splitStr);
		LTShield = ltShieldProvinceLocal.split(splitStr);
		DXGood = dxGoodProvinceLocal.split(splitStr);
		DXOrdinary = dxOrdinaryProvinceLocal.split(splitStr);
		DXShield = dxShieldProvinceLocal.split(splitStr);
	    
//		System.out.println(ydGoodProvinceLocal);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydOrdinaryProvinceLocal);
//		System.out.println(ydShieldProvinceLocal);
//		System.out.println(ltGoodProvinceLcoal);
//		System.out.println(ltOrdinaryProvinceLocal);
//		System.out.println(ltShieldProvinceLocal);
//		System.out.println(dxGoodProvinceLocal);
//		System.out.println(dxOrdinaryProvinceLocal);
//		System.out.println(dxShieldProvinceLocal);
		
	}
	/**
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.SAPath);
		System.out.println(result);
	}
}
