package hcyclewala.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hcyclewala.pageobjects.OrderPage;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@routerlink, 'cart')]")
    WebElement cartHeader;

    @FindBy(xpath = "//button[contains(@routerlink, 'myorders')]")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy)
    {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }
    public void waitForWebElementToAppear(WebElement findBy)
    {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9000));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException
    {
        //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9000));
        // wait.until(ExpectedConditions.invisibilityOf(ele));
        Thread.sleep(1000);
    }

    public void waitForElementClickable(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9000));
        wait.until(ExpectedConditions.elementToBeClickable(ele));

    }

    public void goToCart()
    {
        cartHeader.click();     
    }

    public OrderPage gotoOrders()
    {
        ordersHeader.click();
        OrderPage orderpage = new OrderPage(driver);
        return orderpage;
    }
}
