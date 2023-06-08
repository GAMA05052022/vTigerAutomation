package SelfTesting;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.eva.vtiger.MyHomePage.Home.HomePage;
import com.eva.vtiger.login.LoginVtiger;
import GenericMethodsFolder.WebUtil;

public class TestNGPractice {

	static WebUtil gn;
	HomePage home;

	@BeforeClass
	public void gnInitilization() {
		gn = new WebUtil();
	}

	@BeforeMethod
	public void launchB(Method m) {
		String name = m.getName();
		gn.createTest(name);
		gn.openBrowser("chrome");
		gn.navigateUrl("http://localhost:8888");
		LoginVtiger login = new LoginVtiger(gn);
		home = login.validLogin();
	}

	@Test
	public void createLead() {
		System.out.println(10/0);
		home.gotoLeads();
	}

	@Test
	public void createInvoice() {
		home.gotoInvoice();
	}

	@AfterMethod
	public void logOut(ITestResult itr) {
		int status=itr.getStatus();
		if(status==1) {
			System.out.println("Pass");
		}else {
			System.out.println("Fail");
		}
		home.signOut();
	}

	@AfterClass
	public void flush() {
		gn.getFlush();
	}
}
