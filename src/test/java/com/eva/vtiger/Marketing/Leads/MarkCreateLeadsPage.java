package com.eva.vtiger.Marketing.Leads;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.eva.vtiger.OR_Marketing.Leads.MarkCreateLeadsPageOR;
import GenericMethodsFolder.WebUtil;

public class MarkCreateLeadsPage extends MarkCreateLeadsPageOR {

	private WebUtil gn;

	public MarkCreateLeadsPage(WebUtil gn) {
		super(gn);
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	/**
	 * @param gn
	 * @throws IOException
	 */
	public MarkCreateLeadsPage basicInformation(Map<String, String> mapObj)  {
		gn.inputTextValue(mapObj.get("FirstName"), firstName, "First Name");
		gn.inputTextValue(mapObj.get("LastName"), lastName, "Last Name");
		gn.inputTextValue(mapObj.get("Company_Name"), companyName, "Company Name");
		gn.inputTextValue(mapObj.get("mobile"), mobileNo, "mobile box");
		return this;
	}

	/**
	 * @param gn
	 * @throws IOException
	 */
	public void saveButton() {
		gn.click(saveBt, "saveButton");
	}

	/**
	 * @param gn
	 * @throws IOException
	 */
	public void cancelButton() {
		gn.click(cancelBt, "cancelButton");
	}

	/**
	 * @param gn
	 * @throws IOException
	 */
	public void moreInformation(WebElement we) {
		gn.click(we, "more Information");
	}

	/**
	 * @param gn
	 * @throws IOException
	 */
	public void clickbasicInformation() {
		// gn.click("//b[text()='More Information ']", "xpath", "basic information");
	}
}
