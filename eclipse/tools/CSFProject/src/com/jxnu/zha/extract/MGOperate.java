package com.jxnu.zha.extract;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.utils.FileUtil;
import com.jxnu.zha.utils.StringUtil;

public class MGOperate {
	
	public static String[] YDGood = {};//�ƶ�����ʡ��
	public static String[] YDOrdinary = {};//�ƶ����ز���ʡ��
	public static String[] YDShield = {};//�ƶ��޼Ʒ�ʡ��
	
	public static String[] LTGood = {};//��ͨ����ʡ��
	public static String[] LTOrdinary = {};//��ͨ���ز���ʡ��
	public static String[] LTShield = {};//��ͨ�޼Ʒ�ʡ��
	
	public static String[] DXGood = {};//��������ʡ��
	public static String[] DXOrdinary = {};//���ų��ز���ʡ��
	public static String[] DXShield = {};//�����޼Ʒ�ʡ��
	
	private final static String ydCharset = "�ƶ�	";
	private final static String ltCharset = "��ͨ	";
	private final static String dxCharset = "����	";
	private final static String yysCharset = "��Ӫ��";
	private final static String splitStr = "��";
	
	
	/**
	 * ʵ��
	 */
	private volatile static MGOperate instance;
    /**
     * ���캯��
     */
	private MGOperate(){
//        System.out.println("Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
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
		
		int ydFirstPos = StringUtil.findNumber(result, ydCharset, 1);//�ƶ���һ�γ��ֵ�λ��
		int ltFirstPos = StringUtil.findNumber(result, ltCharset, 1);//��ͨ��һ�γ��ֵ�λ��
		int dxFirstPos = StringUtil.findNumber(result, dxCharset, 1);//���ŵ�һ�γ��ֵ�λ��
		int ydSecondPos = StringUtil.findNumber(result, ydCharset, 2);//�ƶ��ڶ��γ��ֵ�λ��
		int ltSecondPos = StringUtil.findNumber(result, ltCharset, 2);//��ͨ�ڶ��γ��ֵ�λ��
		int dxSecondPos = StringUtil.findNumber(result, dxCharset, 2);//���ŵڶ��γ��ֵ�λ��
		int ydThirdPos = StringUtil.findNumber(result, ydCharset, 3);//�ƶ������γ��ֵ�λ��
		int ltThirdPos = StringUtil.findNumber(result, ltCharset, 3);//��ͨ�����γ��ֵ�λ��
		int dxThirdPos = StringUtil.findNumber(result, dxCharset, 3);//���ŵ����γ��ֵ�λ��
		int yysFirstPos = StringUtil.findNumber(result, yysCharset, 1);//��Ӫ�̵�һ�γ��ֵ�λ��
		int yysSecondPos = StringUtil.findNumber(result, yysCharset, 2);//��Ӫ�̵ڶ��γ��ֵ�λ��
		int yysThirdPos = StringUtil.findNumber(result, yysCharset, 3);//��Ӫ�̵����γ��ֵ�λ��
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
