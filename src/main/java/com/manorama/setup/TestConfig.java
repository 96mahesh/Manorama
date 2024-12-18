package com.manorama.setup;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.log4testng.Logger;

public class TestConfig {

	static Logger LOGGER = Logger.getLogger(TestConfig.class);

	private static TestConfig testConfig;
	private static Config config;
	private String testModulesPath;
	private String browser;
	private String excel_path;
	private String screen_shot_path;
	private String bankStatement_Path;
	private String lender_Path;
	private String download_Path;
	private String appURL;
	private String appUserId;
	private String appPWD;
	private String developerNOCRequestURL;
	private String developerUserId;
	private String developerUserPWD;
	private String investorNOCRequestURL;
	private String lenderUserId;
	private String lenderUserPWD;
	private String developer2NOCRequestURL;
	private String developerUserId2;
	private String developerUserPWD2;
	private String reportPath;
	private static String ENVName;
	private int FailureRetryCount;
	private String recordingFlag;
	private String MINWAITTIME;
	private String AVGWAITTIME;
	private String MAXWAITTIME;
	private String OBJWAITTIME;
	private String scriptTimeoutSecounds;

	private String pmcNOCRequestURL;
	private String UserId;
	private String UserPWD;
	
	private static String moduleName;
	private static String projDir;
	private static String testSuiteName;
	
	
	private static String UnitNumber;
	private static String FinalDecision_PMC;
	private static String FinalDecision_Investor;
	private static String FinalDecision_Debenture;
	private static String SelectField_DevHomePage;
	public static String Search_DevHomePage;
	private static String SelectField_DebentureHomePage;
	public static String Search_DebentureHomePage;
	public static String Search_PendingTable_UNo_Withdraw;
	public static String SelectField_PendingTable;
	public static String SearchFieldApprovedTable;
	public static String Search_ApprovedTable;
	
	private TestConfig() {
	}

	static {
		try {
			if (System.getProperty("ENVName") == null) {
				ENVName = "TEST";
			} else {
				ENVName = System.getProperty("ENVName");
			}
			projDir = System.getProperty("user.dir");
			config = new Config(projDir + "/src/main/resources/Test_Config/" + ENVName + "_config.properties", projDir + "/src/main/resources/Test_Config/" + "TestData_config.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}

		PropertyConfigurator.configure("log4j.properties");
		if (System.getProperty("moduleName") == null) {
			moduleName = config.getPropertyValue("moduleName");
		} else {
			moduleName = config.getPropertyValue("moduleName");
		}
	}

	public static TestConfig getInstance() {
		if (testConfig == null) {
			testConfig = new TestConfig();
		}
		return testConfig;
	}

	public static Config getConfig() {
		return config;
	}

	public String getTestModulesPath() {
		return testModulesPath;
	}

	public String getBrowser() {
		return browser;
	}
	
	public String getExcel_path() {
		return excel_path;
	}
	
	public String getScreenShotPath() {
		return screen_shot_path;
	}
	
	public String getBankStatement_Path() {
		return bankStatement_Path;
	}
	
	public String getLender_Path() {
		return lender_Path;
	}
	
	public String getDownload_Path() {
		return download_Path;
	}

	public String getAppURL() {
		return appURL;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public String getAppPWD() {
		return appPWD;
	}

	public String getDeveloperNOCRequestURL() {
		return developerNOCRequestURL;
}

	public String getDeveloperUserId() {
		return developerUserId;
	}

	public String getDeveloperUserPWD() {
		return developerUserPWD;
	}

	public String getInvestorNOCRequestURL() {
		return investorNOCRequestURL;
	}

	public String getLenderUserId() {
		return lenderUserId;
	}

	public String getLenderUserPWD() {
		return lenderUserPWD;
	}

	public String getDeveloper2NOCRequestURL() {
		return developer2NOCRequestURL;
	}

	public String getDeveloperUserId2() {
		return developerUserId2;
	}

	public String getDeveloperUserPWD2() {
		return developerUserPWD2;
	}

	public String getReportPath() {
		return reportPath;
	}

	public String getExecEnvironment() {
		return ENVName;
	}

	public int getFailureRetryCount() {
		return FailureRetryCount;
	}

	public String getRecordingFlag() {
		return recordingFlag;
	}

	public String getMINWAITTIME() {
		return MINWAITTIME;
	}

	public String getAVGWAITTIME() {
		return AVGWAITTIME;
	}

	public String getMAXWAITTIME() {
		return MAXWAITTIME;
	}

	public String getOBJWAITTIME() {
		return OBJWAITTIME;
	}

	public String getScriptTimeoutSecounds() {
		return scriptTimeoutSecounds;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getProjDir() {
		return projDir;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}
	
	public String getpmcNOCRequestURL() {
		return pmcNOCRequestURL;
	}

	public String getUserId() {
		return UserId;
	}

	public String getUserPWD() {
		return UserPWD;
	}
	
	public String getUnitNumber() {
		return UnitNumber;
	}
	public String getFinalDecisionPMC() {
		return FinalDecision_PMC;
	}
	public String getFinalDecisionInvestor() {
		return FinalDecision_Investor;
	}
	public String getFinalDecisionDebenture() {
		return FinalDecision_Debenture;
	}
	
	public String getSelectField_DevHomePage() {
		return SelectField_DevHomePage;
	}
	
	public String getSearch_DevHomePage() {
		return Search_DevHomePage;
	}
	public String getSelectField_DebentureHomePage() {
		return SelectField_DebentureHomePage;
	}
	
	public String getSearch_DebentureHomePage() {
		return Search_DebentureHomePage;
	}
	public String getSearch_PendingTable_UNo_Withdraw() {
		return Search_PendingTable_UNo_Withdraw;
	}
	public String getSelectField_PendingTable() {
		return SelectField_PendingTable;
	}
	public String getSearchFieldApprovedTable() {
		return SearchFieldApprovedTable;
	}
	public String getSearch_ApprovedTable() {
		return Search_ApprovedTable;
	}
	
	
	// Framework Initialization
	public void suiteSetup() {
		try {
			frameworkSetup();
		} catch (Exception e) {
			LOGGER.error("Exception " + e.getClass().getName() + " caught from suitesetup menthod");
		}
	}

	public WebDriver createDriverInstance(String browserType) {
		WebDriver driver = null;
		browserType = browserType.toUpperCase();
		ChromeOptions options = null;
		switch (browserType) {
		case "CHROME":
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			break;
		case "CHROMEHEADLESS":
			//WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("--no-sandbox");
			options.addArguments("window-size=1920,1080");
			options.addArguments("--start-fullscreen");
			driver = new ChromeDriver(options);
			break;
		case "EDGE":
			//WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			driver.manage().deleteAllCookies();
		}
		driver.manage().timeouts().setScriptTimeout(Long.parseLong(TestConfig.getInstance().getScriptTimeoutSecounds()),
				TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(TestConfig.getInstance().getAVGWAITTIME()),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(TestConfig.getInstance().getScriptTimeoutSecounds()),
				TimeUnit.SECONDS);

		return driver;
	}

	private void frameworkSetup() throws IOException {
		browser = config.getPropertyValue("browser");
		excel_path = config.getPropertyValue("excel_path");
		screen_shot_path = config.getPropertyValue("screen_shot_path");
		bankStatement_Path = config.getPropertyValue("bankStatement_Path");
		lender_Path = config.getPropertyValue("lender_Path");
		download_Path = config.getPropertyValue("download_Path");
		appURL = config.getPropertyValue("appURL");
		appUserId = config.getPropertyValue("appUserId");
		appPWD = config.getPropertyValue("appPWD");
		developerNOCRequestURL = config.getPropertyValue("developerNOCRequestURL");
		developerUserId = config.getPropertyValue("developerUserId");
		developerUserPWD = config.getPropertyValue("developerUserPWD");
		investorNOCRequestURL = config.getPropertyValue("investorNOCRequestURL");
		lenderUserId = config.getPropertyValue("lenderUserId");
		lenderUserPWD = config.getPropertyValue("lenderUserPWD");
		developer2NOCRequestURL = config.getPropertyValue("developer2NOCRequestURL");
		developerUserId2 = config.getPropertyValue("developerUserId2");
		developerUserPWD2 = config.getPropertyValue("developerUserPWD2");
		reportPath = config.getPropertyValue("reportPath");
		ENVName = config.getPropertyValue("ENVName");
		testSuiteName = config.getPropertyValue("testSuiteName");
		FailureRetryCount = Integer.parseInt(config.getPropertyValue("FailureRetryCount"));
		recordingFlag = config.getPropertyValue("recordingFlag");
		MINWAITTIME = config.getPropertyValue("MINWAITTIME");
		AVGWAITTIME = config.getPropertyValue("AVGWAITTIME");
		MAXWAITTIME = config.getPropertyValue("MAXWAITTIME");
		OBJWAITTIME = config.getPropertyValue("OBJWAITTIME");
		scriptTimeoutSecounds = config.getPropertyValue("scriptTimeoutSecounds");
		pmcNOCRequestURL = config.getPropertyValue("pmcNOCRequestURL");
		UserId = config.getPropertyValue("UserId");
		UserPWD = config.getPropertyValue("UserPWD");
		UnitNumber = config.getPropertyValue("UnitNumber");
		FinalDecision_PMC=config.getPropertyValue("FinalDecision_PMC");
		FinalDecision_Investor=config.getPropertyValue("FinalDecision_Investor");
		FinalDecision_Debenture=config.getPropertyValue("FinalDecision_Debenture");
		SelectField_DevHomePage=config.getPropertyValue("SelectField_DevHomePage");
		Search_DevHomePage=config.getPropertyValue("Search_DevHomePage");
		SelectField_DebentureHomePage=config.getPropertyValue("SelectField_DebentureHomePage");
		Search_DebentureHomePage=config.getPropertyValue("Search_DebentureHomePage");
		Search_PendingTable_UNo_Withdraw=config.getPropertyValue("Search_PendingTable_UNo_Withdraw");
		SelectField_PendingTable=config.getPropertyValue("SelectField_PendingTable");
		SearchFieldApprovedTable=config.getPropertyValue("SearchFieldApprovedTable");
		Search_ApprovedTable=config.getPropertyValue("Search_ApprovedTable");
		
	}
		
		
		
}
