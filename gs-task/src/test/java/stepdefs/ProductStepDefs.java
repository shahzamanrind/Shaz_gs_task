package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BagPage;
import pageobjects.ProductDisplayPage;
import pageobjects.ProductListingPage;
import pageobjects.MiniCartPage;
import stepdefs.hooks.Hooks;

import java.time.Duration;
import java.util.*;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductStepDefs {

  private final WebDriver driver;

  public ProductStepDefs(){
    this.driver = Hooks.getDriver();
  }

  @Given("the user is on {string} product page")
  public void NavigateToProductPage(String productPage){
    if(Objects.equals(productPage, "men")){
      driver.get("https://uk.gymshark.com/collections/all-products/mens");
    } else if (Objects.equals(productPage, "women")) {
      driver.get("https://uk.gymshark.com/collections/all-products/womens");
    }
  }

  @When("the user selects {string} product")
  public void SelectProduct(String productName){
    ProductListingPage productListingPage = new ProductListingPage();
    String productLink = productListingPage.selectProduct(productName);
    driver.get(productLink);
  }

  @When("the user selects size {string}")
  public void SelectSize(String size){
    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    productDisplayPage.SelectSize(size);
  }

  @When("the user add the product to bag")
  public void AddProductToBag(){
    ProductDisplayPage productDisplayPage = new ProductDisplayPage();
    productDisplayPage.selectAddToBag();
  }

  @When("the user selects another product")
  public void addMultipleProducts(){
    MiniCartPage miniCartPage = new MiniCartPage();
    miniCartPage.selectProductFromMiniBag();
  }

  @Then("the product should appear in the Bag")
  public void theProductShouldAppearInTheBag() {




  }

  @Then("the user removes a product")
  public void removeProduct(){
    String removeItemToastMessageXpath =  "//p[contains(text(),'You removed an item from your bag.')]";
    BagPage bagPage = new BagPage();
    bagPage.removeItem();
    assertThat("You removed an item from your bag.").isEqualTo(driver.findElement(By.xpath(removeItemToastMessageXpath)).getText());
  }

  @Then("the user verifies total amount")
  public void verifyAmount(){
    BagPage bagPage = new BagPage();
    WebElement totalValueElement = driver.findElement(By.xpath("//p[@data-locator-id='miniBag-totalValue-read']"));
    String totalValue = totalValueElement.getText();
    double total = bagPage.getTotal();
    assertThat(total).isEqualTo(Double.parseDouble(totalValue.replace('£',' ').trim()));
  }
}
