package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {

	    FileInputStream file = new FileInputStream(
	        System.getProperty("user.dir") + "/src/test/resources/config.properties"
	    );
	    p = new Properties();
	    p.load(file);

	    logger = LogManager.getLogger(this.getClass());

	    // ================= REMOTE EXECUTION =================
	    if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

	        MutableCapabilities options;

	        // ================= LINUX (DOCKER GRID) =================
	        if (os.equalsIgnoreCase("linux")) {

	            switch (br.toLowerCase()) {
	                case "chrome":  options = new ChromeOptions(); break;
	                case "firefox": options = new FirefoxOptions(); break;
	                case "edge":    options = new EdgeOptions(); break;
	                default: throw new RuntimeException("Unsupported browser on Linux Grid: " + br);
	            }
	            options.setCapability("platformName", "linux");
	        }

	        // ================= WINDOWS =================
	        else if (os.equalsIgnoreCase("windows")) {

	            switch (br.toLowerCase()) {
	                case "chrome":  options = new ChromeOptions(); break;
	                case "firefox": options = new FirefoxOptions(); break;
	                case "edge":    options = new EdgeOptions(); break;
	                case "safari": throw new RuntimeException("Safari is NOT supported on Windows");
	                default: throw new RuntimeException("Unsupported browser on Windows: " + br);
	            }
	            options.setCapability("platformName", "windows");
	            options.setCapability("platformVersion", "11");
	        }

	        // ================= MAC =================
	        else if (os.equalsIgnoreCase("mac")) {

	            switch (br.toLowerCase()) {
	                case "chrome":  options = new ChromeOptions(); break;
	                case "firefox": options = new FirefoxOptions(); break;
	                case "edge":    options = new EdgeOptions(); break;
	                case "safari":  options = new SafariOptions(); break;
	                default: throw new RuntimeException("Unsupported browser on macOS: " + br);
	            }
	            options.setCapability("platformName", "mac");
	        }

	        else {
	            throw new RuntimeException("Invalid OS provided: " + os);
	        }

	        driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
	    }

	    // ================= LOCAL EXECUTION =================
	    else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

	        switch (br.toLowerCase()) {
	            case "chrome":  driver = new ChromeDriver(); break;
	            case "firefox": driver = new FirefoxDriver(); break;
	            case "edge":    driver = new EdgeDriver(); break;
	            default:
	                throw new RuntimeException("Invalid local browser: " + br);
	        }
	    }

	    driver.manage().deleteAllCookies();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(p.getProperty("appUrl"));
	    driver.manage().window().maximize();
	}




	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}

	public String randomString()
	{
		@SuppressWarnings("deprecation")
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber()
	{
		@SuppressWarnings("deprecation")
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric()
	{
		@SuppressWarnings("deprecation")
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		@SuppressWarnings("deprecation")
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return(generatedString+"@"+generatednumber);
	}

	public String captureScreen(String tname)
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp + ".png";;
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
