import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
// lujain
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://automationteststore.com/";
	String myWebsite2 = "https://www.google.com/";

	String[] fName = { "lujain", "fares", "ahmad", "kinan" };
	String[] lName = { "ali", "kareem", "omar" };

	Random rand = new Random();

	String firstName = "";
	String lastName = "";
	String emailAddress = "";
	String pass = "";
	String loginName = "";

	String dominName = "@gmail.com";

	@BeforeTest
	public void setup() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // da2eman baktobha
		driver.manage().window().maximize();
		driver.get(myWebsite);
//		driver.navigate().to(myWebsite2);
//		driver.navigate().back();
//		driver.manage().window().minimize();
//		driver.navigate().forward();
//		System.out.print(driver.getCurrentUrl().toUpperCase());
//		driver.navigate().refresh();
	}

	@Test(priority = 1 /* , invocationCount = 10 */ , enabled = false) // repeat test 10
	public void signUp() throws InterruptedException {
		// search for (Create) from top left of website and it's case sensetive
		// driver.findElement(By.partialLinkText("Create")).click();

		// search for full text of link

		// By .... called (locater)
		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		SignUpButton.click();

		int randomFname = rand.nextInt(fName.length);
		int randomLname = rand.nextInt(lName.length);
		int randomEmailNumber = rand.nextInt(564548);

		firstName = fName[randomFname];
		lastName = lName[randomLname];

		emailAddress = firstName + lastName + randomEmailNumber + dominName;
		pass = "ilovemyMom1233@44";

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(firstName);

		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(lastName);

		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(emailAddress);

		WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AddressInput.sendKeys("Irbid - Edoun");

		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("Capital city");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector2 = new Select(CountryInput);
		
		int randomCountry = rand.nextInt(1, 240); // get random number between 1 and 239

		selector2.selectByIndex(randomCountry);
		
		Thread.sleep(2000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));

		Select selector = new Select(ZoneIdInput);
		int randomZoneId = rand.nextInt(1, 6);
		selector.selectByIndex(randomZoneId);

		WebElement ZipCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		ZipCodeInput.sendKeys("133310");

		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		LoginNameInput.sendKeys(firstName + lastName + randomEmailNumber);
		loginName = firstName + lastName + randomEmailNumber;
		
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(pass);

		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys(pass);
		
		WebElement agreeInput = driver.findElement(By.id("AccountFrm_agree"));
		agreeInput.click();
		
		Thread.sleep(2000);
		WebElement CreateAnAccountbutton = driver.findElement(By.xpath("//button[@title='Continue']"));
		Thread.sleep(2000);

		CreateAnAccountbutton.click();
	

	}

	@Test(priority = 2, enabled = false) // will not work
	public void signOut() throws InterruptedException {
		
		WebElement logOutHover = driver.findElement(By.linkText("Welcome back "+ firstName));
		Actions action = new Actions(driver);
		Thread.sleep(2000);
		action.moveToElement(logOutHover).perform();
		
		WebElement logOutButton = driver.findElement(By.linkText("Not "+firstName+"? Logoff"));
		Thread.sleep(2000);
		action.moveToElement(logOutButton);
		Thread.sleep(2000);

		action.click().build().perform();
		
		//String logOutUrl = "https://magento.softwaretestingboard.com/customer/account/logout/";
		//driver.get(logOutUrl);

	}
	@Test(priority = 3 , enabled = false)
	public void login() throws InterruptedException {
		
		driver.findElement(By.linkText("Login or register")).click();


		WebElement username = driver.findElement(By.id("loginFrm_loginname"));
		username.sendKeys(loginName);

		WebElement password = driver.findElement(By.id("loginFrm_password"));
		password.sendKeys(pass);

		WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
		Thread.sleep(2000);

		loginButton.click();
	}
	
	@Test(priority = 4)
	public void AddToCart() throws InterruptedException
	{
		String[] WebSitesForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomIndex = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomIndex]);

		WebElement items =  driver.findElement(By.cssSelector(".thumbnails.row"));
		
		List<WebElement> sections = items.findElements(By.tagName("li"));
		
		Thread.sleep(2000);;
		int randomIndex2 = rand.nextInt(sections.size());
		sections.get(randomIndex2).click();;
		
		WebElement products = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		List<WebElement> pp = products.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12"));
		int randomIndex3 = rand.nextInt(pp.size());
		 pp.get(randomIndex3).click();
		
			Thread.sleep(2000);;

		 WebElement add = driver.findElement(By.className("productpagecart"));
		 int li =  add.findElements(By.tagName("li")).get(0).findElements(By.tagName("a")).size();
		
		 if(li > 0)
		 {
			 driver.findElement(By.className("cart")).click();
			 boolean actual = driver.findElement(By.id("cart_checkout1")).isDisplayed();
				boolean expected = true;
				Assert.assertEquals(actual, expected, "soso hi" );
		 }
		 else
		 { 

			 driver.get(myWebsite);;
			 
			 String actual = myWebsite;
				String expected = driver.getCurrentUrl();
				Assert.assertEquals(actual, expected , "test successfuly");
			
		 }
		

		
	}
	}
