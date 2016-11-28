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

public class CategoryTest {
	private WebDriver driver;
	private Actions action;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String categoryId;
	private String categoryName;
	private String categoryOrder;
	private String categoryModName;
	private String categoryModOrder;
	private int modifyNum;
	private int deleteNum;
	private int oplogNum;
	private String categoryXpath;
	private String headquarterXpath;
	private String NCOutside= BaseConfiguration.getValue("NCOutside");

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
        driver=BaseDriverUtil.getDriver();
		action = new Actions(driver);
        BaseDriverUtil.loginSite();
        
		Random random = new Random();
		int sufix = random.nextInt(1000);
		categoryId = "CA" + sufix;
		categoryName = "大类" + sufix;
		categoryOrder = "" + sufix;
		categoryModName = "大类Mod" + sufix;
		categoryModOrder = String.valueOf(sufix - 1);
		modifyNum = 1;
		deleteNum = 2;
		if (!NCOutside.equals("true")) {
			deleteNum = 2;
			oplogNum = 3;
		} else {
			oplogNum = 2;
		}

		
		headquarterXpath=BaseConfiguration.getValue("headquarterXpath");
		categoryXpath = BaseConfiguration.getValue("categoryXpath");

	}

	@Test(priority = 1)
	public void categoryAddingTest() throws Exception {
		// Thread.sleep(3000);
		// driver.get(baseUrl + "/homePage");

		// WebElement mainMenu =
		// driver.findElement(By.xpath("//html[@id='ng-app']/body/div/div/div[2]/div/ul/li[3]/a/span"));
		// action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath("//html[@id='ng-app']/body/div/div/div[2]/div/ul/li[3]/div/div[2]/div/h5"))).click();
		// action.moveToElement(nav).moveByOffset(3， 3).build().perform();

		WebElement headquarter = driver
				.findElement(By.xpath(headquarterXpath));
		if (headquarter.isDisplayed()) {
			action.moveToElement(headquarter);
		}
		jsClick(driver, categoryXpath);
		
		if (!NCOutside.equals("true")) {
		
			driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
			driver.findElement(By.name("categoryCode")).clear();
			driver.findElement(By.name("categoryCode")).sendKeys(categoryId);
			driver.findElement(By.name("categoryName")).clear();
			driver.findElement(By.name("categoryName")).sendKeys(categoryName);
			driver.findElement(By.name("sortNo")).clear();
			driver.findElement(By.name("sortNo")).sendKeys(categoryOrder);
			driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
			
		} else {
			System.out.println("***************************NCOutside:"+NCOutside);
		    driver.findElement(By.cssSelector("div.pull-right > button.btn.btn-primary")).click();
		    Thread.sleep(2000);
//		    List<WebElement> trlist = driver.findElements(By.xpath(BaseConfiguration.getValue("categoryNCOutsideTabletr")));
//		    if (!trlist.isEmpty()){
		    			   
		    //jsClick(driver, "//html[@id='ng-app']/body/div[1]/div[3]/div[3]/div/div[2]/div[1]/div/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/a");
//		    driver.findElement(By.xpath("//html[@id='ng-app']/body/div[1]/div[3]/div[3]/div/div[2]/div[1]/div/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/a")).click();
		    
		    driver.findElement(By.linkText("添加")).click();
		    driver.findElement(By.name("sortNo")).clear();
		    driver.findElement(By.name("sortNo")).sendKeys(categoryOrder);
		    categoryId= driver.findElement(By.xpath("//*[@id='ngdialog1']/div[2]/div[1]/div[2]/form/div[1]/span")).getText();
			driver.findElement(By.cssSelector("div.input-group > div > label")).click();
		    driver.findElement(By.cssSelector("div.input-group > div > label")).click();
		    driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
		    //driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
		    jsClick(driver, "//html[@id='ng-app']/body/div[1]/div[3]/div[3]/div/div[2]/div[1]/div/div/div[2]/div/div[2]/div/div[1]/div/button");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();

//		    }
		}

		// driver.close();
		// driver.quit();
	}

	@Test(dependsOnMethods = { "categoryAddingTest" }, priority = 2)
	public void categoryModifyTest() throws Exception {

		Thread.sleep(3000);
		findElementInTable(categoryId).findElement(By.linkText("操作")).click();
		// driver.findElement(By.cssSelector("a.btn-more.dropdown-toggle >
		// span.caret")).click();

		int lineNum = queryLineInTable(categoryId);
		String xpathModify = "//html[@id='ng-app']/body/ul[" + lineNum + "]/li[" + modifyNum + "]/a";
		System.out.println("***************" + xpathModify);
		jsClick(driver, xpathModify);
		// driver.findElement(By.linkText("修改")).click();
		Thread.sleep(3000);
        if (!NCOutside.equals("true")) {
    		driver.findElement(By.name("categoryName")).clear();
    		driver.findElement(By.name("categoryName")).sendKeys(categoryModName);
		} 

		driver.findElement(By.cssSelector("div.input-group > div > label")).click();
		driver.findElement(By.name("sortNo")).clear();
		driver.findElement(By.name("sortNo")).sendKeys(categoryModOrder);
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();
	}

	@Test(dependsOnMethods={"categoryAddingTest"},priority = 3)
	public void categoryOplogTest() throws Exception {
		Thread.sleep(3000);
		findElementInTable(categoryId).findElement(By.linkText("操作")).click();
		int lineNum = queryLineInTable(categoryId);
		String xpathOplog = "//html[@id='ng-app']/body/ul[" + lineNum + "]/li[" + oplogNum + "]/a";
		jsClick(driver, xpathOplog);
		//driver.findElement(By.linkText("操作记录")).click();
		driver.findElement(By.cssSelector("div.ngdialog-close")).click();
	}

	//@Test(dependsOnMethods={"categoryAddingTest"},priority = 4)
	public void categoryDeleteTest() throws Exception {
		Thread.sleep(3000);
		//driver.findElement(By.linkText("操作")).click();
		//driver.findElement(By.xpath("(//a[contains(text(),'删除')])[2]")).click();
		findElementInTable(categoryId).findElement(By.linkText("操作")).click();
		int lineNum = queryLineInTable(categoryId);
		String xpathDelete = "//html[@id='ng-app']/body/ul[" + lineNum + "]/li[" + deleteNum + "]/a";
		jsClick(driver, xpathDelete);
		
		driver.findElement(By.cssSelector("div.dialog-footer > button.btn.btn-primary")).click();

	}

	private int queryLineInTable(String containText) {
		int Line = 0;
		List<WebElement> trlist;
        if (NCOutside.equals("true")) {
    		trlist = driver.findElements(By.xpath(BaseConfiguration.getValue("categoryNCTabletr")));
			
		} else {
			trlist = driver.findElements(By.xpath(BaseConfiguration.getValue("categoryTabletr")));
			
		}
        
        for (int r = 0; r < trlist.size(); r++) {
			String trText = trlist.get(r).getText();
			if (trText.contains(containText)) {
				Line = r+1;
			}
		}
		return Line;
	}

	private WebElement findElementInTable(String containText) {
		List<WebElement> trlist;
        if (NCOutside.equals("true")) {
    		trlist = driver.findElements(By.xpath(BaseConfiguration.getValue("categoryNCTabletr")));
			
		} else {
			trlist = driver.findElements(By.xpath(BaseConfiguration.getValue("categoryTabletr")));
			
		}
		for (int r = 0; r < trlist.size(); r++) {
			String trText = trlist.get(r).getText();
			if (trText.contains(containText)) {
				return trlist.get(r);
			}
		}
		return null;

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

    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
