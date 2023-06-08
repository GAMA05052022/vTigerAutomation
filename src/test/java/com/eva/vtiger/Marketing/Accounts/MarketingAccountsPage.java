package com.eva.vtiger.Marketing.Accounts;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.OR_Marketing.Accounts.MarketingAccountsPageOR;

import GenericMethodsFolder.WebUtil;

public class MarketingAccountsPage extends MarketingAccountsPageOR{

	private WebUtil gn;
	public MarketingAccountsPage(WebUtil gn) {
		super(gn);
		this.gn=gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public MarkAcCreateAccountsPage createAccountButton() throws IOException {
		gn.click(createAccountBt, "CreateAccountButton");
		return new MarkAcCreateAccountsPage(gn);
	}

	public void delete() {

	}

	public void sendMail() {

	}


	public void sendSMS() {

	}

}
