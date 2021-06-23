package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	protected WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	protected HPage signUpAndLogin() {
		driver.get("http://localhost:" + this.port + "/signup");
		SPage sPage = new SPage(driver);
		sPage.setFirstName("Test");
		sPage.setLastName("Test");
		sPage.setUserName("Test");
		sPage.setPassword("Test");
		sPage.signUp();
		driver.get("http://localhost:" + this.port + "/login");
		LPage lPage = new LPage(driver);
		lPage.sUserName("Test");
		lPage.sPassword("Test");
		lPage.login();

		return new HPage(driver);
	}

}
