package com.eva.vtiger.Sales.Invice;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import GenericMethodsFolder.WebUtil;

public class SalesInvoiceLandingPage extends SalesInvoiceLandingPageOR {

	WebUtil gn;

	public SalesInvoiceLandingPage(WebUtil gn) {
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public CreatingInvoiceLandingPage createButton() throws IOException {
		gn.click(createInvoiceBt, "create Button Invoice");
		return new CreatingInvoiceLandingPage(gn);
	}
}
