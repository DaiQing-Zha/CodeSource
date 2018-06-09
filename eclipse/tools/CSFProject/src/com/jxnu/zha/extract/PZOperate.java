package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class PZOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ��μ�ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ�μ�ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//���Ŵμ�ʡ��
	
	private final static String ydGoodProvince = "�ƶ�:";
	private final static String ltGoodProvince = "��ͨ:";
	private final static String dxGoodProvince = "����:";
	private final static String splitStr = " ";
	/**
	 * ʵ��
	 */
	private volatile static PZOperate instance;
    /**
     * ���캯��
     */
	private PZOperate(){
//        System.out.println("PZOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static PZOperate getInstance(){
        if(instance==null){
            synchronized (PZOperate.class){
                if(instance==null){
                    instance=new PZOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.PZPath);
//		System.out.println(result);
		
		String ydGoodProvinceLcoal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(), result.length());
		
		YDGood = ydGoodProvinceLcoal.split(splitStr);
		LTGood = ltGoodProvinceLocal.split(splitStr);
		DXGood = dxGoodProvinceLocal.split(splitStr);
		
//		System.out.println(ydGoodProvinceLcoal);
//		for (int i = 0; i < YDGood.length; i++) {
//	    	System.out.print(YDGood[i] + " ");
//		}
//		System.out.println(ydGoodProvinceLcoal);
//		System.out.println(ltGoodProvinceLocal);
//		System.out.println(dxGoodProvinceLocal);
		
	}
	
	/**
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.PZPath);
		System.out.println(result);
	}
}
