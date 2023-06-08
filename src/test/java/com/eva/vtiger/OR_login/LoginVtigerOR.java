package com.eva.vtiger.OR_login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericMethodsFolder.WebUtil;

public class LoginVtigerOR {

	@FindBy(name = "user_name")
	protected WebElement userName;
	@FindBy(name = "user_password")
	protected WebElement userPass;
	@FindBy(name = "Login")
	protected WebElement loginBt;
	@FindBy(xpath = "//font[@color='Brown']")
	protected WebElement errorMsgSt;

	public LoginVtigerOR(WebUtil gn) {
		PageFactory.initElements(gn.getDriver(), this);
	}
}
