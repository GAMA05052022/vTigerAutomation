package com.eva.vtiger.Sales.Invice;

import org.openqa.selenium.support.PageFactory;
import GenericMethodsFolder.WebUtil;

public class AccountsSearch extends AccountsSearchOR {

	private WebUtil gn;

	public AccountsSearch(WebUtil gn) {
		super(gn);
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public void selectAccountName() {
		gn.click(accountName, "Account Name");

//		gn.actionClick(accountName, "Account Name"); 
	}

	public void selectIteamName() {
		gn.jsClick(iteamName);
	}
}
