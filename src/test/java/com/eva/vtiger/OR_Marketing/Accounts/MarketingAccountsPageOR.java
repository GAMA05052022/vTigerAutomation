package com.eva.vtiger.OR_Marketing.Accounts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.eva.vtiger.Based_Page.Base_PageFunction;

import GenericMethodsFolder.WebUtil;

public class MarketingAccountsPageOR extends Base_PageFunction{

	public MarketingAccountsPageOR(WebUtil gn) {
		super(gn);
	}
	@FindBy(xpath = "//img[@title='Create Account...']")
	protected WebElement createAccountBt;
	@FindBy(xpath = "//input[@name='search_text']")
	protected WebElement searchBox;
	@FindBy(xpath = "//input[@value=' Search Now ']")
	protected WebElement searchBt;
	

}
