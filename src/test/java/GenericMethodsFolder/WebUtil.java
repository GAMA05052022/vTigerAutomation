package GenericMethodsFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebUtil {
	private WebDriver driver;
	private ExtentTest extTest;
	private ExtentReports ext;
	private Properties prop;

	public WebUtil() {
		genaretReports();
	}

	public WebDriver getDriver() {
		return driver;
	}

	/// -----------------> WebDriver Interface <------------------ ///

	/*
	 * we create generic method of openBrowser/ (launch browser) return WebDriver
	 */
	public WebDriver openBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("incognito");
			//			options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			extTest.log(Status.INFO, "Browser launch successfully");
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			extTest.log(Status.INFO, "Browser launch successfully");
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			extTest.log(Status.INFO, "Browser launch successfully");
		}
		return driver;
	}

	/* we create close method */
	public void close(String browserPage) {
		try {
			driver.close();
			extTest.log(Status.INFO, "browser close successfully");
		} catch (Exception e) {
			getScreenShot(browserPage);
		}
	}

	/**
	 * @method Quit page
	 * @param browserPage @
	 */
	public void quit(String browserPage) {
		try {
			driver.quit();
			extTest.log(Status.INFO, "browser close successfully");
		} catch (Exception e) {
			getScreenShot(browserPage);
		}
	}

	/* maximize method */
	public void maximize() {
		driver.manage().window().maximize();
		extTest.log(Status.INFO, "broswer maximize successfully");
	}

	/* minimize method */
	public void minimize() {
		driver.manage().window().minimize();
		extTest.log(Status.INFO, "browser minimize successfully");
	}

	/*
	 * we create generic method of navigate URL
	 */
	public void navigateUrl(String url) {
		driver.get(url);
		extTest.log(Status.INFO, "URL Navigate " + url + " successfully");
	}

	/// -------------> WebElement Interface <--------------- ///

	/*
	 * we create generic method of getList<WebElement> find the Element by locator
	 * return List<WebElement>
	 */
	public List<WebElement> getList(String locatorValue, String locatorType, String elementName) {
		List<WebElement> listWe = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			listWe = driver.findElements(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			listWe = driver.findElements(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			listWe = driver.findElements(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			listWe = driver.findElements(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			listWe = driver.findElements(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("css")) {
			listWe = driver.findElements(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("innerTest")) {
			listWe = driver.findElements(By.linkText(locatorValue));
		} else {
			extTest.log(Status.FAIL, elementName + "wrong xpath");
		}
		return listWe;
	}

	/*
	 * we create generic method of checkElement and ( Verify Element) verify
	 * isDisplayed verify is Enabled return boolean
	 */
	public boolean checkElement(WebElement we, String elementName) {

		boolean status = false;

		if (we.isDisplayed()) {
			extTest.log(Status.PASS, elementName + " element is Displayed");
			if (we.isEnabled()) {
				extTest.log(Status.PASS, elementName + " element is Enabled");
				status = true;
			} else {
				extTest.log(Status.FAIL, elementName + " element is not Enabled");
			}
		} else {
			extTest.log(Status.FAIL, elementName + " element is not Displayed");
		}
		return status;
	}

	/*
	 * we create generic method of inputTextValue () call the method of
	 * getWebElement() call the method of checkElement() call the moethod of
	 * sendKeys()
	 */
	public void inputTextValue(String value, WebElement we, String elementName) {
		try {
			boolean status = checkElement(we, elementName);
			if (status == true) {
				we.clear();
				we.sendKeys(value);
				extTest.log(Status.INFO, "entered value in " + elementName + "box is successfully");
			} else {
				extTest.log(Status.INFO, "not entered value in " + elementName + " box");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("screen sendValue");
			getScreenShot(elementName);
		}
	}

	/*
	 * we create the generic method of click call the method of getWebElement() call
	 * the method of checkElement() call the method click of webElement
	 */
	public void click(WebElement we, String elementName) {
		try {
			boolean status = checkElement(we, elementName);
			if (status == true) {
				we.click();
				extTest.log(Status.INFO, "click " + elementName + " button is successfully");
			} else {
				extTest.log(Status.INFO, "not click " + elementName + "button is successfully");
			}
		} catch (Exception e) {
			new Actions(driver).click(we).perform();
		} catch (Throwable e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
			e.printStackTrace();
			System.out.println("screen clickSearch");
			getScreenShot(elementName);
		}
	}

	/* click multiple Element */
	public void clickMultipleElements(String locatorValue, String locatorType, String elementName) {
		try {
			List<WebElement> listWe = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < listWe.size(); i++) {
				WebElement we = listWe.get(i);
				elementName = we.getText();
				boolean st = checkElement(we, elementName);
				if (st == true) {
					we.click();
					extTest.log(Status.INFO, elementName + " click successfully");
				} else {
					extTest.log(Status.FAIL, elementName + " Not click ");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/*
	 * getInnerText method return String
	 */
	public String getInnerText(WebElement we, String elementName) {
		String innerText = "";
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				innerText = we.getText();

				extTest.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
			} else {
				extTest.log(Status.FAIL, elementName + " Not getText ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return innerText;
	}

	/**
	 * @method getInnerTextMultipleElements
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return List<String> @
	 */
	public List<String> getInnerTextMultipleElements(String locatorValue, String locatorType, String elementName) {
		//		String innerText = "";
		List<String> arrList = null;
		try {
			arrList = new ArrayList<String>();
			List<WebElement> lstWe = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < lstWe.size(); i++) {
				WebElement we = lstWe.get(i);
				boolean st = checkElement(we, elementName);
				if (st == true) {
					String innerText = we.getText();
					arrList.add(innerText);
					extTest.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
				} else {
					extTest.log(Status.FAIL, elementName + " Not getText");
				}
			}

		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return arrList;
	}

	/*
	 * getAttributeValue method return String
	 */
	public String getAttributeValue(WebElement we, String elementName, String attributeName) {
		String attributeValue = "";
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				attributeValue = we.getAttribute(attributeName);

				extTest.log(Status.INFO, elementName + " getAttribute Value successfully that is = " + attributeValue);
			} else {
				extTest.log(Status.FAIL, elementName + " Not get Attribute Value ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return attributeValue;
	}

	/* get CssValue method */
	public String getCssValue(WebElement we, String elementName, String background_color_Ya_color,
			String colorHasValue) {
		String colorText = "";
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				String colorProperty = we.getCssValue(background_color_Ya_color);
				colorText = Color.fromString(colorProperty).asHex();
				if (colorText.equalsIgnoreCase(colorHasValue)) {
					extTest.log(Status.INFO, elementName + " of color is Right");
				} else {
					extTest.log(Status.FAIL, elementName + " of color is not Right");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return colorText;
	}

	/**
	 * @method getSize
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName  @
	 */
	public Dimension getSize(WebElement we, String elementName) {
		Dimension dimsn = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				dimsn = we.getSize();
				int height = dimsn.getHeight();
				int width = dimsn.getWidth();
				extTest.log(Status.PASS, elementName + " actualHeight = " + height + " actualWidth = " + width);
			} else {
				extTest.log(Status.FAIL, elementName + " not get size");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return dimsn;
	}

	/**
	 * @method get Location
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return Point class of object @
	 */
	public Point getLocation(WebElement we, String elementName) {
		Point pnt = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				pnt = we.getLocation();
				int x = pnt.getY();
				int y = pnt.getY();
				extTest.log(Status.PASS, elementName + " actualXCordinate = " + x + " actualYCordinate = " + y);
			} else {
				extTest.log(Status.FAIL, elementName + " not get size");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return pnt;
	}
	/// ----------------> Select class <----------------- ///

	/* selectByText */
	public void selectByVisibleText(WebElement we, String elementName, String dropDownAttributeValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByVisibleText(dropDownAttributeValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			System.out.println("screen selectValue");
			getScreenShot(elementName);
		}
	}

	/* selectByValue */
	public void selectByValue(WebElement we, String elementName, String dropDownValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByValue(dropDownValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dropDown");
			getScreenShot(elementName);
		}
	}

	/* selectByIndex */
	public void selectByIndex(WebElement we, String elementName, int dropDownValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByIndex(dropDownValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectByValue ---> who select already in dropDown */
	public void selectDeselectByValue(WebElement we, String elementName, String dropDownValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByValue(dropDownValue);
				extTest.log(Status.INFO,
						elementName + " deselect Value in dropDown successfully that is = " + dropDownValue);
			} else {
				extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectByVisibleText --> who select already in dropDown */
	public void selectDeselectByVisibleText(WebElement we, String elementName, String dropDownAttributeValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByVisibleText(dropDownAttributeValue);
				extTest.log(Status.INFO,
						elementName + " deselectBy Value in dropDown successfully that is = " + dropDownAttributeValue);
			} else {
				extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectedByIndex ----> who aleardy prasent in dropDown */
	public void selecDeselecttByIndex(WebElement we, String elementName, int dropDownValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByIndex(dropDownValue);
				extTest.log(Status.INFO,
						elementName + " deselect  Value in dropDown successfully that is = " + dropDownValue);
			} else {
				extTest.log(Status.INFO, " Not deselect  Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectAll ----> whenever select multiple options */
	public void selectDeselectAll(WebElement we, String elementName) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectAll();
				extTest.log(Status.INFO, elementName + " deselect All Value in multiple dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not deselect All Value in " + elementName + "multiple dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getFirstSelectedOption */
	public void selectGetFirstSelectedOptions(WebElement we, String elementName) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				String getFSO = sl.getFirstSelectedOption().getText();
				extTest.log(Status.INFO,
						" getFirstSelectedOption Value in " + elementName + " dropDown is = " + getFSO);
			} else {
				extTest.log(Status.INFO, " Not getFirstSelectOption Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getAllSelectedOptions */
	public void selectGetAllSelectedOptions(WebElement we, String elementName) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				List<WebElement> lst = sl.getAllSelectedOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOptions = lst.get(i).getText();
					i++;
					extTest.log(Status.INFO, "get one by one all selected options = " + i + " : " + getOptions);
				}
				extTest.log(Status.INFO, " getAllSelectedOptions Value in " + elementName + " dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getOptions --> who prasent in dropDown */
	public void selectGetOptions(WebElement we, String elementName) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				List<WebElement> lst = sl.getOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOptions = lst.get(i).getText();
					i++;
					extTest.log(Status.INFO, "get one by one all options in dropdown = " + i + " : " + getOptions);
				}
				extTest.log(Status.INFO, " getAllOptions Value in " + elementName + " dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* count dropdown items */
	public void selectGetDropdownItemsCount(WebElement we, String elementName, String dropDownAttributeValue) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				Select sl = new Select(we);
				int NoItems = sl.getOptions().size();
				extTest.log(Status.INFO,
						elementName + " Count Items who Prasent in " + elementName + "DropDown = " + NoItems);
			} else {
				extTest.log(Status.INFO, " Not Count Items who Prasent in DropDown " + elementName);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	//// ------------------> Actions class <------------------- ////

	/*
	 * @method MouseOver
	 * 
	 * @description it is use to MouseOver on the dropDown
	 * 
	 * @param locatorValue
	 * 
	 * @param locatorType
	 * 
	 * @param elementName
	 * 
	 * @return Actions class of object
	 * 
	 * @
	 */
	public Actions actionMouseOver(WebElement we, String elementName) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.moveToElement(we).perform();
				;
				extTest.log(Status.PASS, elementName + " MouseOver successfully");
			} else {
				extTest.log(Status.FAIL, elementName + " Not MouseOver");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @method drag and drop
	 * @description It is used to drag and drop Element one place to anthor place
	 * @param drag
	 * @param drop
	 * @param elementName
	 * @return Actions class of object @
	 */
	public Actions actionDragAndDrop(WebElement drag, WebElement drop, String elementName) {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.dragAndDrop(drag, drop).perform();
			extTest.log(Status.PASS, elementName + " Drag and Drop successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @method drag and drop
	 * @description It is used to drag and drop any Element one place to another
	 *              plcae
	 * @param locatorValue
	 * @param locatorType
	 * @param xOffset
	 * @param yOffset
	 * @param elementName
	 * @return Atcions class of object @
	 */
	public Actions actionDragAndDrop(WebElement we, int xOffset, int yOffset, String elementName) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.dragAndDropBy(we, xOffset, yOffset).perform();
				extTest.log(Status.PASS, elementName + " Drag and Drop successfully");
			} else {
				extTest.log(Status.PASS, elementName + " Not Drag and Drop successfully");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions Click with WebElement */
	public Actions actionClick(WebElement we, String elementName) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.click(we).perform();
				;
				extTest.log(Status.PASS, elementName + " click successfully");
			} else {
				extTest.log(Status.FAIL, elementName + " Not click");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @param we
	 * @param elementName
	 * @return @
	 */
	public Actions actionDoubleClick(WebElement we, String elementName) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.doubleClick(we).perform();
				extTest.log(Status.PASS, elementName + " Doubleclick successfully");
			} else {
				extTest.log(Status.FAIL, elementName + " Not Doubleclick");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @method click without webelement
	 * @param elementName
	 * @return @
	 */
	public Actions actionClick(String elementName) {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.click().perform();
			;
			extTest.log(Status.PASS, elementName + " click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions sendKeys with WebElement */
	public Actions actionSendKeys(WebElement we, String elementName, String KeysValue) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.sendKeys(we, KeysValue).perform();
				;
				extTest.log(Status.PASS, elementName + " sendKeys " + KeysValue + "  successfully ");
			} else {
				extTest.log(Status.FAIL, elementName + " Not SendKeys ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions sendKeys Without WebElement */
	public Actions actionSendKeys(String elementName, String KeysValue) {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.sendKeys(KeysValue).perform();
			;
			extTest.log(Status.PASS, elementName + " click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @method ContextClick
	 * @description it is use to Right click on Element
	 * @param elementName
	 * @return Actions class of object @
	 */
	public Actions actionContextClick(String elementName) {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.contextClick().perform();
			extTest.log(Status.PASS, elementName + " Right click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/**
	 * @method ContextClick
	 * @description it is use to Right click on Element
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return Actions class of object @
	 */
	public Actions actionContextClick(WebElement we, String elementName) {
		Actions ac = null;
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.contextClick(we).perform();
				extTest.log(Status.PASS, elementName + " Right click successfully");
			} else {
				extTest.log(Status.PASS, elementName + "Not Right click successfully");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}
	/// ---------------> switchWindow <----------------- ///

	/* switchWindow by Title */
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualTitle = driver.getTitle();
			System.out.println(actualTitle + "actual");
			String expTitle = expectedTitle;
			if (actualTitle.equalsIgnoreCase(expTitle)) {
				break;
			}
		}
	}

	/* switchWindow by URL */
	public void switchToWindowByUrl(String expectedUrl) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualUrl = driver.getCurrentUrl();
			System.out.println(actualUrl + "  ------url");
			String expUrl = expectedUrl;
			if (actualUrl.equalsIgnoreCase(expUrl)) {
				break;
			} else {
				extTest.log(Status.FAIL, "Not switch window");
			}
		}
	}

	/*
	 * get Title of page return String
	 */
	public String getTitle() {
		String title = driver.getTitle();
		extTest.log(Status.INFO, "Get Title " + title + " successfully");
		return title;
	}

	/*
	 * get CurrentUrl of page return String
	 * 
	 * @return url
	 */
	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		extTest.log(Status.INFO, "Get Title " + url + " successfully");
		return url;
	}

	/// -------------------> Upload File <------------------- ///

	/**
	 * @param locatorValue //input[@type='file']
	 * @param locatorType  xpath
	 * @param elemntName   upload file
	 * @param dataValue    File Path @
	 */
	public void uploadFile(WebElement we, String elementName, String filePath) {
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				we.sendKeys(filePath);
				extTest.log(Status.PASS, "file upload successfully in " + elementName);
			} else {
				extTest.log(Status.FAIL, "file not upload  in " + elementName);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/// ---------------------> switch To Frame <---------------------- ///

	/**
	 * @method switchToFrameByIndex method
	 * @param indexValue like as 0 ,1, 2, 3, 5, 7, 9 @
	 */
	public void switchToFrameByIndex(int indexValue, String elementName) {
		try {
			driver.switchTo().frame(indexValue);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method switchToFrameByString method
	 * @param name or id like as "hdd" or "120" @
	 */
	public void switchToFrameByString(String nameOrid, String elementName) {
		try {
			driver.switchTo().frame(nameOrid);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method switchToFrameByWebElement method
	 * @param WebElement WebElement we=driver.findElement(By.xpath(" xpath ")); @
	 */
	public void switchToFrameByWebElement(String we, String elementName) {
		try {
			driver.switchTo().frame(we);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method switchToParentFrame method
	 * @param WebElement WebElement we=driver.findElement(By.xpath(" xpath ")); @
	 */
	public void switchToParentFrame(String elementName) {
		try {
			driver.switchTo().parentFrame();
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/// ----------------> Popup Handle <---------------- ///
	/**
	 * @method getAlertText
	 * @param String
	 * @return String
	 */
	public String getAlertText(String elementName) {
		String popupValue = "";
		try {
			popupValue = driver.switchTo().alert().getText();
			extTest.log(Status.INFO, elementName + " getText of popup");
		} catch (Exception e) {
			extTest.log(Status.FAIL, elementName + " not getText of popup");
		}
		return popupValue;
	}

	/**
	 * @method acceptAlert
	 * @param String
	 */
	public void acceptAlert(String elementName) {
		try {
			driver.switchTo().alert().accept();
			extTest.log(Status.INFO, elementName + " click ok successfully");
		} catch (Exception e) {
			extTest.log(Status.INFO, elementName + " not click ok successfully");
		}
	}

	/**
	 * @method dimissAlert
	 * @param String @
	 */
	public void dimissAlert(String elementName) {
		try {
			driver.switchTo().alert().dismiss();
			extTest.log(Status.INFO, elementName + " click cencel button successfully");
		} catch (Exception e) {
			extTest.log(Status.INFO, elementName + " not click cencel button successfully");
			getScreenShot(elementName);
		}
	}

	/// ------------------> Wait <----------------- ///

	/**
	 * @method implicitlyWait
	 * @description it is wait for all object
	 * @param time
	 */
	public void implicitlyWait(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	/**
	 * @method exciplicitlyWaitVisibility
	 * @description it is wait for specific Element and specific condition of
	 *              visibility
	 * @param time
	 * @param WebElement
	 */
	public void exciplicitlyWaitVisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(we));
	}

	/**
	 * @method exciplicitlyWait invisibility
	 * @description it is wait for specific Element and specific condition of
	 *              invisibility
	 * @param time
	 * @param we
	 */
	public void exciplicitlyWaitInvisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(we));
	}

	/**
	 * @method exciplicitlyWait ElementToBeClickable
	 * @description it is wait for specific Element and specific condition of
	 *              elementToBeClickable
	 * @param time
	 * @param we
	 */
	public void exciplicitlyWaitElementToBeClickable(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	/**
	 * @method exciplicitlyWait ElementToBeClickable
	 * @description it is wait for specific Element and specific condition of
	 *              elementToBeClickable
	 * @param time
	 * @param locatorValue
	 */
	public void exciplicitlyWaitElementToBeClickable(long time, String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
	}

	/**
	 * @method getCurrentDate
	 * @param pattern
	 * @param day
	 * @return String
	 */
	public String getCurrentDate(String pattern, int chooseDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, chooseDay);
		Date d = c.getTime();
		DateFormat df = new SimpleDateFormat(pattern);
		String dateStamp = df.format(d);
		return dateStamp;
	}
	// ---------> Validate Methods <--------- //

	/**
	 * @discription Verify Home Page is Appeared
	 * @param expectedTitle @
	 */
	public void verifyHomePage(String expectedTitle) {
		try {
			String actualTitle = getTitle();
			if (actualTitle.equalsIgnoreCase(expectedTitle)) {
				extTest.log(Status.PASS, "HomePage is appeared because actualTitle is " + actualTitle
						+ " and expectedTitle is " + expectedTitle + " matched successfully");
			} else {
				extTest.log(Status.FAIL, "HomePage is not appeared because actualTitle is " + actualTitle
						+ " and expectedTitle is " + expectedTitle + " not matched");
			}
		} catch (Exception e) {
			e.printStackTrace();
			getScreenShot("HmPgVfy");
		}
	}

	/**
	 * @method validate Text
	 * @description It is used to verify text our expected with actual text
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedText
	 * @param elementName
	 * @return String @
	 */
	public String validateText(WebElement we, String expectedText, String elementName) {
		String actualText = "";
		try {
			actualText = getInnerText(we, elementName);
			if (actualText.equalsIgnoreCase(expectedText)) {
				extTest.log(Status.PASS, elementName + " is pass because actualText -" + actualText
						+ " and expectedText -" + expectedText + " matched");
			} else {
				extTest.log(Status.FAIL, elementName + " is failed because actualText -" + actualText
						+ " and expectedText -" + expectedText + " not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualText;
	}

	/**
	 * @method validateMultipleText
	 * @description It is used to matched the actualText with expectedText
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedText
	 * @param elementName  @
	 */
	public void validateMultipleText(String locatorValue, String locatorType, List<String> expectedText,
			String elementName) {

		try {
			List<WebElement> list = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < list.size(); i++) {
				WebElement we = list.get(i);
				String actualText = we.getText();
				String exText = expectedText.get(i);
				if (actualText.equalsIgnoreCase(exText)) {
					extTest.log(Status.PASS, elementName + " is pass because actualText - " + actualText
							+ " and expectedText- " + exText + " matched");
				} else {
					extTest.log(Status.FAIL, elementName + " is failed because actualText - " + actualText
							+ " and expectedText- " + exText + " not matched");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}

	}

	/**
	 * @method validateAttributeValue
	 * @discription It is used to validate AttributeValue with our
	 *              ExpectedAttributeValue
	 * @param locatorValue
	 * @param locatorType
	 * @param attributeName
	 * @param expectedAttributeValue
	 * @return String @
	 */
	public String validateAttributeValue(WebElement we, String attributeName, String expectedAttributeValue) {
		String actualAttributeValue = "";
		try {
			actualAttributeValue = we.getAttribute(attributeName);
			if (actualAttributeValue.equalsIgnoreCase(expectedAttributeValue)) {
				extTest.log(Status.PASS, expectedAttributeValue + " is pass because actualText -" + actualAttributeValue
						+ " and expectedText -" + expectedAttributeValue + " matched");
			} else {
				extTest.log(Status.PASS, expectedAttributeValue + " is failed because actualText -"
						+ actualAttributeValue + " and expectedText -" + expectedAttributeValue + " not matched");
			}
		} catch (Exception e) {
			getScreenShot(expectedAttributeValue);
		}
		return actualAttributeValue;
	}

	/**
	 * @method validateTitle
	 * @discription It is used to validate Title with our Expected
	 * @param expectedTitle
	 * @param elementName
	 * @return String @
	 */
	public String validateTitle(String expectedTitle, String elementName) {
		String actualTitle = "";
		try {
			actualTitle = driver.getTitle();
			if (actualTitle.equalsIgnoreCase(actualTitle)) {
				extTest.log(Status.PASS, elementName + " is passed because actualTitle - " + actualTitle
						+ " and expectedTitle -" + expectedTitle + " is matched");
			} else {
				extTest.log(Status.PASS, elementName + " is failed because actualTitle - " + actualTitle
						+ " and expectedTitle -" + expectedTitle + " is not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualTitle;
	}

	/**
	 * @method validateUrl
	 * @discription It is used to validate Url with our Expected Url
	 * @param expectedUrl
	 * @param elementName
	 * @return String @
	 */
	public String validateUrl(String expectedUrl, String elementName) {
		String actualUrl = "";
		try {
			actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				extTest.log(Status.PASS, elementName + " is passed because actualTitle - " + actualUrl
						+ " and expectedTitle -" + expectedUrl + " is matched");
			} else {
				extTest.log(Status.PASS, elementName + " is failed because actualTitle - " + actualUrl
						+ " and expectedTitle -" + expectedUrl + " is not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualUrl;
	}

	/**
	 * @method ValidateHeight
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedHeight
	 * @param elementName    @
	 */
	public void validateHeight(WebElement we, int expectedHeight, String elementName) {
		try {
			Dimension dim = getSize(we, elementName);
			int actualHeight = dim.getHeight();
			if (actualHeight == expectedHeight) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualHeight - " + actualHeight
						+ " expectedHeight =" + expectedHeight);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualHeight - " + actualHeight
						+ " expectedHeight - " + expectedHeight);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validateWidth
	 * @discription It is used to validate the width size of element with our
	 *              expected
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedWidth
	 * @param elementName   @
	 */
	public void validateWidth(WebElement we, int expectedWidth, String elementName) {
		try {
			Dimension dim = getSize(we, elementName);
			int actualWidth = dim.getWidth();
			if (actualWidth == expectedWidth) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualWidth - " + actualWidth
						+ " expectedWidth =" + expectedWidth);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualWidth - " + actualWidth
						+ " expectedWidth - " + expectedWidth);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validate X cordinate
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedXcordinate
	 * @param elementName        @
	 */
	public void validateXcordinate(WebElement we, int expectedXcordinate, String elementName) {
		try {
			Point dim = getLocation(we, elementName);
			int actualXcordinate = dim.getX();
			if (actualXcordinate == expectedXcordinate) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualXcordinate - "
						+ actualXcordinate + " expectedXcordinate =" + expectedXcordinate);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualXcordinate - "
						+ actualXcordinate + " expectedXcordinate - " + expectedXcordinate);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validate Y cordinate
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedYcordinate
	 * @param elementName        @
	 */
	public void validateYcordinate(WebElement we, int expectedYcordinate, String elementName) {
		try {
			Point dim = getLocation(we, elementName);
			int actualYcordinate = dim.getY();
			if (actualYcordinate == expectedYcordinate) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualYcordinate - "
						+ actualYcordinate + " expectedYcordinate =" + expectedYcordinate);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualYcordinate - "
						+ actualYcordinate + " expectedYcordinate - " + expectedYcordinate);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/*
	 * we create the getScreenShot() method and add the screenShot with Report file
	 */
	public void getScreenShot(String elementName) {
		try {
			TakesScreenshot tc = (TakesScreenshot) driver;
			File from = tc.getScreenshotAs(OutputType.FILE);
			File to = new File("Screenshot\\" + elementName + ".png");
			Files.copy(from, to);
			extTest.addScreenCaptureFromPath(to.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method geraretReports
	 * @discription It is used to genaretReports
	 * @param testCaseId
	 * @return
	 */
	public void genaretReports() {
		DateFormat df = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String dateStamp = df.format(new Date());
		File filePath = new File("Report " + dateStamp + ".html");
		ExtentSparkReporter esp = new ExtentSparkReporter(filePath);
		ext = new ExtentReports();
		ext.attachReporter(esp);
	}

	public void createTest(String testCaseId) {
		extTest = ext.createTest(testCaseId);

	}

	public void getFlush() {
		ext.flush();
	}

	public void takeScreenshot() {
	}

	public void validateVisibleText(WebElement we, String elementName) {
		try {
			boolean st = we.isDisplayed();
			if (st == true) {
				extTest.log(Status.PASS, elementName + " is visible");
			} else {
				extTest.log(Status.FAIL, elementName + " is not visible");
			}
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void validatePopUpText(String expectedText) {
		try {
			String actualText = driver.switchTo().alert().getText();
			if (actualText.contentEquals(expectedText)) {
				extTest.log(Status.PASS, "Actul Text is =" + actualText + "is match with Expected = " + expectedText);
			} else {
				extTest.log(Status.FAIL,
						"Actul Text is =" + actualText + "is not match with Expected = " + expectedText);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void validateClickAbleElementOrNot(WebElement we, String elementName) {
		try {
			boolean st = we.isEnabled();
			if (st == true) {
				extTest.log(Status.PASS, elementName + " is Enable");
			} else {
				extTest.log(Status.FAIL, elementName + " is not Disable");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getProperty() {
		prop = new Properties();
		InputStream ins;
		try {
			ins = new FileInputStream("config.properties");
			prop.load(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String configValue(String keyName) {
		if (prop == null) {
			getProperty();
		}
		String value = prop.getProperty(keyName);
		return value;
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

}
