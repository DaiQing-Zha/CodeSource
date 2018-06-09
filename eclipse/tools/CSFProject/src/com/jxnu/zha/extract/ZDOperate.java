package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class ZDOperate {

	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ��μ�ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ�μ�ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//���Ŵμ�ʡ��
	
	private final static String ydGoodProvince = "�ƶ�����:";
	private final static String ltGoodProvince = "��ͨ����:";
	private final static String dxGoodProvince = "��������:";
	private final static String ydOpenProvince = "�ƶ���ͨ:";
	
	private final static String splitStr1 = ",";
	private final static String splitStr2 = " ";
	 
	/**
	 * ʵ��
	 */
	private volatile static ZDOperate instance;
    /**
     * ���캯��ss
     */
	private ZDOperate(){
//        System.out.println("ZDOperate Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static ZDOperate getInstance(){
        if(instance==null){
            synchronized (ZDOperate.class){
                if(instance==null){
                    instance=new ZDOperate();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ʡ�ݷ���
	 */
	public void classifyProvince(){
		String result = FileUtil.readFileData(Config.ZDPath);
//		System.out.println(result);
		
		int ydOpenPos = StringUtil.findNumber(result, ydOpenProvince, 1);//һ��ʡ�ݵ�һ�γ��ֵ�λ��
		
		String ydGoodProvinceLcoal = result.substring(result.indexOf(ydGoodProvince) + ydGoodProvince.length(), result.indexOf(ltGoodProvince));
		String ltGoodProvinceLocal = result.substring(result.indexOf(ltGoodProvince) + ltGoodProvince.length(), result.indexOf(dxGoodProvince));
		String dxGoodProvinceLocal = result.substring(result.indexOf(dxGoodProvince) + dxGoodProvince.length(),ydOpenPos);
		
		
		YDGood = ydGoodProvinceLcoal.split(splitStr1);
		LTGood = ltGoodProvinceLocal.split(splitStr1);
		DXGood = dxGoodProvinceLocal.split(splitStr2);
		
//		for (int i = 0; i < YDGood.length; i++) {
//			System.out.print(YDGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < LTGood.length; i++) {
//			System.out.print(LTGood[i] + " ");
//		}
//		System.out.println("\n");
//		for (int i = 0; i < DXGood.length; i++) {
//			System.out.print(DXGood[i] + " ");
//		}
	}
	
	/**
	 * ���Ժ���
	 */
	public void testFun(){
		String result = FileUtil.readFileData(Config.ZDPath);
		System.out.println(result);
	}
}
