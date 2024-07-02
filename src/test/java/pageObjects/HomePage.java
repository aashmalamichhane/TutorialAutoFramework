package pageObjects;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Base {
    WebDriver driver;

   public WebElement myAccountButton = driver.findElement(By.xpath("//span[normalize-space()='My Account']"));
   public WebElement loginMenu = driver.findElement(By.linkText("Login"));

   public HomePage(WebDriver driver)

   {
       this.driver = driver;
   }


}
