package com.eva.vtiger.Sales.Invice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.eva.vtiger.Based_Page.Base_PageFunction;
import GenericMethodsFolder.WebUtil;

public class AccountsSearchOR extends Base_PageFunction {

	@FindBy(xpath = "//a[text()='Ram']")
	protected WebElement accountName;

	@FindBy(xpath = "//a[text()='abcd1234']")
	protected WebElement iteamName;
	
	public AccountsSearchOR(WebUtil gn) {
		super(gn);
	}

}
