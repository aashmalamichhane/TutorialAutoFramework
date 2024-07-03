package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() throws IOException
    {
        ExtentReports extentReport = new ExtentReports();

        File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(extentReportFile);

        sparkReport.config().setTheme(Theme.DARK);
        sparkReport.config().setReportName("Automaton Framework Report");
        sparkReport.config().setDocumentTitle("Automation Report Title URL");
        sparkReport.config().setTimeStampFormat("DD/MM/YYY HH:MM:SS");

        extentReport.attachReporter(sparkReport);

        //To show additional data in reports regarding automation
        Properties configProp = new Properties();
        File configPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
        FileInputStream fis = new FileInputStream(configPropFile);
        configProp.load(fis);

        extentReport.setSystemInfo("APPLICATION URL", configProp.getProperty("url"));
        extentReport.setSystemInfo("APPLICATION BROWSER NAME ", configProp.getProperty("browser"));
        extentReport.setSystemInfo("AUTOMATING USER ", System.getProperty("os.name"));

        return extentReport;
    }
}



