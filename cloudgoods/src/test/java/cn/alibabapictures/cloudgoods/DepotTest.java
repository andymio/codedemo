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

public class DepotTest {
	private WebDriver driver;
	private Actions action;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String depotId;
	private String depotName;
	private String depotAddress;
	private String depotModName;
	private String depotModAddress;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String depotXpath;
	private String headquarterXpath;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();
        
		Random random = new Random();
		int sufix = random.nextInt(1000);
		depotId = "CK" + sufix;
		depotName = "仓库" + sufix;
		depotModName = "仓库Mod" + sufix;
		depotAddress = "地址" + sufix;
		depotModAddress = "地址Mod" + sufix;	
		
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		depotXpath = BaseConfiguration.getValue("depotXpath");
	}

	@Test(priority = 1)
	public void depotAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, depotXpath);
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
		driver.findElement(By.name("depotId")).clear();
		driver.findElement(By.name("depotId")).sendKeys(depotId);
		driver.findElement(By.name("depotName")).clear();
		driver.findElement(By.name("depotName")).sendKeys(depotName);
		driver.findElement(By.name("depotAddress")).clear();
		driver.findElement(By.name("depotAddress")).sendKeys(depotAddress);
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();

	}

	@Test(dependsOnMethods = { "depotAddingTest" }, priority = 2)
	public void depotModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(depotId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("depotName")).clear();
	    driver.findElement(By.name("depotName")).sendKeys(depotModName);
	    driver.findElement(By.name("depotAddress")).clear();
	    driver.findElement(By.name("depotAddress")).sendKeys(depotModAddress);
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

	@Test(dependsOnMethods={"depotAddingTest"},priority = 3)
	public void depotOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(depotId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.ngdialog-close")).click();
	}

	//@Test(dependsOnMethods={"depotAddingTest"},priority = 4)
	public void depotDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(depotId);
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
