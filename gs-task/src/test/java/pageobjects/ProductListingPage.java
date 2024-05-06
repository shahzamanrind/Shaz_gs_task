package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static utils.SeleniumCommands.getCommands;


public class ProductListingPage {
    private static final By PRODUCT_LISTING_PAGE = By.cssSelector("[data-locator-id='filtersPanel-component']");

    public ProductListingPage() {
        getCommands().waitForAndGetVisibleElementLocated(PRODUCT_LISTING_PAGE);
    }

    public String selectProduct(String productName){
        String productNameElementXpath = "(//a[@aria-label='"+productName+"'])[1]";
        WebElement product = getCommands().waitForAndGetVisibleElementLocated(By.xpath(productNameElementXpath));
        return product.getAttribute("href");
    }


}
