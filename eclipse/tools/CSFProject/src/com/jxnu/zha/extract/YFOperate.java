package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;

public class YFOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	
	private final static String ydGoodProvince = "�ƶ�:";
	private final static String ltGoodProvince = "��ͨ��ֻ����20Ԫ��:";
	private final static String dxGoodProvince = "���ţ�ֻ����20Ԫ��:";
	private final static String splitStr = ",";
	
	/**
	 * ʵ��
	 */
	private volatile static YFOperate instance;
    /**
     * ���캯��
     */
	private YFOperate(){
//        System.out.println("YFOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static YFOperate getInstance(){
        if(instance==null){
            synchronized (YFOperate.class){
                if(instance==null){
                    instance=new YFOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.YFPath);
		result = result.replaceAll("\\.", " ").replaceAll(" ", ",");
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
		String result = FileUtil.readFileData(Config.YFPath);
		result = result.replaceAll("\\.", " ").replaceAll(" ", ",");
		System.out.println(result);
	}
}
