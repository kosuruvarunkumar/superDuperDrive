package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RPage {

    private final JavascriptExecutor javascriptExecutor;

    public RPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @FindBy(id = "a-success")
    private WebElement aResultSuccess;

    public void clickOk() {
        javascriptExecutor.executeScript("arguments[0].click();", aResultSuccess);
    }
}
