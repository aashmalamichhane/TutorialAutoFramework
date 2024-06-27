package tutorialninjatestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {

    @Test
    public  void verifyLoginwithValidCredentials(){
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    driver.get("https://tutorialsninja.com/demo/");
    driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("aashma.info@gmail.com");
    driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test@123");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
    driver.quit();


    }
}
