package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtils {
	@SuppressWarnings("resource")
	public static String readTxt(String path){
		File file=new File(path);
		InputStream is;
		BufferedReader reader;
		StringBuilder bf=new StringBuilder();
		if(file.isFile()&&file.exists()){
			try {
				is=new FileInputStream(file);
				reader=new BufferedReader(new InputStreamReader(is,"GBK"));
				String str;
				while((str=reader.readLine())!=null){
					bf.append(str);
				}
				System.out.println(bf);
				return bf.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("文件打开错误");
		}
		
		return "";
	}
	
}
