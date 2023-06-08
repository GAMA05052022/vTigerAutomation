package com.eva.vtiger.Based_Page;

import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import com.eva.vtiger.Based_OR.Base_OR;
import GenericMethodsFolder.WebUtil;

public class Base_PageFunction extends Base_OR {

	private WebUtil gn;

	public Base_PageFunction(WebUtil gn) {
		this.gn = gn;
		PageFactory.initElements(gn.getDriver(), this);
	}

	public void signOut() {
		gn.click(signOut, "SignOut");
	}

	public void saveButton() {
		gn.click(saveBt, "saveButton");
	}

	/**
	 * Send data in Search Box
	 * 
	 * @param dataValue
	 * @throws IOException
	 */
	public void inputValueInSerchBox(String dataValue)  {
		gn.inputTextValue(dataValue, searchBox, "SearchBox");
	}

	/**
	 * Select Drop Down In Search Drop Down
	 * 
	 * @param selectDD
	 * @throws IOException
	 */
	public void selectValueInDropDown(String selectDD) {
		gn.selectByVisibleText(searchDropDown, "SearchDD", selectDD);
	}

	/**
	 * Search Button
	 * 
	 * @throws IOException
	 */
	public void clickOnSearchBt() {
		gn.click(searchBt, "searchBt");
	}

	public void searchData(String interDataValue, String selectDD)  {
		inputValueInSerchBox(interDataValue); 
		selectValueInDropDown(selectDD);
		clickOnSearchBt(); 
	}
}
