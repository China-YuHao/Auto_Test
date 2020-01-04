package com.Auto_Test.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
	public List<String[]> csvlist = new ArrayList<String[]>();
	
    public List<String[]> redcsv() throws IOException {
		//表格文件路径
		File csv = new File("./src/Auto.csv");
		try {
			//读取CSV文件
			BufferedReader buffreader = new BufferedReader(new FileReader(csv));
			//去除标题
			buffreader.readLine();
			String line = "";
			while ((line = buffreader.readLine()) != null) {
				csvlist.add(line.split(","));
			}
			return csvlist;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("文件读取失败！");
			return null;
		}
		
    }
}
