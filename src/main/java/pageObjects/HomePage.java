package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage  {
    WebDriver driver;

    public HomePage(WebDriver driver)

    {
        this.driver = driver;
    }

    public WebElement getMyAccountButton(){
        return driver.findElement(By.xpath("//a[@title=\"My Account\"]"));
    }

    public WebElement getLoginMenu(){
        return driver.findElement(By.linkText("Login"));
    }
}
