package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests extends CloudStorageApplicationTests {

    public static final String TEST_URL = "https://www.test.com/";
    public static final String TEST_USERNAME = "test";
    public static final String TEST_PASSWORD = "test";
    public static final String EXAMPLE_URL = "http://www.example.com/";
    public static final String EXAMPLE_USERNAME = "example";
    public static final String EXAMPLE_PASSWORD = "example";

    @Test
    public void credentialCreation() {
        HPage hPage = signUpAndLogin();
        saveAndCheckCredential(TEST_URL, TEST_USERNAME, TEST_PASSWORD, hPage);
        hPage.dCredential();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        hPage.logout();
    }

    private void saveAndCheckCredential(String url, String username, String password, HPage hPage) {
        saveCredential(url, username, password, hPage);
        hPage.nToCredentialsTab();
        Credentials credential = hPage.gFirstCredential();
        Assertions.assertEquals(url, credential.getUrl());
        Assertions.assertEquals(username, credential.getUserName());
        Assertions.assertNotEquals(password, credential.getPassword());
    }

    private void saveCredential(String url, String username, String password, HPage hPage) {
        hPage.nToCredentialsTab();
        hPage.aNewCredential();
        sCredentialFields(url, username, password, hPage);
        hPage.saveCredentialChanges();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        hPage.nToCredentialsTab();
    }

    private void sCredentialFields(String url, String username, String password, HPage hPage) {
        hPage.sCredentialUrl(url);
        hPage.sCredentialUsername(username);
        hPage.sCredentialPassword(password);
    }

    @Test
    public void testCredentialModification() {
        HPage homePage = signUpAndLogin();
        saveAndCheckCredential(TEST_URL, TEST_USERNAME, TEST_PASSWORD, homePage);
        Credentials originalCredential = homePage.gFirstCredential();
        String firstEncryptedPassword = originalCredential.getPassword();
        homePage.eCredential();
        String newUrl = EXAMPLE_URL;
        String newCredentialUsername = EXAMPLE_USERNAME;
        String newPassword = EXAMPLE_PASSWORD;
        sCredentialFields(newUrl, newCredentialUsername, newPassword, homePage);
        homePage.saveCredentialChanges();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        homePage.nToCredentialsTab();
        Credentials modifiedCredential = homePage.gFirstCredential();
        Assertions.assertEquals(newUrl, modifiedCredential.getUrl());
        Assertions.assertEquals(newCredentialUsername, modifiedCredential.getUserName());
        String modifiedCredentialPassword = modifiedCredential.getPassword();
        Assertions.assertNotEquals(newPassword, modifiedCredentialPassword);
        Assertions.assertNotEquals(firstEncryptedPassword, modifiedCredentialPassword);
        homePage.dCredential();
        rPage.clickOk();
        homePage.logout();
    }

    @Test
    public void testDeletion() {
        HPage hPage = signUpAndLogin();
        saveCredential(TEST_URL, TEST_USERNAME, TEST_PASSWORD, hPage);
        saveCredential(EXAMPLE_URL, EXAMPLE_USERNAME, EXAMPLE_PASSWORD, hPage);
        saveCredential("http://www.test.com/", "test", "test", hPage);
        Assertions.assertFalse(hPage.noCredentials(driver));
        hPage.dCredential();
        RPage rPage = new RPage(driver);
        rPage.clickOk();
        hPage.nToCredentialsTab();
        hPage.dCredential();
        rPage.clickOk();
        hPage.nToCredentialsTab();
        hPage.dCredential();
        rPage.clickOk();
        hPage.nToCredentialsTab();
        Assertions.assertTrue(hPage.noCredentials(driver));
        hPage.logout();
    }
}
