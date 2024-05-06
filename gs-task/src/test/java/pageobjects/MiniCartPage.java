package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stepdefs.hooks.Hooks;

import static utils.SeleniumCommands.getCommands;

public class MiniCartPage {


    private static final By MINI_CART_PAGE = By.xpath("//div[contains(@class,'mini-cart_content-area')]");
    public  static final By REMOVE_ITEM_BUTTON = By.xpath("//button[@aria-label='remove from bag'][1]");
    public static final By REMOVE_ITEM_MESSAGE = By.xpath("//p[contains(text(),'You removed an item from your bag.')]");
    private final WebDriver driver = Hooks.getDriver();

    public MiniCartPage() {
        getCommands().waitForAndGetVisibleElementLocated(MINI_CART_PAGE);
    }

    public void selectProductFromMiniBag(){
        WebElement article = getCommands().waitForAndGetVisibleElementLocated(By.xpath("(//div[contains(@class,'mini-cart_content-area')]//a)[1]"));
        String articleUrl = article.getAttribute("href");
        driver.get(articleUrl);
    }

}

