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

public class UnitTest {
	private WebDriver driver;
	private Actions action;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String unitId;
	private String unitName;
	private String unitModName;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String unitXpath;
	private String headquarterXpath;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();

		Random random = new Random();
		int sufix = random.nextInt(1000);
		unitId = "JLDW" + sufix;
		unitName = "计量单位" + sufix;
		unitModName = "计量单位Mod" + sufix;
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		unitXpath = BaseConfiguration.getValue("unitXpath");
	}

	@Test(priority = 1)
	public void unitAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, unitXpath);
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
		driver.findElement(By.name("unitCode")).clear();
		driver.findElement(By.name("unitCode")).sendKeys(unitId);
		driver.findElement(By.name("unitName")).clear();
		driver.findElement(By.name("unitName")).sendKeys(unitName);
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();

	}

	@Test(dependsOnMethods = { "unitAddingTest" }, priority = 2)
	public void unitModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(unitId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("unitName")).clear();
	    driver.findElement(By.name("unitName")).sendKeys(unitModName);
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

	@Test(dependsOnMethods={"unitAddingTest"},priority = 3)
	public void unitOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(unitId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.ngdialog-close")).click();
	}

	//@Test(dependsOnMethods={"unitAddingTest"},priority = 4)
	public void unitDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(unitId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'删除')])[2]")).click();
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
