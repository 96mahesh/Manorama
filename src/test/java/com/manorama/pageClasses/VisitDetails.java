package com.manorama.pageClasses;

import org.openqa.selenium.By;

import com.manorama.framework.BaseTest;

public class VisitDetails {

	public By visitditails = By.xpath("//button[text()='Visit Details']");
	public By iframe = By.xpath("//iframe[@id='cntPlcHolder_frmVisitDet']");
	public By click_here_btn = By.xpath("//a[@id='cntPlcHolder_btnPrePage']");
	
	public String visitditails_name = "click on visitditails";
	public String iframe_name = "enter iframe";
	public String click_here_btn_name = "click_here_btn";
	
	public void clickOnVisitDetails() {
		BaseTest.utilObj.get().getUIUtils().clickElement(visitditails_name, visitditails);
	}
	
	public void switchIframe() {
		BaseTest.utilObj.get().getUIUtils().switchToFrame(iframe_name, iframe);
	}
	
	public void clickOnclickHereBtn() {
		BaseTest.utilObj.get().getUIUtils().clickElementUsingAction(click_here_btn_name, click_here_btn);
	}
	
	
}

