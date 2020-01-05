package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;


    @BeforeScenario
    public void setUp() throws Exception {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        } else if (os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        }
        String baseUrl = "https://mutlubiev.com/";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-popup-blocking");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        wait = new WebDriverWait(driver, 5, 150);
    }


    @AfterScenario
    public void tearDown() {
        driver.quit();
    }


}





