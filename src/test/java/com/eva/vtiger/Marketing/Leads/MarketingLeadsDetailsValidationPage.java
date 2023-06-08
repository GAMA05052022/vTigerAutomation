package com.eva.vtiger.Marketing.Leads;

import org.openqa.selenium.support.PageFactory;
import com.eva.vtiger.OR_Marketing.Leads.MarketingLeadsDetailsValidationPageOR;
import GenericMethodsFolder.WebUtil;

public class MarketingLeadsDetailsValidationPage extends MarketingLeadsDetailsValidationPageOR {

	private WebUtil gn;

	public MarketingLeadsDetailsValidationPage(WebUtil gn) {
		super(gn);
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public void validateText(String expectedText) {
		gn.validateText(firstName, expectedText, "firstName");
	}
}
