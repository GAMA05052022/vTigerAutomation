package com.eva.vtiger.Based_OR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Base_OR {

	@FindBy(xpath = "//b[text()='Description Information']//parent::td//parent::tr//preceding-sibling::tr//input[@value='  Save  ']")
	protected WebElement saveBt;

	@FindBy(xpath = "//input[@name='search_text']")
	protected WebElement searchBox;

	@FindBy(xpath = "//select[contains(@name,'search_field')]")
	protected WebElement searchDropDown;

	@FindBy(xpath = "//input[contains(@value,'Search Now')]")
	protected WebElement searchBt;

	@FindBy(linkText = "Sign Out")
	protected WebElement signOut;
}
