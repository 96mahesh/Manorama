package com.manorama.setup;

import com.manorama.framework.AssertManager;
import com.manorama.framework.BaseTest;
import com.manorama.utils.DBUtils;
import com.manorama.utils.DataLoaders;
import com.manorama.utils.UIUtils;

public class InitUtilObjects {
	private UIUtils uiutil;
	private AssertManager assertManager;
	private DBUtils dbUtil;
	private DataLoaders data ;
	
	public InitUtilObjects() {
		uiutil = new UIUtils();
		assertManager = new AssertManager();
		dbUtil = new DBUtils();
		data = new DataLoaders();
	}
	
	public void setupTestObj() {
		BaseTest.utilObj.get().uiutil.setupTestObj();
		BaseTest.utilObj.get().assertManager.setupTestObj();
		BaseTest.utilObj.get().data.setupTestObj();
	}
	
	public UIUtils getUIUtils() {
		return uiutil;
	}
	
	public DataLoaders getDataLoaders() {
		return data;
	}
	
	public AssertManager getAssertManager() {
		return assertManager;
	}
	
	public DBUtils getDBUtils() {
		return dbUtil;
	}

}
