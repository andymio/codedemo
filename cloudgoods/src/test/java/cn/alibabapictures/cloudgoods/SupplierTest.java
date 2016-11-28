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

public class SupplierTest {
	private WebDriver driver;
	private Actions action;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String supplierId;
	private String supplierName;
	private String supplierModName;
	private String discount;
	private String discountMod;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String supplierXpath;
	private String headquarterXpath;


	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();

		Random random = new Random();
		int sufix = random.nextInt(1000);
		supplierId = "GYS" + sufix;
		supplierName = "供应商" + sufix;
		supplierModName = "供应商Mod" + sufix;
		discount="15";
		discountMod="18";
		
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		supplierXpath = BaseConfiguration.getValue("supplierXpath");
	}

	@Test(priority = 1)
	public void supplierAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, supplierXpath);
		driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
		driver.findElement(By.name("supplierCode")).clear();
		driver.findElement(By.name("supplierCode")).sendKeys(supplierId);
		driver.findElement(By.name("supplierName")).clear();
		driver.findElement(By.name("supplierName")).sendKeys(supplierName);
	    driver.findElement(By.name("discount")).clear();
	    driver.findElement(By.name("discount")).sendKeys(discount);
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
	    driver.findElement(By.name("contactName")).clear();
	    driver.findElement(By.name("contactName")).sendKeys("张三");
	    driver.findElement(By.name("contactPhone")).clear();
	    driver.findElement(By.name("contactPhone")).sendKeys("13612345678");
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();

	}

	@Test(dependsOnMethods = { "supplierAddingTest" }, priority = 2)
	public void supplierModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(supplierId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("supplierName")).clear();
	    driver.findElement(By.name("supplierName")).sendKeys(supplierModName);
	    driver.findElement(By.name("discount")).clear();
	    driver.findElement(By.name("discount")).sendKeys(discountMod);
	    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

	@Test(dependsOnMethods={"supplierAddingTest"},priority = 3)
	public void supplierOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(supplierId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.ngdialog-close")).click();
	}

	//@Test(dependsOnMethods={"supplierAddingTest"},priority = 4)
	public void supplierDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(supplierId);
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
