package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTests {

    @LocalServerPort
    private int port;

    private WebDriver webDriver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.webDriver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testPageAccess() {
        webDriver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", webDriver.getTitle());

        webDriver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", webDriver.getTitle());

        webDriver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", webDriver.getTitle());
    }

    @Test
    public void primaryUserActions() {
        webDriver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", webDriver.getTitle());

        SPage sPage = new SPage(webDriver);
        sPage.setFirstName("Test");
        sPage.setLastName("Test");
        sPage.setUserName("Test");
        sPage.setPassword("Test");
        sPage.signUp();

        webDriver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", webDriver.getTitle());

        LPage lPage = new LPage(webDriver);
        lPage.sUserName("Test");
        lPage.sPassword("Test");
        lPage.login();

        HPage hPage = new HPage(webDriver);
        hPage.logout();

        webDriver.get("http://localhost:" + this.port + "/home");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Login", webDriver.getTitle());
    }
}
