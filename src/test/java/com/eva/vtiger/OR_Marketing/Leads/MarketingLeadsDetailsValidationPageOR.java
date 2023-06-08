package com.eva.vtiger.OR_Marketing.Leads;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.eva.vtiger.Based_Page.Base_PageFunction;
import GenericMethodsFolder.WebUtil;

public class MarketingLeadsDetailsValidationPageOR extends Base_PageFunction{
	@FindBy(xpath = "//span[@id='dtlview_First Name']")
	protected WebElement firstName;

	public MarketingLeadsDetailsValidationPageOR(WebUtil gn) {
		super(gn);
	}
}
