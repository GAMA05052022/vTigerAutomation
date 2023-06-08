package com.eva.vtiger.Sales.Invice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesInvoiceLandingPageOR {

	@FindBy(xpath = "//img[@title='Create Invoice...']")
	protected WebElement createInvoiceBt;

}
