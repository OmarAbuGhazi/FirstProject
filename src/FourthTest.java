import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FourthTest {
	WebDriver driver = new ChromeDriver();
	// OmarAbuGhazi
	String TheWeb = "https://automationteststore.com/";
	String[] FirstNames = { "Ahmad", "Mohammed", "Mustafa", "Issa", "Malek" };
	String[] LastNames = { "Khaled", "Hatem", "Yamen", "Hussam", "Laith" };
	Random rand = new Random();
	Actions action = new Actions(driver);
	String LoginInfo;
	String PasswordForAll = "Omar.Test123$#@";
	// String GlobalUserName;

	@BeforeTest
	public void setUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(TheWeb);
	}

	@Test(priority = 1) // invocationCount here not Working.
	public void signup() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		int RandomFirstNames = rand.nextInt(FirstNames.length);
		int RandomLastNames = rand.nextInt(LastNames.length);
		String FirstNameInput = FirstNames[RandomFirstNames];
		String LastNameInput = LastNames[RandomLastNames];
		int RandomEmailNum = rand.nextInt(100000);
		LoginInfo = FirstNameInput + LastNameInput + RandomEmailNum;
		// String RandomEmailNumber = String.valueOf(RandomEmailNum);
		String DomainInput = "@yahoo.com";
		String TotalEmail = FirstNameInput + LastNameInput + RandomEmailNum + DomainInput;
		WebElement LoginOrRegisterBtn = driver.findElement(By.linkText("Login or register"));
		LoginOrRegisterBtn.click();
		WebElement ContinueBtn = driver.findElement(By.xpath("//button[@title='Continue']"));
		ContinueBtn.click();
		WebElement FirstNameWeb = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameWeb.sendKeys(FirstNameInput);
		// GlobalUserName = FirstNameInput;
		WebElement LastNameWeb = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameWeb.sendKeys(LastNameInput);
		WebElement Email = driver.findElement(By.id("AccountFrm_email"));
		Email.sendKeys(TotalEmail);
		WebElement Address = driver.findElement(By.id("AccountFrm_address_1"));
		Address.sendKeys("amman - alyasmeen ");
		WebElement City = driver.findElement(By.id("AccountFrm_city"));
		City.sendKeys("Capital City");
		WebElement Country = driver.findElement(By.id("AccountFrm_country_id"));
		Select selector = new Select(Country);
		int RandomCountry = rand.nextInt(1, 240);
		selector.selectByIndex(RandomCountry);
		Thread.sleep(2000);
		WebElement Zone = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector2 = new Select(Zone);
		int RandomState = rand.nextInt(1, 6);
		selector2.selectByIndex(RandomState);
		WebElement PostalCode = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCode.sendKeys("11171");
		WebElement LoginName = driver.findElement(By.id("AccountFrm_loginname"));
		LoginName.sendKeys(LoginInfo);
		WebElement Password = driver.findElement(By.id("AccountFrm_password"));
		Password.sendKeys(PasswordForAll);
		WebElement ConfPass = driver.findElement(By.id("AccountFrm_confirm"));
		ConfPass.sendKeys(PasswordForAll);
		WebElement AgreeBtn = driver.findElement(By.id("AccountFrm_agree"));
		AgreeBtn.click();
		WebElement Submit = driver.findElement(By.xpath("//button[@title='Continue']"));
		Submit.click();
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {
		WebElement hoverelement = driver.findElement(By.className("menu_text"));
		action.moveToElement(hoverelement).perform();
		Thread.sleep(2000);
		WebElement Logout = driver.findElement(By.partialLinkText("Logoff"));
		Logout.click();
	}

	@Test(priority = 3)
	public void Login() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(2000);
		WebElement LoginPage = driver.findElement(By.linkText("Login or register"));
		LoginPage.click();
		Thread.sleep(1000);
		WebElement LoginUserName = driver.findElement(By.id("loginFrm_loginname"));
		LoginUserName.sendKeys(LoginInfo);
		Thread.sleep(1000);
		WebElement PasswordLog = driver.findElement(By.id("loginFrm_password"));
		PasswordLog.sendKeys(PasswordForAll);
		Thread.sleep(1000);
		WebElement LoginBtn = driver.findElement(By.xpath("//button[@title='Login']"));
		LoginBtn.click();
	}

	@Test(priority = 4)
	public void AddtoCart() throws InterruptedException {

		String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };
		int randomWebSite = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomWebSite]);
		// find UL tag
		WebElement ListOfItems = driver.findElement(By.cssSelector(".thumbnails.row"));
		// each UL tag has LI tag find it and define the size for each tag
		int TotalNumberOfItems = ListOfItems.findElements(By.tagName("li")).size();
		// give me a random item from the multiple products available
		int RandomItem = rand.nextInt(TotalNumberOfItems);
		Thread.sleep(2000);
		// find the element then get the random item previously chosen then click.
		ListOfItems.findElements(By.tagName("li")).get(RandomItem).click();
		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		int TotalItemsInContainer = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		int RandomItemsInItems = rand.nextInt(TotalItemsInContainer);
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(RandomItemsInItems).click();
		Thread.sleep(2000);

		WebElement Ulitem = driver.findElement(By.className("productpagecart"));
		int LIiem = Ulitem.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();

		if (LIiem > 0) {
			Thread.sleep(1000);
			driver.findElement(By.className("cart")).click();
			Thread.sleep(1000);
			String ActualResult = driver.findElement(By.className("heading1")).getText();
			String ExpectedResult = "Shopping Cart";
			Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase(),
					"the expected result to find 'shopping cart' in the checkout page");
			boolean ExpectedOutcome = true;
			boolean ActualOutCome = driver.findElement(By.id("cart_checkout1")).isDisplayed();
			assertEquals(ActualOutCome, ExpectedOutcome, "Expected result to find checkout Btn");
		} else {
			driver.get(TheWeb);
			System.out.println("Out Of Stocks");
			String ExpectedResult = "https://automationteststore.com/";
			String ActualResult = driver.getCurrentUrl();
			Assert.assertEquals(ActualResult, ExpectedResult,
					"The expected result when the item is out of stock, return to the home page of the website");
		}
	}
}
