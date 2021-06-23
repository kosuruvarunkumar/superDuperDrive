package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HPage {
    @FindBy(id = "btn-logout")
    private WebElement lButton;

    @FindBy(id = "fileUpload")
    private WebElement fUpload;

    @FindBy(id = "btn-addNewNote")
    private WebElement bAddNewNote;

    @FindBy(id = "btn-addNewCredential")
    private WebElement bAddNewCredential;

    @FindBy(id = "note-title")
    private WebElement tNoteTitle;

    @FindBy(id = "nav-notes-tab")
    private WebElement nNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement nCredentialsTab;

    @FindBy(id = "note-description")
    private WebElement tNoteDescription;

    @FindBy(id = "btn-saveChanges")
    private WebElement bSaveChanges;

    @FindBy(id = "tbl-noteTitle")
    private WebElement tbl_NoteTitle;

    @FindBy(id = "tbl-noteDescription")
    private WebElement tbl_NoteDescription;

    @FindBy(id = "btnEditNote")
    private WebElement bEditNote;

    @FindBy(id = "btn-editCredential")
    private WebElement bEditCredential;

    @FindBy(id = "note-description")
    private WebElement tModifyNoteDescription;

    @FindBy(id = "btn-deleteNote")
    private WebElement bDeleteNote;

    @FindBy(id = "btn-deleteCredential")
    private WebElement bDeleteCredential;

    @FindBy(id = "credential-url")
    private WebElement tCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement tCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement tCredentialPassword;

    @FindBy(id = "btn-credentialSaveChanges")
    private WebElement bCredentialSaveChanges;

    @FindBy(id = "tbl-credentialUrl")
    private WebElement tbl_CredentialUrl;

    @FindBy(id = "tbl-credentialUsername")
    private WebElement tbl_CredentialUsername;

    @FindBy(id = "tbl-credentialPassword")
    private WebElement tbl_CredentialPassword;

    private final JavascriptExecutor javascriptExecutor;

    private final WebDriverWait webDriverWait;

    public HPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        javascriptExecutor = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, 500);
    }

    public void logout() {
        javascriptExecutor.executeScript("arguments[0].click();", lButton);
    }

    public void eNote() {
        javascriptExecutor.executeScript("arguments[0].click();", bEditNote);
    }

    public void eCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", bEditCredential);
    }

    public void dNote() {
        javascriptExecutor.executeScript("arguments[0].click();", bDeleteNote);
    }

    public void dCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", bDeleteCredential);
    }

    public void aNewNote() {
        javascriptExecutor.executeScript("arguments[0].click();", bAddNewNote);
    }

    public void aNewCredential() {
        javascriptExecutor.executeScript("arguments[0].click();", bAddNewCredential);
    }

    public void sNoteTitle(String noteTitle) {
        javascriptExecutor.executeScript("arguments[0].value='" + noteTitle + "';", tNoteTitle);
    }

    public void sCredentialUrl(String url) {
        javascriptExecutor.executeScript("arguments[0].value='" + url + "';", tCredentialUrl);
    }

    public void sCredentialUsername(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", tCredentialUsername);
    }

    public void sCredentialPassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", tCredentialPassword);
    }

    public void mNoteTitle(String newNoteTitle) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tNoteTitle)).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tNoteTitle)).sendKeys(newNoteTitle);
    }

    public void mNoteDescription(String newNoteDescription) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tModifyNoteDescription)).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(tModifyNoteDescription)).sendKeys(newNoteDescription);
    }

    public void nToNotesTab() {
        javascriptExecutor.executeScript("arguments[0].click();", nNotesTab);
    }

    public void nToCredentialsTab() {
        javascriptExecutor.executeScript("arguments[0].click();", nCredentialsTab);
    }

    public void sNoteDescription(String noteDescription) {
        javascriptExecutor.executeScript("arguments[0].value='"+ noteDescription +"';", tNoteDescription);
    }

    public void saveNoteChanges() {
        javascriptExecutor.executeScript("arguments[0].click();", bSaveChanges);
    }

    public void saveCredentialChanges() {
        javascriptExecutor.executeScript("arguments[0].click();", bCredentialSaveChanges);
    }

    public boolean noNotes(WebDriver driver) {
        return !elementPresent(By.id("tbl-noteTitle"), driver) && !elementPresent(By.id(
                "tableNoteDescription"), driver);
    }

    public boolean noCredentials(WebDriver driver) {
        return !elementPresent(By.id("tbl-credentialUrl"), driver) &&
                !elementPresent(By.id("tbl-credentialUsername"), driver) &&
                !elementPresent(By.id("tbl-credentialPassword"), driver);
    }

    public boolean elementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);

            return true;
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    public Credentials gFirstCredential() {
        String url = webDriverWait.until(ExpectedConditions.elementToBeClickable(tbl_CredentialUrl)).getText();
        String username = tbl_CredentialUsername.getText();
        String password = tbl_CredentialPassword.getText();

        return new Credentials(url, username, password);
    }

    public Notes gFirstNote() {
        String title = webDriverWait.until(ExpectedConditions.elementToBeClickable(tbl_NoteTitle)).getText();
        String description = tbl_NoteDescription.getText();

        return new Notes(title, description);
    }
}
