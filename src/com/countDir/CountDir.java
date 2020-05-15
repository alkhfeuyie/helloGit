package com.countDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CountDir {
	private int count;
	/**
	 * ͳ��һ��Ŀ¼������java�ļ�������������
	 * @param string
	 */
	private void countDir(String sourceDir) {
		File fSourceDir = new File(sourceDir);
		if(!fSourceDir.exists() || !fSourceDir.isDirectory()) {
			System.out.println("ԴĿ¼������");
			return;
		}
		//����Ŀ¼�µ��ļ���Ŀ¼
		File[] file = fSourceDir.listFiles();
		for (File file2 : file) {
			if(file2.isFile()) {
				if(file2.getName().toLowerCase().endsWith(".java")) {//�ж��ļ�����׺
					try {
						countLine(file2);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(file2.isDirectory()) {
				String subSourceDir = sourceDir + File.separator + file2.getName();
				countDir(subSourceDir);//�õݹ�ʵ��
			}
		}
	}
	private void countLine(File sourceFile) throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(sourceFile));
			while(br.readLine()!=null) {
				count++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}		
	}
	public static void main(String[] args) {
		CountDir testCountDir = new CountDir();
		testCountDir.countDir("D:\\eclipse\\eclipse workspace\\.metadata");
		System.out.println(testCountDir.count);
	}
}


