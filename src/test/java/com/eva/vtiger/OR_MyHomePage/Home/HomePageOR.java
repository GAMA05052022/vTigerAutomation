package com.eva.vtiger.OR_MyHomePage.Home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.eva.vtiger.Based_Page.Base_PageFunction;
import GenericMethodsFolder.WebUtil;

public class HomePageOR extends Base_PageFunction{

	@FindBy(linkText = "Marketing")
	protected WebElement marketingModule;
	@FindBy(linkText = "Accounts")
	protected WebElement accountSubM;
	@FindBy(linkText = "Leads")
	protected WebElement leadsSubM;
	@FindBy(linkText = "Documents")
	protected WebElement documentsSubM;
	@FindBy(linkText = "Sales")
	protected WebElement salaesModule;
	@FindBy(linkText = "Invoice")
	protected WebElement invoiceSubM;

	public HomePageOR(WebUtil gn) {
		super(gn);
	}
}
