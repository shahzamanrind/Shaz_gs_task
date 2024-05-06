package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static utils.SeleniumCommands.getCommands;


public class ProductDisplayPage {

  private static final By PRODUCT_DISPLAY_PAGE = By.cssSelector("[data-locator-id='pdp-page']");
  private static final By SIZE_SMALL_BUTTON = By.cssSelector("[data-locator-id='pdp-size-s-select']");
  private static final By ADD_TO_BAG_BUTTON = By.cssSelector("[data-locator-id='pdp-addToBag-submit']");
  private static final By COOKIE_BANNER = By.id("onetrust-accept-btn-handler");

  public ProductDisplayPage() {
    getCommands().waitForAndGetVisibleElementLocated(PRODUCT_DISPLAY_PAGE);
  }

  public void closeCookieBanner() {
    getCommands().waitForAndClickOnElementLocated(COOKIE_BANNER);
  }


  public void selectAddToBag() {
    By addToBagButton = By.xpath("//button[contains(text(),'Add to bag')]");
    getCommands().waitForAndClickOnElementLocated(addToBagButton);
  }

  public void SelectSize(String size){
    String sizeElementXpath = "//button[normalize-space()='"+size+"']";
    By sizeElement = By.xpath(sizeElementXpath);
    getCommands().waitForAndClickOnElementLocated(sizeElement);
  }

  public String selectProduct(String productName){
    String productNameElementXpath = "(//a[@aria-label='"+productName+"'])[1]";
    WebElement product = getCommands().waitForAndGetVisibleElementLocated(By.xpath(productNameElementXpath));
    return product.getAttribute("href");
  }


}
