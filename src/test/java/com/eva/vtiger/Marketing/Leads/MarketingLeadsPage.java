package com.eva.vtiger.Marketing.Leads;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.eva.vtiger.OR_Marketing.Leads.MarketingLeadsPageOR;

import GenericMethodsFolder.WebUtil;

public class MarketingLeadsPage extends MarketingLeadsPageOR {
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement createLeadsBt;

	private WebUtil gn;

	public MarketingLeadsPage(WebUtil gn) {
		super(gn);
		this.gn = gn;
	}

	public MarkCreateLeadsPage clickCreateLeadsButton()  {
		gn.click(createLeadsBt, "createLeadsButton");
		return new MarkCreateLeadsPage(gn);
	}

	public void verifyLeadsTable(List<String> expectedList)  {
		gn.validateMultipleText("//a[@title='Leads']", "xpath", expectedList, "LeadsTable");
	}
}
