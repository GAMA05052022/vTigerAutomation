package com.eva.vtiger.Sales.Invice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatingInvoiceLandingPageOR {

	@FindBy(name = "subject")
	protected WebElement subject;

	@FindBy(id = "customerno")
	protected WebElement customerNo;

	@FindBy(name = "bill_street")
	protected WebElement billingAddress;

	@FindBy(id = "bill_city")
	protected WebElement billingCity;

	@FindBy(id = "bill_state")
	protected WebElement billingState;

	@FindBy(name = "bill_country")
	protected WebElement billingCountry;

	@FindBy(name = "vtiger_purchaseorder")
	protected WebElement purchaseOrder;

	@FindBy(name = "invoicedate")
	protected WebElement invoiceDate;

	@FindBy(name = "salescommission")
	protected WebElement salesCommission;

	@FindBy(xpath = "//input[@id='single_accountid']//following-sibling::img[@title='Select']")
	protected WebElement accountSelect;

	@FindBy(xpath = "//b[text()='Copy Billing address']//preceding-sibling::input[@name='cpy']")
	protected WebElement radioBtCopyBillAdd;

	@FindBy(xpath = "//b[text()='Invoice Information']//parent::td//parent::tr//preceding-sibling::tr//input[@value='  Save  ']")
	protected WebElement saveBt;

	@FindBy(id = "searchIcon1")
	protected WebElement iteam;
	
	@FindBy(id = "qty1")
	protected WebElement quantity;
}
