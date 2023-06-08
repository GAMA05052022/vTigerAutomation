package com.eva.vtiger.login;

import java.io.IOException;
import java.util.Map;

import com.eva.vtiger.MyHomePage.Home.HomePage;
import com.eva.vtiger.OR_login.LoginVtigerOR;

import GenericMethodsFolder.WebUtil;

public class LoginVtiger extends LoginVtigerOR {

	private WebUtil gn;

	public LoginVtiger(WebUtil gn) {
		super(gn);
		this.gn = gn;
	}

	public HomePage validLogin(Map<String, String> map) {
		gn.inputTextValue(map.get("user_name"), userName, "userNameBox");
		gn.inputTextValue(map.get("user_pass"), userPass, "userNamePass");
		gn.click(loginBt, "Login button");
		return new HomePage(gn);
	}
	
	public HomePage validLogin() {
		gn.inputTextValue(gn.configValue("user_name"), userName, "userNameBox");
		gn.inputTextValue(gn.configValue("user_pass"), userPass, "userNamePass");
		gn.click(loginBt, "Login button");
		return new HomePage(gn);
	}

	public void validateErrorMsgSt(String expectedMsg, String elementName) {
		gn.validateText(errorMsgSt, expectedMsg, elementName);
	}

	public void changeLanguage() throws IOException {
		// gn.selectByIndex("//select[@name='login_language']", "xpath", "language", 0);

	}

	public void changeTheams() {

	}
}
