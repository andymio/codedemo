package cn.alibabapictures.cloudgoods;

import java.util.regex.Pattern;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import cn.alibabapictures.common.BaseConfiguration;
import cn.alibabapictures.common.BaseDriverUtil;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PlaceTest {
	private WebDriver driver;
	private Actions action;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String placeId;
	private String placeName;
	private String placeModName;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String placeXpath;
	private String headquarterXpath;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();

		Random random = new Random();
		int sufix = random.nextInt(1000);
		placeId = "XSDD" + sufix;
		placeName = "销售地点" + sufix;
		placeModName = "销售地点Mod" + sufix;
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		placeXpath = BaseConfiguration.getValue("placeXpath");
	}

	@Test(priority = 1)
	public void placeAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, placeXpath);
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
		driver.findElement(By.name("placeCode")).clear();
		driver.findElement(By.name("placeCode")).sendKeys(placeId);
		driver.findElement(By.name("placeName")).clear();
		driver.findElement(By.name("placeName")).sendKeys(placeName);
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();

	}

	@Test(dependsOnMethods = { "placeAddingTest" }, priority = 2)
	public void placeModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(placeId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("placeName")).clear();
	    driver.findElement(By.name("placeName")).sendKeys(placeModName);
	    driver.findElement(By.cssSelector("div.input-group.ng-scope > div > label")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

	@Test(dependsOnMethods={"placeAddingTest"},priority = 3)
	public void placeOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(placeId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.ngdialog-close")).click();
	}

	//@Test(dependsOnMethods={"placeAddingTest"},priority = 4)
	public void placeDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(placeId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("删除")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

private void queryById(String id){
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(id);
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
}

	private void jsClick(WebDriver driver, String xpath) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(xpath));
		jsExe.executeScript("arguments[0].click();", element);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		// driver.quit();
		driver.close();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
