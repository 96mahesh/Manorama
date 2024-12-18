package com.manorama.pageClasses;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.manorama.framework.BaseTest;
import com.manorama.setup.TestConfig;
import com.manorama.utils.DataConfigConsatant;
import com.manorama.utils.XLUtility;

import junit.framework.Assert;

public class Manorama_Registrationpage{

     XLUtility xlUtil = new XLUtility(TestConfig.getInstance().getExcel_path());
     
     String prefex;
     String firtname;
     String middl_name;
     String last_name;
     String finalname;
     String fullname;
     String patient_department;
     String consult_doctor;
     String catagory;
     String patient;
     static String  uhid;

	public By RegistrationPage = By.xpath("//a[text()='Registration' and @id = 'ancModule']");
	public By Serach_Link = By.xpath("//a[contains(text(),'Search Patient')]");
	public By Prefix = By.xpath("//select[@id='cntPlcHolder_ddlPrefix']");
	public By firstname = By.xpath("//input[@placeholder='First Name']");
	public By middlename = By.xpath("//input[@placeholder='Middle Name']");
    public By lastName = By.xpath("//input[@placeholder='Last Name']");
    public By nameOnHealthCard = By.xpath("//input[@placeholder='Name On Healthcard']");
    public By nameOnPrint = By.xpath("//input[@placeholder='Name On Print']");
    public By dd = By.xpath("//input[@placeholder='DD']");
    public By mm = By.xpath("//input[@placeholder='MM']");
    public By year = By.xpath("//input[@placeholder='Year']");
    public By gender = By.xpath("//select[@id='cntPlcHolder_ddlGender']");
    public By mobileumber = By.xpath("//input[@placeholder='Mobile Number']");
    public By permanentaddress = By.xpath("//textarea[@placeholder='Permanent Address']");
    public By department = By.xpath("//button[@id='btnSelectDept' and @onclick='GetDepartmentList(event);']");
    public By department_list = By.xpath("//table[@id='tblDepartment']//tr//td//input");
    public By consulting_doctor = By.xpath("//button[@id='btnSelectConsultingDoctor']");
    public By consulting_doctor_list = By.xpath("//table[@id='tblConsultingDoctor']//tr//td//input");
    public By patient_Category = By.xpath("//select[@id='cntPlcHolder_ddlPatType']");
    public By patient_company= By.xpath("//select[@id='cntPlcHolder_ddlPatientCompany']");
    public By clickcalculatevalidity = By.xpath("//input[@value='Calculate Validity']");
    public By consultation_charge = By.xpath("//input[@placeholder='ConsultationCharges']");
    public By total_charges = By.xpath("//input[@placeholder='Total']");
    public By clicksavebtn = By.xpath("//input[@value='Save']");
    public By savesuccessfully = By.xpath("//label[text()='Saved Successfully']");
    public By visitbody = By.xpath("//label[text()='Saved Successfully']//following::div[@id='divAdd']");
    public By UHID = By.xpath("//span[@id='cntPlcHolder_lblModalUHID']");
    public By name = By.xpath("//span[@id='cntPlcHolder_lblModalName']");
    public By visitno = By.xpath("//span[@id='cntPlcHolder_lblModalVisitCode']");
    public By department_patient = By.xpath("//span[@id='cntPlcHolder_lblModalDeptName']");
    public By category_patitent = By.xpath("//span[@id='cntPlcHolder_lblModalPatCategory']");
    public By close_btn = By.xpath("//input[@id='cntPlcHolder_btnModalCancel']");
    
	
	public String Prefix_name = "Select_dropdown_Prefix";
	public String RegistrationPage_name  = "Registration_page-is_availeble";
	public String serach_link_name = "Search link is availble";
	public String firstname_val = "Enter name";
	public String middlename_val = "Enter middle name";
	public String lastname_val = "Enter Last name";
	public String nameOnHealthCard_name = "Verify Helth card name";
	public String nameOnPrint_name = "verify name on print";
	public String dd_name = "Enter_date";
	public String mm_name = "Enter_month";
	public String year_name = "Enter_year_name";
	public String gender_name = "Geneder is aviable";
	public String mobileumber_name = "mobileumber enter";
	public String permanentaddress_name = "enter permanent address";
	public String department_name = "click_on_department";
	public String department_list_name = "get data from department";
	public String consulting_doctor_name = "click doctor icon";
	public String consulting_doctor_list_name = "select doctor";
	public String patient_Category_name = "select category of patient";
	public String patient_company_name = "select patient company";
	public String clickcalculatevalidity_name = "click calculate validity";
	public String consultation_charge_name = "get consultation charges";
	public String total_charges_name = "get total charges";
	public String clicksavebtn_name = "click save btn";
	public String savesuccessfully_name = " save success fully";
	public String visitbody_name = "visit details is available";
	public String UHID_name = "Get text UHID";
	public String name_patient = "verify patient name";
	public String visitno_name = "get vist no";
	public String department_patient_name = "verify department of patient";
	public String category_patitent_name = "verify catagory";
	public String close_btn_name = "click close btn";
	
	
	public void checkRegistration() throws IOException {
		BaseTest.utilObj.get().getUIUtils().validatePageTitle(xlUtil.getCellData("Manorama", 1, "Registration_pageTitle"), false);
	}
	
	public void checkRegistrationPresent_Dev() {
		boolean status = BaseTest.utilObj.get().getUIUtils().isDisplayed(RegistrationPage);
		Assert.assertTrue("Test script pass", status);
//		boolean status = BaseTest.utilObj.get().getUIUtils().objWait(RegistrationPage_name, RegistrationPage, "3", true);
//		Assert.assertTrue("Test script pass", status);	
	}
	
	public void get_Serach_lint() {
		String data = BaseTest.utilObj.get().getUIUtils().getText(serach_link_name, Serach_Link, true);
		Assert.assertEquals(data, DataConfigConsatant.Search_text);	
	}
	
	public void clickOnSearchlnk() {
		BaseTest.utilObj.get().getUIUtils().clickElement(serach_link_name, Serach_Link);
	}
	
	public void select_prefix_dropdown() throws IOException {
		prefex = xlUtil.getCellData("Manorama", 1, "Prefix_val");
		BaseTest.utilObj.get().getUIUtils().selectValue(Prefix_name, Prefix, "Text", prefex);
	}
	
	public void enterFristName() throws IOException {
		firtname = xlUtil.getCellData("Sheet1", 1, "FIRST_NAME");
		BaseTest.utilObj.get().getUIUtils().inputText(firstname_val, firstname, firtname);
	}
	
	public void enteMiddleName() throws IOException{
		middl_name =  xlUtil.getCellData("Sheet1", 1, "MIDDLE_NAME");
		BaseTest.utilObj.get().getUIUtils().inputText(middlename_val, middlename,middl_name);
	}
	
	public void enterLastName() throws IOException {
		last_name = xlUtil.getCellData("Sheet1", 1, "LAST_NAME");
		BaseTest.utilObj.get().getUIUtils().inputText(lastname_val, lastName,last_name);
	}
	
	public void verifynameOnHealthcard() {
		finalname = firtname+""+middl_name+" "+last_name;
		System.out.println("final name :" + finalname);
		String data = BaseTest.utilObj.get().getUIUtils().getText(nameOnHealthCard_name, nameOnHealthCard, true);
		System.err.println("final name data :" +data);
		if(finalname.contains(data)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
	}
	
	public void verifyNameOnPrint() {
		fullname = prefex+" "+firtname+""+middl_name+" "+last_name;
		patient = BaseTest.utilObj.get().getUIUtils().getText(nameOnPrint_name, nameOnPrint, true);
		if(fullname.contains(patient)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	
	public void enterDate() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(dd_name, dd, xlUtil.getCellData("Sheet1", 1, "DD"));
	}
	
	public void enterMonth() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(mm_name, mm, xlUtil.getCellData("Sheet1", 1, "MM"));
	}
	
	public void enterYear() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(year_name, year, xlUtil.getCellData("Sheet1", 1, "YYYY"));
	}
	
	public void validate_gender() {
		String value = BaseTest.utilObj.get().getUIUtils().getText(gender_name, gender, false);
		System.out.println(value);
		if(value.contains("MALE")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	public void enterMobileNumber(int i) throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(mobileumber_name, mobileumber, xlUtil.getCellData("Sheet1", i, "MOBILE"));
	}
	
	public void enterPermanentAddress() throws IOException {
		BaseTest.utilObj.get().getUIUtils().inputText(permanentaddress_name, permanentaddress, xlUtil.getCellData("Sheet1", 1, "ADDRESS"));
	}
	
	public void enterCellNumber() throws Exception {
		BaseTest.utilObj.get().getUIUtils().inputText(mobileumber_name, mobileumber, xlUtil.getCellData("Sheet1", 2, "MOBILE"));
	}
	
	public void clickOndepartment() throws Exception {		
		BaseTest.utilObj.get().getUIUtils().scrollIntoView(department);
		BaseTest.utilObj.get().getUIUtils().clickElement(department_name, department);
	}
	
	public void getDepartmentList() throws IOException {
		int data = BaseTest.utilObj.get().getUIUtils().objCnt(department_list);
		patient_department = xlUtil.getCellData("Sheet1", 1, "DEPARTMENT");
		String Datalist = BaseTest.utilObj.get().getUIUtils().getListOfdataElement(department_list_name, department_list,patient_department);
		System.out.println(Datalist);
		System.out.println(data);
		
	}
	
	public void clickOnConsultingDoctor() throws Exception {
		BaseTest.utilObj.get().getUIUtils().clickElement(consulting_doctor_name, consulting_doctor);
	}
	
	public void getDoctorsList() throws IOException {
		int data = BaseTest.utilObj.get().getUIUtils().objCnt(consulting_doctor_list);
		consult_doctor = xlUtil.getCellData("Sheet1", 1, "DOCTOR");
	    BaseTest.utilObj.get().getUIUtils().getListOfdataElement(consulting_doctor_list_name, consulting_doctor_list, consult_doctor);
	    System.out.println(data);
		
	}
	
	public void selectPatientCatagory() throws IOException  {
		catagory = xlUtil.getCellData("Sheet1", 1, "CATEGORY");
		BaseTest.utilObj.get().getUIUtils().selectValue(patient_Category_name, patient_Category, "Text", catagory );
	}
	
	public void selectPatientCompany() throws Exception {
		//BaseTest.utilObj.get().getUIUtils().selectValue(patient_company_name, patient_company, "Text", xlUtil.getCellData("Sheet1", 1, "COMPANY"));
		for (int i = 0; i < 3; i++) {
			try {
				BaseTest.utilObj.get().getUIUtils().selectValue(patient_company_name, patient_company, "Text",
						xlUtil.getCellData("Sheet1", 1, "COMPANY"));
				break;
			} catch (StaleElementReferenceException e) {
				
					// TODO Auto-generated catch block
			        System.out.println("Attempt " + (i + 1) + " - Retrying...");
				}
			}
			
	}
	
	public void clickOnClaculateValidity() throws Exception {
		BaseTest.utilObj.get().getUIUtils().clickElement(clickcalculatevalidity_name, clickcalculatevalidity);
		BaseTest.utilObj.get().getUIUtils().tackScreenShot("image");
	}
	
	public void verifyConsultationCharges() {
		String actual = BaseTest.utilObj.get().getUIUtils().getText(consultation_charge_name, consultation_charge, true);
		String excepted = BaseTest.utilObj.get().getUIUtils().getText(total_charges_name, total_charges, true);
		if(actual.equals(excepted)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	public void clickOnSaveBtn() throws Exception {
		BaseTest.utilObj.get().getUIUtils().clickElement(clicksavebtn_name, clicksavebtn);
		BaseTest.utilObj.get().getUIUtils().waitForSec(5);
	}
	
	public void saveSuccessFullyMsg() {
		BaseTest.utilObj.get().getUIUtils().waitForSec(15);
		String data = BaseTest.utilObj.get().getUIUtils().getText(savesuccessfully_name, savesuccessfully, true);
		System.out.println(data);
		Assert.assertEquals(data, DataConfigConsatant.success_msg);
	}
	
	public void verifyVisitDetails() throws Exception {
		BaseTest.utilObj.get().getUIUtils().isDisplayed(visitbody);
		BaseTest.utilObj.get().getUIUtils().tackScreenShot("UHID");
	}
	
	public void getDataUHID() {
		uhid =BaseTest.utilObj.get().getUIUtils().getText(UHID_name, UHID, true);
		System.out.println(uhid);
		
	}
	
	public void verifyPatientname() {
		String actualname = BaseTest.utilObj.get().getUIUtils().getText(name_patient, name, true);
		System.out.println("actualname : "+actualname);
		System.out.println("patient :"+patient);
		if(actualname.equalsIgnoreCase(patient)){
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	public void getDataVisitNo() {
		String visitdata = BaseTest.utilObj.get().getUIUtils().getText(visitno_name, visitno, true);
		System.out.println(visitdata);
	}
	
	public void verifyDepartmentOfPatient() {
		String department_name = BaseTest.utilObj.get().getUIUtils().getText(department_patient_name, department_patient, true);
		if(department_name.equalsIgnoreCase(patient_department)) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	public void verifyCatagoryname() {
		String catagory =  BaseTest.utilObj.get().getUIUtils().getText(category_patitent_name, category_patitent);
		if(catagory.equalsIgnoreCase(catagory)) {
			Assert.assertTrue(true);
		}
		else {
		Assert.assertTrue(false);
		}
		
	}
	
	public void clickOncloseBtn() {
		BaseTest.utilObj.get().getUIUtils().clickElement(close_btn_name, close_btn);
	}
	
	
	
	
}
