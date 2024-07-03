package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage  {
    WebDriver driver;

    @FindBy(xpath= "//span[normalize-space()='My Account']")
    public WebElement myAccountButton;

    @FindBy(linkText= "Login")
    public WebElement loginMenu;

   public HomePage(WebDriver driver)

   {
       this.driver = driver;
   }


}
