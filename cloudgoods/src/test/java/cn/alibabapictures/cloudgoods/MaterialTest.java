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

public class MaterialTest {
	private WebDriver driver;
	private Actions action;

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String materialId;
	private String materialName;
	private String materialModName;
	private String discount;
	private String discountMod;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String materialXpath;
	private String headquarterXpath;


	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();

		Random random = new Random();
		int sufix = random.nextInt(1000);
		materialId = "YCL" + sufix;
		materialName = "原材料" + sufix;
		materialModName = "原材料Mod" + sufix;
		discount="15";
		discountMod="18";
		
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		materialXpath = BaseConfiguration.getValue("materialXpath");
	}

	@Test(priority = 1)
	public void materialAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, materialXpath);
		driver.findElement(By.cssSelector("div.action-right > button.btn.btn-primary")).click();
	    driver.findElement(By.name("itemCode")).clear();
	    driver.findElement(By.name("itemCode")).sendKeys(materialId);
	    driver.findElement(By.name("itemName")).clear();
	    driver.findElement(By.name("itemName")).sendKeys(materialName);
	    driver.findElement(By.name("fastCode")).clear();
	    driver.findElement(By.name("fastCode")).sendKeys("123456789");
	    driver.findElement(By.name("barcode0")).clear();
	    driver.findElement(By.name("barcode0")).sendKeys("987654321");
	    driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'可口可乐饮料供应商')])[2]")).click();
	    driver.findElement(By.name("cost")).clear();
	    driver.findElement(By.name("cost")).sendKeys("10");
	    driver.findElement(By.xpath("(//button[@type='button'])[15]")).click();
	    driver.findElement(By.linkText("罐")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[17]")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'饮料')])[4]")).click();
	    driver.findElement(By.xpath("//input[@value='D']")).click();  //仓库
	    //driver.findElement(By.xpath("//input[@value='P']")).click();  //货架
	    Thread.sleep(2000);
	    // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
	    //[@id='ngdialog1']/div[2]/div[1]/div[2]/form/div[1]/div[10]/div[2]/asynlister/div/button[2] 点击选择仓库
	    //driver.findElement(By.xpath("(//html[@id='ngdialog1']/div[2]/div[1]/div[2]/form/div[1]/div[10]/div[2]/asynlister/div/button[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();  //仓库下拉选择
	    driver.findElement(By.linkText("卖品大仓")).click();
	    driver.findElement(By.name("tipsCount")).clear();
	    driver.findElement(By.name("tipsCount")).sendKeys("10");
	    driver.findElement(By.name("sortNo")).clear();
	    driver.findElement(By.name("sortNo")).sendKeys("123");
	    driver.findElement(By.name("spec")).clear();
	    driver.findElement(By.name("spec")).sendKeys("500ml");
	    driver.findElement(By.name("packageCount")).clear();
	    driver.findElement(By.name("packageCount")).sendKeys("10");
	    driver.findElement(By.cssSelector("div.input-group.ng-scope > div > label")).click();
	    driver.findElement(By.cssSelector("div.input-group.ng-scope > div > label")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[20]")).click(); //刷新
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//span[contains(text(),'全选/全不选')])[1]")).click();  //全选影院
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	   
	    
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(materialId);
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

	}
	
	@Test(dependsOnMethods = { "materialAddingTest" }, priority = 3)
	public void materialCopyTest() throws Exception {

		Thread.sleep(3000);
		queryById(materialId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("复制")).click();
	    driver.findElement(By.name("itemCode")).clear();
	    driver.findElement(By.name("itemCode")).sendKeys(materialId+"CP");
	    driver.findElement(By.name("itemName")).clear();
	    driver.findElement(By.name("itemName")).sendKeys(materialName+"CP");
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	}

	@Test(dependsOnMethods = { "materialAddingTest" }, priority = 2)
	public void materialModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(materialId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("itemName")).clear();
	    driver.findElement(By.name("itemName")).sendKeys(materialModName);
	    //driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	   // jsClick(driver, "//html[@id='ngdialog9']/div[2]/div[1]/div[2]/form/div[4]/button[1]");
	    Thread.sleep(2000);
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}
	


	@Test(dependsOnMethods={"materialAddingTest"},priority = 4)
	public void materialOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(materialId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	}

	//@Test(dependsOnMethods={"materialAddingTest"},priority = 5)
	public void materialDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(materialId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("删除")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
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
