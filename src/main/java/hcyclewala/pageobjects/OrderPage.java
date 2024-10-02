package hcyclewala.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hcyclewala.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

    @FindBy(xpath = "//table[@class='table table-bordered table-hover ng-star-inserted']/tbody/tr/td[2]")
    List<WebElement> products;

    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName)
    {
        Boolean match = products.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }


}
