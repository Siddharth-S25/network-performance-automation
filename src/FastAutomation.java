import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FastAutomation {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://fast.com/");
            By speedValueLocator = By.id("speed-value");
            By speedUnitLocator = By.id("speed-units");
            String className = "";

            System.out.println("Running Fast.com speed test...");

            while (true) {
                WebElement speedValueElement = wait.until(ExpectedConditions.presenceOfElementLocated(speedValueLocator));
                WebElement speedUnitElement = wait.until(ExpectedConditions.presenceOfElementLocated(speedUnitLocator));
                System.out.println(speedValueElement.getText() + " " + speedUnitElement.getText());
                className = speedValueElement.getAttribute("class");

                if (className != null && className.contains("succeeded")) break;
            }

            WebElement speedValueElement = wait.until(ExpectedConditions.presenceOfElementLocated(speedValueLocator));
            WebElement speedUnitElement = wait.until(ExpectedConditions.presenceOfElementLocated(speedUnitLocator));
            System.out.println("-------------- Final Speed --------------");
            System.out.println(speedValueElement.getText() + " " + speedUnitElement.getText());

        } catch (Exception e) {
            System.out.println("Error during speed test: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
