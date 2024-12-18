package com.manorama.classes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Time {

	@Test
	public void m() {
		
		Map<String,String> cur_dt=new LinkedHashMap<String,String>();
		Map<String,String> app_dt=new LinkedHashMap<String,String>();
		//System.out.println(System.currentTimeMillis());
		
//		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd MM yyyy hh:mm:ss a");
//		LocalDateTime localdatetime = LocalDateTime.now();
//		String newdate = localdatetime.format(date);
//		System.out.println(newdate);
		
		String timeStamp = new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date());
	//	String dateStamp = new SimpleDateFormat("dd.MMM.yyyy").format(new Date());
		
		System.out.println(timeStamp);
	//	System.out.println(dateStamp);
		String[] s=timeStamp.split(":");
		System.out.println(Arrays.toString(s));
		
		String[] ss=s[0].split(" ");
		System.out.println(Arrays.toString(ss));
		
		String year = ss[2];
		cur_dt.put("year", year);
		String month = ss[1];
		cur_dt.put("month", month);
		String day = ss[0];
		cur_dt.put("day", day);
		String hour = ss[3];
		cur_dt.put("hour", hour);
		String minute = s[1];
		cur_dt.put("minute", minute);
		String sec = s[2];
		cur_dt.put("sec", sec);
		
//		System.out.println("year is : "+year);
//		System.out.println("month is : "+month);
//		System.out.println("day is : "+day);
//		System.out.println("hour is : "+hour);
//		System.out.println("minute is : "+minute);
//		System.out.println("sec is : "+sec);
		System.out.println("cur_dt is "+cur_dt);
		
		TimeMethods timeMethods = new TimeMethods();
		String sakjhf="14 Dec 2023 16:45:02";
		app_dt = timeMethods.getAppliDateAndTime(sakjhf);
		System.out.println("app_dt is "+app_dt);
			
		if(app_dt.get("year").equals(cur_dt.get("year")))
		{
			System.out.println("app year is "+app_dt.get("year"));
			System.out.println("current year is "+cur_dt.get("year"));
			if(app_dt.get("month").equals(cur_dt.get("month"))) 
			{
				System.out.println("app month is "+app_dt.get("month"));
				System.out.println("current month is "+cur_dt.get("month"));
				if(app_dt.get("day").equals(cur_dt.get("day"))) 
				{
					System.out.println("app day is "+app_dt.get("day"));
					System.out.println("current day is "+cur_dt.get("day"));
					if(app_dt.get("hour").equals(cur_dt.get("hour")))
					{
						System.out.println("app hour is "+app_dt.get("hour"));
						System.out.println("current hour is "+cur_dt.get("hour"));
						String min_app = (String) app_dt.get("minute");
						String min_cur = (String) cur_dt.get("minute");
						int min_app_int = Integer.parseInt(min_app);
						int min_cur_int = Integer.parseInt(min_cur);
						
						System.out.println("app min is "+app_dt.get("minute"));
						System.out.println("current min is "+cur_dt.get("minute"));
						
						System.out.println("app minute int value is "+min_app_int);
						System.out.println("cur minute int value is "+min_cur_int);
						if(min_app_int<=min_cur_int) {
							System.out.println("Validation is success");
							Assert.assertTrue(true);
						}else {
							System.out.println("Validation is not happen beacuse minute mismatch");
							Assert.assertFalse(true);
						}
					}
				}
			}
		}else {
			System.out.println("Validation is not happen beacuse year mismatch");
			Assert.assertFalse(true);
		}
	}
		
}
	

