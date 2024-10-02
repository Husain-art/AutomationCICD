package hcyclewala.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hcyclewala.pageobjects.LandingPage;

public class BaseTest {
    public WebDriver driver;
    public LandingPage l;
    public WebDriver initializeDriver() throws IOException
    {       
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\hcyclewala\\resources\\GlobalData.properties");
        // C:\\Users\\v-hcyclewala\\JavaProjects\\Introduction\\selenium_framework_design
        prop.load(fis);
        String browserName = System.getProperty("Browser")!= null? System.getProperty("Browser") : prop.getProperty("Browser");
        // String browserName = prop.getProperty("Browser");

        if(browserName.contains("edge"))
        {
            EdgeOptions options = new EdgeOptions();
            System.setProperty("webdriver.edge.driver","C:\\Users\\v-hcyclewala\\Downloads\\edgedriver_win64 (5)\\msedgedriver.exe");
            if (browserName.contains("headless"))
            {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
        }
        else if(browserName.equalsIgnoreCase("chrome"))
        {
           //chrome
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
           //firefox
        }
        
        // WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
    {
        String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
        
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(Source, file);
        return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException
    {
        driver = initializeDriver();
        l = new LandingPage(driver);
        l.goTo();
        return l;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
