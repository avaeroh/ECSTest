package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.TestCase.fail;

public class Test_base {

    public static WebDriver driver;
    public static Properties props;
    String machineType = System.getProperty("machineType");

    public Test_base(){
        try {
            props = new Properties();
            FileInputStream fileInput = null;
            if (machineType.equalsIgnoreCase("laptop")) {
                 fileInput = new FileInputStream("D:/WIP/ECSFramework/src/test/java/data/config.properties");
            } else {
                fileInput = new FileInputStream("<PATH TO YOUR CONFIG FILE>");
            }
            props.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUp() {
        String selectedBrowser = props.getProperty("browser");
        if (selectedBrowser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "<PATH>");
            driver = new FirefoxDriver();

        } else if (selectedBrowser.equalsIgnoreCase("chrome")) {
            String machineType = System.getProperty("machineType");
            if (machineType.equalsIgnoreCase("laptop")) {
                System.setProperty("webdriver.chrome.driver", "D:/WIP/ECSFramework/chromedriver_win32/chromedriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", "<PATH TO YOUR CHROMEDRIVER EXECUTABLE>");
            }
            driver = new ChromeDriver();
        }
        nonSpecificDriverConfig();
    }

    private static void nonSpecificDriverConfig() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static void tearDown() {
        driver.quit();
    }

}
