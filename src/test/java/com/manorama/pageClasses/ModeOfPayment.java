package com.manorama.pageClasses;

import java.io.IOException;

import org.openqa.selenium.By;

import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.XLUtility;

public class ModeOfPayment {
	
	XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());
	

	public By mode_of_payment=By.xpath("//a[text()='Mode Of Payment']");
	public By select_payment_type=By.xpath("//select[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_ddlPaymentType_0']");
	public By enter_card_number=By.xpath("//input[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_txtCardNo_0']");
	public By bank_name_details=By.xpath("//input[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_ddlEditableBankName_0']");
	public By branch_name_details=By.xpath("//input[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_ddlEditableBranchName_0']");
	public By enter_amount=By.xpath("//input[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_txtAmount_0']");
	public By approval_number=By.xpath("//input[@id='cntPlcHolder_ucPaymentDetails_gvPayementDetails_txtBatchNo_0']");
	
	public String modeOfPayment_text="click on mode of payment";
	public String select_payment_type_name="click on select payment type";
	public String enter_card_number_name="enter data in card field";
	public String bank_details_name="enter bank details";
	public String branch_details_name="enter branch details";
	public String enter_amount_name="enter the amount";
	public String approval_number_name="enter pproval number";
	
	public void ClickOnModeOfPayment() {
		BaseTest.utilObj.get().getUIUtils().clickElement(modeOfPayment_text, mode_of_payment);
	}
	
	public void select_Payment_type() throws IOException {
		BaseTest.utilObj.get().getUIUtils().selectValue(select_payment_type_name, select_payment_type, "Text", xlUtil.getCellData("Sheet3", 1, "Mode"));
//		BaseTest.utilObj.get().getUIUtils().sendKeysWithKeysTAB(select_payment_type_name, select_payment_type);
//		BaseTest.utilObj.get().getUIUtils().inputTextUsingActionClass(enter_card_number_name, enter_card_number, xlUtil.getCellData("Login_page", 1, "cardNumber"));
//		BaseTest.utilObj.get().getUIUtils().waitForSec(10);
 
 
	}
	public void enter_card_number() throws IOException {
		try {
		BaseTest.utilObj.get().getUIUtils().inputTextUsingJavascriptExecutor(enter_card_number_name, enter_card_number, xlUtil.getCellData("Sheet3", 1, "Card"));
		String data=BaseTest.utilObj.get().getUIUtils().getText(enter_card_number_name, enter_card_number,true);
		System.out.println(data);
		BaseTest.utilObj.get().getUIUtils().waitForSec(10);
		}
 
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void enter_bankName() throws IOException {
		try {
			BaseTest.utilObj.get().getUIUtils().inputTextUsingJavascriptExecutor(bank_details_name, bank_name_details, xlUtil.getCellData("Sheet3", 1, "BankName"));
			String data=BaseTest.utilObj.get().getUIUtils().getText(bank_details_name, bank_name_details, true);
			System.out.println(data);
	}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	public void enter_branch_name() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputTextUsingJavascriptExecutor(branch_details_name, branch_name_details, xlUtil.getCellData("Sheet3", 1, "BranchName"));
	}
	public void enter_the_amount() throws IOException {
		try {
		
		BaseTest.utilObj.get().getUIUtils().inputTextUsingJavascriptExecutor(enter_amount_name, enter_amount,OdpBillPage.bill);
		BaseTest.utilObj.get().getUIUtils().waitForSec(10);
		}
		catch(Exception e) {
//		BaseTest.utilObj.get().getUIUtils().inputText(enter_amount_name, enter_amount, xlUtil.getCellData("Login_page", 1, "Amount"));
	}}
	public void enter_the_approval_number() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputTextUsingJavascriptExecutor(approval_number_name, approval_number, xlUtil.getCellData("Sheet3", 1, "ApprovalNumber"));
	}

}
