package testcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import utils.Utilities;

import java.io.IOException;

public class Login extends Base {

    WebDriver driver;

    public Login() {
        super();  //It will call parent class constructor-
    }

    @BeforeMethod
    public void setUp() {
        driver = initBrowserApplication(prop.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        homePage.getMyAccountButton().click();
        homePage.getLoginMenu().click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyLoginwithValidCredentials() {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginwithInvalidCredentials() {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataProp.getProperty("invalidPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actWarningmsg, expWarningmsg, "Expected message is not as actual message");
    }

    @Test(priority = 3)
    public void verifyLoginwithvalidEmailandInvalidPass() {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(testDataProp.getProperty("invalidPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg = testDataProp.getProperty("invalidCredentialWarningMessage");
        Assert.assertEquals(actWarningmsg, expWarningmsg, "Expected message is not as actual message");

    }

    @Test(priority = 4)
    public void verifyLoginwithInvalidEmailandValidPass() {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg = testDataProp.getProperty("invalidCredentialWarningMessage");
        Assert.assertEquals(actWarningmsg, expWarningmsg, "Expected message is not as actual message");
    }

    @Test(priority = 5)
    public void verifyLoginwithNullData() {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg = testDataProp.getProperty("ddinvalidCredentialWarningMessage");
        Assert.assertEquals(actWarningmsg, expWarningmsg, "Expected message is not as actual message");
    }

    @DataProvider(name = "validCredFromRxcel")
    public Object[][] supplyTestExcelData() throws IOException {
        Object[][] data = Utilities.getTestDataFromExcelSheet("Login");
        return data;
    }

    @Test(priority = 6, dataProvider = "validCredFromRxcel")
    public void verifyLoginwithExcelData(String email, String password) {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }
}


