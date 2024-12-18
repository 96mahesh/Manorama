package com.manorama.pageClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.XLUtility;

public class LoginPage {

	XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());

	public By emailTxt = By.xpath("//input[@placeholder='Username']");
	public By passwordTxt = By.xpath("//input[@placeholder='Password']");
	public By signInBtn = By.xpath("//input[@value='LOG IN']");
	public By logout = By.xpath("//a[@id='lnkLogout']");
	

	public String emailTxt_Name = "Sign-In-email, Phone or Skype text field";
	public String passwordTxt_Name = "Password text field";
	public String signInBtn_Name = "Sign-In button";
	public String logout_name = "log out";
	

	public void loginManorama() throws IOException {
		BaseTest.LOGGER.get().logTestStep(BaseTest.extentTest.get(), "INFO",
				"<b>Going to login into TURBOPARTNERS application using Developer credential<b>", false);
		BaseTest.driver.get().get(xlUtil.getCellData("Manorama", 1, "Manorama_Url"));
		BaseTest.utilObj.get().getUIUtils().inputText(emailTxt_Name, emailTxt,
				xlUtil.getCellData("Manorama", 1, "UserName_Manorama"));
		BaseTest.utilObj.get().getUIUtils().waitForSec(2);
		BaseTest.utilObj.get().getUIUtils().inputText(passwordTxt_Name, passwordTxt,
				xlUtil.getCellData("Manorama", 1, "Password_Manorama"));
		BaseTest.utilObj.get().getUIUtils().clickElement(signInBtn_Name, signInBtn);
		BaseTest.utilObj.get().getUIUtils().waitForSec(1);
		
		BaseTest.utilObj.get().getUIUtils().waitForSec(3);

	}
	
	public void clickOnLogOut() {
		BaseTest.utilObj.get().getUIUtils().clickElement(logout_name, logout);
	}

}
