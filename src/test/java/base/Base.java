package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.Utilities;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public WebDriver driver ;
    public  Properties prop;
    public Properties testDataProp;

    public  Base() {
        String currentPath = System.getProperty("user.dir");
        prop = new Properties();
        testDataProp = new Properties();

        try {
            FileReader testDataFile = new FileReader(currentPath + "\\src\\main\\java\\testdata\\test_data.properties");
            testDataProp.load(testDataFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader conFile = new FileReader(currentPath + "\\src\\main\\java\\config\\config.properties");
            prop.load(conFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver initBrowserApplication(String browserName)
    {

        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();

        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_time));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_time));
        driver.get(prop.getProperty("url"));

        return driver;
    }
    }


