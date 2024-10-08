package testcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utilities;
public class Register extends Base {

    WebDriver driver;

     @BeforeMethod
    public void setUp(){
         driver = initBrowserApplication(prop.getProperty("browser"));
         driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
         driver.findElement(By.linkText("Register")).click();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegisterWithMandatoryFileds() {

        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataProp.getProperty("firstName"));
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataProp.getProperty("lastName"));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataProp.getProperty("phoneNumber"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@name=\"agree\"]")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actSuccessMessage = driver.findElement(By.xpath("//div[@id=\"content\"]//h1")).getText();
        String expSuccessMesage = testDataProp.getProperty("accountCreatedMessage");
        Assert.assertEquals(actSuccessMessage, expSuccessMesage, "Registration is not completed");
    }

    @Test(priority = 2)
    public void verifyRegisterWithExistingEmail() {

        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(testDataProp.getProperty("firstName"));
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys(testDataProp.getProperty("lastName"));
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys(testDataProp.getProperty("phoneNumber"));
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys(prop.getProperty("validPassword"));
        driver.findElement(By.xpath("//input[@name=\"agree\"]")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String actErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        String expErrorMesage = testDataProp.getProperty("emailAlreadyExistWarningMessage");
        Assert.assertEquals(actErrorMessage, expErrorMesage, "Warning message is not shown");
    }

    @Test(priority = 3)
    public void verifyRegisterWithNullData() {

        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String privacyWarningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
        Assert.assertTrue(privacyWarningMsg.contains(testDataProp.getProperty("privacyAgreeWarningMessage")));

        String firstNameWarningMsg = driver.findElement(By.xpath("//div[contains(text(),\"First Name must be between 1 and 32 characters!\")]")).getText();
        Assert.assertTrue(firstNameWarningMsg.contains(testDataProp.getProperty("firstNameWarningMessage")));

        String emailWarningMsg = driver.findElement(By.xpath("//div[contains(text(),\"E-Mail Address does not appear to be valid!\")]")).getText();
        Assert.assertTrue(emailWarningMsg.contains(testDataProp.getProperty("emailWarningMessage")));

        String telephoneWarningMsg = driver.findElement(By.xpath("//div[contains(text(),\"Telephone must be between 3 and 32 characters!\")]")).getText();
       Assert.assertTrue(telephoneWarningMsg.contains(testDataProp.getProperty("phoneWarningMessage")));

    }
}



