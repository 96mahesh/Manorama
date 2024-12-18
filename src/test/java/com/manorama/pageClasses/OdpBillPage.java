package com.manorama.pageClasses;

import java.io.IOException;

import org.openqa.selenium.By;

import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.DataConfigConsatant;
import com.manorama.utils.XLUtility;

import junit.framework.Assert;

public class OdpBillPage {
	
	 XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());
	 
	   static String bill;

	    public By Redirecting_bill = By.xpath("//input[@id='cntPlcHolder_btnRedirectToBillig']");
	    public By servece_name = By.xpath("//input[@id='cntPlcHolder_TxtTest']");
	    public By search_btn = By.xpath("//button[@value='Search Test']");
		public By lab_check_box = By.xpath("//input[contains(@name,'Complete Blood Count (With ESR)- EDTA Blood')]");
		public By add_btn = By.xpath("//button[@value='ADD']");
		public By doctor_fee = By.xpath("//input[@name='ctl00$cntPlcHolder$gvInvoice$ctl02$txtRate']");
		public By lab_fee = By.xpath("//input[@name='ctl00$cntPlcHolder$gvInvoice$ctl03$txtRate']");
		public By bill_amout = By.xpath("//input[@name='ctl00$cntPlcHolder$txtPatBillAmt']");
		public By cash_recive = By.xpath("//input[@name='ctl00$cntPlcHolder$txtCashRecieved']");
		public By creditGivenBy = By.xpath("//select[@id='cntPlcHolder_ddlCreditGivenBy']");
		public By creditApprovedBy = By.xpath("//select[@id='cntPlcHolder_ddlCreditApprovedBy']");
		public By remarks = By.xpath("//input[@placeholder='Remark']");
		public By saved_text = By.xpath("//div[text()='Saved']");
		public By ok_btn = By.xpath("//button[contains(text(),'Ok') and @id='btnOk']");
		//save  alert
		public By billprint_btn = By.xpath("//input[@value='Bill Print']");
		public By print_bill = By.xpath("//embed[@type='application/pdf']");
		public By check_box=By.xpath("//table[@class='table customSearchTable']//tr//td//span//input[@type='checkbox']");
		public By selectDepartment_service=By.xpath("//table[@class='table customSearchTable']//tr");
		public By bill_cancel = By.xpath("//input[@value='Bill Cancel ']");
		public By author_by = By.xpath("//select[@id='cntPlcHolder_ddlCanAutoR']");
		public By reason =  By.xpath("//input[@id='txtCancelDischargeRemark']");
		//ok
		public By refund_btn = By.xpath("//button[@id='btnConfirmYes']");
		public By canceled_txt = By .xpath("//div[text()='Cancelled']");
		public By click_ok_btn = By.xpath("//button[contains(text(),'Ok') and @id='cntPlcHolder_Button3']");
		
		
		public String Redirecting_bill_name = "click on Redirecting";
		public String servece_name_name = "send data from service";
		public String search_btn_name = "click on serach btn";
		public String lab_check_box_name = "click on lab_check_box";
		public String add_btn_name = "click on add btn";
		public String doctor_fee_name = "get doctor_fee";
		public String lab_fee_name = "get lab_fee";
		public String bill_amout_name = "get bill_amout";
		public String cash_recive_name = "enter value cash_recive";
		public String creditGivenBy_name = "select creditGivenBy";
		public String creditApprovedBy_name = "select creditApprovedBy";
		public String remarks_name = "Enter remarks";
		public String billprint_btn_name = "click on billprint_btn";
		public String saved_text_name = "validate save text";
		public String ok_btn_name = "click on ok button";
		public String select_department_service_name="select required tests";
		public String checkBox_name="click on check box";
		public String bill_cancel_name = "click on cancel bill";
		public String author_by_name = "select author";
		public String reason_name = "Enter reason for cancel";
		public String refund_btn_name = "click on refund btn";
		public String canceled_tex_name = "validate text";
		public String click_ok_btn_name = "click on bill cancel ok btn";
		
		
		public void clickOnRedirecting_billbtn() {
			BaseTest.utilObj.get().getUIUtils().clickElement(Redirecting_bill_name, Redirecting_bill);
		}
		
		public void switchToWindow() throws IOException {
			BaseTest.utilObj.get().getUIUtils().getRightWindow(xlUtil.getCellData("Sheet2", 1, "Bill_pageTitle"));
		}
		
		public void labServicess() throws IOException {
			BaseTest.utilObj.get().getUIUtils().inputText(servece_name_name, servece_name, xlUtil.getCellData("Sheet2", 1, "Lab_Service"));
					
		}
		
		public void clickOnSerchBtn() {
			BaseTest.utilObj.get().getUIUtils().clickElement(search_btn_name, search_btn);
		}
		
		public void clickOnLabCheckBox() throws IOException {
			//BaseTest.utilObj.get().getUIUtils().clickElement(lab_check_box_name, lab_check_box);
			BaseTest.utilObj.get().getUIUtils().clickOnCheckbox(checkBox_name, check_box, select_department_service_name, selectDepartment_service, 
					xlUtil.getCellData("Sheet2", 1, "PackageName"));
		}
		
		public void clickOnAddBtn() {
			BaseTest.utilObj.get().getUIUtils().clickElement(add_btn_name, add_btn);
		}
		
		public void verifyBillAmount() {
			String docval = BaseTest.utilObj.get().getUIUtils().getText(doctor_fee_name,doctor_fee,true);
			System.out.println("value is " + docval);//1000.00
			double docfee = Double.parseDouble(docval);
			System.out.println("value is " + docfee);
			String labval = BaseTest.utilObj.get().getUIUtils().getText(lab_fee_name,lab_fee,true);
			System.out.println("value is " + labval);
			double labfee = Double.parseDouble(labval);
			System.out.println("value is " + labfee);
			double fee = docfee+labfee;
			System.out.println(fee);
			bill = BaseTest.utilObj.get().getUIUtils().getText(bill_amout_name, bill_amout, true);
			double totalbill = Double.parseDouble(bill);
			System.out.println("totalbill"+totalbill);
			System.out.println(totalbill);
			if(fee == totalbill) {
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
			
		}
		
		public void enterValueCashRecive() throws IOException {
			BaseTest.utilObj.get().getUIUtils().clearAndEnterText(cash_recive_name, cash_recive, xlUtil.getCellData("Sheet2", 1, "Cash"));
		}
		
		public void selectCreditGivenBy() throws IOException {
			String data = xlUtil.getCellData("Sheet2", 1, "Cridet_bill");
			BaseTest.utilObj.get().getUIUtils().selectValue(creditGivenBy_name, creditGivenBy, "Text", data);
		}
		
		public void selectCreditApprovedBy() throws IOException {
			String data = xlUtil.getCellData("Sheet2", 1, "Cridet_bill");
			BaseTest.utilObj.get().getUIUtils().selectValue(creditApprovedBy_name, creditApprovedBy, "Text", data);
		}
		
		public void enterRemarks() throws IOException {
			BaseTest.utilObj.get().getUIUtils().inputText(remarks_name, remarks, xlUtil.getCellData("Sheet2", 1, "Remarks"));
		}
		
		public void switchToAlert() {
			BaseTest.utilObj.get().getUIUtils().switchToAlertAndAccept();
		}
		
		public void clickOnPrintBillBtn() throws Exception {
			BaseTest.utilObj.get().getUIUtils().waitForSec(3);
			BaseTest.utilObj.get().getUIUtils().clickElement(billprint_btn_name, billprint_btn);
			BaseTest.utilObj.get().getUIUtils().waitForSec(10);
			BaseTest.utilObj.get().getUIUtils().tackScreenShot("Bill");
		}
		
		public void verifyBillpage() {
		 boolean status = BaseTest.utilObj.get().getUIUtils().isDisplayed(print_bill);	
		 Assert.assertTrue("Fail", status);
		}	
		
		public void switchToWindowsDefaluContent() {
			BaseTest.utilObj.get().getUIUtils().switchDefaultCintent();
		}
		
		public void clickOnOkButton() throws Exception {
			BaseTest.utilObj.get().getUIUtils().clickElement(ok_btn_name, ok_btn);
			BaseTest.utilObj.get().getUIUtils().tackScreenShot("Bill");
		}
		
		public void validate_save_btn() {
			String actual = BaseTest.utilObj.get().getUIUtils().getText(saved_text_name, saved_text, true);
			System.out.println(actual);
			Assert.assertEquals(actual, DataConfigConsatant.save_text);
		}
		
		public void clickOnBillCancelBtn() {
			BaseTest.utilObj.get().getUIUtils().clickElement(bill_cancel_name, bill_cancel);
		}
		
		public void selectAuthorBy() throws IOException {
			BaseTest.utilObj.get().getUIUtils().selectValue(author_by_name, author_by, "Text", xlUtil.getCellData("Sheet2", 1, "Cridet_bill"));
		}
		
		public void enterForReason() throws IOException {
			BaseTest.utilObj.get().getUIUtils().inputText(reason_name, reason, xlUtil.getCellData("Sheet2", 1, "Reason"));
		}
		
		public void clickOnRefundBtn() {
			BaseTest.utilObj.get().getUIUtils().waitForSec(3);
			BaseTest.utilObj.get().getUIUtils().clickElement(refund_btn_name, refund_btn);
		}
		
		public void validateCancellText() {
			String actual = BaseTest.utilObj.get().getUIUtils().getText(canceled_tex_name, canceled_txt, true);
			Assert.assertEquals(actual, DataConfigConsatant.canceled_text);
		}
		
		public void clickOnbillCancekOkBtn() {
			BaseTest.utilObj.get().getUIUtils().clickElement(click_ok_btn_name, click_ok_btn);
		}
		
		
}
