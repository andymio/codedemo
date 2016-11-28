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

public class TransferredTest {
	private WebDriver driver;
	private Actions action;

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String transferredId;
	private String transferredName;
	private String transferredModName;
	private String discount;
	private String discountMod;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String transferredXpath;
	private String headquarterXpath;


	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();

		Random random = new Random();
		int sufix = random.nextInt(1000);
		transferredId = "XT" + sufix;
		transferredName = "现调" + sufix;
		transferredModName = "现调Mod" + sufix;
		discount="15";
		discountMod="18";
		
		modifyNum = 1;
		deleteNum = 2;
		oplogNum = 3;
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		transferredXpath = BaseConfiguration.getValue("transferredXpath");
	}

	@Test(priority = 1)
	public void transferredAddingTest() throws Exception {
		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, transferredXpath);
		driver.findElement(By.cssSelector("a.btn.btn-primary > span")).click();
	    driver.findElement(By.cssSelector("div.pop-left > div.input-group.ng-scope > input[name=\"itemCode\"]")).clear();
	    driver.findElement(By.cssSelector("div.pop-left > div.input-group.ng-scope > input[name=\"itemCode\"]")).sendKeys(transferredId);
	    driver.findElement(By.cssSelector("inputtag.ng-scope > div.input-group.ng-scope > input[name=\"itemName\"]")).clear();
	    driver.findElement(By.cssSelector("inputtag.ng-scope > div.input-group.ng-scope > input[name=\"itemName\"]")).sendKeys(transferredModName);
	    driver.findElement(By.name("fastCode")).clear();
	    driver.findElement(By.name("fastCode")).sendKeys("123456789");
	    driver.findElement(By.name("barcode0")).clear();
	    driver.findElement(By.name("barcode0")).sendKeys("987654321");
	    driver.findElement(By.xpath("(//button[@type='button'])[30]")).click();
	    driver.findElement(By.linkText("个")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[32]")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'饮料')])[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[34]")).click();
	    driver.findElement(By.linkText("原料")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[38]")).click();
	    driver.findElement(By.linkText("可乐原浆5 ￥20")).click();
	    driver.findElement(By.name("packageGroupTransferredItem03")).clear();
	    driver.findElement(By.name("packageGroupTransferredItem03")).sendKeys("1");
	    driver.findElement(By.xpath("//div[@id='ngdialog1']/div[2]/div/div[2]/form/div/formulartag/div/div/span[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[40]")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'原料')])[2]")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[44]")).click();
	    driver.findElement(By.xpath("(//a[contains(text(),'原材料182 ￥10')])[2]")).click();
	    driver.findElement(By.name("packageGroupTransferredItem13")).clear();
	    driver.findElement(By.name("packageGroupTransferredItem13")).sendKeys("1");
	    driver.findElement(By.cssSelector("div.input-group.ng-scope > div > label")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[46]")).click();
	    driver.findElement(By.linkText("参与")).click();
	    driver.findElement(By.name("pureIntegral")).clear();
	    driver.findElement(By.name("pureIntegral")).sendKeys("1");
	    driver.findElement(By.name("integral")).clear();
	    driver.findElement(By.name("integral")).sendKeys("2");
	    driver.findElement(By.name("integralCash")).clear();
	    driver.findElement(By.name("integralCash")).sendKeys("3");
	    driver.findElement(By.xpath("(//button[@type='button'])[48]")).click();
	    driver.findElement(By.cssSelector("a.ng-binding > label.checkbox > div.checkbox-icon")).click();
	    driver.findElement(By.xpath("//div[@id='ngdialog1']/div[2]/div/div[2]/form/div/div[16]/placeslister/div/div/ul/li[3]/a/label/div")).click();
	    Thread.sleep(2000);	  
	    driver.findElement(By.xpath("(//button[@type='button'])[48]")).click();
	    Thread.sleep(2000);
	    //[@id='ngdialog12']/div[2]/div[1]/div[2]/form/div[1]/div[15]/asynlister/div/button[2]
	    driver.findElement(By.xpath("(//button[@type='button'])[50]")).click();
	    //Thread.sleep(2000);
	    //driver.findElement(By.xpath("(//html[@id='ngdialog12']/div[2]/div[1]/div[2]/form/div[1]/div[15]/asynlister/div/button[2]")).click();

	    driver.findElement(By.xpath("(//a[contains(text(),'小卖部')])[3]")).click();
	    Thread.sleep(2000);
	    //driver.findElement(By.name("beginDate")).click();

	    //driver.findElement(By.xpath("//input[@placeholder='选择时间'][1]")).click();
	    action.moveToElement(driver.findElement(By.xpath("//input[@placeholder='选择时间'][1]"))).click();
	    jsClick(driver, "//html[@id='ng-app']/body/div[6]/div[3]/table/tbody/tr[1]/td[3]");
	    action.moveToElement(driver.findElement(By.xpath("//input[@placeholder='选择日期'][1]"))).click();
	    jsClick(driver, "//html[@id='ng-app']/body/div[7]/div[3]/table/tbody/tr[5]/td[4]");
	    
	   // driver.findElement(By.xpath("//input[@placeholder='选择时间'][1]")).sendKeys("2016-11-01");
	   // driver.findElement(By.xpath("//input[@placeholder='选择日期'][1]")).clear();	
	   // driver.findElement(By.xpath("//input[@placeholder='选择日期'][1]")).sendKeys("2016-11-30");
	  
	    // driver.findElement(By.xpath("//html[@id='ngdialog12']/div[2]/div[1]/div[2]/form/div[1]/timetag/div[1]/input[1]")).click();	
	   // Thread.sleep(2000);
	  //  action.clickAndHold(driver.findElement(By.xpath("//html[@id='ngdialog12']/div[2]/div[1]/div[2]/form/div[1]/timetag/div[1]/input[1]"))).moveToElement(driver.findElement(By.xpath("//html[@id='ng-app']/body/div[6]/div[3]/table/tbody/tr[1]/td[3]"))).click();
	    
	    
	    
	    //driver.findElement(By.xpath("//div[@id='sizzle1479871192326']/div[3]/table/tbody/tr/td[3]")).click();
	    //driver.findElement(By.xpath("//div[@id='sizzle1479871192326']/div[3]/table/tbody/tr[5]/td[4]")).click();
	    driver.findElement(By.xpath("//div[@id='ngdialog1']/div[2]/div/div[2]/form/div/toggletag[2]/div/div/label")).click();
	    driver.findElement(By.xpath("//div[@id='ngdialog1']/div[2]/div/div[2]/form/div/toggletag[3]/div/div/label")).click();
	    driver.findElement(By.name("sortNo")).clear();
	    driver.findElement(By.name("sortNo")).sendKeys("123");
	    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
	    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys("12345");
	    driver.findElement(By.xpath("(//button[@type='button'])[51]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//span[contains(text(),'全选/全不选')])[1]")).click(); 
	    //driver.findElement(By.cssSelector("div.check-wrap > label.checkbox > div.checkbox-icon")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	   
	    
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(transferredId);
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

	}
	
	@Test(dependsOnMethods = { "transferredAddingTest" }, priority = 3)
	public void transferredCopyTest() throws Exception {

		Thread.sleep(3000);
		queryById(transferredId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("复制")).click();
	    driver.findElement(By.cssSelector("div.pop-left > div.input-group.ng-scope > input[name=\"itemCode\"]")).clear();
	    driver.findElement(By.cssSelector("div.pop-left > div.input-group.ng-scope > input[name=\"itemCode\"]")).sendKeys(transferredId+"CP");
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	}

	@Test(dependsOnMethods = { "transferredAddingTest" }, priority = 2)
	public void transferredModifyTest() throws Exception {

		Thread.sleep(3000);
		queryById(transferredId);
	    driver.findElement(By.linkText("操作")).click();
	    driver.findElement(By.linkText("修改")).click();
	    driver.findElement(By.name("itemName")).clear();
	    driver.findElement(By.name("itemName")).sendKeys(transferredModName);
	    //driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	   // jsClick(driver, "//html[@id='ngdialog9']/div[2]/div[1]/div[2]/form/div[4]/button[1]");
	    Thread.sleep(2000);
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}
	


	@Test(dependsOnMethods={"transferredAddingTest"},priority = 4)
	public void transferredOplogTest() throws Exception {
		Thread.sleep(3000);
		queryById(transferredId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
	    driver.findElement(By.linkText("操作记录")).click();
	    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	    //driver.findElement(By.xpath("(//span[contains(text(),'确定')])[1]")).click();
	}
	
	@Test(dependsOnMethods={"transferredAddingTest"},priority = 5)
    public void transferredDiffConfigTest() throws Exception{
		Thread.sleep(3000);
		queryById(transferredId);
	    driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle > span.caret")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'差异化配置')])[1]")).click();
        driver.findElement(By.name("sortNo")).clear();
        driver.findElement(By.name("sortNo")).sendKeys("321");
        driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
    }

	//差异化后不可删除主项
	//@Test(dependsOnMethods={"transferredAddingTest"},priority = 5)
	public void transferredDeleteTest() throws Exception {
		Thread.sleep(3000);
		queryById(transferredId);
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
