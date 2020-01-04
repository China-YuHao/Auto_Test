package com.Auto_Test.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Verify {
	
	public static boolean flag = true;
	public static List<Error> errormsg = new ArrayList<>();
	/**
	 * actual：实际
	 * expected：预期
	 * */
	public static void assertEquals(int actual,int expected){
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			flag = false;
			errormsg.add(e);
		}
	}
	
	public static void assertEquals(String actual,String expected){
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			flag = false;
			errormsg.add(e);
		}
	}
	
	public static void assertEquals(String actual,String expected,String message){
		try {
			Assert.assertEquals(actual, expected);
		} catch (Error e) {
			flag = false;
			errormsg.add(e);
		}
	}

}
