package com.eva.vtiger.Marketing.Accounts;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.OR_Marketing.Accounts.MarkAcCreateAccountsPageOR;
import GenericMethodsFolder.WebUtil;

public class MarkAcCreateAccountsPage extends MarkAcCreateAccountsPageOR {

	private WebUtil gn;

	public MarkAcCreateAccountsPage(WebUtil gn) {
		super(gn);
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public MarkAcCreateAccountsPage basicInformation() throws IOException {
		gn.inputTextValue("Ram", accountName, "Account Name");
		return this;
	}

	public void cancelButton(WebUtil gn) throws IOException {
		gn.click(cancelBt, "cancelButton");
	}

	public void moreInformation() throws IOException {
		// gn.click("//b[text()='More Information ']", "xpath", "more Information");
	}

	public void clickbasicInformation() throws IOException {
		// gn.click("//b[text()='More Information ']", "xpath", "basic information");
	}
}
