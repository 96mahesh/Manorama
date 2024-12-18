package com.manorama.pageClasses;

import java.io.IOException;
import org.openqa.selenium.By;
import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.XLUtility;

public class SearchPage {
	
	XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());

	public By uhid = By.id("cntPlcHolder_txtUHID");
	public By search = By.id("cntPlcHolder_btnSearch");
	public By save_visit = By.id("cntPlcHolder_btnAddVisit");
	public By search_alert = By.id("btnOk");
	public By patient_bill_close = By.id("cntPlcHolder_btnCloseModel");
	public By patient_detais = By.id("cntPlcHolder_lblPatientDemoAfterCollapse");
	public By cross_btn=By.xpath("(//span[text()='Patient Dues Details']//following::button[@class='close' and @onclick='CloseModal()'])[1]");

	public String uhid_num = "enter uhid num";
	public String serach_btn = "click search btn";
	public String patient_details_txt = "get patient detalis";
	public String save_visit_btn = "save_visit_btn";
	public String search_alert_btn = "click ok button";
	public String patient_bill_close_btn = "click close button";
	public String patient_due_cross_btn="click cross button";


	public void enter_uhid_num() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(uhid_num, uhid, xlUtil.getCellData("Sheet3", 1, "UHID"));
	}

	public void click_search_btn() throws Exception {
		BaseTest.utilObj.get().getUIUtils().clickElement(serach_btn, search);
		BaseTest.utilObj.get().getUIUtils().waitForSec(10);
		try {
			if (BaseTest.utilObj.get().getUIUtils().isDisplayed(patient_bill_close)) {
				BaseTest.utilObj.get().getUIUtils().tackScreenShot("patient bill dues");
				BaseTest.utilObj.get().getUIUtils().clickElement(patient_bill_close_btn, patient_bill_close);
			} else {
				BaseTest.utilObj.get().getUIUtils().getText(patient_details_txt, patient_detais);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void click_save_visit_btn() {
		BaseTest.utilObj.get().getUIUtils().clickElement(save_visit_btn, save_visit);
	}

	public void click_alert_ok_btn() {
		BaseTest.utilObj.get().getUIUtils().clickElement(search_alert_btn, search_alert);
	}
	
	public void click_patient_due_cross_btn() {
		BaseTest.utilObj.get().getUIUtils().waitForSec(3);
		BaseTest.utilObj.get().getUIUtils().clickElement(patient_due_cross_btn, cross_btn);
	}

}
