package com.manorama.pageClasses;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.DataConfigConsatant;
import com.manorama.utils.XLUtility;

public class UpdatePatient {

	XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());

	public By updatePatient = By.xpath("//a[text()='Update Patient Demographics']");
	public By serchBox = By.xpath("//input[@id='txtCommonPatientSearch']");
	public By frame_loctaor = By.xpath("//iframe[@id='iframe1']");
	public By text_dta = By.xpath("//h4[text()='Contact to your administrator for further assistance']");
	public By click_here_btn = By.xpath("//a[@id='cntPlcHolder_btnPrePage']");
	public By search_btn = By.xpath("//button[@id='btnserchPatient']");

	
	public String updatePatient_name = "click on upadate patient";
	public String serchBox_name = "Enter value";
	public String frame_loctaor_name = "Enter frame";
	public String text_data_name = "get text form page";
	public String click_here_btn_name = "click_here_btn";
	public String search_btn_name = "search_btn";
	
	

	public void clickOnUpdatePatient() {

		BaseTest.utilObj.get().getUIUtils().clickElement(updatePatient_name, updatePatient);
	}
	
	public void switchFrame() {
		BaseTest.utilObj.get().getUIUtils().switchToFrame(frame_loctaor_name, frame_loctaor);
	}
	
	public void enterValueSearcBox() {
		BaseTest.utilObj.get().getUIUtils().inputText(serchBox_name, serchBox, Manorama_Registrationpage.uhid);
	}
	
	public void getData() {
		String data = BaseTest.utilObj.get().getUIUtils().getText(text_data_name, text_dta, true);
		Assert.assertEquals(data, DataConfigConsatant.text_data_validation);
	}
	
	public void clickOnclickHereBtn() {
		BaseTest.utilObj.get().getUIUtils().clickElement(click_here_btn_name, click_here_btn);
	}
	
	public void clickOnserch_btn() {
		BaseTest.utilObj.get().getUIUtils().clickElement(search_btn_name, search_btn);
	}
	
	
	
	
}
