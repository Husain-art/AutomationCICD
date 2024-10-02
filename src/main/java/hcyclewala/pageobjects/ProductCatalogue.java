package hcyclewala.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hcyclewala.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//section[@id='products']//div[@class='row']/div")
    List<WebElement> products;

    By productBy = By.xpath("//section[@id='products']//div[@class='row']/div");
    By addToCart = By.xpath(".//button[2]");
    By ToastMessage = By.xpath("//div[@id='toast-container']");

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    public List<WebElement> getProductList(){
        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String ProductName)
    {       
        WebElement prod = getProductList().stream().filter(product-> product.findElement(By.xpath(".//b")).getText().equals(ProductName)).findFirst().orElse(null);       
        return prod;
    }

    public CartPage addProductToCart(String ProductName) throws InterruptedException
    {
        WebElement prod = getProductByName(ProductName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(ToastMessage);
        waitForElementToDisappear(spinner);
        CartPage cp = new CartPage(driver);
        return cp;
    }

}
