package com.qunar.autotest.uitest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class CommonFunction {
	 public String getString(String str) {
		 int num;
		 StringBuffer result=new StringBuffer();
	        if ((str != null) && (str.contains("str:"))) {
	        	num=Integer.parseInt(str.split(":")[1]) ;
	        	for (int i=0;i<num;i++)
	        		result=result.append("一");
	            return result.toString();

	        }
	        if ((str != null) && (str.contains("eng:"))) {
	        	num=Integer.parseInt(str.split(":")[1]) ;
	        	for (int i=0;i<num;i++)
	        		result=result.append("a");
	            return result.toString();

	        }
	        if ((str != null) && (str.contains("random"))) {
	        	Calendar calendar = Calendar.getInstance();
	        	 Date today = calendar.getTime();
	        	 SimpleDateFormat timesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            return str+timesdf.format(today);

	        }
	        else
	            return str;

	    }
	 	
}
