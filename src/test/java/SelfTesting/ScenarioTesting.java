package SelfTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import GenericMethodsFolder.WebUtil;

public class ScenarioTesting {
	static WebUtil gn = new WebUtil();

	public static void main(String[] args) {
		gn.openBrowser("chrome");
		gn.navigateUrl("http://google.com");
		WebElement we = gn.getDriver().findElement(By.xpath("//textarea[@id='APjFqb']"));
		gn.inputTextValue("twiter", we, "search Box");
		we.submit();
		WebElement thirdLink = gn.getDriver().findElement(By.xpath("TbwUpd NJjxre iUh30 apx8Vc ojE3Fb"));
		gn.click(thirdLink, "Third link");
	}

	@Test
	public void amazone() {
		gn.openBrowser("chrome");
		gn.navigateUrl("https://www.amazon.in/");
		gn.click(gn.getDriver().findElement(By.xpath("(//img[@class='product-image'])[2]")), "grosery");
	}

}
