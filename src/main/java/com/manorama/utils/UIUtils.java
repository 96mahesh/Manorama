package com.manorama.utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.manorama.framework.BaseTest;
import com.manorama.framework.LogMe;
import com.manorama.setup.TestConfig;

public class UIUtils {
	private LogMe LOGGER = null;
	private WebDriver driver = null;
	private ExtentTest extentTest = null;
	private SoftAssert sAssert = null;
	Alert alert;
	
	public void setupTestObj() {
		this.LOGGER = BaseTest.LOGGER.get();
		this.driver = BaseTest.driver.get();
		this.extentTest = BaseTest.extentTest.get();
		this.sAssert = BaseTest.sAssert.get();
	}
	
	public void waitForSec(int i) {
		try {
			long timeInSec = i*1000;
			Thread.sleep(timeInSec);
		}catch(InterruptedException e) {
			e.printStackTrace();			
		}
	}
	
	public String getPageTitle(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String pageTitle = null;
		try {
			if(js.executeScript("return document.readyState").toString().equals("complete")) {
				pageTitle = driver.getTitle().trim();
				System.out.println("pageTitle"+pageTitle);
			}else {
				for(int i=1; i<=15; i++) {
					waitForSec(1);
					if(js.executeScript("return document.readyState").toString().equals("complete")) {
						pageTitle = driver.getTitle().trim();
						System.err.println("pageTitle"+pageTitle);
						break;
					}
				}
			}
		}catch(Exception e) {
			BaseTest.utilObj.get().getAssertManager().sAssertException("Exception occurred in getPageTitle:"+e.getMessage(), true, false);
		}
		return pageTitle;
	}
	
	public void validatePageTitle(String expectedPageTtitle, boolean isHardAssert) {
		try {
			String actualPageTitle = getPageTitle(driver);
			LOGGER.logTestStep(extentTest, "INFO", "<b><Going to valiadate page title><b>", false);
			BaseTest.sAssert.get().assertEquals(actualPageTitle, expectedPageTtitle);
			if(isHardAssert) {
				BaseTest.sAssert.get().assertAll();
				Assert.fail("validatePageTitle failed");
			}
				
		}catch(Exception e) {
			BaseTest.utilObj.get().getAssertManager().sAssertException("Something went wrong while validating page title. Exception:"+e.getMessage(), isHardAssert);
		}
	}
	public boolean objWait(String elementName, By by, String maxDurationInSec, boolean hardAssert, boolean...toBeClickable) {
		boolean flag = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Integer.parseInt(maxDurationInSec)))
					.pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
			
//			boolean el2 = wait.until(new Function<WebDriver, Boolean>() {
//                public Boolean apply(WebDriver driver) {
//                       boolean isTrue = (boolean)js.executeScript("return window.location.host !== ''");
//                       return isTrue;
//                }
//          });
		wait.until((ExpectedConditions.presenceOfElementLocated(by)));
		flag = driver.findElement(by).isDisplayed();
		wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(by)));
		if(toBeClickable.length>0) {
			if(toBeClickable[0]==true) {
				wait.until((ExpectedConditions.elementToBeClickable(by)));
			}
		}
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			
		}
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Exception occurred in element:"+elementName+ " wait."+e.getMessage(), true, hardAssert);
			flag = false;
		}		
		return flag;
	}
	
	
	
	public boolean objWaitWithoutIsDisplayed(String elementName, By by, String maxDurationInSec, boolean hardAssert) {
		boolean flag = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Integer.parseInt(maxDurationInSec)))
					.pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
		wait.until((ExpectedConditions.presenceOfElementLocated(by)));
		//flag = driver.findElement(by).isDisplayed();
		wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(by)));
		wait.until((ExpectedConditions.elementToBeClickable(by)));
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Exception occurred in element:"+elementName+ " wait."+e.getMessage(), true, hardAssert);
			flag = false;
		}		
		return flag;
	}
	
	public WebElement findElement(String elementName, By by,String waitTimeInSec, boolean hardAssert, boolean...toBeClickable) {
		if(toBeClickable.length>0) {
			if(toBeClickable[0]==false) {
				objWait(elementName, by, waitTimeInSec, hardAssert, false );
			}
		}else {
			objWait(elementName, by, waitTimeInSec, hardAssert );
		}
		
		WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	//Developed by Kanvitha
	public boolean isDisplayed(By by) {
		WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		return driver.findElement(by).isDisplayed();
	}
	
	//Developed by Kanvitha
		public boolean isEnabled(By by) {
			WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return driver.findElement(by).isEnabled();
		}
//		boolean status;
//		public boolean isEnabledAndClick(String elementName,By by) {
//			
//			try {
//				WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
//				wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
//				WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
//				status = element.isEnabled();
//				if(status) {
//					element.click();
//				}
//			}catch(Exception e) {
//				try {
//					WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
//					wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
//					WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
//					status = element.isEnabled();
//					if(status) {
//						element.click();
//					}
//				}catch(Exception e1) {
//					System.out.println("tried for multiple times");
//				}
//				
//			}
//			
//			return status;
//		}
		
		public boolean isEnabledAndClick(String elementName,By by) {
			WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wWait.until(ExpectedConditions.visibilityOfElementLocated(by));
			WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			boolean status = element.isEnabled();
			if(status) {
				element.click();
			}
			return status;
		}
		
		List<String> listOfRejectedNocs= new ArrayList<String>();
		List<WebElement> listEle;
		public List<String> getListOfRequestedOnTime(String s1,By by1,String s2,By by2){
			try {
				do {
					BaseTest.utilObj.get().getUIUtils().waitForSec(5);
					listEle = BaseTest.utilObj.get().getUIUtils().findElements(s1, by1,
							TestConfig.getInstance().getOBJWAITTIME());

					for (WebElement e : listEle) {
						if(!e.getText().equals(""))
						listOfRejectedNocs.add(e.getText());
					}

				} while (BaseTest.utilObj.get().getUIUtils().isEnabledAndClick(s2, by2));
			}catch(Exception e) {
				
			}
			System.out.println(listOfRejectedNocs);
			System.out.println("size of rejecttime"+listOfRejectedNocs.size());
			return listOfRejectedNocs;
		}
		
		/*** Developed By Mahesh ***/
		String data;
		public String getListOfdataElement(String elementname,By by, String name){
			try {
					BaseTest.utilObj.get().getUIUtils().waitForSec(3);
					List<WebElement> datalist = driver.findElements(by);
					for (WebElement e : datalist) {
						BaseTest.utilObj.get().getUIUtils().waitForSec(1);
						data = e.getAttribute("value");
						if(data.equals(name)) {
							e.click();
							break;
						}
					}
		}
			catch(Exception e) {
			System.out.println(e);
			}

			return data;
		}
	
	
	
	//Developed by Kanvitha
	public List<WebElement> findElements(String elementName, By by,String waitTimeInSec) {
		
		
		WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(waitTimeInSec)));
		return wWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}
	
	//Developed By Kanvitha
	public int countOfRows(String elementName, By by) {
		return driver.findElements(by).size();
	}
	
	//Developed By Kanvitha
	public void switchToAlertAndAccept() {
		driver.switchTo().alert().accept();
	}
	
	public void switchDefaultCintent() {
		driver.switchTo().defaultContent();
	}
	
	//Developed by Kanvitha
	public void sendKeysWithClickAndKeysEnter(String elementName, By by) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.click(element).sendKeys(Keys.ENTER).build().perform();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Developed by Kanvitha
		public void sendKeysWithKeysEnter(String elementName, By by) {
			Actions action = new Actions(driver);
			WebElement element;
			try {
				element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
				action.sendKeys(Keys.ENTER).build().perform();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	//Developed by Kanvitha
	public void sendKeysWithKeysBACK_SPACE(String elementName, By by) {
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			Thread.sleep(3000);
			element.clear();
			element.click();
			element.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendKeysWithKeys_ARROW_DOWN(String elementName, By by) {
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			Thread.sleep(3000);
			element.clear();
			element.click();
			element.sendKeys(Keys.ARROW_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Developed by Kanvitha
	public void clearAndEnterText(String elementName, By by,String text) {
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			element.clear();
			element.sendKeys(text);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void inputTextUsingActionClassClickTab(String elementName, By by, String data,Keys key) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.click(element).sendKeys(data).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+" using action class on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public void inputTextUsingActionClassClick(String elementName, By by, String data) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.click(element).sendKeys(data).sendKeys(Keys.ENTER).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+" using action class on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public void inputTextMultipleUsingActionClassClick(String elementName, By by, String data,String data1) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.click(element).sendKeys(data).sendKeys(Keys.ENTER).sendKeys(data1).sendKeys(Keys.ENTER).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+" using action class on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
		
	public void inputText(String elementName, By by, String data) {
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false);
			element.clear();
			element.sendKeys(data);
			if(data.toLowerCase().contains("password")) {
				LOGGER.logTestStep(extentTest, "INFO", "Entered password text:****** on element:"+elementName, false);
			}else {
				LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+ " on element:"+elementName, false);
			}			
		}catch(Exception e) {
			if(data.toLowerCase().contains("password")) {
				BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text **** on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
			}else {
				BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+ " on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
			}			
		}
	}
	public void inputTextwithKeys(String elementName, By by, String data, Keys key) {
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false);
			element.clear();
			element.sendKeys(data,key);
			if(data.toLowerCase().contains("password")) {
				LOGGER.logTestStep(extentTest, "INFO", "Entered password text:****** on element:"+elementName, false);
			}else {
				LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+ " on element:"+elementName, false);
			}			
		}catch(Exception e) {
			if(data.toLowerCase().contains("password")) {
				BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text **** on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
			}else {
				BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+ " on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
			}			
		}
	}
	
	public void inputTextUsingActionClass(String elementName, By by, String data) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.moveToElement(element).sendKeys(data).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+" using action class on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public void inputTextUsingJavascriptExecutor(String elementName, By by, String data) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			js.executeScript("arguments[0].value='"+ data +"';", element);
			js.executeScript("arguments[0].value = arguments[1];", element, data);
			LOGGER.logTestStep(extentTest, "INFO", "Entered text:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while inputting text:"+data+" using action class on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	
	public void clickElement(String viewrequest, By by) {
		WebElement element;
		try {
			element = findElement(viewrequest, by, TestConfig.getInstance().getOBJWAITTIME(), false);
			element.click();
			LOGGER.logTestStep(extentTest, "INFO", "Clicked on element:"+viewrequest, false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while clicking on element:"+viewrequest+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	//developed by Kanvitha
	public int getValueFromTextBox(String viewrequest, By by) {
		WebElement element;
		String value = null;
		int intvalue = 0;
		try {
			element = findElement(viewrequest, by, TestConfig.getInstance().getOBJWAITTIME(), false);
			//element.click();
			//element.sendKeys(Keys.chord(Keys.CONTROL,"a"));
			value=element.getAttribute("value");
			Float fl = Float.valueOf(value);
			intvalue = Math.round(fl);
			LOGGER.logTestStep(extentTest, "INFO", "Clicked on element:"+viewrequest, false);
			return intvalue;
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while clicking on element:"+viewrequest+ ".Exception"+e.getMessage(), true, true);
		}
		return intvalue;
	}
	public int getValueFromTextBox1(String viewrequest, By by) {
		WebElement element;
		String value = null;
		int intvalue = 0;
		String val="";
		int b=0;
		try {
			element = findElement(viewrequest, by, TestConfig.getInstance().getOBJWAITTIME(), false);
			value=element.getAttribute("value");
			//System.out.println("Value is:"+ value);
			int ss = value.indexOf(".");
			String sss=value.substring(0,ss);
			//System.out.println(sss);
			String a[]=sss.split(",");
			for(int i=0;i<a.length;i++) {
				val=val.concat(a[i]);
			}
			//System.out.println(val);
			b=Integer.parseInt(val);
			//System.out.println("int value is:"+b);
			LOGGER.logTestStep(extentTest, "INFO", "Clicked on element:"+viewrequest, false);
			return b;
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while clicking on element:"+viewrequest+ ".Exception"+e.getMessage(), true, true);
		}
		return b;
	}
	
	public String getText(String elementName, By by, boolean logMsg, boolean...toBeClickable) {
		String text = null;
		WebElement element = null;;
		try {
			if(toBeClickable.length>0) {
				if(! toBeClickable[0]==true) {
					element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false, toBeClickable[0]);
					text = element.getText().trim();
				}
			}else {
				element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false);
				text = element.getText().trim();
			}	
			
			
			if(StringUtils.isBlank(text)) {
				text = element.getAttribute("value").trim();
			}
			if(logMsg==true) {
				LOGGER.logTestStep(extentTest, "INFO", "Element:"+elementName+" text value:"+text+".", false);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Unable to retrieve text of element:"+elementName+ ".Exception"+e.getMessage(), true, false);
		}
		return text;
	}
	//developed by Kanvitha
	public String getText(String elementName, By by) {

		WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false);
		String text = element.getText();

		return text;
	}
	
	// ********* Developed By Mahesh ********* //
	
	public String getAttribute(String elementName, By by,String Attributevalue) {

		WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), false);
		String text = element.getAttribute(elementName);

		return text;
	}

	public void selectValue(String elementName, By by, String selectBy, String option) {
		try {
			objWait(elementName, by,TestConfig.getInstance().getOBJWAITTIME(), true);
			Select select = new Select(driver.findElement(by));
			switch (selectBy.toLowerCase()) {
			case "index":
				select.selectByIndex(Integer.valueOf(option));
				LOGGER.logTestStep(extentTest, "INFO", "Selected dropdown value as :"+option+" on element::"+elementName+".", false);
				break;
			case "text":
				select.selectByVisibleText(option);
				LOGGER.logTestStep(extentTest, "INFO", "Selected dropdown value as :"+option+" on element::"+elementName+".", false);
				break;
			case "value":
				select.selectByValue(option);
				LOGGER.logTestStep(extentTest, "INFO", "Selected dropdown value as :"+option+" on element::"+elementName+".", false);
				break;
			case "containstext":
				int indexNum = 1;
				for(WebElement element : select.getOptions()) {
					if(element.getText().toLowerCase().contains(option.toLowerCase())) {
						select.selectByIndex(indexNum);
						LOGGER.logTestStep(extentTest, "INFO", "Selected dropdown containstext value as :"+option+" on element::"+elementName+".", false);
						break;
					}
					indexNum++;
					
				}
			default:
				break;
			}			
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while selecting the value:"+option+ " on element:"+elementName+".Exception"+e.getMessage(), true, true);
		}
	}
	
	public String getSelectedValue(String elementName, By by) {
		String selectedText = null;
		try {
			objWait(elementName, by,TestConfig.getInstance().getOBJWAITTIME(), true);
			Select select = new Select(driver.findElement(by));
			selectedText = select.getFirstSelectedOption().getText();						
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while getting the first selected option for element :"+elementName+".Exception"+e.getMessage(), true, false);
		}
		return selectedText;
	}
	
	public void moveToElement(String elementName, By by) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.moveToElement(element).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Moved on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while moving on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	public WebElement clickElementUsingAction(String elementName, By by) {
		Actions action = new Actions(driver);
		WebElement element = null;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.moveToElement(element).click().build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Clicked on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while clickElementUsingAction on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
		return element;
	}
	
	public void rightClickElement(String elementName, By by) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.contextClick(element).perform();
			LOGGER.logTestStep(extentTest, "INFO", "Right clicked on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while right clicking on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public void doubleClickElement(String elementName, By by) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.doubleClick().build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Double clicked on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while double clicking on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public void pressKeyboardKeys(String elementName, By by, Keys data) {
		Actions action = new Actions(driver);
		WebElement element;
		try {
			element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			action.moveToElement(element).sendKeys(data).build().perform();
			LOGGER.logTestStep(extentTest, "INFO", "Pressed keyboard keys:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while pressing the keyboard keys:"+data+" on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
	
	public String getRandomNumber(int digCount) {
		String num = null;
		try {
			num = CommonUtils.generateNumericNumber(digCount);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while getting the random number.Exception"+e.getMessage(), true, true);
		}
		return num;
	}
	
	public boolean waitForInvisibilityOfElement(String elementName, By by, String waitInSec, boolean hardAssert) {
		boolean flag = true;
		try {
			WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(waitInSec)));
			wWait.until(ExpectedConditions.invisibilityOfElementLocated(by));			
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while waiting for invisibility of element:"+elementName+".Exception"+e.getMessage(), true, true);
			flag = false;
		}
		return flag;		
	}
	
	public boolean getRightWindow(String windowTitle) {
		String title = null;
		try {
			Set<String> handles = driver.getWindowHandles();
			List<String> hlist = new ArrayList<String>(handles);
			for(String handle : hlist) {
				title = driver.switchTo().window(handle).getTitle();
				if(title.contains(windowTitle)) {
					return true;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Something went wrong while navigating between the screen:"+windowTitle+".Exception"+e.getMessage(), true, true);					
		}
		return false;
	}
	
	public String getTextUsingJS(String elementName, String xpath, boolean logMsg) {
		String objVal = null;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			objVal = (String) js.executeScript("return document.evaluate(\""+xpath+"\", document, null, XPathResult.FIRST_ORDERED_TYPE, null).singleNodeValue.value;");
			if(logMsg) {
				LOGGER.logTestStep(extentTest, "INFO", "Element:"+elementName+" text value using JS as:"+objVal+".", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Unable to retrieve text using JS of element:"+elementName+".Exception"+e.getMessage(), true, false);
		}
		
		return objVal;
	}
	
	public void clickElementUsingJS(String elementName, String xpath, boolean logMsg) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)BaseTest.driver.get();
			String val ="document.evaluate(\""+xpath+"\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0).click();";
			//js.executeScript("document.evaluate(\""+xpath+"\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0).dispatchEvent(new MouseEvent('dblclick'));");
			js.executeScript("document.evaluate(\""+xpath+"\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0).click();");
			if(logMsg) {
				LOGGER.logTestStep(extentTest, "INFO", "Element:"+elementName+" clicked using JS.", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Element clicked using JS of element:"+elementName+".Exception"+e.getMessage(), true, false);
		}
	}
	
	//js.executeScript("return document.evaluate(\""+xpath+"\", document, null, XPathResult.FIRST_ORDERED_TYPE, null).singleNodeValue.click();");
	public void clickElementUsingJS2(String elementName, String xpath, boolean logMsg) {
		try {
			JavascriptExecutor js = (JavascriptExecutor)BaseTest.driver.get();
			String val ="document.evaluate(\""+xpath+"\", document, null, XPathResult.FIRST_ORDERED_TYPE, null).singleNodeValue.click();";
			js.executeScript("document.evaluate(\""+xpath+"\", document, null, XPathResult.FIRST_ORDERED_TYPE, null).singleNodeValue.click();");
			if(logMsg) {
				LOGGER.logTestStep(extentTest, "INFO", "Element:"+elementName+" clicked using JS.", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Element clicked using JS of element:"+elementName+".Exception"+e.getMessage(), true, false);
		}
	}	
	public boolean validateObjectPresent(By by, String objectName, boolean screenShot, boolean isHardAssert) {
		boolean flag = false;
		try {
			LOGGER.logTestStep(extentTest, "INFO", "Going to valiadate object:"+objectName+" presents in page", false);
			boolean isObjPresent= objWait(objectName, by, "90", isHardAssert);
			if(isObjPresent) {
				flag = true;
			}
			LOGGER.logTestStep(extentTest, "INFO", "isObjPresent:"+isObjPresent, false);
			BaseTest.utilObj.get().getAssertManager().sAssertEquals(isObjPresent, true, "Validating presense of element:"+objectName, screenShot, isHardAssert);
			
		}catch(Exception e) {
			BaseTest.utilObj.get().getAssertManager().sAssertException("Something went wrong while validating page title. Exception:"+e.getMessage(), true, isHardAssert);
			flag = false;
		}
		return flag;
	}
	public boolean validateObjectPresentWithoutIsDisplayed(By by, String objectName, boolean screenShot, boolean isHardAssert) {
		boolean flag = false;
		try {
			LOGGER.logTestStep(extentTest, "INFO", "Going to valiadate object:"+objectName+" presents in page", false);
			boolean isObjPresent= objWaitWithoutIsDisplayed(objectName, by, "90", isHardAssert);
			if(isObjPresent) {
				flag = true;
			}
			LOGGER.logTestStep(extentTest, "INFO", "isObjPresent:"+isObjPresent, false);
			BaseTest.utilObj.get().getAssertManager().sAssertEquals(isObjPresent, true, "Validating presense of element:"+objectName, screenShot, isHardAssert);
			
		}catch(Exception e) {
			BaseTest.utilObj.get().getAssertManager().sAssertException("Something went wrong while validating page title. Exception:"+e.getMessage(), true, isHardAssert);
			flag = false;
		}
		return flag;
	}
	
	public void scrollIntoView(By by) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
			waitForSec(1);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int objCnt(By by) {
		int cnt =0;
		try {
			setOnMinImplicitWait();
			cnt = driver.findElements(by).size();
			setOffMinImplicitWait();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public void setOnMinImplicitWait() {
		BaseTest.driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	public void setOffMinImplicitWait() {
		BaseTest.driver.get().manage().timeouts().implicitlyWait(Long.parseLong(TestConfig.getInstance().getScriptTimeoutSecounds()), TimeUnit.SECONDS);
	}
	
	public void uploadFile(String filePath) {
		try {
			StringSelection str = new StringSelection(filePath);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(str, null);
			BaseTest.utilObj.get().getUIUtils().waitForSec(4);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			BaseTest.utilObj.get().getUIUtils().waitForSec(4);
		}catch(Exception e) {
			BaseTest.utilObj.get().getAssertManager().sAssertException("Something went wrong while validating page title. Exception:"+e.getMessage(), true, true);
			
		}
	}
	
	public  int getListOfDataElementsCount(String elementName, By by,String name) {
		int count=0;
		try {
		BaseTest.utilObj.get().getUIUtils().waitForSec(5);
		List<WebElement>dataList=driver.findElements(by);
		for (WebElement element : dataList) {
			count++;
			BaseTest.utilObj.get().getUIUtils().waitForSec(1);
			String data=element.getText();
			if(data.contains(name)) {
				System.out.println(data);
				break;
			}
			
		}}
		catch (Exception e) {
			System.out.println(e);
		}
		return count;
		
	}
	public void clickOnCheckbox(String elementName1,By by1,String elementName2,By by2,String name) {
		int countData=getListOfDataElementsCount(elementName2,by2,name);
		int count=0;
		try {
			BaseTest.utilObj.get().getUIUtils().waitForSec(5);
			List<WebElement>dataList=driver.findElements(by1);
			for (WebElement element : dataList) {
				count++;
			
				BaseTest.utilObj.get().getUIUtils().waitForSec(1);
				if(count==countData) {
					element.click();
 
				}
 
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public  String takeScreenShot() 
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		String srcfile = scrShot.getScreenshotAs(OutputType.BASE64);
		return srcfile;

	}

	public  String capcturingPicture(String filename) throws IOException 
	{
		File sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir") + "//.ss//" + filename + ".png");
		FileUtils.copyFile(sourcefile, destinationFile);
		return destinationFile.getAbsolutePath();
	}
	
	public void tackScreenShot(String imegename) throws Exception, Exception 
	{
        TakesScreenshot ts = (TakesScreenshot) driver;
        FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(TestConfig.getInstance().getScreenShotPath()+imegename+".png"));
    }
	
	/*** Developed By MAhesh ***/
	
	public void switchToFrame(String elementName, By by) {
		try {
			WebElement element = findElement(elementName, by, TestConfig.getInstance().getOBJWAITTIME(), true);
			driver.switchTo().frame(element);
			LOGGER.logTestStep(extentTest, "INFO", "Pressed keyboard keys:"+data+" on element:"+elementName+".", false);
		}catch(Exception e) {
			e.printStackTrace();
			BaseTest.utilObj.get().getAssertManager().sAssertException("Failed while pressing the keyboard keys:"+data+" on element:"+elementName+ ".Exception"+e.getMessage(), true, true);
		}
	}
}

