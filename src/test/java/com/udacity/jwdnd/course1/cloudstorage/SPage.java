package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SPage {
    @FindBy(id = "inputFirstName")
    private WebElement iFirstName;

    @FindBy(id = "inputLastName")
    private WebElement iLastName;

    @FindBy(id = "inputUsername")
    private WebElement iUserName;

    @FindBy(id = "inputPassword")
    private WebElement iPassword;

    @FindBy(id = "btn-submit")
    private WebElement sButton;

    private final JavascriptExecutor javascriptExecutor;

    public SPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public void setFirstName(String fName) {
        javascriptExecutor.executeScript("arguments[0].value='"+ fName +"';", iFirstName);
    }

    public void setLastName(String lName) {
        javascriptExecutor.executeScript("arguments[0].value='"+ lName +"';", iLastName);
    }

    public void setUserName(String userName) {
        javascriptExecutor.executeScript("arguments[0].value='"+ userName +"';", iUserName);
    }

    public void setPassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='"+ password +"';", iPassword);
    }

    public void signUp() {
        javascriptExecutor.executeScript("arguments[0].click();", sButton);
    }
}
