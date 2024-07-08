package testcases;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OTPNumber {
    public static final String ACCOUNT_SID = "ACc2b4afe76adb2ad4641c2e5e8ee759aa";
    public static final String AUTH_TOKEN = "6e9f45fe31e574c0fcf9133c5641ee02";

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement signinBtn = driver.findElement(By.cssSelector("a#nav-link-accountList>span>span"));
        WebElement startHeereBtn = driver.findElement(By.xpath("//div[@id=\"nav-flyout-ya-newCust\"]//a[@class=\"nav-a\"][normalize-space()=\"Start here.\"]"));

        Actions act = new Actions(driver);
        act.moveToElement(signinBtn).moveToElement(startHeereBtn).click().build().perform();

        driver.findElement(By.id("ap_email_login")).sendKeys("3342924380");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class=\"a-button-text a-declarative\"]")).click();
        driver.findElement(By.xpath("//ul[@role=\"listbox\"]//li//a[contains(text(),'United States ')]")).click();
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
        driver.findElement(By.id("ap_customer_name")).sendKeys("Test Aashma");
        driver.findElement(By.id("ap_password")).sendKeys("TestAutomation@123");
       driver.findElement(By.id("continue")).click();

        // get the OTP using Twilio APIs:
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String smsBody = getMessage();
        System.out.println(smsBody);
        String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
        System.out.println(OTPNumber);

        driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

    }

    public static String getMessage() {
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+13342924380")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }

}

