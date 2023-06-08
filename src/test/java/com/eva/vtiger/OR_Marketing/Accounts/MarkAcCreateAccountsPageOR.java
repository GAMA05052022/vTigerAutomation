package com.eva.vtiger.OR_Marketing.Accounts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.eva.vtiger.Based_Page.Base_PageFunction;

import GenericMethodsFolder.WebUtil;

public class MarkAcCreateAccountsPageOR extends Base_PageFunction{
	public MarkAcCreateAccountsPageOR(WebUtil gn) {
		super(gn);
	}
	@FindBy(xpath = "//input[@name='accountname']")
	protected WebElement accountName;
	@FindBy(xpath = "//b[text()='Description Information']//parent::td//parent::tr//parent::tbody//tr//input[@value='  Cancel  ']")
	protected WebElement cancelBt;
}
