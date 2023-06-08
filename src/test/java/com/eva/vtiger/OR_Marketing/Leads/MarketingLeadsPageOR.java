package com.eva.vtiger.OR_Marketing.Leads;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Based_Page.Base_PageFunction;

import GenericMethodsFolder.WebUtil;

public class MarketingLeadsPageOR extends Base_PageFunction {
	@FindBy(xpath = "//img[@title='Create Lead...']")
	protected WebElement createLeadsBt;

	public MarketingLeadsPageOR(WebUtil gn) {
		super(gn);
		PageFactory.initElements(gn.getDriver(), this);
	}
}
