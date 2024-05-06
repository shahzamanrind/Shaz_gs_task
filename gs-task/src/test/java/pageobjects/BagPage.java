package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stepdefs.hooks.Hooks;
import java.util.ArrayList;
import java.util.List;

import static utils.SeleniumCommands.getCommands;

public class BagPage {


  private static final By BAG_PAGE = By.cssSelector("[data-locator-id='miniBag-component']");
  public  static final By REMOVE_ITEM_BUTTON = By.xpath("//button[@aria-label='remove from bag'][1]");
  public static final By REMOVE_ITEM_MESSAGE = By.xpath("//p[contains(text(),'You removed an item from your bag.')]");
  private final WebDriver driver = Hooks.getDriver();

  public BagPage() {
    getCommands().waitForAndGetVisibleElementLocated(BAG_PAGE);
  }

  public void removeItem(){
    getCommands().waitForAndClickOnElementLocated(REMOVE_ITEM_BUTTON);
    getCommands().waitForAndGetVisibleElementLocated(REMOVE_ITEM_MESSAGE);
  }

  private List<String> cleaseTextFromList(List<String> prices){
    List<String> newPrices = new ArrayList<>();
    for(String price : prices){
      String newPrice = price.replace('£',' ');
      newPrice = newPrice.trim();
      newPrice = newPrice.split(" ")[0];
      newPrices.add(newPrice);
    }
    return newPrices;
  }

  private Double computeTotal(List<String> prices){
    List<String> newPrices = cleaseTextFromList(prices);
    double total = 0.0;
    for(String price : newPrices){
      total += Double.parseDouble(price);
    }
    return total;
  }

  public void selectProductFromMiniBag(){
    WebElement article = driver.findElement(By.xpath("(//section[contains(@class,'minibag-view')]//article[@aria-label='product']//a)[1]"));
    String articleUrl = article.getAttribute("href");
    driver.get(articleUrl);
  }

  public Double getTotal(){
    List<String> prices = new ArrayList<>();
    List<WebElement> regularPrices = driver.findElements(By.xpath("//p[contains(@aria-label,'Regular price £')]"));
    List<WebElement> salesPrices = driver.findElements(By.xpath("//p[contains(@aria-label,'Sale price £')]"));
    for (WebElement regularPrice : regularPrices) {
      prices.add(regularPrice.getText());
    }
    for (WebElement salePrice : salesPrices) {
      prices.add(salePrice.getText());
    }
    return this.computeTotal(prices);

  }

}
