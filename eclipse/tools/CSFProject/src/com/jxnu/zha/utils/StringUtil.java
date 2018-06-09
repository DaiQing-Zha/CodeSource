package com.jxnu.zha.utils;

public class StringUtil { 
	
	/**
	 * ��һ���ַ��У��ҳ�һ���ַ����ĵڼ��γ���λ��  
	 * @param str
	 * @param letter
	 * @param num
	 * @return
	 */
    public static int findNumber (String str,String letter,int num){  
        int count=0;  
        int str_len=str.length();  
        int substr_len=letter.length();  
        for (int i=0;i<str_len-substr_len ;i++ ) {  
             if (letter.equals(str.substring(i,i+substr_len))) {  
                    count++;  
                    if (count == num) return i;
             }  
        }  
        return 0;  
    } 
}
