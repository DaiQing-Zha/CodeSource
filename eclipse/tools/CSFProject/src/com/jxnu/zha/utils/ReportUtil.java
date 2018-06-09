package com.jxnu.zha.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jxnu.zha.config.Config;
import com.jxnu.zha.extract.JMOperate;
import com.jxnu.zha.extract.MGOperate;
import com.jxnu.zha.extract.PZOperate;
import com.jxnu.zha.extract.QPOperate;
import com.jxnu.zha.extract.SAOperate;
import com.jxnu.zha.extract.SFOperate;
import com.jxnu.zha.extract.TKOperate;
import com.jxnu.zha.extract.WYOperate;
import com.jxnu.zha.extract.XMOperate;
import com.jxnu.zha.extract.YFOperate;
import com.jxnu.zha.extract.YJOperate;
import com.jxnu.zha.extract.ZDOperate;

public class ReportUtil {
	//�����У�����У��Ϻ��У������У��ӱ�ʡ��ɽ��ʡ������ʡ������ʡ��������ʡ������ʡ���㽭ʡ������ʡ������ʡ������ʡ
	//ɽ��ʡ������ʡ������ʡ������ʡ���㶫ʡ������ʡ���Ĵ�ʡ������ʡ������ʡ������ʡ������ʡ���ຣʡ��̨��ʡ
	//���ɹ�������������׳�������������������������Ļ������������½�ά���������������ر��������������ر���������
	
	private static final int TYPE_YD_GOOD = 0;//�ƶ�����
	private static final int TYPE_LT_GOOD = 1;//��ͨ����
	private static final int TYPE_DX_GOOD = 2;//��������
	private static final int TYPE_ALL_GOOD = 3;//�ۺ�����
	private static final int TYPE_ALL_EJ = 4;//�����ۺ϶���
	private static final int TYPE_YD_SHIELD = 5;//�ƶ�����
	private static final int TYPE_LT_SHIELD = 6;//��ͨ����
	private static final int TYPE_DX_SHIELD = 7;//��������
	
	private int mStyle = Config.STYLE_ONE;
	
	List<String> mLstProvince = new ArrayList<>();
	
	/**
	 * ʵ��
	 */
	private volatile static ReportUtil instance;
    /**
     * ���캯��
     */
	private ReportUtil(){
//        System.out.println("ReportUtil Singleton has loaded-----------------------------------------");
    }
    /**
     * ��ȡʵ��
     * @return
     */
	public static ReportUtil getInstance(){
        if(instance==null){
            synchronized (ReportUtil.class){
                if(instance==null){
                    instance=new ReportUtil();
                }
            }
        }
        return instance;
    }
	
	/**
	 * ��ȡ����ʡ��
	 */
	public void collectGoodProvince(int style){
		mStyle = style;
		JMOperate.getInstance().classifyProvince();
		MGOperate.getInstance().classifyProvince();
//		PZOperate.getInstance().classifyProvince();
		QPOperate.getInstance().classifyProvince();
		SAOperate.getInstance().classifyProvince();
		SFOperate.getInstance().classifyProvince();
		TKOperate.getInstance().classifyProvince();
		WYOperate.getInstance().classifyProvince();
		XMOperate.getInstance().classifyProvince();
		YFOperate.getInstance().classifyProvince();
		YJOperate.getInstance().classifyProvince();
		ZDOperate.getInstance().classifyProvince();
		sortProvinceByOperator(TYPE_YD_GOOD);
//		System.out.println("-----------------------------------------1");
		sortProvinceByOperator(TYPE_LT_GOOD);
//		System.out.println("-----------------------------------------2");
		sortProvinceByOperator(TYPE_DX_GOOD);
//		System.out.println("-----------------------------------------3");
		sortProvinceByOperator(TYPE_ALL_GOOD);
//		System.out.println("-----------------------------------------4");
		sortProvinceByOperator(TYPE_ALL_EJ);
//		System.out.println("-----------------------------------------5");
		sortProvinceByOperator(TYPE_YD_SHIELD);
//		System.out.println("-----------------------------------------6");
		sortProvinceByOperator(TYPE_LT_SHIELD);
//		System.out.println("-----------------------------------------7");
		sortProvinceByOperator(TYPE_DX_SHIELD);
//		System.out.println("-----------------------------------------8");
        try {
        	Date date = new Date();          
			SimpleDateFormat dfFileName = new SimpleDateFormat("MM��dd��ʡ��");  
			String strFileName = dfFileName.format(date);  
			FileUtil.fileWriter(Config.RESULTRootPath + strFileName + ".txt", mLstProvince);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ʡ������
	 */
	private void sortProvinceByOperator(int type) {
		String[] provinceArr = null;
		String operator = "";
		if (type == TYPE_YD_GOOD) {
			Date date = new Date();          
			SimpleDateFormat df = new SimpleDateFormat("MM��dd��ʡ��");  
			String dateStr = df.format(date);  
			
			operator = dateStr + "\n1>\nһ��ʡ�ݣ�\n�ƶ���\n";
			 provinceArr = mergeYDGoodProvince();
		} else if (type == TYPE_LT_GOOD) {
			provinceArr = mergeLTGoodProvince();
			operator = "��ͨ��\n";
		} else if (type == TYPE_DX_GOOD){
			provinceArr = mergeDXGoodProvince();
			operator = "���ţ�\n";
		} else if (type == TYPE_ALL_GOOD) {
			provinceArr = mergeAllGoodProvince();
			operator = "�����ۺ�����ʡ�ݣ�\n";
		} else if (type == TYPE_ALL_EJ) {
			provinceArr = mergeSWZHEJProvince();
			operator = "\n2>\n�����ۺ϶���ʡ�ݣ�\n";
		} else if (type == TYPE_YD_SHIELD) {
			provinceArr = mergeYDShieldProvince();
			operator = "\n3>\n����ʡ�ݣ�\n�ƶ���\n";
		} else if (type == TYPE_LT_SHIELD) {
			provinceArr = mergeLTShieldProvince();
			operator = "��ͨ��\n";
		} else if (type == TYPE_DX_SHIELD) {
			provinceArr = mergeDXShieldProvince();
			operator = "���ţ�\n";
		}
		Map<String, Integer> map = new HashMap<>();
        for (String str : provinceArr) {
            Integer num = map.get(str);
            num = null == num ? 1 : num + 1;
            map.put(str, num);
        }

        if (provinceArr.length != map.size()) {
//            System.out.println("������ͬ��Ԫ��!");
        }
        Map<String, String> resultMap = sortMapByValue(map);
        Set set = resultMap.entrySet();
        Iterator<Map.Entry> it = set.iterator();
        
        mLstProvince.add(operator);
        
        if (mStyle == Config.STYLE_ONE) {
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
                mLstProvince.add(entry.getKey() + " ");
            }
		} else if (mStyle == Config.STYLE_TWO) {
			while (it.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
                mLstProvince.add(entry.getKey() + ":" + entry.getValue() + " ");	
            }
		} else if (mStyle == Config.STYLE_THREE) {
			while (it.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)it.next();
                mLstProvince.add(entry.getKey() + " ");
            }
			it = set.iterator();
			mLstProvince.add("\n");
			while (it.hasNext()) {
				Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
				if (Integer.parseInt(String.valueOf(entry.getValue())) < 10) {
					if (entry.getKey().length() == 2) {
						mLstProvince.add(" " + entry.getValue() + "   ");
					} else if (entry.getKey().length() == 3) {
						mLstProvince.add("  " + entry.getValue() + "     ");
					}
				} else {
					if (entry.getKey().length() == 2) {
						mLstProvince.add(" " + entry.getValue() + "  ");
					} else if (entry.getKey().length() == 3) {
						mLstProvince.add("  " + entry.getValue() + "   ");
					}
				}
			}
		}
        mLstProvince.add("\n\n");
	}
	/**
	 * �ϲ��ƶ�����ʡ��
	 * @return
	 */
	private String[] mergeYDGoodProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.YDGood));
		list.addAll(Arrays.asList(MGOperate.YDGood));
        list.addAll(Arrays.asList(PZOperate.YDGood));
        list.addAll(Arrays.asList(QPOperate.YDGood));
        list.addAll(Arrays.asList(SAOperate.YDGood));
        list.addAll(Arrays.asList(SFOperate.YDGood));
        list.addAll(Arrays.asList(TKOperate.YDGood));
        list.addAll(Arrays.asList(WYOperate.YDGood));
        list.addAll(Arrays.asList(XMOperate.YDGood));
        list.addAll(Arrays.asList(YFOperate.YDGood));
        list.addAll(Arrays.asList(YJOperate.YDGood));
        list.addAll(Arrays.asList(ZDOperate.YDGood));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	/**
	 * �ϲ���ͨ����ʡ��
	 * @return
	 */
	private String[] mergeLTGoodProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.LTGood));
		list.addAll(Arrays.asList(MGOperate.LTGood));
        list.addAll(Arrays.asList(PZOperate.LTGood));
        list.addAll(Arrays.asList(QPOperate.LTGood));
        list.addAll(Arrays.asList(SAOperate.LTGood));
        list.addAll(Arrays.asList(SFOperate.LTGood));
        list.addAll(Arrays.asList(TKOperate.LTGood));
        list.addAll(Arrays.asList(WYOperate.LTGood));
        list.addAll(Arrays.asList(XMOperate.LTGood));
        list.addAll(Arrays.asList(YFOperate.LTGood));
        list.addAll(Arrays.asList(YJOperate.LTGood));
        list.addAll(Arrays.asList(ZDOperate.LTGood));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	/**
	 * �ϲ���������ʡ��
	 * @return
	 */
	private String[] mergeDXGoodProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.DXGood));
		list.addAll(Arrays.asList(MGOperate.DXGood));
        list.addAll(Arrays.asList(PZOperate.DXGood));
        list.addAll(Arrays.asList(QPOperate.DXGood));
        list.addAll(Arrays.asList(SAOperate.DXGood));
        list.addAll(Arrays.asList(SFOperate.DXGood));
        list.addAll(Arrays.asList(TKOperate.DXGood));
        list.addAll(Arrays.asList(WYOperate.DXGood));
        list.addAll(Arrays.asList(XMOperate.DXGood));
        list.addAll(Arrays.asList(YFOperate.DXGood));
        list.addAll(Arrays.asList(YJOperate.DXGood));
        list.addAll(Arrays.asList(ZDOperate.DXGood));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	
	/**
	 * �����ۺ϶���ʡ��
	 * @return
	 */
	private String[] mergeSWZHEJProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.YDOrdinary));
		list.addAll(Arrays.asList(JMOperate.LTOrdinary));
        list.addAll(Arrays.asList(JMOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(MGOperate.YDOrdinary));
        list.addAll(Arrays.asList(MGOperate.LTOrdinary));
        list.addAll(Arrays.asList(MGOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(QPOperate.YDOrdinary));
        list.addAll(Arrays.asList(QPOperate.LTOrdinary));
        list.addAll(Arrays.asList(QPOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(SAOperate.YDOrdinary));
        list.addAll(Arrays.asList(SAOperate.LTOrdinary));
        list.addAll(Arrays.asList(SAOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(SFOperate.YDOrdinary));
        list.addAll(Arrays.asList(SFOperate.LTOrdinary));
        list.addAll(Arrays.asList(SFOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(TKOperate.YDOrdinary));
        list.addAll(Arrays.asList(TKOperate.LTOrdinary));
        list.addAll(Arrays.asList(TKOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(WYOperate.YDOrdinary));
        list.addAll(Arrays.asList(WYOperate.LTOrdinary));
        list.addAll(Arrays.asList(WYOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(XMOperate.YDOrdinary));
        list.addAll(Arrays.asList(XMOperate.LTOrdinary));
        list.addAll(Arrays.asList(XMOperate.DXOrdinary));

        list.addAll(Arrays.asList(YJOperate.YDOrdinary));
        list.addAll(Arrays.asList(YJOperate.LTOrdinary));
        list.addAll(Arrays.asList(YJOperate.DXOrdinary));
        
        list.addAll(Arrays.asList(ZDOperate.YDOrdinary));
        list.addAll(Arrays.asList(ZDOperate.LTOrdinary));
        list.addAll(Arrays.asList(ZDOperate.DXOrdinary));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	
	
	/**
	 * �ϲ�����ʡ�ݣ��ƶ�����ͨ�����ţ�
	 * @return
	 */
	private String[] mergeAllGoodProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.YDGood));
		list.addAll(Arrays.asList(MGOperate.YDGood));
        list.addAll(Arrays.asList(PZOperate.YDGood));
        list.addAll(Arrays.asList(QPOperate.YDGood));
        list.addAll(Arrays.asList(SAOperate.YDGood));
        list.addAll(Arrays.asList(SFOperate.YDGood));
        list.addAll(Arrays.asList(TKOperate.YDGood));
        list.addAll(Arrays.asList(WYOperate.YDGood));
        list.addAll(Arrays.asList(XMOperate.YDGood));
        list.addAll(Arrays.asList(YFOperate.YDGood));
        list.addAll(Arrays.asList(YJOperate.YDGood));
        list.addAll(Arrays.asList(ZDOperate.YDGood));
        
        list.addAll(Arrays.asList(JMOperate.LTGood));
        list.addAll(Arrays.asList(MGOperate.LTGood));
        list.addAll(Arrays.asList(PZOperate.LTGood));
        list.addAll(Arrays.asList(QPOperate.LTGood));
        list.addAll(Arrays.asList(SAOperate.LTGood));
        list.addAll(Arrays.asList(SFOperate.LTGood));
        list.addAll(Arrays.asList(TKOperate.LTGood));
        list.addAll(Arrays.asList(WYOperate.LTGood));
        list.addAll(Arrays.asList(XMOperate.LTGood));
        list.addAll(Arrays.asList(YFOperate.LTGood));
        list.addAll(Arrays.asList(YJOperate.LTGood));
        list.addAll(Arrays.asList(ZDOperate.LTGood));
        
        list.addAll(Arrays.asList(JMOperate.DXGood));
        list.addAll(Arrays.asList(MGOperate.DXGood));
        list.addAll(Arrays.asList(PZOperate.DXGood));
        list.addAll(Arrays.asList(QPOperate.DXGood));
        list.addAll(Arrays.asList(SAOperate.DXGood));
        list.addAll(Arrays.asList(SFOperate.DXGood));
        list.addAll(Arrays.asList(TKOperate.DXGood));
        list.addAll(Arrays.asList(WYOperate.DXGood));
        list.addAll(Arrays.asList(XMOperate.DXGood));
        list.addAll(Arrays.asList(YFOperate.DXGood));
        list.addAll(Arrays.asList(YJOperate.DXGood));
        list.addAll(Arrays.asList(ZDOperate.DXGood));
        
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    } 
	
	/**
	 * �ϲ��ƶ�����ʡ��
	 * @return
	 */
	private String[] mergeYDShieldProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.YDShield));
		list.addAll(Arrays.asList(MGOperate.YDShield));
        list.addAll(Arrays.asList(QPOperate.YDShield));
        list.addAll(Arrays.asList(SAOperate.YDShield));
        list.addAll(Arrays.asList(SFOperate.YDShield));
        list.addAll(Arrays.asList(TKOperate.YDShield));
        list.addAll(Arrays.asList(WYOperate.YDShield));
        list.addAll(Arrays.asList(XMOperate.YDShield));
        list.addAll(Arrays.asList(YJOperate.YDShield));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	/**
	 * �ϲ���ͨ����ʡ��
	 * @return
	 */
	private String[] mergeLTShieldProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.LTShield));
		list.addAll(Arrays.asList(MGOperate.LTShield));
        list.addAll(Arrays.asList(QPOperate.LTShield));
        list.addAll(Arrays.asList(SAOperate.LTShield));
        list.addAll(Arrays.asList(SFOperate.LTShield));
        list.addAll(Arrays.asList(TKOperate.LTShield));
        list.addAll(Arrays.asList(WYOperate.LTShield));
        list.addAll(Arrays.asList(XMOperate.LTShield));
        list.addAll(Arrays.asList(YJOperate.LTShield));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	/**
	 * �ϲ���������ʡ��
	 * @return
	 */
	private String[] mergeDXShieldProvince() {  
		List list = new ArrayList(Arrays.asList(JMOperate.DXShield));
		list.addAll(Arrays.asList(MGOperate.DXShield));
        list.addAll(Arrays.asList(QPOperate.DXShield));
        list.addAll(Arrays.asList(SAOperate.DXShield));
        list.addAll(Arrays.asList(SFOperate.DXShield));
        list.addAll(Arrays.asList(TKOperate.DXShield));
        list.addAll(Arrays.asList(WYOperate.DXShield));
        list.addAll(Arrays.asList(XMOperate.DXShield));
        list.addAll(Arrays.asList(YJOperate.DXShield));
        Object[] object = list.toArray();
        String[] strs = new String[list.size()];
        for (int i = 0; i < object.length; i++) {
        	strs[i] = object[i].toString().trim();
        }
        return strs;
    }  
	
	
    /**
     * ʹ�� Map��value��������
     * @param oriMap
     * @return
     */
    public  Map<String, String> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue() + "");
        }
        return sortedMap;
    }
    /**
     * ����
     * @author Charles
     *
     */
    class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

            return me2.getValue().compareTo(me1.getValue());
        }
    }
}
