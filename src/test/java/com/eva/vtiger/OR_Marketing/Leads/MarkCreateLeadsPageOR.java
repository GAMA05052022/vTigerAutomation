package com.eva.vtiger.OR_Marketing.Leads;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.eva.vtiger.Based_Page.Base_PageFunction;

import GenericMethodsFolder.WebUtil;

public class MarkCreateLeadsPageOR extends Base_PageFunction {
	@FindBy(xpath = "//input[@name='firstname']")
	protected WebElement firstName;
	@FindBy(xpath = "//input[@name='lastname']")
	protected WebElement lastName;
	@FindBy(xpath = "//input[@name='company']")
	protected WebElement companyName;
	@FindBy(id = "mobile")
	protected WebElement mobileNo;
	@FindBy(xpath = "//b[text()='Description Information']//parent::td//parent::tr//following-sibling::tr//input[@value='  Save  ']")
	protected WebElement saveBt;
	@FindBy(xpath = "//b[text()='Description Information']//parent::td//parent::tr//following-sibling::tr//input[@value='  Cencal  ']")
	protected WebElement cancelBt;

	public MarkCreateLeadsPageOR(WebUtil gn) {
		super(gn);
	}
}
