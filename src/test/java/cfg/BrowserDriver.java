package cfg;

import actions.DriverActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BrowserDriver extends DriverActions {

    public static WebDriver driver;

    public void setUp(){
        System.out.println("SetUp method is called");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        setWebDriver(driver);

    }

    public void tearDown(){
        driver.close();
        driver.quit();
    }
//
//    @Test
//    public BrowserDriver(){
//        this.driver = driver;
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "/src/test/resources/drivers/chromedriver.exe");
//        this.driver = new ChromeDriver();
//    }
//    public void close(){
//        this.driver.close();
//    }


}
