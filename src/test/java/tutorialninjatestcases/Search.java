package tutorialninjatestcases;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base {

    WebDriver driver;

    @BeforeMethod
    public void setUp()
        {
            driver = initBrowserApplication(prop.getProperty("browser"));

        }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }


    @Test(priority = 1)
    public void verifySearchwithValidProduct()
        {
           WebElement searchBox=  driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
           searchBox.sendKeys("iphone");
           searchBox.sendKeys(Keys.ENTER);
            Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed());
        }

    @Test(priority = 2)
    public  void verifyInvalidProduct()
    {
        WebElement searchBox=  driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
        searchBox.sendKeys("iphondddde");
        searchBox.sendKeys(Keys.ENTER);
        String productNotAvailableMsg = driver.findElement(By.xpath("//div[@id='content']//p[2]")).getText();
        Assert.assertTrue(productNotAvailableMsg.contains("There is no product that matches the search criteria."));
    }

    }
