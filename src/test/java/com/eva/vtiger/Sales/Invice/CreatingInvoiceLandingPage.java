package com.eva.vtiger.Sales.Invice;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import GenericMethodsFolder.WebUtil;

public class CreatingInvoiceLandingPage extends CreatingInvoiceLandingPageOR {

	private WebUtil gn;

	public CreatingInvoiceLandingPage(WebUtil gn) {
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public CreatingInvoiceLandingPage basicInformation(Map<String, String> map) throws InterruptedException {
		gn.inputTextValue(map.get("Subject"), subject, "Subject");
		gn.inputTextValue(map.get("InvoiceDate"), invoiceDate, "invoiceDate");
		gn.inputTextValue(map.get("CustomerNo"), customerNo, "CustomerNo");
		gn.inputTextValue(map.get("PurchaseOrder"), purchaseOrder, "PurchaseOrder");
		gn.inputTextValue(map.get("SalesCommission"), salesCommission, "SalesCommission");

		gn.click(accountSelect, "AccountName");
		gn.switchToWindowByTitle("");
		AccountsSearch accSearch = new AccountsSearch(gn);
		accSearch.searchData(map.get("AccountName"), map.get("DropDownAccount"));
		accSearch.selectAccountName();
		gn.acceptAlert("PopUp");
		gn.switchToWindowByTitle("admin - Sales - Invoice - vtiger CRM 5 - Commercial Open Source CRM");
		gn.inputTextValue(map.get("BillingAddress"), billingAddress, "BilliingAddress");
		gn.inputTextValue(map.get("BillingCity"), billingCity, "BillingCity");
		gn.inputTextValue(map.get("BillingState"), billingState, "BillingState");
		gn.inputTextValue(map.get("BillingCountry"), billingCountry, "BillingCountry");
		gn.click(radioBtCopyBillAdd, "Radio Bt Cpy Add");
		gn.click(iteam, "Iteam");
		gn.switchToWindowByTitle("");
		accSearch.searchData(map.get("ProductName"), map.get("DropDownItem"));
		accSearch.selectIteamName();
		gn.switchToWindowByTitle("admin - Sales - Invoice - vtiger CRM 5 - Commercial Open Source CRM");
		gn.inputTextValue(map.get("Quantity"), quantity, "Qty");
		return this;
	}

	public void saveBt() {
		gn.actionClick(saveBt, "Save Bt");
	}
}
