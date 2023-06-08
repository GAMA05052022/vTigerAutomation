package TestCase;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.eva.vtiger.MyHomePage.Home.HomePage;
import com.eva.vtiger.login.LoginVtiger;
import GenericMethodsFolder.WebUtil;
import ReadExcelData_Utility.ExcelUtility;

public class BaseMethodOfTestNG {
	protected static WebUtil gn;
	protected HomePage homeObj;

	@BeforeClass
	public void createObjectGN() {
		gn = new WebUtil();
	}

	@BeforeMethod
	public void loginVtiger(Method m) throws IOException {
		String name = m.getName();
		ExcelUtility.getAllData("ExcelFile\\Test Data.xlsx", name);
		gn.createTest(name);
		gn.openBrowser(gn.configValue("browser"));
		gn.navigateUrl(gn.configValue("url"));
		LoginVtiger logP = new LoginVtiger(gn);
		homeObj = logP.validLogin();
	}

	@AfterMethod
	public void logOutPage(ITestResult itr) throws InterruptedException {
		int status = itr.getStatus();
		System.out.println(status);
		homeObj.signOut();
		gn.quit("chrome");
	}

	@AfterClass
	public void flush() {
		gn.getFlush();
	}

}
