package com.jxnu.zha.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileUtil {
	/**
	 * ����д��
	 * @param fileName
	 * @param clist
	 * @throws IOException
	 */
    public static void fileWriter(String fileName,List<String> clist) throws IOException{
        //����һ��FileWriter����
        FileWriter fw = new FileWriter(fileName);
        //����clist����д�뵽fileName��
        for (String str: clist){
            fw.write(str);
//            fw.write("\n");
        }
        //ˢ�»�����
        fw.flush();
        //�ر��ļ�������
        fw.close();
    }
    /**
     * ��ȡ�ļ�
     * @param pathname
     * @return
     */
	public static String readFileData(String pathname){
		StringBuffer stringBuffer = new StringBuffer();
		try {
            File filename = new File(pathname); // Ҫ��ȡ����·����input��txt�ļ�  
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ����һ������������reader  
            BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
            String line = "";  
            
            line = br.readLine();  
            if (line != null) {
            	stringBuffer.append(line);
			}
            while (line != null) {  
                line = br.readLine(); // һ�ζ���һ������
                stringBuffer.append(line);
            }  
		} catch (Exception e) {
			System.out.println("error message = " + e.getMessage());
			return stringBuffer.toString().replaceAll("null", "");
		}
		return stringBuffer.toString().replaceAll("null", "").replaceAll("��", ",").replaceAll("��", ":").replaceAll("���ɹ�", "����").replaceAll("���̣�", "");
	}
}
