package testcases;

import org.junit.Test;
import static org.junit.Assert.*;
import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class OTPverification {

             public String getOTP() throws IOException, MailosaurException {

                long testStart = System.currentTimeMillis();
                String apiKey = "EA2gy6eVTQe1ow9FUbn8hG0ZuGryqx8t";
                String serverId = "ryy9gtfm";
                String serverDomain = "ryy9gtfm.mailosaur.net";

                MailosaurClient mailosaur = new MailosaurClient(apiKey);
                MessageSearchParams params = new MessageSearchParams();
                params.withServer(serverId).withReceivedAfter(testStart);

                SearchCriteria criteria = new SearchCriteria();
                criteria.withSentTo("asdas@" + serverDomain);

                Message message = mailosaur.messages().get(params, criteria);
                System.out.println(message.subject());
                System.out.println(message.cc());
                System.out.println(message.to());
                //body content
                System.out.println(message.text().body());

                // How many codes?
                System.out.println(message.text().codes().size()); // 2
                Code firstCode = message.text().codes().get(0);


                String otpCode = firstCode.value(); // "456812"
                assertNotNull(message);
                assertEquals("Sign in to VB Express", message.subject());

                return otpCode;
            }

            @Test
            public void OTPTest() throws MailosaurException, IOException {
                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get("https://vbexpress.stage.veribom.com/");
                String currPath = System.getProperty("user.dir");
                String filepath = currPath + "\\TestFile.gz";
                driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys(filepath);
                driver.findElement(By.xpath("//button[normalize-space()=\"Generate Report\"]")).click();
                driver.findElement(By.xpath("//input[@id=\"Email\"]")).sendKeys("asdas@ryy9gtfm.mailosaur.net");
                driver.findElement(By.xpath("//button[normalize-space()=\"Get OTP\"]")).click();
                driver.findElement(By.xpath("//input[@id=\"OTP\"]")).sendKeys(getOTP());
                driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        }
}