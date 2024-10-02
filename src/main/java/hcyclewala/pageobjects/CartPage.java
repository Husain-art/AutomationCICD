package hcyclewala.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hcyclewala.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//div[@class='cart']//h3")
    List<WebElement> products;

    @FindBy(css = ".subtotal button")
    WebElement CheckOut;

    public Boolean VerifyProducts(String productName)
    {
        Boolean match = products.stream().anyMatch(product->product.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout()
    {
        CheckOut.click();
        CheckoutPage cop = new CheckoutPage(driver);
        return cop;
    }

}
