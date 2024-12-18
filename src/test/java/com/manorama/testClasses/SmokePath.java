package com.manorama.testClasses;

import org.testng.annotations.Test;
import com.manorama.framework.BaseTest;
import com.manorama.pageClasses.LoginPage;
import com.manorama.pageClasses.Manorama_Registrationpage;
import com.manorama.pageClasses.ModeOfPayment;
import com.manorama.pageClasses.OdpBillPage;
import com.manorama.pageClasses.SearchPage;
import com.manorama.pageClasses.UpdatePatient;
import com.manorama.pageClasses.VisitDetails;

public class SmokePath extends BaseTest{
	
	@Test(priority = 0, description = "Manorama Registration")
	public void registration_Manorama() throws Exception {

		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		OdpBillPage billpage = new OdpBillPage();
		
		login.loginManorama();
		register.checkRegistration();
		register.checkRegistrationPresent_Dev();
		register.get_Serach_lint();
		register.select_prefix_dropdown();
		register.enterFristName();
		register.enteMiddleName();
		register.enterLastName();
		register.verifynameOnHealthcard();
		register.verifyNameOnPrint();
//		register.enterDate();
//		register.enterMonth();
		register.enterYear();
		register.validate_gender();
		register.enterMobileNumber(1);
		register.enterPermanentAddress();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		register.selectPatientCatagory();
		register.selectPatientCompany();
		register.clickOnClaculateValidity();
		register.verifyConsultationCharges();
		register.clickOnSaveBtn();
		register.saveSuccessFullyMsg();
		register.verifyVisitDetails();
		register.getDataUHID();
		register.verifyPatientname();
		register.getDataVisitNo();
		register.verifyDepartmentOfPatient();
		register.verifyCatagoryname();
		register.clickOncloseBtn();
		billpage.clickOnRedirecting_billbtn();
		billpage.switchToWindow();
		billpage.labServicess();
		billpage.clickOnSerchBtn();
		billpage.clickOnLabCheckBox();
		billpage.clickOnAddBtn();
		billpage.verifyBillAmount();
		billpage.enterValueCashRecive();
		billpage.selectCreditGivenBy();
		billpage.selectCreditApprovedBy();
		billpage.enterRemarks();
		register.clickOnSaveBtn();
		billpage.switchToWindowsDefaluContent();
		billpage.validate_save_btn();
		billpage.clickOnOkButton();
		billpage.clickOnPrintBillBtn();
		login.clickOnLogOut();
	}
	
	
	@Test(priority = 1, description = "Manorama Registration_bill-cancel")
	public void registration_bill_cancel_Manorama() throws Exception {

		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		OdpBillPage billpage = new OdpBillPage();
		
		login.loginManorama();
		register.checkRegistration();
		register.checkRegistrationPresent_Dev();
		register.get_Serach_lint();
		register.select_prefix_dropdown();
		register.enterFristName();
		register.enteMiddleName();
		register.enterLastName();
		register.verifynameOnHealthcard();
		register.verifyNameOnPrint();
//		register.enterDate();
//		register.enterMonth();
		register.enterYear();
		register.validate_gender();
		register.enterMobileNumber(2);
		register.enterPermanentAddress();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		register.selectPatientCatagory();
		register.selectPatientCompany();
		register.clickOnClaculateValidity();
		register.verifyConsultationCharges();
		register.clickOnSaveBtn();
		register.saveSuccessFullyMsg();
		register.verifyVisitDetails();
		register.getDataUHID();
		register.verifyPatientname();
		register.getDataVisitNo();
		register.verifyDepartmentOfPatient();
		register.verifyCatagoryname();
		register.clickOncloseBtn();
		billpage.clickOnRedirecting_billbtn();
		billpage.switchToWindow();
		billpage.labServicess();
		billpage.clickOnSerchBtn();
		billpage.clickOnLabCheckBox();
		billpage.clickOnAddBtn();
		billpage.verifyBillAmount();
		billpage.enterValueCashRecive();
		billpage.selectCreditGivenBy();
		billpage.selectCreditApprovedBy();
		billpage.enterRemarks();
		register.clickOnSaveBtn();
		billpage.switchToWindowsDefaluContent();
		billpage.validate_save_btn();
		billpage.clickOnOkButton();
		billpage.clickOnBillCancelBtn();
		billpage.selectAuthorBy();
		billpage.enterForReason();
		billpage.clickOnbillCancekOkBtn();
		//billpage.clickOnRefundBtn();
		billpage.validateCancellText();
		billpage.clickOnOkButton();
		login.clickOnLogOut();
	}
	
	@Test(priority = 2, description = "Update patient")
	public void update_patient_name() throws Exception {

		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		UpdatePatient update = new UpdatePatient();
		login.loginManorama();
		register.checkRegistration();
		register.checkRegistrationPresent_Dev();
		register.get_Serach_lint();
		register.select_prefix_dropdown();
		register.enterFristName();
		register.enteMiddleName();
		register.enterLastName();
		register.verifynameOnHealthcard();
		register.verifyNameOnPrint();
//		register.enterDate();
//		register.enterMonth();
		register.enterYear();
		register.validate_gender();
		register.enterMobileNumber(3);
		register.enterPermanentAddress();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		register.selectPatientCatagory();
		register.selectPatientCompany();
		register.clickOnClaculateValidity();
		register.verifyConsultationCharges();
		register.clickOnSaveBtn();
		register.saveSuccessFullyMsg();
		register.verifyVisitDetails();
		register.getDataUHID();
		register.verifyPatientname();
		register.getDataVisitNo();
		register.verifyDepartmentOfPatient();
		register.verifyCatagoryname();
		register.clickOncloseBtn();
		update.clickOnUpdatePatient();
		update.switchFrame();
		update.enterValueSearcBox();
		update.clickOnserch_btn();
		update.getData();
		update.clickOnclickHereBtn();
		login.clickOnLogOut();
}
	
	@Test(priority = 3, description = "Visit patient details")
	public void visitDetails() throws Exception {

		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		UpdatePatient update = new UpdatePatient();
		VisitDetails details = new VisitDetails();
		
		login.loginManorama();
		register.checkRegistration();
		register.checkRegistrationPresent_Dev();
		register.get_Serach_lint();
		register.select_prefix_dropdown();
		register.enterFristName();
		register.enteMiddleName();
		register.enterLastName();
		register.verifynameOnHealthcard();
		register.verifyNameOnPrint();
//		register.enterDate();
//		register.enterMonth();
		register.enterYear();
		register.validate_gender();
		register.enterMobileNumber(4);
		register.enterPermanentAddress();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		register.selectPatientCatagory();
		register.selectPatientCompany();
		register.clickOnClaculateValidity();
		register.verifyConsultationCharges();
		register.clickOnSaveBtn();
		register.saveSuccessFullyMsg();
		register.verifyVisitDetails();
		register.getDataUHID();
		register.verifyPatientname();
		register.getDataVisitNo();
		register.verifyDepartmentOfPatient();
		register.verifyCatagoryname();
		register.clickOncloseBtn();
		details.clickOnVisitDetails();
		details.switchIframe();
		update.enterValueSearcBox();
		update.clickOnserch_btn();
		update.getData();
		details.clickOnclickHereBtn();
		login.clickOnLogOut();
}
	
	@Test(priority = 4, description = "Mode of patent")
	public void modeOfPayment() throws Exception {

		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		OdpBillPage billpage = new OdpBillPage();
		ModeOfPayment payment = new ModeOfPayment();
		
		login.loginManorama();
		register.checkRegistration();
		register.checkRegistrationPresent_Dev();
		register.get_Serach_lint();
		register.select_prefix_dropdown();
		register.enterFristName();
		register.enteMiddleName();
		register.enterLastName();
		register.verifynameOnHealthcard();
		register.verifyNameOnPrint();
//		register.enterDate();
//		register.enterMonth();
		register.enterYear();
		register.validate_gender();
		register.enterMobileNumber(5);
		register.enterPermanentAddress();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		register.selectPatientCatagory();
		register.selectPatientCompany();
		register.clickOnClaculateValidity();
		register.verifyConsultationCharges();
		register.clickOnSaveBtn();
		register.saveSuccessFullyMsg();
		register.verifyVisitDetails();
		register.getDataUHID();
		register.verifyPatientname();
		register.getDataVisitNo();
		register.verifyDepartmentOfPatient();
		register.verifyCatagoryname();
		register.clickOncloseBtn();
		billpage.clickOnRedirecting_billbtn();
		billpage.switchToWindow();
		billpage.labServicess();
		billpage.clickOnSerchBtn();
		billpage.clickOnLabCheckBox();
		billpage.clickOnAddBtn();
		billpage.verifyBillAmount();
		payment.ClickOnModeOfPayment();
		payment.select_Payment_type();
		payment.enter_card_number();
		payment.enter_bankName();
		payment.enter_branch_name();
		payment.enter_the_amount();
		payment.enter_the_approval_number();
		register.clickOnSaveBtn();
		billpage.clickOnOkButton();
		login.clickOnLogOut();
}
	
	@Test(priority = 5, description = "search functions")
	public void search_functions() throws Exception {
		
		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		SearchPage search = new SearchPage();
		
		login.loginManorama();
		register.clickOnSearchlnk(); 
		search.enter_uhid_num();
		search.click_search_btn();
		login.clickOnLogOut();
	}
	
	@Test(priority = 6, description = "search functions")
	public void patient_followup_vist() throws Exception {
		LoginPage login = new LoginPage();
		Manorama_Registrationpage register = new Manorama_Registrationpage();
		SearchPage search = new SearchPage();
		OdpBillPage billpage = new OdpBillPage();
		
		login.loginManorama();
		register.clickOnSearchlnk(); 
		search.enter_uhid_num();
		search.click_search_btn();
		register.clickOndepartment();
		register.getDepartmentList();
		register.clickOnConsultingDoctor();
		register.getDoctorsList();
		search.click_alert_ok_btn();
		register.clickOnClaculateValidity();
		search.click_save_visit_btn();
		register.clickOncloseBtn();
		billpage.clickOnRedirecting_billbtn();
		billpage.switchToWindow();
		search.click_patient_due_cross_btn();
		billpage.labServicess();
		billpage.clickOnSerchBtn();
		billpage.clickOnLabCheckBox();
		billpage.clickOnAddBtn();
		//billpage.verifyBillAmount();
		billpage.enterValueCashRecive();
		billpage.selectCreditGivenBy();
		billpage.selectCreditApprovedBy();
		billpage.enterRemarks();
		register.clickOnSaveBtn();
		billpage.validate_save_btn();
		billpage.clickOnOkButton();
		billpage.clickOnPrintBillBtn();
		login.clickOnLogOut();
	}
	
}
