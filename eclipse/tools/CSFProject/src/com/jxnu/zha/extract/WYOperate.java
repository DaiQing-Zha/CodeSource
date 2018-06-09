package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class WYOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ���ͨʡ��
	public static String[] YDShield = {};//�ƶ�����ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ��ͨʡ��
	public static String[] LTShield = {};//��ͨ����ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//������ͨʡ��
	public static String[] DXShield = {};//��������ʡ��
	
	private final static String ydGoodProvince = "�ƶ���ͨ�����ʣ�:";
	private final static String ydOrdinaryProvince = "�ƶ���ͨ��һ�㣩:";
	private final static String ydShieldProvince = "�ƶ�����:";
	
	private final static String ltGoodProvince = "��ͨ��ͨ�����ʣ�:";
	private final static String ltOrdinaryProvince = "��ͨ��ͨ��һ�㣩:";
	private final static String ltShieldProvince = "��ͨ����:";
	
	private final static String dxGoodProvince = "���ſ�ͨ�����ʣ�:";
	private final static String dxOrdinaryProvince = "���ſ�ͨ��һ�㣩:";
	private final static String dxShieldProvince = "��������:";
	
	private final static String splitStr = ",";
	
	/**
	 * ʵ��
	 */
	private volatile static WYOperate instance;
    /**
     * ���캯��
     */
	private WYOperate(){
//        System.out.println("WYOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static WYOperate getInstance(){
        if(instance==null){
            synchronized (WYOperate.class){
                if(instance==null){
                    instance=new WYOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.WYPath);
//		System.out.println(result);
		String ydGoodProvinceLocal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ydOrdinaryProvince));
		String ydOrdinaryProvinceLocal = result.substring(result.indexOf(ydOrdinaryProvince) + ydOrdinaryProvince.length(), result.indexOf(ydShieldProvince));
		String ydShieldProvinceLocal = result.substring(result.indexOf(ydShieldProvince) + ydShieldProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(ltOrdinaryProvince));
		String ltOrdinaryProvinceLocal = result.substring(result.indexOf(ltOrdinaryProvince) + ltOrdinaryProvince.length(), result.indexOf(ltShieldProvince));
		String ltShieldProvinceLocal = result.substring(result.indexOf(ltShieldProvince) + ltShieldProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(), result.indexOf(dxOrdinaryProvince));
		String dxOrdinaryProvinceLocal = result.substring(result.indexOf(dxOrdinaryProvince) + dxOrdinaryProvince.length(), result.indexOf(dxShieldProvince));
		String dxShieldProvinceLocal = result.substring(result.indexOf(dxShieldProvince) + dxShieldProvince.length(), result.length());
		
		YDGood = ydGoodProvinceLocal.split(splitStr);
		YDOrdinary = ydOrdinaryProvinceLocal.split(splitStr);
		YDShield = ydShieldProvinceLocal.split(splitStr);
		LTGood = ltGoodProvinceLocal.split(splitStr);
		LTOrdinary = ltOrdinaryProvinceLocal.split(splitStr);
		LTShield = ltShieldProvinceLocal.split(splitStr);
		DXGood = dxGoodProvinceLocal.split(splitStr);
		DXOrdinary = dxOrdinaryProvinceLocal.split(splitStr);
		DXShield = dxShieldProvinceLocal.split(splitStr);
	    
//		System.out.println(ydGoodProvinceLocal);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydGoodProvinceLocal);
//		System.out.println(ydOrdinaryProvinceLocal);
//		System.out.println(ydShieldProvinceLocal);
//		
//		System.out.println(ltGoodProvinceLocal);
//		System.out.println(ltOrdinaryProvinceLocal);
//		System.out.println(ltShieldProvinceLocal);
//		
//		System.out.println(dxGoodProvinceLocal);
//		System.out.println(dxOrdinaryProvinceLocal);
//		System.out.println(dxShieldProvinceLocal);
		
	}
	/**
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.WYPath);
		System.out.println(result);
	}
}
