package com.manorama.classes;

import java.util.Map;

import org.testng.annotations.Test;

public class TimeMethodRunnerTest {
	
	@Test
	public void callingDateTimeTest() {
		TimeMethods timeMethods = new TimeMethods();
		Map<String,String> t=timeMethods.getCurrentDateAndTime();
		System.out.println(t);
	}

}
