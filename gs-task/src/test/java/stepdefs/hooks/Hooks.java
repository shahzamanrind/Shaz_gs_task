package stepdefs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Hooks {

  private static WebDriver driver;

  @Before
  public void setup() {
    if (driver == null) {
      System.setProperty("webdriver.chrome.driver", "C:/Users/shahz/OneDrive/Desktop/Shahz_Project/gs-task copy/resources/chromedriver.exe");
      driver = new ChromeDriver();
      driver.get("https://uk.gymshark.com/");
      WebElement cookiePopup = driver.findElement(By.id("onetrust-accept-btn-handler"));
      cookiePopup.click();
      driver.manage().window().maximize();
    }
  }

  @After
  public void teardown() {
    if (driver != null) {
      driver.close();
      driver = null;
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }

}
