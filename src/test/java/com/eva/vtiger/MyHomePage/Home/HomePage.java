package com.eva.vtiger.MyHomePage.Home;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.Marketing.Accounts.MarketingAccountsPage;
import com.eva.vtiger.Marketing.Leads.MarketingLeadsPage;
import com.eva.vtiger.OR_MyHomePage.Home.HomePageOR;
import com.eva.vtiger.Sales.Invice.SalesInvoiceLandingPage;

import GenericMethodsFolder.WebUtil;

public class HomePage extends HomePageOR {

	private WebUtil gn;

	public HomePage(WebUtil gn) {
		super(gn);
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public void gotoCampaigns() throws IOException {
		gn.click(marketingModule, "Marketing");
	}

	public MarketingAccountsPage gotoAccounts() throws IOException {
		gn.actionMouseOver(marketingModule, "Marketing");
		gn.click(accountSubM, "Accounts");
		return new MarketingAccountsPage(gn);
	}

	public MarketingLeadsPage gotoLeads()  {
		gn.actionMouseOver(marketingModule, "Marketing");
		gn.click(leadsSubM, "Leads");
		return new MarketingLeadsPage(gn);
	}

	public void gotoDocuments()  {
		gn.actionMouseOver(marketingModule, "Marketing");
		gn.click(documentsSubM, "Documents");
	}
	
	public SalesInvoiceLandingPage gotoInvoice()  {
		gn.actionMouseOver(salaesModule, "Sales");
		gn.click(invoiceSubM, "Invoice");
		return new SalesInvoiceLandingPage(gn);
	}

	public void verifyHomePage()  {
		gn.verifyHomePage("admin - My Home Page - Home - vtiger CRM 5 - Commercial Open Source CRM");
	}

	public void verifyVisibleMarketing(String elementName) {
		gn.validateVisibleText(marketingModule, elementName);
	}
	
}
