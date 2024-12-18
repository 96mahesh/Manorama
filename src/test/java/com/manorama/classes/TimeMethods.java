package com.manorama.classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeMethods {

	public Map<String, String> getCurrentDateAndTime() {
		
		Map<String,String> dt=new LinkedHashMap<String,String>();
		
		String timeStamp = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
		//System.out.println(timeStamp);
		// System.out.println(dateStamp);
		String[] s = timeStamp.split(":");
		//System.out.println(Arrays.toString(s));

		String[] ss = s[0].split(" ");
		//System.out.println(Arrays.toString(ss));

		String year = ss[2];
		dt.put("year", year);
		String month = ss[1];
		dt.put("month", month);
		String day = ss[0];
		dt.put("day", day);
		String hour = ss[3];
		dt.put("hour", hour);
		String minute = s[1];
		dt.put("minute", minute);
		String sec = s[2];
		dt.put("sec", sec);

		
		return dt;
//		System.out.println(dt);
//		System.out.println("year is : " + year);
//		System.out.println("month is : " + month);
//		System.out.println("day is : " + day);
//		System.out.println("hour is : " + hour);
//		System.out.println("minute is : " + minute);
//		System.out.println("sec is : " + sec);
	}

	public Map<String, String> getAppliDateAndTime(String datetime) {
		
		Map<String,String> dt=new LinkedHashMap<String,String>();
		
		String timeStamp = datetime;
		//System.out.println(timeStamp);
		// System.out.println(dateStamp);
		String[] s = timeStamp.split(":");
		//System.out.println(Arrays.toString(s));
		//31 Oct 2023 10:26:12
		String[] ss = s[0].split(" ");
		//System.out.println(Arrays.toString(ss));

		String year = ss[2];
		dt.put("year", year);
		String month = ss[1];
		dt.put("month", month);
		String day = ss[0];
		dt.put("day", day);
		String hour = ss[3];
		dt.put("hour", hour);
		String minute = s[1];
		dt.put("minute", minute);
		String sec = s[2];
		dt.put("sec", sec);
		
		return dt;

	}
	
	
}
