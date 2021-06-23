package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LPage {
    @FindBy(id = "inputUsername")
    private WebElement iUserName;

    @FindBy(id = "inputPassword")
    private WebElement iPassword;

    @FindBy(id = "btn-submit")
    private WebElement sButton;

    private final JavascriptExecutor javascriptExecutor;

    public LPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
    }


    public void login() {
        javascriptExecutor.executeScript("arguments[0].click();", sButton);
    }

    public void sPassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='"+ password +"';", iPassword);
    }

    public void sUserName(String userName) {
        javascriptExecutor.executeScript("arguments[0].value='"+ userName +"';", iUserName);
    }
}
