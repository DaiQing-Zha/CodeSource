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
	 * 按行写入
	 * @param fileName
	 * @param clist
	 * @throws IOException
	 */
    public static void fileWriter(String fileName,List<String> clist) throws IOException{
        //创建一个FileWriter对象
        FileWriter fw = new FileWriter(fileName);
        //遍历clist集合写入到fileName中
        for (String str: clist){
            fw.write(str);
//            fw.write("\n");
        }
        //刷新缓冲区
        fw.flush();
        //关闭文件流对象
        fw.close();
    }
    /**
     * 读取文件
     * @param pathname
     * @return
     */
	public static String readFileData(String pathname){
		StringBuffer stringBuffer = new StringBuffer();
		try {
            File filename = new File(pathname); // 要读取以上路径的input。txt文件  
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";  
            
            line = br.readLine();  
            if (line != null) {
            	stringBuffer.append(line);
			}
            while (line != null) {  
                line = br.readLine(); // 一次读入一行数据
                stringBuffer.append(line);
            }  
		} catch (Exception e) {
			System.out.println("error message = " + e.getMessage());
			return stringBuffer.toString().replaceAll("null", "");
		}
		return stringBuffer.toString().replaceAll("null", "").replaceAll("，", ",").replaceAll("：", ":").replaceAll("内蒙古", "内蒙").replaceAll("（√）", "");
	}
}
