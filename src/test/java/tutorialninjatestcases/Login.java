package tutorialninjatestcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utilities;

public class Login extends Base {

    WebDriver driver ;
    @BeforeMethod
    public void setUp()
    {
        driver = initBrowserApplication("chrome");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.linkText("Login")).click();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    @Test(priority = 1)
    public  void verifyLoginwithValidCredentials(){

        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("aashma.info@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test@123");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginwithInvalidCredentials()
    {

        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailTimeStamp() );
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test@123");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg ="Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actWarningmsg,expWarningmsg, "Expected message is not as actual message");
    }

    @Test(priority = 3)
    public void verifyLoginwithvalidEmailandInvalidCredentials()
    {

        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("aashma.info@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test@123ee");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg ="Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actWarningmsg,expWarningmsg, "Expected message is not as actual message");

    }

    @Test(priority = 4)
    public void verifyLoginwithInvalidEmailandValidCredentials()
    {

        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailTimeStamp());
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test@123ee");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg ="Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actWarningmsg,expWarningmsg, "Expected message is not as actual message");
    }

    @Test(priority = 5)
    public void verifyLoginwithNullData()
    {
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        String actWarningmsg = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String expWarningmsg ="Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actWarningmsg,expWarningmsg, "Expected message is not as actual message");
    }



}
